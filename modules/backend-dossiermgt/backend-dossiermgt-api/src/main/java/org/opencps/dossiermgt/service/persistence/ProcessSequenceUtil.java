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

import org.opencps.dossiermgt.model.ProcessSequence;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the process sequence service. This utility wraps {@link org.opencps.dossiermgt.service.persistence.impl.ProcessSequencePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see ProcessSequencePersistence
 * @see org.opencps.dossiermgt.service.persistence.impl.ProcessSequencePersistenceImpl
 * @generated
 */
@ProviderType
public class ProcessSequenceUtil {
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
	public static void clearCache(ProcessSequence processSequence) {
		getPersistence().clearCache(processSequence);
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
	public static List<ProcessSequence> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ProcessSequence> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ProcessSequence> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ProcessSequence> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ProcessSequence update(ProcessSequence processSequence) {
		return getPersistence().update(processSequence);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ProcessSequence update(ProcessSequence processSequence,
		ServiceContext serviceContext) {
		return getPersistence().update(processSequence, serviceContext);
	}

	/**
	* Returns all the process sequences where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching process sequences
	*/
	public static List<ProcessSequence> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the process sequences where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of process sequences
	* @param end the upper bound of the range of process sequences (not inclusive)
	* @return the range of matching process sequences
	*/
	public static List<ProcessSequence> findByUuid(String uuid, int start,
		int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the process sequences where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of process sequences
	* @param end the upper bound of the range of process sequences (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching process sequences
	*/
	public static List<ProcessSequence> findByUuid(String uuid, int start,
		int end, OrderByComparator<ProcessSequence> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the process sequences where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of process sequences
	* @param end the upper bound of the range of process sequences (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching process sequences
	*/
	public static List<ProcessSequence> findByUuid(String uuid, int start,
		int end, OrderByComparator<ProcessSequence> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first process sequence in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process sequence
	* @throws NoSuchProcessSequenceException if a matching process sequence could not be found
	*/
	public static ProcessSequence findByUuid_First(String uuid,
		OrderByComparator<ProcessSequence> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessSequenceException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first process sequence in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process sequence, or <code>null</code> if a matching process sequence could not be found
	*/
	public static ProcessSequence fetchByUuid_First(String uuid,
		OrderByComparator<ProcessSequence> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last process sequence in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process sequence
	* @throws NoSuchProcessSequenceException if a matching process sequence could not be found
	*/
	public static ProcessSequence findByUuid_Last(String uuid,
		OrderByComparator<ProcessSequence> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessSequenceException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last process sequence in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process sequence, or <code>null</code> if a matching process sequence could not be found
	*/
	public static ProcessSequence fetchByUuid_Last(String uuid,
		OrderByComparator<ProcessSequence> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the process sequences before and after the current process sequence in the ordered set where uuid = &#63;.
	*
	* @param processSequenceId the primary key of the current process sequence
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process sequence
	* @throws NoSuchProcessSequenceException if a process sequence with the primary key could not be found
	*/
	public static ProcessSequence[] findByUuid_PrevAndNext(
		long processSequenceId, String uuid,
		OrderByComparator<ProcessSequence> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessSequenceException {
		return getPersistence()
				   .findByUuid_PrevAndNext(processSequenceId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the process sequences where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of process sequences where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching process sequences
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the process sequence where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchProcessSequenceException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching process sequence
	* @throws NoSuchProcessSequenceException if a matching process sequence could not be found
	*/
	public static ProcessSequence findByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchProcessSequenceException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the process sequence where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching process sequence, or <code>null</code> if a matching process sequence could not be found
	*/
	public static ProcessSequence fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the process sequence where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching process sequence, or <code>null</code> if a matching process sequence could not be found
	*/
	public static ProcessSequence fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the process sequence where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the process sequence that was removed
	*/
	public static ProcessSequence removeByUUID_G(String uuid, long groupId)
		throws org.opencps.dossiermgt.exception.NoSuchProcessSequenceException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of process sequences where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching process sequences
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the process sequences where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching process sequences
	*/
	public static List<ProcessSequence> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the process sequences where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of process sequences
	* @param end the upper bound of the range of process sequences (not inclusive)
	* @return the range of matching process sequences
	*/
	public static List<ProcessSequence> findByUuid_C(String uuid,
		long companyId, int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the process sequences where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of process sequences
	* @param end the upper bound of the range of process sequences (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching process sequences
	*/
	public static List<ProcessSequence> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<ProcessSequence> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the process sequences where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of process sequences
	* @param end the upper bound of the range of process sequences (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching process sequences
	*/
	public static List<ProcessSequence> findByUuid_C(String uuid,
		long companyId, int start, int end,
		OrderByComparator<ProcessSequence> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first process sequence in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process sequence
	* @throws NoSuchProcessSequenceException if a matching process sequence could not be found
	*/
	public static ProcessSequence findByUuid_C_First(String uuid,
		long companyId, OrderByComparator<ProcessSequence> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessSequenceException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first process sequence in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process sequence, or <code>null</code> if a matching process sequence could not be found
	*/
	public static ProcessSequence fetchByUuid_C_First(String uuid,
		long companyId, OrderByComparator<ProcessSequence> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last process sequence in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process sequence
	* @throws NoSuchProcessSequenceException if a matching process sequence could not be found
	*/
	public static ProcessSequence findByUuid_C_Last(String uuid,
		long companyId, OrderByComparator<ProcessSequence> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessSequenceException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last process sequence in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process sequence, or <code>null</code> if a matching process sequence could not be found
	*/
	public static ProcessSequence fetchByUuid_C_Last(String uuid,
		long companyId, OrderByComparator<ProcessSequence> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the process sequences before and after the current process sequence in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param processSequenceId the primary key of the current process sequence
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process sequence
	* @throws NoSuchProcessSequenceException if a process sequence with the primary key could not be found
	*/
	public static ProcessSequence[] findByUuid_C_PrevAndNext(
		long processSequenceId, String uuid, long companyId,
		OrderByComparator<ProcessSequence> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessSequenceException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(processSequenceId, uuid,
			companyId, orderByComparator);
	}

	/**
	* Removes all the process sequences where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of process sequences where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching process sequences
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Returns all the process sequences where groupId = &#63; and sequenceNo = &#63;.
	*
	* @param groupId the group ID
	* @param sequenceNo the sequence no
	* @return the matching process sequences
	*/
	public static List<ProcessSequence> findByG_SN(long groupId,
		String sequenceNo) {
		return getPersistence().findByG_SN(groupId, sequenceNo);
	}

	/**
	* Returns a range of all the process sequences where groupId = &#63; and sequenceNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param sequenceNo the sequence no
	* @param start the lower bound of the range of process sequences
	* @param end the upper bound of the range of process sequences (not inclusive)
	* @return the range of matching process sequences
	*/
	public static List<ProcessSequence> findByG_SN(long groupId,
		String sequenceNo, int start, int end) {
		return getPersistence().findByG_SN(groupId, sequenceNo, start, end);
	}

	/**
	* Returns an ordered range of all the process sequences where groupId = &#63; and sequenceNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param sequenceNo the sequence no
	* @param start the lower bound of the range of process sequences
	* @param end the upper bound of the range of process sequences (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching process sequences
	*/
	public static List<ProcessSequence> findByG_SN(long groupId,
		String sequenceNo, int start, int end,
		OrderByComparator<ProcessSequence> orderByComparator) {
		return getPersistence()
				   .findByG_SN(groupId, sequenceNo, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the process sequences where groupId = &#63; and sequenceNo = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param sequenceNo the sequence no
	* @param start the lower bound of the range of process sequences
	* @param end the upper bound of the range of process sequences (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching process sequences
	*/
	public static List<ProcessSequence> findByG_SN(long groupId,
		String sequenceNo, int start, int end,
		OrderByComparator<ProcessSequence> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByG_SN(groupId, sequenceNo, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first process sequence in the ordered set where groupId = &#63; and sequenceNo = &#63;.
	*
	* @param groupId the group ID
	* @param sequenceNo the sequence no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process sequence
	* @throws NoSuchProcessSequenceException if a matching process sequence could not be found
	*/
	public static ProcessSequence findByG_SN_First(long groupId,
		String sequenceNo, OrderByComparator<ProcessSequence> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessSequenceException {
		return getPersistence()
				   .findByG_SN_First(groupId, sequenceNo, orderByComparator);
	}

	/**
	* Returns the first process sequence in the ordered set where groupId = &#63; and sequenceNo = &#63;.
	*
	* @param groupId the group ID
	* @param sequenceNo the sequence no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process sequence, or <code>null</code> if a matching process sequence could not be found
	*/
	public static ProcessSequence fetchByG_SN_First(long groupId,
		String sequenceNo, OrderByComparator<ProcessSequence> orderByComparator) {
		return getPersistence()
				   .fetchByG_SN_First(groupId, sequenceNo, orderByComparator);
	}

	/**
	* Returns the last process sequence in the ordered set where groupId = &#63; and sequenceNo = &#63;.
	*
	* @param groupId the group ID
	* @param sequenceNo the sequence no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process sequence
	* @throws NoSuchProcessSequenceException if a matching process sequence could not be found
	*/
	public static ProcessSequence findByG_SN_Last(long groupId,
		String sequenceNo, OrderByComparator<ProcessSequence> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessSequenceException {
		return getPersistence()
				   .findByG_SN_Last(groupId, sequenceNo, orderByComparator);
	}

	/**
	* Returns the last process sequence in the ordered set where groupId = &#63; and sequenceNo = &#63;.
	*
	* @param groupId the group ID
	* @param sequenceNo the sequence no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process sequence, or <code>null</code> if a matching process sequence could not be found
	*/
	public static ProcessSequence fetchByG_SN_Last(long groupId,
		String sequenceNo, OrderByComparator<ProcessSequence> orderByComparator) {
		return getPersistence()
				   .fetchByG_SN_Last(groupId, sequenceNo, orderByComparator);
	}

	/**
	* Returns the process sequences before and after the current process sequence in the ordered set where groupId = &#63; and sequenceNo = &#63;.
	*
	* @param processSequenceId the primary key of the current process sequence
	* @param groupId the group ID
	* @param sequenceNo the sequence no
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process sequence
	* @throws NoSuchProcessSequenceException if a process sequence with the primary key could not be found
	*/
	public static ProcessSequence[] findByG_SN_PrevAndNext(
		long processSequenceId, long groupId, String sequenceNo,
		OrderByComparator<ProcessSequence> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessSequenceException {
		return getPersistence()
				   .findByG_SN_PrevAndNext(processSequenceId, groupId,
			sequenceNo, orderByComparator);
	}

	/**
	* Removes all the process sequences where groupId = &#63; and sequenceNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param sequenceNo the sequence no
	*/
	public static void removeByG_SN(long groupId, String sequenceNo) {
		getPersistence().removeByG_SN(groupId, sequenceNo);
	}

	/**
	* Returns the number of process sequences where groupId = &#63; and sequenceNo = &#63;.
	*
	* @param groupId the group ID
	* @param sequenceNo the sequence no
	* @return the number of matching process sequences
	*/
	public static int countByG_SN(long groupId, String sequenceNo) {
		return getPersistence().countByG_SN(groupId, sequenceNo);
	}

	/**
	* Returns all the process sequences where groupId = &#63; and serviceProcessId = &#63;.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @return the matching process sequences
	*/
	public static List<ProcessSequence> findByF_GID_SID(long groupId,
		long serviceProcessId) {
		return getPersistence().findByF_GID_SID(groupId, serviceProcessId);
	}

	/**
	* Returns a range of all the process sequences where groupId = &#63; and serviceProcessId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param start the lower bound of the range of process sequences
	* @param end the upper bound of the range of process sequences (not inclusive)
	* @return the range of matching process sequences
	*/
	public static List<ProcessSequence> findByF_GID_SID(long groupId,
		long serviceProcessId, int start, int end) {
		return getPersistence()
				   .findByF_GID_SID(groupId, serviceProcessId, start, end);
	}

	/**
	* Returns an ordered range of all the process sequences where groupId = &#63; and serviceProcessId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param start the lower bound of the range of process sequences
	* @param end the upper bound of the range of process sequences (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching process sequences
	*/
	public static List<ProcessSequence> findByF_GID_SID(long groupId,
		long serviceProcessId, int start, int end,
		OrderByComparator<ProcessSequence> orderByComparator) {
		return getPersistence()
				   .findByF_GID_SID(groupId, serviceProcessId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the process sequences where groupId = &#63; and serviceProcessId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param start the lower bound of the range of process sequences
	* @param end the upper bound of the range of process sequences (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching process sequences
	*/
	public static List<ProcessSequence> findByF_GID_SID(long groupId,
		long serviceProcessId, int start, int end,
		OrderByComparator<ProcessSequence> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByF_GID_SID(groupId, serviceProcessId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first process sequence in the ordered set where groupId = &#63; and serviceProcessId = &#63;.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process sequence
	* @throws NoSuchProcessSequenceException if a matching process sequence could not be found
	*/
	public static ProcessSequence findByF_GID_SID_First(long groupId,
		long serviceProcessId,
		OrderByComparator<ProcessSequence> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessSequenceException {
		return getPersistence()
				   .findByF_GID_SID_First(groupId, serviceProcessId,
			orderByComparator);
	}

	/**
	* Returns the first process sequence in the ordered set where groupId = &#63; and serviceProcessId = &#63;.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching process sequence, or <code>null</code> if a matching process sequence could not be found
	*/
	public static ProcessSequence fetchByF_GID_SID_First(long groupId,
		long serviceProcessId,
		OrderByComparator<ProcessSequence> orderByComparator) {
		return getPersistence()
				   .fetchByF_GID_SID_First(groupId, serviceProcessId,
			orderByComparator);
	}

	/**
	* Returns the last process sequence in the ordered set where groupId = &#63; and serviceProcessId = &#63;.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process sequence
	* @throws NoSuchProcessSequenceException if a matching process sequence could not be found
	*/
	public static ProcessSequence findByF_GID_SID_Last(long groupId,
		long serviceProcessId,
		OrderByComparator<ProcessSequence> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessSequenceException {
		return getPersistence()
				   .findByF_GID_SID_Last(groupId, serviceProcessId,
			orderByComparator);
	}

	/**
	* Returns the last process sequence in the ordered set where groupId = &#63; and serviceProcessId = &#63;.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching process sequence, or <code>null</code> if a matching process sequence could not be found
	*/
	public static ProcessSequence fetchByF_GID_SID_Last(long groupId,
		long serviceProcessId,
		OrderByComparator<ProcessSequence> orderByComparator) {
		return getPersistence()
				   .fetchByF_GID_SID_Last(groupId, serviceProcessId,
			orderByComparator);
	}

	/**
	* Returns the process sequences before and after the current process sequence in the ordered set where groupId = &#63; and serviceProcessId = &#63;.
	*
	* @param processSequenceId the primary key of the current process sequence
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next process sequence
	* @throws NoSuchProcessSequenceException if a process sequence with the primary key could not be found
	*/
	public static ProcessSequence[] findByF_GID_SID_PrevAndNext(
		long processSequenceId, long groupId, long serviceProcessId,
		OrderByComparator<ProcessSequence> orderByComparator)
		throws org.opencps.dossiermgt.exception.NoSuchProcessSequenceException {
		return getPersistence()
				   .findByF_GID_SID_PrevAndNext(processSequenceId, groupId,
			serviceProcessId, orderByComparator);
	}

	/**
	* Removes all the process sequences where groupId = &#63; and serviceProcessId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	*/
	public static void removeByF_GID_SID(long groupId, long serviceProcessId) {
		getPersistence().removeByF_GID_SID(groupId, serviceProcessId);
	}

	/**
	* Returns the number of process sequences where groupId = &#63; and serviceProcessId = &#63;.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @return the number of matching process sequences
	*/
	public static int countByF_GID_SID(long groupId, long serviceProcessId) {
		return getPersistence().countByF_GID_SID(groupId, serviceProcessId);
	}

	/**
	* Returns the process sequence where groupId = &#63; and serviceProcessId = &#63; and sequenceNo = &#63; or throws a {@link NoSuchProcessSequenceException} if it could not be found.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param sequenceNo the sequence no
	* @return the matching process sequence
	* @throws NoSuchProcessSequenceException if a matching process sequence could not be found
	*/
	public static ProcessSequence findByF_GID_SID_SNO(long groupId,
		long serviceProcessId, String sequenceNo)
		throws org.opencps.dossiermgt.exception.NoSuchProcessSequenceException {
		return getPersistence()
				   .findByF_GID_SID_SNO(groupId, serviceProcessId, sequenceNo);
	}

	/**
	* Returns the process sequence where groupId = &#63; and serviceProcessId = &#63; and sequenceNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param sequenceNo the sequence no
	* @return the matching process sequence, or <code>null</code> if a matching process sequence could not be found
	*/
	public static ProcessSequence fetchByF_GID_SID_SNO(long groupId,
		long serviceProcessId, String sequenceNo) {
		return getPersistence()
				   .fetchByF_GID_SID_SNO(groupId, serviceProcessId, sequenceNo);
	}

	/**
	* Returns the process sequence where groupId = &#63; and serviceProcessId = &#63; and sequenceNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param sequenceNo the sequence no
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching process sequence, or <code>null</code> if a matching process sequence could not be found
	*/
	public static ProcessSequence fetchByF_GID_SID_SNO(long groupId,
		long serviceProcessId, String sequenceNo, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByF_GID_SID_SNO(groupId, serviceProcessId, sequenceNo,
			retrieveFromCache);
	}

	/**
	* Removes the process sequence where groupId = &#63; and serviceProcessId = &#63; and sequenceNo = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param sequenceNo the sequence no
	* @return the process sequence that was removed
	*/
	public static ProcessSequence removeByF_GID_SID_SNO(long groupId,
		long serviceProcessId, String sequenceNo)
		throws org.opencps.dossiermgt.exception.NoSuchProcessSequenceException {
		return getPersistence()
				   .removeByF_GID_SID_SNO(groupId, serviceProcessId, sequenceNo);
	}

	/**
	* Returns the number of process sequences where groupId = &#63; and serviceProcessId = &#63; and sequenceNo = &#63;.
	*
	* @param groupId the group ID
	* @param serviceProcessId the service process ID
	* @param sequenceNo the sequence no
	* @return the number of matching process sequences
	*/
	public static int countByF_GID_SID_SNO(long groupId, long serviceProcessId,
		String sequenceNo) {
		return getPersistence()
				   .countByF_GID_SID_SNO(groupId, serviceProcessId, sequenceNo);
	}

	/**
	* Caches the process sequence in the entity cache if it is enabled.
	*
	* @param processSequence the process sequence
	*/
	public static void cacheResult(ProcessSequence processSequence) {
		getPersistence().cacheResult(processSequence);
	}

	/**
	* Caches the process sequences in the entity cache if it is enabled.
	*
	* @param processSequences the process sequences
	*/
	public static void cacheResult(List<ProcessSequence> processSequences) {
		getPersistence().cacheResult(processSequences);
	}

	/**
	* Creates a new process sequence with the primary key. Does not add the process sequence to the database.
	*
	* @param processSequenceId the primary key for the new process sequence
	* @return the new process sequence
	*/
	public static ProcessSequence create(long processSequenceId) {
		return getPersistence().create(processSequenceId);
	}

	/**
	* Removes the process sequence with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param processSequenceId the primary key of the process sequence
	* @return the process sequence that was removed
	* @throws NoSuchProcessSequenceException if a process sequence with the primary key could not be found
	*/
	public static ProcessSequence remove(long processSequenceId)
		throws org.opencps.dossiermgt.exception.NoSuchProcessSequenceException {
		return getPersistence().remove(processSequenceId);
	}

	public static ProcessSequence updateImpl(ProcessSequence processSequence) {
		return getPersistence().updateImpl(processSequence);
	}

	/**
	* Returns the process sequence with the primary key or throws a {@link NoSuchProcessSequenceException} if it could not be found.
	*
	* @param processSequenceId the primary key of the process sequence
	* @return the process sequence
	* @throws NoSuchProcessSequenceException if a process sequence with the primary key could not be found
	*/
	public static ProcessSequence findByPrimaryKey(long processSequenceId)
		throws org.opencps.dossiermgt.exception.NoSuchProcessSequenceException {
		return getPersistence().findByPrimaryKey(processSequenceId);
	}

	/**
	* Returns the process sequence with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param processSequenceId the primary key of the process sequence
	* @return the process sequence, or <code>null</code> if a process sequence with the primary key could not be found
	*/
	public static ProcessSequence fetchByPrimaryKey(long processSequenceId) {
		return getPersistence().fetchByPrimaryKey(processSequenceId);
	}

	public static java.util.Map<java.io.Serializable, ProcessSequence> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the process sequences.
	*
	* @return the process sequences
	*/
	public static List<ProcessSequence> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the process sequences.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of process sequences
	* @param end the upper bound of the range of process sequences (not inclusive)
	* @return the range of process sequences
	*/
	public static List<ProcessSequence> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the process sequences.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of process sequences
	* @param end the upper bound of the range of process sequences (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of process sequences
	*/
	public static List<ProcessSequence> findAll(int start, int end,
		OrderByComparator<ProcessSequence> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the process sequences.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProcessSequenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of process sequences
	* @param end the upper bound of the range of process sequences (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of process sequences
	*/
	public static List<ProcessSequence> findAll(int start, int end,
		OrderByComparator<ProcessSequence> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the process sequences from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of process sequences.
	*
	* @return the number of process sequences
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ProcessSequencePersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ProcessSequencePersistence, ProcessSequencePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ProcessSequencePersistence.class);

		ServiceTracker<ProcessSequencePersistence, ProcessSequencePersistence> serviceTracker =
			new ServiceTracker<ProcessSequencePersistence, ProcessSequencePersistence>(bundle.getBundleContext(),
				ProcessSequencePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}