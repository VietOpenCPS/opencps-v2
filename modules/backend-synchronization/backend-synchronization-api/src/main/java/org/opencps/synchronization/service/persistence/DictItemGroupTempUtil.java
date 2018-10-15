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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.synchronization.model.DictItemGroupTemp;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.Date;
import java.util.List;

/**
 * The persistence utility for the dict item group temp service. This utility wraps {@link org.opencps.synchronization.service.persistence.impl.DictItemGroupTempPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author trungdk
 * @see DictItemGroupTempPersistence
 * @see org.opencps.synchronization.service.persistence.impl.DictItemGroupTempPersistenceImpl
 * @generated
 */
@ProviderType
public class DictItemGroupTempUtil {
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
	public static void clearCache(DictItemGroupTemp dictItemGroupTemp) {
		getPersistence().clearCache(dictItemGroupTemp);
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
	public static List<DictItemGroupTemp> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DictItemGroupTemp> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DictItemGroupTemp> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<DictItemGroupTemp> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static DictItemGroupTemp update(DictItemGroupTemp dictItemGroupTemp) {
		return getPersistence().update(dictItemGroupTemp);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static DictItemGroupTemp update(
		DictItemGroupTemp dictItemGroupTemp, ServiceContext serviceContext) {
		return getPersistence().update(dictItemGroupTemp, serviceContext);
	}

	/**
	* Returns all the dict item group temps where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching dict item group temps
	*/
	public static List<DictItemGroupTemp> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the dict item group temps where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dict item group temps
	* @param end the upper bound of the range of dict item group temps (not inclusive)
	* @return the range of matching dict item group temps
	*/
	public static List<DictItemGroupTemp> findByUuid(String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the dict item group temps where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dict item group temps
	* @param end the upper bound of the range of dict item group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict item group temps
	*/
	public static List<DictItemGroupTemp> findByUuid(String uuid, int start,
		int end, OrderByComparator<DictItemGroupTemp> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dict item group temps where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dict item group temps
	* @param end the upper bound of the range of dict item group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict item group temps
	*/
	public static List<DictItemGroupTemp> findByUuid(String uuid, int start,
		int end, OrderByComparator<DictItemGroupTemp> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first dict item group temp in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item group temp
	* @throws NoSuchDictItemGroupTempException if a matching dict item group temp could not be found
	*/
	public static DictItemGroupTemp findByUuid_First(String uuid,
		OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchDictItemGroupTempException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first dict item group temp in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	*/
	public static DictItemGroupTemp fetchByUuid_First(String uuid,
		OrderByComparator<DictItemGroupTemp> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last dict item group temp in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item group temp
	* @throws NoSuchDictItemGroupTempException if a matching dict item group temp could not be found
	*/
	public static DictItemGroupTemp findByUuid_Last(String uuid,
		OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchDictItemGroupTempException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last dict item group temp in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	*/
	public static DictItemGroupTemp fetchByUuid_Last(String uuid,
		OrderByComparator<DictItemGroupTemp> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the dict item group temps before and after the current dict item group temp in the ordered set where uuid = &#63;.
	*
	* @param dictItemGroupId the primary key of the current dict item group temp
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict item group temp
	* @throws NoSuchDictItemGroupTempException if a dict item group temp with the primary key could not be found
	*/
	public static DictItemGroupTemp[] findByUuid_PrevAndNext(
		long dictItemGroupId, String uuid,
		OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchDictItemGroupTempException {
		return getPersistence()
				   .findByUuid_PrevAndNext(dictItemGroupId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the dict item group temps where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of dict item group temps where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching dict item group temps
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the dict item group temp where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDictItemGroupTempException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dict item group temp
	* @throws NoSuchDictItemGroupTempException if a matching dict item group temp could not be found
	*/
	public static DictItemGroupTemp findByUUID_G(String uuid, long groupId)
		throws org.opencps.synchronization.exception.NoSuchDictItemGroupTempException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the dict item group temp where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	*/
	public static DictItemGroupTemp fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the dict item group temp where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	*/
	public static DictItemGroupTemp fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the dict item group temp where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the dict item group temp that was removed
	*/
	public static DictItemGroupTemp removeByUUID_G(String uuid, long groupId)
		throws org.opencps.synchronization.exception.NoSuchDictItemGroupTempException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of dict item group temps where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching dict item group temps
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the dict item group temps where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching dict item group temps
	*/
	public static List<DictItemGroupTemp> findByUuid_C(String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the dict item group temps where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dict item group temps
	* @param end the upper bound of the range of dict item group temps (not inclusive)
	* @return the range of matching dict item group temps
	*/
	public static List<DictItemGroupTemp> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the dict item group temps where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dict item group temps
	* @param end the upper bound of the range of dict item group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict item group temps
	*/
	public static List<DictItemGroupTemp> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<DictItemGroupTemp> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dict item group temps where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dict item group temps
	* @param end the upper bound of the range of dict item group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict item group temps
	*/
	public static List<DictItemGroupTemp> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<DictItemGroupTemp> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dict item group temp in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item group temp
	* @throws NoSuchDictItemGroupTempException if a matching dict item group temp could not be found
	*/
	public static DictItemGroupTemp findByUuid_C_First(String uuid,
		long companyId, OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchDictItemGroupTempException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first dict item group temp in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	*/
	public static DictItemGroupTemp fetchByUuid_C_First(String uuid,
		long companyId, OrderByComparator<DictItemGroupTemp> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last dict item group temp in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item group temp
	* @throws NoSuchDictItemGroupTempException if a matching dict item group temp could not be found
	*/
	public static DictItemGroupTemp findByUuid_C_Last(String uuid,
		long companyId, OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchDictItemGroupTempException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last dict item group temp in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	*/
	public static DictItemGroupTemp fetchByUuid_C_Last(String uuid,
		long companyId, OrderByComparator<DictItemGroupTemp> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the dict item group temps before and after the current dict item group temp in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param dictItemGroupId the primary key of the current dict item group temp
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict item group temp
	* @throws NoSuchDictItemGroupTempException if a dict item group temp with the primary key could not be found
	*/
	public static DictItemGroupTemp[] findByUuid_C_PrevAndNext(
		long dictItemGroupId, String uuid, long companyId,
		OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchDictItemGroupTempException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(dictItemGroupId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the dict item group temps where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of dict item group temps where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching dict item group temps
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the dict item group temp where groupId = &#63; and dictGroupId = &#63; and dictItemId = &#63; or throws a {@link NoSuchDictItemGroupTempException} if it could not be found.
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @param dictItemId the dict item ID
	* @return the matching dict item group temp
	* @throws NoSuchDictItemGroupTempException if a matching dict item group temp could not be found
	*/
	public static DictItemGroupTemp findByF_dictItemId_dictGroupId(
		long groupId, long dictGroupId, long dictItemId)
		throws org.opencps.synchronization.exception.NoSuchDictItemGroupTempException {
		return getPersistence()
				   .findByF_dictItemId_dictGroupId(groupId, dictGroupId,
			dictItemId);
	}

	/**
	* Returns the dict item group temp where groupId = &#63; and dictGroupId = &#63; and dictItemId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @param dictItemId the dict item ID
	* @return the matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	*/
	public static DictItemGroupTemp fetchByF_dictItemId_dictGroupId(
		long groupId, long dictGroupId, long dictItemId) {
		return getPersistence()
				   .fetchByF_dictItemId_dictGroupId(groupId, dictGroupId,
			dictItemId);
	}

	/**
	* Returns the dict item group temp where groupId = &#63; and dictGroupId = &#63; and dictItemId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @param dictItemId the dict item ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	*/
	public static DictItemGroupTemp fetchByF_dictItemId_dictGroupId(
		long groupId, long dictGroupId, long dictItemId,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_dictItemId_dictGroupId(groupId, dictGroupId,
			dictItemId, retrieveFromCache);
	}

	/**
	* Removes the dict item group temp where groupId = &#63; and dictGroupId = &#63; and dictItemId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @param dictItemId the dict item ID
	* @return the dict item group temp that was removed
	*/
	public static DictItemGroupTemp removeByF_dictItemId_dictGroupId(
		long groupId, long dictGroupId, long dictItemId)
		throws org.opencps.synchronization.exception.NoSuchDictItemGroupTempException {
		return getPersistence()
				   .removeByF_dictItemId_dictGroupId(groupId, dictGroupId,
			dictItemId);
	}

	/**
	* Returns the number of dict item group temps where groupId = &#63; and dictGroupId = &#63; and dictItemId = &#63;.
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @param dictItemId the dict item ID
	* @return the number of matching dict item group temps
	*/
	public static int countByF_dictItemId_dictGroupId(long groupId,
		long dictGroupId, long dictItemId) {
		return getPersistence()
				   .countByF_dictItemId_dictGroupId(groupId, dictGroupId,
			dictItemId);
	}

	/**
	* Returns all the dict item group temps where groupId = &#63; and dictGroupId = &#63;.
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @return the matching dict item group temps
	*/
	public static List<DictItemGroupTemp> findByF_dictGroupId(long groupId,
		long dictGroupId) {
		return getPersistence().findByF_dictGroupId(groupId, dictGroupId);
	}

	/**
	* Returns a range of all the dict item group temps where groupId = &#63; and dictGroupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @param start the lower bound of the range of dict item group temps
	* @param end the upper bound of the range of dict item group temps (not inclusive)
	* @return the range of matching dict item group temps
	*/
	public static List<DictItemGroupTemp> findByF_dictGroupId(long groupId,
		long dictGroupId, int start, int end) {
		return getPersistence()
				   .findByF_dictGroupId(groupId, dictGroupId, start, end);
	}

	/**
	* Returns an ordered range of all the dict item group temps where groupId = &#63; and dictGroupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @param start the lower bound of the range of dict item group temps
	* @param end the upper bound of the range of dict item group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict item group temps
	*/
	public static List<DictItemGroupTemp> findByF_dictGroupId(long groupId,
		long dictGroupId, int start, int end,
		OrderByComparator<DictItemGroupTemp> orderByComparator) {
		return getPersistence()
				   .findByF_dictGroupId(groupId, dictGroupId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dict item group temps where groupId = &#63; and dictGroupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @param start the lower bound of the range of dict item group temps
	* @param end the upper bound of the range of dict item group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict item group temps
	*/
	public static List<DictItemGroupTemp> findByF_dictGroupId(long groupId,
		long dictGroupId, int start, int end,
		OrderByComparator<DictItemGroupTemp> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_dictGroupId(groupId, dictGroupId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dict item group temp in the ordered set where groupId = &#63; and dictGroupId = &#63;.
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item group temp
	* @throws NoSuchDictItemGroupTempException if a matching dict item group temp could not be found
	*/
	public static DictItemGroupTemp findByF_dictGroupId_First(long groupId,
		long dictGroupId, OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchDictItemGroupTempException {
		return getPersistence()
				   .findByF_dictGroupId_First(groupId, dictGroupId,
			orderByComparator);
	}

	/**
	* Returns the first dict item group temp in the ordered set where groupId = &#63; and dictGroupId = &#63;.
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	*/
	public static DictItemGroupTemp fetchByF_dictGroupId_First(long groupId,
		long dictGroupId, OrderByComparator<DictItemGroupTemp> orderByComparator) {
		return getPersistence()
				   .fetchByF_dictGroupId_First(groupId, dictGroupId,
			orderByComparator);
	}

	/**
	* Returns the last dict item group temp in the ordered set where groupId = &#63; and dictGroupId = &#63;.
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item group temp
	* @throws NoSuchDictItemGroupTempException if a matching dict item group temp could not be found
	*/
	public static DictItemGroupTemp findByF_dictGroupId_Last(long groupId,
		long dictGroupId, OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchDictItemGroupTempException {
		return getPersistence()
				   .findByF_dictGroupId_Last(groupId, dictGroupId,
			orderByComparator);
	}

	/**
	* Returns the last dict item group temp in the ordered set where groupId = &#63; and dictGroupId = &#63;.
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	*/
	public static DictItemGroupTemp fetchByF_dictGroupId_Last(long groupId,
		long dictGroupId, OrderByComparator<DictItemGroupTemp> orderByComparator) {
		return getPersistence()
				   .fetchByF_dictGroupId_Last(groupId, dictGroupId,
			orderByComparator);
	}

	/**
	* Returns the dict item group temps before and after the current dict item group temp in the ordered set where groupId = &#63; and dictGroupId = &#63;.
	*
	* @param dictItemGroupId the primary key of the current dict item group temp
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict item group temp
	* @throws NoSuchDictItemGroupTempException if a dict item group temp with the primary key could not be found
	*/
	public static DictItemGroupTemp[] findByF_dictGroupId_PrevAndNext(
		long dictItemGroupId, long groupId, long dictGroupId,
		OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchDictItemGroupTempException {
		return getPersistence()
				   .findByF_dictGroupId_PrevAndNext(dictItemGroupId, groupId,
			dictGroupId, orderByComparator);
	}

	/**
	* Removes all the dict item group temps where groupId = &#63; and dictGroupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	*/
	public static void removeByF_dictGroupId(long groupId, long dictGroupId) {
		getPersistence().removeByF_dictGroupId(groupId, dictGroupId);
	}

	/**
	* Returns the number of dict item group temps where groupId = &#63; and dictGroupId = &#63;.
	*
	* @param groupId the group ID
	* @param dictGroupId the dict group ID
	* @return the number of matching dict item group temps
	*/
	public static int countByF_dictGroupId(long groupId, long dictGroupId) {
		return getPersistence().countByF_dictGroupId(groupId, dictGroupId);
	}

	/**
	* Returns all the dict item group temps where groupId = &#63; and dictItemId = &#63;.
	*
	* @param groupId the group ID
	* @param dictItemId the dict item ID
	* @return the matching dict item group temps
	*/
	public static List<DictItemGroupTemp> findByF_dictItemId(long groupId,
		long dictItemId) {
		return getPersistence().findByF_dictItemId(groupId, dictItemId);
	}

	/**
	* Returns a range of all the dict item group temps where groupId = &#63; and dictItemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dictItemId the dict item ID
	* @param start the lower bound of the range of dict item group temps
	* @param end the upper bound of the range of dict item group temps (not inclusive)
	* @return the range of matching dict item group temps
	*/
	public static List<DictItemGroupTemp> findByF_dictItemId(long groupId,
		long dictItemId, int start, int end) {
		return getPersistence()
				   .findByF_dictItemId(groupId, dictItemId, start, end);
	}

	/**
	* Returns an ordered range of all the dict item group temps where groupId = &#63; and dictItemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dictItemId the dict item ID
	* @param start the lower bound of the range of dict item group temps
	* @param end the upper bound of the range of dict item group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict item group temps
	*/
	public static List<DictItemGroupTemp> findByF_dictItemId(long groupId,
		long dictItemId, int start, int end,
		OrderByComparator<DictItemGroupTemp> orderByComparator) {
		return getPersistence()
				   .findByF_dictItemId(groupId, dictItemId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dict item group temps where groupId = &#63; and dictItemId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param dictItemId the dict item ID
	* @param start the lower bound of the range of dict item group temps
	* @param end the upper bound of the range of dict item group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict item group temps
	*/
	public static List<DictItemGroupTemp> findByF_dictItemId(long groupId,
		long dictItemId, int start, int end,
		OrderByComparator<DictItemGroupTemp> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_dictItemId(groupId, dictItemId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dict item group temp in the ordered set where groupId = &#63; and dictItemId = &#63;.
	*
	* @param groupId the group ID
	* @param dictItemId the dict item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item group temp
	* @throws NoSuchDictItemGroupTempException if a matching dict item group temp could not be found
	*/
	public static DictItemGroupTemp findByF_dictItemId_First(long groupId,
		long dictItemId, OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchDictItemGroupTempException {
		return getPersistence()
				   .findByF_dictItemId_First(groupId, dictItemId,
			orderByComparator);
	}

	/**
	* Returns the first dict item group temp in the ordered set where groupId = &#63; and dictItemId = &#63;.
	*
	* @param groupId the group ID
	* @param dictItemId the dict item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	*/
	public static DictItemGroupTemp fetchByF_dictItemId_First(long groupId,
		long dictItemId, OrderByComparator<DictItemGroupTemp> orderByComparator) {
		return getPersistence()
				   .fetchByF_dictItemId_First(groupId, dictItemId,
			orderByComparator);
	}

	/**
	* Returns the last dict item group temp in the ordered set where groupId = &#63; and dictItemId = &#63;.
	*
	* @param groupId the group ID
	* @param dictItemId the dict item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item group temp
	* @throws NoSuchDictItemGroupTempException if a matching dict item group temp could not be found
	*/
	public static DictItemGroupTemp findByF_dictItemId_Last(long groupId,
		long dictItemId, OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchDictItemGroupTempException {
		return getPersistence()
				   .findByF_dictItemId_Last(groupId, dictItemId,
			orderByComparator);
	}

	/**
	* Returns the last dict item group temp in the ordered set where groupId = &#63; and dictItemId = &#63;.
	*
	* @param groupId the group ID
	* @param dictItemId the dict item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	*/
	public static DictItemGroupTemp fetchByF_dictItemId_Last(long groupId,
		long dictItemId, OrderByComparator<DictItemGroupTemp> orderByComparator) {
		return getPersistence()
				   .fetchByF_dictItemId_Last(groupId, dictItemId,
			orderByComparator);
	}

	/**
	* Returns the dict item group temps before and after the current dict item group temp in the ordered set where groupId = &#63; and dictItemId = &#63;.
	*
	* @param dictItemGroupId the primary key of the current dict item group temp
	* @param groupId the group ID
	* @param dictItemId the dict item ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict item group temp
	* @throws NoSuchDictItemGroupTempException if a dict item group temp with the primary key could not be found
	*/
	public static DictItemGroupTemp[] findByF_dictItemId_PrevAndNext(
		long dictItemGroupId, long groupId, long dictItemId,
		OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchDictItemGroupTempException {
		return getPersistence()
				   .findByF_dictItemId_PrevAndNext(dictItemGroupId, groupId,
			dictItemId, orderByComparator);
	}

	/**
	* Removes all the dict item group temps where groupId = &#63; and dictItemId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param dictItemId the dict item ID
	*/
	public static void removeByF_dictItemId(long groupId, long dictItemId) {
		getPersistence().removeByF_dictItemId(groupId, dictItemId);
	}

	/**
	* Returns the number of dict item group temps where groupId = &#63; and dictItemId = &#63;.
	*
	* @param groupId the group ID
	* @param dictItemId the dict item ID
	* @return the number of matching dict item group temps
	*/
	public static int countByF_dictItemId(long groupId, long dictItemId) {
		return getPersistence().countByF_dictItemId(groupId, dictItemId);
	}

	/**
	* Returns all the dict item group temps where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @return the matching dict item group temps
	*/
	public static List<DictItemGroupTemp> findByF_newerThan(Date modifiedDate,
		long groupId) {
		return getPersistence().findByF_newerThan(modifiedDate, groupId);
	}

	/**
	* Returns a range of all the dict item group temps where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param start the lower bound of the range of dict item group temps
	* @param end the upper bound of the range of dict item group temps (not inclusive)
	* @return the range of matching dict item group temps
	*/
	public static List<DictItemGroupTemp> findByF_newerThan(Date modifiedDate,
		long groupId, int start, int end) {
		return getPersistence()
				   .findByF_newerThan(modifiedDate, groupId, start, end);
	}

	/**
	* Returns an ordered range of all the dict item group temps where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param start the lower bound of the range of dict item group temps
	* @param end the upper bound of the range of dict item group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict item group temps
	*/
	public static List<DictItemGroupTemp> findByF_newerThan(Date modifiedDate,
		long groupId, int start, int end,
		OrderByComparator<DictItemGroupTemp> orderByComparator) {
		return getPersistence()
				   .findByF_newerThan(modifiedDate, groupId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dict item group temps where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param start the lower bound of the range of dict item group temps
	* @param end the upper bound of the range of dict item group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict item group temps
	*/
	public static List<DictItemGroupTemp> findByF_newerThan(Date modifiedDate,
		long groupId, int start, int end,
		OrderByComparator<DictItemGroupTemp> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_newerThan(modifiedDate, groupId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dict item group temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item group temp
	* @throws NoSuchDictItemGroupTempException if a matching dict item group temp could not be found
	*/
	public static DictItemGroupTemp findByF_newerThan_First(Date modifiedDate,
		long groupId, OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchDictItemGroupTempException {
		return getPersistence()
				   .findByF_newerThan_First(modifiedDate, groupId,
			orderByComparator);
	}

	/**
	* Returns the first dict item group temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	*/
	public static DictItemGroupTemp fetchByF_newerThan_First(
		Date modifiedDate, long groupId,
		OrderByComparator<DictItemGroupTemp> orderByComparator) {
		return getPersistence()
				   .fetchByF_newerThan_First(modifiedDate, groupId,
			orderByComparator);
	}

	/**
	* Returns the last dict item group temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item group temp
	* @throws NoSuchDictItemGroupTempException if a matching dict item group temp could not be found
	*/
	public static DictItemGroupTemp findByF_newerThan_Last(Date modifiedDate,
		long groupId, OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchDictItemGroupTempException {
		return getPersistence()
				   .findByF_newerThan_Last(modifiedDate, groupId,
			orderByComparator);
	}

	/**
	* Returns the last dict item group temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	*/
	public static DictItemGroupTemp fetchByF_newerThan_Last(Date modifiedDate,
		long groupId, OrderByComparator<DictItemGroupTemp> orderByComparator) {
		return getPersistence()
				   .fetchByF_newerThan_Last(modifiedDate, groupId,
			orderByComparator);
	}

	/**
	* Returns the dict item group temps before and after the current dict item group temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param dictItemGroupId the primary key of the current dict item group temp
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict item group temp
	* @throws NoSuchDictItemGroupTempException if a dict item group temp with the primary key could not be found
	*/
	public static DictItemGroupTemp[] findByF_newerThan_PrevAndNext(
		long dictItemGroupId, Date modifiedDate, long groupId,
		OrderByComparator<DictItemGroupTemp> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchDictItemGroupTempException {
		return getPersistence()
				   .findByF_newerThan_PrevAndNext(dictItemGroupId,
			modifiedDate, groupId, orderByComparator);
	}

	/**
	* Removes all the dict item group temps where modifiedDate &ge; &#63; and groupId = &#63; from the database.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	*/
	public static void removeByF_newerThan(Date modifiedDate, long groupId) {
		getPersistence().removeByF_newerThan(modifiedDate, groupId);
	}

	/**
	* Returns the number of dict item group temps where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @return the number of matching dict item group temps
	*/
	public static int countByF_newerThan(Date modifiedDate, long groupId) {
		return getPersistence().countByF_newerThan(modifiedDate, groupId);
	}

	/**
	* Caches the dict item group temp in the entity cache if it is enabled.
	*
	* @param dictItemGroupTemp the dict item group temp
	*/
	public static void cacheResult(DictItemGroupTemp dictItemGroupTemp) {
		getPersistence().cacheResult(dictItemGroupTemp);
	}

	/**
	* Caches the dict item group temps in the entity cache if it is enabled.
	*
	* @param dictItemGroupTemps the dict item group temps
	*/
	public static void cacheResult(List<DictItemGroupTemp> dictItemGroupTemps) {
		getPersistence().cacheResult(dictItemGroupTemps);
	}

	/**
	* Creates a new dict item group temp with the primary key. Does not add the dict item group temp to the database.
	*
	* @param dictItemGroupId the primary key for the new dict item group temp
	* @return the new dict item group temp
	*/
	public static DictItemGroupTemp create(long dictItemGroupId) {
		return getPersistence().create(dictItemGroupId);
	}

	/**
	* Removes the dict item group temp with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dictItemGroupId the primary key of the dict item group temp
	* @return the dict item group temp that was removed
	* @throws NoSuchDictItemGroupTempException if a dict item group temp with the primary key could not be found
	*/
	public static DictItemGroupTemp remove(long dictItemGroupId)
		throws org.opencps.synchronization.exception.NoSuchDictItemGroupTempException {
		return getPersistence().remove(dictItemGroupId);
	}

	public static DictItemGroupTemp updateImpl(
		DictItemGroupTemp dictItemGroupTemp) {
		return getPersistence().updateImpl(dictItemGroupTemp);
	}

	/**
	* Returns the dict item group temp with the primary key or throws a {@link NoSuchDictItemGroupTempException} if it could not be found.
	*
	* @param dictItemGroupId the primary key of the dict item group temp
	* @return the dict item group temp
	* @throws NoSuchDictItemGroupTempException if a dict item group temp with the primary key could not be found
	*/
	public static DictItemGroupTemp findByPrimaryKey(long dictItemGroupId)
		throws org.opencps.synchronization.exception.NoSuchDictItemGroupTempException {
		return getPersistence().findByPrimaryKey(dictItemGroupId);
	}

	/**
	* Returns the dict item group temp with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dictItemGroupId the primary key of the dict item group temp
	* @return the dict item group temp, or <code>null</code> if a dict item group temp with the primary key could not be found
	*/
	public static DictItemGroupTemp fetchByPrimaryKey(long dictItemGroupId) {
		return getPersistence().fetchByPrimaryKey(dictItemGroupId);
	}

	public static java.util.Map<java.io.Serializable, DictItemGroupTemp> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the dict item group temps.
	*
	* @return the dict item group temps
	*/
	public static List<DictItemGroupTemp> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the dict item group temps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict item group temps
	* @param end the upper bound of the range of dict item group temps (not inclusive)
	* @return the range of dict item group temps
	*/
	public static List<DictItemGroupTemp> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the dict item group temps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict item group temps
	* @param end the upper bound of the range of dict item group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dict item group temps
	*/
	public static List<DictItemGroupTemp> findAll(int start, int end,
		OrderByComparator<DictItemGroupTemp> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dict item group temps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict item group temps
	* @param end the upper bound of the range of dict item group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dict item group temps
	*/
	public static List<DictItemGroupTemp> findAll(int start, int end,
		OrderByComparator<DictItemGroupTemp> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the dict item group temps from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of dict item group temps.
	*
	* @return the number of dict item group temps
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static DictItemGroupTempPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DictItemGroupTempPersistence, DictItemGroupTempPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DictItemGroupTempPersistence.class);

		ServiceTracker<DictItemGroupTempPersistence, DictItemGroupTempPersistence> serviceTracker =
			new ServiceTracker<DictItemGroupTempPersistence, DictItemGroupTempPersistence>(bundle.getBundleContext(),
				DictItemGroupTempPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}