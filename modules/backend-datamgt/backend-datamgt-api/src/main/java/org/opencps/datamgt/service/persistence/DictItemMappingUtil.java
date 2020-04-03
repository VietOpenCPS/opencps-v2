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

package org.opencps.datamgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.datamgt.model.DictItemMapping;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the dict item mapping service. This utility wraps {@link org.opencps.datamgt.service.persistence.impl.DictItemMappingPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see DictItemMappingPersistence
 * @see org.opencps.datamgt.service.persistence.impl.DictItemMappingPersistenceImpl
 * @generated
 */
@ProviderType
public class DictItemMappingUtil {
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
	public static void clearCache(DictItemMapping dictItemMapping) {
		getPersistence().clearCache(dictItemMapping);
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
	public static List<DictItemMapping> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DictItemMapping> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DictItemMapping> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<DictItemMapping> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static DictItemMapping update(DictItemMapping dictItemMapping) {
		return getPersistence().update(dictItemMapping);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static DictItemMapping update(DictItemMapping dictItemMapping,
		ServiceContext serviceContext) {
		return getPersistence().update(dictItemMapping, serviceContext);
	}

	/**
	* Returns the dict item mapping where groupId = &#63; and itemCode = &#63; and collectionId = &#63; or throws a {@link NoSuchDictItemMappingException} if it could not be found.
	*
	* @param groupId the group ID
	* @param itemCode the item code
	* @param collectionId the collection ID
	* @return the matching dict item mapping
	* @throws NoSuchDictItemMappingException if a matching dict item mapping could not be found
	*/
	public static DictItemMapping findByF_GID_IC_CID(long groupId,
		String itemCode, long collectionId)
		throws org.opencps.datamgt.exception.NoSuchDictItemMappingException {
		return getPersistence()
				   .findByF_GID_IC_CID(groupId, itemCode, collectionId);
	}

	/**
	* Returns the dict item mapping where groupId = &#63; and itemCode = &#63; and collectionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param itemCode the item code
	* @param collectionId the collection ID
	* @return the matching dict item mapping, or <code>null</code> if a matching dict item mapping could not be found
	*/
	public static DictItemMapping fetchByF_GID_IC_CID(long groupId,
		String itemCode, long collectionId) {
		return getPersistence()
				   .fetchByF_GID_IC_CID(groupId, itemCode, collectionId);
	}

	/**
	* Returns the dict item mapping where groupId = &#63; and itemCode = &#63; and collectionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param itemCode the item code
	* @param collectionId the collection ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dict item mapping, or <code>null</code> if a matching dict item mapping could not be found
	*/
	public static DictItemMapping fetchByF_GID_IC_CID(long groupId,
		String itemCode, long collectionId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_GID_IC_CID(groupId, itemCode, collectionId,
			retrieveFromCache);
	}

	/**
	* Removes the dict item mapping where groupId = &#63; and itemCode = &#63; and collectionId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param itemCode the item code
	* @param collectionId the collection ID
	* @return the dict item mapping that was removed
	*/
	public static DictItemMapping removeByF_GID_IC_CID(long groupId,
		String itemCode, long collectionId)
		throws org.opencps.datamgt.exception.NoSuchDictItemMappingException {
		return getPersistence()
				   .removeByF_GID_IC_CID(groupId, itemCode, collectionId);
	}

	/**
	* Returns the number of dict item mappings where groupId = &#63; and itemCode = &#63; and collectionId = &#63;.
	*
	* @param groupId the group ID
	* @param itemCode the item code
	* @param collectionId the collection ID
	* @return the number of matching dict item mappings
	*/
	public static int countByF_GID_IC_CID(long groupId, String itemCode,
		long collectionId) {
		return getPersistence()
				   .countByF_GID_IC_CID(groupId, itemCode, collectionId);
	}

	/**
	* Returns the dict item mapping where groupId = &#63; and itemCodeDVCQG = &#63; and collectionId = &#63; or throws a {@link NoSuchDictItemMappingException} if it could not be found.
	*
	* @param groupId the group ID
	* @param itemCodeDVCQG the item code dvcqg
	* @param collectionId the collection ID
	* @return the matching dict item mapping
	* @throws NoSuchDictItemMappingException if a matching dict item mapping could not be found
	*/
	public static DictItemMapping findByF_GID_ICDVCQG_CID(long groupId,
		String itemCodeDVCQG, long collectionId)
		throws org.opencps.datamgt.exception.NoSuchDictItemMappingException {
		return getPersistence()
				   .findByF_GID_ICDVCQG_CID(groupId, itemCodeDVCQG, collectionId);
	}

	/**
	* Returns the dict item mapping where groupId = &#63; and itemCodeDVCQG = &#63; and collectionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param itemCodeDVCQG the item code dvcqg
	* @param collectionId the collection ID
	* @return the matching dict item mapping, or <code>null</code> if a matching dict item mapping could not be found
	*/
	public static DictItemMapping fetchByF_GID_ICDVCQG_CID(long groupId,
		String itemCodeDVCQG, long collectionId) {
		return getPersistence()
				   .fetchByF_GID_ICDVCQG_CID(groupId, itemCodeDVCQG,
			collectionId);
	}

	/**
	* Returns the dict item mapping where groupId = &#63; and itemCodeDVCQG = &#63; and collectionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param itemCodeDVCQG the item code dvcqg
	* @param collectionId the collection ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dict item mapping, or <code>null</code> if a matching dict item mapping could not be found
	*/
	public static DictItemMapping fetchByF_GID_ICDVCQG_CID(long groupId,
		String itemCodeDVCQG, long collectionId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_GID_ICDVCQG_CID(groupId, itemCodeDVCQG,
			collectionId, retrieveFromCache);
	}

	/**
	* Removes the dict item mapping where groupId = &#63; and itemCodeDVCQG = &#63; and collectionId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param itemCodeDVCQG the item code dvcqg
	* @param collectionId the collection ID
	* @return the dict item mapping that was removed
	*/
	public static DictItemMapping removeByF_GID_ICDVCQG_CID(long groupId,
		String itemCodeDVCQG, long collectionId)
		throws org.opencps.datamgt.exception.NoSuchDictItemMappingException {
		return getPersistence()
				   .removeByF_GID_ICDVCQG_CID(groupId, itemCodeDVCQG,
			collectionId);
	}

	/**
	* Returns the number of dict item mappings where groupId = &#63; and itemCodeDVCQG = &#63; and collectionId = &#63;.
	*
	* @param groupId the group ID
	* @param itemCodeDVCQG the item code dvcqg
	* @param collectionId the collection ID
	* @return the number of matching dict item mappings
	*/
	public static int countByF_GID_ICDVCQG_CID(long groupId,
		String itemCodeDVCQG, long collectionId) {
		return getPersistence()
				   .countByF_GID_ICDVCQG_CID(groupId, itemCodeDVCQG,
			collectionId);
	}

	/**
	* Returns all the dict item mappings where groupId = &#63; and collectionId = &#63;.
	*
	* @param groupId the group ID
	* @param collectionId the collection ID
	* @return the matching dict item mappings
	*/
	public static List<DictItemMapping> findByF_GID_CID(long groupId,
		long collectionId) {
		return getPersistence().findByF_GID_CID(groupId, collectionId);
	}

	/**
	* Returns a range of all the dict item mappings where groupId = &#63; and collectionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param collectionId the collection ID
	* @param start the lower bound of the range of dict item mappings
	* @param end the upper bound of the range of dict item mappings (not inclusive)
	* @return the range of matching dict item mappings
	*/
	public static List<DictItemMapping> findByF_GID_CID(long groupId,
		long collectionId, int start, int end) {
		return getPersistence()
				   .findByF_GID_CID(groupId, collectionId, start, end);
	}

	/**
	* Returns an ordered range of all the dict item mappings where groupId = &#63; and collectionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param collectionId the collection ID
	* @param start the lower bound of the range of dict item mappings
	* @param end the upper bound of the range of dict item mappings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict item mappings
	*/
	public static List<DictItemMapping> findByF_GID_CID(long groupId,
		long collectionId, int start, int end,
		OrderByComparator<DictItemMapping> orderByComparator) {
		return getPersistence()
				   .findByF_GID_CID(groupId, collectionId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dict item mappings where groupId = &#63; and collectionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param collectionId the collection ID
	* @param start the lower bound of the range of dict item mappings
	* @param end the upper bound of the range of dict item mappings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict item mappings
	*/
	public static List<DictItemMapping> findByF_GID_CID(long groupId,
		long collectionId, int start, int end,
		OrderByComparator<DictItemMapping> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_GID_CID(groupId, collectionId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dict item mapping in the ordered set where groupId = &#63; and collectionId = &#63;.
	*
	* @param groupId the group ID
	* @param collectionId the collection ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item mapping
	* @throws NoSuchDictItemMappingException if a matching dict item mapping could not be found
	*/
	public static DictItemMapping findByF_GID_CID_First(long groupId,
		long collectionId, OrderByComparator<DictItemMapping> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchDictItemMappingException {
		return getPersistence()
				   .findByF_GID_CID_First(groupId, collectionId,
			orderByComparator);
	}

	/**
	* Returns the first dict item mapping in the ordered set where groupId = &#63; and collectionId = &#63;.
	*
	* @param groupId the group ID
	* @param collectionId the collection ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item mapping, or <code>null</code> if a matching dict item mapping could not be found
	*/
	public static DictItemMapping fetchByF_GID_CID_First(long groupId,
		long collectionId, OrderByComparator<DictItemMapping> orderByComparator) {
		return getPersistence()
				   .fetchByF_GID_CID_First(groupId, collectionId,
			orderByComparator);
	}

	/**
	* Returns the last dict item mapping in the ordered set where groupId = &#63; and collectionId = &#63;.
	*
	* @param groupId the group ID
	* @param collectionId the collection ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item mapping
	* @throws NoSuchDictItemMappingException if a matching dict item mapping could not be found
	*/
	public static DictItemMapping findByF_GID_CID_Last(long groupId,
		long collectionId, OrderByComparator<DictItemMapping> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchDictItemMappingException {
		return getPersistence()
				   .findByF_GID_CID_Last(groupId, collectionId,
			orderByComparator);
	}

	/**
	* Returns the last dict item mapping in the ordered set where groupId = &#63; and collectionId = &#63;.
	*
	* @param groupId the group ID
	* @param collectionId the collection ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item mapping, or <code>null</code> if a matching dict item mapping could not be found
	*/
	public static DictItemMapping fetchByF_GID_CID_Last(long groupId,
		long collectionId, OrderByComparator<DictItemMapping> orderByComparator) {
		return getPersistence()
				   .fetchByF_GID_CID_Last(groupId, collectionId,
			orderByComparator);
	}

	/**
	* Returns the dict item mappings before and after the current dict item mapping in the ordered set where groupId = &#63; and collectionId = &#63;.
	*
	* @param mappingId the primary key of the current dict item mapping
	* @param groupId the group ID
	* @param collectionId the collection ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict item mapping
	* @throws NoSuchDictItemMappingException if a dict item mapping with the primary key could not be found
	*/
	public static DictItemMapping[] findByF_GID_CID_PrevAndNext(
		long mappingId, long groupId, long collectionId,
		OrderByComparator<DictItemMapping> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchDictItemMappingException {
		return getPersistence()
				   .findByF_GID_CID_PrevAndNext(mappingId, groupId,
			collectionId, orderByComparator);
	}

	/**
	* Removes all the dict item mappings where groupId = &#63; and collectionId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param collectionId the collection ID
	*/
	public static void removeByF_GID_CID(long groupId, long collectionId) {
		getPersistence().removeByF_GID_CID(groupId, collectionId);
	}

	/**
	* Returns the number of dict item mappings where groupId = &#63; and collectionId = &#63;.
	*
	* @param groupId the group ID
	* @param collectionId the collection ID
	* @return the number of matching dict item mappings
	*/
	public static int countByF_GID_CID(long groupId, long collectionId) {
		return getPersistence().countByF_GID_CID(groupId, collectionId);
	}

	/**
	* Caches the dict item mapping in the entity cache if it is enabled.
	*
	* @param dictItemMapping the dict item mapping
	*/
	public static void cacheResult(DictItemMapping dictItemMapping) {
		getPersistence().cacheResult(dictItemMapping);
	}

	/**
	* Caches the dict item mappings in the entity cache if it is enabled.
	*
	* @param dictItemMappings the dict item mappings
	*/
	public static void cacheResult(List<DictItemMapping> dictItemMappings) {
		getPersistence().cacheResult(dictItemMappings);
	}

	/**
	* Creates a new dict item mapping with the primary key. Does not add the dict item mapping to the database.
	*
	* @param mappingId the primary key for the new dict item mapping
	* @return the new dict item mapping
	*/
	public static DictItemMapping create(long mappingId) {
		return getPersistence().create(mappingId);
	}

	/**
	* Removes the dict item mapping with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param mappingId the primary key of the dict item mapping
	* @return the dict item mapping that was removed
	* @throws NoSuchDictItemMappingException if a dict item mapping with the primary key could not be found
	*/
	public static DictItemMapping remove(long mappingId)
		throws org.opencps.datamgt.exception.NoSuchDictItemMappingException {
		return getPersistence().remove(mappingId);
	}

	public static DictItemMapping updateImpl(DictItemMapping dictItemMapping) {
		return getPersistence().updateImpl(dictItemMapping);
	}

	/**
	* Returns the dict item mapping with the primary key or throws a {@link NoSuchDictItemMappingException} if it could not be found.
	*
	* @param mappingId the primary key of the dict item mapping
	* @return the dict item mapping
	* @throws NoSuchDictItemMappingException if a dict item mapping with the primary key could not be found
	*/
	public static DictItemMapping findByPrimaryKey(long mappingId)
		throws org.opencps.datamgt.exception.NoSuchDictItemMappingException {
		return getPersistence().findByPrimaryKey(mappingId);
	}

	/**
	* Returns the dict item mapping with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param mappingId the primary key of the dict item mapping
	* @return the dict item mapping, or <code>null</code> if a dict item mapping with the primary key could not be found
	*/
	public static DictItemMapping fetchByPrimaryKey(long mappingId) {
		return getPersistence().fetchByPrimaryKey(mappingId);
	}

	public static java.util.Map<java.io.Serializable, DictItemMapping> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the dict item mappings.
	*
	* @return the dict item mappings
	*/
	public static List<DictItemMapping> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the dict item mappings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict item mappings
	* @param end the upper bound of the range of dict item mappings (not inclusive)
	* @return the range of dict item mappings
	*/
	public static List<DictItemMapping> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the dict item mappings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict item mappings
	* @param end the upper bound of the range of dict item mappings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dict item mappings
	*/
	public static List<DictItemMapping> findAll(int start, int end,
		OrderByComparator<DictItemMapping> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dict item mappings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict item mappings
	* @param end the upper bound of the range of dict item mappings (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dict item mappings
	*/
	public static List<DictItemMapping> findAll(int start, int end,
		OrderByComparator<DictItemMapping> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the dict item mappings from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of dict item mappings.
	*
	* @return the number of dict item mappings
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static DictItemMappingPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DictItemMappingPersistence, DictItemMappingPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DictItemMappingPersistence.class);

		ServiceTracker<DictItemMappingPersistence, DictItemMappingPersistence> serviceTracker =
			new ServiceTracker<DictItemMappingPersistence, DictItemMappingPersistence>(bundle.getBundleContext(),
				DictItemMappingPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}