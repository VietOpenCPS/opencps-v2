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

package org.opencps.synchronization.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.synchronization.model.PushCollection;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the push collection service. This utility wraps {@link org.opencps.synchronization.service.persistence.impl.PushCollectionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author trungdk
 * @see PushCollectionPersistence
 * @see org.opencps.synchronization.service.persistence.impl.PushCollectionPersistenceImpl
 * @generated
 */
@ProviderType
public class PushCollectionUtil {
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
	public static void clearCache(PushCollection pushCollection) {
		getPersistence().clearCache(pushCollection);
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
	public static List<PushCollection> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<PushCollection> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<PushCollection> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<PushCollection> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static PushCollection update(PushCollection pushCollection) {
		return getPersistence().update(pushCollection);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static PushCollection update(PushCollection pushCollection,
		ServiceContext serviceContext) {
		return getPersistence().update(pushCollection, serviceContext);
	}

	/**
	* Returns all the push collections where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching push collections
	*/
	public static List<PushCollection> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the push collections where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of push collections
	* @param end the upper bound of the range of push collections (not inclusive)
	* @return the range of matching push collections
	*/
	public static List<PushCollection> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the push collections where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of push collections
	* @param end the upper bound of the range of push collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching push collections
	*/
	public static List<PushCollection> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<PushCollection> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the push collections where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of push collections
	* @param end the upper bound of the range of push collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching push collections
	*/
	public static List<PushCollection> findByUuid(java.lang.String uuid,
		int start, int end,
		OrderByComparator<PushCollection> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first push collection in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching push collection
	* @throws NoSuchPushCollectionException if a matching push collection could not be found
	*/
	public static PushCollection findByUuid_First(java.lang.String uuid,
		OrderByComparator<PushCollection> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchPushCollectionException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first push collection in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching push collection, or <code>null</code> if a matching push collection could not be found
	*/
	public static PushCollection fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<PushCollection> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last push collection in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching push collection
	* @throws NoSuchPushCollectionException if a matching push collection could not be found
	*/
	public static PushCollection findByUuid_Last(java.lang.String uuid,
		OrderByComparator<PushCollection> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchPushCollectionException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last push collection in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching push collection, or <code>null</code> if a matching push collection could not be found
	*/
	public static PushCollection fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<PushCollection> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the push collections before and after the current push collection in the ordered set where uuid = &#63;.
	*
	* @param pushCollectionId the primary key of the current push collection
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next push collection
	* @throws NoSuchPushCollectionException if a push collection with the primary key could not be found
	*/
	public static PushCollection[] findByUuid_PrevAndNext(
		long pushCollectionId, java.lang.String uuid,
		OrderByComparator<PushCollection> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchPushCollectionException {
		return getPersistence()
				   .findByUuid_PrevAndNext(pushCollectionId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the push collections where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of push collections where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching push collections
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the push collection where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPushCollectionException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching push collection
	* @throws NoSuchPushCollectionException if a matching push collection could not be found
	*/
	public static PushCollection findByUUID_G(java.lang.String uuid,
		long groupId)
		throws org.opencps.synchronization.exception.NoSuchPushCollectionException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the push collection where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching push collection, or <code>null</code> if a matching push collection could not be found
	*/
	public static PushCollection fetchByUUID_G(java.lang.String uuid,
		long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the push collection where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching push collection, or <code>null</code> if a matching push collection could not be found
	*/
	public static PushCollection fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the push collection where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the push collection that was removed
	*/
	public static PushCollection removeByUUID_G(java.lang.String uuid,
		long groupId)
		throws org.opencps.synchronization.exception.NoSuchPushCollectionException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of push collections where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching push collections
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the push collections where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching push collections
	*/
	public static List<PushCollection> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the push collections where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of push collections
	* @param end the upper bound of the range of push collections (not inclusive)
	* @return the range of matching push collections
	*/
	public static List<PushCollection> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the push collections where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of push collections
	* @param end the upper bound of the range of push collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching push collections
	*/
	public static List<PushCollection> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<PushCollection> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the push collections where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of push collections
	* @param end the upper bound of the range of push collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching push collections
	*/
	public static List<PushCollection> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<PushCollection> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first push collection in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching push collection
	* @throws NoSuchPushCollectionException if a matching push collection could not be found
	*/
	public static PushCollection findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<PushCollection> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchPushCollectionException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first push collection in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching push collection, or <code>null</code> if a matching push collection could not be found
	*/
	public static PushCollection fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<PushCollection> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last push collection in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching push collection
	* @throws NoSuchPushCollectionException if a matching push collection could not be found
	*/
	public static PushCollection findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<PushCollection> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchPushCollectionException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last push collection in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching push collection, or <code>null</code> if a matching push collection could not be found
	*/
	public static PushCollection fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<PushCollection> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the push collections before and after the current push collection in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param pushCollectionId the primary key of the current push collection
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next push collection
	* @throws NoSuchPushCollectionException if a push collection with the primary key could not be found
	*/
	public static PushCollection[] findByUuid_C_PrevAndNext(
		long pushCollectionId, java.lang.String uuid, long companyId,
		OrderByComparator<PushCollection> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchPushCollectionException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(pushCollectionId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the push collections where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of push collections where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching push collections
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the push collection where groupId = &#63; and collectionCode = &#63; and method = &#63; or throws a {@link NoSuchPushCollectionException} if it could not be found.
	*
	* @param groupId the group ID
	* @param collectionCode the collection code
	* @param method the method
	* @return the matching push collection
	* @throws NoSuchPushCollectionException if a matching push collection could not be found
	*/
	public static PushCollection findByF_collectionCode_Method(long groupId,
		java.lang.String collectionCode, java.lang.String method)
		throws org.opencps.synchronization.exception.NoSuchPushCollectionException {
		return getPersistence()
				   .findByF_collectionCode_Method(groupId, collectionCode,
			method);
	}

	/**
	* Returns the push collection where groupId = &#63; and collectionCode = &#63; and method = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param collectionCode the collection code
	* @param method the method
	* @return the matching push collection, or <code>null</code> if a matching push collection could not be found
	*/
	public static PushCollection fetchByF_collectionCode_Method(long groupId,
		java.lang.String collectionCode, java.lang.String method) {
		return getPersistence()
				   .fetchByF_collectionCode_Method(groupId, collectionCode,
			method);
	}

	/**
	* Returns the push collection where groupId = &#63; and collectionCode = &#63; and method = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param collectionCode the collection code
	* @param method the method
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching push collection, or <code>null</code> if a matching push collection could not be found
	*/
	public static PushCollection fetchByF_collectionCode_Method(long groupId,
		java.lang.String collectionCode, java.lang.String method,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_collectionCode_Method(groupId, collectionCode,
			method, retrieveFromCache);
	}

	/**
	* Removes the push collection where groupId = &#63; and collectionCode = &#63; and method = &#63; from the database.
	*
	* @param groupId the group ID
	* @param collectionCode the collection code
	* @param method the method
	* @return the push collection that was removed
	*/
	public static PushCollection removeByF_collectionCode_Method(long groupId,
		java.lang.String collectionCode, java.lang.String method)
		throws org.opencps.synchronization.exception.NoSuchPushCollectionException {
		return getPersistence()
				   .removeByF_collectionCode_Method(groupId, collectionCode,
			method);
	}

	/**
	* Returns the number of push collections where groupId = &#63; and collectionCode = &#63; and method = &#63;.
	*
	* @param groupId the group ID
	* @param collectionCode the collection code
	* @param method the method
	* @return the number of matching push collections
	*/
	public static int countByF_collectionCode_Method(long groupId,
		java.lang.String collectionCode, java.lang.String method) {
		return getPersistence()
				   .countByF_collectionCode_Method(groupId, collectionCode,
			method);
	}

	/**
	* Returns all the push collections where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @return the matching push collections
	*/
	public static List<PushCollection> findByF_groupId_serverNo(long groupId,
		java.lang.String serverNo) {
		return getPersistence().findByF_groupId_serverNo(groupId, serverNo);
	}

	/**
	* Returns a range of all the push collections where groupId = &#63; and serverNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param start the lower bound of the range of push collections
	* @param end the upper bound of the range of push collections (not inclusive)
	* @return the range of matching push collections
	*/
	public static List<PushCollection> findByF_groupId_serverNo(long groupId,
		java.lang.String serverNo, int start, int end) {
		return getPersistence()
				   .findByF_groupId_serverNo(groupId, serverNo, start, end);
	}

	/**
	* Returns an ordered range of all the push collections where groupId = &#63; and serverNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param start the lower bound of the range of push collections
	* @param end the upper bound of the range of push collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching push collections
	*/
	public static List<PushCollection> findByF_groupId_serverNo(long groupId,
		java.lang.String serverNo, int start, int end,
		OrderByComparator<PushCollection> orderByComparator) {
		return getPersistence()
				   .findByF_groupId_serverNo(groupId, serverNo, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the push collections where groupId = &#63; and serverNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param start the lower bound of the range of push collections
	* @param end the upper bound of the range of push collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching push collections
	*/
	public static List<PushCollection> findByF_groupId_serverNo(long groupId,
		java.lang.String serverNo, int start, int end,
		OrderByComparator<PushCollection> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_groupId_serverNo(groupId, serverNo, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first push collection in the ordered set where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching push collection
	* @throws NoSuchPushCollectionException if a matching push collection could not be found
	*/
	public static PushCollection findByF_groupId_serverNo_First(long groupId,
		java.lang.String serverNo,
		OrderByComparator<PushCollection> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchPushCollectionException {
		return getPersistence()
				   .findByF_groupId_serverNo_First(groupId, serverNo,
			orderByComparator);
	}

	/**
	* Returns the first push collection in the ordered set where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching push collection, or <code>null</code> if a matching push collection could not be found
	*/
	public static PushCollection fetchByF_groupId_serverNo_First(long groupId,
		java.lang.String serverNo,
		OrderByComparator<PushCollection> orderByComparator) {
		return getPersistence()
				   .fetchByF_groupId_serverNo_First(groupId, serverNo,
			orderByComparator);
	}

	/**
	* Returns the last push collection in the ordered set where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching push collection
	* @throws NoSuchPushCollectionException if a matching push collection could not be found
	*/
	public static PushCollection findByF_groupId_serverNo_Last(long groupId,
		java.lang.String serverNo,
		OrderByComparator<PushCollection> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchPushCollectionException {
		return getPersistence()
				   .findByF_groupId_serverNo_Last(groupId, serverNo,
			orderByComparator);
	}

	/**
	* Returns the last push collection in the ordered set where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching push collection, or <code>null</code> if a matching push collection could not be found
	*/
	public static PushCollection fetchByF_groupId_serverNo_Last(long groupId,
		java.lang.String serverNo,
		OrderByComparator<PushCollection> orderByComparator) {
		return getPersistence()
				   .fetchByF_groupId_serverNo_Last(groupId, serverNo,
			orderByComparator);
	}

	/**
	* Returns the push collections before and after the current push collection in the ordered set where groupId = &#63; and serverNo = &#63;.
	*
	* @param pushCollectionId the primary key of the current push collection
	* @param groupId the group ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next push collection
	* @throws NoSuchPushCollectionException if a push collection with the primary key could not be found
	*/
	public static PushCollection[] findByF_groupId_serverNo_PrevAndNext(
		long pushCollectionId, long groupId, java.lang.String serverNo,
		OrderByComparator<PushCollection> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchPushCollectionException {
		return getPersistence()
				   .findByF_groupId_serverNo_PrevAndNext(pushCollectionId,
			groupId, serverNo, orderByComparator);
	}

	/**
	* Removes all the push collections where groupId = &#63; and serverNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	*/
	public static void removeByF_groupId_serverNo(long groupId,
		java.lang.String serverNo) {
		getPersistence().removeByF_groupId_serverNo(groupId, serverNo);
	}

	/**
	* Returns the number of push collections where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @return the number of matching push collections
	*/
	public static int countByF_groupId_serverNo(long groupId,
		java.lang.String serverNo) {
		return getPersistence().countByF_groupId_serverNo(groupId, serverNo);
	}

	/**
	* Caches the push collection in the entity cache if it is enabled.
	*
	* @param pushCollection the push collection
	*/
	public static void cacheResult(PushCollection pushCollection) {
		getPersistence().cacheResult(pushCollection);
	}

	/**
	* Caches the push collections in the entity cache if it is enabled.
	*
	* @param pushCollections the push collections
	*/
	public static void cacheResult(List<PushCollection> pushCollections) {
		getPersistence().cacheResult(pushCollections);
	}

	/**
	* Creates a new push collection with the primary key. Does not add the push collection to the database.
	*
	* @param pushCollectionId the primary key for the new push collection
	* @return the new push collection
	*/
	public static PushCollection create(long pushCollectionId) {
		return getPersistence().create(pushCollectionId);
	}

	/**
	* Removes the push collection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param pushCollectionId the primary key of the push collection
	* @return the push collection that was removed
	* @throws NoSuchPushCollectionException if a push collection with the primary key could not be found
	*/
	public static PushCollection remove(long pushCollectionId)
		throws org.opencps.synchronization.exception.NoSuchPushCollectionException {
		return getPersistence().remove(pushCollectionId);
	}

	public static PushCollection updateImpl(PushCollection pushCollection) {
		return getPersistence().updateImpl(pushCollection);
	}

	/**
	* Returns the push collection with the primary key or throws a {@link NoSuchPushCollectionException} if it could not be found.
	*
	* @param pushCollectionId the primary key of the push collection
	* @return the push collection
	* @throws NoSuchPushCollectionException if a push collection with the primary key could not be found
	*/
	public static PushCollection findByPrimaryKey(long pushCollectionId)
		throws org.opencps.synchronization.exception.NoSuchPushCollectionException {
		return getPersistence().findByPrimaryKey(pushCollectionId);
	}

	/**
	* Returns the push collection with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param pushCollectionId the primary key of the push collection
	* @return the push collection, or <code>null</code> if a push collection with the primary key could not be found
	*/
	public static PushCollection fetchByPrimaryKey(long pushCollectionId) {
		return getPersistence().fetchByPrimaryKey(pushCollectionId);
	}

	public static java.util.Map<java.io.Serializable, PushCollection> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the push collections.
	*
	* @return the push collections
	*/
	public static List<PushCollection> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the push collections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of push collections
	* @param end the upper bound of the range of push collections (not inclusive)
	* @return the range of push collections
	*/
	public static List<PushCollection> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the push collections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of push collections
	* @param end the upper bound of the range of push collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of push collections
	*/
	public static List<PushCollection> findAll(int start, int end,
		OrderByComparator<PushCollection> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the push collections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of push collections
	* @param end the upper bound of the range of push collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of push collections
	*/
	public static List<PushCollection> findAll(int start, int end,
		OrderByComparator<PushCollection> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the push collections from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of push collections.
	*
	* @return the number of push collections
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static PushCollectionPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PushCollectionPersistence, PushCollectionPersistence> _serviceTracker =
		ServiceTrackerFactory.open(PushCollectionPersistence.class);
}