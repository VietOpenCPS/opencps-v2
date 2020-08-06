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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.dossiermgt.exception.NoSuchApplicableInfoException;
import org.opencps.dossiermgt.model.ApplicableInfo;

/**
 * The persistence interface for the applicable info service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.ApplicableInfoPersistenceImpl
 * @see ApplicableInfoUtil
 * @generated
 */
@ProviderType
public interface ApplicableInfoPersistence extends BasePersistence<ApplicableInfo> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ApplicableInfoUtil} to access the applicable info persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the applicable infos where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching applicable infos
	*/
	public java.util.List<ApplicableInfo> findByUuid(String uuid);

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
	public java.util.List<ApplicableInfo> findByUuid(String uuid, int start,
		int end);

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
	public java.util.List<ApplicableInfo> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicableInfo> orderByComparator);

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
	public java.util.List<ApplicableInfo> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicableInfo> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first applicable info in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching applicable info
	* @throws NoSuchApplicableInfoException if a matching applicable info could not be found
	*/
	public ApplicableInfo findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicableInfo> orderByComparator)
		throws NoSuchApplicableInfoException;

	/**
	* Returns the first applicable info in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching applicable info, or <code>null</code> if a matching applicable info could not be found
	*/
	public ApplicableInfo fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicableInfo> orderByComparator);

	/**
	* Returns the last applicable info in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching applicable info
	* @throws NoSuchApplicableInfoException if a matching applicable info could not be found
	*/
	public ApplicableInfo findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicableInfo> orderByComparator)
		throws NoSuchApplicableInfoException;

	/**
	* Returns the last applicable info in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching applicable info, or <code>null</code> if a matching applicable info could not be found
	*/
	public ApplicableInfo fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicableInfo> orderByComparator);

	/**
	* Returns the applicable infos before and after the current applicable info in the ordered set where uuid = &#63;.
	*
	* @param applicableInfoId the primary key of the current applicable info
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next applicable info
	* @throws NoSuchApplicableInfoException if a applicable info with the primary key could not be found
	*/
	public ApplicableInfo[] findByUuid_PrevAndNext(long applicableInfoId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicableInfo> orderByComparator)
		throws NoSuchApplicableInfoException;

	/**
	* Removes all the applicable infos where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of applicable infos where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching applicable infos
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the applicable info where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchApplicableInfoException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching applicable info
	* @throws NoSuchApplicableInfoException if a matching applicable info could not be found
	*/
	public ApplicableInfo findByUUID_G(String uuid, long groupId)
		throws NoSuchApplicableInfoException;

	/**
	* Returns the applicable info where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching applicable info, or <code>null</code> if a matching applicable info could not be found
	*/
	public ApplicableInfo fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the applicable info where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching applicable info, or <code>null</code> if a matching applicable info could not be found
	*/
	public ApplicableInfo fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the applicable info where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the applicable info that was removed
	*/
	public ApplicableInfo removeByUUID_G(String uuid, long groupId)
		throws NoSuchApplicableInfoException;

	/**
	* Returns the number of applicable infos where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching applicable infos
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the applicable infos where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching applicable infos
	*/
	public java.util.List<ApplicableInfo> findByUuid_C(String uuid,
		long companyId);

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
	public java.util.List<ApplicableInfo> findByUuid_C(String uuid,
		long companyId, int start, int end);

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
	public java.util.List<ApplicableInfo> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicableInfo> orderByComparator);

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
	public java.util.List<ApplicableInfo> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicableInfo> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first applicable info in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching applicable info
	* @throws NoSuchApplicableInfoException if a matching applicable info could not be found
	*/
	public ApplicableInfo findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicableInfo> orderByComparator)
		throws NoSuchApplicableInfoException;

	/**
	* Returns the first applicable info in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching applicable info, or <code>null</code> if a matching applicable info could not be found
	*/
	public ApplicableInfo fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicableInfo> orderByComparator);

	/**
	* Returns the last applicable info in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching applicable info
	* @throws NoSuchApplicableInfoException if a matching applicable info could not be found
	*/
	public ApplicableInfo findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicableInfo> orderByComparator)
		throws NoSuchApplicableInfoException;

	/**
	* Returns the last applicable info in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching applicable info, or <code>null</code> if a matching applicable info could not be found
	*/
	public ApplicableInfo fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicableInfo> orderByComparator);

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
	public ApplicableInfo[] findByUuid_C_PrevAndNext(long applicableInfoId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicableInfo> orderByComparator)
		throws NoSuchApplicableInfoException;

	/**
	* Removes all the applicable infos where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of applicable infos where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching applicable infos
	*/
	public int countByUuid_C(String uuid, long companyId);

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
	public ApplicableInfo findByG_SC_GC_SL(long groupId, String serviceCode,
		String govAgencyCode, int serviceLevel)
		throws NoSuchApplicableInfoException;

	/**
	* Returns the applicable info where groupId = &#63; and serviceCode = &#63; and govAgencyCode = &#63; and serviceLevel = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @param serviceLevel the service level
	* @return the matching applicable info, or <code>null</code> if a matching applicable info could not be found
	*/
	public ApplicableInfo fetchByG_SC_GC_SL(long groupId, String serviceCode,
		String govAgencyCode, int serviceLevel);

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
	public ApplicableInfo fetchByG_SC_GC_SL(long groupId, String serviceCode,
		String govAgencyCode, int serviceLevel, boolean retrieveFromCache);

	/**
	* Removes the applicable info where groupId = &#63; and serviceCode = &#63; and govAgencyCode = &#63; and serviceLevel = &#63; from the database.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @param serviceLevel the service level
	* @return the applicable info that was removed
	*/
	public ApplicableInfo removeByG_SC_GC_SL(long groupId, String serviceCode,
		String govAgencyCode, int serviceLevel)
		throws NoSuchApplicableInfoException;

	/**
	* Returns the number of applicable infos where groupId = &#63; and serviceCode = &#63; and govAgencyCode = &#63; and serviceLevel = &#63;.
	*
	* @param groupId the group ID
	* @param serviceCode the service code
	* @param govAgencyCode the gov agency code
	* @param serviceLevel the service level
	* @return the number of matching applicable infos
	*/
	public int countByG_SC_GC_SL(long groupId, String serviceCode,
		String govAgencyCode, int serviceLevel);

	/**
	* Returns all the applicable infos where serviceConfigMappingId = &#63;.
	*
	* @param serviceConfigMappingId the service config mapping ID
	* @return the matching applicable infos
	*/
	public java.util.List<ApplicableInfo> findByServiceConfigMappingId(
		long serviceConfigMappingId);

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
	public java.util.List<ApplicableInfo> findByServiceConfigMappingId(
		long serviceConfigMappingId, int start, int end);

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
	public java.util.List<ApplicableInfo> findByServiceConfigMappingId(
		long serviceConfigMappingId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicableInfo> orderByComparator);

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
	public java.util.List<ApplicableInfo> findByServiceConfigMappingId(
		long serviceConfigMappingId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicableInfo> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first applicable info in the ordered set where serviceConfigMappingId = &#63;.
	*
	* @param serviceConfigMappingId the service config mapping ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching applicable info
	* @throws NoSuchApplicableInfoException if a matching applicable info could not be found
	*/
	public ApplicableInfo findByServiceConfigMappingId_First(
		long serviceConfigMappingId,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicableInfo> orderByComparator)
		throws NoSuchApplicableInfoException;

	/**
	* Returns the first applicable info in the ordered set where serviceConfigMappingId = &#63;.
	*
	* @param serviceConfigMappingId the service config mapping ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching applicable info, or <code>null</code> if a matching applicable info could not be found
	*/
	public ApplicableInfo fetchByServiceConfigMappingId_First(
		long serviceConfigMappingId,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicableInfo> orderByComparator);

	/**
	* Returns the last applicable info in the ordered set where serviceConfigMappingId = &#63;.
	*
	* @param serviceConfigMappingId the service config mapping ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching applicable info
	* @throws NoSuchApplicableInfoException if a matching applicable info could not be found
	*/
	public ApplicableInfo findByServiceConfigMappingId_Last(
		long serviceConfigMappingId,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicableInfo> orderByComparator)
		throws NoSuchApplicableInfoException;

	/**
	* Returns the last applicable info in the ordered set where serviceConfigMappingId = &#63;.
	*
	* @param serviceConfigMappingId the service config mapping ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching applicable info, or <code>null</code> if a matching applicable info could not be found
	*/
	public ApplicableInfo fetchByServiceConfigMappingId_Last(
		long serviceConfigMappingId,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicableInfo> orderByComparator);

	/**
	* Returns the applicable infos before and after the current applicable info in the ordered set where serviceConfigMappingId = &#63;.
	*
	* @param applicableInfoId the primary key of the current applicable info
	* @param serviceConfigMappingId the service config mapping ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next applicable info
	* @throws NoSuchApplicableInfoException if a applicable info with the primary key could not be found
	*/
	public ApplicableInfo[] findByServiceConfigMappingId_PrevAndNext(
		long applicableInfoId, long serviceConfigMappingId,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicableInfo> orderByComparator)
		throws NoSuchApplicableInfoException;

	/**
	* Removes all the applicable infos where serviceConfigMappingId = &#63; from the database.
	*
	* @param serviceConfigMappingId the service config mapping ID
	*/
	public void removeByServiceConfigMappingId(long serviceConfigMappingId);

	/**
	* Returns the number of applicable infos where serviceConfigMappingId = &#63;.
	*
	* @param serviceConfigMappingId the service config mapping ID
	* @return the number of matching applicable infos
	*/
	public int countByServiceConfigMappingId(long serviceConfigMappingId);

	/**
	* Caches the applicable info in the entity cache if it is enabled.
	*
	* @param applicableInfo the applicable info
	*/
	public void cacheResult(ApplicableInfo applicableInfo);

	/**
	* Caches the applicable infos in the entity cache if it is enabled.
	*
	* @param applicableInfos the applicable infos
	*/
	public void cacheResult(java.util.List<ApplicableInfo> applicableInfos);

	/**
	* Creates a new applicable info with the primary key. Does not add the applicable info to the database.
	*
	* @param applicableInfoId the primary key for the new applicable info
	* @return the new applicable info
	*/
	public ApplicableInfo create(long applicableInfoId);

	/**
	* Removes the applicable info with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param applicableInfoId the primary key of the applicable info
	* @return the applicable info that was removed
	* @throws NoSuchApplicableInfoException if a applicable info with the primary key could not be found
	*/
	public ApplicableInfo remove(long applicableInfoId)
		throws NoSuchApplicableInfoException;

	public ApplicableInfo updateImpl(ApplicableInfo applicableInfo);

	/**
	* Returns the applicable info with the primary key or throws a {@link NoSuchApplicableInfoException} if it could not be found.
	*
	* @param applicableInfoId the primary key of the applicable info
	* @return the applicable info
	* @throws NoSuchApplicableInfoException if a applicable info with the primary key could not be found
	*/
	public ApplicableInfo findByPrimaryKey(long applicableInfoId)
		throws NoSuchApplicableInfoException;

	/**
	* Returns the applicable info with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param applicableInfoId the primary key of the applicable info
	* @return the applicable info, or <code>null</code> if a applicable info with the primary key could not be found
	*/
	public ApplicableInfo fetchByPrimaryKey(long applicableInfoId);

	@Override
	public java.util.Map<java.io.Serializable, ApplicableInfo> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the applicable infos.
	*
	* @return the applicable infos
	*/
	public java.util.List<ApplicableInfo> findAll();

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
	public java.util.List<ApplicableInfo> findAll(int start, int end);

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
	public java.util.List<ApplicableInfo> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicableInfo> orderByComparator);

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
	public java.util.List<ApplicableInfo> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ApplicableInfo> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the applicable infos from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of applicable infos.
	*
	* @return the number of applicable infos
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}