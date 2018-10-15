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

package org.opencps.usermgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.usermgt.model.Visibility;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the visibility service. This utility wraps {@link org.opencps.usermgt.service.persistence.impl.VisibilityPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see VisibilityPersistence
 * @see org.opencps.usermgt.service.persistence.impl.VisibilityPersistenceImpl
 * @generated
 */
@ProviderType
public class VisibilityUtil {
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
	public static void clearCache(Visibility visibility) {
		getPersistence().clearCache(visibility);
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
	public static List<Visibility> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Visibility> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Visibility> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Visibility> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Visibility update(Visibility visibility) {
		return getPersistence().update(visibility);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Visibility update(Visibility visibility,
		ServiceContext serviceContext) {
		return getPersistence().update(visibility, serviceContext);
	}

	/**
	* Returns all the visibilities where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching visibilities
	*/
	public static List<Visibility> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the visibilities where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VisibilityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of visibilities
	* @param end the upper bound of the range of visibilities (not inclusive)
	* @return the range of matching visibilities
	*/
	public static List<Visibility> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the visibilities where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VisibilityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of visibilities
	* @param end the upper bound of the range of visibilities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching visibilities
	*/
	public static List<Visibility> findByUuid(String uuid, int start, int end,
		OrderByComparator<Visibility> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the visibilities where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VisibilityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of visibilities
	* @param end the upper bound of the range of visibilities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching visibilities
	*/
	public static List<Visibility> findByUuid(String uuid, int start, int end,
		OrderByComparator<Visibility> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first visibility in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching visibility
	* @throws NoSuchVisibilityException if a matching visibility could not be found
	*/
	public static Visibility findByUuid_First(String uuid,
		OrderByComparator<Visibility> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchVisibilityException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first visibility in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching visibility, or <code>null</code> if a matching visibility could not be found
	*/
	public static Visibility fetchByUuid_First(String uuid,
		OrderByComparator<Visibility> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last visibility in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching visibility
	* @throws NoSuchVisibilityException if a matching visibility could not be found
	*/
	public static Visibility findByUuid_Last(String uuid,
		OrderByComparator<Visibility> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchVisibilityException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last visibility in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching visibility, or <code>null</code> if a matching visibility could not be found
	*/
	public static Visibility fetchByUuid_Last(String uuid,
		OrderByComparator<Visibility> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the visibilities before and after the current visibility in the ordered set where uuid = &#63;.
	*
	* @param visibilityId the primary key of the current visibility
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next visibility
	* @throws NoSuchVisibilityException if a visibility with the primary key could not be found
	*/
	public static Visibility[] findByUuid_PrevAndNext(long visibilityId,
		String uuid, OrderByComparator<Visibility> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchVisibilityException {
		return getPersistence()
				   .findByUuid_PrevAndNext(visibilityId, uuid, orderByComparator);
	}

	/**
	* Removes all the visibilities where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of visibilities where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching visibilities
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the visibility where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchVisibilityException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching visibility
	* @throws NoSuchVisibilityException if a matching visibility could not be found
	*/
	public static Visibility findByUUID_G(String uuid, long groupId)
		throws org.opencps.usermgt.exception.NoSuchVisibilityException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the visibility where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching visibility, or <code>null</code> if a matching visibility could not be found
	*/
	public static Visibility fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the visibility where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching visibility, or <code>null</code> if a matching visibility could not be found
	*/
	public static Visibility fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the visibility where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the visibility that was removed
	*/
	public static Visibility removeByUUID_G(String uuid, long groupId)
		throws org.opencps.usermgt.exception.NoSuchVisibilityException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of visibilities where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching visibilities
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the visibilities where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching visibilities
	*/
	public static List<Visibility> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the visibilities where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VisibilityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of visibilities
	* @param end the upper bound of the range of visibilities (not inclusive)
	* @return the range of matching visibilities
	*/
	public static List<Visibility> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the visibilities where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VisibilityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of visibilities
	* @param end the upper bound of the range of visibilities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching visibilities
	*/
	public static List<Visibility> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<Visibility> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the visibilities where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VisibilityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of visibilities
	* @param end the upper bound of the range of visibilities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching visibilities
	*/
	public static List<Visibility> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<Visibility> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first visibility in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching visibility
	* @throws NoSuchVisibilityException if a matching visibility could not be found
	*/
	public static Visibility findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Visibility> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchVisibilityException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first visibility in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching visibility, or <code>null</code> if a matching visibility could not be found
	*/
	public static Visibility fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<Visibility> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last visibility in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching visibility
	* @throws NoSuchVisibilityException if a matching visibility could not be found
	*/
	public static Visibility findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Visibility> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchVisibilityException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last visibility in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching visibility, or <code>null</code> if a matching visibility could not be found
	*/
	public static Visibility fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<Visibility> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the visibilities before and after the current visibility in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param visibilityId the primary key of the current visibility
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next visibility
	* @throws NoSuchVisibilityException if a visibility with the primary key could not be found
	*/
	public static Visibility[] findByUuid_C_PrevAndNext(long visibilityId,
		String uuid, long companyId,
		OrderByComparator<Visibility> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchVisibilityException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(visibilityId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the visibilities where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of visibilities where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching visibilities
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Caches the visibility in the entity cache if it is enabled.
	*
	* @param visibility the visibility
	*/
	public static void cacheResult(Visibility visibility) {
		getPersistence().cacheResult(visibility);
	}

	/**
	* Caches the visibilities in the entity cache if it is enabled.
	*
	* @param visibilities the visibilities
	*/
	public static void cacheResult(List<Visibility> visibilities) {
		getPersistence().cacheResult(visibilities);
	}

	/**
	* Creates a new visibility with the primary key. Does not add the visibility to the database.
	*
	* @param visibilityId the primary key for the new visibility
	* @return the new visibility
	*/
	public static Visibility create(long visibilityId) {
		return getPersistence().create(visibilityId);
	}

	/**
	* Removes the visibility with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param visibilityId the primary key of the visibility
	* @return the visibility that was removed
	* @throws NoSuchVisibilityException if a visibility with the primary key could not be found
	*/
	public static Visibility remove(long visibilityId)
		throws org.opencps.usermgt.exception.NoSuchVisibilityException {
		return getPersistence().remove(visibilityId);
	}

	public static Visibility updateImpl(Visibility visibility) {
		return getPersistence().updateImpl(visibility);
	}

	/**
	* Returns the visibility with the primary key or throws a {@link NoSuchVisibilityException} if it could not be found.
	*
	* @param visibilityId the primary key of the visibility
	* @return the visibility
	* @throws NoSuchVisibilityException if a visibility with the primary key could not be found
	*/
	public static Visibility findByPrimaryKey(long visibilityId)
		throws org.opencps.usermgt.exception.NoSuchVisibilityException {
		return getPersistence().findByPrimaryKey(visibilityId);
	}

	/**
	* Returns the visibility with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param visibilityId the primary key of the visibility
	* @return the visibility, or <code>null</code> if a visibility with the primary key could not be found
	*/
	public static Visibility fetchByPrimaryKey(long visibilityId) {
		return getPersistence().fetchByPrimaryKey(visibilityId);
	}

	public static java.util.Map<java.io.Serializable, Visibility> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the visibilities.
	*
	* @return the visibilities
	*/
	public static List<Visibility> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the visibilities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VisibilityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of visibilities
	* @param end the upper bound of the range of visibilities (not inclusive)
	* @return the range of visibilities
	*/
	public static List<Visibility> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the visibilities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VisibilityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of visibilities
	* @param end the upper bound of the range of visibilities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of visibilities
	*/
	public static List<Visibility> findAll(int start, int end,
		OrderByComparator<Visibility> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the visibilities.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VisibilityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of visibilities
	* @param end the upper bound of the range of visibilities (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of visibilities
	*/
	public static List<Visibility> findAll(int start, int end,
		OrderByComparator<Visibility> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the visibilities from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of visibilities.
	*
	* @return the number of visibilities
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static VisibilityPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<VisibilityPersistence, VisibilityPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(VisibilityPersistence.class);

		ServiceTracker<VisibilityPersistence, VisibilityPersistence> serviceTracker =
			new ServiceTracker<VisibilityPersistence, VisibilityPersistence>(bundle.getBundleContext(),
				VisibilityPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}