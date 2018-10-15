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

import org.opencps.datamgt.model.DictItemGroup;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.Date;
import java.util.List;

/**
 * The persistence utility for the dict item group service. This utility wraps {@link org.opencps.datamgt.service.persistence.impl.DictItemGroupPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see DictItemGroupPersistence
 * @see org.opencps.datamgt.service.persistence.impl.DictItemGroupPersistenceImpl
 * @generated
 */
@ProviderType
public class DictItemGroupUtil {
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
	public static void clearCache(DictItemGroup dictItemGroup) {
		getPersistence().clearCache(dictItemGroup);
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
	public static List<DictItemGroup> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DictItemGroup> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DictItemGroup> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<DictItemGroup> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static DictItemGroup update(DictItemGroup dictItemGroup) {
		return getPersistence().update(dictItemGroup);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static DictItemGroup update(DictItemGroup dictItemGroup,
		ServiceContext serviceContext) {
		return getPersistence().update(dictItemGroup, serviceContext);
	}

	/**
	* Returns all the dict item groups where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching dict item groups
	*/
	public static List<DictItemGroup> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the dict item groups where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dict item groups
	* @param end the upper bound of the range of dict item groups (not inclusive)
	* @return the range of matching dict item groups
	*/
	public static List<DictItemGroup> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the dict item groups where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dict item groups
	* @param end the upper bound of the range of dict item groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict item groups
	*/
	public static List<DictItemGroup> findByUuid(String uuid, int start,
		int end, OrderByComparator<DictItemGroup> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dict item groups where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dict item groups
	* @param end the upper bound of the range of dict item groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict item groups
	*/
	public static List<DictItemGroup> findByUuid(String uuid, int start,
		int end, OrderByComparator<DictItemGroup> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first dict item group in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item group
	* @throws NoSuchDictItemGroupException if a matching dict item group could not be found
	*/
	public static DictItemGroup findByUuid_First(String uuid,
		OrderByComparator<DictItemGroup> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchDictItemGroupException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first dict item group in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item group, or <code>null</code> if a matching dict item group could not be found
	*/
	public static DictItemGroup fetchByUuid_First(String uuid,
		OrderByComparator<DictItemGroup> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last dict item group in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item group
	* @throws NoSuchDictItemGroupException if a matching dict item group could not be found
	*/
	public static DictItemGroup findByUuid_Last(String uuid,
		OrderByComparator<DictItemGroup> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchDictItemGroupException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last dict item group in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item group, or <code>null</code> if a matching dict item group could not be found
	*/
	public static DictItemGroup fetchByUuid_Last(String uuid,
		OrderByComparator<DictItemGroup> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the dict item groups before and after the current dict item group in the ordered set where uuid = &#63;.
	*
	* @param dictItemGroupId the primary key of the current dict item group
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict item group
	* @throws NoSuchDictItemGroupException if a dict item group with the primary key could not be found
	*/
	public static DictItemGroup[] findByUuid_PrevAndNext(long dictItemGroupId,
		String uuid, OrderByComparator<DictItemGroup> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchDictItemGroupException {
		return getPersistence()
				   .findByUuid_PrevAndNext(dictItemGroupId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the dict item groups where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of dict item groups where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching dict item groups
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the dict item group where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDictItemGroupException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dict item group
	* @throws NoSuchDictItemGroupException if a matching dict item group could not be found
	*/
	public static DictItemGroup findByUUID_G(String uuid, long groupId)
		throws org.opencps.datamgt.exception.NoSuchDictItemGroupException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the dict item group where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dict item group, or <code>null</code> if a matching dict item group could not be found
	*/
	public static DictItemGroup fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the dict item group where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dict item group, or <code>null</code> if a matching dict item group could not be found
	*/
	public static DictItemGroup fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the dict item group where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the dict item group that was removed
	*/
	public static DictItemGroup removeByUUID_G(String uuid, long groupId)
		throws org.opencps.datamgt.exception.NoSuchDictItemGroupException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of dict item groups where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching dict item groups
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the dict item groups where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching dict item groups
	*/
	public static List<DictItemGroup> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the dict item groups where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dict item groups
	* @param end the upper bound of the range of dict item groups (not inclusive)
	* @return the range of matching dict item groups
	*/
	public static List<DictItemGroup> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the dict item groups where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dict item groups
	* @param end the upper bound of the range of dict item groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict item groups
	*/
	public static List<DictItemGroup> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<DictItemGroup> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dict item groups where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dict item groups
	* @param end the upper bound of the range of dict item groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict item groups
	*/
	public static List<DictItemGroup> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<DictItemGroup> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dict item group in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item group
	* @throws NoSuchDictItemGroupException if a matching dict item group could not be found
	*/
	public static DictItemGroup findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DictItemGroup> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchDictItemGroupException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first dict item group in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item group, or <code>null</code> if a matching dict item group could not be found
	*/
	public static DictItemGroup fetchByUuid_C_First(String uuid,
		long companyId, OrderByComparator<DictItemGroup> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last dict item group in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item group
	* @throws NoSuchDictItemGroupException if a matching dict item group could not be found
	*/
	public static DictItemGroup findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DictItemGroup> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchDictItemGroupException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last dict item group in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item group, or <code>null</code> if a matching dict item group could not be found
	*/
	public static DictItemGroup fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DictItemGroup> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the dict item groups before and after the current dict item group in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param dictItemGroupId the primary key of the current dict item group
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict item group
	* @throws NoSuchDictItemGroupException if a dict item group with the primary key could not be found
	*/
	public static DictItemGroup[] findByUuid_C_PrevAndNext(
		long dictItemGroupId, String uuid, long companyId,
		OrderByComparator<DictItemGroup> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchDictItemGroupException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(dictItemGroupId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the dict item groups where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of dict item groups where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching dict item groups
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the dict item group where groupId = &#63; and dictGroupId = &#63; and dictItemId = &#63; or throws a {@link NoSuchDictItemGroupException} if it could not be found.
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @param dictItemId the dict item ID
	* @return the matching dict item group
	* @throws NoSuchDictItemGroupException if a matching dict item group could not be found
	*/
	public static DictItemGroup findByF_dictItemId_dictGroupId(long groupId,
		long dictGroupId, long dictItemId)
		throws org.opencps.datamgt.exception.NoSuchDictItemGroupException {
		return getPersistence()
				   .findByF_dictItemId_dictGroupId(groupId, dictGroupId,
			dictItemId);
	}

	/**
	* Returns the dict item group where groupId = &#63; and dictGroupId = &#63; and dictItemId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @param dictItemId the dict item ID
	* @return the matching dict item group, or <code>null</code> if a matching dict item group could not be found
	*/
	public static DictItemGroup fetchByF_dictItemId_dictGroupId(long groupId,
		long dictGroupId, long dictItemId) {
		return getPersistence()
				   .fetchByF_dictItemId_dictGroupId(groupId, dictGroupId,
			dictItemId);
	}

	/**
	* Returns the dict item group where groupId = &#63; and dictGroupId = &#63; and dictItemId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @param dictItemId the dict item ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dict item group, or <code>null</code> if a matching dict item group could not be found
	*/
	public static DictItemGroup fetchByF_dictItemId_dictGroupId(long groupId,
		long dictGroupId, long dictItemId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_dictItemId_dictGroupId(groupId, dictGroupId,
			dictItemId, retrieveFromCache);
	}

	/**
	* Removes the dict item group where groupId = &#63; and dictGroupId = &#63; and dictItemId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @param dictItemId the dict item ID
	* @return the dict item group that was removed
	*/
	public static DictItemGroup removeByF_dictItemId_dictGroupId(long groupId,
		long dictGroupId, long dictItemId)
		throws org.opencps.datamgt.exception.NoSuchDictItemGroupException {
		return getPersistence()
				   .removeByF_dictItemId_dictGroupId(groupId, dictGroupId,
			dictItemId);
	}

	/**
	* Returns the number of dict item groups where groupId = &#63; and dictGroupId = &#63; and dictItemId = &#63;.
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @param dictItemId the dict item ID
	* @return the number of matching dict item groups
	*/
	public static int countByF_dictItemId_dictGroupId(long groupId,
		long dictGroupId, long dictItemId) {
		return getPersistence()
				   .countByF_dictItemId_dictGroupId(groupId, dictGroupId,
			dictItemId);
	}

	/**
	* Returns all the dict item groups where groupId = &#63; and dictGroupId = &#63;.
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @return the matching dict item groups
	*/
	public static List<DictItemGroup> findByF_dictGroupId(long groupId,
		long dictGroupId) {
		return getPersistence().findByF_dictGroupId(groupId, dictGroupId);
	}

	/**
	* Returns a range of all the dict item groups where groupId = &#63; and dictGroupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @param start the lower bound of the range of dict item groups
	* @param end the upper bound of the range of dict item groups (not inclusive)
	* @return the range of matching dict item groups
	*/
	public static List<DictItemGroup> findByF_dictGroupId(long groupId,
		long dictGroupId, int start, int end) {
		return getPersistence()
				   .findByF_dictGroupId(groupId, dictGroupId, start, end);
	}

	/**
	* Returns an ordered range of all the dict item groups where groupId = &#63; and dictGroupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @param start the lower bound of the range of dict item groups
	* @param end the upper bound of the range of dict item groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict item groups
	*/
	public static List<DictItemGroup> findByF_dictGroupId(long groupId,
		long dictGroupId, int start, int end,
		OrderByComparator<DictItemGroup> orderByComparator) {
		return getPersistence()
				   .findByF_dictGroupId(groupId, dictGroupId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dict item groups where groupId = &#63; and dictGroupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @param start the lower bound of the range of dict item groups
	* @param end the upper bound of the range of dict item groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict item groups
	*/
	public static List<DictItemGroup> findByF_dictGroupId(long groupId,
		long dictGroupId, int start, int end,
		OrderByComparator<DictItemGroup> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_dictGroupId(groupId, dictGroupId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dict item group in the ordered set where groupId = &#63; and dictGroupId = &#63;.
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item group
	* @throws NoSuchDictItemGroupException if a matching dict item group could not be found
	*/
	public static DictItemGroup findByF_dictGroupId_First(long groupId,
		long dictGroupId, OrderByComparator<DictItemGroup> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchDictItemGroupException {
		return getPersistence()
				   .findByF_dictGroupId_First(groupId, dictGroupId,
			orderByComparator);
	}

	/**
	* Returns the first dict item group in the ordered set where groupId = &#63; and dictGroupId = &#63;.
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item group, or <code>null</code> if a matching dict item group could not be found
	*/
	public static DictItemGroup fetchByF_dictGroupId_First(long groupId,
		long dictGroupId, OrderByComparator<DictItemGroup> orderByComparator) {
		return getPersistence()
				   .fetchByF_dictGroupId_First(groupId, dictGroupId,
			orderByComparator);
	}

	/**
	* Returns the last dict item group in the ordered set where groupId = &#63; and dictGroupId = &#63;.
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item group
	* @throws NoSuchDictItemGroupException if a matching dict item group could not be found
	*/
	public static DictItemGroup findByF_dictGroupId_Last(long groupId,
		long dictGroupId, OrderByComparator<DictItemGroup> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchDictItemGroupException {
		return getPersistence()
				   .findByF_dictGroupId_Last(groupId, dictGroupId,
			orderByComparator);
	}

	/**
	* Returns the last dict item group in the ordered set where groupId = &#63; and dictGroupId = &#63;.
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item group, or <code>null</code> if a matching dict item group could not be found
	*/
	public static DictItemGroup fetchByF_dictGroupId_Last(long groupId,
		long dictGroupId, OrderByComparator<DictItemGroup> orderByComparator) {
		return getPersistence()
				   .fetchByF_dictGroupId_Last(groupId, dictGroupId,
			orderByComparator);
	}

	/**
	* Returns the dict item groups before and after the current dict item group in the ordered set where groupId = &#63; and dictGroupId = &#63;.
	*
	* @param dictItemGroupId the primary key of the current dict item group
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict item group
	* @throws NoSuchDictItemGroupException if a dict item group with the primary key could not be found
	*/
	public static DictItemGroup[] findByF_dictGroupId_PrevAndNext(
		long dictItemGroupId, long groupId, long dictGroupId,
		OrderByComparator<DictItemGroup> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchDictItemGroupException {
		return getPersistence()
				   .findByF_dictGroupId_PrevAndNext(dictItemGroupId, groupId,
			dictGroupId, orderByComparator);
	}

	/**
	* Removes all the dict item groups where groupId = &#63; and dictGroupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	*/
	public static void removeByF_dictGroupId(long groupId, long dictGroupId) {
		getPersistence().removeByF_dictGroupId(groupId, dictGroupId);
	}

	/**
	* Returns the number of dict item groups where groupId = &#63; and dictGroupId = &#63;.
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @return the number of matching dict item groups
	*/
	public static int countByF_dictGroupId(long groupId, long dictGroupId) {
		return getPersistence().countByF_dictGroupId(groupId, dictGroupId);
	}

	/**
	* Returns all the dict item groups where groupId = &#63; and dictItemId = &#63;.
	*
	* @param groupId the group ID
	* @param dictItemId the dict item ID
	* @return the matching dict item groups
	*/
	public static List<DictItemGroup> findByF_dictItemId(long groupId,
		long dictItemId) {
		return getPersistence().findByF_dictItemId(groupId, dictItemId);
	}

	/**
	* Returns a range of all the dict item groups where groupId = &#63; and dictItemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dictItemId the dict item ID
	* @param start the lower bound of the range of dict item groups
	* @param end the upper bound of the range of dict item groups (not inclusive)
	* @return the range of matching dict item groups
	*/
	public static List<DictItemGroup> findByF_dictItemId(long groupId,
		long dictItemId, int start, int end) {
		return getPersistence()
				   .findByF_dictItemId(groupId, dictItemId, start, end);
	}

	/**
	* Returns an ordered range of all the dict item groups where groupId = &#63; and dictItemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dictItemId the dict item ID
	* @param start the lower bound of the range of dict item groups
	* @param end the upper bound of the range of dict item groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict item groups
	*/
	public static List<DictItemGroup> findByF_dictItemId(long groupId,
		long dictItemId, int start, int end,
		OrderByComparator<DictItemGroup> orderByComparator) {
		return getPersistence()
				   .findByF_dictItemId(groupId, dictItemId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dict item groups where groupId = &#63; and dictItemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dictItemId the dict item ID
	* @param start the lower bound of the range of dict item groups
	* @param end the upper bound of the range of dict item groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict item groups
	*/
	public static List<DictItemGroup> findByF_dictItemId(long groupId,
		long dictItemId, int start, int end,
		OrderByComparator<DictItemGroup> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_dictItemId(groupId, dictItemId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dict item group in the ordered set where groupId = &#63; and dictItemId = &#63;.
	*
	* @param groupId the group ID
	* @param dictItemId the dict item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item group
	* @throws NoSuchDictItemGroupException if a matching dict item group could not be found
	*/
	public static DictItemGroup findByF_dictItemId_First(long groupId,
		long dictItemId, OrderByComparator<DictItemGroup> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchDictItemGroupException {
		return getPersistence()
				   .findByF_dictItemId_First(groupId, dictItemId,
			orderByComparator);
	}

	/**
	* Returns the first dict item group in the ordered set where groupId = &#63; and dictItemId = &#63;.
	*
	* @param groupId the group ID
	* @param dictItemId the dict item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item group, or <code>null</code> if a matching dict item group could not be found
	*/
	public static DictItemGroup fetchByF_dictItemId_First(long groupId,
		long dictItemId, OrderByComparator<DictItemGroup> orderByComparator) {
		return getPersistence()
				   .fetchByF_dictItemId_First(groupId, dictItemId,
			orderByComparator);
	}

	/**
	* Returns the last dict item group in the ordered set where groupId = &#63; and dictItemId = &#63;.
	*
	* @param groupId the group ID
	* @param dictItemId the dict item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item group
	* @throws NoSuchDictItemGroupException if a matching dict item group could not be found
	*/
	public static DictItemGroup findByF_dictItemId_Last(long groupId,
		long dictItemId, OrderByComparator<DictItemGroup> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchDictItemGroupException {
		return getPersistence()
				   .findByF_dictItemId_Last(groupId, dictItemId,
			orderByComparator);
	}

	/**
	* Returns the last dict item group in the ordered set where groupId = &#63; and dictItemId = &#63;.
	*
	* @param groupId the group ID
	* @param dictItemId the dict item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item group, or <code>null</code> if a matching dict item group could not be found
	*/
	public static DictItemGroup fetchByF_dictItemId_Last(long groupId,
		long dictItemId, OrderByComparator<DictItemGroup> orderByComparator) {
		return getPersistence()
				   .fetchByF_dictItemId_Last(groupId, dictItemId,
			orderByComparator);
	}

	/**
	* Returns the dict item groups before and after the current dict item group in the ordered set where groupId = &#63; and dictItemId = &#63;.
	*
	* @param dictItemGroupId the primary key of the current dict item group
	* @param groupId the group ID
	* @param dictItemId the dict item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict item group
	* @throws NoSuchDictItemGroupException if a dict item group with the primary key could not be found
	*/
	public static DictItemGroup[] findByF_dictItemId_PrevAndNext(
		long dictItemGroupId, long groupId, long dictItemId,
		OrderByComparator<DictItemGroup> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchDictItemGroupException {
		return getPersistence()
				   .findByF_dictItemId_PrevAndNext(dictItemGroupId, groupId,
			dictItemId, orderByComparator);
	}

	/**
	* Removes all the dict item groups where groupId = &#63; and dictItemId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dictItemId the dict item ID
	*/
	public static void removeByF_dictItemId(long groupId, long dictItemId) {
		getPersistence().removeByF_dictItemId(groupId, dictItemId);
	}

	/**
	* Returns the number of dict item groups where groupId = &#63; and dictItemId = &#63;.
	*
	* @param groupId the group ID
	* @param dictItemId the dict item ID
	* @return the number of matching dict item groups
	*/
	public static int countByF_dictItemId(long groupId, long dictItemId) {
		return getPersistence().countByF_dictItemId(groupId, dictItemId);
	}

	/**
	* Returns all the dict item groups where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @return the matching dict item groups
	*/
	public static List<DictItemGroup> findByF_newerThan(Date modifiedDate,
		long groupId) {
		return getPersistence().findByF_newerThan(modifiedDate, groupId);
	}

	/**
	* Returns a range of all the dict item groups where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param start the lower bound of the range of dict item groups
	* @param end the upper bound of the range of dict item groups (not inclusive)
	* @return the range of matching dict item groups
	*/
	public static List<DictItemGroup> findByF_newerThan(Date modifiedDate,
		long groupId, int start, int end) {
		return getPersistence()
				   .findByF_newerThan(modifiedDate, groupId, start, end);
	}

	/**
	* Returns an ordered range of all the dict item groups where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param start the lower bound of the range of dict item groups
	* @param end the upper bound of the range of dict item groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict item groups
	*/
	public static List<DictItemGroup> findByF_newerThan(Date modifiedDate,
		long groupId, int start, int end,
		OrderByComparator<DictItemGroup> orderByComparator) {
		return getPersistence()
				   .findByF_newerThan(modifiedDate, groupId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dict item groups where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param start the lower bound of the range of dict item groups
	* @param end the upper bound of the range of dict item groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict item groups
	*/
	public static List<DictItemGroup> findByF_newerThan(Date modifiedDate,
		long groupId, int start, int end,
		OrderByComparator<DictItemGroup> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_newerThan(modifiedDate, groupId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dict item group in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item group
	* @throws NoSuchDictItemGroupException if a matching dict item group could not be found
	*/
	public static DictItemGroup findByF_newerThan_First(Date modifiedDate,
		long groupId, OrderByComparator<DictItemGroup> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchDictItemGroupException {
		return getPersistence()
				   .findByF_newerThan_First(modifiedDate, groupId,
			orderByComparator);
	}

	/**
	* Returns the first dict item group in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item group, or <code>null</code> if a matching dict item group could not be found
	*/
	public static DictItemGroup fetchByF_newerThan_First(Date modifiedDate,
		long groupId, OrderByComparator<DictItemGroup> orderByComparator) {
		return getPersistence()
				   .fetchByF_newerThan_First(modifiedDate, groupId,
			orderByComparator);
	}

	/**
	* Returns the last dict item group in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item group
	* @throws NoSuchDictItemGroupException if a matching dict item group could not be found
	*/
	public static DictItemGroup findByF_newerThan_Last(Date modifiedDate,
		long groupId, OrderByComparator<DictItemGroup> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchDictItemGroupException {
		return getPersistence()
				   .findByF_newerThan_Last(modifiedDate, groupId,
			orderByComparator);
	}

	/**
	* Returns the last dict item group in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item group, or <code>null</code> if a matching dict item group could not be found
	*/
	public static DictItemGroup fetchByF_newerThan_Last(Date modifiedDate,
		long groupId, OrderByComparator<DictItemGroup> orderByComparator) {
		return getPersistence()
				   .fetchByF_newerThan_Last(modifiedDate, groupId,
			orderByComparator);
	}

	/**
	* Returns the dict item groups before and after the current dict item group in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param dictItemGroupId the primary key of the current dict item group
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict item group
	* @throws NoSuchDictItemGroupException if a dict item group with the primary key could not be found
	*/
	public static DictItemGroup[] findByF_newerThan_PrevAndNext(
		long dictItemGroupId, Date modifiedDate, long groupId,
		OrderByComparator<DictItemGroup> orderByComparator)
		throws org.opencps.datamgt.exception.NoSuchDictItemGroupException {
		return getPersistence()
				   .findByF_newerThan_PrevAndNext(dictItemGroupId,
			modifiedDate, groupId, orderByComparator);
	}

	/**
	* Removes all the dict item groups where modifiedDate &ge; &#63; and groupId = &#63; from the database.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	*/
	public static void removeByF_newerThan(Date modifiedDate, long groupId) {
		getPersistence().removeByF_newerThan(modifiedDate, groupId);
	}

	/**
	* Returns the number of dict item groups where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @return the number of matching dict item groups
	*/
	public static int countByF_newerThan(Date modifiedDate, long groupId) {
		return getPersistence().countByF_newerThan(modifiedDate, groupId);
	}

	/**
	* Caches the dict item group in the entity cache if it is enabled.
	*
	* @param dictItemGroup the dict item group
	*/
	public static void cacheResult(DictItemGroup dictItemGroup) {
		getPersistence().cacheResult(dictItemGroup);
	}

	/**
	* Caches the dict item groups in the entity cache if it is enabled.
	*
	* @param dictItemGroups the dict item groups
	*/
	public static void cacheResult(List<DictItemGroup> dictItemGroups) {
		getPersistence().cacheResult(dictItemGroups);
	}

	/**
	* Creates a new dict item group with the primary key. Does not add the dict item group to the database.
	*
	* @param dictItemGroupId the primary key for the new dict item group
	* @return the new dict item group
	*/
	public static DictItemGroup create(long dictItemGroupId) {
		return getPersistence().create(dictItemGroupId);
	}

	/**
	* Removes the dict item group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dictItemGroupId the primary key of the dict item group
	* @return the dict item group that was removed
	* @throws NoSuchDictItemGroupException if a dict item group with the primary key could not be found
	*/
	public static DictItemGroup remove(long dictItemGroupId)
		throws org.opencps.datamgt.exception.NoSuchDictItemGroupException {
		return getPersistence().remove(dictItemGroupId);
	}

	public static DictItemGroup updateImpl(DictItemGroup dictItemGroup) {
		return getPersistence().updateImpl(dictItemGroup);
	}

	/**
	* Returns the dict item group with the primary key or throws a {@link NoSuchDictItemGroupException} if it could not be found.
	*
	* @param dictItemGroupId the primary key of the dict item group
	* @return the dict item group
	* @throws NoSuchDictItemGroupException if a dict item group with the primary key could not be found
	*/
	public static DictItemGroup findByPrimaryKey(long dictItemGroupId)
		throws org.opencps.datamgt.exception.NoSuchDictItemGroupException {
		return getPersistence().findByPrimaryKey(dictItemGroupId);
	}

	/**
	* Returns the dict item group with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dictItemGroupId the primary key of the dict item group
	* @return the dict item group, or <code>null</code> if a dict item group with the primary key could not be found
	*/
	public static DictItemGroup fetchByPrimaryKey(long dictItemGroupId) {
		return getPersistence().fetchByPrimaryKey(dictItemGroupId);
	}

	public static java.util.Map<java.io.Serializable, DictItemGroup> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the dict item groups.
	*
	* @return the dict item groups
	*/
	public static List<DictItemGroup> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the dict item groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict item groups
	* @param end the upper bound of the range of dict item groups (not inclusive)
	* @return the range of dict item groups
	*/
	public static List<DictItemGroup> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the dict item groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict item groups
	* @param end the upper bound of the range of dict item groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dict item groups
	*/
	public static List<DictItemGroup> findAll(int start, int end,
		OrderByComparator<DictItemGroup> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dict item groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict item groups
	* @param end the upper bound of the range of dict item groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dict item groups
	*/
	public static List<DictItemGroup> findAll(int start, int end,
		OrderByComparator<DictItemGroup> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the dict item groups from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of dict item groups.
	*
	* @return the number of dict item groups
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static DictItemGroupPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DictItemGroupPersistence, DictItemGroupPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DictItemGroupPersistence.class);

		ServiceTracker<DictItemGroupPersistence, DictItemGroupPersistence> serviceTracker =
			new ServiceTracker<DictItemGroupPersistence, DictItemGroupPersistence>(bundle.getBundleContext(),
				DictItemGroupPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}