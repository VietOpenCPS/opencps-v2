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

import org.opencps.synchronization.model.PushDictItem;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the push dict item service. This utility wraps {@link org.opencps.synchronization.service.persistence.impl.PushDictItemPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author trungdk
 * @see PushDictItemPersistence
 * @see org.opencps.synchronization.service.persistence.impl.PushDictItemPersistenceImpl
 * @generated
 */
@ProviderType
public class PushDictItemUtil {
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
	public static void clearCache(PushDictItem pushDictItem) {
		getPersistence().clearCache(pushDictItem);
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
	public static List<PushDictItem> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<PushDictItem> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<PushDictItem> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<PushDictItem> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static PushDictItem update(PushDictItem pushDictItem) {
		return getPersistence().update(pushDictItem);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static PushDictItem update(PushDictItem pushDictItem,
		ServiceContext serviceContext) {
		return getPersistence().update(pushDictItem, serviceContext);
	}

	/**
	* Returns all the push dict items where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching push dict items
	*/
	public static List<PushDictItem> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the push dict items where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of push dict items
	* @param end the upper bound of the range of push dict items (not inclusive)
	* @return the range of matching push dict items
	*/
	public static List<PushDictItem> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the push dict items where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of push dict items
	* @param end the upper bound of the range of push dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching push dict items
	*/
	public static List<PushDictItem> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<PushDictItem> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the push dict items where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of push dict items
	* @param end the upper bound of the range of push dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching push dict items
	*/
	public static List<PushDictItem> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<PushDictItem> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first push dict item in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching push dict item
	* @throws NoSuchPushDictItemException if a matching push dict item could not be found
	*/
	public static PushDictItem findByUuid_First(java.lang.String uuid,
		OrderByComparator<PushDictItem> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchPushDictItemException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first push dict item in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching push dict item, or <code>null</code> if a matching push dict item could not be found
	*/
	public static PushDictItem fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<PushDictItem> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last push dict item in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching push dict item
	* @throws NoSuchPushDictItemException if a matching push dict item could not be found
	*/
	public static PushDictItem findByUuid_Last(java.lang.String uuid,
		OrderByComparator<PushDictItem> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchPushDictItemException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last push dict item in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching push dict item, or <code>null</code> if a matching push dict item could not be found
	*/
	public static PushDictItem fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<PushDictItem> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the push dict items before and after the current push dict item in the ordered set where uuid = &#63;.
	*
	* @param pushDictItemId the primary key of the current push dict item
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next push dict item
	* @throws NoSuchPushDictItemException if a push dict item with the primary key could not be found
	*/
	public static PushDictItem[] findByUuid_PrevAndNext(long pushDictItemId,
		java.lang.String uuid, OrderByComparator<PushDictItem> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchPushDictItemException {
		return getPersistence()
				   .findByUuid_PrevAndNext(pushDictItemId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the push dict items where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of push dict items where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching push dict items
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the push dict item where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchPushDictItemException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching push dict item
	* @throws NoSuchPushDictItemException if a matching push dict item could not be found
	*/
	public static PushDictItem findByUUID_G(java.lang.String uuid, long groupId)
		throws org.opencps.synchronization.exception.NoSuchPushDictItemException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the push dict item where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching push dict item, or <code>null</code> if a matching push dict item could not be found
	*/
	public static PushDictItem fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the push dict item where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching push dict item, or <code>null</code> if a matching push dict item could not be found
	*/
	public static PushDictItem fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the push dict item where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the push dict item that was removed
	*/
	public static PushDictItem removeByUUID_G(java.lang.String uuid,
		long groupId)
		throws org.opencps.synchronization.exception.NoSuchPushDictItemException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of push dict items where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching push dict items
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the push dict items where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching push dict items
	*/
	public static List<PushDictItem> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the push dict items where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of push dict items
	* @param end the upper bound of the range of push dict items (not inclusive)
	* @return the range of matching push dict items
	*/
	public static List<PushDictItem> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the push dict items where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of push dict items
	* @param end the upper bound of the range of push dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching push dict items
	*/
	public static List<PushDictItem> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<PushDictItem> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the push dict items where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of push dict items
	* @param end the upper bound of the range of push dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching push dict items
	*/
	public static List<PushDictItem> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<PushDictItem> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first push dict item in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching push dict item
	* @throws NoSuchPushDictItemException if a matching push dict item could not be found
	*/
	public static PushDictItem findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<PushDictItem> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchPushDictItemException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first push dict item in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching push dict item, or <code>null</code> if a matching push dict item could not be found
	*/
	public static PushDictItem fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<PushDictItem> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last push dict item in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching push dict item
	* @throws NoSuchPushDictItemException if a matching push dict item could not be found
	*/
	public static PushDictItem findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<PushDictItem> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchPushDictItemException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last push dict item in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching push dict item, or <code>null</code> if a matching push dict item could not be found
	*/
	public static PushDictItem fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<PushDictItem> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the push dict items before and after the current push dict item in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param pushDictItemId the primary key of the current push dict item
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next push dict item
	* @throws NoSuchPushDictItemException if a push dict item with the primary key could not be found
	*/
	public static PushDictItem[] findByUuid_C_PrevAndNext(long pushDictItemId,
		java.lang.String uuid, long companyId,
		OrderByComparator<PushDictItem> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchPushDictItemException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(pushDictItemId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the push dict items where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of push dict items where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching push dict items
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the push dict item where groupId = &#63; and collectionCode = &#63; and itemCode = &#63; and method = &#63; or throws a {@link NoSuchPushDictItemException} if it could not be found.
	*
	* @param groupId the group ID
	* @param collectionCode the collection code
	* @param itemCode the item code
	* @param method the method
	* @return the matching push dict item
	* @throws NoSuchPushDictItemException if a matching push dict item could not be found
	*/
	public static PushDictItem findByF_collectionCode_itemCode_Method(
		long groupId, java.lang.String collectionCode,
		java.lang.String itemCode, java.lang.String method)
		throws org.opencps.synchronization.exception.NoSuchPushDictItemException {
		return getPersistence()
				   .findByF_collectionCode_itemCode_Method(groupId,
			collectionCode, itemCode, method);
	}

	/**
	* Returns the push dict item where groupId = &#63; and collectionCode = &#63; and itemCode = &#63; and method = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param collectionCode the collection code
	* @param itemCode the item code
	* @param method the method
	* @return the matching push dict item, or <code>null</code> if a matching push dict item could not be found
	*/
	public static PushDictItem fetchByF_collectionCode_itemCode_Method(
		long groupId, java.lang.String collectionCode,
		java.lang.String itemCode, java.lang.String method) {
		return getPersistence()
				   .fetchByF_collectionCode_itemCode_Method(groupId,
			collectionCode, itemCode, method);
	}

	/**
	* Returns the push dict item where groupId = &#63; and collectionCode = &#63; and itemCode = &#63; and method = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param collectionCode the collection code
	* @param itemCode the item code
	* @param method the method
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching push dict item, or <code>null</code> if a matching push dict item could not be found
	*/
	public static PushDictItem fetchByF_collectionCode_itemCode_Method(
		long groupId, java.lang.String collectionCode,
		java.lang.String itemCode, java.lang.String method,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_collectionCode_itemCode_Method(groupId,
			collectionCode, itemCode, method, retrieveFromCache);
	}

	/**
	* Removes the push dict item where groupId = &#63; and collectionCode = &#63; and itemCode = &#63; and method = &#63; from the database.
	*
	* @param groupId the group ID
	* @param collectionCode the collection code
	* @param itemCode the item code
	* @param method the method
	* @return the push dict item that was removed
	*/
	public static PushDictItem removeByF_collectionCode_itemCode_Method(
		long groupId, java.lang.String collectionCode,
		java.lang.String itemCode, java.lang.String method)
		throws org.opencps.synchronization.exception.NoSuchPushDictItemException {
		return getPersistence()
				   .removeByF_collectionCode_itemCode_Method(groupId,
			collectionCode, itemCode, method);
	}

	/**
	* Returns the number of push dict items where groupId = &#63; and collectionCode = &#63; and itemCode = &#63; and method = &#63;.
	*
	* @param groupId the group ID
	* @param collectionCode the collection code
	* @param itemCode the item code
	* @param method the method
	* @return the number of matching push dict items
	*/
	public static int countByF_collectionCode_itemCode_Method(long groupId,
		java.lang.String collectionCode, java.lang.String itemCode,
		java.lang.String method) {
		return getPersistence()
				   .countByF_collectionCode_itemCode_Method(groupId,
			collectionCode, itemCode, method);
	}

	/**
	* Returns all the push dict items where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @return the matching push dict items
	*/
	public static List<PushDictItem> findByF_groupId_serverNo(long groupId,
		java.lang.String serverNo) {
		return getPersistence().findByF_groupId_serverNo(groupId, serverNo);
	}

	/**
	* Returns a range of all the push dict items where groupId = &#63; and serverNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param start the lower bound of the range of push dict items
	* @param end the upper bound of the range of push dict items (not inclusive)
	* @return the range of matching push dict items
	*/
	public static List<PushDictItem> findByF_groupId_serverNo(long groupId,
		java.lang.String serverNo, int start, int end) {
		return getPersistence()
				   .findByF_groupId_serverNo(groupId, serverNo, start, end);
	}

	/**
	* Returns an ordered range of all the push dict items where groupId = &#63; and serverNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param start the lower bound of the range of push dict items
	* @param end the upper bound of the range of push dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching push dict items
	*/
	public static List<PushDictItem> findByF_groupId_serverNo(long groupId,
		java.lang.String serverNo, int start, int end,
		OrderByComparator<PushDictItem> orderByComparator) {
		return getPersistence()
				   .findByF_groupId_serverNo(groupId, serverNo, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the push dict items where groupId = &#63; and serverNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param start the lower bound of the range of push dict items
	* @param end the upper bound of the range of push dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching push dict items
	*/
	public static List<PushDictItem> findByF_groupId_serverNo(long groupId,
		java.lang.String serverNo, int start, int end,
		OrderByComparator<PushDictItem> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_groupId_serverNo(groupId, serverNo, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first push dict item in the ordered set where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching push dict item
	* @throws NoSuchPushDictItemException if a matching push dict item could not be found
	*/
	public static PushDictItem findByF_groupId_serverNo_First(long groupId,
		java.lang.String serverNo,
		OrderByComparator<PushDictItem> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchPushDictItemException {
		return getPersistence()
				   .findByF_groupId_serverNo_First(groupId, serverNo,
			orderByComparator);
	}

	/**
	* Returns the first push dict item in the ordered set where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching push dict item, or <code>null</code> if a matching push dict item could not be found
	*/
	public static PushDictItem fetchByF_groupId_serverNo_First(long groupId,
		java.lang.String serverNo,
		OrderByComparator<PushDictItem> orderByComparator) {
		return getPersistence()
				   .fetchByF_groupId_serverNo_First(groupId, serverNo,
			orderByComparator);
	}

	/**
	* Returns the last push dict item in the ordered set where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching push dict item
	* @throws NoSuchPushDictItemException if a matching push dict item could not be found
	*/
	public static PushDictItem findByF_groupId_serverNo_Last(long groupId,
		java.lang.String serverNo,
		OrderByComparator<PushDictItem> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchPushDictItemException {
		return getPersistence()
				   .findByF_groupId_serverNo_Last(groupId, serverNo,
			orderByComparator);
	}

	/**
	* Returns the last push dict item in the ordered set where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching push dict item, or <code>null</code> if a matching push dict item could not be found
	*/
	public static PushDictItem fetchByF_groupId_serverNo_Last(long groupId,
		java.lang.String serverNo,
		OrderByComparator<PushDictItem> orderByComparator) {
		return getPersistence()
				   .fetchByF_groupId_serverNo_Last(groupId, serverNo,
			orderByComparator);
	}

	/**
	* Returns the push dict items before and after the current push dict item in the ordered set where groupId = &#63; and serverNo = &#63;.
	*
	* @param pushDictItemId the primary key of the current push dict item
	* @param groupId the group ID
	* @param serverNo the server no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next push dict item
	* @throws NoSuchPushDictItemException if a push dict item with the primary key could not be found
	*/
	public static PushDictItem[] findByF_groupId_serverNo_PrevAndNext(
		long pushDictItemId, long groupId, java.lang.String serverNo,
		OrderByComparator<PushDictItem> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchPushDictItemException {
		return getPersistence()
				   .findByF_groupId_serverNo_PrevAndNext(pushDictItemId,
			groupId, serverNo, orderByComparator);
	}

	/**
	* Removes all the push dict items where groupId = &#63; and serverNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	*/
	public static void removeByF_groupId_serverNo(long groupId,
		java.lang.String serverNo) {
		getPersistence().removeByF_groupId_serverNo(groupId, serverNo);
	}

	/**
	* Returns the number of push dict items where groupId = &#63; and serverNo = &#63;.
	*
	* @param groupId the group ID
	* @param serverNo the server no
	* @return the number of matching push dict items
	*/
	public static int countByF_groupId_serverNo(long groupId,
		java.lang.String serverNo) {
		return getPersistence().countByF_groupId_serverNo(groupId, serverNo);
	}

	/**
	* Caches the push dict item in the entity cache if it is enabled.
	*
	* @param pushDictItem the push dict item
	*/
	public static void cacheResult(PushDictItem pushDictItem) {
		getPersistence().cacheResult(pushDictItem);
	}

	/**
	* Caches the push dict items in the entity cache if it is enabled.
	*
	* @param pushDictItems the push dict items
	*/
	public static void cacheResult(List<PushDictItem> pushDictItems) {
		getPersistence().cacheResult(pushDictItems);
	}

	/**
	* Creates a new push dict item with the primary key. Does not add the push dict item to the database.
	*
	* @param pushDictItemId the primary key for the new push dict item
	* @return the new push dict item
	*/
	public static PushDictItem create(long pushDictItemId) {
		return getPersistence().create(pushDictItemId);
	}

	/**
	* Removes the push dict item with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param pushDictItemId the primary key of the push dict item
	* @return the push dict item that was removed
	* @throws NoSuchPushDictItemException if a push dict item with the primary key could not be found
	*/
	public static PushDictItem remove(long pushDictItemId)
		throws org.opencps.synchronization.exception.NoSuchPushDictItemException {
		return getPersistence().remove(pushDictItemId);
	}

	public static PushDictItem updateImpl(PushDictItem pushDictItem) {
		return getPersistence().updateImpl(pushDictItem);
	}

	/**
	* Returns the push dict item with the primary key or throws a {@link NoSuchPushDictItemException} if it could not be found.
	*
	* @param pushDictItemId the primary key of the push dict item
	* @return the push dict item
	* @throws NoSuchPushDictItemException if a push dict item with the primary key could not be found
	*/
	public static PushDictItem findByPrimaryKey(long pushDictItemId)
		throws org.opencps.synchronization.exception.NoSuchPushDictItemException {
		return getPersistence().findByPrimaryKey(pushDictItemId);
	}

	/**
	* Returns the push dict item with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param pushDictItemId the primary key of the push dict item
	* @return the push dict item, or <code>null</code> if a push dict item with the primary key could not be found
	*/
	public static PushDictItem fetchByPrimaryKey(long pushDictItemId) {
		return getPersistence().fetchByPrimaryKey(pushDictItemId);
	}

	public static java.util.Map<java.io.Serializable, PushDictItem> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the push dict items.
	*
	* @return the push dict items
	*/
	public static List<PushDictItem> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the push dict items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of push dict items
	* @param end the upper bound of the range of push dict items (not inclusive)
	* @return the range of push dict items
	*/
	public static List<PushDictItem> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the push dict items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of push dict items
	* @param end the upper bound of the range of push dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of push dict items
	*/
	public static List<PushDictItem> findAll(int start, int end,
		OrderByComparator<PushDictItem> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the push dict items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushDictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of push dict items
	* @param end the upper bound of the range of push dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of push dict items
	*/
	public static List<PushDictItem> findAll(int start, int end,
		OrderByComparator<PushDictItem> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the push dict items from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of push dict items.
	*
	* @return the number of push dict items
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static PushDictItemPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PushDictItemPersistence, PushDictItemPersistence> _serviceTracker =
		ServiceTrackerFactory.open(PushDictItemPersistence.class);
}