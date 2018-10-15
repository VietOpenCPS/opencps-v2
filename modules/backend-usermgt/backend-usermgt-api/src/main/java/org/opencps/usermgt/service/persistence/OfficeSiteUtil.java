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

package org.opencps.usermgt.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.usermgt.model.OfficeSite;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the office site service. This utility wraps {@link org.opencps.usermgt.service.persistence.impl.OfficeSitePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see OfficeSitePersistence
 * @see org.opencps.usermgt.service.persistence.impl.OfficeSitePersistenceImpl
 * @generated
 */
@ProviderType
public class OfficeSiteUtil {
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
	public static void clearCache(OfficeSite officeSite) {
		getPersistence().clearCache(officeSite);
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
	public static List<OfficeSite> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<OfficeSite> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<OfficeSite> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<OfficeSite> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static OfficeSite update(OfficeSite officeSite) {
		return getPersistence().update(officeSite);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static OfficeSite update(OfficeSite officeSite,
		ServiceContext serviceContext) {
		return getPersistence().update(officeSite, serviceContext);
	}

	/**
	* Returns all the office sites where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching office sites
	*/
	public static List<OfficeSite> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the office sites where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfficeSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of office sites
	* @param end the upper bound of the range of office sites (not inclusive)
	* @return the range of matching office sites
	*/
	public static List<OfficeSite> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the office sites where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfficeSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of office sites
	* @param end the upper bound of the range of office sites (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching office sites
	*/
	public static List<OfficeSite> findByUuid(String uuid, int start, int end,
		OrderByComparator<OfficeSite> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the office sites where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfficeSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of office sites
	* @param end the upper bound of the range of office sites (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching office sites
	*/
	public static List<OfficeSite> findByUuid(String uuid, int start, int end,
		OrderByComparator<OfficeSite> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first office site in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching office site
	* @throws NoSuchOfficeSiteException if a matching office site could not be found
	*/
	public static OfficeSite findByUuid_First(String uuid,
		OrderByComparator<OfficeSite> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchOfficeSiteException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first office site in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching office site, or <code>null</code> if a matching office site could not be found
	*/
	public static OfficeSite fetchByUuid_First(String uuid,
		OrderByComparator<OfficeSite> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last office site in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching office site
	* @throws NoSuchOfficeSiteException if a matching office site could not be found
	*/
	public static OfficeSite findByUuid_Last(String uuid,
		OrderByComparator<OfficeSite> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchOfficeSiteException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last office site in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching office site, or <code>null</code> if a matching office site could not be found
	*/
	public static OfficeSite fetchByUuid_Last(String uuid,
		OrderByComparator<OfficeSite> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the office sites before and after the current office site in the ordered set where uuid = &#63;.
	*
	* @param officeSiteId the primary key of the current office site
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next office site
	* @throws NoSuchOfficeSiteException if a office site with the primary key could not be found
	*/
	public static OfficeSite[] findByUuid_PrevAndNext(long officeSiteId,
		String uuid, OrderByComparator<OfficeSite> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchOfficeSiteException {
		return getPersistence()
				   .findByUuid_PrevAndNext(officeSiteId, uuid, orderByComparator);
	}

	/**
	* Removes all the office sites where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of office sites where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching office sites
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the office site where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchOfficeSiteException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching office site
	* @throws NoSuchOfficeSiteException if a matching office site could not be found
	*/
	public static OfficeSite findByUUID_G(String uuid, long groupId)
		throws org.opencps.usermgt.exception.NoSuchOfficeSiteException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the office site where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching office site, or <code>null</code> if a matching office site could not be found
	*/
	public static OfficeSite fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the office site where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching office site, or <code>null</code> if a matching office site could not be found
	*/
	public static OfficeSite fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the office site where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the office site that was removed
	*/
	public static OfficeSite removeByUUID_G(String uuid, long groupId)
		throws org.opencps.usermgt.exception.NoSuchOfficeSiteException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of office sites where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching office sites
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the office sites where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching office sites
	*/
	public static List<OfficeSite> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the office sites where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfficeSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of office sites
	* @param end the upper bound of the range of office sites (not inclusive)
	* @return the range of matching office sites
	*/
	public static List<OfficeSite> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the office sites where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfficeSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of office sites
	* @param end the upper bound of the range of office sites (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching office sites
	*/
	public static List<OfficeSite> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<OfficeSite> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the office sites where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfficeSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of office sites
	* @param end the upper bound of the range of office sites (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching office sites
	*/
	public static List<OfficeSite> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<OfficeSite> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first office site in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching office site
	* @throws NoSuchOfficeSiteException if a matching office site could not be found
	*/
	public static OfficeSite findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<OfficeSite> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchOfficeSiteException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first office site in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching office site, or <code>null</code> if a matching office site could not be found
	*/
	public static OfficeSite fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<OfficeSite> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last office site in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching office site
	* @throws NoSuchOfficeSiteException if a matching office site could not be found
	*/
	public static OfficeSite findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<OfficeSite> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchOfficeSiteException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last office site in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching office site, or <code>null</code> if a matching office site could not be found
	*/
	public static OfficeSite fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<OfficeSite> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the office sites before and after the current office site in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param officeSiteId the primary key of the current office site
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next office site
	* @throws NoSuchOfficeSiteException if a office site with the primary key could not be found
	*/
	public static OfficeSite[] findByUuid_C_PrevAndNext(long officeSiteId,
		String uuid, long companyId,
		OrderByComparator<OfficeSite> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchOfficeSiteException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(officeSiteId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the office sites where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of office sites where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching office sites
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the office site where groupId = &#63; and siteGroupId = &#63; or throws a {@link NoSuchOfficeSiteException} if it could not be found.
	*
	* @param groupId the group ID
	* @param siteGroupId the site group ID
	* @return the matching office site
	* @throws NoSuchOfficeSiteException if a matching office site could not be found
	*/
	public static OfficeSite findByF_groupId_siteGroupId(long groupId,
		long siteGroupId)
		throws org.opencps.usermgt.exception.NoSuchOfficeSiteException {
		return getPersistence().findByF_groupId_siteGroupId(groupId, siteGroupId);
	}

	/**
	* Returns the office site where groupId = &#63; and siteGroupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param siteGroupId the site group ID
	* @return the matching office site, or <code>null</code> if a matching office site could not be found
	*/
	public static OfficeSite fetchByF_groupId_siteGroupId(long groupId,
		long siteGroupId) {
		return getPersistence()
				   .fetchByF_groupId_siteGroupId(groupId, siteGroupId);
	}

	/**
	* Returns the office site where groupId = &#63; and siteGroupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param siteGroupId the site group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching office site, or <code>null</code> if a matching office site could not be found
	*/
	public static OfficeSite fetchByF_groupId_siteGroupId(long groupId,
		long siteGroupId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_groupId_siteGroupId(groupId, siteGroupId,
			retrieveFromCache);
	}

	/**
	* Removes the office site where groupId = &#63; and siteGroupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param siteGroupId the site group ID
	* @return the office site that was removed
	*/
	public static OfficeSite removeByF_groupId_siteGroupId(long groupId,
		long siteGroupId)
		throws org.opencps.usermgt.exception.NoSuchOfficeSiteException {
		return getPersistence()
				   .removeByF_groupId_siteGroupId(groupId, siteGroupId);
	}

	/**
	* Returns the number of office sites where groupId = &#63; and siteGroupId = &#63;.
	*
	* @param groupId the group ID
	* @param siteGroupId the site group ID
	* @return the number of matching office sites
	*/
	public static int countByF_groupId_siteGroupId(long groupId,
		long siteGroupId) {
		return getPersistence()
				   .countByF_groupId_siteGroupId(groupId, siteGroupId);
	}

	/**
	* Caches the office site in the entity cache if it is enabled.
	*
	* @param officeSite the office site
	*/
	public static void cacheResult(OfficeSite officeSite) {
		getPersistence().cacheResult(officeSite);
	}

	/**
	* Caches the office sites in the entity cache if it is enabled.
	*
	* @param officeSites the office sites
	*/
	public static void cacheResult(List<OfficeSite> officeSites) {
		getPersistence().cacheResult(officeSites);
	}

	/**
	* Creates a new office site with the primary key. Does not add the office site to the database.
	*
	* @param officeSiteId the primary key for the new office site
	* @return the new office site
	*/
	public static OfficeSite create(long officeSiteId) {
		return getPersistence().create(officeSiteId);
	}

	/**
	* Removes the office site with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param officeSiteId the primary key of the office site
	* @return the office site that was removed
	* @throws NoSuchOfficeSiteException if a office site with the primary key could not be found
	*/
	public static OfficeSite remove(long officeSiteId)
		throws org.opencps.usermgt.exception.NoSuchOfficeSiteException {
		return getPersistence().remove(officeSiteId);
	}

	public static OfficeSite updateImpl(OfficeSite officeSite) {
		return getPersistence().updateImpl(officeSite);
	}

	/**
	* Returns the office site with the primary key or throws a {@link NoSuchOfficeSiteException} if it could not be found.
	*
	* @param officeSiteId the primary key of the office site
	* @return the office site
	* @throws NoSuchOfficeSiteException if a office site with the primary key could not be found
	*/
	public static OfficeSite findByPrimaryKey(long officeSiteId)
		throws org.opencps.usermgt.exception.NoSuchOfficeSiteException {
		return getPersistence().findByPrimaryKey(officeSiteId);
	}

	/**
	* Returns the office site with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param officeSiteId the primary key of the office site
	* @return the office site, or <code>null</code> if a office site with the primary key could not be found
	*/
	public static OfficeSite fetchByPrimaryKey(long officeSiteId) {
		return getPersistence().fetchByPrimaryKey(officeSiteId);
	}

	public static java.util.Map<java.io.Serializable, OfficeSite> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the office sites.
	*
	* @return the office sites
	*/
	public static List<OfficeSite> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the office sites.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfficeSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of office sites
	* @param end the upper bound of the range of office sites (not inclusive)
	* @return the range of office sites
	*/
	public static List<OfficeSite> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the office sites.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfficeSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of office sites
	* @param end the upper bound of the range of office sites (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of office sites
	*/
	public static List<OfficeSite> findAll(int start, int end,
		OrderByComparator<OfficeSite> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the office sites.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfficeSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of office sites
	* @param end the upper bound of the range of office sites (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of office sites
	*/
	public static List<OfficeSite> findAll(int start, int end,
		OrderByComparator<OfficeSite> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the office sites from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of office sites.
	*
	* @return the number of office sites
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static OfficeSitePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<OfficeSitePersistence, OfficeSitePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(OfficeSitePersistence.class);

		ServiceTracker<OfficeSitePersistence, OfficeSitePersistence> serviceTracker =
			new ServiceTracker<OfficeSitePersistence, OfficeSitePersistence>(bundle.getBundleContext(),
				OfficeSitePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}