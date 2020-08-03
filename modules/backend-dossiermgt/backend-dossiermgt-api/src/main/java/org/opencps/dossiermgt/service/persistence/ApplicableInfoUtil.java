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

import org.opencps.dossiermgt.model.ApplicableInfo;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the applicable info service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.ApplicableInfoPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see ApplicableInfoPersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.ApplicableInfoPersistenceImpl
 * @generated
 */
@ProviderType
public class ApplicableInfoUtil {
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
	public static void clearCache(ApplicableInfo applicableInfo) {
		getPersistence().clearCache(applicableInfo);
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
	public static List<ApplicableInfo> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ApplicableInfo> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ApplicableInfo> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ApplicableInfo> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ApplicableInfo update(ApplicableInfo applicableInfo) {
		return getPersistence().update(applicableInfo);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ApplicableInfo update(ApplicableInfo applicableInfo,
		ServiceContext serviceContext) {
		return getPersistence().update(applicableInfo, serviceContext);
	}

	/**
	* Returns all the applicable infos where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching applicable infos
	*/
	public static List<ApplicableInfo> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the applicable infos where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicableInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of applicable infos
	* @param end the upper bound of the range of applicable infos (not inclusive)
	* @return the range of matching applicable infos
	*/
	public static List<ApplicableInfo> findByUuid(String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the applicable infos where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicableInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of applicable infos
	* @param end the upper bound of the range of applicable infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching applicable infos
	*/
	public static List<ApplicableInfo> findByUuid(String uuid, int start,
		int end, OrderByComparator<ApplicableInfo> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the applicable infos where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicableInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of applicable infos
	* @param end the upper bound of the range of applicable infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching applicable infos
	*/
	public static List<ApplicableInfo> findByUuid(String uuid, int start,
		int end, OrderByComparator<ApplicableInfo> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first applicable info in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching applicable info
	* @throws NoSuchApplicableInfoException if a matching applicable info could not be found
	*/
	public static ApplicableInfo findByUuid_First(String uuid,
		OrderByComparator<ApplicableInfo> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchApplicableInfoException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first applicable info in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching applicable info, or <code>null</code> if a matching applicable info could not be found
	*/
	public static ApplicableInfo fetchByUuid_First(String uuid,
		OrderByComparator<ApplicableInfo> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last applicable info in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching applicable info
	* @throws NoSuchApplicableInfoException if a matching applicable info could not be found
	*/
	public static ApplicableInfo findByUuid_Last(String uuid,
		OrderByComparator<ApplicableInfo> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchApplicableInfoException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last applicable info in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching applicable info, or <code>null</code> if a matching applicable info could not be found
	*/
	public static ApplicableInfo fetchByUuid_Last(String uuid,
		OrderByComparator<ApplicableInfo> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the applicable infos before and after the current applicable info in the ordered set where uuid = &#63;.
	*
	* @param applicableInfoId the primary key of the current applicable info
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next applicable info
	* @throws NoSuchApplicableInfoException if a applicable info with the primary key could not be found
	*/
	public static ApplicableInfo[] findByUuid_PrevAndNext(
		long applicableInfoId, String uuid,
		OrderByComparator<ApplicableInfo> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchApplicableInfoException {
		return getPersistence()
				   .findByUuid_PrevAndNext(applicableInfoId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the applicable infos where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of applicable infos where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching applicable infos
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the applicable info where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchApplicableInfoException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching applicable info
	* @throws NoSuchApplicableInfoException if a matching applicable info could not be found
	*/
	public static ApplicableInfo findByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchApplicableInfoException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the applicable info where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching applicable info, or <code>null</code> if a matching applicable info could not be found
	*/
	public static ApplicableInfo fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the applicable info where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching applicable info, or <code>null</code> if a matching applicable info could not be found
	*/
	public static ApplicableInfo fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the applicable info where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the applicable info that was removed
	*/
	public static ApplicableInfo removeByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchApplicableInfoException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of applicable infos where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching applicable infos
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the applicable infos where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching applicable infos
	*/
	public static List<ApplicableInfo> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the applicable infos where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicableInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of applicable infos
	* @param end the upper bound of the range of applicable infos (not inclusive)
	* @return the range of matching applicable infos
	*/
	public static List<ApplicableInfo> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the applicable infos where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicableInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of applicable infos
	* @param end the upper bound of the range of applicable infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching applicable infos
	*/
	public static List<ApplicableInfo> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<ApplicableInfo> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the applicable infos where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicableInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of applicable infos
	* @param end the upper bound of the range of applicable infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching applicable infos
	*/
	public static List<ApplicableInfo> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<ApplicableInfo> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first applicable info in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching applicable info
	* @throws NoSuchApplicableInfoException if a matching applicable info could not be found
	*/
	public static ApplicableInfo findByUuid_C_First(String uuid,
		long companyId, OrderByComparator<ApplicableInfo> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchApplicableInfoException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first applicable info in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching applicable info, or <code>null</code> if a matching applicable info could not be found
	*/
	public static ApplicableInfo fetchByUuid_C_First(String uuid,
		long companyId, OrderByComparator<ApplicableInfo> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last applicable info in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching applicable info
	* @throws NoSuchApplicableInfoException if a matching applicable info could not be found
	*/
	public static ApplicableInfo findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ApplicableInfo> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchApplicableInfoException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last applicable info in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching applicable info, or <code>null</code> if a matching applicable info could not be found
	*/
	public static ApplicableInfo fetchByUuid_C_Last(String uuid,
		long companyId, OrderByComparator<ApplicableInfo> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the applicable infos before and after the current applicable info in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param applicableInfoId the primary key of the current applicable info
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next applicable info
	* @throws NoSuchApplicableInfoException if a applicable info with the primary key could not be found
	*/
	public static ApplicableInfo[] findByUuid_C_PrevAndNext(
		long applicableInfoId, String uuid, long companyId,
		OrderByComparator<ApplicableInfo> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchApplicableInfoException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(applicableInfoId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the applicable infos where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of applicable infos where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching applicable infos
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the applicable info where groupId = &#63; and serviceCode = &#63; and govAgencyCode = &#63; and serviceLevel = &#63; or throws a {@link NoSuchApplicableInfoException} if it could not be found.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @param serviceLevel the service level
	* @return the matching applicable info
	* @throws NoSuchApplicableInfoException if a matching applicable info could not be found
	*/
	public static ApplicableInfo findByG_SC_GC_SL(long groupId,
		String serviceCode, String govAgencyCode, int serviceLevel)
		throws org.opencps.dossiermgt.exception.NoSuchApplicableInfoException {
		return getPersistence()
				   .findByG_SC_GC_SL(groupId, serviceCode, govAgencyCode,
			serviceLevel);
	}

	/**
	* Returns the applicable info where groupId = &#63; and serviceCode = &#63; and govAgencyCode = &#63; and serviceLevel = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @param serviceLevel the service level
	* @return the matching applicable info, or <code>null</code> if a matching applicable info could not be found
	*/
	public static ApplicableInfo fetchByG_SC_GC_SL(long groupId,
		String serviceCode, String govAgencyCode, int serviceLevel) {
		return getPersistence()
				   .fetchByG_SC_GC_SL(groupId, serviceCode, govAgencyCode,
			serviceLevel);
	}

	/**
	* Returns the applicable info where groupId = &#63; and serviceCode = &#63; and govAgencyCode = &#63; and serviceLevel = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @param serviceLevel the service level
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching applicable info, or <code>null</code> if a matching applicable info could not be found
	*/
	public static ApplicableInfo fetchByG_SC_GC_SL(long groupId,
		String serviceCode, String govAgencyCode, int serviceLevel,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByG_SC_GC_SL(groupId, serviceCode, govAgencyCode,
			serviceLevel, retrieveFromCache);
	}

	/**
	* Removes the applicable info where groupId = &#63; and serviceCode = &#63; and govAgencyCode = &#63; and serviceLevel = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @param serviceLevel the service level
	* @return the applicable info that was removed
	*/
	public static ApplicableInfo removeByG_SC_GC_SL(long groupId,
		String serviceCode, String govAgencyCode, int serviceLevel)
		throws org.opencps.dossiermgt.exception.NoSuchApplicableInfoException {
		return getPersistence()
				   .removeByG_SC_GC_SL(groupId, serviceCode, govAgencyCode,
			serviceLevel);
	}

	/**
	* Returns the number of applicable infos where groupId = &#63; and serviceCode = &#63; and govAgencyCode = &#63; and serviceLevel = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @param serviceLevel the service level
	* @return the number of matching applicable infos
	*/
	public static int countByG_SC_GC_SL(long groupId, String serviceCode,
		String govAgencyCode, int serviceLevel) {
		return getPersistence()
				   .countByG_SC_GC_SL(groupId, serviceCode, govAgencyCode,
			serviceLevel);
	}

	/**
	* Returns all the applicable infos where serviceConfigMappingId = &#63;.
	*
	* @param serviceConfigMappingId the service config mapping ID
	* @return the matching applicable infos
	*/
	public static List<ApplicableInfo> findByServiceConfigMappingId(
		long serviceConfigMappingId) {
		return getPersistence()
				   .findByServiceConfigMappingId(serviceConfigMappingId);
	}

	/**
	* Returns a range of all the applicable infos where serviceConfigMappingId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicableInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serviceConfigMappingId the service config mapping ID
	* @param start the lower bound of the range of applicable infos
	* @param end the upper bound of the range of applicable infos (not inclusive)
	* @return the range of matching applicable infos
	*/
	public static List<ApplicableInfo> findByServiceConfigMappingId(
		long serviceConfigMappingId, int start, int end) {
		return getPersistence()
				   .findByServiceConfigMappingId(serviceConfigMappingId, start,
			end);
	}

	/**
	* Returns an ordered range of all the applicable infos where serviceConfigMappingId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicableInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serviceConfigMappingId the service config mapping ID
	* @param start the lower bound of the range of applicable infos
	* @param end the upper bound of the range of applicable infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching applicable infos
	*/
	public static List<ApplicableInfo> findByServiceConfigMappingId(
		long serviceConfigMappingId, int start, int end,
		OrderByComparator<ApplicableInfo> orderByComparator) {
		return getPersistence()
				   .findByServiceConfigMappingId(serviceConfigMappingId, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the applicable infos where serviceConfigMappingId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicableInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param serviceConfigMappingId the service config mapping ID
	* @param start the lower bound of the range of applicable infos
	* @param end the upper bound of the range of applicable infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching applicable infos
	*/
	public static List<ApplicableInfo> findByServiceConfigMappingId(
		long serviceConfigMappingId, int start, int end,
		OrderByComparator<ApplicableInfo> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByServiceConfigMappingId(serviceConfigMappingId, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first applicable info in the ordered set where serviceConfigMappingId = &#63;.
	*
	* @param serviceConfigMappingId the service config mapping ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching applicable info
	* @throws NoSuchApplicableInfoException if a matching applicable info could not be found
	*/
	public static ApplicableInfo findByServiceConfigMappingId_First(
		long serviceConfigMappingId,
		OrderByComparator<ApplicableInfo> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchApplicableInfoException {
		return getPersistence()
				   .findByServiceConfigMappingId_First(serviceConfigMappingId,
			orderByComparator);
	}

	/**
	* Returns the first applicable info in the ordered set where serviceConfigMappingId = &#63;.
	*
	* @param serviceConfigMappingId the service config mapping ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching applicable info, or <code>null</code> if a matching applicable info could not be found
	*/
	public static ApplicableInfo fetchByServiceConfigMappingId_First(
		long serviceConfigMappingId,
		OrderByComparator<ApplicableInfo> orderByComparator) {
		return getPersistence()
				   .fetchByServiceConfigMappingId_First(serviceConfigMappingId,
			orderByComparator);
	}

	/**
	* Returns the last applicable info in the ordered set where serviceConfigMappingId = &#63;.
	*
	* @param serviceConfigMappingId the service config mapping ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching applicable info
	* @throws NoSuchApplicableInfoException if a matching applicable info could not be found
	*/
	public static ApplicableInfo findByServiceConfigMappingId_Last(
		long serviceConfigMappingId,
		OrderByComparator<ApplicableInfo> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchApplicableInfoException {
		return getPersistence()
				   .findByServiceConfigMappingId_Last(serviceConfigMappingId,
			orderByComparator);
	}

	/**
	* Returns the last applicable info in the ordered set where serviceConfigMappingId = &#63;.
	*
	* @param serviceConfigMappingId the service config mapping ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching applicable info, or <code>null</code> if a matching applicable info could not be found
	*/
	public static ApplicableInfo fetchByServiceConfigMappingId_Last(
		long serviceConfigMappingId,
		OrderByComparator<ApplicableInfo> orderByComparator) {
		return getPersistence()
				   .fetchByServiceConfigMappingId_Last(serviceConfigMappingId,
			orderByComparator);
	}

	/**
	* Returns the applicable infos before and after the current applicable info in the ordered set where serviceConfigMappingId = &#63;.
	*
	* @param applicableInfoId the primary key of the current applicable info
	* @param serviceConfigMappingId the service config mapping ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next applicable info
	* @throws NoSuchApplicableInfoException if a applicable info with the primary key could not be found
	*/
	public static ApplicableInfo[] findByServiceConfigMappingId_PrevAndNext(
		long applicableInfoId, long serviceConfigMappingId,
		OrderByComparator<ApplicableInfo> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchApplicableInfoException {
		return getPersistence()
				   .findByServiceConfigMappingId_PrevAndNext(applicableInfoId,
			serviceConfigMappingId, orderByComparator);
	}

	/**
	* Removes all the applicable infos where serviceConfigMappingId = &#63; from the database.
	*
	* @param serviceConfigMappingId the service config mapping ID
	*/
	public static void removeByServiceConfigMappingId(
		long serviceConfigMappingId) {
		getPersistence().removeByServiceConfigMappingId(serviceConfigMappingId);
	}

	/**
	* Returns the number of applicable infos where serviceConfigMappingId = &#63;.
	*
	* @param serviceConfigMappingId the service config mapping ID
	* @return the number of matching applicable infos
	*/
	public static int countByServiceConfigMappingId(long serviceConfigMappingId) {
		return getPersistence()
				   .countByServiceConfigMappingId(serviceConfigMappingId);
	}

	/**
	* Caches the applicable info in the entity cache if it is enabled.
	*
	* @param applicableInfo the applicable info
	*/
	public static void cacheResult(ApplicableInfo applicableInfo) {
		getPersistence().cacheResult(applicableInfo);
	}

	/**
	* Caches the applicable infos in the entity cache if it is enabled.
	*
	* @param applicableInfos the applicable infos
	*/
	public static void cacheResult(List<ApplicableInfo> applicableInfos) {
		getPersistence().cacheResult(applicableInfos);
	}

	/**
	* Creates a new applicable info with the primary key. Does not add the applicable info to the database.
	*
	* @param applicableInfoId the primary key for the new applicable info
	* @return the new applicable info
	*/
	public static ApplicableInfo create(long applicableInfoId) {
		return getPersistence().create(applicableInfoId);
	}

	/**
	* Removes the applicable info with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param applicableInfoId the primary key of the applicable info
	* @return the applicable info that was removed
	* @throws NoSuchApplicableInfoException if a applicable info with the primary key could not be found
	*/
	public static ApplicableInfo remove(long applicableInfoId)
		throws org.opencps.dossiermgt.exception.NoSuchApplicableInfoException {
		return getPersistence().remove(applicableInfoId);
	}

	public static ApplicableInfo updateImpl(ApplicableInfo applicableInfo) {
		return getPersistence().updateImpl(applicableInfo);
	}

	/**
	* Returns the applicable info with the primary key or throws a {@link NoSuchApplicableInfoException} if it could not be found.
	*
	* @param applicableInfoId the primary key of the applicable info
	* @return the applicable info
	* @throws NoSuchApplicableInfoException if a applicable info with the primary key could not be found
	*/
	public static ApplicableInfo findByPrimaryKey(long applicableInfoId)
		throws org.opencps.dossiermgt.exception.NoSuchApplicableInfoException {
		return getPersistence().findByPrimaryKey(applicableInfoId);
	}

	/**
	* Returns the applicable info with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param applicableInfoId the primary key of the applicable info
	* @return the applicable info, or <code>null</code> if a applicable info with the primary key could not be found
	*/
	public static ApplicableInfo fetchByPrimaryKey(long applicableInfoId) {
		return getPersistence().fetchByPrimaryKey(applicableInfoId);
	}

	public static java.util.Map<java.io.Serializable, ApplicableInfo> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the applicable infos.
	*
	* @return the applicable infos
	*/
	public static List<ApplicableInfo> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the applicable infos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicableInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of applicable infos
	* @param end the upper bound of the range of applicable infos (not inclusive)
	* @return the range of applicable infos
	*/
	public static List<ApplicableInfo> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the applicable infos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicableInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of applicable infos
	* @param end the upper bound of the range of applicable infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of applicable infos
	*/
	public static List<ApplicableInfo> findAll(int start, int end,
		OrderByComparator<ApplicableInfo> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the applicable infos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicableInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of applicable infos
	* @param end the upper bound of the range of applicable infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of applicable infos
	*/
	public static List<ApplicableInfo> findAll(int start, int end,
		OrderByComparator<ApplicableInfo> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the applicable infos from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of applicable infos.
	*
	* @return the number of applicable infos
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ApplicableInfoPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ApplicableInfoPersistence, ApplicableInfoPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ApplicableInfoPersistence.class);

		ServiceTracker<ApplicableInfoPersistence, ApplicableInfoPersistence> serviceTracker =
			new ServiceTracker<ApplicableInfoPersistence, ApplicableInfoPersistence>(bundle.getBundleContext(),
				ApplicableInfoPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}