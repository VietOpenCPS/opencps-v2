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

package org.opencps.backend.processmgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.backend.processmgt.model.DossierActionUser;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the dossier action user service. This utility wraps {@link org.opencps.backend.processmgt.service.persistence.impl.DossierActionUserPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see DossierActionUserPersistence
 * @see org.opencps.backend.processmgt.service.persistence.impl.DossierActionUserPersistenceImpl
 * @generated
 */
@ProviderType
public class DossierActionUserUtil {
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
	public static void clearCache(DossierActionUser dossierActionUser) {
		getPersistence().clearCache(dossierActionUser);
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
	public static List<DossierActionUser> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DossierActionUser> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DossierActionUser> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<DossierActionUser> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static DossierActionUser update(DossierActionUser dossierActionUser) {
		return getPersistence().update(dossierActionUser);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static DossierActionUser update(
		DossierActionUser dossierActionUser, ServiceContext serviceContext) {
		return getPersistence().update(dossierActionUser, serviceContext);
	}

	/**
	* Returns all the dossier action users where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching dossier action users
	*/
	public static List<DossierActionUser> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the dossier action users where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier action users
	* @param end the upper bound of the range of dossier action users (not inclusive)
	* @return the range of matching dossier action users
	*/
	public static List<DossierActionUser> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the dossier action users where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier action users
	* @param end the upper bound of the range of dossier action users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier action users
	*/
	public static List<DossierActionUser> findByUuid(java.lang.String uuid,
		int start, int end,
		OrderByComparator<DossierActionUser> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier action users where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier action users
	* @param end the upper bound of the range of dossier action users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier action users
	*/
	public static List<DossierActionUser> findByUuid(java.lang.String uuid,
		int start, int end,
		OrderByComparator<DossierActionUser> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first dossier action user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action user
	* @throws NoSuchDossierActionUserException if a matching dossier action user could not be found
	*/
	public static DossierActionUser findByUuid_First(java.lang.String uuid,
		OrderByComparator<DossierActionUser> orderByComparator)
		throws org.opencps.backend.processmgt.exception.NoSuchDossierActionUserException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first dossier action user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action user, or <code>null</code> if a matching dossier action user could not be found
	*/
	public static DossierActionUser fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<DossierActionUser> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last dossier action user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action user
	* @throws NoSuchDossierActionUserException if a matching dossier action user could not be found
	*/
	public static DossierActionUser findByUuid_Last(java.lang.String uuid,
		OrderByComparator<DossierActionUser> orderByComparator)
		throws org.opencps.backend.processmgt.exception.NoSuchDossierActionUserException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last dossier action user in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action user, or <code>null</code> if a matching dossier action user could not be found
	*/
	public static DossierActionUser fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<DossierActionUser> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the dossier action users before and after the current dossier action user in the ordered set where uuid = &#63;.
	*
	* @param dossierActionUserPK the primary key of the current dossier action user
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier action user
	* @throws NoSuchDossierActionUserException if a dossier action user with the primary key could not be found
	*/
	public static DossierActionUser[] findByUuid_PrevAndNext(
		DossierActionUserPK dossierActionUserPK, java.lang.String uuid,
		OrderByComparator<DossierActionUser> orderByComparator)
		throws org.opencps.backend.processmgt.exception.NoSuchDossierActionUserException {
		return getPersistence()
				   .findByUuid_PrevAndNext(dossierActionUserPK, uuid,
			orderByComparator);
	}

	/**
	* Removes all the dossier action users where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of dossier action users where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching dossier action users
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns all the dossier action users where dossierActionId = &#63;.
	*
	* @param dossierActionId the dossier action ID
	* @return the matching dossier action users
	*/
	public static List<DossierActionUser> findByD_A_ID(long dossierActionId) {
		return getPersistence().findByD_A_ID(dossierActionId);
	}

	/**
	* Returns a range of all the dossier action users where dossierActionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierActionId the dossier action ID
	* @param start the lower bound of the range of dossier action users
	* @param end the upper bound of the range of dossier action users (not inclusive)
	* @return the range of matching dossier action users
	*/
	public static List<DossierActionUser> findByD_A_ID(long dossierActionId,
		int start, int end) {
		return getPersistence().findByD_A_ID(dossierActionId, start, end);
	}

	/**
	* Returns an ordered range of all the dossier action users where dossierActionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierActionId the dossier action ID
	* @param start the lower bound of the range of dossier action users
	* @param end the upper bound of the range of dossier action users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier action users
	*/
	public static List<DossierActionUser> findByD_A_ID(long dossierActionId,
		int start, int end,
		OrderByComparator<DossierActionUser> orderByComparator) {
		return getPersistence()
				   .findByD_A_ID(dossierActionId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier action users where dossierActionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossierActionId the dossier action ID
	* @param start the lower bound of the range of dossier action users
	* @param end the upper bound of the range of dossier action users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier action users
	*/
	public static List<DossierActionUser> findByD_A_ID(long dossierActionId,
		int start, int end,
		OrderByComparator<DossierActionUser> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByD_A_ID(dossierActionId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier action user in the ordered set where dossierActionId = &#63;.
	*
	* @param dossierActionId the dossier action ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action user
	* @throws NoSuchDossierActionUserException if a matching dossier action user could not be found
	*/
	public static DossierActionUser findByD_A_ID_First(long dossierActionId,
		OrderByComparator<DossierActionUser> orderByComparator)
		throws org.opencps.backend.processmgt.exception.NoSuchDossierActionUserException {
		return getPersistence()
				   .findByD_A_ID_First(dossierActionId, orderByComparator);
	}

	/**
	* Returns the first dossier action user in the ordered set where dossierActionId = &#63;.
	*
	* @param dossierActionId the dossier action ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier action user, or <code>null</code> if a matching dossier action user could not be found
	*/
	public static DossierActionUser fetchByD_A_ID_First(long dossierActionId,
		OrderByComparator<DossierActionUser> orderByComparator) {
		return getPersistence()
				   .fetchByD_A_ID_First(dossierActionId, orderByComparator);
	}

	/**
	* Returns the last dossier action user in the ordered set where dossierActionId = &#63;.
	*
	* @param dossierActionId the dossier action ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action user
	* @throws NoSuchDossierActionUserException if a matching dossier action user could not be found
	*/
	public static DossierActionUser findByD_A_ID_Last(long dossierActionId,
		OrderByComparator<DossierActionUser> orderByComparator)
		throws org.opencps.backend.processmgt.exception.NoSuchDossierActionUserException {
		return getPersistence()
				   .findByD_A_ID_Last(dossierActionId, orderByComparator);
	}

	/**
	* Returns the last dossier action user in the ordered set where dossierActionId = &#63;.
	*
	* @param dossierActionId the dossier action ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier action user, or <code>null</code> if a matching dossier action user could not be found
	*/
	public static DossierActionUser fetchByD_A_ID_Last(long dossierActionId,
		OrderByComparator<DossierActionUser> orderByComparator) {
		return getPersistence()
				   .fetchByD_A_ID_Last(dossierActionId, orderByComparator);
	}

	/**
	* Returns the dossier action users before and after the current dossier action user in the ordered set where dossierActionId = &#63;.
	*
	* @param dossierActionUserPK the primary key of the current dossier action user
	* @param dossierActionId the dossier action ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier action user
	* @throws NoSuchDossierActionUserException if a dossier action user with the primary key could not be found
	*/
	public static DossierActionUser[] findByD_A_ID_PrevAndNext(
		DossierActionUserPK dossierActionUserPK, long dossierActionId,
		OrderByComparator<DossierActionUser> orderByComparator)
		throws org.opencps.backend.processmgt.exception.NoSuchDossierActionUserException {
		return getPersistence()
				   .findByD_A_ID_PrevAndNext(dossierActionUserPK,
			dossierActionId, orderByComparator);
	}

	/**
	* Removes all the dossier action users where dossierActionId = &#63; from the database.
	*
	* @param dossierActionId the dossier action ID
	*/
	public static void removeByD_A_ID(long dossierActionId) {
		getPersistence().removeByD_A_ID(dossierActionId);
	}

	/**
	* Returns the number of dossier action users where dossierActionId = &#63;.
	*
	* @param dossierActionId the dossier action ID
	* @return the number of matching dossier action users
	*/
	public static int countByD_A_ID(long dossierActionId) {
		return getPersistence().countByD_A_ID(dossierActionId);
	}

	/**
	* Caches the dossier action user in the entity cache if it is enabled.
	*
	* @param dossierActionUser the dossier action user
	*/
	public static void cacheResult(DossierActionUser dossierActionUser) {
		getPersistence().cacheResult(dossierActionUser);
	}

	/**
	* Caches the dossier action users in the entity cache if it is enabled.
	*
	* @param dossierActionUsers the dossier action users
	*/
	public static void cacheResult(List<DossierActionUser> dossierActionUsers) {
		getPersistence().cacheResult(dossierActionUsers);
	}

	/**
	* Creates a new dossier action user with the primary key. Does not add the dossier action user to the database.
	*
	* @param dossierActionUserPK the primary key for the new dossier action user
	* @return the new dossier action user
	*/
	public static DossierActionUser create(
		DossierActionUserPK dossierActionUserPK) {
		return getPersistence().create(dossierActionUserPK);
	}

	/**
	* Removes the dossier action user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierActionUserPK the primary key of the dossier action user
	* @return the dossier action user that was removed
	* @throws NoSuchDossierActionUserException if a dossier action user with the primary key could not be found
	*/
	public static DossierActionUser remove(
		DossierActionUserPK dossierActionUserPK)
		throws org.opencps.backend.processmgt.exception.NoSuchDossierActionUserException {
		return getPersistence().remove(dossierActionUserPK);
	}

	public static DossierActionUser updateImpl(
		DossierActionUser dossierActionUser) {
		return getPersistence().updateImpl(dossierActionUser);
	}

	/**
	* Returns the dossier action user with the primary key or throws a {@link NoSuchDossierActionUserException} if it could not be found.
	*
	* @param dossierActionUserPK the primary key of the dossier action user
	* @return the dossier action user
	* @throws NoSuchDossierActionUserException if a dossier action user with the primary key could not be found
	*/
	public static DossierActionUser findByPrimaryKey(
		DossierActionUserPK dossierActionUserPK)
		throws org.opencps.backend.processmgt.exception.NoSuchDossierActionUserException {
		return getPersistence().findByPrimaryKey(dossierActionUserPK);
	}

	/**
	* Returns the dossier action user with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dossierActionUserPK the primary key of the dossier action user
	* @return the dossier action user, or <code>null</code> if a dossier action user with the primary key could not be found
	*/
	public static DossierActionUser fetchByPrimaryKey(
		DossierActionUserPK dossierActionUserPK) {
		return getPersistence().fetchByPrimaryKey(dossierActionUserPK);
	}

	public static java.util.Map<java.io.Serializable, DossierActionUser> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the dossier action users.
	*
	* @return the dossier action users
	*/
	public static List<DossierActionUser> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the dossier action users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier action users
	* @param end the upper bound of the range of dossier action users (not inclusive)
	* @return the range of dossier action users
	*/
	public static List<DossierActionUser> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the dossier action users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier action users
	* @param end the upper bound of the range of dossier action users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dossier action users
	*/
	public static List<DossierActionUser> findAll(int start, int end,
		OrderByComparator<DossierActionUser> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier action users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierActionUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier action users
	* @param end the upper bound of the range of dossier action users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dossier action users
	*/
	public static List<DossierActionUser> findAll(int start, int end,
		OrderByComparator<DossierActionUser> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the dossier action users from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of dossier action users.
	*
	* @return the number of dossier action users
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static DossierActionUserPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DossierActionUserPersistence, DossierActionUserPersistence> _serviceTracker =
		ServiceTrackerFactory.open(DossierActionUserPersistence.class);
}