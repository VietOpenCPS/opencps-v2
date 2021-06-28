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

import org.opencps.dossiermgt.model.CsdlDcServiceInfo;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the csdl dc service info service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.CsdlDcServiceInfoPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see CsdlDcServiceInfoPersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.CsdlDcServiceInfoPersistenceImpl
 * @generated
 */
@ProviderType
public class CsdlDcServiceInfoUtil {
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
	public static void clearCache(CsdlDcServiceInfo csdlDcServiceInfo) {
		getPersistence().clearCache(csdlDcServiceInfo);
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
	public static List<CsdlDcServiceInfo> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CsdlDcServiceInfo> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CsdlDcServiceInfo> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CsdlDcServiceInfo> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CsdlDcServiceInfo update(CsdlDcServiceInfo csdlDcServiceInfo) {
		return getPersistence().update(csdlDcServiceInfo);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CsdlDcServiceInfo update(
		CsdlDcServiceInfo csdlDcServiceInfo, ServiceContext serviceContext) {
		return getPersistence().update(csdlDcServiceInfo, serviceContext);
	}

	/**
	* Returns all the csdl dc service infos where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching csdl dc service infos
	*/
	public static List<CsdlDcServiceInfo> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the csdl dc service infos where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of csdl dc service infos
	* @param end the upper bound of the range of csdl dc service infos (not inclusive)
	* @return the range of matching csdl dc service infos
	*/
	public static List<CsdlDcServiceInfo> findByUuid(String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the csdl dc service infos where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of csdl dc service infos
	* @param end the upper bound of the range of csdl dc service infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching csdl dc service infos
	*/
	public static List<CsdlDcServiceInfo> findByUuid(String uuid, int start,
		int end, OrderByComparator<CsdlDcServiceInfo> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the csdl dc service infos where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of csdl dc service infos
	* @param end the upper bound of the range of csdl dc service infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching csdl dc service infos
	*/
	public static List<CsdlDcServiceInfo> findByUuid(String uuid, int start,
		int end, OrderByComparator<CsdlDcServiceInfo> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first csdl dc service info in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching csdl dc service info
	* @throws NoSuchCsdlDcServiceInfoException if a matching csdl dc service info could not be found
	*/
	public static CsdlDcServiceInfo findByUuid_First(String uuid,
		OrderByComparator<CsdlDcServiceInfo> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchCsdlDcServiceInfoException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first csdl dc service info in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching csdl dc service info, or <code>null</code> if a matching csdl dc service info could not be found
	*/
	public static CsdlDcServiceInfo fetchByUuid_First(String uuid,
		OrderByComparator<CsdlDcServiceInfo> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last csdl dc service info in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching csdl dc service info
	* @throws NoSuchCsdlDcServiceInfoException if a matching csdl dc service info could not be found
	*/
	public static CsdlDcServiceInfo findByUuid_Last(String uuid,
		OrderByComparator<CsdlDcServiceInfo> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchCsdlDcServiceInfoException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last csdl dc service info in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching csdl dc service info, or <code>null</code> if a matching csdl dc service info could not be found
	*/
	public static CsdlDcServiceInfo fetchByUuid_Last(String uuid,
		OrderByComparator<CsdlDcServiceInfo> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the csdl dc service infos before and after the current csdl dc service info in the ordered set where uuid = &#63;.
	*
	* @param idDcService the primary key of the current csdl dc service info
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next csdl dc service info
	* @throws NoSuchCsdlDcServiceInfoException if a csdl dc service info with the primary key could not be found
	*/
	public static CsdlDcServiceInfo[] findByUuid_PrevAndNext(long idDcService,
		String uuid, OrderByComparator<CsdlDcServiceInfo> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchCsdlDcServiceInfoException {
		return getPersistence()
				   .findByUuid_PrevAndNext(idDcService, uuid, orderByComparator);
	}

	/**
	* Removes all the csdl dc service infos where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of csdl dc service infos where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching csdl dc service infos
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the csdl dc service info where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchCsdlDcServiceInfoException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching csdl dc service info
	* @throws NoSuchCsdlDcServiceInfoException if a matching csdl dc service info could not be found
	*/
	public static CsdlDcServiceInfo findByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchCsdlDcServiceInfoException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the csdl dc service info where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching csdl dc service info, or <code>null</code> if a matching csdl dc service info could not be found
	*/
	public static CsdlDcServiceInfo fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the csdl dc service info where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching csdl dc service info, or <code>null</code> if a matching csdl dc service info could not be found
	*/
	public static CsdlDcServiceInfo fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the csdl dc service info where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the csdl dc service info that was removed
	*/
	public static CsdlDcServiceInfo removeByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchCsdlDcServiceInfoException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of csdl dc service infos where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching csdl dc service infos
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the csdl dc service infos where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching csdl dc service infos
	*/
	public static List<CsdlDcServiceInfo> findByUuid_C(String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the csdl dc service infos where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of csdl dc service infos
	* @param end the upper bound of the range of csdl dc service infos (not inclusive)
	* @return the range of matching csdl dc service infos
	*/
	public static List<CsdlDcServiceInfo> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the csdl dc service infos where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of csdl dc service infos
	* @param end the upper bound of the range of csdl dc service infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching csdl dc service infos
	*/
	public static List<CsdlDcServiceInfo> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<CsdlDcServiceInfo> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the csdl dc service infos where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of csdl dc service infos
	* @param end the upper bound of the range of csdl dc service infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching csdl dc service infos
	*/
	public static List<CsdlDcServiceInfo> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<CsdlDcServiceInfo> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first csdl dc service info in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching csdl dc service info
	* @throws NoSuchCsdlDcServiceInfoException if a matching csdl dc service info could not be found
	*/
	public static CsdlDcServiceInfo findByUuid_C_First(String uuid,
		long companyId, OrderByComparator<CsdlDcServiceInfo> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchCsdlDcServiceInfoException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first csdl dc service info in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching csdl dc service info, or <code>null</code> if a matching csdl dc service info could not be found
	*/
	public static CsdlDcServiceInfo fetchByUuid_C_First(String uuid,
		long companyId, OrderByComparator<CsdlDcServiceInfo> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last csdl dc service info in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching csdl dc service info
	* @throws NoSuchCsdlDcServiceInfoException if a matching csdl dc service info could not be found
	*/
	public static CsdlDcServiceInfo findByUuid_C_Last(String uuid,
		long companyId, OrderByComparator<CsdlDcServiceInfo> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchCsdlDcServiceInfoException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last csdl dc service info in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching csdl dc service info, or <code>null</code> if a matching csdl dc service info could not be found
	*/
	public static CsdlDcServiceInfo fetchByUuid_C_Last(String uuid,
		long companyId, OrderByComparator<CsdlDcServiceInfo> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the csdl dc service infos before and after the current csdl dc service info in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param idDcService the primary key of the current csdl dc service info
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next csdl dc service info
	* @throws NoSuchCsdlDcServiceInfoException if a csdl dc service info with the primary key could not be found
	*/
	public static CsdlDcServiceInfo[] findByUuid_C_PrevAndNext(
		long idDcService, String uuid, long companyId,
		OrderByComparator<CsdlDcServiceInfo> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchCsdlDcServiceInfoException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(idDcService, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the csdl dc service infos where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of csdl dc service infos where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching csdl dc service infos
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the csdl dc service info where serviceCode = &#63; and status = &#63; or throws a {@link NoSuchCsdlDcServiceInfoException} if it could not be found.
	*
	* @param serviceCode the service code
	* @param status the status
	* @return the matching csdl dc service info
	* @throws NoSuchCsdlDcServiceInfoException if a matching csdl dc service info could not be found
	*/
	public static CsdlDcServiceInfo findByF_SERVICECODE_STATUS(
		String serviceCode, int status)
		throws org.opencps.dossiermgt.exception.NoSuchCsdlDcServiceInfoException {
		return getPersistence().findByF_SERVICECODE_STATUS(serviceCode, status);
	}

	/**
	* Returns the csdl dc service info where serviceCode = &#63; and status = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param serviceCode the service code
	* @param status the status
	* @return the matching csdl dc service info, or <code>null</code> if a matching csdl dc service info could not be found
	*/
	public static CsdlDcServiceInfo fetchByF_SERVICECODE_STATUS(
		String serviceCode, int status) {
		return getPersistence().fetchByF_SERVICECODE_STATUS(serviceCode, status);
	}

	/**
	* Returns the csdl dc service info where serviceCode = &#63; and status = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param serviceCode the service code
	* @param status the status
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching csdl dc service info, or <code>null</code> if a matching csdl dc service info could not be found
	*/
	public static CsdlDcServiceInfo fetchByF_SERVICECODE_STATUS(
		String serviceCode, int status, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_SERVICECODE_STATUS(serviceCode, status,
			retrieveFromCache);
	}

	/**
	* Removes the csdl dc service info where serviceCode = &#63; and status = &#63; from the database.
	*
	* @param serviceCode the service code
	* @param status the status
	* @return the csdl dc service info that was removed
	*/
	public static CsdlDcServiceInfo removeByF_SERVICECODE_STATUS(
		String serviceCode, int status)
		throws org.opencps.dossiermgt.exception.NoSuchCsdlDcServiceInfoException {
		return getPersistence().removeByF_SERVICECODE_STATUS(serviceCode, status);
	}

	/**
	* Returns the number of csdl dc service infos where serviceCode = &#63; and status = &#63;.
	*
	* @param serviceCode the service code
	* @param status the status
	* @return the number of matching csdl dc service infos
	*/
	public static int countByF_SERVICECODE_STATUS(String serviceCode, int status) {
		return getPersistence().countByF_SERVICECODE_STATUS(serviceCode, status);
	}

	/**
	* Caches the csdl dc service info in the entity cache if it is enabled.
	*
	* @param csdlDcServiceInfo the csdl dc service info
	*/
	public static void cacheResult(CsdlDcServiceInfo csdlDcServiceInfo) {
		getPersistence().cacheResult(csdlDcServiceInfo);
	}

	/**
	* Caches the csdl dc service infos in the entity cache if it is enabled.
	*
	* @param csdlDcServiceInfos the csdl dc service infos
	*/
	public static void cacheResult(List<CsdlDcServiceInfo> csdlDcServiceInfos) {
		getPersistence().cacheResult(csdlDcServiceInfos);
	}

	/**
	* Creates a new csdl dc service info with the primary key. Does not add the csdl dc service info to the database.
	*
	* @param idDcService the primary key for the new csdl dc service info
	* @return the new csdl dc service info
	*/
	public static CsdlDcServiceInfo create(long idDcService) {
		return getPersistence().create(idDcService);
	}

	/**
	* Removes the csdl dc service info with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param idDcService the primary key of the csdl dc service info
	* @return the csdl dc service info that was removed
	* @throws NoSuchCsdlDcServiceInfoException if a csdl dc service info with the primary key could not be found
	*/
	public static CsdlDcServiceInfo remove(long idDcService)
		throws org.opencps.dossiermgt.exception.NoSuchCsdlDcServiceInfoException {
		return getPersistence().remove(idDcService);
	}

	public static CsdlDcServiceInfo updateImpl(
		CsdlDcServiceInfo csdlDcServiceInfo) {
		return getPersistence().updateImpl(csdlDcServiceInfo);
	}

	/**
	* Returns the csdl dc service info with the primary key or throws a {@link NoSuchCsdlDcServiceInfoException} if it could not be found.
	*
	* @param idDcService the primary key of the csdl dc service info
	* @return the csdl dc service info
	* @throws NoSuchCsdlDcServiceInfoException if a csdl dc service info with the primary key could not be found
	*/
	public static CsdlDcServiceInfo findByPrimaryKey(long idDcService)
		throws org.opencps.dossiermgt.exception.NoSuchCsdlDcServiceInfoException {
		return getPersistence().findByPrimaryKey(idDcService);
	}

	/**
	* Returns the csdl dc service info with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param idDcService the primary key of the csdl dc service info
	* @return the csdl dc service info, or <code>null</code> if a csdl dc service info with the primary key could not be found
	*/
	public static CsdlDcServiceInfo fetchByPrimaryKey(long idDcService) {
		return getPersistence().fetchByPrimaryKey(idDcService);
	}

	public static java.util.Map<java.io.Serializable, CsdlDcServiceInfo> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the csdl dc service infos.
	*
	* @return the csdl dc service infos
	*/
	public static List<CsdlDcServiceInfo> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the csdl dc service infos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of csdl dc service infos
	* @param end the upper bound of the range of csdl dc service infos (not inclusive)
	* @return the range of csdl dc service infos
	*/
	public static List<CsdlDcServiceInfo> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the csdl dc service infos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of csdl dc service infos
	* @param end the upper bound of the range of csdl dc service infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of csdl dc service infos
	*/
	public static List<CsdlDcServiceInfo> findAll(int start, int end,
		OrderByComparator<CsdlDcServiceInfo> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the csdl dc service infos.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CsdlDcServiceInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of csdl dc service infos
	* @param end the upper bound of the range of csdl dc service infos (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of csdl dc service infos
	*/
	public static List<CsdlDcServiceInfo> findAll(int start, int end,
		OrderByComparator<CsdlDcServiceInfo> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the csdl dc service infos from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of csdl dc service infos.
	*
	* @return the number of csdl dc service infos
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CsdlDcServiceInfoPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CsdlDcServiceInfoPersistence, CsdlDcServiceInfoPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CsdlDcServiceInfoPersistence.class);

		ServiceTracker<CsdlDcServiceInfoPersistence, CsdlDcServiceInfoPersistence> serviceTracker =
			new ServiceTracker<CsdlDcServiceInfoPersistence, CsdlDcServiceInfoPersistence>(bundle.getBundleContext(),
				CsdlDcServiceInfoPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}