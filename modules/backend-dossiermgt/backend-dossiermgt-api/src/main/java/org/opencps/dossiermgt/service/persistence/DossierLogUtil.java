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

import org.opencps.dossiermgt.model.DossierLog;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the dossier log service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.DossierLogPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see DossierLogPersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.DossierLogPersistenceImpl
 * @generated
 */
@ProviderType
public class DossierLogUtil {
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
	public static void clearCache(DossierLog dossierLog) {
		getPersistence().clearCache(dossierLog);
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
	public static List<DossierLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DossierLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DossierLog> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<DossierLog> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static DossierLog update(DossierLog dossierLog) {
		return getPersistence().update(dossierLog);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static DossierLog update(DossierLog dossierLog,
		ServiceContext serviceContext) {
		return getPersistence().update(dossierLog, serviceContext);
	}

	/**
	* Returns all the dossier logs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching dossier logs
	*/
	public static List<DossierLog> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the dossier logs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier logs
	* @param end the upper bound of the range of dossier logs (not inclusive)
	* @return the range of matching dossier logs
	*/
	public static List<DossierLog> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the dossier logs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier logs
	* @param end the upper bound of the range of dossier logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier logs
	*/
	public static List<DossierLog> findByUuid(String uuid, int start, int end,
		OrderByComparator<DossierLog> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier logs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dossier logs
	* @param end the upper bound of the range of dossier logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier logs
	*/
	public static List<DossierLog> findByUuid(String uuid, int start, int end,
		OrderByComparator<DossierLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first dossier log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier log
	* @throws NoSuchDossierLogException if a matching dossier log could not be found
	*/
	public static DossierLog findByUuid_First(String uuid,
		OrderByComparator<DossierLog> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierLogException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first dossier log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier log, or <code>null</code> if a matching dossier log could not be found
	*/
	public static DossierLog fetchByUuid_First(String uuid,
		OrderByComparator<DossierLog> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last dossier log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier log
	* @throws NoSuchDossierLogException if a matching dossier log could not be found
	*/
	public static DossierLog findByUuid_Last(String uuid,
		OrderByComparator<DossierLog> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierLogException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last dossier log in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier log, or <code>null</code> if a matching dossier log could not be found
	*/
	public static DossierLog fetchByUuid_Last(String uuid,
		OrderByComparator<DossierLog> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the dossier logs before and after the current dossier log in the ordered set where uuid = &#63;.
	*
	* @param dossierLogId the primary key of the current dossier log
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier log
	* @throws NoSuchDossierLogException if a dossier log with the primary key could not be found
	*/
	public static DossierLog[] findByUuid_PrevAndNext(long dossierLogId,
		String uuid, OrderByComparator<DossierLog> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierLogException {
		return getPersistence()
				   .findByUuid_PrevAndNext(dossierLogId, uuid, orderByComparator);
	}

	/**
	* Removes all the dossier logs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of dossier logs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching dossier logs
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the dossier log where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDossierLogException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier log
	* @throws NoSuchDossierLogException if a matching dossier log could not be found
	*/
	public static DossierLog findByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierLogException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the dossier log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dossier log, or <code>null</code> if a matching dossier log could not be found
	*/
	public static DossierLog fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the dossier log where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dossier log, or <code>null</code> if a matching dossier log could not be found
	*/
	public static DossierLog fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the dossier log where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the dossier log that was removed
	*/
	public static DossierLog removeByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierLogException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of dossier logs where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching dossier logs
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the dossier logs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching dossier logs
	*/
	public static List<DossierLog> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the dossier logs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier logs
	* @param end the upper bound of the range of dossier logs (not inclusive)
	* @return the range of matching dossier logs
	*/
	public static List<DossierLog> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the dossier logs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier logs
	* @param end the upper bound of the range of dossier logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier logs
	*/
	public static List<DossierLog> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<DossierLog> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier logs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dossier logs
	* @param end the upper bound of the range of dossier logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier logs
	*/
	public static List<DossierLog> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<DossierLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier log
	* @throws NoSuchDossierLogException if a matching dossier log could not be found
	*/
	public static DossierLog findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DossierLog> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierLogException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first dossier log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier log, or <code>null</code> if a matching dossier log could not be found
	*/
	public static DossierLog fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<DossierLog> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last dossier log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier log
	* @throws NoSuchDossierLogException if a matching dossier log could not be found
	*/
	public static DossierLog findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DossierLog> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierLogException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last dossier log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier log, or <code>null</code> if a matching dossier log could not be found
	*/
	public static DossierLog fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<DossierLog> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the dossier logs before and after the current dossier log in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param dossierLogId the primary key of the current dossier log
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier log
	* @throws NoSuchDossierLogException if a dossier log with the primary key could not be found
	*/
	public static DossierLog[] findByUuid_C_PrevAndNext(long dossierLogId,
		String uuid, long companyId,
		OrderByComparator<DossierLog> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierLogException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(dossierLogId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the dossier logs where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of dossier logs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching dossier logs
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the dossier logs where notificationType = &#63;.
	*
	* @param notificationType the notification type
	* @return the matching dossier logs
	*/
	public static List<DossierLog> findByNotiType(String notificationType) {
		return getPersistence().findByNotiType(notificationType);
	}

	/**
	* Returns a range of all the dossier logs where notificationType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param notificationType the notification type
	* @param start the lower bound of the range of dossier logs
	* @param end the upper bound of the range of dossier logs (not inclusive)
	* @return the range of matching dossier logs
	*/
	public static List<DossierLog> findByNotiType(String notificationType,
		int start, int end) {
		return getPersistence().findByNotiType(notificationType, start, end);
	}

	/**
	* Returns an ordered range of all the dossier logs where notificationType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param notificationType the notification type
	* @param start the lower bound of the range of dossier logs
	* @param end the upper bound of the range of dossier logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier logs
	*/
	public static List<DossierLog> findByNotiType(String notificationType,
		int start, int end, OrderByComparator<DossierLog> orderByComparator) {
		return getPersistence()
				   .findByNotiType(notificationType, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier logs where notificationType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param notificationType the notification type
	* @param start the lower bound of the range of dossier logs
	* @param end the upper bound of the range of dossier logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier logs
	*/
	public static List<DossierLog> findByNotiType(String notificationType,
		int start, int end, OrderByComparator<DossierLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByNotiType(notificationType, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier log in the ordered set where notificationType = &#63;.
	*
	* @param notificationType the notification type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier log
	* @throws NoSuchDossierLogException if a matching dossier log could not be found
	*/
	public static DossierLog findByNotiType_First(String notificationType,
		OrderByComparator<DossierLog> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierLogException {
		return getPersistence()
				   .findByNotiType_First(notificationType, orderByComparator);
	}

	/**
	* Returns the first dossier log in the ordered set where notificationType = &#63;.
	*
	* @param notificationType the notification type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier log, or <code>null</code> if a matching dossier log could not be found
	*/
	public static DossierLog fetchByNotiType_First(String notificationType,
		OrderByComparator<DossierLog> orderByComparator) {
		return getPersistence()
				   .fetchByNotiType_First(notificationType, orderByComparator);
	}

	/**
	* Returns the last dossier log in the ordered set where notificationType = &#63;.
	*
	* @param notificationType the notification type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier log
	* @throws NoSuchDossierLogException if a matching dossier log could not be found
	*/
	public static DossierLog findByNotiType_Last(String notificationType,
		OrderByComparator<DossierLog> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierLogException {
		return getPersistence()
				   .findByNotiType_Last(notificationType, orderByComparator);
	}

	/**
	* Returns the last dossier log in the ordered set where notificationType = &#63;.
	*
	* @param notificationType the notification type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier log, or <code>null</code> if a matching dossier log could not be found
	*/
	public static DossierLog fetchByNotiType_Last(String notificationType,
		OrderByComparator<DossierLog> orderByComparator) {
		return getPersistence()
				   .fetchByNotiType_Last(notificationType, orderByComparator);
	}

	/**
	* Returns the dossier logs before and after the current dossier log in the ordered set where notificationType = &#63;.
	*
	* @param dossierLogId the primary key of the current dossier log
	* @param notificationType the notification type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier log
	* @throws NoSuchDossierLogException if a dossier log with the primary key could not be found
	*/
	public static DossierLog[] findByNotiType_PrevAndNext(long dossierLogId,
		String notificationType, OrderByComparator<DossierLog> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierLogException {
		return getPersistence()
				   .findByNotiType_PrevAndNext(dossierLogId, notificationType,
			orderByComparator);
	}

	/**
	* Removes all the dossier logs where notificationType = &#63; from the database.
	*
	* @param notificationType the notification type
	*/
	public static void removeByNotiType(String notificationType) {
		getPersistence().removeByNotiType(notificationType);
	}

	/**
	* Returns the number of dossier logs where notificationType = &#63;.
	*
	* @param notificationType the notification type
	* @return the number of matching dossier logs
	*/
	public static int countByNotiType(String notificationType) {
		return getPersistence().countByNotiType(notificationType);
	}

	/**
	* Returns all the dossier logs where notificationType = &#63; and dossierId = &#63;.
	*
	* @param notificationType the notification type
	* @param dossierId the dossier ID
	* @return the matching dossier logs
	*/
	public static List<DossierLog> findByDID_NTYP(String notificationType,
		long dossierId) {
		return getPersistence().findByDID_NTYP(notificationType, dossierId);
	}

	/**
	* Returns a range of all the dossier logs where notificationType = &#63; and dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param notificationType the notification type
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of dossier logs
	* @param end the upper bound of the range of dossier logs (not inclusive)
	* @return the range of matching dossier logs
	*/
	public static List<DossierLog> findByDID_NTYP(String notificationType,
		long dossierId, int start, int end) {
		return getPersistence()
				   .findByDID_NTYP(notificationType, dossierId, start, end);
	}

	/**
	* Returns an ordered range of all the dossier logs where notificationType = &#63; and dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param notificationType the notification type
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of dossier logs
	* @param end the upper bound of the range of dossier logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier logs
	*/
	public static List<DossierLog> findByDID_NTYP(String notificationType,
		long dossierId, int start, int end,
		OrderByComparator<DossierLog> orderByComparator) {
		return getPersistence()
				   .findByDID_NTYP(notificationType, dossierId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier logs where notificationType = &#63; and dossierId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param notificationType the notification type
	* @param dossierId the dossier ID
	* @param start the lower bound of the range of dossier logs
	* @param end the upper bound of the range of dossier logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier logs
	*/
	public static List<DossierLog> findByDID_NTYP(String notificationType,
		long dossierId, int start, int end,
		OrderByComparator<DossierLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByDID_NTYP(notificationType, dossierId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first dossier log in the ordered set where notificationType = &#63; and dossierId = &#63;.
	*
	* @param notificationType the notification type
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier log
	* @throws NoSuchDossierLogException if a matching dossier log could not be found
	*/
	public static DossierLog findByDID_NTYP_First(String notificationType,
		long dossierId, OrderByComparator<DossierLog> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierLogException {
		return getPersistence()
				   .findByDID_NTYP_First(notificationType, dossierId,
			orderByComparator);
	}

	/**
	* Returns the first dossier log in the ordered set where notificationType = &#63; and dossierId = &#63;.
	*
	* @param notificationType the notification type
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier log, or <code>null</code> if a matching dossier log could not be found
	*/
	public static DossierLog fetchByDID_NTYP_First(String notificationType,
		long dossierId, OrderByComparator<DossierLog> orderByComparator) {
		return getPersistence()
				   .fetchByDID_NTYP_First(notificationType, dossierId,
			orderByComparator);
	}

	/**
	* Returns the last dossier log in the ordered set where notificationType = &#63; and dossierId = &#63;.
	*
	* @param notificationType the notification type
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier log
	* @throws NoSuchDossierLogException if a matching dossier log could not be found
	*/
	public static DossierLog findByDID_NTYP_Last(String notificationType,
		long dossierId, OrderByComparator<DossierLog> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierLogException {
		return getPersistence()
				   .findByDID_NTYP_Last(notificationType, dossierId,
			orderByComparator);
	}

	/**
	* Returns the last dossier log in the ordered set where notificationType = &#63; and dossierId = &#63;.
	*
	* @param notificationType the notification type
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier log, or <code>null</code> if a matching dossier log could not be found
	*/
	public static DossierLog fetchByDID_NTYP_Last(String notificationType,
		long dossierId, OrderByComparator<DossierLog> orderByComparator) {
		return getPersistence()
				   .fetchByDID_NTYP_Last(notificationType, dossierId,
			orderByComparator);
	}

	/**
	* Returns the dossier logs before and after the current dossier log in the ordered set where notificationType = &#63; and dossierId = &#63;.
	*
	* @param dossierLogId the primary key of the current dossier log
	* @param notificationType the notification type
	* @param dossierId the dossier ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier log
	* @throws NoSuchDossierLogException if a dossier log with the primary key could not be found
	*/
	public static DossierLog[] findByDID_NTYP_PrevAndNext(long dossierLogId,
		String notificationType, long dossierId,
		OrderByComparator<DossierLog> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierLogException {
		return getPersistence()
				   .findByDID_NTYP_PrevAndNext(dossierLogId, notificationType,
			dossierId, orderByComparator);
	}

	/**
	* Removes all the dossier logs where notificationType = &#63; and dossierId = &#63; from the database.
	*
	* @param notificationType the notification type
	* @param dossierId the dossier ID
	*/
	public static void removeByDID_NTYP(String notificationType, long dossierId) {
		getPersistence().removeByDID_NTYP(notificationType, dossierId);
	}

	/**
	* Returns the number of dossier logs where notificationType = &#63; and dossierId = &#63;.
	*
	* @param notificationType the notification type
	* @param dossierId the dossier ID
	* @return the number of matching dossier logs
	*/
	public static int countByDID_NTYP(String notificationType, long dossierId) {
		return getPersistence().countByDID_NTYP(notificationType, dossierId);
	}

	/**
	* Returns all the dossier logs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching dossier logs
	*/
	public static List<DossierLog> findByG(long groupId) {
		return getPersistence().findByG(groupId);
	}

	/**
	* Returns a range of all the dossier logs where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of dossier logs
	* @param end the upper bound of the range of dossier logs (not inclusive)
	* @return the range of matching dossier logs
	*/
	public static List<DossierLog> findByG(long groupId, int start, int end) {
		return getPersistence().findByG(groupId, start, end);
	}

	/**
	* Returns an ordered range of all the dossier logs where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of dossier logs
	* @param end the upper bound of the range of dossier logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dossier logs
	*/
	public static List<DossierLog> findByG(long groupId, int start, int end,
		OrderByComparator<DossierLog> orderByComparator) {
		return getPersistence().findByG(groupId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier logs where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of dossier logs
	* @param end the upper bound of the range of dossier logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dossier logs
	*/
	public static List<DossierLog> findByG(long groupId, int start, int end,
		OrderByComparator<DossierLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG(groupId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first dossier log in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier log
	* @throws NoSuchDossierLogException if a matching dossier log could not be found
	*/
	public static DossierLog findByG_First(long groupId,
		OrderByComparator<DossierLog> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierLogException {
		return getPersistence().findByG_First(groupId, orderByComparator);
	}

	/**
	* Returns the first dossier log in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dossier log, or <code>null</code> if a matching dossier log could not be found
	*/
	public static DossierLog fetchByG_First(long groupId,
		OrderByComparator<DossierLog> orderByComparator) {
		return getPersistence().fetchByG_First(groupId, orderByComparator);
	}

	/**
	* Returns the last dossier log in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier log
	* @throws NoSuchDossierLogException if a matching dossier log could not be found
	*/
	public static DossierLog findByG_Last(long groupId,
		OrderByComparator<DossierLog> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierLogException {
		return getPersistence().findByG_Last(groupId, orderByComparator);
	}

	/**
	* Returns the last dossier log in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dossier log, or <code>null</code> if a matching dossier log could not be found
	*/
	public static DossierLog fetchByG_Last(long groupId,
		OrderByComparator<DossierLog> orderByComparator) {
		return getPersistence().fetchByG_Last(groupId, orderByComparator);
	}

	/**
	* Returns the dossier logs before and after the current dossier log in the ordered set where groupId = &#63;.
	*
	* @param dossierLogId the primary key of the current dossier log
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dossier log
	* @throws NoSuchDossierLogException if a dossier log with the primary key could not be found
	*/
	public static DossierLog[] findByG_PrevAndNext(long dossierLogId,
		long groupId, OrderByComparator<DossierLog> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchDossierLogException {
		return getPersistence()
				   .findByG_PrevAndNext(dossierLogId, groupId, orderByComparator);
	}

	/**
	* Removes all the dossier logs where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public static void removeByG(long groupId) {
		getPersistence().removeByG(groupId);
	}

	/**
	* Returns the number of dossier logs where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching dossier logs
	*/
	public static int countByG(long groupId) {
		return getPersistence().countByG(groupId);
	}

	/**
	* Caches the dossier log in the entity cache if it is enabled.
	*
	* @param dossierLog the dossier log
	*/
	public static void cacheResult(DossierLog dossierLog) {
		getPersistence().cacheResult(dossierLog);
	}

	/**
	* Caches the dossier logs in the entity cache if it is enabled.
	*
	* @param dossierLogs the dossier logs
	*/
	public static void cacheResult(List<DossierLog> dossierLogs) {
		getPersistence().cacheResult(dossierLogs);
	}

	/**
	* Creates a new dossier log with the primary key. Does not add the dossier log to the database.
	*
	* @param dossierLogId the primary key for the new dossier log
	* @return the new dossier log
	*/
	public static DossierLog create(long dossierLogId) {
		return getPersistence().create(dossierLogId);
	}

	/**
	* Removes the dossier log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierLogId the primary key of the dossier log
	* @return the dossier log that was removed
	* @throws NoSuchDossierLogException if a dossier log with the primary key could not be found
	*/
	public static DossierLog remove(long dossierLogId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierLogException {
		return getPersistence().remove(dossierLogId);
	}

	public static DossierLog updateImpl(DossierLog dossierLog) {
		return getPersistence().updateImpl(dossierLog);
	}

	/**
	* Returns the dossier log with the primary key or throws a {@link NoSuchDossierLogException} if it could not be found.
	*
	* @param dossierLogId the primary key of the dossier log
	* @return the dossier log
	* @throws NoSuchDossierLogException if a dossier log with the primary key could not be found
	*/
	public static DossierLog findByPrimaryKey(long dossierLogId)
		throws org.opencps.dossiermgt.exception.NoSuchDossierLogException {
		return getPersistence().findByPrimaryKey(dossierLogId);
	}

	/**
	* Returns the dossier log with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dossierLogId the primary key of the dossier log
	* @return the dossier log, or <code>null</code> if a dossier log with the primary key could not be found
	*/
	public static DossierLog fetchByPrimaryKey(long dossierLogId) {
		return getPersistence().fetchByPrimaryKey(dossierLogId);
	}

	public static java.util.Map<java.io.Serializable, DossierLog> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the dossier logs.
	*
	* @return the dossier logs
	*/
	public static List<DossierLog> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the dossier logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier logs
	* @param end the upper bound of the range of dossier logs (not inclusive)
	* @return the range of dossier logs
	*/
	public static List<DossierLog> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the dossier logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier logs
	* @param end the upper bound of the range of dossier logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dossier logs
	*/
	public static List<DossierLog> findAll(int start, int end,
		OrderByComparator<DossierLog> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the dossier logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DossierLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier logs
	* @param end the upper bound of the range of dossier logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dossier logs
	*/
	public static List<DossierLog> findAll(int start, int end,
		OrderByComparator<DossierLog> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the dossier logs from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of dossier logs.
	*
	* @return the number of dossier logs
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static DossierLogPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DossierLogPersistence, DossierLogPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DossierLogPersistence.class);

		ServiceTracker<DossierLogPersistence, DossierLogPersistence> serviceTracker =
			new ServiceTracker<DossierLogPersistence, DossierLogPersistence>(bundle.getBundleContext(),
				DossierLogPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}