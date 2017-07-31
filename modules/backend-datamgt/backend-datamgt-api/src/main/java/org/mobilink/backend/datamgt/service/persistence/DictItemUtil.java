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

package org.mobilink.backend.datamgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.mobilink.backend.datamgt.model.DictItem;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the dict item service. This utility wraps {@link org.mobilink.backend.datamgt.service.persistence.impl.DictItemPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Binhth
 * @see DictItemPersistence
 * @see org.mobilink.backend.datamgt.service.persistence.impl.DictItemPersistenceImpl
 * @generated
 */
@ProviderType
public class DictItemUtil {
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
	public static void clearCache(DictItem dictItem) {
		getPersistence().clearCache(dictItem);
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
	public static List<DictItem> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DictItem> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DictItem> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<DictItem> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static DictItem update(DictItem dictItem) {
		return getPersistence().update(dictItem);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static DictItem update(DictItem dictItem,
		ServiceContext serviceContext) {
		return getPersistence().update(dictItem, serviceContext);
	}

	/**
	* Returns all the dict items where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching dict items
	*/
	public static List<DictItem> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the dict items where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @return the range of matching dict items
	*/
	public static List<DictItem> findByUuid(java.lang.String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the dict items where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict items
	*/
	public static List<DictItem> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<DictItem> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dict items where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict items
	*/
	public static List<DictItem> findByUuid(java.lang.String uuid, int start,
		int end, OrderByComparator<DictItem> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first dict item in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item
	* @throws NoSuchDictItemException if a matching dict item could not be found
	*/
	public static DictItem findByUuid_First(java.lang.String uuid,
		OrderByComparator<DictItem> orderByComparator)
		throws org.mobilink.backend.datamgt.exception.NoSuchDictItemException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first dict item in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item, or <code>null</code> if a matching dict item could not be found
	*/
	public static DictItem fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<DictItem> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last dict item in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item
	* @throws NoSuchDictItemException if a matching dict item could not be found
	*/
	public static DictItem findByUuid_Last(java.lang.String uuid,
		OrderByComparator<DictItem> orderByComparator)
		throws org.mobilink.backend.datamgt.exception.NoSuchDictItemException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last dict item in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item, or <code>null</code> if a matching dict item could not be found
	*/
	public static DictItem fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<DictItem> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the dict items before and after the current dict item in the ordered set where uuid = &#63;.
	*
	* @param dictItemId the primary key of the current dict item
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict item
	* @throws NoSuchDictItemException if a dict item with the primary key could not be found
	*/
	public static DictItem[] findByUuid_PrevAndNext(long dictItemId,
		java.lang.String uuid, OrderByComparator<DictItem> orderByComparator)
		throws org.mobilink.backend.datamgt.exception.NoSuchDictItemException {
		return getPersistence()
				   .findByUuid_PrevAndNext(dictItemId, uuid, orderByComparator);
	}

	/**
	* Removes all the dict items where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of dict items where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching dict items
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the dict item where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDictItemException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dict item
	* @throws NoSuchDictItemException if a matching dict item could not be found
	*/
	public static DictItem findByUUID_G(java.lang.String uuid, long groupId)
		throws org.mobilink.backend.datamgt.exception.NoSuchDictItemException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the dict item where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dict item, or <code>null</code> if a matching dict item could not be found
	*/
	public static DictItem fetchByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the dict item where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dict item, or <code>null</code> if a matching dict item could not be found
	*/
	public static DictItem fetchByUUID_G(java.lang.String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the dict item where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the dict item that was removed
	*/
	public static DictItem removeByUUID_G(java.lang.String uuid, long groupId)
		throws org.mobilink.backend.datamgt.exception.NoSuchDictItemException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of dict items where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching dict items
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the dict items where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching dict items
	*/
	public static List<DictItem> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the dict items where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @return the range of matching dict items
	*/
	public static List<DictItem> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the dict items where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict items
	*/
	public static List<DictItem> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<DictItem> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dict items where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict items
	*/
	public static List<DictItem> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<DictItem> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dict item in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item
	* @throws NoSuchDictItemException if a matching dict item could not be found
	*/
	public static DictItem findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<DictItem> orderByComparator)
		throws org.mobilink.backend.datamgt.exception.NoSuchDictItemException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first dict item in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item, or <code>null</code> if a matching dict item could not be found
	*/
	public static DictItem fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<DictItem> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last dict item in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item
	* @throws NoSuchDictItemException if a matching dict item could not be found
	*/
	public static DictItem findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<DictItem> orderByComparator)
		throws org.mobilink.backend.datamgt.exception.NoSuchDictItemException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last dict item in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item, or <code>null</code> if a matching dict item could not be found
	*/
	public static DictItem fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<DictItem> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the dict items before and after the current dict item in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param dictItemId the primary key of the current dict item
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict item
	* @throws NoSuchDictItemException if a dict item with the primary key could not be found
	*/
	public static DictItem[] findByUuid_C_PrevAndNext(long dictItemId,
		java.lang.String uuid, long companyId,
		OrderByComparator<DictItem> orderByComparator)
		throws org.mobilink.backend.datamgt.exception.NoSuchDictItemException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(dictItemId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the dict items where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of dict items where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching dict items
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the dict items where dictCollectionId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @return the matching dict items
	*/
	public static List<DictItem> findByF_dictCollectionId(long dictCollectionId) {
		return getPersistence().findByF_dictCollectionId(dictCollectionId);
	}

	/**
	* Returns a range of all the dict items where dictCollectionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dictCollectionId the dict collection ID
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @return the range of matching dict items
	*/
	public static List<DictItem> findByF_dictCollectionId(
		long dictCollectionId, int start, int end) {
		return getPersistence()
				   .findByF_dictCollectionId(dictCollectionId, start, end);
	}

	/**
	* Returns an ordered range of all the dict items where dictCollectionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dictCollectionId the dict collection ID
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict items
	*/
	public static List<DictItem> findByF_dictCollectionId(
		long dictCollectionId, int start, int end,
		OrderByComparator<DictItem> orderByComparator) {
		return getPersistence()
				   .findByF_dictCollectionId(dictCollectionId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dict items where dictCollectionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dictCollectionId the dict collection ID
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict items
	*/
	public static List<DictItem> findByF_dictCollectionId(
		long dictCollectionId, int start, int end,
		OrderByComparator<DictItem> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_dictCollectionId(dictCollectionId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dict item in the ordered set where dictCollectionId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item
	* @throws NoSuchDictItemException if a matching dict item could not be found
	*/
	public static DictItem findByF_dictCollectionId_First(
		long dictCollectionId, OrderByComparator<DictItem> orderByComparator)
		throws org.mobilink.backend.datamgt.exception.NoSuchDictItemException {
		return getPersistence()
				   .findByF_dictCollectionId_First(dictCollectionId,
			orderByComparator);
	}

	/**
	* Returns the first dict item in the ordered set where dictCollectionId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item, or <code>null</code> if a matching dict item could not be found
	*/
	public static DictItem fetchByF_dictCollectionId_First(
		long dictCollectionId, OrderByComparator<DictItem> orderByComparator) {
		return getPersistence()
				   .fetchByF_dictCollectionId_First(dictCollectionId,
			orderByComparator);
	}

	/**
	* Returns the last dict item in the ordered set where dictCollectionId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item
	* @throws NoSuchDictItemException if a matching dict item could not be found
	*/
	public static DictItem findByF_dictCollectionId_Last(
		long dictCollectionId, OrderByComparator<DictItem> orderByComparator)
		throws org.mobilink.backend.datamgt.exception.NoSuchDictItemException {
		return getPersistence()
				   .findByF_dictCollectionId_Last(dictCollectionId,
			orderByComparator);
	}

	/**
	* Returns the last dict item in the ordered set where dictCollectionId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item, or <code>null</code> if a matching dict item could not be found
	*/
	public static DictItem fetchByF_dictCollectionId_Last(
		long dictCollectionId, OrderByComparator<DictItem> orderByComparator) {
		return getPersistence()
				   .fetchByF_dictCollectionId_Last(dictCollectionId,
			orderByComparator);
	}

	/**
	* Returns the dict items before and after the current dict item in the ordered set where dictCollectionId = &#63;.
	*
	* @param dictItemId the primary key of the current dict item
	* @param dictCollectionId the dict collection ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict item
	* @throws NoSuchDictItemException if a dict item with the primary key could not be found
	*/
	public static DictItem[] findByF_dictCollectionId_PrevAndNext(
		long dictItemId, long dictCollectionId,
		OrderByComparator<DictItem> orderByComparator)
		throws org.mobilink.backend.datamgt.exception.NoSuchDictItemException {
		return getPersistence()
				   .findByF_dictCollectionId_PrevAndNext(dictItemId,
			dictCollectionId, orderByComparator);
	}

	/**
	* Removes all the dict items where dictCollectionId = &#63; from the database.
	*
	* @param dictCollectionId the dict collection ID
	*/
	public static void removeByF_dictCollectionId(long dictCollectionId) {
		getPersistence().removeByF_dictCollectionId(dictCollectionId);
	}

	/**
	* Returns the number of dict items where dictCollectionId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @return the number of matching dict items
	*/
	public static int countByF_dictCollectionId(long dictCollectionId) {
		return getPersistence().countByF_dictCollectionId(dictCollectionId);
	}

	/**
	* Returns the dict item where itemCode = &#63; and groupId = &#63; or throws a {@link NoSuchDictItemException} if it could not be found.
	*
	* @param itemCode the item code
	* @param groupId the group ID
	* @return the matching dict item
	* @throws NoSuchDictItemException if a matching dict item could not be found
	*/
	public static DictItem findByF_dictItemCode(java.lang.String itemCode,
		long groupId)
		throws org.mobilink.backend.datamgt.exception.NoSuchDictItemException {
		return getPersistence().findByF_dictItemCode(itemCode, groupId);
	}

	/**
	* Returns the dict item where itemCode = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param itemCode the item code
	* @param groupId the group ID
	* @return the matching dict item, or <code>null</code> if a matching dict item could not be found
	*/
	public static DictItem fetchByF_dictItemCode(java.lang.String itemCode,
		long groupId) {
		return getPersistence().fetchByF_dictItemCode(itemCode, groupId);
	}

	/**
	* Returns the dict item where itemCode = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param itemCode the item code
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dict item, or <code>null</code> if a matching dict item could not be found
	*/
	public static DictItem fetchByF_dictItemCode(java.lang.String itemCode,
		long groupId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_dictItemCode(itemCode, groupId, retrieveFromCache);
	}

	/**
	* Removes the dict item where itemCode = &#63; and groupId = &#63; from the database.
	*
	* @param itemCode the item code
	* @param groupId the group ID
	* @return the dict item that was removed
	*/
	public static DictItem removeByF_dictItemCode(java.lang.String itemCode,
		long groupId)
		throws org.mobilink.backend.datamgt.exception.NoSuchDictItemException {
		return getPersistence().removeByF_dictItemCode(itemCode, groupId);
	}

	/**
	* Returns the number of dict items where itemCode = &#63; and groupId = &#63;.
	*
	* @param itemCode the item code
	* @param groupId the group ID
	* @return the number of matching dict items
	*/
	public static int countByF_dictItemCode(java.lang.String itemCode,
		long groupId) {
		return getPersistence().countByF_dictItemCode(itemCode, groupId);
	}

	/**
	* Caches the dict item in the entity cache if it is enabled.
	*
	* @param dictItem the dict item
	*/
	public static void cacheResult(DictItem dictItem) {
		getPersistence().cacheResult(dictItem);
	}

	/**
	* Caches the dict items in the entity cache if it is enabled.
	*
	* @param dictItems the dict items
	*/
	public static void cacheResult(List<DictItem> dictItems) {
		getPersistence().cacheResult(dictItems);
	}

	/**
	* Creates a new dict item with the primary key. Does not add the dict item to the database.
	*
	* @param dictItemId the primary key for the new dict item
	* @return the new dict item
	*/
	public static DictItem create(long dictItemId) {
		return getPersistence().create(dictItemId);
	}

	/**
	* Removes the dict item with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dictItemId the primary key of the dict item
	* @return the dict item that was removed
	* @throws NoSuchDictItemException if a dict item with the primary key could not be found
	*/
	public static DictItem remove(long dictItemId)
		throws org.mobilink.backend.datamgt.exception.NoSuchDictItemException {
		return getPersistence().remove(dictItemId);
	}

	public static DictItem updateImpl(DictItem dictItem) {
		return getPersistence().updateImpl(dictItem);
	}

	/**
	* Returns the dict item with the primary key or throws a {@link NoSuchDictItemException} if it could not be found.
	*
	* @param dictItemId the primary key of the dict item
	* @return the dict item
	* @throws NoSuchDictItemException if a dict item with the primary key could not be found
	*/
	public static DictItem findByPrimaryKey(long dictItemId)
		throws org.mobilink.backend.datamgt.exception.NoSuchDictItemException {
		return getPersistence().findByPrimaryKey(dictItemId);
	}

	/**
	* Returns the dict item with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dictItemId the primary key of the dict item
	* @return the dict item, or <code>null</code> if a dict item with the primary key could not be found
	*/
	public static DictItem fetchByPrimaryKey(long dictItemId) {
		return getPersistence().fetchByPrimaryKey(dictItemId);
	}

	public static java.util.Map<java.io.Serializable, DictItem> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the dict items.
	*
	* @return the dict items
	*/
	public static List<DictItem> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the dict items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @return the range of dict items
	*/
	public static List<DictItem> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the dict items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dict items
	*/
	public static List<DictItem> findAll(int start, int end,
		OrderByComparator<DictItem> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dict items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dict items
	*/
	public static List<DictItem> findAll(int start, int end,
		OrderByComparator<DictItem> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the dict items from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of dict items.
	*
	* @return the number of dict items
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static DictItemPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DictItemPersistence, DictItemPersistence> _serviceTracker =
		ServiceTrackerFactory.open(DictItemPersistence.class);
}