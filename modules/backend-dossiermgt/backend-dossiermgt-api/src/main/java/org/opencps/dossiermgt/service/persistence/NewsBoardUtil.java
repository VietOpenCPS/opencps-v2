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

package org.opencps.dossiermgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.dossiermgt.model.NewsBoard;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the news board service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.NewsBoardPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see NewsBoardPersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.NewsBoardPersistenceImpl
 * @generated
 */
@ProviderType
public class NewsBoardUtil {
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
	public static void clearCache(NewsBoard newsBoard) {
		getPersistence().clearCache(newsBoard);
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
	public static List<NewsBoard> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<NewsBoard> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<NewsBoard> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<NewsBoard> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static NewsBoard update(NewsBoard newsBoard) {
		return getPersistence().update(newsBoard);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static NewsBoard update(NewsBoard newsBoard,
		ServiceContext serviceContext) {
		return getPersistence().update(newsBoard, serviceContext);
	}

	/**
	* Returns all the news boards where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching news boards
	*/
	public static List<NewsBoard> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the news boards where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NewsBoardModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of news boards
	* @param end the upper bound of the range of news boards (not inclusive)
	* @return the range of matching news boards
	*/
	public static List<NewsBoard> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the news boards where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NewsBoardModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of news boards
	* @param end the upper bound of the range of news boards (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching news boards
	*/
	public static List<NewsBoard> findByUuid(String uuid, int start, int end,
		OrderByComparator<NewsBoard> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the news boards where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NewsBoardModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of news boards
	* @param end the upper bound of the range of news boards (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching news boards
	*/
	public static List<NewsBoard> findByUuid(String uuid, int start, int end,
		OrderByComparator<NewsBoard> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first news board in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching news board
	* @throws NoSuchNewsBoardException if a matching news board could not be found
	*/
	public static NewsBoard findByUuid_First(String uuid,
		OrderByComparator<NewsBoard> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchNewsBoardException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first news board in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching news board, or <code>null</code> if a matching news board could not be found
	*/
	public static NewsBoard fetchByUuid_First(String uuid,
		OrderByComparator<NewsBoard> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last news board in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching news board
	* @throws NoSuchNewsBoardException if a matching news board could not be found
	*/
	public static NewsBoard findByUuid_Last(String uuid,
		OrderByComparator<NewsBoard> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchNewsBoardException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last news board in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching news board, or <code>null</code> if a matching news board could not be found
	*/
	public static NewsBoard fetchByUuid_Last(String uuid,
		OrderByComparator<NewsBoard> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the news boards before and after the current news board in the ordered set where uuid = &#63;.
	*
	* @param newsBoardId the primary key of the current news board
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next news board
	* @throws NoSuchNewsBoardException if a news board with the primary key could not be found
	*/
	public static NewsBoard[] findByUuid_PrevAndNext(long newsBoardId,
		String uuid, OrderByComparator<NewsBoard> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchNewsBoardException {
		return getPersistence()
				   .findByUuid_PrevAndNext(newsBoardId, uuid, orderByComparator);
	}

	/**
	* Removes all the news boards where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of news boards where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching news boards
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the news board where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchNewsBoardException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching news board
	* @throws NoSuchNewsBoardException if a matching news board could not be found
	*/
	public static NewsBoard findByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchNewsBoardException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the news board where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching news board, or <code>null</code> if a matching news board could not be found
	*/
	public static NewsBoard fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the news board where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching news board, or <code>null</code> if a matching news board could not be found
	*/
	public static NewsBoard fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the news board where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the news board that was removed
	*/
	public static NewsBoard removeByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchNewsBoardException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of news boards where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching news boards
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the news boards where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching news boards
	*/
	public static List<NewsBoard> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the news boards where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NewsBoardModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of news boards
	* @param end the upper bound of the range of news boards (not inclusive)
	* @return the range of matching news boards
	*/
	public static List<NewsBoard> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the news boards where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NewsBoardModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of news boards
	* @param end the upper bound of the range of news boards (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching news boards
	*/
	public static List<NewsBoard> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<NewsBoard> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the news boards where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NewsBoardModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of news boards
	* @param end the upper bound of the range of news boards (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching news boards
	*/
	public static List<NewsBoard> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<NewsBoard> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first news board in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching news board
	* @throws NoSuchNewsBoardException if a matching news board could not be found
	*/
	public static NewsBoard findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<NewsBoard> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchNewsBoardException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first news board in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching news board, or <code>null</code> if a matching news board could not be found
	*/
	public static NewsBoard fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<NewsBoard> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last news board in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching news board
	* @throws NoSuchNewsBoardException if a matching news board could not be found
	*/
	public static NewsBoard findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<NewsBoard> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchNewsBoardException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last news board in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching news board, or <code>null</code> if a matching news board could not be found
	*/
	public static NewsBoard fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<NewsBoard> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the news boards before and after the current news board in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param newsBoardId the primary key of the current news board
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next news board
	* @throws NoSuchNewsBoardException if a news board with the primary key could not be found
	*/
	public static NewsBoard[] findByUuid_C_PrevAndNext(long newsBoardId,
		String uuid, long companyId,
		OrderByComparator<NewsBoard> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchNewsBoardException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(newsBoardId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the news boards where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of news boards where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching news boards
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the news boards where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching news boards
	*/
	public static List<NewsBoard> findByF_GID(long groupId) {
		return getPersistence().findByF_GID(groupId);
	}

	/**
	* Returns a range of all the news boards where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NewsBoardModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of news boards
	* @param end the upper bound of the range of news boards (not inclusive)
	* @return the range of matching news boards
	*/
	public static List<NewsBoard> findByF_GID(long groupId, int start, int end) {
		return getPersistence().findByF_GID(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the news boards where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NewsBoardModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of news boards
	* @param end the upper bound of the range of news boards (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching news boards
	*/
	public static List<NewsBoard> findByF_GID(long groupId, int start, int end,
		OrderByComparator<NewsBoard> orderByComparator) {
		return getPersistence()
				   .findByF_GID(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the news boards where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NewsBoardModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of news boards
	* @param end the upper bound of the range of news boards (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching news boards
	*/
	public static List<NewsBoard> findByF_GID(long groupId, int start, int end,
		OrderByComparator<NewsBoard> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_GID(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first news board in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching news board
	* @throws NoSuchNewsBoardException if a matching news board could not be found
	*/
	public static NewsBoard findByF_GID_First(long groupId,
		OrderByComparator<NewsBoard> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchNewsBoardException {
		return getPersistence().findByF_GID_First(groupId, orderByComparator);
	}

	/**
	* Returns the first news board in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching news board, or <code>null</code> if a matching news board could not be found
	*/
	public static NewsBoard fetchByF_GID_First(long groupId,
		OrderByComparator<NewsBoard> orderByComparator) {
		return getPersistence().fetchByF_GID_First(groupId, orderByComparator);
	}

	/**
	* Returns the last news board in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching news board
	* @throws NoSuchNewsBoardException if a matching news board could not be found
	*/
	public static NewsBoard findByF_GID_Last(long groupId,
		OrderByComparator<NewsBoard> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchNewsBoardException {
		return getPersistence().findByF_GID_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last news board in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching news board, or <code>null</code> if a matching news board could not be found
	*/
	public static NewsBoard fetchByF_GID_Last(long groupId,
		OrderByComparator<NewsBoard> orderByComparator) {
		return getPersistence().fetchByF_GID_Last(groupId, orderByComparator);
	}

	/**
	* Returns the news boards before and after the current news board in the ordered set where groupId = &#63;.
	*
	* @param newsBoardId the primary key of the current news board
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next news board
	* @throws NoSuchNewsBoardException if a news board with the primary key could not be found
	*/
	public static NewsBoard[] findByF_GID_PrevAndNext(long newsBoardId,
		long groupId, OrderByComparator<NewsBoard> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchNewsBoardException {
		return getPersistence()
				   .findByF_GID_PrevAndNext(newsBoardId, groupId,
			orderByComparator);
	}

	/**
	* Removes all the news boards where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByF_GID(long groupId) {
		getPersistence().removeByF_GID(groupId);
	}

	/**
	* Returns the number of news boards where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching news boards
	*/
	public static int countByF_GID(long groupId) {
		return getPersistence().countByF_GID(groupId);
	}

	/**
	* Caches the news board in the entity cache if it is enabled.
	*
	* @param newsBoard the news board
	*/
	public static void cacheResult(NewsBoard newsBoard) {
		getPersistence().cacheResult(newsBoard);
	}

	/**
	* Caches the news boards in the entity cache if it is enabled.
	*
	* @param newsBoards the news boards
	*/
	public static void cacheResult(List<NewsBoard> newsBoards) {
		getPersistence().cacheResult(newsBoards);
	}

	/**
	* Creates a new news board with the primary key. Does not add the news board to the database.
	*
	* @param newsBoardId the primary key for the new news board
	* @return the new news board
	*/
	public static NewsBoard create(long newsBoardId) {
		return getPersistence().create(newsBoardId);
	}

	/**
	* Removes the news board with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param newsBoardId the primary key of the news board
	* @return the news board that was removed
	* @throws NoSuchNewsBoardException if a news board with the primary key could not be found
	*/
	public static NewsBoard remove(long newsBoardId)
		throws org.opencps.dossiermgt.exception.NoSuchNewsBoardException {
		return getPersistence().remove(newsBoardId);
	}

	public static NewsBoard updateImpl(NewsBoard newsBoard) {
		return getPersistence().updateImpl(newsBoard);
	}

	/**
	* Returns the news board with the primary key or throws a {@link NoSuchNewsBoardException} if it could not be found.
	*
	* @param newsBoardId the primary key of the news board
	* @return the news board
	* @throws NoSuchNewsBoardException if a news board with the primary key could not be found
	*/
	public static NewsBoard findByPrimaryKey(long newsBoardId)
		throws org.opencps.dossiermgt.exception.NoSuchNewsBoardException {
		return getPersistence().findByPrimaryKey(newsBoardId);
	}

	/**
	* Returns the news board with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param newsBoardId the primary key of the news board
	* @return the news board, or <code>null</code> if a news board with the primary key could not be found
	*/
	public static NewsBoard fetchByPrimaryKey(long newsBoardId) {
		return getPersistence().fetchByPrimaryKey(newsBoardId);
	}

	public static java.util.Map<java.io.Serializable, NewsBoard> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the news boards.
	*
	* @return the news boards
	*/
	public static List<NewsBoard> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the news boards.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NewsBoardModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of news boards
	* @param end the upper bound of the range of news boards (not inclusive)
	* @return the range of news boards
	*/
	public static List<NewsBoard> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the news boards.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NewsBoardModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of news boards
	* @param end the upper bound of the range of news boards (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of news boards
	*/
	public static List<NewsBoard> findAll(int start, int end,
		OrderByComparator<NewsBoard> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the news boards.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link NewsBoardModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of news boards
	* @param end the upper bound of the range of news boards (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of news boards
	*/
	public static List<NewsBoard> findAll(int start, int end,
		OrderByComparator<NewsBoard> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the news boards from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of news boards.
	*
	* @return the number of news boards
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static NewsBoardPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<NewsBoardPersistence, NewsBoardPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(NewsBoardPersistence.class);

		ServiceTracker<NewsBoardPersistence, NewsBoardPersistence> serviceTracker =
			new ServiceTracker<NewsBoardPersistence, NewsBoardPersistence>(bundle.getBundleContext(),
				NewsBoardPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}