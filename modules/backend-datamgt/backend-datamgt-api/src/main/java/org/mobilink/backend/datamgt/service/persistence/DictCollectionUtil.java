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

import org.mobilink.backend.datamgt.model.DictCollection;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the dict collection service. This utility wraps {@link org.mobilink.backend.datamgt.service.persistence.impl.DictCollectionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Binhth
 * @see DictCollectionPersistence
 * @see org.mobilink.backend.datamgt.service.persistence.impl.DictCollectionPersistenceImpl
 * @generated
 */
@ProviderType
public class DictCollectionUtil {
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
	public static void clearCache(DictCollection dictCollection) {
		getPersistence().clearCache(dictCollection);
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
	public static List<DictCollection> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DictCollection> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DictCollection> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<DictCollection> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static DictCollection update(DictCollection dictCollection) {
		return getPersistence().update(dictCollection);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static DictCollection update(DictCollection dictCollection,
		ServiceContext serviceContext) {
		return getPersistence().update(dictCollection, serviceContext);
	}

	/**
	* Returns all the dict collections where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching dict collections
	*/
	public static List<DictCollection> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the dict collections where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dict collections
	* @param end the upper bound of the range of dict collections (not inclusive)
	* @return the range of matching dict collections
	*/
	public static List<DictCollection> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the dict collections where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dict collections
	* @param end the upper bound of the range of dict collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict collections
	*/
	public static List<DictCollection> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<DictCollection> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dict collections where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dict collections
	* @param end the upper bound of the range of dict collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict collections
	*/
	public static List<DictCollection> findByUuid(java.lang.String uuid,
		int start, int end,
		OrderByComparator<DictCollection> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first dict collection in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict collection
	* @throws NoSuchDictCollectionException if a matching dict collection could not be found
	*/
	public static DictCollection findByUuid_First(java.lang.String uuid,
		OrderByComparator<DictCollection> orderByComparator)
		throws org.mobilink.backend.datamgt.exception.NoSuchDictCollectionException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first dict collection in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict collection, or <code>null</code> if a matching dict collection could not be found
	*/
	public static DictCollection fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<DictCollection> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last dict collection in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict collection
	* @throws NoSuchDictCollectionException if a matching dict collection could not be found
	*/
	public static DictCollection findByUuid_Last(java.lang.String uuid,
		OrderByComparator<DictCollection> orderByComparator)
		throws org.mobilink.backend.datamgt.exception.NoSuchDictCollectionException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last dict collection in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict collection, or <code>null</code> if a matching dict collection could not be found
	*/
	public static DictCollection fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<DictCollection> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the dict collections before and after the current dict collection in the ordered set where uuid = &#63;.
	*
	* @param dictCollectionId the primary key of the current dict collection
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict collection
	* @throws NoSuchDictCollectionException if a dict collection with the primary key could not be found
	*/
	public static DictCollection[] findByUuid_PrevAndNext(
		long dictCollectionId, java.lang.String uuid,
		OrderByComparator<DictCollection> orderByComparator)
		throws org.mobilink.backend.datamgt.exception.NoSuchDictCollectionException {
		return getPersistence()
				   .findByUuid_PrevAndNext(dictCollectionId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the dict collections where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of dict collections where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching dict collections
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the dict collection where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDictCollectionException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dict collection
	* @throws NoSuchDictCollectionException if a matching dict collection could not be found
	*/
	public static DictCollection findByUUID_G(java.lang.String uuid,
		long groupId)
		throws org.mobilink.backend.datamgt.exception.NoSuchDictCollectionException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the dict collection where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dict collection, or <code>null</code> if a matching dict collection could not be found
	*/
	public static DictCollection fetchByUUID_G(java.lang.String uuid,
		long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the dict collection where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dict collection, or <code>null</code> if a matching dict collection could not be found
	*/
	public static DictCollection fetchByUUID_G(java.lang.String uuid,
		long groupId, boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the dict collection where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the dict collection that was removed
	*/
	public static DictCollection removeByUUID_G(java.lang.String uuid,
		long groupId)
		throws org.mobilink.backend.datamgt.exception.NoSuchDictCollectionException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of dict collections where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching dict collections
	*/
	public static int countByUUID_G(java.lang.String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the dict collections where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching dict collections
	*/
	public static List<DictCollection> findByUuid_C(java.lang.String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the dict collections where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dict collections
	* @param end the upper bound of the range of dict collections (not inclusive)
	* @return the range of matching dict collections
	*/
	public static List<DictCollection> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the dict collections where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dict collections
	* @param end the upper bound of the range of dict collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict collections
	*/
	public static List<DictCollection> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<DictCollection> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dict collections where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dict collections
	* @param end the upper bound of the range of dict collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict collections
	*/
	public static List<DictCollection> findByUuid_C(java.lang.String uuid,
		long companyId, int start, int end,
		OrderByComparator<DictCollection> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dict collection in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict collection
	* @throws NoSuchDictCollectionException if a matching dict collection could not be found
	*/
	public static DictCollection findByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<DictCollection> orderByComparator)
		throws org.mobilink.backend.datamgt.exception.NoSuchDictCollectionException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first dict collection in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict collection, or <code>null</code> if a matching dict collection could not be found
	*/
	public static DictCollection fetchByUuid_C_First(java.lang.String uuid,
		long companyId, OrderByComparator<DictCollection> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last dict collection in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict collection
	* @throws NoSuchDictCollectionException if a matching dict collection could not be found
	*/
	public static DictCollection findByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<DictCollection> orderByComparator)
		throws org.mobilink.backend.datamgt.exception.NoSuchDictCollectionException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last dict collection in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict collection, or <code>null</code> if a matching dict collection could not be found
	*/
	public static DictCollection fetchByUuid_C_Last(java.lang.String uuid,
		long companyId, OrderByComparator<DictCollection> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the dict collections before and after the current dict collection in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param dictCollectionId the primary key of the current dict collection
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict collection
	* @throws NoSuchDictCollectionException if a dict collection with the primary key could not be found
	*/
	public static DictCollection[] findByUuid_C_PrevAndNext(
		long dictCollectionId, java.lang.String uuid, long companyId,
		OrderByComparator<DictCollection> orderByComparator)
		throws org.mobilink.backend.datamgt.exception.NoSuchDictCollectionException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(dictCollectionId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the dict collections where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(java.lang.String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of dict collections where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching dict collections
	*/
	public static int countByUuid_C(java.lang.String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the dict collection where collectionCode = &#63; and groupId = &#63; or throws a {@link NoSuchDictCollectionException} if it could not be found.
	*
	* @param collectionCode the collection code
	* @param groupId the group ID
	* @return the matching dict collection
	* @throws NoSuchDictCollectionException if a matching dict collection could not be found
	*/
	public static DictCollection findByF_dictCollectionCode(
		java.lang.String collectionCode, long groupId)
		throws org.mobilink.backend.datamgt.exception.NoSuchDictCollectionException {
		return getPersistence()
				   .findByF_dictCollectionCode(collectionCode, groupId);
	}

	/**
	* Returns the dict collection where collectionCode = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param collectionCode the collection code
	* @param groupId the group ID
	* @return the matching dict collection, or <code>null</code> if a matching dict collection could not be found
	*/
	public static DictCollection fetchByF_dictCollectionCode(
		java.lang.String collectionCode, long groupId) {
		return getPersistence()
				   .fetchByF_dictCollectionCode(collectionCode, groupId);
	}

	/**
	* Returns the dict collection where collectionCode = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param collectionCode the collection code
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dict collection, or <code>null</code> if a matching dict collection could not be found
	*/
	public static DictCollection fetchByF_dictCollectionCode(
		java.lang.String collectionCode, long groupId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_dictCollectionCode(collectionCode, groupId,
			retrieveFromCache);
	}

	/**
	* Removes the dict collection where collectionCode = &#63; and groupId = &#63; from the database.
	*
	* @param collectionCode the collection code
	* @param groupId the group ID
	* @return the dict collection that was removed
	*/
	public static DictCollection removeByF_dictCollectionCode(
		java.lang.String collectionCode, long groupId)
		throws org.mobilink.backend.datamgt.exception.NoSuchDictCollectionException {
		return getPersistence()
				   .removeByF_dictCollectionCode(collectionCode, groupId);
	}

	/**
	* Returns the number of dict collections where collectionCode = &#63; and groupId = &#63;.
	*
	* @param collectionCode the collection code
	* @param groupId the group ID
	* @return the number of matching dict collections
	*/
	public static int countByF_dictCollectionCode(
		java.lang.String collectionCode, long groupId) {
		return getPersistence()
				   .countByF_dictCollectionCode(collectionCode, groupId);
	}

	/**
	* Caches the dict collection in the entity cache if it is enabled.
	*
	* @param dictCollection the dict collection
	*/
	public static void cacheResult(DictCollection dictCollection) {
		getPersistence().cacheResult(dictCollection);
	}

	/**
	* Caches the dict collections in the entity cache if it is enabled.
	*
	* @param dictCollections the dict collections
	*/
	public static void cacheResult(List<DictCollection> dictCollections) {
		getPersistence().cacheResult(dictCollections);
	}

	/**
	* Creates a new dict collection with the primary key. Does not add the dict collection to the database.
	*
	* @param dictCollectionId the primary key for the new dict collection
	* @return the new dict collection
	*/
	public static DictCollection create(long dictCollectionId) {
		return getPersistence().create(dictCollectionId);
	}

	/**
	* Removes the dict collection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dictCollectionId the primary key of the dict collection
	* @return the dict collection that was removed
	* @throws NoSuchDictCollectionException if a dict collection with the primary key could not be found
	*/
	public static DictCollection remove(long dictCollectionId)
		throws org.mobilink.backend.datamgt.exception.NoSuchDictCollectionException {
		return getPersistence().remove(dictCollectionId);
	}

	public static DictCollection updateImpl(DictCollection dictCollection) {
		return getPersistence().updateImpl(dictCollection);
	}

	/**
	* Returns the dict collection with the primary key or throws a {@link NoSuchDictCollectionException} if it could not be found.
	*
	* @param dictCollectionId the primary key of the dict collection
	* @return the dict collection
	* @throws NoSuchDictCollectionException if a dict collection with the primary key could not be found
	*/
	public static DictCollection findByPrimaryKey(long dictCollectionId)
		throws org.mobilink.backend.datamgt.exception.NoSuchDictCollectionException {
		return getPersistence().findByPrimaryKey(dictCollectionId);
	}

	/**
	* Returns the dict collection with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dictCollectionId the primary key of the dict collection
	* @return the dict collection, or <code>null</code> if a dict collection with the primary key could not be found
	*/
	public static DictCollection fetchByPrimaryKey(long dictCollectionId) {
		return getPersistence().fetchByPrimaryKey(dictCollectionId);
	}

	public static java.util.Map<java.io.Serializable, DictCollection> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the dict collections.
	*
	* @return the dict collections
	*/
	public static List<DictCollection> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the dict collections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict collections
	* @param end the upper bound of the range of dict collections (not inclusive)
	* @return the range of dict collections
	*/
	public static List<DictCollection> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the dict collections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict collections
	* @param end the upper bound of the range of dict collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dict collections
	*/
	public static List<DictCollection> findAll(int start, int end,
		OrderByComparator<DictCollection> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dict collections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict collections
	* @param end the upper bound of the range of dict collections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dict collections
	*/
	public static List<DictCollection> findAll(int start, int end,
		OrderByComparator<DictCollection> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the dict collections from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of dict collections.
	*
	* @return the number of dict collections
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static DictCollectionPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DictCollectionPersistence, DictCollectionPersistence> _serviceTracker =
		ServiceTrackerFactory.open(DictCollectionPersistence.class);
}