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

import org.opencps.dossiermgt.exception.NoSuchDynamicReportException;
import org.opencps.dossiermgt.model.DynamicReport;

/**
 * The persistence interface for the dynamic report service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author huymq
 * @see org.opencps.dossiermgt.service.persistence.impl.DynamicReportPersistenceImpl
 * @see DynamicReportUtil
 * @generated
 */
@ProviderType
public interface DynamicReportPersistence extends BasePersistence<DynamicReport> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DynamicReportUtil} to access the dynamic report persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the dynamic reports where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching dynamic reports
	*/
	public java.util.List<DynamicReport> findByUuid(String uuid);

	/**
	* Returns a range of all the dynamic reports where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DynamicReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dynamic reports
	* @param end the upper bound of the range of dynamic reports (not inclusive)
	* @return the range of matching dynamic reports
	*/
	public java.util.List<DynamicReport> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the dynamic reports where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DynamicReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dynamic reports
	* @param end the upper bound of the range of dynamic reports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dynamic reports
	*/
	public java.util.List<DynamicReport> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DynamicReport> orderByComparator);

	/**
	* Returns an ordered range of all the dynamic reports where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DynamicReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of dynamic reports
	* @param end the upper bound of the range of dynamic reports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dynamic reports
	*/
	public java.util.List<DynamicReport> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<DynamicReport> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dynamic report in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dynamic report
	* @throws NoSuchDynamicReportException if a matching dynamic report could not be found
	*/
	public DynamicReport findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DynamicReport> orderByComparator)
		throws NoSuchDynamicReportException;

	/**
	* Returns the first dynamic report in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dynamic report, or <code>null</code> if a matching dynamic report could not be found
	*/
	public DynamicReport fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DynamicReport> orderByComparator);

	/**
	* Returns the last dynamic report in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dynamic report
	* @throws NoSuchDynamicReportException if a matching dynamic report could not be found
	*/
	public DynamicReport findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DynamicReport> orderByComparator)
		throws NoSuchDynamicReportException;

	/**
	* Returns the last dynamic report in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dynamic report, or <code>null</code> if a matching dynamic report could not be found
	*/
	public DynamicReport fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DynamicReport> orderByComparator);

	/**
	* Returns the dynamic reports before and after the current dynamic report in the ordered set where uuid = &#63;.
	*
	* @param dynamicReportId the primary key of the current dynamic report
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dynamic report
	* @throws NoSuchDynamicReportException if a dynamic report with the primary key could not be found
	*/
	public DynamicReport[] findByUuid_PrevAndNext(long dynamicReportId,
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DynamicReport> orderByComparator)
		throws NoSuchDynamicReportException;

	/**
	* Removes all the dynamic reports where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of dynamic reports where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching dynamic reports
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the dynamic report where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchDynamicReportException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dynamic report
	* @throws NoSuchDynamicReportException if a matching dynamic report could not be found
	*/
	public DynamicReport findByUUID_G(String uuid, long groupId)
		throws NoSuchDynamicReportException;

	/**
	* Returns the dynamic report where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching dynamic report, or <code>null</code> if a matching dynamic report could not be found
	*/
	public DynamicReport fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the dynamic report where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dynamic report, or <code>null</code> if a matching dynamic report could not be found
	*/
	public DynamicReport fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the dynamic report where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the dynamic report that was removed
	*/
	public DynamicReport removeByUUID_G(String uuid, long groupId)
		throws NoSuchDynamicReportException;

	/**
	* Returns the number of dynamic reports where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching dynamic reports
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the dynamic reports where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching dynamic reports
	*/
	public java.util.List<DynamicReport> findByUuid_C(String uuid,
		long companyId);

	/**
	* Returns a range of all the dynamic reports where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DynamicReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dynamic reports
	* @param end the upper bound of the range of dynamic reports (not inclusive)
	* @return the range of matching dynamic reports
	*/
	public java.util.List<DynamicReport> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the dynamic reports where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DynamicReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dynamic reports
	* @param end the upper bound of the range of dynamic reports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dynamic reports
	*/
	public java.util.List<DynamicReport> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DynamicReport> orderByComparator);

	/**
	* Returns an ordered range of all the dynamic reports where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DynamicReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of dynamic reports
	* @param end the upper bound of the range of dynamic reports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dynamic reports
	*/
	public java.util.List<DynamicReport> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DynamicReport> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dynamic report in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dynamic report
	* @throws NoSuchDynamicReportException if a matching dynamic report could not be found
	*/
	public DynamicReport findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DynamicReport> orderByComparator)
		throws NoSuchDynamicReportException;

	/**
	* Returns the first dynamic report in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dynamic report, or <code>null</code> if a matching dynamic report could not be found
	*/
	public DynamicReport fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DynamicReport> orderByComparator);

	/**
	* Returns the last dynamic report in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dynamic report
	* @throws NoSuchDynamicReportException if a matching dynamic report could not be found
	*/
	public DynamicReport findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DynamicReport> orderByComparator)
		throws NoSuchDynamicReportException;

	/**
	* Returns the last dynamic report in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dynamic report, or <code>null</code> if a matching dynamic report could not be found
	*/
	public DynamicReport fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DynamicReport> orderByComparator);

	/**
	* Returns the dynamic reports before and after the current dynamic report in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param dynamicReportId the primary key of the current dynamic report
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dynamic report
	* @throws NoSuchDynamicReportException if a dynamic report with the primary key could not be found
	*/
	public DynamicReport[] findByUuid_C_PrevAndNext(long dynamicReportId,
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DynamicReport> orderByComparator)
		throws NoSuchDynamicReportException;

	/**
	* Removes all the dynamic reports where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of dynamic reports where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching dynamic reports
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Returns the dynamic report where groupId = &#63; and reportCode = &#63; or throws a {@link NoSuchDynamicReportException} if it could not be found.
	*
	* @param groupId the group ID
	* @param reportCode the report code
	* @return the matching dynamic report
	* @throws NoSuchDynamicReportException if a matching dynamic report could not be found
	*/
	public DynamicReport findByF_GID_CODE(long groupId, String reportCode)
		throws NoSuchDynamicReportException;

	/**
	* Returns the dynamic report where groupId = &#63; and reportCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param reportCode the report code
	* @return the matching dynamic report, or <code>null</code> if a matching dynamic report could not be found
	*/
	public DynamicReport fetchByF_GID_CODE(long groupId, String reportCode);

	/**
	* Returns the dynamic report where groupId = &#63; and reportCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param reportCode the report code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dynamic report, or <code>null</code> if a matching dynamic report could not be found
	*/
	public DynamicReport fetchByF_GID_CODE(long groupId, String reportCode,
		boolean retrieveFromCache);

	/**
	* Removes the dynamic report where groupId = &#63; and reportCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param reportCode the report code
	* @return the dynamic report that was removed
	*/
	public DynamicReport removeByF_GID_CODE(long groupId, String reportCode)
		throws NoSuchDynamicReportException;

	/**
	* Returns the number of dynamic reports where groupId = &#63; and reportCode = &#63;.
	*
	* @param groupId the group ID
	* @param reportCode the report code
	* @return the number of matching dynamic reports
	*/
	public int countByF_GID_CODE(long groupId, String reportCode);

	/**
	* Caches the dynamic report in the entity cache if it is enabled.
	*
	* @param dynamicReport the dynamic report
	*/
	public void cacheResult(DynamicReport dynamicReport);

	/**
	* Caches the dynamic reports in the entity cache if it is enabled.
	*
	* @param dynamicReports the dynamic reports
	*/
	public void cacheResult(java.util.List<DynamicReport> dynamicReports);

	/**
	* Creates a new dynamic report with the primary key. Does not add the dynamic report to the database.
	*
	* @param dynamicReportId the primary key for the new dynamic report
	* @return the new dynamic report
	*/
	public DynamicReport create(long dynamicReportId);

	/**
	* Removes the dynamic report with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dynamicReportId the primary key of the dynamic report
	* @return the dynamic report that was removed
	* @throws NoSuchDynamicReportException if a dynamic report with the primary key could not be found
	*/
	public DynamicReport remove(long dynamicReportId)
		throws NoSuchDynamicReportException;

	public DynamicReport updateImpl(DynamicReport dynamicReport);

	/**
	* Returns the dynamic report with the primary key or throws a {@link NoSuchDynamicReportException} if it could not be found.
	*
	* @param dynamicReportId the primary key of the dynamic report
	* @return the dynamic report
	* @throws NoSuchDynamicReportException if a dynamic report with the primary key could not be found
	*/
	public DynamicReport findByPrimaryKey(long dynamicReportId)
		throws NoSuchDynamicReportException;

	/**
	* Returns the dynamic report with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param dynamicReportId the primary key of the dynamic report
	* @return the dynamic report, or <code>null</code> if a dynamic report with the primary key could not be found
	*/
	public DynamicReport fetchByPrimaryKey(long dynamicReportId);

	@Override
	public java.util.Map<java.io.Serializable, DynamicReport> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the dynamic reports.
	*
	* @return the dynamic reports
	*/
	public java.util.List<DynamicReport> findAll();

	/**
	* Returns a range of all the dynamic reports.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DynamicReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dynamic reports
	* @param end the upper bound of the range of dynamic reports (not inclusive)
	* @return the range of dynamic reports
	*/
	public java.util.List<DynamicReport> findAll(int start, int end);

	/**
	* Returns an ordered range of all the dynamic reports.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DynamicReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dynamic reports
	* @param end the upper bound of the range of dynamic reports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of dynamic reports
	*/
	public java.util.List<DynamicReport> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DynamicReport> orderByComparator);

	/**
	* Returns an ordered range of all the dynamic reports.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DynamicReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dynamic reports
	* @param end the upper bound of the range of dynamic reports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of dynamic reports
	*/
	public java.util.List<DynamicReport> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DynamicReport> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the dynamic reports from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of dynamic reports.
	*
	* @return the number of dynamic reports
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}