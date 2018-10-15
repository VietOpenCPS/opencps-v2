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

import org.opencps.dossiermgt.model.RegistrationLog;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the registration log service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.RegistrationLogPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see RegistrationLogPersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.RegistrationLogPersistenceImpl
 * @generated
 */
@ProviderType
public class RegistrationLogUtil {
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
	public static void clearCache(RegistrationLog registrationLog) {
		getPersistence().clearCache(registrationLog);
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
	public static List<RegistrationLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<RegistrationLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<RegistrationLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<RegistrationLog> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static RegistrationLog update(RegistrationLog registrationLog) {
		return getPersistence().update(registrationLog);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static RegistrationLog update(RegistrationLog registrationLog,
		ServiceContext serviceContext) {
		return getPersistence().update(registrationLog, serviceContext);
	}

	/**
	* Returns all the registration logs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching registration logs
	*/
	public static List<RegistrationLog> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the registration logs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of registration logs
	* @param end the upper bound of the range of registration logs (not inclusive)
	* @return the range of matching registration logs
	*/
	public static List<RegistrationLog> findByUuid(String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the registration logs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of registration logs
	* @param end the upper bound of the range of registration logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching registration logs
	*/
	public static List<RegistrationLog> findByUuid(String uuid, int start,
		int end, OrderByComparator<RegistrationLog> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the registration logs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of registration logs
	* @param end the upper bound of the range of registration logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching registration logs
	*/
	public static List<RegistrationLog> findByUuid(String uuid, int start,
		int end, OrderByComparator<RegistrationLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first registration log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration log
	* @throws NoSuchRegistrationLogException if a matching registration log could not be found
	*/
	public static RegistrationLog findByUuid_First(String uuid,
		OrderByComparator<RegistrationLog> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationLogException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first registration log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration log, or <code>null</code> if a matching registration log could not be found
	*/
	public static RegistrationLog fetchByUuid_First(String uuid,
		OrderByComparator<RegistrationLog> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last registration log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration log
	* @throws NoSuchRegistrationLogException if a matching registration log could not be found
	*/
	public static RegistrationLog findByUuid_Last(String uuid,
		OrderByComparator<RegistrationLog> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationLogException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last registration log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration log, or <code>null</code> if a matching registration log could not be found
	*/
	public static RegistrationLog fetchByUuid_Last(String uuid,
		OrderByComparator<RegistrationLog> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the registration logs before and after the current registration log in the ordered set where uuid = &#63;.
	*
	* @param registrationLogId the primary key of the current registration log
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next registration log
	* @throws NoSuchRegistrationLogException if a registration log with the primary key could not be found
	*/
	public static RegistrationLog[] findByUuid_PrevAndNext(
		long registrationLogId, String uuid,
		OrderByComparator<RegistrationLog> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationLogException {
		return getPersistence()
				   .findByUuid_PrevAndNext(registrationLogId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the registration logs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of registration logs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching registration logs
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the registration log where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchRegistrationLogException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching registration log
	* @throws NoSuchRegistrationLogException if a matching registration log could not be found
	*/
	public static RegistrationLog findByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationLogException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the registration log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching registration log, or <code>null</code> if a matching registration log could not be found
	*/
	public static RegistrationLog fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the registration log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching registration log, or <code>null</code> if a matching registration log could not be found
	*/
	public static RegistrationLog fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the registration log where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the registration log that was removed
	*/
	public static RegistrationLog removeByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationLogException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of registration logs where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching registration logs
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the registration logs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching registration logs
	*/
	public static List<RegistrationLog> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the registration logs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of registration logs
	* @param end the upper bound of the range of registration logs (not inclusive)
	* @return the range of matching registration logs
	*/
	public static List<RegistrationLog> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the registration logs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of registration logs
	* @param end the upper bound of the range of registration logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching registration logs
	*/
	public static List<RegistrationLog> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<RegistrationLog> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the registration logs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of registration logs
	* @param end the upper bound of the range of registration logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching registration logs
	*/
	public static List<RegistrationLog> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<RegistrationLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first registration log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration log
	* @throws NoSuchRegistrationLogException if a matching registration log could not be found
	*/
	public static RegistrationLog findByUuid_C_First(String uuid,
		long companyId, OrderByComparator<RegistrationLog> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationLogException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first registration log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration log, or <code>null</code> if a matching registration log could not be found
	*/
	public static RegistrationLog fetchByUuid_C_First(String uuid,
		long companyId, OrderByComparator<RegistrationLog> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last registration log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration log
	* @throws NoSuchRegistrationLogException if a matching registration log could not be found
	*/
	public static RegistrationLog findByUuid_C_Last(String uuid,
		long companyId, OrderByComparator<RegistrationLog> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationLogException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last registration log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration log, or <code>null</code> if a matching registration log could not be found
	*/
	public static RegistrationLog fetchByUuid_C_Last(String uuid,
		long companyId, OrderByComparator<RegistrationLog> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the registration logs before and after the current registration log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param registrationLogId the primary key of the current registration log
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next registration log
	* @throws NoSuchRegistrationLogException if a registration log with the primary key could not be found
	*/
	public static RegistrationLog[] findByUuid_C_PrevAndNext(
		long registrationLogId, String uuid, long companyId,
		OrderByComparator<RegistrationLog> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationLogException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(registrationLogId, uuid,
			companyId, orderByComparator);
	}

	/**
	* Removes all the registration logs where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of registration logs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching registration logs
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the registration logs where groupId = &#63; and registrationId = &#63;.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @return the matching registration logs
	*/
	public static List<RegistrationLog> findByG_REGID(long groupId,
		long registrationId) {
		return getPersistence().findByG_REGID(groupId, registrationId);
	}

	/**
	* Returns a range of all the registration logs where groupId = &#63; and registrationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param start the lower bound of the range of registration logs
	* @param end the upper bound of the range of registration logs (not inclusive)
	* @return the range of matching registration logs
	*/
	public static List<RegistrationLog> findByG_REGID(long groupId,
		long registrationId, int start, int end) {
		return getPersistence()
				   .findByG_REGID(groupId, registrationId, start, end);
	}

	/**
	* Returns an ordered range of all the registration logs where groupId = &#63; and registrationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param start the lower bound of the range of registration logs
	* @param end the upper bound of the range of registration logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching registration logs
	*/
	public static List<RegistrationLog> findByG_REGID(long groupId,
		long registrationId, int start, int end,
		OrderByComparator<RegistrationLog> orderByComparator) {
		return getPersistence()
				   .findByG_REGID(groupId, registrationId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the registration logs where groupId = &#63; and registrationId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param start the lower bound of the range of registration logs
	* @param end the upper bound of the range of registration logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching registration logs
	*/
	public static List<RegistrationLog> findByG_REGID(long groupId,
		long registrationId, int start, int end,
		OrderByComparator<RegistrationLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_REGID(groupId, registrationId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first registration log in the ordered set where groupId = &#63; and registrationId = &#63;.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration log
	* @throws NoSuchRegistrationLogException if a matching registration log could not be found
	*/
	public static RegistrationLog findByG_REGID_First(long groupId,
		long registrationId,
		OrderByComparator<RegistrationLog> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationLogException {
		return getPersistence()
				   .findByG_REGID_First(groupId, registrationId,
			orderByComparator);
	}

	/**
	* Returns the first registration log in the ordered set where groupId = &#63; and registrationId = &#63;.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching registration log, or <code>null</code> if a matching registration log could not be found
	*/
	public static RegistrationLog fetchByG_REGID_First(long groupId,
		long registrationId,
		OrderByComparator<RegistrationLog> orderByComparator) {
		return getPersistence()
				   .fetchByG_REGID_First(groupId, registrationId,
			orderByComparator);
	}

	/**
	* Returns the last registration log in the ordered set where groupId = &#63; and registrationId = &#63;.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration log
	* @throws NoSuchRegistrationLogException if a matching registration log could not be found
	*/
	public static RegistrationLog findByG_REGID_Last(long groupId,
		long registrationId,
		OrderByComparator<RegistrationLog> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationLogException {
		return getPersistence()
				   .findByG_REGID_Last(groupId, registrationId,
			orderByComparator);
	}

	/**
	* Returns the last registration log in the ordered set where groupId = &#63; and registrationId = &#63;.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching registration log, or <code>null</code> if a matching registration log could not be found
	*/
	public static RegistrationLog fetchByG_REGID_Last(long groupId,
		long registrationId,
		OrderByComparator<RegistrationLog> orderByComparator) {
		return getPersistence()
				   .fetchByG_REGID_Last(groupId, registrationId,
			orderByComparator);
	}

	/**
	* Returns the registration logs before and after the current registration log in the ordered set where groupId = &#63; and registrationId = &#63;.
	*
	* @param registrationLogId the primary key of the current registration log
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next registration log
	* @throws NoSuchRegistrationLogException if a registration log with the primary key could not be found
	*/
	public static RegistrationLog[] findByG_REGID_PrevAndNext(
		long registrationLogId, long groupId, long registrationId,
		OrderByComparator<RegistrationLog> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationLogException {
		return getPersistence()
				   .findByG_REGID_PrevAndNext(registrationLogId, groupId,
			registrationId, orderByComparator);
	}

	/**
	* Removes all the registration logs where groupId = &#63; and registrationId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	*/
	public static void removeByG_REGID(long groupId, long registrationId) {
		getPersistence().removeByG_REGID(groupId, registrationId);
	}

	/**
	* Returns the number of registration logs where groupId = &#63; and registrationId = &#63;.
	*
	* @param groupId the group ID
	* @param registrationId the registration ID
	* @return the number of matching registration logs
	*/
	public static int countByG_REGID(long groupId, long registrationId) {
		return getPersistence().countByG_REGID(groupId, registrationId);
	}

	/**
	* Caches the registration log in the entity cache if it is enabled.
	*
	* @param registrationLog the registration log
	*/
	public static void cacheResult(RegistrationLog registrationLog) {
		getPersistence().cacheResult(registrationLog);
	}

	/**
	* Caches the registration logs in the entity cache if it is enabled.
	*
	* @param registrationLogs the registration logs
	*/
	public static void cacheResult(List<RegistrationLog> registrationLogs) {
		getPersistence().cacheResult(registrationLogs);
	}

	/**
	* Creates a new registration log with the primary key. Does not add the registration log to the database.
	*
	* @param registrationLogId the primary key for the new registration log
	* @return the new registration log
	*/
	public static RegistrationLog create(long registrationLogId) {
		return getPersistence().create(registrationLogId);
	}

	/**
	* Removes the registration log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param registrationLogId the primary key of the registration log
	* @return the registration log that was removed
	* @throws NoSuchRegistrationLogException if a registration log with the primary key could not be found
	*/
	public static RegistrationLog remove(long registrationLogId)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationLogException {
		return getPersistence().remove(registrationLogId);
	}

	public static RegistrationLog updateImpl(RegistrationLog registrationLog) {
		return getPersistence().updateImpl(registrationLog);
	}

	/**
	* Returns the registration log with the primary key or throws a {@link NoSuchRegistrationLogException} if it could not be found.
	*
	* @param registrationLogId the primary key of the registration log
	* @return the registration log
	* @throws NoSuchRegistrationLogException if a registration log with the primary key could not be found
	*/
	public static RegistrationLog findByPrimaryKey(long registrationLogId)
		throws org.opencps.dossiermgt.exception.NoSuchRegistrationLogException {
		return getPersistence().findByPrimaryKey(registrationLogId);
	}

	/**
	* Returns the registration log with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param registrationLogId the primary key of the registration log
	* @return the registration log, or <code>null</code> if a registration log with the primary key could not be found
	*/
	public static RegistrationLog fetchByPrimaryKey(long registrationLogId) {
		return getPersistence().fetchByPrimaryKey(registrationLogId);
	}

	public static java.util.Map<java.io.Serializable, RegistrationLog> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the registration logs.
	*
	* @return the registration logs
	*/
	public static List<RegistrationLog> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the registration logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of registration logs
	* @param end the upper bound of the range of registration logs (not inclusive)
	* @return the range of registration logs
	*/
	public static List<RegistrationLog> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the registration logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of registration logs
	* @param end the upper bound of the range of registration logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of registration logs
	*/
	public static List<RegistrationLog> findAll(int start, int end,
		OrderByComparator<RegistrationLog> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the registration logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link RegistrationLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of registration logs
	* @param end the upper bound of the range of registration logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of registration logs
	*/
	public static List<RegistrationLog> findAll(int start, int end,
		OrderByComparator<RegistrationLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the registration logs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of registration logs.
	*
	* @return the number of registration logs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static RegistrationLogPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<RegistrationLogPersistence, RegistrationLogPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(RegistrationLogPersistence.class);

		ServiceTracker<RegistrationLogPersistence, RegistrationLogPersistence> serviceTracker =
			new ServiceTracker<RegistrationLogPersistence, RegistrationLogPersistence>(bundle.getBundleContext(),
				RegistrationLogPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}