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

import org.opencps.dossiermgt.model.Dossier;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.Date;
import java.util.List;

/**
 * The persistence utility for the dossier service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.DossierPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see DossierPersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.DossierPersistenceImpl
 * @generated
 */
@ProviderType
public class DossierUtil {
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
	public static void clearCache(Dossier dossier) {
		getPersistence().clearCache(dossier);
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
	public static List<Dossier> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Dossier> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Dossier> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Dossier update(Dossier dossier) {
		return getPersistence().update(dossier);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Dossier update(Dossier dossier, ServiceContext serviceContext) {
		return getPersistence().update(dossier, serviceContext);
	}

	/**
	* Returns all the dossiers where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching dossiers
	*/
	public static List<Dossier> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the dossiers where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public static List<Dossier> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the dossiers where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByUuid(String uuid, int start, int end,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossiers where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByUuid(String uuid, int start, int end,
		OrderByComparator<Dossier> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first dossier in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByUuid_First(String uuid,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first dossier in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByUuid_First(String uuid,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByUuid_Last(String uuid,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByUuid_Last(String uuid,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where uuid = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public static Dossier[] findByUuid_PrevAndNext(long dossierId, String uuid,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByUuid_PrevAndNext(dossierId, uuid, orderByComparator);
	}

	/**
	* Removes all the dossiers where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of dossiers where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching dossiers
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the dossier where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDossierException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the dossier where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the dossier where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the dossier where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the dossier that was removed
	*/
	public static Dossier removeByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of dossiers where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching dossiers
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the dossiers where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching dossiers
	*/
	public static List<Dossier> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the dossiers where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public static List<Dossier> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the dossiers where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossiers where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first dossier in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public static Dossier[] findByUuid_C_PrevAndNext(long dossierId,
		String uuid, long companyId,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(dossierId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the dossiers where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of dossiers where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching dossiers
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the dossiers where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the matching dossiers
	*/
	public static List<Dossier> findByG_UID(long groupId, long userId) {
		return getPersistence().findByG_UID(groupId, userId);
	}

	/**
	* Returns a range of all the dossiers where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public static List<Dossier> findByG_UID(long groupId, long userId,
		int start, int end) {
		return getPersistence().findByG_UID(groupId, userId, start, end);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByG_UID(long groupId, long userId,
		int start, int end, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .findByG_UID(groupId, userId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByG_UID(long groupId, long userId,
		int start, int end, OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_UID(groupId, userId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByG_UID_First(long groupId, long userId,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_UID_First(groupId, userId, orderByComparator);
	}

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByG_UID_First(long groupId, long userId,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByG_UID_First(groupId, userId, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByG_UID_Last(long groupId, long userId,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_UID_Last(groupId, userId, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByG_UID_Last(long groupId, long userId,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByG_UID_Last(groupId, userId, orderByComparator);
	}

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public static Dossier[] findByG_UID_PrevAndNext(long dossierId,
		long groupId, long userId, OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_UID_PrevAndNext(dossierId, groupId, userId,
			orderByComparator);
	}

	/**
	* Removes all the dossiers where groupId = &#63; and userId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	*/
	public static void removeByG_UID(long groupId, long userId) {
		getPersistence().removeByG_UID(groupId, userId);
	}

	/**
	* Returns the number of dossiers where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the number of matching dossiers
	*/
	public static int countByG_UID(long groupId, long userId) {
		return getPersistence().countByG_UID(groupId, userId);
	}

	/**
	* Returns the dossier where groupId = &#63; and referenceUid = &#63; or throws a {@link NoSuchDossierException} if it could not be found.
	*
	* @param groupId the group ID
	* @param referenceUid the reference uid
	* @return the matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByG_REF(long groupId, String referenceUid)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence().findByG_REF(groupId, referenceUid);
	}

	/**
	* Returns the dossier where groupId = &#63; and referenceUid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param referenceUid the reference uid
	* @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByG_REF(long groupId, String referenceUid) {
		return getPersistence().fetchByG_REF(groupId, referenceUid);
	}

	/**
	* Returns the dossier where groupId = &#63; and referenceUid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param referenceUid the reference uid
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByG_REF(long groupId, String referenceUid,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByG_REF(groupId, referenceUid, retrieveFromCache);
	}

	/**
	* Removes the dossier where groupId = &#63; and referenceUid = &#63; from the database.
	*
	* @param groupId the group ID
	* @param referenceUid the reference uid
	* @return the dossier that was removed
	*/
	public static Dossier removeByG_REF(long groupId, String referenceUid)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence().removeByG_REF(groupId, referenceUid);
	}

	/**
	* Returns the number of dossiers where groupId = &#63; and referenceUid = &#63;.
	*
	* @param groupId the group ID
	* @param referenceUid the reference uid
	* @return the number of matching dossiers
	*/
	public static int countByG_REF(long groupId, String referenceUid) {
		return getPersistence().countByG_REF(groupId, referenceUid);
	}

	/**
	* Returns all the dossiers where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @return the matching dossiers
	*/
	public static List<Dossier> findByG_SC(long groupId, String serviceCode) {
		return getPersistence().findByG_SC(groupId, serviceCode);
	}

	/**
	* Returns a range of all the dossiers where groupId = &#63; and serviceCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public static List<Dossier> findByG_SC(long groupId, String serviceCode,
		int start, int end) {
		return getPersistence().findByG_SC(groupId, serviceCode, start, end);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and serviceCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByG_SC(long groupId, String serviceCode,
		int start, int end, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .findByG_SC(groupId, serviceCode, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and serviceCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByG_SC(long groupId, String serviceCode,
		int start, int end, OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_SC(groupId, serviceCode, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByG_SC_First(long groupId, String serviceCode,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_SC_First(groupId, serviceCode, orderByComparator);
	}

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByG_SC_First(long groupId, String serviceCode,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByG_SC_First(groupId, serviceCode, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByG_SC_Last(long groupId, String serviceCode,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_SC_Last(groupId, serviceCode, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByG_SC_Last(long groupId, String serviceCode,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByG_SC_Last(groupId, serviceCode, orderByComparator);
	}

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and serviceCode = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public static Dossier[] findByG_SC_PrevAndNext(long dossierId,
		long groupId, String serviceCode,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_SC_PrevAndNext(dossierId, groupId, serviceCode,
			orderByComparator);
	}

	/**
	* Removes all the dossiers where groupId = &#63; and serviceCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	*/
	public static void removeByG_SC(long groupId, String serviceCode) {
		getPersistence().removeByG_SC(groupId, serviceCode);
	}

	/**
	* Returns the number of dossiers where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @return the number of matching dossiers
	*/
	public static int countByG_SC(long groupId, String serviceCode) {
		return getPersistence().countByG_SC(groupId, serviceCode);
	}

	/**
	* Returns all the dossiers where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @return the matching dossiers
	*/
	public static List<Dossier> findByG_GAC(long groupId, String serviceCode) {
		return getPersistence().findByG_GAC(groupId, serviceCode);
	}

	/**
	* Returns a range of all the dossiers where groupId = &#63; and serviceCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public static List<Dossier> findByG_GAC(long groupId, String serviceCode,
		int start, int end) {
		return getPersistence().findByG_GAC(groupId, serviceCode, start, end);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and serviceCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByG_GAC(long groupId, String serviceCode,
		int start, int end, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .findByG_GAC(groupId, serviceCode, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and serviceCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByG_GAC(long groupId, String serviceCode,
		int start, int end, OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_GAC(groupId, serviceCode, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByG_GAC_First(long groupId, String serviceCode,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_GAC_First(groupId, serviceCode, orderByComparator);
	}

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByG_GAC_First(long groupId, String serviceCode,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByG_GAC_First(groupId, serviceCode, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByG_GAC_Last(long groupId, String serviceCode,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_GAC_Last(groupId, serviceCode, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByG_GAC_Last(long groupId, String serviceCode,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByG_GAC_Last(groupId, serviceCode, orderByComparator);
	}

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and serviceCode = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public static Dossier[] findByG_GAC_PrevAndNext(long dossierId,
		long groupId, String serviceCode,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_GAC_PrevAndNext(dossierId, groupId, serviceCode,
			orderByComparator);
	}

	/**
	* Removes all the dossiers where groupId = &#63; and serviceCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	*/
	public static void removeByG_GAC(long groupId, String serviceCode) {
		getPersistence().removeByG_GAC(groupId, serviceCode);
	}

	/**
	* Returns the number of dossiers where groupId = &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @return the number of matching dossiers
	*/
	public static int countByG_GAC(long groupId, String serviceCode) {
		return getPersistence().countByG_GAC(groupId, serviceCode);
	}

	/**
	* Returns all the dossiers where groupId = &#63; and dossierTemplateNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierTemplateNo the dossier template no
	* @return the matching dossiers
	*/
	public static List<Dossier> findByG_DTN(long groupId,
		String dossierTemplateNo) {
		return getPersistence().findByG_DTN(groupId, dossierTemplateNo);
	}

	/**
	* Returns a range of all the dossiers where groupId = &#63; and dossierTemplateNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierTemplateNo the dossier template no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public static List<Dossier> findByG_DTN(long groupId,
		String dossierTemplateNo, int start, int end) {
		return getPersistence()
				   .findByG_DTN(groupId, dossierTemplateNo, start, end);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and dossierTemplateNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierTemplateNo the dossier template no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByG_DTN(long groupId,
		String dossierTemplateNo, int start, int end,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .findByG_DTN(groupId, dossierTemplateNo, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and dossierTemplateNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierTemplateNo the dossier template no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByG_DTN(long groupId,
		String dossierTemplateNo, int start, int end,
		OrderByComparator<Dossier> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_DTN(groupId, dossierTemplateNo, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and dossierTemplateNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierTemplateNo the dossier template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByG_DTN_First(long groupId,
		String dossierTemplateNo, OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_DTN_First(groupId, dossierTemplateNo,
			orderByComparator);
	}

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and dossierTemplateNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierTemplateNo the dossier template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByG_DTN_First(long groupId,
		String dossierTemplateNo, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByG_DTN_First(groupId, dossierTemplateNo,
			orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and dossierTemplateNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierTemplateNo the dossier template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByG_DTN_Last(long groupId,
		String dossierTemplateNo, OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_DTN_Last(groupId, dossierTemplateNo,
			orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and dossierTemplateNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierTemplateNo the dossier template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByG_DTN_Last(long groupId,
		String dossierTemplateNo, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByG_DTN_Last(groupId, dossierTemplateNo,
			orderByComparator);
	}

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and dossierTemplateNo = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param groupId the group ID
	* @param dossierTemplateNo the dossier template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public static Dossier[] findByG_DTN_PrevAndNext(long dossierId,
		long groupId, String dossierTemplateNo,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_DTN_PrevAndNext(dossierId, groupId,
			dossierTemplateNo, orderByComparator);
	}

	/**
	* Removes all the dossiers where groupId = &#63; and dossierTemplateNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dossierTemplateNo the dossier template no
	*/
	public static void removeByG_DTN(long groupId, String dossierTemplateNo) {
		getPersistence().removeByG_DTN(groupId, dossierTemplateNo);
	}

	/**
	* Returns the number of dossiers where groupId = &#63; and dossierTemplateNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierTemplateNo the dossier template no
	* @return the number of matching dossiers
	*/
	public static int countByG_DTN(long groupId, String dossierTemplateNo) {
		return getPersistence().countByG_DTN(groupId, dossierTemplateNo);
	}

	/**
	* Returns all the dossiers where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @return the matching dossiers
	*/
	public static List<Dossier> findByG_DID(long groupId, long dossierId) {
		return getPersistence().findByG_DID(groupId, dossierId);
	}

	/**
	* Returns a range of all the dossiers where groupId = &#63; and dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public static List<Dossier> findByG_DID(long groupId, long dossierId,
		int start, int end) {
		return getPersistence().findByG_DID(groupId, dossierId, start, end);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByG_DID(long groupId, long dossierId,
		int start, int end, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .findByG_DID(groupId, dossierId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByG_DID(long groupId, long dossierId,
		int start, int end, OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_DID(groupId, dossierId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByG_DID_First(long groupId, long dossierId,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_DID_First(groupId, dossierId, orderByComparator);
	}

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByG_DID_First(long groupId, long dossierId,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByG_DID_First(groupId, dossierId, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByG_DID_Last(long groupId, long dossierId,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_DID_Last(groupId, dossierId, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByG_DID_Last(long groupId, long dossierId,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByG_DID_Last(groupId, dossierId, orderByComparator);
	}

	/**
	* Removes all the dossiers where groupId = &#63; and dossierId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	*/
	public static void removeByG_DID(long groupId, long dossierId) {
		getPersistence().removeByG_DID(groupId, dossierId);
	}

	/**
	* Returns the number of dossiers where groupId = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param dossierId the dossier ID
	* @return the number of matching dossiers
	*/
	public static int countByG_DID(long groupId, long dossierId) {
		return getPersistence().countByG_DID(groupId, dossierId);
	}

	/**
	* Returns all the dossiers where originality &ne; &#63; and dossierStatus = &#63;.
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @return the matching dossiers
	*/
	public static List<Dossier> findByNOTO_DS(int originality,
		String dossierStatus) {
		return getPersistence().findByNOTO_DS(originality, dossierStatus);
	}

	/**
	* Returns a range of all the dossiers where originality &ne; &#63; and dossierStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public static List<Dossier> findByNOTO_DS(int originality,
		String dossierStatus, int start, int end) {
		return getPersistence()
				   .findByNOTO_DS(originality, dossierStatus, start, end);
	}

	/**
	* Returns an ordered range of all the dossiers where originality &ne; &#63; and dossierStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByNOTO_DS(int originality,
		String dossierStatus, int start, int end,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .findByNOTO_DS(originality, dossierStatus, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossiers where originality &ne; &#63; and dossierStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByNOTO_DS(int originality,
		String dossierStatus, int start, int end,
		OrderByComparator<Dossier> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByNOTO_DS(originality, dossierStatus, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier in the ordered set where originality &ne; &#63; and dossierStatus = &#63;.
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByNOTO_DS_First(int originality,
		String dossierStatus, OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByNOTO_DS_First(originality, dossierStatus,
			orderByComparator);
	}

	/**
	* Returns the first dossier in the ordered set where originality &ne; &#63; and dossierStatus = &#63;.
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByNOTO_DS_First(int originality,
		String dossierStatus, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByNOTO_DS_First(originality, dossierStatus,
			orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where originality &ne; &#63; and dossierStatus = &#63;.
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByNOTO_DS_Last(int originality,
		String dossierStatus, OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByNOTO_DS_Last(originality, dossierStatus,
			orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where originality &ne; &#63; and dossierStatus = &#63;.
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByNOTO_DS_Last(int originality,
		String dossierStatus, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByNOTO_DS_Last(originality, dossierStatus,
			orderByComparator);
	}

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where originality &ne; &#63; and dossierStatus = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public static Dossier[] findByNOTO_DS_PrevAndNext(long dossierId,
		int originality, String dossierStatus,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByNOTO_DS_PrevAndNext(dossierId, originality,
			dossierStatus, orderByComparator);
	}

	/**
	* Returns all the dossiers where originality &ne; all &#63; and dossierStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param originalities the originalities
	* @param dossierStatus the dossier status
	* @return the matching dossiers
	*/
	public static List<Dossier> findByNOTO_DS(int[] originalities,
		String dossierStatus) {
		return getPersistence().findByNOTO_DS(originalities, dossierStatus);
	}

	/**
	* Returns a range of all the dossiers where originality &ne; all &#63; and dossierStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param originalities the originalities
	* @param dossierStatus the dossier status
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public static List<Dossier> findByNOTO_DS(int[] originalities,
		String dossierStatus, int start, int end) {
		return getPersistence()
				   .findByNOTO_DS(originalities, dossierStatus, start, end);
	}

	/**
	* Returns an ordered range of all the dossiers where originality &ne; all &#63; and dossierStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param originalities the originalities
	* @param dossierStatus the dossier status
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByNOTO_DS(int[] originalities,
		String dossierStatus, int start, int end,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .findByNOTO_DS(originalities, dossierStatus, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossiers where originality &ne; &#63; and dossierStatus = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByNOTO_DS(int[] originalities,
		String dossierStatus, int start, int end,
		OrderByComparator<Dossier> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByNOTO_DS(originalities, dossierStatus, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the dossiers where originality &ne; &#63; and dossierStatus = &#63; from the database.
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	*/
	public static void removeByNOTO_DS(int originality, String dossierStatus) {
		getPersistence().removeByNOTO_DS(originality, dossierStatus);
	}

	/**
	* Returns the number of dossiers where originality &ne; &#63; and dossierStatus = &#63;.
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @return the number of matching dossiers
	*/
	public static int countByNOTO_DS(int originality, String dossierStatus) {
		return getPersistence().countByNOTO_DS(originality, dossierStatus);
	}

	/**
	* Returns the number of dossiers where originality &ne; all &#63; and dossierStatus = &#63;.
	*
	* @param originalities the originalities
	* @param dossierStatus the dossier status
	* @return the number of matching dossiers
	*/
	public static int countByNOTO_DS(int[] originalities, String dossierStatus) {
		return getPersistence().countByNOTO_DS(originalities, dossierStatus);
	}

	/**
	* Returns all the dossiers where originality = &#63; and dossierStatus = &#63;.
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @return the matching dossiers
	*/
	public static List<Dossier> findByF_OG_DS(int originality,
		String dossierStatus) {
		return getPersistence().findByF_OG_DS(originality, dossierStatus);
	}

	/**
	* Returns a range of all the dossiers where originality = &#63; and dossierStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public static List<Dossier> findByF_OG_DS(int originality,
		String dossierStatus, int start, int end) {
		return getPersistence()
				   .findByF_OG_DS(originality, dossierStatus, start, end);
	}

	/**
	* Returns an ordered range of all the dossiers where originality = &#63; and dossierStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByF_OG_DS(int originality,
		String dossierStatus, int start, int end,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .findByF_OG_DS(originality, dossierStatus, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossiers where originality = &#63; and dossierStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByF_OG_DS(int originality,
		String dossierStatus, int start, int end,
		OrderByComparator<Dossier> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_OG_DS(originality, dossierStatus, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier in the ordered set where originality = &#63; and dossierStatus = &#63;.
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByF_OG_DS_First(int originality,
		String dossierStatus, OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByF_OG_DS_First(originality, dossierStatus,
			orderByComparator);
	}

	/**
	* Returns the first dossier in the ordered set where originality = &#63; and dossierStatus = &#63;.
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByF_OG_DS_First(int originality,
		String dossierStatus, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByF_OG_DS_First(originality, dossierStatus,
			orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where originality = &#63; and dossierStatus = &#63;.
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByF_OG_DS_Last(int originality,
		String dossierStatus, OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByF_OG_DS_Last(originality, dossierStatus,
			orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where originality = &#63; and dossierStatus = &#63;.
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByF_OG_DS_Last(int originality,
		String dossierStatus, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByF_OG_DS_Last(originality, dossierStatus,
			orderByComparator);
	}

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where originality = &#63; and dossierStatus = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public static Dossier[] findByF_OG_DS_PrevAndNext(long dossierId,
		int originality, String dossierStatus,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByF_OG_DS_PrevAndNext(dossierId, originality,
			dossierStatus, orderByComparator);
	}

	/**
	* Removes all the dossiers where originality = &#63; and dossierStatus = &#63; from the database.
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	*/
	public static void removeByF_OG_DS(int originality, String dossierStatus) {
		getPersistence().removeByF_OG_DS(originality, dossierStatus);
	}

	/**
	* Returns the number of dossiers where originality = &#63; and dossierStatus = &#63;.
	*
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @return the number of matching dossiers
	*/
	public static int countByF_OG_DS(int originality, String dossierStatus) {
		return getPersistence().countByF_OG_DS(originality, dossierStatus);
	}

	/**
	* Returns all the dossiers where groupId = &#63; and companyId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus &ne; &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @return the matching dossiers
	*/
	public static List<Dossier> findByG_C_GAC_SC_DTNO_NOTDS(long groupId,
		long companyId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus) {
		return getPersistence()
				   .findByG_C_GAC_SC_DTNO_NOTDS(groupId, companyId,
			govAgencyCode, serviceCode, dossierTemplateNo, dossierStatus);
	}

	/**
	* Returns a range of all the dossiers where groupId = &#63; and companyId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public static List<Dossier> findByG_C_GAC_SC_DTNO_NOTDS(long groupId,
		long companyId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus, int start, int end) {
		return getPersistence()
				   .findByG_C_GAC_SC_DTNO_NOTDS(groupId, companyId,
			govAgencyCode, serviceCode, dossierTemplateNo, dossierStatus,
			start, end);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and companyId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByG_C_GAC_SC_DTNO_NOTDS(long groupId,
		long companyId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus, int start, int end,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .findByG_C_GAC_SC_DTNO_NOTDS(groupId, companyId,
			govAgencyCode, serviceCode, dossierTemplateNo, dossierStatus,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and companyId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByG_C_GAC_SC_DTNO_NOTDS(long groupId,
		long companyId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus, int start, int end,
		OrderByComparator<Dossier> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_C_GAC_SC_DTNO_NOTDS(groupId, companyId,
			govAgencyCode, serviceCode, dossierTemplateNo, dossierStatus,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and companyId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus &ne; &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByG_C_GAC_SC_DTNO_NOTDS_First(long groupId,
		long companyId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_C_GAC_SC_DTNO_NOTDS_First(groupId, companyId,
			govAgencyCode, serviceCode, dossierTemplateNo, dossierStatus,
			orderByComparator);
	}

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and companyId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus &ne; &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByG_C_GAC_SC_DTNO_NOTDS_First(long groupId,
		long companyId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByG_C_GAC_SC_DTNO_NOTDS_First(groupId, companyId,
			govAgencyCode, serviceCode, dossierTemplateNo, dossierStatus,
			orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and companyId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus &ne; &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByG_C_GAC_SC_DTNO_NOTDS_Last(long groupId,
		long companyId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_C_GAC_SC_DTNO_NOTDS_Last(groupId, companyId,
			govAgencyCode, serviceCode, dossierTemplateNo, dossierStatus,
			orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and companyId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus &ne; &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByG_C_GAC_SC_DTNO_NOTDS_Last(long groupId,
		long companyId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByG_C_GAC_SC_DTNO_NOTDS_Last(groupId, companyId,
			govAgencyCode, serviceCode, dossierTemplateNo, dossierStatus,
			orderByComparator);
	}

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and companyId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus &ne; &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param groupId the group ID
	* @param companyId the company ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public static Dossier[] findByG_C_GAC_SC_DTNO_NOTDS_PrevAndNext(
		long dossierId, long groupId, long companyId, String govAgencyCode,
		String serviceCode, String dossierTemplateNo, String dossierStatus,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_C_GAC_SC_DTNO_NOTDS_PrevAndNext(dossierId, groupId,
			companyId, govAgencyCode, serviceCode, dossierTemplateNo,
			dossierStatus, orderByComparator);
	}

	/**
	* Removes all the dossiers where groupId = &#63; and companyId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus &ne; &#63; from the database.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	*/
	public static void removeByG_C_GAC_SC_DTNO_NOTDS(long groupId,
		long companyId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus) {
		getPersistence()
			.removeByG_C_GAC_SC_DTNO_NOTDS(groupId, companyId, govAgencyCode,
			serviceCode, dossierTemplateNo, dossierStatus);
	}

	/**
	* Returns the number of dossiers where groupId = &#63; and companyId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus &ne; &#63;.
	*
	* @param groupId the group ID
	* @param companyId the company ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @return the number of matching dossiers
	*/
	public static int countByG_C_GAC_SC_DTNO_NOTDS(long groupId,
		long companyId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus) {
		return getPersistence()
				   .countByG_C_GAC_SC_DTNO_NOTDS(groupId, companyId,
			govAgencyCode, serviceCode, dossierTemplateNo, dossierStatus);
	}

	/**
	* Returns the dossier where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierId = &#63; or throws a {@link NoSuchDossierException} if it could not be found.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierId the dossier ID
	* @return the matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByF_GID_GOV_DID(long groupId,
		String govAgencyCode, String serviceCode, long dossierId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByF_GID_GOV_DID(groupId, govAgencyCode, serviceCode,
			dossierId);
	}

	/**
	* Returns the dossier where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierId the dossier ID
	* @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByF_GID_GOV_DID(long groupId,
		String govAgencyCode, String serviceCode, long dossierId) {
		return getPersistence()
				   .fetchByF_GID_GOV_DID(groupId, govAgencyCode, serviceCode,
			dossierId);
	}

	/**
	* Returns the dossier where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierId the dossier ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByF_GID_GOV_DID(long groupId,
		String govAgencyCode, String serviceCode, long dossierId,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_GID_GOV_DID(groupId, govAgencyCode, serviceCode,
			dossierId, retrieveFromCache);
	}

	/**
	* Removes the dossier where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierId the dossier ID
	* @return the dossier that was removed
	*/
	public static Dossier removeByF_GID_GOV_DID(long groupId,
		String govAgencyCode, String serviceCode, long dossierId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .removeByF_GID_GOV_DID(groupId, govAgencyCode, serviceCode,
			dossierId);
	}

	/**
	* Returns the number of dossiers where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierId = &#63;.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierId the dossier ID
	* @return the number of matching dossiers
	*/
	public static int countByF_GID_GOV_DID(long groupId, String govAgencyCode,
		String serviceCode, long dossierId) {
		return getPersistence()
				   .countByF_GID_GOV_DID(groupId, govAgencyCode, serviceCode,
			dossierId);
	}

	/**
	* Returns all the dossiers where groupId = &#63; and originality &ne; &#63; and dossierStatus = &#63; and serviceCode = &#63; and govAgencyCode = &#63;.
	*
	* @param groupId the group ID
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @return the matching dossiers
	*/
	public static List<Dossier> findByG_NOTO_DS_SC_GC(long groupId,
		int originality, String dossierStatus, String serviceCode,
		String govAgencyCode) {
		return getPersistence()
				   .findByG_NOTO_DS_SC_GC(groupId, originality, dossierStatus,
			serviceCode, govAgencyCode);
	}

	/**
	* Returns a range of all the dossiers where groupId = &#63; and originality &ne; &#63; and dossierStatus = &#63; and serviceCode = &#63; and govAgencyCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public static List<Dossier> findByG_NOTO_DS_SC_GC(long groupId,
		int originality, String dossierStatus, String serviceCode,
		String govAgencyCode, int start, int end) {
		return getPersistence()
				   .findByG_NOTO_DS_SC_GC(groupId, originality, dossierStatus,
			serviceCode, govAgencyCode, start, end);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and originality &ne; &#63; and dossierStatus = &#63; and serviceCode = &#63; and govAgencyCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByG_NOTO_DS_SC_GC(long groupId,
		int originality, String dossierStatus, String serviceCode,
		String govAgencyCode, int start, int end,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .findByG_NOTO_DS_SC_GC(groupId, originality, dossierStatus,
			serviceCode, govAgencyCode, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and originality &ne; &#63; and dossierStatus = &#63; and serviceCode = &#63; and govAgencyCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByG_NOTO_DS_SC_GC(long groupId,
		int originality, String dossierStatus, String serviceCode,
		String govAgencyCode, int start, int end,
		OrderByComparator<Dossier> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_NOTO_DS_SC_GC(groupId, originality, dossierStatus,
			serviceCode, govAgencyCode, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and originality &ne; &#63; and dossierStatus = &#63; and serviceCode = &#63; and govAgencyCode = &#63;.
	*
	* @param groupId the group ID
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByG_NOTO_DS_SC_GC_First(long groupId,
		int originality, String dossierStatus, String serviceCode,
		String govAgencyCode, OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_NOTO_DS_SC_GC_First(groupId, originality,
			dossierStatus, serviceCode, govAgencyCode, orderByComparator);
	}

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and originality &ne; &#63; and dossierStatus = &#63; and serviceCode = &#63; and govAgencyCode = &#63;.
	*
	* @param groupId the group ID
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByG_NOTO_DS_SC_GC_First(long groupId,
		int originality, String dossierStatus, String serviceCode,
		String govAgencyCode, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByG_NOTO_DS_SC_GC_First(groupId, originality,
			dossierStatus, serviceCode, govAgencyCode, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and originality &ne; &#63; and dossierStatus = &#63; and serviceCode = &#63; and govAgencyCode = &#63;.
	*
	* @param groupId the group ID
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByG_NOTO_DS_SC_GC_Last(long groupId,
		int originality, String dossierStatus, String serviceCode,
		String govAgencyCode, OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_NOTO_DS_SC_GC_Last(groupId, originality,
			dossierStatus, serviceCode, govAgencyCode, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and originality &ne; &#63; and dossierStatus = &#63; and serviceCode = &#63; and govAgencyCode = &#63;.
	*
	* @param groupId the group ID
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByG_NOTO_DS_SC_GC_Last(long groupId,
		int originality, String dossierStatus, String serviceCode,
		String govAgencyCode, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByG_NOTO_DS_SC_GC_Last(groupId, originality,
			dossierStatus, serviceCode, govAgencyCode, orderByComparator);
	}

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and originality &ne; &#63; and dossierStatus = &#63; and serviceCode = &#63; and govAgencyCode = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param groupId the group ID
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public static Dossier[] findByG_NOTO_DS_SC_GC_PrevAndNext(long dossierId,
		long groupId, int originality, String dossierStatus,
		String serviceCode, String govAgencyCode,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_NOTO_DS_SC_GC_PrevAndNext(dossierId, groupId,
			originality, dossierStatus, serviceCode, govAgencyCode,
			orderByComparator);
	}

	/**
	* Removes all the dossiers where groupId = &#63; and originality &ne; &#63; and dossierStatus = &#63; and serviceCode = &#63; and govAgencyCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	*/
	public static void removeByG_NOTO_DS_SC_GC(long groupId, int originality,
		String dossierStatus, String serviceCode, String govAgencyCode) {
		getPersistence()
			.removeByG_NOTO_DS_SC_GC(groupId, originality, dossierStatus,
			serviceCode, govAgencyCode);
	}

	/**
	* Returns the number of dossiers where groupId = &#63; and originality &ne; &#63; and dossierStatus = &#63; and serviceCode = &#63; and govAgencyCode = &#63;.
	*
	* @param groupId the group ID
	* @param originality the originality
	* @param dossierStatus the dossier status
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @return the number of matching dossiers
	*/
	public static int countByG_NOTO_DS_SC_GC(long groupId, int originality,
		String dossierStatus, String serviceCode, String govAgencyCode) {
		return getPersistence()
				   .countByG_NOTO_DS_SC_GC(groupId, originality, dossierStatus,
			serviceCode, govAgencyCode);
	}

	/**
	* Returns all the dossiers where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and applicantIdType = &#63; and originality = &#63;.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param applicantIdType the applicant ID type
	* @param originality the originality
	* @return the matching dossiers
	*/
	public static List<Dossier> findByGID_GC_SC_DTN_DS_APP_ORI(long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, String applicantIdType, int originality) {
		return getPersistence()
				   .findByGID_GC_SC_DTN_DS_APP_ORI(groupId, govAgencyCode,
			serviceCode, dossierTemplateNo, dossierStatus, applicantIdType,
			originality);
	}

	/**
	* Returns a range of all the dossiers where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and applicantIdType = &#63; and originality = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param applicantIdType the applicant ID type
	* @param originality the originality
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public static List<Dossier> findByGID_GC_SC_DTN_DS_APP_ORI(long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, String applicantIdType, int originality,
		int start, int end) {
		return getPersistence()
				   .findByGID_GC_SC_DTN_DS_APP_ORI(groupId, govAgencyCode,
			serviceCode, dossierTemplateNo, dossierStatus, applicantIdType,
			originality, start, end);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and applicantIdType = &#63; and originality = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param applicantIdType the applicant ID type
	* @param originality the originality
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByGID_GC_SC_DTN_DS_APP_ORI(long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, String applicantIdType, int originality,
		int start, int end, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .findByGID_GC_SC_DTN_DS_APP_ORI(groupId, govAgencyCode,
			serviceCode, dossierTemplateNo, dossierStatus, applicantIdType,
			originality, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and applicantIdType = &#63; and originality = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param applicantIdType the applicant ID type
	* @param originality the originality
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByGID_GC_SC_DTN_DS_APP_ORI(long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, String applicantIdType, int originality,
		int start, int end, OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGID_GC_SC_DTN_DS_APP_ORI(groupId, govAgencyCode,
			serviceCode, dossierTemplateNo, dossierStatus, applicantIdType,
			originality, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and applicantIdType = &#63; and originality = &#63;.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param applicantIdType the applicant ID type
	* @param originality the originality
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByGID_GC_SC_DTN_DS_APP_ORI_First(long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, String applicantIdType, int originality,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByGID_GC_SC_DTN_DS_APP_ORI_First(groupId,
			govAgencyCode, serviceCode, dossierTemplateNo, dossierStatus,
			applicantIdType, originality, orderByComparator);
	}

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and applicantIdType = &#63; and originality = &#63;.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param applicantIdType the applicant ID type
	* @param originality the originality
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByGID_GC_SC_DTN_DS_APP_ORI_First(long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, String applicantIdType, int originality,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByGID_GC_SC_DTN_DS_APP_ORI_First(groupId,
			govAgencyCode, serviceCode, dossierTemplateNo, dossierStatus,
			applicantIdType, originality, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and applicantIdType = &#63; and originality = &#63;.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param applicantIdType the applicant ID type
	* @param originality the originality
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByGID_GC_SC_DTN_DS_APP_ORI_Last(long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, String applicantIdType, int originality,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByGID_GC_SC_DTN_DS_APP_ORI_Last(groupId, govAgencyCode,
			serviceCode, dossierTemplateNo, dossierStatus, applicantIdType,
			originality, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and applicantIdType = &#63; and originality = &#63;.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param applicantIdType the applicant ID type
	* @param originality the originality
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByGID_GC_SC_DTN_DS_APP_ORI_Last(long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, String applicantIdType, int originality,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByGID_GC_SC_DTN_DS_APP_ORI_Last(groupId,
			govAgencyCode, serviceCode, dossierTemplateNo, dossierStatus,
			applicantIdType, originality, orderByComparator);
	}

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and applicantIdType = &#63; and originality = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param applicantIdType the applicant ID type
	* @param originality the originality
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public static Dossier[] findByGID_GC_SC_DTN_DS_APP_ORI_PrevAndNext(
		long dossierId, long groupId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus, String applicantIdType,
		int originality, OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByGID_GC_SC_DTN_DS_APP_ORI_PrevAndNext(dossierId,
			groupId, govAgencyCode, serviceCode, dossierTemplateNo,
			dossierStatus, applicantIdType, originality, orderByComparator);
	}

	/**
	* Returns all the dossiers where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = any &#63; and applicantIdType = &#63; and originality = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatuses the dossier statuses
	* @param applicantIdType the applicant ID type
	* @param originality the originality
	* @return the matching dossiers
	*/
	public static List<Dossier> findByGID_GC_SC_DTN_DS_APP_ORI(long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String[] dossierStatuses, String applicantIdType, int originality) {
		return getPersistence()
				   .findByGID_GC_SC_DTN_DS_APP_ORI(groupId, govAgencyCode,
			serviceCode, dossierTemplateNo, dossierStatuses, applicantIdType,
			originality);
	}

	/**
	* Returns a range of all the dossiers where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = any &#63; and applicantIdType = &#63; and originality = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatuses the dossier statuses
	* @param applicantIdType the applicant ID type
	* @param originality the originality
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public static List<Dossier> findByGID_GC_SC_DTN_DS_APP_ORI(long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String[] dossierStatuses, String applicantIdType, int originality,
		int start, int end) {
		return getPersistence()
				   .findByGID_GC_SC_DTN_DS_APP_ORI(groupId, govAgencyCode,
			serviceCode, dossierTemplateNo, dossierStatuses, applicantIdType,
			originality, start, end);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = any &#63; and applicantIdType = &#63; and originality = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatuses the dossier statuses
	* @param applicantIdType the applicant ID type
	* @param originality the originality
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByGID_GC_SC_DTN_DS_APP_ORI(long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String[] dossierStatuses, String applicantIdType, int originality,
		int start, int end, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .findByGID_GC_SC_DTN_DS_APP_ORI(groupId, govAgencyCode,
			serviceCode, dossierTemplateNo, dossierStatuses, applicantIdType,
			originality, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and applicantIdType = &#63; and originality = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param applicantIdType the applicant ID type
	* @param originality the originality
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByGID_GC_SC_DTN_DS_APP_ORI(long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String[] dossierStatuses, String applicantIdType, int originality,
		int start, int end, OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGID_GC_SC_DTN_DS_APP_ORI(groupId, govAgencyCode,
			serviceCode, dossierTemplateNo, dossierStatuses, applicantIdType,
			originality, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the dossiers where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and applicantIdType = &#63; and originality = &#63; from the database.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param applicantIdType the applicant ID type
	* @param originality the originality
	*/
	public static void removeByGID_GC_SC_DTN_DS_APP_ORI(long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, String applicantIdType, int originality) {
		getPersistence()
			.removeByGID_GC_SC_DTN_DS_APP_ORI(groupId, govAgencyCode,
			serviceCode, dossierTemplateNo, dossierStatus, applicantIdType,
			originality);
	}

	/**
	* Returns the number of dossiers where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and applicantIdType = &#63; and originality = &#63;.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param applicantIdType the applicant ID type
	* @param originality the originality
	* @return the number of matching dossiers
	*/
	public static int countByGID_GC_SC_DTN_DS_APP_ORI(long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, String applicantIdType, int originality) {
		return getPersistence()
				   .countByGID_GC_SC_DTN_DS_APP_ORI(groupId, govAgencyCode,
			serviceCode, dossierTemplateNo, dossierStatus, applicantIdType,
			originality);
	}

	/**
	* Returns the number of dossiers where groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = any &#63; and applicantIdType = &#63; and originality = &#63;.
	*
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatuses the dossier statuses
	* @param applicantIdType the applicant ID type
	* @param originality the originality
	* @return the number of matching dossiers
	*/
	public static int countByGID_GC_SC_DTN_DS_APP_ORI(long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String[] dossierStatuses, String applicantIdType, int originality) {
		return getPersistence()
				   .countByGID_GC_SC_DTN_DS_APP_ORI(groupId, govAgencyCode,
			serviceCode, dossierTemplateNo, dossierStatuses, applicantIdType,
			originality);
	}

	/**
	* Returns all the dossiers where groupId = &#63; and originDossierId = &#63;.
	*
	* @param groupId the group ID
	* @param originDossierId the origin dossier ID
	* @return the matching dossiers
	*/
	public static List<Dossier> findByG_O_DID(long groupId, long originDossierId) {
		return getPersistence().findByG_O_DID(groupId, originDossierId);
	}

	/**
	* Returns a range of all the dossiers where groupId = &#63; and originDossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param originDossierId the origin dossier ID
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public static List<Dossier> findByG_O_DID(long groupId,
		long originDossierId, int start, int end) {
		return getPersistence()
				   .findByG_O_DID(groupId, originDossierId, start, end);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and originDossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param originDossierId the origin dossier ID
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByG_O_DID(long groupId,
		long originDossierId, int start, int end,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .findByG_O_DID(groupId, originDossierId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and originDossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param originDossierId the origin dossier ID
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByG_O_DID(long groupId,
		long originDossierId, int start, int end,
		OrderByComparator<Dossier> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_O_DID(groupId, originDossierId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and originDossierId = &#63;.
	*
	* @param groupId the group ID
	* @param originDossierId the origin dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByG_O_DID_First(long groupId,
		long originDossierId, OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_O_DID_First(groupId, originDossierId,
			orderByComparator);
	}

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and originDossierId = &#63;.
	*
	* @param groupId the group ID
	* @param originDossierId the origin dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByG_O_DID_First(long groupId,
		long originDossierId, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByG_O_DID_First(groupId, originDossierId,
			orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and originDossierId = &#63;.
	*
	* @param groupId the group ID
	* @param originDossierId the origin dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByG_O_DID_Last(long groupId,
		long originDossierId, OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_O_DID_Last(groupId, originDossierId,
			orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and originDossierId = &#63;.
	*
	* @param groupId the group ID
	* @param originDossierId the origin dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByG_O_DID_Last(long groupId,
		long originDossierId, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByG_O_DID_Last(groupId, originDossierId,
			orderByComparator);
	}

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and originDossierId = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param groupId the group ID
	* @param originDossierId the origin dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public static Dossier[] findByG_O_DID_PrevAndNext(long dossierId,
		long groupId, long originDossierId,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_O_DID_PrevAndNext(dossierId, groupId,
			originDossierId, orderByComparator);
	}

	/**
	* Removes all the dossiers where groupId = &#63; and originDossierId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param originDossierId the origin dossier ID
	*/
	public static void removeByG_O_DID(long groupId, long originDossierId) {
		getPersistence().removeByG_O_DID(groupId, originDossierId);
	}

	/**
	* Returns the number of dossiers where groupId = &#63; and originDossierId = &#63;.
	*
	* @param groupId the group ID
	* @param originDossierId the origin dossier ID
	* @return the number of matching dossiers
	*/
	public static int countByG_O_DID(long groupId, long originDossierId) {
		return getPersistence().countByG_O_DID(groupId, originDossierId);
	}

	/**
	* Returns all the dossiers where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching dossiers
	*/
	public static List<Dossier> findByG(long groupId) {
		return getPersistence().findByG(groupId);
	}

	/**
	* Returns a range of all the dossiers where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public static List<Dossier> findByG(long groupId, int start, int end) {
		return getPersistence().findByG(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByG(long groupId, int start, int end,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence().findByG(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByG(long groupId, int start, int end,
		OrderByComparator<Dossier> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByG(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first dossier in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByG_First(long groupId,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence().findByG_First(groupId, orderByComparator);
	}

	/**
	* Returns the first dossier in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByG_First(long groupId,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence().fetchByG_First(groupId, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByG_Last(long groupId,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence().findByG_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByG_Last(long groupId,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence().fetchByG_Last(groupId, orderByComparator);
	}

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public static Dossier[] findByG_PrevAndNext(long dossierId, long groupId,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_PrevAndNext(dossierId, groupId, orderByComparator);
	}

	/**
	* Removes all the dossiers where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByG(long groupId) {
		getPersistence().removeByG(groupId);
	}

	/**
	* Returns the number of dossiers where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching dossiers
	*/
	public static int countByG(long groupId) {
		return getPersistence().countByG(groupId);
	}

	/**
	* Returns all the dossiers where dossierNo = &#63; and applicantIdNo = &#63;.
	*
	* @param dossierNo the dossier no
	* @param applicantIdNo the applicant ID no
	* @return the matching dossiers
	*/
	public static List<Dossier> findByDN_AN(String dossierNo,
		String applicantIdNo) {
		return getPersistence().findByDN_AN(dossierNo, applicantIdNo);
	}

	/**
	* Returns a range of all the dossiers where dossierNo = &#63; and applicantIdNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierNo the dossier no
	* @param applicantIdNo the applicant ID no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public static List<Dossier> findByDN_AN(String dossierNo,
		String applicantIdNo, int start, int end) {
		return getPersistence().findByDN_AN(dossierNo, applicantIdNo, start, end);
	}

	/**
	* Returns an ordered range of all the dossiers where dossierNo = &#63; and applicantIdNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierNo the dossier no
	* @param applicantIdNo the applicant ID no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByDN_AN(String dossierNo,
		String applicantIdNo, int start, int end,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .findByDN_AN(dossierNo, applicantIdNo, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossiers where dossierNo = &#63; and applicantIdNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierNo the dossier no
	* @param applicantIdNo the applicant ID no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByDN_AN(String dossierNo,
		String applicantIdNo, int start, int end,
		OrderByComparator<Dossier> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByDN_AN(dossierNo, applicantIdNo, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier in the ordered set where dossierNo = &#63; and applicantIdNo = &#63;.
	*
	* @param dossierNo the dossier no
	* @param applicantIdNo the applicant ID no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByDN_AN_First(String dossierNo,
		String applicantIdNo, OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByDN_AN_First(dossierNo, applicantIdNo,
			orderByComparator);
	}

	/**
	* Returns the first dossier in the ordered set where dossierNo = &#63; and applicantIdNo = &#63;.
	*
	* @param dossierNo the dossier no
	* @param applicantIdNo the applicant ID no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByDN_AN_First(String dossierNo,
		String applicantIdNo, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByDN_AN_First(dossierNo, applicantIdNo,
			orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where dossierNo = &#63; and applicantIdNo = &#63;.
	*
	* @param dossierNo the dossier no
	* @param applicantIdNo the applicant ID no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByDN_AN_Last(String dossierNo,
		String applicantIdNo, OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByDN_AN_Last(dossierNo, applicantIdNo, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where dossierNo = &#63; and applicantIdNo = &#63;.
	*
	* @param dossierNo the dossier no
	* @param applicantIdNo the applicant ID no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByDN_AN_Last(String dossierNo,
		String applicantIdNo, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByDN_AN_Last(dossierNo, applicantIdNo,
			orderByComparator);
	}

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where dossierNo = &#63; and applicantIdNo = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param dossierNo the dossier no
	* @param applicantIdNo the applicant ID no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public static Dossier[] findByDN_AN_PrevAndNext(long dossierId,
		String dossierNo, String applicantIdNo,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByDN_AN_PrevAndNext(dossierId, dossierNo,
			applicantIdNo, orderByComparator);
	}

	/**
	* Removes all the dossiers where dossierNo = &#63; and applicantIdNo = &#63; from the database.
	*
	* @param dossierNo the dossier no
	* @param applicantIdNo the applicant ID no
	*/
	public static void removeByDN_AN(String dossierNo, String applicantIdNo) {
		getPersistence().removeByDN_AN(dossierNo, applicantIdNo);
	}

	/**
	* Returns the number of dossiers where dossierNo = &#63; and applicantIdNo = &#63;.
	*
	* @param dossierNo the dossier no
	* @param applicantIdNo the applicant ID no
	* @return the number of matching dossiers
	*/
	public static int countByDN_AN(String dossierNo, String applicantIdNo) {
		return getPersistence().countByDN_AN(dossierNo, applicantIdNo);
	}

	/**
	* Returns all the dossiers where viaPostal = &#63;.
	*
	* @param viaPostal the via postal
	* @return the matching dossiers
	*/
	public static List<Dossier> findByVIAPOSTAL(int viaPostal) {
		return getPersistence().findByVIAPOSTAL(viaPostal);
	}

	/**
	* Returns a range of all the dossiers where viaPostal = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param viaPostal the via postal
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public static List<Dossier> findByVIAPOSTAL(int viaPostal, int start,
		int end) {
		return getPersistence().findByVIAPOSTAL(viaPostal, start, end);
	}

	/**
	* Returns an ordered range of all the dossiers where viaPostal = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param viaPostal the via postal
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByVIAPOSTAL(int viaPostal, int start,
		int end, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .findByVIAPOSTAL(viaPostal, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossiers where viaPostal = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param viaPostal the via postal
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByVIAPOSTAL(int viaPostal, int start,
		int end, OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByVIAPOSTAL(viaPostal, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first dossier in the ordered set where viaPostal = &#63;.
	*
	* @param viaPostal the via postal
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByVIAPOSTAL_First(int viaPostal,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByVIAPOSTAL_First(viaPostal, orderByComparator);
	}

	/**
	* Returns the first dossier in the ordered set where viaPostal = &#63;.
	*
	* @param viaPostal the via postal
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByVIAPOSTAL_First(int viaPostal,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByVIAPOSTAL_First(viaPostal, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where viaPostal = &#63;.
	*
	* @param viaPostal the via postal
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByVIAPOSTAL_Last(int viaPostal,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByVIAPOSTAL_Last(viaPostal, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where viaPostal = &#63;.
	*
	* @param viaPostal the via postal
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByVIAPOSTAL_Last(int viaPostal,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByVIAPOSTAL_Last(viaPostal, orderByComparator);
	}

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where viaPostal = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param viaPostal the via postal
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public static Dossier[] findByVIAPOSTAL_PrevAndNext(long dossierId,
		int viaPostal, OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByVIAPOSTAL_PrevAndNext(dossierId, viaPostal,
			orderByComparator);
	}

	/**
	* Removes all the dossiers where viaPostal = &#63; from the database.
	*
	* @param viaPostal the via postal
	*/
	public static void removeByVIAPOSTAL(int viaPostal) {
		getPersistence().removeByVIAPOSTAL(viaPostal);
	}

	/**
	* Returns the number of dossiers where viaPostal = &#63;.
	*
	* @param viaPostal the via postal
	* @return the number of matching dossiers
	*/
	public static int countByVIAPOSTAL(int viaPostal) {
		return getPersistence().countByVIAPOSTAL(viaPostal);
	}

	/**
	* Returns all the dossiers where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierActionId = &#63; and originality = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierActionId the dossier action ID
	* @param originality the originality
	* @return the matching dossiers
	*/
	public static List<Dossier> findByU_G_GAC_SC_DTNO_DAI_O(long userId,
		long groupId, String govAgencyCode, String serviceCode,
		long dossierActionId, int originality) {
		return getPersistence()
				   .findByU_G_GAC_SC_DTNO_DAI_O(userId, groupId, govAgencyCode,
			serviceCode, dossierActionId, originality);
	}

	/**
	* Returns a range of all the dossiers where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierActionId = &#63; and originality = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierActionId the dossier action ID
	* @param originality the originality
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public static List<Dossier> findByU_G_GAC_SC_DTNO_DAI_O(long userId,
		long groupId, String govAgencyCode, String serviceCode,
		long dossierActionId, int originality, int start, int end) {
		return getPersistence()
				   .findByU_G_GAC_SC_DTNO_DAI_O(userId, groupId, govAgencyCode,
			serviceCode, dossierActionId, originality, start, end);
	}

	/**
	* Returns an ordered range of all the dossiers where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierActionId = &#63; and originality = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierActionId the dossier action ID
	* @param originality the originality
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByU_G_GAC_SC_DTNO_DAI_O(long userId,
		long groupId, String govAgencyCode, String serviceCode,
		long dossierActionId, int originality, int start, int end,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .findByU_G_GAC_SC_DTNO_DAI_O(userId, groupId, govAgencyCode,
			serviceCode, dossierActionId, originality, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossiers where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierActionId = &#63; and originality = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierActionId the dossier action ID
	* @param originality the originality
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByU_G_GAC_SC_DTNO_DAI_O(long userId,
		long groupId, String govAgencyCode, String serviceCode,
		long dossierActionId, int originality, int start, int end,
		OrderByComparator<Dossier> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByU_G_GAC_SC_DTNO_DAI_O(userId, groupId, govAgencyCode,
			serviceCode, dossierActionId, originality, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier in the ordered set where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierActionId = &#63; and originality = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierActionId the dossier action ID
	* @param originality the originality
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByU_G_GAC_SC_DTNO_DAI_O_First(long userId,
		long groupId, String govAgencyCode, String serviceCode,
		long dossierActionId, int originality,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByU_G_GAC_SC_DTNO_DAI_O_First(userId, groupId,
			govAgencyCode, serviceCode, dossierActionId, originality,
			orderByComparator);
	}

	/**
	* Returns the first dossier in the ordered set where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierActionId = &#63; and originality = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierActionId the dossier action ID
	* @param originality the originality
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByU_G_GAC_SC_DTNO_DAI_O_First(long userId,
		long groupId, String govAgencyCode, String serviceCode,
		long dossierActionId, int originality,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByU_G_GAC_SC_DTNO_DAI_O_First(userId, groupId,
			govAgencyCode, serviceCode, dossierActionId, originality,
			orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierActionId = &#63; and originality = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierActionId the dossier action ID
	* @param originality the originality
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByU_G_GAC_SC_DTNO_DAI_O_Last(long userId,
		long groupId, String govAgencyCode, String serviceCode,
		long dossierActionId, int originality,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByU_G_GAC_SC_DTNO_DAI_O_Last(userId, groupId,
			govAgencyCode, serviceCode, dossierActionId, originality,
			orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierActionId = &#63; and originality = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierActionId the dossier action ID
	* @param originality the originality
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByU_G_GAC_SC_DTNO_DAI_O_Last(long userId,
		long groupId, String govAgencyCode, String serviceCode,
		long dossierActionId, int originality,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByU_G_GAC_SC_DTNO_DAI_O_Last(userId, groupId,
			govAgencyCode, serviceCode, dossierActionId, originality,
			orderByComparator);
	}

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierActionId = &#63; and originality = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierActionId the dossier action ID
	* @param originality the originality
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public static Dossier[] findByU_G_GAC_SC_DTNO_DAI_O_PrevAndNext(
		long dossierId, long userId, long groupId, String govAgencyCode,
		String serviceCode, long dossierActionId, int originality,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByU_G_GAC_SC_DTNO_DAI_O_PrevAndNext(dossierId, userId,
			groupId, govAgencyCode, serviceCode, dossierActionId, originality,
			orderByComparator);
	}

	/**
	* Removes all the dossiers where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierActionId = &#63; and originality = &#63; from the database.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierActionId the dossier action ID
	* @param originality the originality
	*/
	public static void removeByU_G_GAC_SC_DTNO_DAI_O(long userId, long groupId,
		String govAgencyCode, String serviceCode, long dossierActionId,
		int originality) {
		getPersistence()
			.removeByU_G_GAC_SC_DTNO_DAI_O(userId, groupId, govAgencyCode,
			serviceCode, dossierActionId, originality);
	}

	/**
	* Returns the number of dossiers where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierActionId = &#63; and originality = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierActionId the dossier action ID
	* @param originality the originality
	* @return the number of matching dossiers
	*/
	public static int countByU_G_GAC_SC_DTNO_DAI_O(long userId, long groupId,
		String govAgencyCode, String serviceCode, long dossierActionId,
		int originality) {
		return getPersistence()
				   .countByU_G_GAC_SC_DTNO_DAI_O(userId, groupId,
			govAgencyCode, serviceCode, dossierActionId, originality);
	}

	/**
	* Returns the dossier where groupId = &#63; and dossierNo = &#63; or throws a {@link NoSuchDossierException} if it could not be found.
	*
	* @param groupId the group ID
	* @param dossierNo the dossier no
	* @return the matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByG_DN(long groupId, String dossierNo)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence().findByG_DN(groupId, dossierNo);
	}

	/**
	* Returns the dossier where groupId = &#63; and dossierNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param dossierNo the dossier no
	* @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByG_DN(long groupId, String dossierNo) {
		return getPersistence().fetchByG_DN(groupId, dossierNo);
	}

	/**
	* Returns the dossier where groupId = &#63; and dossierNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param dossierNo the dossier no
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByG_DN(long groupId, String dossierNo,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByG_DN(groupId, dossierNo, retrieveFromCache);
	}

	/**
	* Removes the dossier where groupId = &#63; and dossierNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dossierNo the dossier no
	* @return the dossier that was removed
	*/
	public static Dossier removeByG_DN(long groupId, String dossierNo)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence().removeByG_DN(groupId, dossierNo);
	}

	/**
	* Returns the number of dossiers where groupId = &#63; and dossierNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierNo the dossier no
	* @return the number of matching dossiers
	*/
	public static int countByG_DN(long groupId, String dossierNo) {
		return getPersistence().countByG_DN(groupId, dossierNo);
	}

	/**
	* Returns the dossier where dossierNo = &#63; or throws a {@link NoSuchDossierException} if it could not be found.
	*
	* @param dossierNo the dossier no
	* @return the matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByDO_NO(String dossierNo)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence().findByDO_NO(dossierNo);
	}

	/**
	* Returns the dossier where dossierNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param dossierNo the dossier no
	* @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByDO_NO(String dossierNo) {
		return getPersistence().fetchByDO_NO(dossierNo);
	}

	/**
	* Returns the dossier where dossierNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param dossierNo the dossier no
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByDO_NO(String dossierNo,
		boolean retrieveFromCache) {
		return getPersistence().fetchByDO_NO(dossierNo, retrieveFromCache);
	}

	/**
	* Removes the dossier where dossierNo = &#63; from the database.
	*
	* @param dossierNo the dossier no
	* @return the dossier that was removed
	*/
	public static Dossier removeByDO_NO(String dossierNo)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence().removeByDO_NO(dossierNo);
	}

	/**
	* Returns the number of dossiers where dossierNo = &#63;.
	*
	* @param dossierNo the dossier no
	* @return the number of matching dossiers
	*/
	public static int countByDO_NO(String dossierNo) {
		return getPersistence().countByDO_NO(dossierNo);
	}

	/**
	* Returns the dossier where dossierNo = &#63; and groupId = &#63; or throws a {@link NoSuchDossierException} if it could not be found.
	*
	* @param dossierNo the dossier no
	* @param groupId the group ID
	* @return the matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByDO_NO_GROUP(String dossierNo, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence().findByDO_NO_GROUP(dossierNo, groupId);
	}

	/**
	* Returns the dossier where dossierNo = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param dossierNo the dossier no
	* @param groupId the group ID
	* @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByDO_NO_GROUP(String dossierNo, long groupId) {
		return getPersistence().fetchByDO_NO_GROUP(dossierNo, groupId);
	}

	/**
	* Returns the dossier where dossierNo = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param dossierNo the dossier no
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByDO_NO_GROUP(String dossierNo, long groupId,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByDO_NO_GROUP(dossierNo, groupId, retrieveFromCache);
	}

	/**
	* Removes the dossier where dossierNo = &#63; and groupId = &#63; from the database.
	*
	* @param dossierNo the dossier no
	* @param groupId the group ID
	* @return the dossier that was removed
	*/
	public static Dossier removeByDO_NO_GROUP(String dossierNo, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence().removeByDO_NO_GROUP(dossierNo, groupId);
	}

	/**
	* Returns the number of dossiers where dossierNo = &#63; and groupId = &#63;.
	*
	* @param dossierNo the dossier no
	* @param groupId the group ID
	* @return the number of matching dossiers
	*/
	public static int countByDO_NO_GROUP(String dossierNo, long groupId) {
		return getPersistence().countByDO_NO_GROUP(dossierNo, groupId);
	}

	/**
	* Returns the dossier where postalCodeSend = &#63; and groupId = &#63; or throws a {@link NoSuchDossierException} if it could not be found.
	*
	* @param postalCodeSend the postal code send
	* @param groupId the group ID
	* @return the matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByDO_POST_SEND_GROUP(String postalCodeSend,
		long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence().findByDO_POST_SEND_GROUP(postalCodeSend, groupId);
	}

	/**
	* Returns the dossier where postalCodeSend = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param postalCodeSend the postal code send
	* @param groupId the group ID
	* @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByDO_POST_SEND_GROUP(String postalCodeSend,
		long groupId) {
		return getPersistence()
				   .fetchByDO_POST_SEND_GROUP(postalCodeSend, groupId);
	}

	/**
	* Returns the dossier where postalCodeSend = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param postalCodeSend the postal code send
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByDO_POST_SEND_GROUP(String postalCodeSend,
		long groupId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByDO_POST_SEND_GROUP(postalCodeSend, groupId,
			retrieveFromCache);
	}

	/**
	* Removes the dossier where postalCodeSend = &#63; and groupId = &#63; from the database.
	*
	* @param postalCodeSend the postal code send
	* @param groupId the group ID
	* @return the dossier that was removed
	*/
	public static Dossier removeByDO_POST_SEND_GROUP(String postalCodeSend,
		long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .removeByDO_POST_SEND_GROUP(postalCodeSend, groupId);
	}

	/**
	* Returns the number of dossiers where postalCodeSend = &#63; and groupId = &#63;.
	*
	* @param postalCodeSend the postal code send
	* @param groupId the group ID
	* @return the number of matching dossiers
	*/
	public static int countByDO_POST_SEND_GROUP(String postalCodeSend,
		long groupId) {
		return getPersistence()
				   .countByDO_POST_SEND_GROUP(postalCodeSend, groupId);
	}

	/**
	* Returns the dossier where postalCodeReceived = &#63; and groupId = &#63; or throws a {@link NoSuchDossierException} if it could not be found.
	*
	* @param postalCodeReceived the postal code received
	* @param groupId the group ID
	* @return the matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByDO_POST_RECEIVED_GROUP(
		String postalCodeReceived, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByDO_POST_RECEIVED_GROUP(postalCodeReceived, groupId);
	}

	/**
	* Returns the dossier where postalCodeReceived = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param postalCodeReceived the postal code received
	* @param groupId the group ID
	* @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByDO_POST_RECEIVED_GROUP(
		String postalCodeReceived, long groupId) {
		return getPersistence()
				   .fetchByDO_POST_RECEIVED_GROUP(postalCodeReceived, groupId);
	}

	/**
	* Returns the dossier where postalCodeReceived = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param postalCodeReceived the postal code received
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByDO_POST_RECEIVED_GROUP(
		String postalCodeReceived, long groupId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByDO_POST_RECEIVED_GROUP(postalCodeReceived, groupId,
			retrieveFromCache);
	}

	/**
	* Removes the dossier where postalCodeReceived = &#63; and groupId = &#63; from the database.
	*
	* @param postalCodeReceived the postal code received
	* @param groupId the group ID
	* @return the dossier that was removed
	*/
	public static Dossier removeByDO_POST_RECEIVED_GROUP(
		String postalCodeReceived, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .removeByDO_POST_RECEIVED_GROUP(postalCodeReceived, groupId);
	}

	/**
	* Returns the number of dossiers where postalCodeReceived = &#63; and groupId = &#63;.
	*
	* @param postalCodeReceived the postal code received
	* @param groupId the group ID
	* @return the number of matching dossiers
	*/
	public static int countByDO_POST_RECEIVED_GROUP(String postalCodeReceived,
		long groupId) {
		return getPersistence()
				   .countByDO_POST_RECEIVED_GROUP(postalCodeReceived, groupId);
	}

	/**
	* Returns all the dossiers where groupId = &#63; and applicantIdNo = &#63;.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @return the matching dossiers
	*/
	public static List<Dossier> findByG_AN(long groupId, String applicantIdNo) {
		return getPersistence().findByG_AN(groupId, applicantIdNo);
	}

	/**
	* Returns a range of all the dossiers where groupId = &#63; and applicantIdNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public static List<Dossier> findByG_AN(long groupId, String applicantIdNo,
		int start, int end) {
		return getPersistence().findByG_AN(groupId, applicantIdNo, start, end);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and applicantIdNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByG_AN(long groupId, String applicantIdNo,
		int start, int end, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .findByG_AN(groupId, applicantIdNo, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and applicantIdNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByG_AN(long groupId, String applicantIdNo,
		int start, int end, OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_AN(groupId, applicantIdNo, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and applicantIdNo = &#63;.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByG_AN_First(long groupId, String applicantIdNo,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_AN_First(groupId, applicantIdNo, orderByComparator);
	}

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and applicantIdNo = &#63;.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByG_AN_First(long groupId, String applicantIdNo,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByG_AN_First(groupId, applicantIdNo, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and applicantIdNo = &#63;.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByG_AN_Last(long groupId, String applicantIdNo,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_AN_Last(groupId, applicantIdNo, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and applicantIdNo = &#63;.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByG_AN_Last(long groupId, String applicantIdNo,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByG_AN_Last(groupId, applicantIdNo, orderByComparator);
	}

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and applicantIdNo = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public static Dossier[] findByG_AN_PrevAndNext(long dossierId,
		long groupId, String applicantIdNo,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_AN_PrevAndNext(dossierId, groupId, applicantIdNo,
			orderByComparator);
	}

	/**
	* Removes all the dossiers where groupId = &#63; and applicantIdNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	*/
	public static void removeByG_AN(long groupId, String applicantIdNo) {
		getPersistence().removeByG_AN(groupId, applicantIdNo);
	}

	/**
	* Returns the number of dossiers where groupId = &#63; and applicantIdNo = &#63;.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @return the number of matching dossiers
	*/
	public static int countByG_AN(long groupId, String applicantIdNo) {
		return getPersistence().countByG_AN(groupId, applicantIdNo);
	}

	/**
	* Returns all the dossiers where groupId = &#63; and applicantIdNo = &#63; and dossierStatus = &#63;.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param dossierStatus the dossier status
	* @return the matching dossiers
	*/
	public static List<Dossier> findByF_GID_AN_DS(long groupId,
		String applicantIdNo, String dossierStatus) {
		return getPersistence()
				   .findByF_GID_AN_DS(groupId, applicantIdNo, dossierStatus);
	}

	/**
	* Returns a range of all the dossiers where groupId = &#63; and applicantIdNo = &#63; and dossierStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param dossierStatus the dossier status
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public static List<Dossier> findByF_GID_AN_DS(long groupId,
		String applicantIdNo, String dossierStatus, int start, int end) {
		return getPersistence()
				   .findByF_GID_AN_DS(groupId, applicantIdNo, dossierStatus,
			start, end);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and applicantIdNo = &#63; and dossierStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param dossierStatus the dossier status
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByF_GID_AN_DS(long groupId,
		String applicantIdNo, String dossierStatus, int start, int end,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .findByF_GID_AN_DS(groupId, applicantIdNo, dossierStatus,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and applicantIdNo = &#63; and dossierStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param dossierStatus the dossier status
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByF_GID_AN_DS(long groupId,
		String applicantIdNo, String dossierStatus, int start, int end,
		OrderByComparator<Dossier> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_GID_AN_DS(groupId, applicantIdNo, dossierStatus,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and applicantIdNo = &#63; and dossierStatus = &#63;.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByF_GID_AN_DS_First(long groupId,
		String applicantIdNo, String dossierStatus,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByF_GID_AN_DS_First(groupId, applicantIdNo,
			dossierStatus, orderByComparator);
	}

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and applicantIdNo = &#63; and dossierStatus = &#63;.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByF_GID_AN_DS_First(long groupId,
		String applicantIdNo, String dossierStatus,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByF_GID_AN_DS_First(groupId, applicantIdNo,
			dossierStatus, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and applicantIdNo = &#63; and dossierStatus = &#63;.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByF_GID_AN_DS_Last(long groupId,
		String applicantIdNo, String dossierStatus,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByF_GID_AN_DS_Last(groupId, applicantIdNo,
			dossierStatus, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and applicantIdNo = &#63; and dossierStatus = &#63;.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByF_GID_AN_DS_Last(long groupId,
		String applicantIdNo, String dossierStatus,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByF_GID_AN_DS_Last(groupId, applicantIdNo,
			dossierStatus, orderByComparator);
	}

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and applicantIdNo = &#63; and dossierStatus = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public static Dossier[] findByF_GID_AN_DS_PrevAndNext(long dossierId,
		long groupId, String applicantIdNo, String dossierStatus,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByF_GID_AN_DS_PrevAndNext(dossierId, groupId,
			applicantIdNo, dossierStatus, orderByComparator);
	}

	/**
	* Removes all the dossiers where groupId = &#63; and applicantIdNo = &#63; and dossierStatus = &#63; from the database.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param dossierStatus the dossier status
	*/
	public static void removeByF_GID_AN_DS(long groupId, String applicantIdNo,
		String dossierStatus) {
		getPersistence()
			.removeByF_GID_AN_DS(groupId, applicantIdNo, dossierStatus);
	}

	/**
	* Returns the number of dossiers where groupId = &#63; and applicantIdNo = &#63; and dossierStatus = &#63;.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param dossierStatus the dossier status
	* @return the number of matching dossiers
	*/
	public static int countByF_GID_AN_DS(long groupId, String applicantIdNo,
		String dossierStatus) {
		return getPersistence()
				   .countByF_GID_AN_DS(groupId, applicantIdNo, dossierStatus);
	}

	/**
	* Returns the dossier where groupId = &#63; and applicantIdNo = &#63; and serviceCode = &#63; and govAgencyCode = &#63; and dossierTemplateNo = &#63; and originDossierId = &#63; or throws a {@link NoSuchDossierException} if it could not be found.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @param dossierTemplateNo the dossier template no
	* @param originDossierId the origin dossier ID
	* @return the matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByG_AN_SC_GAC_DTNO_ODID(long groupId,
		String applicantIdNo, String serviceCode, String govAgencyCode,
		String dossierTemplateNo, long originDossierId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_AN_SC_GAC_DTNO_ODID(groupId, applicantIdNo,
			serviceCode, govAgencyCode, dossierTemplateNo, originDossierId);
	}

	/**
	* Returns the dossier where groupId = &#63; and applicantIdNo = &#63; and serviceCode = &#63; and govAgencyCode = &#63; and dossierTemplateNo = &#63; and originDossierId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @param dossierTemplateNo the dossier template no
	* @param originDossierId the origin dossier ID
	* @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByG_AN_SC_GAC_DTNO_ODID(long groupId,
		String applicantIdNo, String serviceCode, String govAgencyCode,
		String dossierTemplateNo, long originDossierId) {
		return getPersistence()
				   .fetchByG_AN_SC_GAC_DTNO_ODID(groupId, applicantIdNo,
			serviceCode, govAgencyCode, dossierTemplateNo, originDossierId);
	}

	/**
	* Returns the dossier where groupId = &#63; and applicantIdNo = &#63; and serviceCode = &#63; and govAgencyCode = &#63; and dossierTemplateNo = &#63; and originDossierId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @param dossierTemplateNo the dossier template no
	* @param originDossierId the origin dossier ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByG_AN_SC_GAC_DTNO_ODID(long groupId,
		String applicantIdNo, String serviceCode, String govAgencyCode,
		String dossierTemplateNo, long originDossierId,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByG_AN_SC_GAC_DTNO_ODID(groupId, applicantIdNo,
			serviceCode, govAgencyCode, dossierTemplateNo, originDossierId,
			retrieveFromCache);
	}

	/**
	* Removes the dossier where groupId = &#63; and applicantIdNo = &#63; and serviceCode = &#63; and govAgencyCode = &#63; and dossierTemplateNo = &#63; and originDossierId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @param dossierTemplateNo the dossier template no
	* @param originDossierId the origin dossier ID
	* @return the dossier that was removed
	*/
	public static Dossier removeByG_AN_SC_GAC_DTNO_ODID(long groupId,
		String applicantIdNo, String serviceCode, String govAgencyCode,
		String dossierTemplateNo, long originDossierId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .removeByG_AN_SC_GAC_DTNO_ODID(groupId, applicantIdNo,
			serviceCode, govAgencyCode, dossierTemplateNo, originDossierId);
	}

	/**
	* Returns the number of dossiers where groupId = &#63; and applicantIdNo = &#63; and serviceCode = &#63; and govAgencyCode = &#63; and dossierTemplateNo = &#63; and originDossierId = &#63;.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @param dossierTemplateNo the dossier template no
	* @param originDossierId the origin dossier ID
	* @return the number of matching dossiers
	*/
	public static int countByG_AN_SC_GAC_DTNO_ODID(long groupId,
		String applicantIdNo, String serviceCode, String govAgencyCode,
		String dossierTemplateNo, long originDossierId) {
		return getPersistence()
				   .countByG_AN_SC_GAC_DTNO_ODID(groupId, applicantIdNo,
			serviceCode, govAgencyCode, dossierTemplateNo, originDossierId);
	}

	/**
	* Returns the dossier where groupId = &#63; and applicantIdNo = &#63; and serviceCode = &#63; and govAgencyCode = &#63; and dossierTemplateNo = &#63; and originDossierId = &#63; and serverNo = &#63; or throws a {@link NoSuchDossierException} if it could not be found.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @param dossierTemplateNo the dossier template no
	* @param originDossierId the origin dossier ID
	* @param serverNo the server no
	* @return the matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByG_AN_SC_GAC_DTNO_SN_ODID(long groupId,
		String applicantIdNo, String serviceCode, String govAgencyCode,
		String dossierTemplateNo, long originDossierId, String serverNo)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_AN_SC_GAC_DTNO_SN_ODID(groupId, applicantIdNo,
			serviceCode, govAgencyCode, dossierTemplateNo, originDossierId,
			serverNo);
	}

	/**
	* Returns the dossier where groupId = &#63; and applicantIdNo = &#63; and serviceCode = &#63; and govAgencyCode = &#63; and dossierTemplateNo = &#63; and originDossierId = &#63; and serverNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @param dossierTemplateNo the dossier template no
	* @param originDossierId the origin dossier ID
	* @param serverNo the server no
	* @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByG_AN_SC_GAC_DTNO_SN_ODID(long groupId,
		String applicantIdNo, String serviceCode, String govAgencyCode,
		String dossierTemplateNo, long originDossierId, String serverNo) {
		return getPersistence()
				   .fetchByG_AN_SC_GAC_DTNO_SN_ODID(groupId, applicantIdNo,
			serviceCode, govAgencyCode, dossierTemplateNo, originDossierId,
			serverNo);
	}

	/**
	* Returns the dossier where groupId = &#63; and applicantIdNo = &#63; and serviceCode = &#63; and govAgencyCode = &#63; and dossierTemplateNo = &#63; and originDossierId = &#63; and serverNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @param dossierTemplateNo the dossier template no
	* @param originDossierId the origin dossier ID
	* @param serverNo the server no
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByG_AN_SC_GAC_DTNO_SN_ODID(long groupId,
		String applicantIdNo, String serviceCode, String govAgencyCode,
		String dossierTemplateNo, long originDossierId, String serverNo,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByG_AN_SC_GAC_DTNO_SN_ODID(groupId, applicantIdNo,
			serviceCode, govAgencyCode, dossierTemplateNo, originDossierId,
			serverNo, retrieveFromCache);
	}

	/**
	* Removes the dossier where groupId = &#63; and applicantIdNo = &#63; and serviceCode = &#63; and govAgencyCode = &#63; and dossierTemplateNo = &#63; and originDossierId = &#63; and serverNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @param dossierTemplateNo the dossier template no
	* @param originDossierId the origin dossier ID
	* @param serverNo the server no
	* @return the dossier that was removed
	*/
	public static Dossier removeByG_AN_SC_GAC_DTNO_SN_ODID(long groupId,
		String applicantIdNo, String serviceCode, String govAgencyCode,
		String dossierTemplateNo, long originDossierId, String serverNo)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .removeByG_AN_SC_GAC_DTNO_SN_ODID(groupId, applicantIdNo,
			serviceCode, govAgencyCode, dossierTemplateNo, originDossierId,
			serverNo);
	}

	/**
	* Returns the number of dossiers where groupId = &#63; and applicantIdNo = &#63; and serviceCode = &#63; and govAgencyCode = &#63; and dossierTemplateNo = &#63; and originDossierId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param applicantIdNo the applicant ID no
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @param dossierTemplateNo the dossier template no
	* @param originDossierId the origin dossier ID
	* @param serverNo the server no
	* @return the number of matching dossiers
	*/
	public static int countByG_AN_SC_GAC_DTNO_SN_ODID(long groupId,
		String applicantIdNo, String serviceCode, String govAgencyCode,
		String dossierTemplateNo, long originDossierId, String serverNo) {
		return getPersistence()
				   .countByG_AN_SC_GAC_DTNO_SN_ODID(groupId, applicantIdNo,
			serviceCode, govAgencyCode, dossierTemplateNo, originDossierId,
			serverNo);
	}

	/**
	* Returns all the dossiers where originality = &#63;.
	*
	* @param originality the originality
	* @return the matching dossiers
	*/
	public static List<Dossier> findByO(int originality) {
		return getPersistence().findByO(originality);
	}

	/**
	* Returns a range of all the dossiers where originality = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param originality the originality
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public static List<Dossier> findByO(int originality, int start, int end) {
		return getPersistence().findByO(originality, start, end);
	}

	/**
	* Returns an ordered range of all the dossiers where originality = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param originality the originality
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByO(int originality, int start, int end,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .findByO(originality, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossiers where originality = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param originality the originality
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByO(int originality, int start, int end,
		OrderByComparator<Dossier> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByO(originality, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first dossier in the ordered set where originality = &#63;.
	*
	* @param originality the originality
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByO_First(int originality,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence().findByO_First(originality, orderByComparator);
	}

	/**
	* Returns the first dossier in the ordered set where originality = &#63;.
	*
	* @param originality the originality
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByO_First(int originality,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence().fetchByO_First(originality, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where originality = &#63;.
	*
	* @param originality the originality
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByO_Last(int originality,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence().findByO_Last(originality, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where originality = &#63;.
	*
	* @param originality the originality
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByO_Last(int originality,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence().fetchByO_Last(originality, orderByComparator);
	}

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where originality = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param originality the originality
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public static Dossier[] findByO_PrevAndNext(long dossierId,
		int originality, OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByO_PrevAndNext(dossierId, originality,
			orderByComparator);
	}

	/**
	* Removes all the dossiers where originality = &#63; from the database.
	*
	* @param originality the originality
	*/
	public static void removeByO(int originality) {
		getPersistence().removeByO(originality);
	}

	/**
	* Returns the number of dossiers where originality = &#63;.
	*
	* @param originality the originality
	* @return the number of matching dossiers
	*/
	public static int countByO(int originality) {
		return getPersistence().countByO(originality);
	}

	/**
	* Returns all the dossiers where groupId = &#63; and processNo = &#63;.
	*
	* @param groupId the group ID
	* @param processNo the process no
	* @return the matching dossiers
	*/
	public static List<Dossier> findByGID_PNO(long groupId, String processNo) {
		return getPersistence().findByGID_PNO(groupId, processNo);
	}

	/**
	* Returns a range of all the dossiers where groupId = &#63; and processNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param processNo the process no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public static List<Dossier> findByGID_PNO(long groupId, String processNo,
		int start, int end) {
		return getPersistence().findByGID_PNO(groupId, processNo, start, end);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and processNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param processNo the process no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByGID_PNO(long groupId, String processNo,
		int start, int end, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .findByGID_PNO(groupId, processNo, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and processNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param processNo the process no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByGID_PNO(long groupId, String processNo,
		int start, int end, OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGID_PNO(groupId, processNo, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and processNo = &#63;.
	*
	* @param groupId the group ID
	* @param processNo the process no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByGID_PNO_First(long groupId, String processNo,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByGID_PNO_First(groupId, processNo, orderByComparator);
	}

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and processNo = &#63;.
	*
	* @param groupId the group ID
	* @param processNo the process no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByGID_PNO_First(long groupId, String processNo,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByGID_PNO_First(groupId, processNo, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and processNo = &#63;.
	*
	* @param groupId the group ID
	* @param processNo the process no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByGID_PNO_Last(long groupId, String processNo,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByGID_PNO_Last(groupId, processNo, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and processNo = &#63;.
	*
	* @param groupId the group ID
	* @param processNo the process no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByGID_PNO_Last(long groupId, String processNo,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByGID_PNO_Last(groupId, processNo, orderByComparator);
	}

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and processNo = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param groupId the group ID
	* @param processNo the process no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public static Dossier[] findByGID_PNO_PrevAndNext(long dossierId,
		long groupId, String processNo,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByGID_PNO_PrevAndNext(dossierId, groupId, processNo,
			orderByComparator);
	}

	/**
	* Removes all the dossiers where groupId = &#63; and processNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param processNo the process no
	*/
	public static void removeByGID_PNO(long groupId, String processNo) {
		getPersistence().removeByGID_PNO(groupId, processNo);
	}

	/**
	* Returns the number of dossiers where groupId = &#63; and processNo = &#63;.
	*
	* @param groupId the group ID
	* @param processNo the process no
	* @return the number of matching dossiers
	*/
	public static int countByGID_PNO(long groupId, String processNo) {
		return getPersistence().countByGID_PNO(groupId, processNo);
	}

	/**
	* Returns all the dossiers where dossierStatus &ne; &#63; and modifiedDate &ge; &#63;.
	*
	* @param dossierStatus the dossier status
	* @param modifiedDate the modified date
	* @return the matching dossiers
	*/
	public static List<Dossier> findByNOT_ST_GT_MD(String dossierStatus,
		Date modifiedDate) {
		return getPersistence().findByNOT_ST_GT_MD(dossierStatus, modifiedDate);
	}

	/**
	* Returns a range of all the dossiers where dossierStatus &ne; &#63; and modifiedDate &ge; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierStatus the dossier status
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public static List<Dossier> findByNOT_ST_GT_MD(String dossierStatus,
		Date modifiedDate, int start, int end) {
		return getPersistence()
				   .findByNOT_ST_GT_MD(dossierStatus, modifiedDate, start, end);
	}

	/**
	* Returns an ordered range of all the dossiers where dossierStatus &ne; &#63; and modifiedDate &ge; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierStatus the dossier status
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByNOT_ST_GT_MD(String dossierStatus,
		Date modifiedDate, int start, int end,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .findByNOT_ST_GT_MD(dossierStatus, modifiedDate, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossiers where dossierStatus &ne; &#63; and modifiedDate &ge; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierStatus the dossier status
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByNOT_ST_GT_MD(String dossierStatus,
		Date modifiedDate, int start, int end,
		OrderByComparator<Dossier> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByNOT_ST_GT_MD(dossierStatus, modifiedDate, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier in the ordered set where dossierStatus &ne; &#63; and modifiedDate &ge; &#63;.
	*
	* @param dossierStatus the dossier status
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByNOT_ST_GT_MD_First(String dossierStatus,
		Date modifiedDate, OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByNOT_ST_GT_MD_First(dossierStatus, modifiedDate,
			orderByComparator);
	}

	/**
	* Returns the first dossier in the ordered set where dossierStatus &ne; &#63; and modifiedDate &ge; &#63;.
	*
	* @param dossierStatus the dossier status
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByNOT_ST_GT_MD_First(String dossierStatus,
		Date modifiedDate, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByNOT_ST_GT_MD_First(dossierStatus, modifiedDate,
			orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where dossierStatus &ne; &#63; and modifiedDate &ge; &#63;.
	*
	* @param dossierStatus the dossier status
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByNOT_ST_GT_MD_Last(String dossierStatus,
		Date modifiedDate, OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByNOT_ST_GT_MD_Last(dossierStatus, modifiedDate,
			orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where dossierStatus &ne; &#63; and modifiedDate &ge; &#63;.
	*
	* @param dossierStatus the dossier status
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByNOT_ST_GT_MD_Last(String dossierStatus,
		Date modifiedDate, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByNOT_ST_GT_MD_Last(dossierStatus, modifiedDate,
			orderByComparator);
	}

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where dossierStatus &ne; &#63; and modifiedDate &ge; &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param dossierStatus the dossier status
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public static Dossier[] findByNOT_ST_GT_MD_PrevAndNext(long dossierId,
		String dossierStatus, Date modifiedDate,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByNOT_ST_GT_MD_PrevAndNext(dossierId, dossierStatus,
			modifiedDate, orderByComparator);
	}

	/**
	* Returns all the dossiers where dossierStatus &ne; all &#63; and modifiedDate &ge; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierStatuses the dossier statuses
	* @param modifiedDate the modified date
	* @return the matching dossiers
	*/
	public static List<Dossier> findByNOT_ST_GT_MD(String[] dossierStatuses,
		Date modifiedDate) {
		return getPersistence().findByNOT_ST_GT_MD(dossierStatuses, modifiedDate);
	}

	/**
	* Returns a range of all the dossiers where dossierStatus &ne; all &#63; and modifiedDate &ge; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierStatuses the dossier statuses
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public static List<Dossier> findByNOT_ST_GT_MD(String[] dossierStatuses,
		Date modifiedDate, int start, int end) {
		return getPersistence()
				   .findByNOT_ST_GT_MD(dossierStatuses, modifiedDate, start, end);
	}

	/**
	* Returns an ordered range of all the dossiers where dossierStatus &ne; all &#63; and modifiedDate &ge; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierStatuses the dossier statuses
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByNOT_ST_GT_MD(String[] dossierStatuses,
		Date modifiedDate, int start, int end,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .findByNOT_ST_GT_MD(dossierStatuses, modifiedDate, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossiers where dossierStatus &ne; &#63; and modifiedDate &ge; &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierStatus the dossier status
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByNOT_ST_GT_MD(String[] dossierStatuses,
		Date modifiedDate, int start, int end,
		OrderByComparator<Dossier> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByNOT_ST_GT_MD(dossierStatuses, modifiedDate, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the dossiers where dossierStatus &ne; &#63; and modifiedDate &ge; &#63; from the database.
	*
	* @param dossierStatus the dossier status
	* @param modifiedDate the modified date
	*/
	public static void removeByNOT_ST_GT_MD(String dossierStatus,
		Date modifiedDate) {
		getPersistence().removeByNOT_ST_GT_MD(dossierStatus, modifiedDate);
	}

	/**
	* Returns the number of dossiers where dossierStatus &ne; &#63; and modifiedDate &ge; &#63;.
	*
	* @param dossierStatus the dossier status
	* @param modifiedDate the modified date
	* @return the number of matching dossiers
	*/
	public static int countByNOT_ST_GT_MD(String dossierStatus,
		Date modifiedDate) {
		return getPersistence().countByNOT_ST_GT_MD(dossierStatus, modifiedDate);
	}

	/**
	* Returns the number of dossiers where dossierStatus &ne; all &#63; and modifiedDate &ge; &#63;.
	*
	* @param dossierStatuses the dossier statuses
	* @param modifiedDate the modified date
	* @return the number of matching dossiers
	*/
	public static int countByNOT_ST_GT_MD(String[] dossierStatuses,
		Date modifiedDate) {
		return getPersistence()
				   .countByNOT_ST_GT_MD(dossierStatuses, modifiedDate);
	}

	/**
	* Returns all the dossiers where groupId = &#63; and originDossierNo = &#63;.
	*
	* @param groupId the group ID
	* @param originDossierNo the origin dossier no
	* @return the matching dossiers
	*/
	public static List<Dossier> findByGID_ORI_NO(long groupId,
		String originDossierNo) {
		return getPersistence().findByGID_ORI_NO(groupId, originDossierNo);
	}

	/**
	* Returns a range of all the dossiers where groupId = &#63; and originDossierNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param originDossierNo the origin dossier no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public static List<Dossier> findByGID_ORI_NO(long groupId,
		String originDossierNo, int start, int end) {
		return getPersistence()
				   .findByGID_ORI_NO(groupId, originDossierNo, start, end);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and originDossierNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param originDossierNo the origin dossier no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByGID_ORI_NO(long groupId,
		String originDossierNo, int start, int end,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .findByGID_ORI_NO(groupId, originDossierNo, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and originDossierNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param originDossierNo the origin dossier no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByGID_ORI_NO(long groupId,
		String originDossierNo, int start, int end,
		OrderByComparator<Dossier> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByGID_ORI_NO(groupId, originDossierNo, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and originDossierNo = &#63;.
	*
	* @param groupId the group ID
	* @param originDossierNo the origin dossier no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByGID_ORI_NO_First(long groupId,
		String originDossierNo, OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByGID_ORI_NO_First(groupId, originDossierNo,
			orderByComparator);
	}

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and originDossierNo = &#63;.
	*
	* @param groupId the group ID
	* @param originDossierNo the origin dossier no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByGID_ORI_NO_First(long groupId,
		String originDossierNo, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByGID_ORI_NO_First(groupId, originDossierNo,
			orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and originDossierNo = &#63;.
	*
	* @param groupId the group ID
	* @param originDossierNo the origin dossier no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByGID_ORI_NO_Last(long groupId,
		String originDossierNo, OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByGID_ORI_NO_Last(groupId, originDossierNo,
			orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and originDossierNo = &#63;.
	*
	* @param groupId the group ID
	* @param originDossierNo the origin dossier no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByGID_ORI_NO_Last(long groupId,
		String originDossierNo, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByGID_ORI_NO_Last(groupId, originDossierNo,
			orderByComparator);
	}

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and originDossierNo = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param groupId the group ID
	* @param originDossierNo the origin dossier no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public static Dossier[] findByGID_ORI_NO_PrevAndNext(long dossierId,
		long groupId, String originDossierNo,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByGID_ORI_NO_PrevAndNext(dossierId, groupId,
			originDossierNo, orderByComparator);
	}

	/**
	* Removes all the dossiers where groupId = &#63; and originDossierNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param originDossierNo the origin dossier no
	*/
	public static void removeByGID_ORI_NO(long groupId, String originDossierNo) {
		getPersistence().removeByGID_ORI_NO(groupId, originDossierNo);
	}

	/**
	* Returns the number of dossiers where groupId = &#63; and originDossierNo = &#63;.
	*
	* @param groupId the group ID
	* @param originDossierNo the origin dossier no
	* @return the number of matching dossiers
	*/
	public static int countByGID_ORI_NO(long groupId, String originDossierNo) {
		return getPersistence().countByGID_ORI_NO(groupId, originDossierNo);
	}

	/**
	* Returns all the dossiers where originDossierNo = &#63;.
	*
	* @param originDossierNo the origin dossier no
	* @return the matching dossiers
	*/
	public static List<Dossier> findByORIGIN_NO(String originDossierNo) {
		return getPersistence().findByORIGIN_NO(originDossierNo);
	}

	/**
	* Returns a range of all the dossiers where originDossierNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param originDossierNo the origin dossier no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public static List<Dossier> findByORIGIN_NO(String originDossierNo,
		int start, int end) {
		return getPersistence().findByORIGIN_NO(originDossierNo, start, end);
	}

	/**
	* Returns an ordered range of all the dossiers where originDossierNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param originDossierNo the origin dossier no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByORIGIN_NO(String originDossierNo,
		int start, int end, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .findByORIGIN_NO(originDossierNo, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossiers where originDossierNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param originDossierNo the origin dossier no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByORIGIN_NO(String originDossierNo,
		int start, int end, OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByORIGIN_NO(originDossierNo, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier in the ordered set where originDossierNo = &#63;.
	*
	* @param originDossierNo the origin dossier no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByORIGIN_NO_First(String originDossierNo,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByORIGIN_NO_First(originDossierNo, orderByComparator);
	}

	/**
	* Returns the first dossier in the ordered set where originDossierNo = &#63;.
	*
	* @param originDossierNo the origin dossier no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByORIGIN_NO_First(String originDossierNo,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByORIGIN_NO_First(originDossierNo, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where originDossierNo = &#63;.
	*
	* @param originDossierNo the origin dossier no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByORIGIN_NO_Last(String originDossierNo,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByORIGIN_NO_Last(originDossierNo, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where originDossierNo = &#63;.
	*
	* @param originDossierNo the origin dossier no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByORIGIN_NO_Last(String originDossierNo,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByORIGIN_NO_Last(originDossierNo, orderByComparator);
	}

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where originDossierNo = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param originDossierNo the origin dossier no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public static Dossier[] findByORIGIN_NO_PrevAndNext(long dossierId,
		String originDossierNo, OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByORIGIN_NO_PrevAndNext(dossierId, originDossierNo,
			orderByComparator);
	}

	/**
	* Removes all the dossiers where originDossierNo = &#63; from the database.
	*
	* @param originDossierNo the origin dossier no
	*/
	public static void removeByORIGIN_NO(String originDossierNo) {
		getPersistence().removeByORIGIN_NO(originDossierNo);
	}

	/**
	* Returns the number of dossiers where originDossierNo = &#63;.
	*
	* @param originDossierNo the origin dossier no
	* @return the number of matching dossiers
	*/
	public static int countByORIGIN_NO(String originDossierNo) {
		return getPersistence().countByORIGIN_NO(originDossierNo);
	}

	/**
	* Returns all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param serviceCode the service code
	* @return the matching dossiers
	*/
	public static List<Dossier> findByG_NOTS_O_SC(long groupId,
		String dossierStatus, int originality, String serviceCode) {
		return getPersistence()
				   .findByG_NOTS_O_SC(groupId, dossierStatus, originality,
			serviceCode);
	}

	/**
	* Returns a range of all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and serviceCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param serviceCode the service code
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public static List<Dossier> findByG_NOTS_O_SC(long groupId,
		String dossierStatus, int originality, String serviceCode, int start,
		int end) {
		return getPersistence()
				   .findByG_NOTS_O_SC(groupId, dossierStatus, originality,
			serviceCode, start, end);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and serviceCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param serviceCode the service code
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByG_NOTS_O_SC(long groupId,
		String dossierStatus, int originality, String serviceCode, int start,
		int end, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .findByG_NOTS_O_SC(groupId, dossierStatus, originality,
			serviceCode, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and serviceCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param serviceCode the service code
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByG_NOTS_O_SC(long groupId,
		String dossierStatus, int originality, String serviceCode, int start,
		int end, OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_NOTS_O_SC(groupId, dossierStatus, originality,
			serviceCode, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByG_NOTS_O_SC_First(long groupId,
		String dossierStatus, int originality, String serviceCode,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_NOTS_O_SC_First(groupId, dossierStatus,
			originality, serviceCode, orderByComparator);
	}

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByG_NOTS_O_SC_First(long groupId,
		String dossierStatus, int originality, String serviceCode,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByG_NOTS_O_SC_First(groupId, dossierStatus,
			originality, serviceCode, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByG_NOTS_O_SC_Last(long groupId,
		String dossierStatus, int originality, String serviceCode,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_NOTS_O_SC_Last(groupId, dossierStatus, originality,
			serviceCode, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByG_NOTS_O_SC_Last(long groupId,
		String dossierStatus, int originality, String serviceCode,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByG_NOTS_O_SC_Last(groupId, dossierStatus,
			originality, serviceCode, orderByComparator);
	}

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and serviceCode = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param serviceCode the service code
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public static Dossier[] findByG_NOTS_O_SC_PrevAndNext(long dossierId,
		long groupId, String dossierStatus, int originality,
		String serviceCode, OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_NOTS_O_SC_PrevAndNext(dossierId, groupId,
			dossierStatus, originality, serviceCode, orderByComparator);
	}

	/**
	* Returns all the dossiers where groupId = &#63; and dossierStatus &ne; all &#63; and originality &ge; &#63; and serviceCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatuses the dossier statuses
	* @param originality the originality
	* @param serviceCode the service code
	* @return the matching dossiers
	*/
	public static List<Dossier> findByG_NOTS_O_SC(long groupId,
		String[] dossierStatuses, int originality, String serviceCode) {
		return getPersistence()
				   .findByG_NOTS_O_SC(groupId, dossierStatuses, originality,
			serviceCode);
	}

	/**
	* Returns a range of all the dossiers where groupId = &#63; and dossierStatus &ne; all &#63; and originality &ge; &#63; and serviceCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatuses the dossier statuses
	* @param originality the originality
	* @param serviceCode the service code
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public static List<Dossier> findByG_NOTS_O_SC(long groupId,
		String[] dossierStatuses, int originality, String serviceCode,
		int start, int end) {
		return getPersistence()
				   .findByG_NOTS_O_SC(groupId, dossierStatuses, originality,
			serviceCode, start, end);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and dossierStatus &ne; all &#63; and originality &ge; &#63; and serviceCode = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatuses the dossier statuses
	* @param originality the originality
	* @param serviceCode the service code
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByG_NOTS_O_SC(long groupId,
		String[] dossierStatuses, int originality, String serviceCode,
		int start, int end, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .findByG_NOTS_O_SC(groupId, dossierStatuses, originality,
			serviceCode, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and serviceCode = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param serviceCode the service code
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByG_NOTS_O_SC(long groupId,
		String[] dossierStatuses, int originality, String serviceCode,
		int start, int end, OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_NOTS_O_SC(groupId, dossierStatuses, originality,
			serviceCode, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and serviceCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param serviceCode the service code
	*/
	public static void removeByG_NOTS_O_SC(long groupId, String dossierStatus,
		int originality, String serviceCode) {
		getPersistence()
			.removeByG_NOTS_O_SC(groupId, dossierStatus, originality,
			serviceCode);
	}

	/**
	* Returns the number of dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param serviceCode the service code
	* @return the number of matching dossiers
	*/
	public static int countByG_NOTS_O_SC(long groupId, String dossierStatus,
		int originality, String serviceCode) {
		return getPersistence()
				   .countByG_NOTS_O_SC(groupId, dossierStatus, originality,
			serviceCode);
	}

	/**
	* Returns the number of dossiers where groupId = &#63; and dossierStatus &ne; all &#63; and originality &ge; &#63; and serviceCode = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatuses the dossier statuses
	* @param originality the originality
	* @param serviceCode the service code
	* @return the number of matching dossiers
	*/
	public static int countByG_NOTS_O_SC(long groupId,
		String[] dossierStatuses, int originality, String serviceCode) {
		return getPersistence()
				   .countByG_NOTS_O_SC(groupId, dossierStatuses, originality,
			serviceCode);
	}

	/**
	* Returns all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param dossierTemplateNo the dossier template no
	* @return the matching dossiers
	*/
	public static List<Dossier> findByG_NOTS_O_DTN(long groupId,
		String dossierStatus, int originality, String dossierTemplateNo) {
		return getPersistence()
				   .findByG_NOTS_O_DTN(groupId, dossierStatus, originality,
			dossierTemplateNo);
	}

	/**
	* Returns a range of all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param dossierTemplateNo the dossier template no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public static List<Dossier> findByG_NOTS_O_DTN(long groupId,
		String dossierStatus, int originality, String dossierTemplateNo,
		int start, int end) {
		return getPersistence()
				   .findByG_NOTS_O_DTN(groupId, dossierStatus, originality,
			dossierTemplateNo, start, end);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param dossierTemplateNo the dossier template no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByG_NOTS_O_DTN(long groupId,
		String dossierStatus, int originality, String dossierTemplateNo,
		int start, int end, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .findByG_NOTS_O_DTN(groupId, dossierStatus, originality,
			dossierTemplateNo, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param dossierTemplateNo the dossier template no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByG_NOTS_O_DTN(long groupId,
		String dossierStatus, int originality, String dossierTemplateNo,
		int start, int end, OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_NOTS_O_DTN(groupId, dossierStatus, originality,
			dossierTemplateNo, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param dossierTemplateNo the dossier template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByG_NOTS_O_DTN_First(long groupId,
		String dossierStatus, int originality, String dossierTemplateNo,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_NOTS_O_DTN_First(groupId, dossierStatus,
			originality, dossierTemplateNo, orderByComparator);
	}

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param dossierTemplateNo the dossier template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByG_NOTS_O_DTN_First(long groupId,
		String dossierStatus, int originality, String dossierTemplateNo,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByG_NOTS_O_DTN_First(groupId, dossierStatus,
			originality, dossierTemplateNo, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param dossierTemplateNo the dossier template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByG_NOTS_O_DTN_Last(long groupId,
		String dossierStatus, int originality, String dossierTemplateNo,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_NOTS_O_DTN_Last(groupId, dossierStatus,
			originality, dossierTemplateNo, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param dossierTemplateNo the dossier template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByG_NOTS_O_DTN_Last(long groupId,
		String dossierStatus, int originality, String dossierTemplateNo,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByG_NOTS_O_DTN_Last(groupId, dossierStatus,
			originality, dossierTemplateNo, orderByComparator);
	}

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param dossierTemplateNo the dossier template no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public static Dossier[] findByG_NOTS_O_DTN_PrevAndNext(long dossierId,
		long groupId, String dossierStatus, int originality,
		String dossierTemplateNo, OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_NOTS_O_DTN_PrevAndNext(dossierId, groupId,
			dossierStatus, originality, dossierTemplateNo, orderByComparator);
	}

	/**
	* Returns all the dossiers where groupId = &#63; and dossierStatus &ne; all &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatuses the dossier statuses
	* @param originality the originality
	* @param dossierTemplateNo the dossier template no
	* @return the matching dossiers
	*/
	public static List<Dossier> findByG_NOTS_O_DTN(long groupId,
		String[] dossierStatuses, int originality, String dossierTemplateNo) {
		return getPersistence()
				   .findByG_NOTS_O_DTN(groupId, dossierStatuses, originality,
			dossierTemplateNo);
	}

	/**
	* Returns a range of all the dossiers where groupId = &#63; and dossierStatus &ne; all &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatuses the dossier statuses
	* @param originality the originality
	* @param dossierTemplateNo the dossier template no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public static List<Dossier> findByG_NOTS_O_DTN(long groupId,
		String[] dossierStatuses, int originality, String dossierTemplateNo,
		int start, int end) {
		return getPersistence()
				   .findByG_NOTS_O_DTN(groupId, dossierStatuses, originality,
			dossierTemplateNo, start, end);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and dossierStatus &ne; all &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatuses the dossier statuses
	* @param originality the originality
	* @param dossierTemplateNo the dossier template no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByG_NOTS_O_DTN(long groupId,
		String[] dossierStatuses, int originality, String dossierTemplateNo,
		int start, int end, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .findByG_NOTS_O_DTN(groupId, dossierStatuses, originality,
			dossierTemplateNo, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param dossierTemplateNo the dossier template no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByG_NOTS_O_DTN(long groupId,
		String[] dossierStatuses, int originality, String dossierTemplateNo,
		int start, int end, OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_NOTS_O_DTN(groupId, dossierStatuses, originality,
			dossierTemplateNo, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and dossierTemplateNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param dossierTemplateNo the dossier template no
	*/
	public static void removeByG_NOTS_O_DTN(long groupId, String dossierStatus,
		int originality, String dossierTemplateNo) {
		getPersistence()
			.removeByG_NOTS_O_DTN(groupId, dossierStatus, originality,
			dossierTemplateNo);
	}

	/**
	* Returns the number of dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param dossierTemplateNo the dossier template no
	* @return the number of matching dossiers
	*/
	public static int countByG_NOTS_O_DTN(long groupId, String dossierStatus,
		int originality, String dossierTemplateNo) {
		return getPersistence()
				   .countByG_NOTS_O_DTN(groupId, dossierStatus, originality,
			dossierTemplateNo);
	}

	/**
	* Returns the number of dossiers where groupId = &#63; and dossierStatus &ne; all &#63; and originality &ge; &#63; and dossierTemplateNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatuses the dossier statuses
	* @param originality the originality
	* @param dossierTemplateNo the dossier template no
	* @return the number of matching dossiers
	*/
	public static int countByG_NOTS_O_DTN(long groupId,
		String[] dossierStatuses, int originality, String dossierTemplateNo) {
		return getPersistence()
				   .countByG_NOTS_O_DTN(groupId, dossierStatuses, originality,
			dossierTemplateNo);
	}

	/**
	* Returns all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and processNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param processNo the process no
	* @return the matching dossiers
	*/
	public static List<Dossier> findByG_NOTS_O_PN(long groupId,
		String dossierStatus, int originality, String processNo) {
		return getPersistence()
				   .findByG_NOTS_O_PN(groupId, dossierStatus, originality,
			processNo);
	}

	/**
	* Returns a range of all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and processNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param processNo the process no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public static List<Dossier> findByG_NOTS_O_PN(long groupId,
		String dossierStatus, int originality, String processNo, int start,
		int end) {
		return getPersistence()
				   .findByG_NOTS_O_PN(groupId, dossierStatus, originality,
			processNo, start, end);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and processNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param processNo the process no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByG_NOTS_O_PN(long groupId,
		String dossierStatus, int originality, String processNo, int start,
		int end, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .findByG_NOTS_O_PN(groupId, dossierStatus, originality,
			processNo, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and processNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param processNo the process no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByG_NOTS_O_PN(long groupId,
		String dossierStatus, int originality, String processNo, int start,
		int end, OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_NOTS_O_PN(groupId, dossierStatus, originality,
			processNo, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and processNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param processNo the process no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByG_NOTS_O_PN_First(long groupId,
		String dossierStatus, int originality, String processNo,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_NOTS_O_PN_First(groupId, dossierStatus,
			originality, processNo, orderByComparator);
	}

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and processNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param processNo the process no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByG_NOTS_O_PN_First(long groupId,
		String dossierStatus, int originality, String processNo,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByG_NOTS_O_PN_First(groupId, dossierStatus,
			originality, processNo, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and processNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param processNo the process no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByG_NOTS_O_PN_Last(long groupId,
		String dossierStatus, int originality, String processNo,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_NOTS_O_PN_Last(groupId, dossierStatus, originality,
			processNo, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and processNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param processNo the process no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByG_NOTS_O_PN_Last(long groupId,
		String dossierStatus, int originality, String processNo,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByG_NOTS_O_PN_Last(groupId, dossierStatus,
			originality, processNo, orderByComparator);
	}

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and processNo = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param processNo the process no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public static Dossier[] findByG_NOTS_O_PN_PrevAndNext(long dossierId,
		long groupId, String dossierStatus, int originality, String processNo,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_NOTS_O_PN_PrevAndNext(dossierId, groupId,
			dossierStatus, originality, processNo, orderByComparator);
	}

	/**
	* Returns all the dossiers where groupId = &#63; and dossierStatus &ne; all &#63; and originality &ge; &#63; and processNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatuses the dossier statuses
	* @param originality the originality
	* @param processNo the process no
	* @return the matching dossiers
	*/
	public static List<Dossier> findByG_NOTS_O_PN(long groupId,
		String[] dossierStatuses, int originality, String processNo) {
		return getPersistence()
				   .findByG_NOTS_O_PN(groupId, dossierStatuses, originality,
			processNo);
	}

	/**
	* Returns a range of all the dossiers where groupId = &#63; and dossierStatus &ne; all &#63; and originality &ge; &#63; and processNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatuses the dossier statuses
	* @param originality the originality
	* @param processNo the process no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public static List<Dossier> findByG_NOTS_O_PN(long groupId,
		String[] dossierStatuses, int originality, String processNo, int start,
		int end) {
		return getPersistence()
				   .findByG_NOTS_O_PN(groupId, dossierStatuses, originality,
			processNo, start, end);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and dossierStatus &ne; all &#63; and originality &ge; &#63; and processNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatuses the dossier statuses
	* @param originality the originality
	* @param processNo the process no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByG_NOTS_O_PN(long groupId,
		String[] dossierStatuses, int originality, String processNo, int start,
		int end, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .findByG_NOTS_O_PN(groupId, dossierStatuses, originality,
			processNo, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and processNo = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param processNo the process no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByG_NOTS_O_PN(long groupId,
		String[] dossierStatuses, int originality, String processNo, int start,
		int end, OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_NOTS_O_PN(groupId, dossierStatuses, originality,
			processNo, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and processNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param processNo the process no
	*/
	public static void removeByG_NOTS_O_PN(long groupId, String dossierStatus,
		int originality, String processNo) {
		getPersistence()
			.removeByG_NOTS_O_PN(groupId, dossierStatus, originality, processNo);
	}

	/**
	* Returns the number of dossiers where groupId = &#63; and dossierStatus &ne; &#63; and originality &ge; &#63; and processNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param processNo the process no
	* @return the number of matching dossiers
	*/
	public static int countByG_NOTS_O_PN(long groupId, String dossierStatus,
		int originality, String processNo) {
		return getPersistence()
				   .countByG_NOTS_O_PN(groupId, dossierStatus, originality,
			processNo);
	}

	/**
	* Returns the number of dossiers where groupId = &#63; and dossierStatus &ne; all &#63; and originality &ge; &#63; and processNo = &#63;.
	*
	* @param groupId the group ID
	* @param dossierStatuses the dossier statuses
	* @param originality the originality
	* @param processNo the process no
	* @return the number of matching dossiers
	*/
	public static int countByG_NOTS_O_PN(long groupId,
		String[] dossierStatuses, int originality, String processNo) {
		return getPersistence()
				   .countByG_NOTS_O_PN(groupId, dossierStatuses, originality,
			processNo);
	}

	/**
	* Returns all the dossiers where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and originality = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @return the matching dossiers
	*/
	public static List<Dossier> findByU_G_GAC_SC_DTNO_DS_O(long userId,
		long groupId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus, int originality) {
		return getPersistence()
				   .findByU_G_GAC_SC_DTNO_DS_O(userId, groupId, govAgencyCode,
			serviceCode, dossierTemplateNo, dossierStatus, originality);
	}

	/**
	* Returns a range of all the dossiers where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and originality = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public static List<Dossier> findByU_G_GAC_SC_DTNO_DS_O(long userId,
		long groupId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus, int originality,
		int start, int end) {
		return getPersistence()
				   .findByU_G_GAC_SC_DTNO_DS_O(userId, groupId, govAgencyCode,
			serviceCode, dossierTemplateNo, dossierStatus, originality, start,
			end);
	}

	/**
	* Returns an ordered range of all the dossiers where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and originality = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByU_G_GAC_SC_DTNO_DS_O(long userId,
		long groupId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus, int originality,
		int start, int end, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .findByU_G_GAC_SC_DTNO_DS_O(userId, groupId, govAgencyCode,
			serviceCode, dossierTemplateNo, dossierStatus, originality, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossiers where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and originality = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByU_G_GAC_SC_DTNO_DS_O(long userId,
		long groupId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus, int originality,
		int start, int end, OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByU_G_GAC_SC_DTNO_DS_O(userId, groupId, govAgencyCode,
			serviceCode, dossierTemplateNo, dossierStatus, originality, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier in the ordered set where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and originality = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByU_G_GAC_SC_DTNO_DS_O_First(long userId,
		long groupId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus, int originality,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByU_G_GAC_SC_DTNO_DS_O_First(userId, groupId,
			govAgencyCode, serviceCode, dossierTemplateNo, dossierStatus,
			originality, orderByComparator);
	}

	/**
	* Returns the first dossier in the ordered set where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and originality = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByU_G_GAC_SC_DTNO_DS_O_First(long userId,
		long groupId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus, int originality,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByU_G_GAC_SC_DTNO_DS_O_First(userId, groupId,
			govAgencyCode, serviceCode, dossierTemplateNo, dossierStatus,
			originality, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and originality = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByU_G_GAC_SC_DTNO_DS_O_Last(long userId,
		long groupId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus, int originality,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByU_G_GAC_SC_DTNO_DS_O_Last(userId, groupId,
			govAgencyCode, serviceCode, dossierTemplateNo, dossierStatus,
			originality, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and originality = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByU_G_GAC_SC_DTNO_DS_O_Last(long userId,
		long groupId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus, int originality,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByU_G_GAC_SC_DTNO_DS_O_Last(userId, groupId,
			govAgencyCode, serviceCode, dossierTemplateNo, dossierStatus,
			originality, orderByComparator);
	}

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and originality = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public static Dossier[] findByU_G_GAC_SC_DTNO_DS_O_PrevAndNext(
		long dossierId, long userId, long groupId, String govAgencyCode,
		String serviceCode, String dossierTemplateNo, String dossierStatus,
		int originality, OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByU_G_GAC_SC_DTNO_DS_O_PrevAndNext(dossierId, userId,
			groupId, govAgencyCode, serviceCode, dossierTemplateNo,
			dossierStatus, originality, orderByComparator);
	}

	/**
	* Removes all the dossiers where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and originality = &#63; from the database.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param originality the originality
	*/
	public static void removeByU_G_GAC_SC_DTNO_DS_O(long userId, long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, int originality) {
		getPersistence()
			.removeByU_G_GAC_SC_DTNO_DS_O(userId, groupId, govAgencyCode,
			serviceCode, dossierTemplateNo, dossierStatus, originality);
	}

	/**
	* Returns the number of dossiers where userId = &#63; and groupId = &#63; and govAgencyCode = &#63; and serviceCode = &#63; and dossierTemplateNo = &#63; and dossierStatus = &#63; and originality = &#63;.
	*
	* @param userId the user ID
	* @param groupId the group ID
	* @param govAgencyCode the gov agency code
	* @param serviceCode the service code
	* @param dossierTemplateNo the dossier template no
	* @param dossierStatus the dossier status
	* @param originality the originality
	* @return the number of matching dossiers
	*/
	public static int countByU_G_GAC_SC_DTNO_DS_O(long userId, long groupId,
		String govAgencyCode, String serviceCode, String dossierTemplateNo,
		String dossierStatus, int originality) {
		return getPersistence()
				   .countByU_G_GAC_SC_DTNO_DS_O(userId, groupId, govAgencyCode,
			serviceCode, dossierTemplateNo, dossierStatus, originality);
	}

	/**
	* Returns all the dossiers where groupId = &#63; and groupDossierId LIKE &#63;.
	*
	* @param groupId the group ID
	* @param groupDossierId the group dossier ID
	* @return the matching dossiers
	*/
	public static List<Dossier> findByG_GDID(long groupId, String groupDossierId) {
		return getPersistence().findByG_GDID(groupId, groupDossierId);
	}

	/**
	* Returns a range of all the dossiers where groupId = &#63; and groupDossierId LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param groupDossierId the group dossier ID
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public static List<Dossier> findByG_GDID(long groupId,
		String groupDossierId, int start, int end) {
		return getPersistence().findByG_GDID(groupId, groupDossierId, start, end);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and groupDossierId LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param groupDossierId the group dossier ID
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByG_GDID(long groupId,
		String groupDossierId, int start, int end,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .findByG_GDID(groupId, groupDossierId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and groupDossierId LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param groupDossierId the group dossier ID
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByG_GDID(long groupId,
		String groupDossierId, int start, int end,
		OrderByComparator<Dossier> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_GDID(groupId, groupDossierId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and groupDossierId LIKE &#63;.
	*
	* @param groupId the group ID
	* @param groupDossierId the group dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByG_GDID_First(long groupId,
		String groupDossierId, OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_GDID_First(groupId, groupDossierId,
			orderByComparator);
	}

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and groupDossierId LIKE &#63;.
	*
	* @param groupId the group ID
	* @param groupDossierId the group dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByG_GDID_First(long groupId,
		String groupDossierId, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByG_GDID_First(groupId, groupDossierId,
			orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and groupDossierId LIKE &#63;.
	*
	* @param groupId the group ID
	* @param groupDossierId the group dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByG_GDID_Last(long groupId,
		String groupDossierId, OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_GDID_Last(groupId, groupDossierId, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and groupDossierId LIKE &#63;.
	*
	* @param groupId the group ID
	* @param groupDossierId the group dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByG_GDID_Last(long groupId,
		String groupDossierId, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByG_GDID_Last(groupId, groupDossierId,
			orderByComparator);
	}

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and groupDossierId LIKE &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param groupId the group ID
	* @param groupDossierId the group dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public static Dossier[] findByG_GDID_PrevAndNext(long dossierId,
		long groupId, String groupDossierId,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_GDID_PrevAndNext(dossierId, groupId,
			groupDossierId, orderByComparator);
	}

	/**
	* Removes all the dossiers where groupId = &#63; and groupDossierId LIKE &#63; from the database.
	*
	* @param groupId the group ID
	* @param groupDossierId the group dossier ID
	*/
	public static void removeByG_GDID(long groupId, String groupDossierId) {
		getPersistence().removeByG_GDID(groupId, groupDossierId);
	}

	/**
	* Returns the number of dossiers where groupId = &#63; and groupDossierId LIKE &#63;.
	*
	* @param groupId the group ID
	* @param groupDossierId the group dossier ID
	* @return the number of matching dossiers
	*/
	public static int countByG_GDID(long groupId, String groupDossierId) {
		return getPersistence().countByG_GDID(groupId, groupDossierId);
	}

	/**
	* Returns all the dossiers where groupId = &#63; and userId = &#63; and dossierStatus = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param dossierStatus the dossier status
	* @return the matching dossiers
	*/
	public static List<Dossier> findByG_UID_DS(long groupId, long userId,
		String dossierStatus) {
		return getPersistence().findByG_UID_DS(groupId, userId, dossierStatus);
	}

	/**
	* Returns a range of all the dossiers where groupId = &#63; and userId = &#63; and dossierStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param dossierStatus the dossier status
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public static List<Dossier> findByG_UID_DS(long groupId, long userId,
		String dossierStatus, int start, int end) {
		return getPersistence()
				   .findByG_UID_DS(groupId, userId, dossierStatus, start, end);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and userId = &#63; and dossierStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param dossierStatus the dossier status
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByG_UID_DS(long groupId, long userId,
		String dossierStatus, int start, int end,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .findByG_UID_DS(groupId, userId, dossierStatus, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and userId = &#63; and dossierStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param dossierStatus the dossier status
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByG_UID_DS(long groupId, long userId,
		String dossierStatus, int start, int end,
		OrderByComparator<Dossier> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_UID_DS(groupId, userId, dossierStatus, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and userId = &#63; and dossierStatus = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByG_UID_DS_First(long groupId, long userId,
		String dossierStatus, OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_UID_DS_First(groupId, userId, dossierStatus,
			orderByComparator);
	}

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and userId = &#63; and dossierStatus = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByG_UID_DS_First(long groupId, long userId,
		String dossierStatus, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByG_UID_DS_First(groupId, userId, dossierStatus,
			orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and userId = &#63; and dossierStatus = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByG_UID_DS_Last(long groupId, long userId,
		String dossierStatus, OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_UID_DS_Last(groupId, userId, dossierStatus,
			orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and userId = &#63; and dossierStatus = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByG_UID_DS_Last(long groupId, long userId,
		String dossierStatus, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByG_UID_DS_Last(groupId, userId, dossierStatus,
			orderByComparator);
	}

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and userId = &#63; and dossierStatus = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param groupId the group ID
	* @param userId the user ID
	* @param dossierStatus the dossier status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public static Dossier[] findByG_UID_DS_PrevAndNext(long dossierId,
		long groupId, long userId, String dossierStatus,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_UID_DS_PrevAndNext(dossierId, groupId, userId,
			dossierStatus, orderByComparator);
	}

	/**
	* Removes all the dossiers where groupId = &#63; and userId = &#63; and dossierStatus = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param dossierStatus the dossier status
	*/
	public static void removeByG_UID_DS(long groupId, long userId,
		String dossierStatus) {
		getPersistence().removeByG_UID_DS(groupId, userId, dossierStatus);
	}

	/**
	* Returns the number of dossiers where groupId = &#63; and userId = &#63; and dossierStatus = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param dossierStatus the dossier status
	* @return the number of matching dossiers
	*/
	public static int countByG_UID_DS(long groupId, long userId,
		String dossierStatus) {
		return getPersistence().countByG_UID_DS(groupId, userId, dossierStatus);
	}

	/**
	* Returns all the dossiers where groupId = &#63; and vnpostalStatus = &#63;.
	*
	* @param groupId the group ID
	* @param vnpostalStatus the vnpostal status
	* @return the matching dossiers
	*/
	public static List<Dossier> findByG_VNP_STT(long groupId, int vnpostalStatus) {
		return getPersistence().findByG_VNP_STT(groupId, vnpostalStatus);
	}

	/**
	* Returns a range of all the dossiers where groupId = &#63; and vnpostalStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param vnpostalStatus the vnpostal status
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public static List<Dossier> findByG_VNP_STT(long groupId,
		int vnpostalStatus, int start, int end) {
		return getPersistence()
				   .findByG_VNP_STT(groupId, vnpostalStatus, start, end);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and vnpostalStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param vnpostalStatus the vnpostal status
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByG_VNP_STT(long groupId,
		int vnpostalStatus, int start, int end,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .findByG_VNP_STT(groupId, vnpostalStatus, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossiers where groupId = &#63; and vnpostalStatus = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param vnpostalStatus the vnpostal status
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByG_VNP_STT(long groupId,
		int vnpostalStatus, int start, int end,
		OrderByComparator<Dossier> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_VNP_STT(groupId, vnpostalStatus, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and vnpostalStatus = &#63;.
	*
	* @param groupId the group ID
	* @param vnpostalStatus the vnpostal status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByG_VNP_STT_First(long groupId,
		int vnpostalStatus, OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_VNP_STT_First(groupId, vnpostalStatus,
			orderByComparator);
	}

	/**
	* Returns the first dossier in the ordered set where groupId = &#63; and vnpostalStatus = &#63;.
	*
	* @param groupId the group ID
	* @param vnpostalStatus the vnpostal status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByG_VNP_STT_First(long groupId,
		int vnpostalStatus, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByG_VNP_STT_First(groupId, vnpostalStatus,
			orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and vnpostalStatus = &#63;.
	*
	* @param groupId the group ID
	* @param vnpostalStatus the vnpostal status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByG_VNP_STT_Last(long groupId,
		int vnpostalStatus, OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_VNP_STT_Last(groupId, vnpostalStatus,
			orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where groupId = &#63; and vnpostalStatus = &#63;.
	*
	* @param groupId the group ID
	* @param vnpostalStatus the vnpostal status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByG_VNP_STT_Last(long groupId,
		int vnpostalStatus, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByG_VNP_STT_Last(groupId, vnpostalStatus,
			orderByComparator);
	}

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where groupId = &#63; and vnpostalStatus = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param groupId the group ID
	* @param vnpostalStatus the vnpostal status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public static Dossier[] findByG_VNP_STT_PrevAndNext(long dossierId,
		long groupId, int vnpostalStatus,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByG_VNP_STT_PrevAndNext(dossierId, groupId,
			vnpostalStatus, orderByComparator);
	}

	/**
	* Removes all the dossiers where groupId = &#63; and vnpostalStatus = &#63; from the database.
	*
	* @param groupId the group ID
	* @param vnpostalStatus the vnpostal status
	*/
	public static void removeByG_VNP_STT(long groupId, int vnpostalStatus) {
		getPersistence().removeByG_VNP_STT(groupId, vnpostalStatus);
	}

	/**
	* Returns the number of dossiers where groupId = &#63; and vnpostalStatus = &#63;.
	*
	* @param groupId the group ID
	* @param vnpostalStatus the vnpostal status
	* @return the number of matching dossiers
	*/
	public static int countByG_VNP_STT(long groupId, int vnpostalStatus) {
		return getPersistence().countByG_VNP_STT(groupId, vnpostalStatus);
	}

	/**
	* Returns the dossier where dossierCounter = &#63; or throws a {@link NoSuchDossierException} if it could not be found.
	*
	* @param dossierCounter the dossier counter
	* @return the matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByDC(String dossierCounter)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence().findByDC(dossierCounter);
	}

	/**
	* Returns the dossier where dossierCounter = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param dossierCounter the dossier counter
	* @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByDC(String dossierCounter) {
		return getPersistence().fetchByDC(dossierCounter);
	}

	/**
	* Returns the dossier where dossierCounter = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param dossierCounter the dossier counter
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByDC(String dossierCounter,
		boolean retrieveFromCache) {
		return getPersistence().fetchByDC(dossierCounter, retrieveFromCache);
	}

	/**
	* Removes the dossier where dossierCounter = &#63; from the database.
	*
	* @param dossierCounter the dossier counter
	* @return the dossier that was removed
	*/
	public static Dossier removeByDC(String dossierCounter)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence().removeByDC(dossierCounter);
	}

	/**
	* Returns the number of dossiers where dossierCounter = &#63;.
	*
	* @param dossierCounter the dossier counter
	* @return the number of matching dossiers
	*/
	public static int countByDC(String dossierCounter) {
		return getPersistence().countByDC(dossierCounter);
	}

	/**
	* Returns all the dossiers where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @return the matching dossiers
	*/
	public static List<Dossier> findByD_OR_D(long dossierId) {
		return getPersistence().findByD_OR_D(dossierId);
	}

	/**
	* Returns a range of all the dossiers where dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public static List<Dossier> findByD_OR_D(long dossierId, int start, int end) {
		return getPersistence().findByD_OR_D(dossierId, start, end);
	}

	/**
	* Returns an ordered range of all the dossiers where dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByD_OR_D(long dossierId, int start,
		int end, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .findByD_OR_D(dossierId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossiers where dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByD_OR_D(long dossierId, int start,
		int end, OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByD_OR_D(dossierId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first dossier in the ordered set where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByD_OR_D_First(long dossierId,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence().findByD_OR_D_First(dossierId, orderByComparator);
	}

	/**
	* Returns the first dossier in the ordered set where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByD_OR_D_First(long dossierId,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence().fetchByD_OR_D_First(dossierId, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByD_OR_D_Last(long dossierId,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence().findByD_OR_D_Last(dossierId, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByD_OR_D_Last(long dossierId,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence().fetchByD_OR_D_Last(dossierId, orderByComparator);
	}

	/**
	* Returns all the dossiers where dossierId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierIds the dossier IDs
	* @return the matching dossiers
	*/
	public static List<Dossier> findByD_OR_D(long[] dossierIds) {
		return getPersistence().findByD_OR_D(dossierIds);
	}

	/**
	* Returns a range of all the dossiers where dossierId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierIds the dossier IDs
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public static List<Dossier> findByD_OR_D(long[] dossierIds, int start,
		int end) {
		return getPersistence().findByD_OR_D(dossierIds, start, end);
	}

	/**
	* Returns an ordered range of all the dossiers where dossierId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierIds the dossier IDs
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByD_OR_D(long[] dossierIds, int start,
		int end, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .findByD_OR_D(dossierIds, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossiers where dossierId = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByD_OR_D(long[] dossierIds, int start,
		int end, OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByD_OR_D(dossierIds, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Removes all the dossiers where dossierId = &#63; from the database.
	*
	* @param dossierId the dossier ID
	*/
	public static void removeByD_OR_D(long dossierId) {
		getPersistence().removeByD_OR_D(dossierId);
	}

	/**
	* Returns the number of dossiers where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @return the number of matching dossiers
	*/
	public static int countByD_OR_D(long dossierId) {
		return getPersistence().countByD_OR_D(dossierId);
	}

	/**
	* Returns the number of dossiers where dossierId = any &#63;.
	*
	* @param dossierIds the dossier IDs
	* @return the number of matching dossiers
	*/
	public static int countByD_OR_D(long[] dossierIds) {
		return getPersistence().countByD_OR_D(dossierIds);
	}

	/**
	* Returns all the dossiers where dossierNo = &#63;.
	*
	* @param dossierNo the dossier no
	* @return the matching dossiers
	*/
	public static List<Dossier> findByNEW_DO_NO(String dossierNo) {
		return getPersistence().findByNEW_DO_NO(dossierNo);
	}

	/**
	* Returns a range of all the dossiers where dossierNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierNo the dossier no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of matching dossiers
	*/
	public static List<Dossier> findByNEW_DO_NO(String dossierNo, int start,
		int end) {
		return getPersistence().findByNEW_DO_NO(dossierNo, start, end);
	}

	/**
	* Returns an ordered range of all the dossiers where dossierNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierNo the dossier no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByNEW_DO_NO(String dossierNo, int start,
		int end, OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .findByNEW_DO_NO(dossierNo, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossiers where dossierNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierNo the dossier no
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossiers
	*/
	public static List<Dossier> findByNEW_DO_NO(String dossierNo, int start,
		int end, OrderByComparator<Dossier> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByNEW_DO_NO(dossierNo, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first dossier in the ordered set where dossierNo = &#63;.
	*
	* @param dossierNo the dossier no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByNEW_DO_NO_First(String dossierNo,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByNEW_DO_NO_First(dossierNo, orderByComparator);
	}

	/**
	* Returns the first dossier in the ordered set where dossierNo = &#63;.
	*
	* @param dossierNo the dossier no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByNEW_DO_NO_First(String dossierNo,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByNEW_DO_NO_First(dossierNo, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where dossierNo = &#63;.
	*
	* @param dossierNo the dossier no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier
	* @throws NoSuchDossierException if a matching dossier could not be found
	*/
	public static Dossier findByNEW_DO_NO_Last(String dossierNo,
		OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByNEW_DO_NO_Last(dossierNo, orderByComparator);
	}

	/**
	* Returns the last dossier in the ordered set where dossierNo = &#63;.
	*
	* @param dossierNo the dossier no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static Dossier fetchByNEW_DO_NO_Last(String dossierNo,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence()
				   .fetchByNEW_DO_NO_Last(dossierNo, orderByComparator);
	}

	/**
	* Returns the dossiers before and after the current dossier in the ordered set where dossierNo = &#63;.
	*
	* @param dossierId the primary key of the current dossier
	* @param dossierNo the dossier no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public static Dossier[] findByNEW_DO_NO_PrevAndNext(long dossierId,
		String dossierNo, OrderByComparator<Dossier> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence()
				   .findByNEW_DO_NO_PrevAndNext(dossierId, dossierNo,
			orderByComparator);
	}

	/**
	* Removes all the dossiers where dossierNo = &#63; from the database.
	*
	* @param dossierNo the dossier no
	*/
	public static void removeByNEW_DO_NO(String dossierNo) {
		getPersistence().removeByNEW_DO_NO(dossierNo);
	}

	/**
	* Returns the number of dossiers where dossierNo = &#63;.
	*
	* @param dossierNo the dossier no
	* @return the number of matching dossiers
	*/
	public static int countByNEW_DO_NO(String dossierNo) {
		return getPersistence().countByNEW_DO_NO(dossierNo);
	}

	/**
	* Caches the dossier in the entity cache if it is enabled.
	*
	* @param dossier the dossier
	*/
	public static void cacheResult(Dossier dossier) {
		getPersistence().cacheResult(dossier);
	}

	/**
	* Caches the dossiers in the entity cache if it is enabled.
	*
	* @param dossiers the dossiers
	*/
	public static void cacheResult(List<Dossier> dossiers) {
		getPersistence().cacheResult(dossiers);
	}

	/**
	* Creates a new dossier with the primary key. Does not add the dossier to the database.
	*
	* @param dossierId the primary key for the new dossier
	* @return the new dossier
	*/
	public static Dossier create(long dossierId) {
		return getPersistence().create(dossierId);
	}

	/**
	* Removes the dossier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierId the primary key of the dossier
	* @return the dossier that was removed
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public static Dossier remove(long dossierId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence().remove(dossierId);
	}

	public static Dossier updateImpl(Dossier dossier) {
		return getPersistence().updateImpl(dossier);
	}

	/**
	* Returns the dossier with the primary key or throws a {@link NoSuchDossierException} if it could not be found.
	*
	* @param dossierId the primary key of the dossier
	* @return the dossier
	* @throws NoSuchDossierException if a dossier with the primary key could not be found
	*/
	public static Dossier findByPrimaryKey(long dossierId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getPersistence().findByPrimaryKey(dossierId);
	}

	/**
	* Returns the dossier with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dossierId the primary key of the dossier
	* @return the dossier, or <code>null</code> if a dossier with the primary key could not be found
	*/
	public static Dossier fetchByPrimaryKey(long dossierId) {
		return getPersistence().fetchByPrimaryKey(dossierId);
	}

	public static java.util.Map<java.io.Serializable, Dossier> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the dossiers.
	*
	* @return the dossiers
	*/
	public static List<Dossier> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the dossiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of dossiers
	*/
	public static List<Dossier> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the dossiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dossiers
	*/
	public static List<Dossier> findAll(int start, int end,
		OrderByComparator<Dossier> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dossiers
	*/
	public static List<Dossier> findAll(int start, int end,
		OrderByComparator<Dossier> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the dossiers from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of dossiers.
	*
	* @return the number of dossiers
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static DossierPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DossierPersistence, DossierPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DossierPersistence.class);

		ServiceTracker<DossierPersistence, DossierPersistence> serviceTracker = new ServiceTracker<DossierPersistence, DossierPersistence>(bundle.getBundleContext(),
				DossierPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}