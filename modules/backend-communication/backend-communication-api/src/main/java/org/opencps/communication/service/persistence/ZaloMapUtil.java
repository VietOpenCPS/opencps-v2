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

package org.opencps.communication.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.communication.model.ZaloMap;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the zalo map service. This utility wraps {@link org.opencps.communication.service.persistence.impl.ZaloMapPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavd
 * @see ZaloMapPersistence
 * @see org.opencps.communication.service.persistence.impl.ZaloMapPersistenceImpl
 * @generated
 */
@ProviderType
public class ZaloMapUtil {
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
	public static void clearCache(ZaloMap zaloMap) {
		getPersistence().clearCache(zaloMap);
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
	public static List<ZaloMap> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ZaloMap> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ZaloMap> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ZaloMap> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ZaloMap update(ZaloMap zaloMap) {
		return getPersistence().update(zaloMap);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ZaloMap update(ZaloMap zaloMap, ServiceContext serviceContext) {
		return getPersistence().update(zaloMap, serviceContext);
	}

	/**
	* Returns the zalo map where uId = &#63; or throws a {@link NoSuchZaloMapException} if it could not be found.
	*
	* @param uId the u ID
	* @return the matching zalo map
	* @throws NoSuchZaloMapException if a matching zalo map could not be found
	*/
	public static ZaloMap findByF_UId(String uId)
		throws org.opencps.communication.exception.NoSuchZaloMapException {
		return getPersistence().findByF_UId(uId);
	}

	/**
	* Returns the zalo map where uId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uId the u ID
	* @return the matching zalo map, or <code>null</code> if a matching zalo map could not be found
	*/
	public static ZaloMap fetchByF_UId(String uId) {
		return getPersistence().fetchByF_UId(uId);
	}

	/**
	* Returns the zalo map where uId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uId the u ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching zalo map, or <code>null</code> if a matching zalo map could not be found
	*/
	public static ZaloMap fetchByF_UId(String uId, boolean retrieveFromCache) {
		return getPersistence().fetchByF_UId(uId, retrieveFromCache);
	}

	/**
	* Removes the zalo map where uId = &#63; from the database.
	*
	* @param uId the u ID
	* @return the zalo map that was removed
	*/
	public static ZaloMap removeByF_UId(String uId)
		throws org.opencps.communication.exception.NoSuchZaloMapException {
		return getPersistence().removeByF_UId(uId);
	}

	/**
	* Returns the number of zalo maps where uId = &#63;.
	*
	* @param uId the u ID
	* @return the number of matching zalo maps
	*/
	public static int countByF_UId(String uId) {
		return getPersistence().countByF_UId(uId);
	}

	/**
	* Returns the zalo map where telNo = &#63; or throws a {@link NoSuchZaloMapException} if it could not be found.
	*
	* @param telNo the tel no
	* @return the matching zalo map
	* @throws NoSuchZaloMapException if a matching zalo map could not be found
	*/
	public static ZaloMap findByF_TelNo(String telNo)
		throws org.opencps.communication.exception.NoSuchZaloMapException {
		return getPersistence().findByF_TelNo(telNo);
	}

	/**
	* Returns the zalo map where telNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param telNo the tel no
	* @return the matching zalo map, or <code>null</code> if a matching zalo map could not be found
	*/
	public static ZaloMap fetchByF_TelNo(String telNo) {
		return getPersistence().fetchByF_TelNo(telNo);
	}

	/**
	* Returns the zalo map where telNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param telNo the tel no
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching zalo map, or <code>null</code> if a matching zalo map could not be found
	*/
	public static ZaloMap fetchByF_TelNo(String telNo, boolean retrieveFromCache) {
		return getPersistence().fetchByF_TelNo(telNo, retrieveFromCache);
	}

	/**
	* Removes the zalo map where telNo = &#63; from the database.
	*
	* @param telNo the tel no
	* @return the zalo map that was removed
	*/
	public static ZaloMap removeByF_TelNo(String telNo)
		throws org.opencps.communication.exception.NoSuchZaloMapException {
		return getPersistence().removeByF_TelNo(telNo);
	}

	/**
	* Returns the number of zalo maps where telNo = &#63;.
	*
	* @param telNo the tel no
	* @return the number of matching zalo maps
	*/
	public static int countByF_TelNo(String telNo) {
		return getPersistence().countByF_TelNo(telNo);
	}

	/**
	* Returns all the zalo maps where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching zalo maps
	*/
	public static List<ZaloMap> findByF_GID(long groupId) {
		return getPersistence().findByF_GID(groupId);
	}

	/**
	* Returns a range of all the zalo maps where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZaloMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of zalo maps
	* @param end the upper bound of the range of zalo maps (not inclusive)
	* @return the range of matching zalo maps
	*/
	public static List<ZaloMap> findByF_GID(long groupId, int start, int end) {
		return getPersistence().findByF_GID(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the zalo maps where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZaloMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of zalo maps
	* @param end the upper bound of the range of zalo maps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching zalo maps
	*/
	public static List<ZaloMap> findByF_GID(long groupId, int start, int end,
		OrderByComparator<ZaloMap> orderByComparator) {
		return getPersistence()
				   .findByF_GID(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the zalo maps where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZaloMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of zalo maps
	* @param end the upper bound of the range of zalo maps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching zalo maps
	*/
	public static List<ZaloMap> findByF_GID(long groupId, int start, int end,
		OrderByComparator<ZaloMap> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_GID(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first zalo map in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching zalo map
	* @throws NoSuchZaloMapException if a matching zalo map could not be found
	*/
	public static ZaloMap findByF_GID_First(long groupId,
		OrderByComparator<ZaloMap> orderByComparator)
		throws org.opencps.communication.exception.NoSuchZaloMapException {
		return getPersistence().findByF_GID_First(groupId, orderByComparator);
	}

	/**
	* Returns the first zalo map in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching zalo map, or <code>null</code> if a matching zalo map could not be found
	*/
	public static ZaloMap fetchByF_GID_First(long groupId,
		OrderByComparator<ZaloMap> orderByComparator) {
		return getPersistence().fetchByF_GID_First(groupId, orderByComparator);
	}

	/**
	* Returns the last zalo map in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching zalo map
	* @throws NoSuchZaloMapException if a matching zalo map could not be found
	*/
	public static ZaloMap findByF_GID_Last(long groupId,
		OrderByComparator<ZaloMap> orderByComparator)
		throws org.opencps.communication.exception.NoSuchZaloMapException {
		return getPersistence().findByF_GID_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last zalo map in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching zalo map, or <code>null</code> if a matching zalo map could not be found
	*/
	public static ZaloMap fetchByF_GID_Last(long groupId,
		OrderByComparator<ZaloMap> orderByComparator) {
		return getPersistence().fetchByF_GID_Last(groupId, orderByComparator);
	}

	/**
	* Returns the zalo maps before and after the current zalo map in the ordered set where groupId = &#63;.
	*
	* @param zaloMapId the primary key of the current zalo map
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next zalo map
	* @throws NoSuchZaloMapException if a zalo map with the primary key could not be found
	*/
	public static ZaloMap[] findByF_GID_PrevAndNext(long zaloMapId,
		long groupId, OrderByComparator<ZaloMap> orderByComparator)
		throws org.opencps.communication.exception.NoSuchZaloMapException {
		return getPersistence()
				   .findByF_GID_PrevAndNext(zaloMapId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the zalo maps where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByF_GID(long groupId) {
		getPersistence().removeByF_GID(groupId);
	}

	/**
	* Returns the number of zalo maps where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching zalo maps
	*/
	public static int countByF_GID(long groupId) {
		return getPersistence().countByF_GID(groupId);
	}

	/**
	* Returns all the zalo maps where zaloOAId = &#63;.
	*
	* @param zaloOAId the zalo oa ID
	* @return the matching zalo maps
	*/
	public static List<ZaloMap> findByF_ZALO_OAID(String zaloOAId) {
		return getPersistence().findByF_ZALO_OAID(zaloOAId);
	}

	/**
	* Returns a range of all the zalo maps where zaloOAId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZaloMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param zaloOAId the zalo oa ID
	* @param start the lower bound of the range of zalo maps
	* @param end the upper bound of the range of zalo maps (not inclusive)
	* @return the range of matching zalo maps
	*/
	public static List<ZaloMap> findByF_ZALO_OAID(String zaloOAId, int start,
		int end) {
		return getPersistence().findByF_ZALO_OAID(zaloOAId, start, end);
	}

	/**
	* Returns an ordered range of all the zalo maps where zaloOAId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZaloMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param zaloOAId the zalo oa ID
	* @param start the lower bound of the range of zalo maps
	* @param end the upper bound of the range of zalo maps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching zalo maps
	*/
	public static List<ZaloMap> findByF_ZALO_OAID(String zaloOAId, int start,
		int end, OrderByComparator<ZaloMap> orderByComparator) {
		return getPersistence()
				   .findByF_ZALO_OAID(zaloOAId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the zalo maps where zaloOAId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZaloMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param zaloOAId the zalo oa ID
	* @param start the lower bound of the range of zalo maps
	* @param end the upper bound of the range of zalo maps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching zalo maps
	*/
	public static List<ZaloMap> findByF_ZALO_OAID(String zaloOAId, int start,
		int end, OrderByComparator<ZaloMap> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_ZALO_OAID(zaloOAId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first zalo map in the ordered set where zaloOAId = &#63;.
	*
	* @param zaloOAId the zalo oa ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching zalo map
	* @throws NoSuchZaloMapException if a matching zalo map could not be found
	*/
	public static ZaloMap findByF_ZALO_OAID_First(String zaloOAId,
		OrderByComparator<ZaloMap> orderByComparator)
		throws org.opencps.communication.exception.NoSuchZaloMapException {
		return getPersistence()
				   .findByF_ZALO_OAID_First(zaloOAId, orderByComparator);
	}

	/**
	* Returns the first zalo map in the ordered set where zaloOAId = &#63;.
	*
	* @param zaloOAId the zalo oa ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching zalo map, or <code>null</code> if a matching zalo map could not be found
	*/
	public static ZaloMap fetchByF_ZALO_OAID_First(String zaloOAId,
		OrderByComparator<ZaloMap> orderByComparator) {
		return getPersistence()
				   .fetchByF_ZALO_OAID_First(zaloOAId, orderByComparator);
	}

	/**
	* Returns the last zalo map in the ordered set where zaloOAId = &#63;.
	*
	* @param zaloOAId the zalo oa ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching zalo map
	* @throws NoSuchZaloMapException if a matching zalo map could not be found
	*/
	public static ZaloMap findByF_ZALO_OAID_Last(String zaloOAId,
		OrderByComparator<ZaloMap> orderByComparator)
		throws org.opencps.communication.exception.NoSuchZaloMapException {
		return getPersistence()
				   .findByF_ZALO_OAID_Last(zaloOAId, orderByComparator);
	}

	/**
	* Returns the last zalo map in the ordered set where zaloOAId = &#63;.
	*
	* @param zaloOAId the zalo oa ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching zalo map, or <code>null</code> if a matching zalo map could not be found
	*/
	public static ZaloMap fetchByF_ZALO_OAID_Last(String zaloOAId,
		OrderByComparator<ZaloMap> orderByComparator) {
		return getPersistence()
				   .fetchByF_ZALO_OAID_Last(zaloOAId, orderByComparator);
	}

	/**
	* Returns the zalo maps before and after the current zalo map in the ordered set where zaloOAId = &#63;.
	*
	* @param zaloMapId the primary key of the current zalo map
	* @param zaloOAId the zalo oa ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next zalo map
	* @throws NoSuchZaloMapException if a zalo map with the primary key could not be found
	*/
	public static ZaloMap[] findByF_ZALO_OAID_PrevAndNext(long zaloMapId,
		String zaloOAId, OrderByComparator<ZaloMap> orderByComparator)
		throws org.opencps.communication.exception.NoSuchZaloMapException {
		return getPersistence()
				   .findByF_ZALO_OAID_PrevAndNext(zaloMapId, zaloOAId,
			orderByComparator);
	}

	/**
	* Removes all the zalo maps where zaloOAId = &#63; from the database.
	*
	* @param zaloOAId the zalo oa ID
	*/
	public static void removeByF_ZALO_OAID(String zaloOAId) {
		getPersistence().removeByF_ZALO_OAID(zaloOAId);
	}

	/**
	* Returns the number of zalo maps where zaloOAId = &#63;.
	*
	* @param zaloOAId the zalo oa ID
	* @return the number of matching zalo maps
	*/
	public static int countByF_ZALO_OAID(String zaloOAId) {
		return getPersistence().countByF_ZALO_OAID(zaloOAId);
	}

	/**
	* Caches the zalo map in the entity cache if it is enabled.
	*
	* @param zaloMap the zalo map
	*/
	public static void cacheResult(ZaloMap zaloMap) {
		getPersistence().cacheResult(zaloMap);
	}

	/**
	* Caches the zalo maps in the entity cache if it is enabled.
	*
	* @param zaloMaps the zalo maps
	*/
	public static void cacheResult(List<ZaloMap> zaloMaps) {
		getPersistence().cacheResult(zaloMaps);
	}

	/**
	* Creates a new zalo map with the primary key. Does not add the zalo map to the database.
	*
	* @param zaloMapId the primary key for the new zalo map
	* @return the new zalo map
	*/
	public static ZaloMap create(long zaloMapId) {
		return getPersistence().create(zaloMapId);
	}

	/**
	* Removes the zalo map with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param zaloMapId the primary key of the zalo map
	* @return the zalo map that was removed
	* @throws NoSuchZaloMapException if a zalo map with the primary key could not be found
	*/
	public static ZaloMap remove(long zaloMapId)
		throws org.opencps.communication.exception.NoSuchZaloMapException {
		return getPersistence().remove(zaloMapId);
	}

	public static ZaloMap updateImpl(ZaloMap zaloMap) {
		return getPersistence().updateImpl(zaloMap);
	}

	/**
	* Returns the zalo map with the primary key or throws a {@link NoSuchZaloMapException} if it could not be found.
	*
	* @param zaloMapId the primary key of the zalo map
	* @return the zalo map
	* @throws NoSuchZaloMapException if a zalo map with the primary key could not be found
	*/
	public static ZaloMap findByPrimaryKey(long zaloMapId)
		throws org.opencps.communication.exception.NoSuchZaloMapException {
		return getPersistence().findByPrimaryKey(zaloMapId);
	}

	/**
	* Returns the zalo map with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param zaloMapId the primary key of the zalo map
	* @return the zalo map, or <code>null</code> if a zalo map with the primary key could not be found
	*/
	public static ZaloMap fetchByPrimaryKey(long zaloMapId) {
		return getPersistence().fetchByPrimaryKey(zaloMapId);
	}

	public static java.util.Map<java.io.Serializable, ZaloMap> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the zalo maps.
	*
	* @return the zalo maps
	*/
	public static List<ZaloMap> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the zalo maps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZaloMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of zalo maps
	* @param end the upper bound of the range of zalo maps (not inclusive)
	* @return the range of zalo maps
	*/
	public static List<ZaloMap> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the zalo maps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZaloMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of zalo maps
	* @param end the upper bound of the range of zalo maps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of zalo maps
	*/
	public static List<ZaloMap> findAll(int start, int end,
		OrderByComparator<ZaloMap> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the zalo maps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZaloMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of zalo maps
	* @param end the upper bound of the range of zalo maps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of zalo maps
	*/
	public static List<ZaloMap> findAll(int start, int end,
		OrderByComparator<ZaloMap> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the zalo maps from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of zalo maps.
	*
	* @return the number of zalo maps
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ZaloMapPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ZaloMapPersistence, ZaloMapPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ZaloMapPersistence.class);

		ServiceTracker<ZaloMapPersistence, ZaloMapPersistence> serviceTracker = new ServiceTracker<ZaloMapPersistence, ZaloMapPersistence>(bundle.getBundleContext(),
				ZaloMapPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}