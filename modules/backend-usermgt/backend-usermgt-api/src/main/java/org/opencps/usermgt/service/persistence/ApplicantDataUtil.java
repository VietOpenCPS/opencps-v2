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

import org.opencps.usermgt.model.ApplicantData;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the applicant data service. This utility wraps {@link org.opencps.usermgt.service.persistence.impl.ApplicantDataPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author khoavu
 * @see ApplicantDataPersistence
 * @see org.opencps.usermgt.service.persistence.impl.ApplicantDataPersistenceImpl
 * @generated
 */
@ProviderType
public class ApplicantDataUtil {
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
	public static void clearCache(ApplicantData applicantData) {
		getPersistence().clearCache(applicantData);
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
	public static List<ApplicantData> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ApplicantData> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ApplicantData> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ApplicantData> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ApplicantData update(ApplicantData applicantData) {
		return getPersistence().update(applicantData);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ApplicantData update(ApplicantData applicantData,
		ServiceContext serviceContext) {
		return getPersistence().update(applicantData, serviceContext);
	}

	/**
	* Returns all the applicant datas where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching applicant datas
	*/
	public static List<ApplicantData> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the applicant datas where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicantDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of applicant datas
	* @param end the upper bound of the range of applicant datas (not inclusive)
	* @return the range of matching applicant datas
	*/
	public static List<ApplicantData> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the applicant datas where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicantDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of applicant datas
	* @param end the upper bound of the range of applicant datas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching applicant datas
	*/
	public static List<ApplicantData> findByUuid(String uuid, int start,
		int end, OrderByComparator<ApplicantData> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the applicant datas where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicantDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of applicant datas
	* @param end the upper bound of the range of applicant datas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching applicant datas
	*/
	public static List<ApplicantData> findByUuid(String uuid, int start,
		int end, OrderByComparator<ApplicantData> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first applicant data in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching applicant data
	* @throws NoSuchApplicantDataException if a matching applicant data could not be found
	*/
	public static ApplicantData findByUuid_First(String uuid,
		OrderByComparator<ApplicantData> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchApplicantDataException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first applicant data in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching applicant data, or <code>null</code> if a matching applicant data could not be found
	*/
	public static ApplicantData fetchByUuid_First(String uuid,
		OrderByComparator<ApplicantData> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last applicant data in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching applicant data
	* @throws NoSuchApplicantDataException if a matching applicant data could not be found
	*/
	public static ApplicantData findByUuid_Last(String uuid,
		OrderByComparator<ApplicantData> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchApplicantDataException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last applicant data in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching applicant data, or <code>null</code> if a matching applicant data could not be found
	*/
	public static ApplicantData fetchByUuid_Last(String uuid,
		OrderByComparator<ApplicantData> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the applicant datas before and after the current applicant data in the ordered set where uuid = &#63;.
	*
	* @param applicantDataId the primary key of the current applicant data
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next applicant data
	* @throws NoSuchApplicantDataException if a applicant data with the primary key could not be found
	*/
	public static ApplicantData[] findByUuid_PrevAndNext(long applicantDataId,
		String uuid, OrderByComparator<ApplicantData> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchApplicantDataException {
		return getPersistence()
				   .findByUuid_PrevAndNext(applicantDataId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the applicant datas where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of applicant datas where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching applicant datas
	*/
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the applicant data where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchApplicantDataException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching applicant data
	* @throws NoSuchApplicantDataException if a matching applicant data could not be found
	*/
	public static ApplicantData findByUUID_G(String uuid, long groupId)
		throws org.opencps.usermgt.exception.NoSuchApplicantDataException {
		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	* Returns the applicant data where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching applicant data, or <code>null</code> if a matching applicant data could not be found
	*/
	public static ApplicantData fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	* Returns the applicant data where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching applicant data, or <code>null</code> if a matching applicant data could not be found
	*/
	public static ApplicantData fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUUID_G(uuid, groupId, retrieveFromCache);
	}

	/**
	* Removes the applicant data where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the applicant data that was removed
	*/
	public static ApplicantData removeByUUID_G(String uuid, long groupId)
		throws org.opencps.usermgt.exception.NoSuchApplicantDataException {
		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	* Returns the number of applicant datas where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching applicant datas
	*/
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	* Returns all the applicant datas where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching applicant datas
	*/
	public static List<ApplicantData> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	* Returns a range of all the applicant datas where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicantDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of applicant datas
	* @param end the upper bound of the range of applicant datas (not inclusive)
	* @return the range of matching applicant datas
	*/
	public static List<ApplicantData> findByUuid_C(String uuid, long companyId,
		int start, int end) {
		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	* Returns an ordered range of all the applicant datas where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicantDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of applicant datas
	* @param end the upper bound of the range of applicant datas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching applicant datas
	*/
	public static List<ApplicantData> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ApplicantData> orderByComparator) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the applicant datas where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicantDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of applicant datas
	* @param end the upper bound of the range of applicant datas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching applicant datas
	*/
	public static List<ApplicantData> findByUuid_C(String uuid, long companyId,
		int start, int end, OrderByComparator<ApplicantData> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid_C(uuid, companyId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first applicant data in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching applicant data
	* @throws NoSuchApplicantDataException if a matching applicant data could not be found
	*/
	public static ApplicantData findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<ApplicantData> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchApplicantDataException {
		return getPersistence()
				   .findByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the first applicant data in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching applicant data, or <code>null</code> if a matching applicant data could not be found
	*/
	public static ApplicantData fetchByUuid_C_First(String uuid,
		long companyId, OrderByComparator<ApplicantData> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_First(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last applicant data in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching applicant data
	* @throws NoSuchApplicantDataException if a matching applicant data could not be found
	*/
	public static ApplicantData findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ApplicantData> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchApplicantDataException {
		return getPersistence()
				   .findByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the last applicant data in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching applicant data, or <code>null</code> if a matching applicant data could not be found
	*/
	public static ApplicantData fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<ApplicantData> orderByComparator) {
		return getPersistence()
				   .fetchByUuid_C_Last(uuid, companyId, orderByComparator);
	}

	/**
	* Returns the applicant datas before and after the current applicant data in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param applicantDataId the primary key of the current applicant data
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next applicant data
	* @throws NoSuchApplicantDataException if a applicant data with the primary key could not be found
	*/
	public static ApplicantData[] findByUuid_C_PrevAndNext(
		long applicantDataId, String uuid, long companyId,
		OrderByComparator<ApplicantData> orderByComparator)
		throws org.opencps.usermgt.exception.NoSuchApplicantDataException {
		return getPersistence()
				   .findByUuid_C_PrevAndNext(applicantDataId, uuid, companyId,
			orderByComparator);
	}

	/**
	* Removes all the applicant datas where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	* Returns the number of applicant datas where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching applicant datas
	*/
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	* Caches the applicant data in the entity cache if it is enabled.
	*
	* @param applicantData the applicant data
	*/
	public static void cacheResult(ApplicantData applicantData) {
		getPersistence().cacheResult(applicantData);
	}

	/**
	* Caches the applicant datas in the entity cache if it is enabled.
	*
	* @param applicantDatas the applicant datas
	*/
	public static void cacheResult(List<ApplicantData> applicantDatas) {
		getPersistence().cacheResult(applicantDatas);
	}

	/**
	* Creates a new applicant data with the primary key. Does not add the applicant data to the database.
	*
	* @param applicantDataId the primary key for the new applicant data
	* @return the new applicant data
	*/
	public static ApplicantData create(long applicantDataId) {
		return getPersistence().create(applicantDataId);
	}

	/**
	* Removes the applicant data with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param applicantDataId the primary key of the applicant data
	* @return the applicant data that was removed
	* @throws NoSuchApplicantDataException if a applicant data with the primary key could not be found
	*/
	public static ApplicantData remove(long applicantDataId)
		throws org.opencps.usermgt.exception.NoSuchApplicantDataException {
		return getPersistence().remove(applicantDataId);
	}

	public static ApplicantData updateImpl(ApplicantData applicantData) {
		return getPersistence().updateImpl(applicantData);
	}

	/**
	* Returns the applicant data with the primary key or throws a {@link NoSuchApplicantDataException} if it could not be found.
	*
	* @param applicantDataId the primary key of the applicant data
	* @return the applicant data
	* @throws NoSuchApplicantDataException if a applicant data with the primary key could not be found
	*/
	public static ApplicantData findByPrimaryKey(long applicantDataId)
		throws org.opencps.usermgt.exception.NoSuchApplicantDataException {
		return getPersistence().findByPrimaryKey(applicantDataId);
	}

	/**
	* Returns the applicant data with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param applicantDataId the primary key of the applicant data
	* @return the applicant data, or <code>null</code> if a applicant data with the primary key could not be found
	*/
	public static ApplicantData fetchByPrimaryKey(long applicantDataId) {
		return getPersistence().fetchByPrimaryKey(applicantDataId);
	}

	public static java.util.Map<java.io.Serializable, ApplicantData> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the applicant datas.
	*
	* @return the applicant datas
	*/
	public static List<ApplicantData> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the applicant datas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicantDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of applicant datas
	* @param end the upper bound of the range of applicant datas (not inclusive)
	* @return the range of applicant datas
	*/
	public static List<ApplicantData> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the applicant datas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicantDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of applicant datas
	* @param end the upper bound of the range of applicant datas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of applicant datas
	*/
	public static List<ApplicantData> findAll(int start, int end,
		OrderByComparator<ApplicantData> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the applicant datas.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ApplicantDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of applicant datas
	* @param end the upper bound of the range of applicant datas (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of applicant datas
	*/
	public static List<ApplicantData> findAll(int start, int end,
		OrderByComparator<ApplicantData> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the applicant datas from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of applicant datas.
	*
	* @return the number of applicant datas
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ApplicantDataPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ApplicantDataPersistence, ApplicantDataPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ApplicantDataPersistence.class);

		ServiceTracker<ApplicantDataPersistence, ApplicantDataPersistence> serviceTracker =
			new ServiceTracker<ApplicantDataPersistence, ApplicantDataPersistence>(bundle.getBundleContext(),
				ApplicantDataPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}