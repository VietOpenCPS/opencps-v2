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

import org.opencps.dossiermgt.model.DossierUser;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the dossier user service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.DossierUserPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see DossierUserPersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.DossierUserPersistenceImpl
 * @generated
 */
@ProviderType
public class DossierUserUtil {
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
	public static void clearCache(DossierUser dossierUser) {
		getPersistence().clearCache(dossierUser);
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
	public static List<DossierUser> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DossierUser> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DossierUser> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<DossierUser> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static DossierUser update(DossierUser dossierUser) {
		return getPersistence().update(dossierUser);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static DossierUser update(DossierUser dossierUser,
		ServiceContext serviceContext) {
		return getPersistence().update(dossierUser, serviceContext);
	}

	/**
	* Returns all the dossier users where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching dossier users
	*/
	public static List<DossierUser> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the dossier users where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier users
	* @param end the upper bound of the range of dossier users (not inclusive)
	* @return the range of matching dossier users
	*/
	public static List<DossierUser> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the dossier users where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier users
	* @param end the upper bound of the range of dossier users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier users
	*/
	public static List<DossierUser> findByUuid(String uuid, int start, int end,
		OrderByComparator<DossierUser> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier users where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier users
	* @param end the upper bound of the range of dossier users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier users
	*/
	public static List<DossierUser> findByUuid(String uuid, int start, int end,
		OrderByComparator<DossierUser> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first dossier user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier user
	* @throws NoSuchDossierUserException if a matching dossier user could not be found
	*/
	public static DossierUser findByUuid_First(String uuid,
		OrderByComparator<DossierUser> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierUserException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first dossier user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier user, or <code>null</code> if a matching dossier user could not be found
	*/
	public static DossierUser fetchByUuid_First(String uuid,
		OrderByComparator<DossierUser> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last dossier user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier user
	* @throws NoSuchDossierUserException if a matching dossier user could not be found
	*/
	public static DossierUser findByUuid_Last(String uuid,
		OrderByComparator<DossierUser> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierUserException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last dossier user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier user, or <code>null</code> if a matching dossier user could not be found
	*/
	public static DossierUser fetchByUuid_Last(String uuid,
		OrderByComparator<DossierUser> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the dossier users before and after the current dossier user in the ordered set where uuid = &#63;.
	*
	* @param dossierUserPK the primary key of the current dossier user
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier user
	* @throws NoSuchDossierUserException if a dossier user with the primary key could not be found
	*/
	public static DossierUser[] findByUuid_PrevAndNext(
		DossierUserPK dossierUserPK, String uuid,
		OrderByComparator<DossierUser> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierUserException {
		return getPersistence()
				   .findByUuid_PrevAndNext(dossierUserPK, uuid,
			orderByComparator);
	}

	/**
	* Removes all the dossier users where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of dossier users where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching dossier users
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns all the dossier users where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @return the matching dossier users
	*/
	public static List<DossierUser> findByDID(long dossierId) {
		return getPersistence().findByDID(dossierId);
	}

	/**
	* Returns a range of all the dossier users where dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of dossier users
	* @param end the upper bound of the range of dossier users (not inclusive)
	* @return the range of matching dossier users
	*/
	public static List<DossierUser> findByDID(long dossierId, int start, int end) {
		return getPersistence().findByDID(dossierId, start, end);
	}

	/**
	* Returns an ordered range of all the dossier users where dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of dossier users
	* @param end the upper bound of the range of dossier users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier users
	*/
	public static List<DossierUser> findByDID(long dossierId, int start,
		int end, OrderByComparator<DossierUser> orderByComparator) {
		return getPersistence()
				   .findByDID(dossierId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier users where dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of dossier users
	* @param end the upper bound of the range of dossier users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier users
	*/
	public static List<DossierUser> findByDID(long dossierId, int start,
		int end, OrderByComparator<DossierUser> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByDID(dossierId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first dossier user in the ordered set where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier user
	* @throws NoSuchDossierUserException if a matching dossier user could not be found
	*/
	public static DossierUser findByDID_First(long dossierId,
		OrderByComparator<DossierUser> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierUserException {
		return getPersistence().findByDID_First(dossierId, orderByComparator);
	}

	/**
	* Returns the first dossier user in the ordered set where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier user, or <code>null</code> if a matching dossier user could not be found
	*/
	public static DossierUser fetchByDID_First(long dossierId,
		OrderByComparator<DossierUser> orderByComparator) {
		return getPersistence().fetchByDID_First(dossierId, orderByComparator);
	}

	/**
	* Returns the last dossier user in the ordered set where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier user
	* @throws NoSuchDossierUserException if a matching dossier user could not be found
	*/
	public static DossierUser findByDID_Last(long dossierId,
		OrderByComparator<DossierUser> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierUserException {
		return getPersistence().findByDID_Last(dossierId, orderByComparator);
	}

	/**
	* Returns the last dossier user in the ordered set where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier user, or <code>null</code> if a matching dossier user could not be found
	*/
	public static DossierUser fetchByDID_Last(long dossierId,
		OrderByComparator<DossierUser> orderByComparator) {
		return getPersistence().fetchByDID_Last(dossierId, orderByComparator);
	}

	/**
	* Returns the dossier users before and after the current dossier user in the ordered set where dossierId = &#63;.
	*
	* @param dossierUserPK the primary key of the current dossier user
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier user
	* @throws NoSuchDossierUserException if a dossier user with the primary key could not be found
	*/
	public static DossierUser[] findByDID_PrevAndNext(
		DossierUserPK dossierUserPK, long dossierId,
		OrderByComparator<DossierUser> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierUserException {
		return getPersistence()
				   .findByDID_PrevAndNext(dossierUserPK, dossierId,
			orderByComparator);
	}

	/**
	* Removes all the dossier users where dossierId = &#63; from the database.
	*
	* @param dossierId the dossier ID
	*/
	public static void removeByDID(long dossierId) {
		getPersistence().removeByDID(dossierId);
	}

	/**
	* Returns the number of dossier users where dossierId = &#63;.
	*
	* @param dossierId the dossier ID
	* @return the number of matching dossier users
	*/
	public static int countByDID(long dossierId) {
		return getPersistence().countByDID(dossierId);
	}

	/**
	* Returns the dossier user where dossierId = &#63; and userId = &#63; or throws a {@link NoSuchDossierUserException} if it could not be found.
	*
	* @param dossierId the dossier ID
	* @param userId the user ID
	* @return the matching dossier user
	* @throws NoSuchDossierUserException if a matching dossier user could not be found
	*/
	public static DossierUser findByDID_UID(long dossierId, long userId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierUserException {
		return getPersistence().findByDID_UID(dossierId, userId);
	}

	/**
	* Returns the dossier user where dossierId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param dossierId the dossier ID
	* @param userId the user ID
	* @return the matching dossier user, or <code>null</code> if a matching dossier user could not be found
	*/
	public static DossierUser fetchByDID_UID(long dossierId, long userId) {
		return getPersistence().fetchByDID_UID(dossierId, userId);
	}

	/**
	* Returns the dossier user where dossierId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param dossierId the dossier ID
	* @param userId the user ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier user, or <code>null</code> if a matching dossier user could not be found
	*/
	public static DossierUser fetchByDID_UID(long dossierId, long userId,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByDID_UID(dossierId, userId, retrieveFromCache);
	}

	/**
	* Removes the dossier user where dossierId = &#63; and userId = &#63; from the database.
	*
	* @param dossierId the dossier ID
	* @param userId the user ID
	* @return the dossier user that was removed
	*/
	public static DossierUser removeByDID_UID(long dossierId, long userId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierUserException {
		return getPersistence().removeByDID_UID(dossierId, userId);
	}

	/**
	* Returns the number of dossier users where dossierId = &#63; and userId = &#63;.
	*
	* @param dossierId the dossier ID
	* @param userId the user ID
	* @return the number of matching dossier users
	*/
	public static int countByDID_UID(long dossierId, long userId) {
		return getPersistence().countByDID_UID(dossierId, userId);
	}

	/**
	* Returns all the dossier users where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching dossier users
	*/
	public static List<DossierUser> findByUID(long userId) {
		return getPersistence().findByUID(userId);
	}

	/**
	* Returns a range of all the dossier users where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of dossier users
	* @param end the upper bound of the range of dossier users (not inclusive)
	* @return the range of matching dossier users
	*/
	public static List<DossierUser> findByUID(long userId, int start, int end) {
		return getPersistence().findByUID(userId, start, end);
	}

	/**
	* Returns an ordered range of all the dossier users where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of dossier users
	* @param end the upper bound of the range of dossier users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier users
	*/
	public static List<DossierUser> findByUID(long userId, int start, int end,
		OrderByComparator<DossierUser> orderByComparator) {
		return getPersistence().findByUID(userId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier users where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of dossier users
	* @param end the upper bound of the range of dossier users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier users
	*/
	public static List<DossierUser> findByUID(long userId, int start, int end,
		OrderByComparator<DossierUser> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUID(userId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first dossier user in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier user
	* @throws NoSuchDossierUserException if a matching dossier user could not be found
	*/
	public static DossierUser findByUID_First(long userId,
		OrderByComparator<DossierUser> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierUserException {
		return getPersistence().findByUID_First(userId, orderByComparator);
	}

	/**
	* Returns the first dossier user in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier user, or <code>null</code> if a matching dossier user could not be found
	*/
	public static DossierUser fetchByUID_First(long userId,
		OrderByComparator<DossierUser> orderByComparator) {
		return getPersistence().fetchByUID_First(userId, orderByComparator);
	}

	/**
	* Returns the last dossier user in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier user
	* @throws NoSuchDossierUserException if a matching dossier user could not be found
	*/
	public static DossierUser findByUID_Last(long userId,
		OrderByComparator<DossierUser> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierUserException {
		return getPersistence().findByUID_Last(userId, orderByComparator);
	}

	/**
	* Returns the last dossier user in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier user, or <code>null</code> if a matching dossier user could not be found
	*/
	public static DossierUser fetchByUID_Last(long userId,
		OrderByComparator<DossierUser> orderByComparator) {
		return getPersistence().fetchByUID_Last(userId, orderByComparator);
	}

	/**
	* Returns the dossier users before and after the current dossier user in the ordered set where userId = &#63;.
	*
	* @param dossierUserPK the primary key of the current dossier user
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier user
	* @throws NoSuchDossierUserException if a dossier user with the primary key could not be found
	*/
	public static DossierUser[] findByUID_PrevAndNext(
		DossierUserPK dossierUserPK, long userId,
		OrderByComparator<DossierUser> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierUserException {
		return getPersistence()
				   .findByUID_PrevAndNext(dossierUserPK, userId,
			orderByComparator);
	}

	/**
	* Removes all the dossier users where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public static void removeByUID(long userId) {
		getPersistence().removeByUID(userId);
	}

	/**
	* Returns the number of dossier users where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching dossier users
	*/
	public static int countByUID(long userId) {
		return getPersistence().countByUID(userId);
	}

	/**
	* Caches the dossier user in the entity cache if it is enabled.
	*
	* @param dossierUser the dossier user
	*/
	public static void cacheResult(DossierUser dossierUser) {
		getPersistence().cacheResult(dossierUser);
	}

	/**
	* Caches the dossier users in the entity cache if it is enabled.
	*
	* @param dossierUsers the dossier users
	*/
	public static void cacheResult(List<DossierUser> dossierUsers) {
		getPersistence().cacheResult(dossierUsers);
	}

	/**
	* Creates a new dossier user with the primary key. Does not add the dossier user to the database.
	*
	* @param dossierUserPK the primary key for the new dossier user
	* @return the new dossier user
	*/
	public static DossierUser create(DossierUserPK dossierUserPK) {
		return getPersistence().create(dossierUserPK);
	}

	/**
	* Removes the dossier user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierUserPK the primary key of the dossier user
	* @return the dossier user that was removed
	* @throws NoSuchDossierUserException if a dossier user with the primary key could not be found
	*/
	public static DossierUser remove(DossierUserPK dossierUserPK)
		throws org.opencps.dossiermgt.exception.NoSuchDossierUserException {
		return getPersistence().remove(dossierUserPK);
	}

	public static DossierUser updateImpl(DossierUser dossierUser) {
		return getPersistence().updateImpl(dossierUser);
	}

	/**
	* Returns the dossier user with the primary key or throws a {@link NoSuchDossierUserException} if it could not be found.
	*
	* @param dossierUserPK the primary key of the dossier user
	* @return the dossier user
	* @throws NoSuchDossierUserException if a dossier user with the primary key could not be found
	*/
	public static DossierUser findByPrimaryKey(DossierUserPK dossierUserPK)
		throws org.opencps.dossiermgt.exception.NoSuchDossierUserException {
		return getPersistence().findByPrimaryKey(dossierUserPK);
	}

	/**
	* Returns the dossier user with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dossierUserPK the primary key of the dossier user
	* @return the dossier user, or <code>null</code> if a dossier user with the primary key could not be found
	*/
	public static DossierUser fetchByPrimaryKey(DossierUserPK dossierUserPK) {
		return getPersistence().fetchByPrimaryKey(dossierUserPK);
	}

	public static java.util.Map<java.io.Serializable, DossierUser> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the dossier users.
	*
	* @return the dossier users
	*/
	public static List<DossierUser> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the dossier users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier users
	* @param end the upper bound of the range of dossier users (not inclusive)
	* @return the range of dossier users
	*/
	public static List<DossierUser> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the dossier users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier users
	* @param end the upper bound of the range of dossier users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dossier users
	*/
	public static List<DossierUser> findAll(int start, int end,
		OrderByComparator<DossierUser> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier users
	* @param end the upper bound of the range of dossier users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dossier users
	*/
	public static List<DossierUser> findAll(int start, int end,
		OrderByComparator<DossierUser> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the dossier users from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of dossier users.
	*
	* @return the number of dossier users
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static java.util.Set<String> getCompoundPKColumnNames() {
		return getPersistence().getCompoundPKColumnNames();
	}

	public static DossierUserPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DossierUserPersistence, DossierUserPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DossierUserPersistence.class);

		ServiceTracker<DossierUserPersistence, DossierUserPersistence> serviceTracker =
			new ServiceTracker<DossierUserPersistence, DossierUserPersistence>(bundle.getBundleContext(),
				DossierUserPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}