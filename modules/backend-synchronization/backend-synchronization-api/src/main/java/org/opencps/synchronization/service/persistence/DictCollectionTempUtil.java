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

import org.opencps.synchronization.model.DictCollectionTemp;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.Date;
import java.util.List;

/**
 * The persistence utility for the dict collection temp service. This utility wraps {@link org.opencps.synchronization.service.persistence.impl.DictCollectionTempPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author trungdk
 * @see DictCollectionTempPersistence
 * @see org.opencps.synchronization.service.persistence.impl.DictCollectionTempPersistenceImpl
 * @generated
 */
@ProviderType
public class DictCollectionTempUtil {
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
	public static void clearCache(DictCollectionTemp dictCollectionTemp) {
		getPersistence().clearCache(dictCollectionTemp);
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
	public static List<DictCollectionTemp> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DictCollectionTemp> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DictCollectionTemp> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<DictCollectionTemp> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static DictCollectionTemp update(
		DictCollectionTemp dictCollectionTemp) {
		return getPersistence().update(dictCollectionTemp);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static DictCollectionTemp update(
		DictCollectionTemp dictCollectionTemp, ServiceContext serviceContext) {
		return getPersistence().update(dictCollectionTemp, serviceContext);
	}

	/**
	* Returns all the dict collection temps where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching dict collection temps
	*/
	public static List<DictCollectionTemp> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the dict collection temps where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dict collection temps
	* @param end the upper bound of the range of dict collection temps (not inclusive)
	* @return the range of matching dict collection temps
	*/
	public static List<DictCollectionTemp> findByUuid(String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the dict collection temps where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dict collection temps
	* @param end the upper bound of the range of dict collection temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict collection temps
	*/
	public static List<DictCollectionTemp> findByUuid(String uuid, int start,
		int end, OrderByComparator<DictCollectionTemp> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dict collection temps where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dict collection temps
	* @param end the upper bound of the range of dict collection temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict collection temps
	*/
	public static List<DictCollectionTemp> findByUuid(String uuid, int start,
		int end, OrderByComparator<DictCollectionTemp> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first dict collection temp in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict collection temp
	* @throws NoSuchDictCollectionTempException if a matching dict collection temp could not be found
	*/
	public static DictCollectionTemp findByUuid_First(String uuid,
		OrderByComparator<DictCollectionTemp> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchDictCollectionTempException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first dict collection temp in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict collection temp, or <code>null</code> if a matching dict collection temp could not be found
	*/
	public static DictCollectionTemp fetchByUuid_First(String uuid,
		OrderByComparator<DictCollectionTemp> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last dict collection temp in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict collection temp
	* @throws NoSuchDictCollectionTempException if a matching dict collection temp could not be found
	*/
	public static DictCollectionTemp findByUuid_Last(String uuid,
		OrderByComparator<DictCollectionTemp> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchDictCollectionTempException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last dict collection temp in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict collection temp, or <code>null</code> if a matching dict collection temp could not be found
	*/
	public static DictCollectionTemp fetchByUuid_Last(String uuid,
		OrderByComparator<DictCollectionTemp> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the dict collection temps before and after the current dict collection temp in the ordered set where uuid = &#63;.
	*
	* @param dictCollectionId the primary key of the current dict collection temp
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict collection temp
	* @throws NoSuchDictCollectionTempException if a dict collection temp with the primary key could not be found
	*/
	public static DictCollectionTemp[] findByUuid_PrevAndNext(
		long dictCollectionId, String uuid,
		OrderByComparator<DictCollectionTemp> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchDictCollectionTempException {
		return getPersistence()
				   .findByUuid_PrevAndNext(dictCollectionId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the dict collection temps where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of dict collection temps where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching dict collection temps
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the dict collection temp where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDictCollectionTempException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dict collection temp
	* @throws NoSuchDictCollectionTempException if a matching dict collection temp could not be found
	*/
	public static DictCollectionTemp findByUUID_G(String uuid, long groupId)
		throws org.opencps.synchronization.exception.NoSuchDictCollectionTempException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the dict collection temp where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dict collection temp, or <code>null</code> if a matching dict collection temp could not be found
	*/
	public static DictCollectionTemp fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the dict collection temp where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dict collection temp, or <code>null</code> if a matching dict collection temp could not be found
	*/
	public static DictCollectionTemp fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the dict collection temp where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the dict collection temp that was removed
	*/
	public static DictCollectionTemp removeByUUID_G(String uuid, long groupId)
		throws org.opencps.synchronization.exception.NoSuchDictCollectionTempException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of dict collection temps where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching dict collection temps
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the dict collection temps where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching dict collection temps
	*/
	public static List<DictCollectionTemp> findByUuid_C(String uuid,
		long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the dict collection temps where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dict collection temps
	* @param end the upper bound of the range of dict collection temps (not inclusive)
	* @return the range of matching dict collection temps
	*/
	public static List<DictCollectionTemp> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the dict collection temps where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dict collection temps
	* @param end the upper bound of the range of dict collection temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict collection temps
	*/
	public static List<DictCollectionTemp> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<DictCollectionTemp> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dict collection temps where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dict collection temps
	* @param end the upper bound of the range of dict collection temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict collection temps
	*/
	public static List<DictCollectionTemp> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<DictCollectionTemp> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dict collection temp in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict collection temp
	* @throws NoSuchDictCollectionTempException if a matching dict collection temp could not be found
	*/
	public static DictCollectionTemp findByUuid_C_First(String uuid,
		long companyId, OrderByComparator<DictCollectionTemp> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchDictCollectionTempException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first dict collection temp in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict collection temp, or <code>null</code> if a matching dict collection temp could not be found
	*/
	public static DictCollectionTemp fetchByUuid_C_First(String uuid,
		long companyId, OrderByComparator<DictCollectionTemp> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last dict collection temp in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict collection temp
	* @throws NoSuchDictCollectionTempException if a matching dict collection temp could not be found
	*/
	public static DictCollectionTemp findByUuid_C_Last(String uuid,
		long companyId, OrderByComparator<DictCollectionTemp> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchDictCollectionTempException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last dict collection temp in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict collection temp, or <code>null</code> if a matching dict collection temp could not be found
	*/
	public static DictCollectionTemp fetchByUuid_C_Last(String uuid,
		long companyId, OrderByComparator<DictCollectionTemp> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the dict collection temps before and after the current dict collection temp in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param dictCollectionId the primary key of the current dict collection temp
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict collection temp
	* @throws NoSuchDictCollectionTempException if a dict collection temp with the primary key could not be found
	*/
	public static DictCollectionTemp[] findByUuid_C_PrevAndNext(
		long dictCollectionId, String uuid, long companyId,
		OrderByComparator<DictCollectionTemp> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchDictCollectionTempException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(dictCollectionId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the dict collection temps where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of dict collection temps where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching dict collection temps
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns the dict collection temp where collectionCode = &#63; and groupId = &#63; or throws a {@link NoSuchDictCollectionTempException} if it could not be found.
	*
	* @param collectionCode the collection code
	* @param groupId the group ID
	* @return the matching dict collection temp
	* @throws NoSuchDictCollectionTempException if a matching dict collection temp could not be found
	*/
	public static DictCollectionTemp findByF_dictCollectionCode(
		String collectionCode, long groupId)
		throws org.opencps.synchronization.exception.NoSuchDictCollectionTempException {
		return getPersistence()
				   .findByF_dictCollectionCode(collectionCode, groupId);
	}

	/**
	* Returns the dict collection temp where collectionCode = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param collectionCode the collection code
	* @param groupId the group ID
	* @return the matching dict collection temp, or <code>null</code> if a matching dict collection temp could not be found
	*/
	public static DictCollectionTemp fetchByF_dictCollectionCode(
		String collectionCode, long groupId) {
		return getPersistence()
				   .fetchByF_dictCollectionCode(collectionCode, groupId);
	}

	/**
	* Returns the dict collection temp where collectionCode = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param collectionCode the collection code
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dict collection temp, or <code>null</code> if a matching dict collection temp could not be found
	*/
	public static DictCollectionTemp fetchByF_dictCollectionCode(
		String collectionCode, long groupId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_dictCollectionCode(collectionCode, groupId,
			retrieveFromCache);
	}

	/**
	* Removes the dict collection temp where collectionCode = &#63; and groupId = &#63; from the database.
	*
	* @param collectionCode the collection code
	* @param groupId the group ID
	* @return the dict collection temp that was removed
	*/
	public static DictCollectionTemp removeByF_dictCollectionCode(
		String collectionCode, long groupId)
		throws org.opencps.synchronization.exception.NoSuchDictCollectionTempException {
		return getPersistence()
				   .removeByF_dictCollectionCode(collectionCode, groupId);
	}

	/**
	* Returns the number of dict collection temps where collectionCode = &#63; and groupId = &#63;.
	*
	* @param collectionCode the collection code
	* @param groupId the group ID
	* @return the number of matching dict collection temps
	*/
	public static int countByF_dictCollectionCode(String collectionCode,
		long groupId) {
		return getPersistence()
				   .countByF_dictCollectionCode(collectionCode, groupId);
	}

	/**
	* Returns all the dict collection temps where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching dict collection temps
	*/
	public static List<DictCollectionTemp> findByF_dictCollectionByGroup(
		long groupId) {
		return getPersistence().findByF_dictCollectionByGroup(groupId);
	}

	/**
	* Returns a range of all the dict collection temps where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of dict collection temps
	* @param end the upper bound of the range of dict collection temps (not inclusive)
	* @return the range of matching dict collection temps
	*/
	public static List<DictCollectionTemp> findByF_dictCollectionByGroup(
		long groupId, int start, int end) {
		return getPersistence()
				   .findByF_dictCollectionByGroup(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the dict collection temps where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of dict collection temps
	* @param end the upper bound of the range of dict collection temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict collection temps
	*/
	public static List<DictCollectionTemp> findByF_dictCollectionByGroup(
		long groupId, int start, int end,
		OrderByComparator<DictCollectionTemp> orderByComparator) {
		return getPersistence()
				   .findByF_dictCollectionByGroup(groupId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dict collection temps where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of dict collection temps
	* @param end the upper bound of the range of dict collection temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict collection temps
	*/
	public static List<DictCollectionTemp> findByF_dictCollectionByGroup(
		long groupId, int start, int end,
		OrderByComparator<DictCollectionTemp> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_dictCollectionByGroup(groupId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dict collection temp in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict collection temp
	* @throws NoSuchDictCollectionTempException if a matching dict collection temp could not be found
	*/
	public static DictCollectionTemp findByF_dictCollectionByGroup_First(
		long groupId, OrderByComparator<DictCollectionTemp> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchDictCollectionTempException {
		return getPersistence()
				   .findByF_dictCollectionByGroup_First(groupId,
			orderByComparator);
	}

	/**
	* Returns the first dict collection temp in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict collection temp, or <code>null</code> if a matching dict collection temp could not be found
	*/
	public static DictCollectionTemp fetchByF_dictCollectionByGroup_First(
		long groupId, OrderByComparator<DictCollectionTemp> orderByComparator) {
		return getPersistence()
				   .fetchByF_dictCollectionByGroup_First(groupId,
			orderByComparator);
	}

	/**
	* Returns the last dict collection temp in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict collection temp
	* @throws NoSuchDictCollectionTempException if a matching dict collection temp could not be found
	*/
	public static DictCollectionTemp findByF_dictCollectionByGroup_Last(
		long groupId, OrderByComparator<DictCollectionTemp> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchDictCollectionTempException {
		return getPersistence()
				   .findByF_dictCollectionByGroup_Last(groupId,
			orderByComparator);
	}

	/**
	* Returns the last dict collection temp in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict collection temp, or <code>null</code> if a matching dict collection temp could not be found
	*/
	public static DictCollectionTemp fetchByF_dictCollectionByGroup_Last(
		long groupId, OrderByComparator<DictCollectionTemp> orderByComparator) {
		return getPersistence()
				   .fetchByF_dictCollectionByGroup_Last(groupId,
			orderByComparator);
	}

	/**
	* Returns the dict collection temps before and after the current dict collection temp in the ordered set where groupId = &#63;.
	*
	* @param dictCollectionId the primary key of the current dict collection temp
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict collection temp
	* @throws NoSuchDictCollectionTempException if a dict collection temp with the primary key could not be found
	*/
	public static DictCollectionTemp[] findByF_dictCollectionByGroup_PrevAndNext(
		long dictCollectionId, long groupId,
		OrderByComparator<DictCollectionTemp> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchDictCollectionTempException {
		return getPersistence()
				   .findByF_dictCollectionByGroup_PrevAndNext(dictCollectionId,
			groupId, orderByComparator);
	}

	/**
	* Removes all the dict collection temps where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByF_dictCollectionByGroup(long groupId) {
		getPersistence().removeByF_dictCollectionByGroup(groupId);
	}

	/**
	* Returns the number of dict collection temps where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching dict collection temps
	*/
	public static int countByF_dictCollectionByGroup(long groupId) {
		return getPersistence().countByF_dictCollectionByGroup(groupId);
	}

	/**
	* Returns all the dict collection temps where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @return the matching dict collection temps
	*/
	public static List<DictCollectionTemp> findByF_dictCollectionNewerThan(
		Date modifiedDate, long groupId) {
		return getPersistence()
				   .findByF_dictCollectionNewerThan(modifiedDate, groupId);
	}

	/**
	* Returns a range of all the dict collection temps where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param start the lower bound of the range of dict collection temps
	* @param end the upper bound of the range of dict collection temps (not inclusive)
	* @return the range of matching dict collection temps
	*/
	public static List<DictCollectionTemp> findByF_dictCollectionNewerThan(
		Date modifiedDate, long groupId, int start, int end) {
		return getPersistence()
				   .findByF_dictCollectionNewerThan(modifiedDate, groupId,
			start, end);
	}

	/**
	* Returns an ordered range of all the dict collection temps where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param start the lower bound of the range of dict collection temps
	* @param end the upper bound of the range of dict collection temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dict collection temps
	*/
	public static List<DictCollectionTemp> findByF_dictCollectionNewerThan(
		Date modifiedDate, long groupId, int start, int end,
		OrderByComparator<DictCollectionTemp> orderByComparator) {
		return getPersistence()
				   .findByF_dictCollectionNewerThan(modifiedDate, groupId,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dict collection temps where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param start the lower bound of the range of dict collection temps
	* @param end the upper bound of the range of dict collection temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dict collection temps
	*/
	public static List<DictCollectionTemp> findByF_dictCollectionNewerThan(
		Date modifiedDate, long groupId, int start, int end,
		OrderByComparator<DictCollectionTemp> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_dictCollectionNewerThan(modifiedDate, groupId,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dict collection temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict collection temp
	* @throws NoSuchDictCollectionTempException if a matching dict collection temp could not be found
	*/
	public static DictCollectionTemp findByF_dictCollectionNewerThan_First(
		Date modifiedDate, long groupId,
		OrderByComparator<DictCollectionTemp> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchDictCollectionTempException {
		return getPersistence()
				   .findByF_dictCollectionNewerThan_First(modifiedDate,
			groupId, orderByComparator);
	}

	/**
	* Returns the first dict collection temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dict collection temp, or <code>null</code> if a matching dict collection temp could not be found
	*/
	public static DictCollectionTemp fetchByF_dictCollectionNewerThan_First(
		Date modifiedDate, long groupId,
		OrderByComparator<DictCollectionTemp> orderByComparator) {
		return getPersistence()
				   .fetchByF_dictCollectionNewerThan_First(modifiedDate,
			groupId, orderByComparator);
	}

	/**
	* Returns the last dict collection temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict collection temp
	* @throws NoSuchDictCollectionTempException if a matching dict collection temp could not be found
	*/
	public static DictCollectionTemp findByF_dictCollectionNewerThan_Last(
		Date modifiedDate, long groupId,
		OrderByComparator<DictCollectionTemp> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchDictCollectionTempException {
		return getPersistence()
				   .findByF_dictCollectionNewerThan_Last(modifiedDate, groupId,
			orderByComparator);
	}

	/**
	* Returns the last dict collection temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dict collection temp, or <code>null</code> if a matching dict collection temp could not be found
	*/
	public static DictCollectionTemp fetchByF_dictCollectionNewerThan_Last(
		Date modifiedDate, long groupId,
		OrderByComparator<DictCollectionTemp> orderByComparator) {
		return getPersistence()
				   .fetchByF_dictCollectionNewerThan_Last(modifiedDate,
			groupId, orderByComparator);
	}

	/**
	* Returns the dict collection temps before and after the current dict collection temp in the ordered set where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param dictCollectionId the primary key of the current dict collection temp
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dict collection temp
	* @throws NoSuchDictCollectionTempException if a dict collection temp with the primary key could not be found
	*/
	public static DictCollectionTemp[] findByF_dictCollectionNewerThan_PrevAndNext(
		long dictCollectionId, Date modifiedDate, long groupId,
		OrderByComparator<DictCollectionTemp> orderByComparator)
		throws org.opencps.synchronization.exception.NoSuchDictCollectionTempException {
		return getPersistence()
				   .findByF_dictCollectionNewerThan_PrevAndNext(dictCollectionId,
			modifiedDate, groupId, orderByComparator);
	}

	/**
	* Removes all the dict collection temps where modifiedDate &ge; &#63; and groupId = &#63; from the database.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	*/
	public static void removeByF_dictCollectionNewerThan(Date modifiedDate,
		long groupId) {
		getPersistence().removeByF_dictCollectionNewerThan(modifiedDate, groupId);
	}

	/**
	* Returns the number of dict collection temps where modifiedDate &ge; &#63; and groupId = &#63;.
	*
	* @param modifiedDate the modified date
	* @param groupId the group ID
	* @return the number of matching dict collection temps
	*/
	public static int countByF_dictCollectionNewerThan(Date modifiedDate,
		long groupId) {
		return getPersistence()
				   .countByF_dictCollectionNewerThan(modifiedDate, groupId);
	}

	/**
	* Caches the dict collection temp in the entity cache if it is enabled.
	*
	* @param dictCollectionTemp the dict collection temp
	*/
	public static void cacheResult(DictCollectionTemp dictCollectionTemp) {
		getPersistence().cacheResult(dictCollectionTemp);
	}

	/**
	* Caches the dict collection temps in the entity cache if it is enabled.
	*
	* @param dictCollectionTemps the dict collection temps
	*/
	public static void cacheResult(List<DictCollectionTemp> dictCollectionTemps) {
		getPersistence().cacheResult(dictCollectionTemps);
	}

	/**
	* Creates a new dict collection temp with the primary key. Does not add the dict collection temp to the database.
	*
	* @param dictCollectionId the primary key for the new dict collection temp
	* @return the new dict collection temp
	*/
	public static DictCollectionTemp create(long dictCollectionId) {
		return getPersistence().create(dictCollectionId);
	}

	/**
	* Removes the dict collection temp with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dictCollectionId the primary key of the dict collection temp
	* @return the dict collection temp that was removed
	* @throws NoSuchDictCollectionTempException if a dict collection temp with the primary key could not be found
	*/
	public static DictCollectionTemp remove(long dictCollectionId)
		throws org.opencps.synchronization.exception.NoSuchDictCollectionTempException {
		return getPersistence().remove(dictCollectionId);
	}

	public static DictCollectionTemp updateImpl(
		DictCollectionTemp dictCollectionTemp) {
		return getPersistence().updateImpl(dictCollectionTemp);
	}

	/**
	* Returns the dict collection temp with the primary key or throws a {@link NoSuchDictCollectionTempException} if it could not be found.
	*
	* @param dictCollectionId the primary key of the dict collection temp
	* @return the dict collection temp
	* @throws NoSuchDictCollectionTempException if a dict collection temp with the primary key could not be found
	*/
	public static DictCollectionTemp findByPrimaryKey(long dictCollectionId)
		throws org.opencps.synchronization.exception.NoSuchDictCollectionTempException {
		return getPersistence().findByPrimaryKey(dictCollectionId);
	}

	/**
	* Returns the dict collection temp with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dictCollectionId the primary key of the dict collection temp
	* @return the dict collection temp, or <code>null</code> if a dict collection temp with the primary key could not be found
	*/
	public static DictCollectionTemp fetchByPrimaryKey(long dictCollectionId) {
		return getPersistence().fetchByPrimaryKey(dictCollectionId);
	}

	public static java.util.Map<java.io.Serializable, DictCollectionTemp> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the dict collection temps.
	*
	* @return the dict collection temps
	*/
	public static List<DictCollectionTemp> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the dict collection temps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict collection temps
	* @param end the upper bound of the range of dict collection temps (not inclusive)
	* @return the range of dict collection temps
	*/
	public static List<DictCollectionTemp> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the dict collection temps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict collection temps
	* @param end the upper bound of the range of dict collection temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dict collection temps
	*/
	public static List<DictCollectionTemp> findAll(int start, int end,
		OrderByComparator<DictCollectionTemp> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dict collection temps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DictCollectionTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict collection temps
	* @param end the upper bound of the range of dict collection temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dict collection temps
	*/
	public static List<DictCollectionTemp> findAll(int start, int end,
		OrderByComparator<DictCollectionTemp> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the dict collection temps from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of dict collection temps.
	*
	* @return the number of dict collection temps
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static DictCollectionTempPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DictCollectionTempPersistence, DictCollectionTempPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DictCollectionTempPersistence.class);

		ServiceTracker<DictCollectionTempPersistence, DictCollectionTempPersistence> serviceTracker =
			new ServiceTracker<DictCollectionTempPersistence, DictCollectionTempPersistence>(bundle.getBundleContext(),
				DictCollectionTempPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}