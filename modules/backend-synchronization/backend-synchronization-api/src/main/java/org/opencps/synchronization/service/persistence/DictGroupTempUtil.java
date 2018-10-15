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

import org.opencps.synchronization.model.DictGroupTemp;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.Date;
import java.util.List;

/**
 * The persistence utility for the dict group temp service. This utility wraps {@link org.opencps.synchronization.service.persistence.impl.DictGroupTempPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author trungdk
 * @see DictGroupTempPersistence
 * @see org.opencps.synchronization.service.persistence.impl.DictGroupTempPersistenceImpl
 * @generated
 */
@ProviderType
public class DictGroupTempUtil {
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
	public static void clearCache(DictGroupTemp dictGroupTemp) {
		getPersistence().clearCache(dictGroupTemp);
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
	public static List<DictGroupTemp> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DictGroupTemp> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DictGroupTemp> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<DictGroupTemp> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static DictGroupTemp update(DictGroupTemp dictGroupTemp) {
		return getPersistence().update(dictGroupTemp);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static DictGroupTemp update(DictGroupTemp dictGroupTemp,
		ServiceContext serviceContext) {
		return getPersistence().update(dictGroupTemp, serviceContext);
	}

	/**
	* Returns all the dict group temps where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching dict group temps
	*/
	public static List<DictGroupTemp> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the dict group temps where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dict group temps
	* @param end the upper bound of the range of dict group temps (not inclusive)
	* @return the range of matching dict group temps
	*/
	public static List<DictGroupTemp> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the dict group temps where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dict group temps
	* @param end the upper bound of the range of dict group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict group temps
	*/
	public static List<DictGroupTemp> findByUuid(String uuid, int start,
		int end, OrderByComparator<DictGroupTemp> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dict group temps where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dict group temps
	* @param end the upper bound of the range of dict group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict group temps
	*/
	public static List<DictGroupTemp> findByUuid(String uuid, int start,
		int end, OrderByComparator<DictGroupTemp> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first dict group temp in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict group temp
	* @throws NoSuchDictGroupTempException if a matching dict group temp could not be found
	*/
	public static DictGroupTemp findByUuid_First(String uuid,
		OrderByComparator<DictGroupTemp> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchDictGroupTempException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first dict group temp in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	*/
	public static DictGroupTemp fetchByUuid_First(String uuid,
		OrderByComparator<DictGroupTemp> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last dict group temp in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict group temp
	* @throws NoSuchDictGroupTempException if a matching dict group temp could not be found
	*/
	public static DictGroupTemp findByUuid_Last(String uuid,
		OrderByComparator<DictGroupTemp> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchDictGroupTempException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last dict group temp in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	*/
	public static DictGroupTemp fetchByUuid_Last(String uuid,
		OrderByComparator<DictGroupTemp> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the dict group temps before and after the current dict group temp in the ordered set where uuid = &#63;.
	*
	* @param dictGroupId the primary key of the current dict group temp
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict group temp
	* @throws NoSuchDictGroupTempException if a dict group temp with the primary key could not be found
	*/
	public static DictGroupTemp[] findByUuid_PrevAndNext(long dictGroupId,
		String uuid, OrderByComparator<DictGroupTemp> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchDictGroupTempException {
		return getPersistence()
				   .findByUuid_PrevAndNext(dictGroupId, uuid, orderByComparator);
	}

	/**
	* Removes all the dict group temps where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of dict group temps where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching dict group temps
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the dict group temp where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDictGroupTempException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dict group temp
	* @throws NoSuchDictGroupTempException if a matching dict group temp could not be found
	*/
	public static DictGroupTemp findByUUID_G(String uuid, long groupId)
		throws org.opencps.synchronization.exception.NoSuchDictGroupTempException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the dict group temp where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	*/
	public static DictGroupTemp fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the dict group temp where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	*/
	public static DictGroupTemp fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the dict group temp where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the dict group temp that was removed
	*/
	public static DictGroupTemp removeByUUID_G(String uuid, long groupId)
		throws org.opencps.synchronization.exception.NoSuchDictGroupTempException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of dict group temps where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching dict group temps
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the dict group temps where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching dict group temps
	*/
	public static List<DictGroupTemp> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the dict group temps where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dict group temps
	* @param end the upper bound of the range of dict group temps (not inclusive)
	* @return the range of matching dict group temps
	*/
	public static List<DictGroupTemp> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the dict group temps where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dict group temps
	* @param end the upper bound of the range of dict group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict group temps
	*/
	public static List<DictGroupTemp> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<DictGroupTemp> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dict group temps where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dict group temps
	* @param end the upper bound of the range of dict group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict group temps
	*/
	public static List<DictGroupTemp> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<DictGroupTemp> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dict group temp in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict group temp
	* @throws NoSuchDictGroupTempException if a matching dict group temp could not be found
	*/
	public static DictGroupTemp findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DictGroupTemp> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchDictGroupTempException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first dict group temp in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	*/
	public static DictGroupTemp fetchByUuid_C_First(String uuid,
		long companyId, OrderByComparator<DictGroupTemp> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last dict group temp in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict group temp
	* @throws NoSuchDictGroupTempException if a matching dict group temp could not be found
	*/
	public static DictGroupTemp findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DictGroupTemp> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchDictGroupTempException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last dict group temp in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	*/
	public static DictGroupTemp fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DictGroupTemp> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the dict group temps before and after the current dict group temp in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param dictGroupId the primary key of the current dict group temp
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict group temp
	* @throws NoSuchDictGroupTempException if a dict group temp with the primary key could not be found
	*/
	public static DictGroupTemp[] findByUuid_C_PrevAndNext(long dictGroupId,
		String uuid, long companyId,
		OrderByComparator<DictGroupTemp> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchDictGroupTempException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(dictGroupId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the dict group temps where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of dict group temps where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching dict group temps
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the dict group temp where groupCode = &#63; and groupId = &#63; or throws a {@link NoSuchDictGroupTempException} if it could not be found.
	*
	* @param groupCode the group code
	* @param groupId the group ID
	* @return the matching dict group temp
	* @throws NoSuchDictGroupTempException if a matching dict group temp could not be found
	*/
	public static DictGroupTemp findByF_groupCode(String groupCode, long groupId)
		throws org.opencps.synchronization.exception.NoSuchDictGroupTempException {
		return getPersistence().findByF_groupCode(groupCode, groupId);
	}

	/**
	* Returns the dict group temp where groupCode = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupCode the group code
	* @param groupId the group ID
	* @return the matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	*/
	public static DictGroupTemp fetchByF_groupCode(String groupCode,
		long groupId) {
		return getPersistence().fetchByF_groupCode(groupCode, groupId);
	}

	/**
	* Returns the dict group temp where groupCode = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupCode the group code
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	*/
	public static DictGroupTemp fetchByF_groupCode(String groupCode,
		long groupId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_groupCode(groupCode, groupId, retrieveFromCache);
	}

	/**
	* Removes the dict group temp where groupCode = &#63; and groupId = &#63; from the database.
	*
	* @param groupCode the group code
	* @param groupId the group ID
	* @return the dict group temp that was removed
	*/
	public static DictGroupTemp removeByF_groupCode(String groupCode,
		long groupId)
		throws org.opencps.synchronization.exception.NoSuchDictGroupTempException {
		return getPersistence().removeByF_groupCode(groupCode, groupId);
	}

	/**
	* Returns the number of dict group temps where groupCode = &#63; and groupId = &#63;.
	*
	* @param groupCode the group code
	* @param groupId the group ID
	* @return the number of matching dict group temps
	*/
	public static int countByF_groupCode(String groupCode, long groupId) {
		return getPersistence().countByF_groupCode(groupCode, groupId);
	}

	/**
	* Returns the dict group temp where groupCode = &#63; and groupId = &#63; and dictCollectionId = &#63; or throws a {@link NoSuchDictGroupTempException} if it could not be found.
	*
	* @param groupCode the group code
	* @param groupId the group ID
	* @param dictCollectionId the dict collection ID
	* @return the matching dict group temp
	* @throws NoSuchDictGroupTempException if a matching dict group temp could not be found
	*/
	public static DictGroupTemp findByGC_GI_DCI(String groupCode, long groupId,
		long dictCollectionId)
		throws org.opencps.synchronization.exception.NoSuchDictGroupTempException {
		return getPersistence()
				   .findByGC_GI_DCI(groupCode, groupId, dictCollectionId);
	}

	/**
	* Returns the dict group temp where groupCode = &#63; and groupId = &#63; and dictCollectionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupCode the group code
	* @param groupId the group ID
	* @param dictCollectionId the dict collection ID
	* @return the matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	*/
	public static DictGroupTemp fetchByGC_GI_DCI(String groupCode,
		long groupId, long dictCollectionId) {
		return getPersistence()
				   .fetchByGC_GI_DCI(groupCode, groupId, dictCollectionId);
	}

	/**
	* Returns the dict group temp where groupCode = &#63; and groupId = &#63; and dictCollectionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupCode the group code
	* @param groupId the group ID
	* @param dictCollectionId the dict collection ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	*/
	public static DictGroupTemp fetchByGC_GI_DCI(String groupCode,
		long groupId, long dictCollectionId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByGC_GI_DCI(groupCode, groupId, dictCollectionId,
			retrieveFromCache);
	}

	/**
	* Removes the dict group temp where groupCode = &#63; and groupId = &#63; and dictCollectionId = &#63; from the database.
	*
	* @param groupCode the group code
	* @param groupId the group ID
	* @param dictCollectionId the dict collection ID
	* @return the dict group temp that was removed
	*/
	public static DictGroupTemp removeByGC_GI_DCI(String groupCode,
		long groupId, long dictCollectionId)
		throws org.opencps.synchronization.exception.NoSuchDictGroupTempException {
		return getPersistence()
				   .removeByGC_GI_DCI(groupCode, groupId, dictCollectionId);
	}

	/**
	* Returns the number of dict group temps where groupCode = &#63; and groupId = &#63; and dictCollectionId = &#63;.
	*
	* @param groupCode the group code
	* @param groupId the group ID
	* @param dictCollectionId the dict collection ID
	* @return the number of matching dict group temps
	*/
	public static int countByGC_GI_DCI(String groupCode, long groupId,
		long dictCollectionId) {
		return getPersistence()
				   .countByGC_GI_DCI(groupCode, groupId, dictCollectionId);
	}

	/**
	* Returns all the dict group temps where dictCollectionId = &#63; and groupId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @return the matching dict group temps
	*/
	public static List<DictGroupTemp> findByGID_DC(long dictCollectionId,
		long groupId) {
		return getPersistence().findByGID_DC(dictCollectionId, groupId);
	}

	/**
	* Returns a range of all the dict group temps where dictCollectionId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @param start the lower bound of the range of dict group temps
	* @param end the upper bound of the range of dict group temps (not inclusive)
	* @return the range of matching dict group temps
	*/
	public static List<DictGroupTemp> findByGID_DC(long dictCollectionId,
		long groupId, int start, int end) {
		return getPersistence()
				   .findByGID_DC(dictCollectionId, groupId, start, end);
	}

	/**
	* Returns an ordered range of all the dict group temps where dictCollectionId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @param start the lower bound of the range of dict group temps
	* @param end the upper bound of the range of dict group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict group temps
	*/
	public static List<DictGroupTemp> findByGID_DC(long dictCollectionId,
		long groupId, int start, int end,
		OrderByComparator<DictGroupTemp> orderByComparator) {
		return getPersistence()
				   .findByGID_DC(dictCollectionId, groupId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dict group temps where dictCollectionId = &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @param start the lower bound of the range of dict group temps
	* @param end the upper bound of the range of dict group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict group temps
	*/
	public static List<DictGroupTemp> findByGID_DC(long dictCollectionId,
		long groupId, int start, int end,
		OrderByComparator<DictGroupTemp> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByGID_DC(dictCollectionId, groupId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dict group temp in the ordered set where dictCollectionId = &#63; and groupId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict group temp
	* @throws NoSuchDictGroupTempException if a matching dict group temp could not be found
	*/
	public static DictGroupTemp findByGID_DC_First(long dictCollectionId,
		long groupId, OrderByComparator<DictGroupTemp> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchDictGroupTempException {
		return getPersistence()
				   .findByGID_DC_First(dictCollectionId, groupId,
			orderByComparator);
	}

	/**
	* Returns the first dict group temp in the ordered set where dictCollectionId = &#63; and groupId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	*/
	public static DictGroupTemp fetchByGID_DC_First(long dictCollectionId,
		long groupId, OrderByComparator<DictGroupTemp> orderByComparator) {
		return getPersistence()
				   .fetchByGID_DC_First(dictCollectionId, groupId,
			orderByComparator);
	}

	/**
	* Returns the last dict group temp in the ordered set where dictCollectionId = &#63; and groupId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict group temp
	* @throws NoSuchDictGroupTempException if a matching dict group temp could not be found
	*/
	public static DictGroupTemp findByGID_DC_Last(long dictCollectionId,
		long groupId, OrderByComparator<DictGroupTemp> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchDictGroupTempException {
		return getPersistence()
				   .findByGID_DC_Last(dictCollectionId, groupId,
			orderByComparator);
	}

	/**
	* Returns the last dict group temp in the ordered set where dictCollectionId = &#63; and groupId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	*/
	public static DictGroupTemp fetchByGID_DC_Last(long dictCollectionId,
		long groupId, OrderByComparator<DictGroupTemp> orderByComparator) {
		return getPersistence()
				   .fetchByGID_DC_Last(dictCollectionId, groupId,
			orderByComparator);
	}

	/**
	* Returns the dict group temps before and after the current dict group temp in the ordered set where dictCollectionId = &#63; and groupId = &#63;.
	*
	* @param dictGroupId the primary key of the current dict group temp
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict group temp
	* @throws NoSuchDictGroupTempException if a dict group temp with the primary key could not be found
	*/
	public static DictGroupTemp[] findByGID_DC_PrevAndNext(long dictGroupId,
		long dictCollectionId, long groupId,
		OrderByComparator<DictGroupTemp> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchDictGroupTempException {
		return getPersistence()
				   .findByGID_DC_PrevAndNext(dictGroupId, dictCollectionId,
			groupId, orderByComparator);
	}

	/**
	* Removes all the dict group temps where dictCollectionId = &#63; and groupId = &#63; from the database.
	*
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	*/
	public static void removeByGID_DC(long dictCollectionId, long groupId) {
		getPersistence().removeByGID_DC(dictCollectionId, groupId);
	}

	/**
	* Returns the number of dict group temps where dictCollectionId = &#63; and groupId = &#63;.
	*
	* @param dictCollectionId the dict collection ID
	* @param groupId the group ID
	* @return the number of matching dict group temps
	*/
	public static int countByGID_DC(long dictCollectionId, long groupId) {
		return getPersistence().countByGID_DC(dictCollectionId, groupId);
	}

	/**
	* Returns all the dict group temps where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @return the matching dict group temps
	*/
	public static List<DictGroupTemp> findByF_dictGroupNewerThan(
		Date modifiedDate, long groupId) {
		return getPersistence().findByF_dictGroupNewerThan(modifiedDate, groupId);
	}

	/**
	* Returns a range of all the dict group temps where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param start the lower bound of the range of dict group temps
	* @param end the upper bound of the range of dict group temps (not inclusive)
	* @return the range of matching dict group temps
	*/
	public static List<DictGroupTemp> findByF_dictGroupNewerThan(
		Date modifiedDate, long groupId, int start, int end) {
		return getPersistence()
				   .findByF_dictGroupNewerThan(modifiedDate, groupId, start, end);
	}

	/**
	* Returns an ordered range of all the dict group temps where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param start the lower bound of the range of dict group temps
	* @param end the upper bound of the range of dict group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict group temps
	*/
	public static List<DictGroupTemp> findByF_dictGroupNewerThan(
		Date modifiedDate, long groupId, int start, int end,
		OrderByComparator<DictGroupTemp> orderByComparator) {
		return getPersistence()
				   .findByF_dictGroupNewerThan(modifiedDate, groupId, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dict group temps where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param start the lower bound of the range of dict group temps
	* @param end the upper bound of the range of dict group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict group temps
	*/
	public static List<DictGroupTemp> findByF_dictGroupNewerThan(
		Date modifiedDate, long groupId, int start, int end,
		OrderByComparator<DictGroupTemp> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_dictGroupNewerThan(modifiedDate, groupId, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dict group temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict group temp
	* @throws NoSuchDictGroupTempException if a matching dict group temp could not be found
	*/
	public static DictGroupTemp findByF_dictGroupNewerThan_First(
		Date modifiedDate, long groupId,
		OrderByComparator<DictGroupTemp> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchDictGroupTempException {
		return getPersistence()
				   .findByF_dictGroupNewerThan_First(modifiedDate, groupId,
			orderByComparator);
	}

	/**
	* Returns the first dict group temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	*/
	public static DictGroupTemp fetchByF_dictGroupNewerThan_First(
		Date modifiedDate, long groupId,
		OrderByComparator<DictGroupTemp> orderByComparator) {
		return getPersistence()
				   .fetchByF_dictGroupNewerThan_First(modifiedDate, groupId,
			orderByComparator);
	}

	/**
	* Returns the last dict group temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict group temp
	* @throws NoSuchDictGroupTempException if a matching dict group temp could not be found
	*/
	public static DictGroupTemp findByF_dictGroupNewerThan_Last(
		Date modifiedDate, long groupId,
		OrderByComparator<DictGroupTemp> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchDictGroupTempException {
		return getPersistence()
				   .findByF_dictGroupNewerThan_Last(modifiedDate, groupId,
			orderByComparator);
	}

	/**
	* Returns the last dict group temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	*/
	public static DictGroupTemp fetchByF_dictGroupNewerThan_Last(
		Date modifiedDate, long groupId,
		OrderByComparator<DictGroupTemp> orderByComparator) {
		return getPersistence()
				   .fetchByF_dictGroupNewerThan_Last(modifiedDate, groupId,
			orderByComparator);
	}

	/**
	* Returns the dict group temps before and after the current dict group temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param dictGroupId the primary key of the current dict group temp
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict group temp
	* @throws NoSuchDictGroupTempException if a dict group temp with the primary key could not be found
	*/
	public static DictGroupTemp[] findByF_dictGroupNewerThan_PrevAndNext(
		long dictGroupId, Date modifiedDate, long groupId,
		OrderByComparator<DictGroupTemp> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchDictGroupTempException {
		return getPersistence()
				   .findByF_dictGroupNewerThan_PrevAndNext(dictGroupId,
			modifiedDate, groupId, orderByComparator);
	}

	/**
	* Removes all the dict group temps where modifiedDate &ge; &#63; and groupId = &#63; from the database.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	*/
	public static void removeByF_dictGroupNewerThan(Date modifiedDate,
		long groupId) {
		getPersistence().removeByF_dictGroupNewerThan(modifiedDate, groupId);
	}

	/**
	* Returns the number of dict group temps where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @return the number of matching dict group temps
	*/
	public static int countByF_dictGroupNewerThan(Date modifiedDate,
		long groupId) {
		return getPersistence()
				   .countByF_dictGroupNewerThan(modifiedDate, groupId);
	}

	/**
	* Caches the dict group temp in the entity cache if it is enabled.
	*
	* @param dictGroupTemp the dict group temp
	*/
	public static void cacheResult(DictGroupTemp dictGroupTemp) {
		getPersistence().cacheResult(dictGroupTemp);
	}

	/**
	* Caches the dict group temps in the entity cache if it is enabled.
	*
	* @param dictGroupTemps the dict group temps
	*/
	public static void cacheResult(List<DictGroupTemp> dictGroupTemps) {
		getPersistence().cacheResult(dictGroupTemps);
	}

	/**
	* Creates a new dict group temp with the primary key. Does not add the dict group temp to the database.
	*
	* @param dictGroupId the primary key for the new dict group temp
	* @return the new dict group temp
	*/
	public static DictGroupTemp create(long dictGroupId) {
		return getPersistence().create(dictGroupId);
	}

	/**
	* Removes the dict group temp with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dictGroupId the primary key of the dict group temp
	* @return the dict group temp that was removed
	* @throws NoSuchDictGroupTempException if a dict group temp with the primary key could not be found
	*/
	public static DictGroupTemp remove(long dictGroupId)
		throws org.opencps.synchronization.exception.NoSuchDictGroupTempException {
		return getPersistence().remove(dictGroupId);
	}

	public static DictGroupTemp updateImpl(DictGroupTemp dictGroupTemp) {
		return getPersistence().updateImpl(dictGroupTemp);
	}

	/**
	* Returns the dict group temp with the primary key or throws a {@link NoSuchDictGroupTempException} if it could not be found.
	*
	* @param dictGroupId the primary key of the dict group temp
	* @return the dict group temp
	* @throws NoSuchDictGroupTempException if a dict group temp with the primary key could not be found
	*/
	public static DictGroupTemp findByPrimaryKey(long dictGroupId)
		throws org.opencps.synchronization.exception.NoSuchDictGroupTempException {
		return getPersistence().findByPrimaryKey(dictGroupId);
	}

	/**
	* Returns the dict group temp with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dictGroupId the primary key of the dict group temp
	* @return the dict group temp, or <code>null</code> if a dict group temp with the primary key could not be found
	*/
	public static DictGroupTemp fetchByPrimaryKey(long dictGroupId) {
		return getPersistence().fetchByPrimaryKey(dictGroupId);
	}

	public static java.util.Map<java.io.Serializable, DictGroupTemp> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the dict group temps.
	*
	* @return the dict group temps
	*/
	public static List<DictGroupTemp> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the dict group temps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict group temps
	* @param end the upper bound of the range of dict group temps (not inclusive)
	* @return the range of dict group temps
	*/
	public static List<DictGroupTemp> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the dict group temps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict group temps
	* @param end the upper bound of the range of dict group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dict group temps
	*/
	public static List<DictGroupTemp> findAll(int start, int end,
		OrderByComparator<DictGroupTemp> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dict group temps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict group temps
	* @param end the upper bound of the range of dict group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dict group temps
	*/
	public static List<DictGroupTemp> findAll(int start, int end,
		OrderByComparator<DictGroupTemp> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the dict group temps from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of dict group temps.
	*
	* @return the number of dict group temps
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static DictGroupTempPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DictGroupTempPersistence, DictGroupTempPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DictGroupTempPersistence.class);

		ServiceTracker<DictGroupTempPersistence, DictGroupTempPersistence> serviceTracker =
			new ServiceTracker<DictGroupTempPersistence, DictGroupTempPersistence>(bundle.getBundleContext(),
				DictGroupTempPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}