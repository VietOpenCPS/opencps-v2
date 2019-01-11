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

package org.opencps.adminconfig.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.adminconfig.exception.NoSuchDynamicReportException;
import org.opencps.adminconfig.model.DynamicReport;

/**
 * The persistence interface for the dynamic report service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author binhth
 * @see org.opencps.adminconfig.service.persistence.impl.DynamicReportPersistenceImpl
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
	* Returns all the dynamic reports where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching dynamic reports
	*/
	public java.util.List<DynamicReport> findByF_GroupId(long groupId);

	/**
	* Returns a range of all the dynamic reports where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DynamicReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of dynamic reports
	* @param end the upper bound of the range of dynamic reports (not inclusive)
	* @return the range of matching dynamic reports
	*/
	public java.util.List<DynamicReport> findByF_GroupId(long groupId,
		int start, int end);

	/**
	* Returns an ordered range of all the dynamic reports where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DynamicReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of dynamic reports
	* @param end the upper bound of the range of dynamic reports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dynamic reports
	*/
	public java.util.List<DynamicReport> findByF_GroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DynamicReport> orderByComparator);

	/**
	* Returns an ordered range of all the dynamic reports where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DynamicReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of dynamic reports
	* @param end the upper bound of the range of dynamic reports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dynamic reports
	*/
	public java.util.List<DynamicReport> findByF_GroupId(long groupId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DynamicReport> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dynamic report in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dynamic report
	* @throws NoSuchDynamicReportException if a matching dynamic report could not be found
	*/
	public DynamicReport findByF_GroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DynamicReport> orderByComparator)
		throws NoSuchDynamicReportException;

	/**
	* Returns the first dynamic report in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dynamic report, or <code>null</code> if a matching dynamic report could not be found
	*/
	public DynamicReport fetchByF_GroupId_First(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DynamicReport> orderByComparator);

	/**
	* Returns the last dynamic report in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dynamic report
	* @throws NoSuchDynamicReportException if a matching dynamic report could not be found
	*/
	public DynamicReport findByF_GroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DynamicReport> orderByComparator)
		throws NoSuchDynamicReportException;

	/**
	* Returns the last dynamic report in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dynamic report, or <code>null</code> if a matching dynamic report could not be found
	*/
	public DynamicReport fetchByF_GroupId_Last(long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DynamicReport> orderByComparator);

	/**
	* Returns the dynamic reports before and after the current dynamic report in the ordered set where groupId = &#63;.
	*
	* @param dynamicReportId the primary key of the current dynamic report
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dynamic report
	* @throws NoSuchDynamicReportException if a dynamic report with the primary key could not be found
	*/
	public DynamicReport[] findByF_GroupId_PrevAndNext(long dynamicReportId,
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator<DynamicReport> orderByComparator)
		throws NoSuchDynamicReportException;

	/**
	* Removes all the dynamic reports where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	*/
	public void removeByF_GroupId(long groupId);

	/**
	* Returns the number of dynamic reports where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching dynamic reports
	*/
	public int countByF_GroupId(long groupId);

	/**
	* Returns the dynamic report where groupId = &#63; and reportCode = &#63; or throws a {@link NoSuchDynamicReportException} if it could not be found.
	*
	* @param groupId the group ID
	* @param reportCode the report code
	* @return the matching dynamic report
	* @throws NoSuchDynamicReportException if a matching dynamic report could not be found
	*/
	public DynamicReport findByF_reportCode(long groupId, String reportCode)
		throws NoSuchDynamicReportException;

	/**
	* Returns the dynamic report where groupId = &#63; and reportCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param reportCode the report code
	* @return the matching dynamic report, or <code>null</code> if a matching dynamic report could not be found
	*/
	public DynamicReport fetchByF_reportCode(long groupId, String reportCode);

	/**
	* Returns the dynamic report where groupId = &#63; and reportCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param reportCode the report code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching dynamic report, or <code>null</code> if a matching dynamic report could not be found
	*/
	public DynamicReport fetchByF_reportCode(long groupId, String reportCode,
		boolean retrieveFromCache);

	/**
	* Removes the dynamic report where groupId = &#63; and reportCode = &#63; from the database.
	*
	* @param groupId the group ID
	* @param reportCode the report code
	* @return the dynamic report that was removed
	*/
	public DynamicReport removeByF_reportCode(long groupId, String reportCode)
		throws NoSuchDynamicReportException;

	/**
	* Returns the number of dynamic reports where groupId = &#63; and reportCode = &#63;.
	*
	* @param groupId the group ID
	* @param reportCode the report code
	* @return the number of matching dynamic reports
	*/
	public int countByF_reportCode(long groupId, String reportCode);

	/**
	* Returns all the dynamic reports where groupId = &#63; and reportType = &#63;.
	*
	* @param groupId the group ID
	* @param reportType the report type
	* @return the matching dynamic reports
	*/
	public java.util.List<DynamicReport> findByF_reportType(long groupId,
		String reportType);

	/**
	* Returns a range of all the dynamic reports where groupId = &#63; and reportType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DynamicReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param reportType the report type
	* @param start the lower bound of the range of dynamic reports
	* @param end the upper bound of the range of dynamic reports (not inclusive)
	* @return the range of matching dynamic reports
	*/
	public java.util.List<DynamicReport> findByF_reportType(long groupId,
		String reportType, int start, int end);

	/**
	* Returns an ordered range of all the dynamic reports where groupId = &#63; and reportType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DynamicReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param reportType the report type
	* @param start the lower bound of the range of dynamic reports
	* @param end the upper bound of the range of dynamic reports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching dynamic reports
	*/
	public java.util.List<DynamicReport> findByF_reportType(long groupId,
		String reportType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DynamicReport> orderByComparator);

	/**
	* Returns an ordered range of all the dynamic reports where groupId = &#63; and reportType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link DynamicReportModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param groupId the group ID
	* @param reportType the report type
	* @param start the lower bound of the range of dynamic reports
	* @param end the upper bound of the range of dynamic reports (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching dynamic reports
	*/
	public java.util.List<DynamicReport> findByF_reportType(long groupId,
		String reportType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DynamicReport> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first dynamic report in the ordered set where groupId = &#63; and reportType = &#63;.
	*
	* @param groupId the group ID
	* @param reportType the report type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dynamic report
	* @throws NoSuchDynamicReportException if a matching dynamic report could not be found
	*/
	public DynamicReport findByF_reportType_First(long groupId,
		String reportType,
		com.liferay.portal.kernel.util.OrderByComparator<DynamicReport> orderByComparator)
		throws NoSuchDynamicReportException;

	/**
	* Returns the first dynamic report in the ordered set where groupId = &#63; and reportType = &#63;.
	*
	* @param groupId the group ID
	* @param reportType the report type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching dynamic report, or <code>null</code> if a matching dynamic report could not be found
	*/
	public DynamicReport fetchByF_reportType_First(long groupId,
		String reportType,
		com.liferay.portal.kernel.util.OrderByComparator<DynamicReport> orderByComparator);

	/**
	* Returns the last dynamic report in the ordered set where groupId = &#63; and reportType = &#63;.
	*
	* @param groupId the group ID
	* @param reportType the report type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dynamic report
	* @throws NoSuchDynamicReportException if a matching dynamic report could not be found
	*/
	public DynamicReport findByF_reportType_Last(long groupId,
		String reportType,
		com.liferay.portal.kernel.util.OrderByComparator<DynamicReport> orderByComparator)
		throws NoSuchDynamicReportException;

	/**
	* Returns the last dynamic report in the ordered set where groupId = &#63; and reportType = &#63;.
	*
	* @param groupId the group ID
	* @param reportType the report type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching dynamic report, or <code>null</code> if a matching dynamic report could not be found
	*/
	public DynamicReport fetchByF_reportType_Last(long groupId,
		String reportType,
		com.liferay.portal.kernel.util.OrderByComparator<DynamicReport> orderByComparator);

	/**
	* Returns the dynamic reports before and after the current dynamic report in the ordered set where groupId = &#63; and reportType = &#63;.
	*
	* @param dynamicReportId the primary key of the current dynamic report
	* @param groupId the group ID
	* @param reportType the report type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next dynamic report
	* @throws NoSuchDynamicReportException if a dynamic report with the primary key could not be found
	*/
	public DynamicReport[] findByF_reportType_PrevAndNext(
		long dynamicReportId, long groupId, String reportType,
		com.liferay.portal.kernel.util.OrderByComparator<DynamicReport> orderByComparator)
		throws NoSuchDynamicReportException;

	/**
	* Removes all the dynamic reports where groupId = &#63; and reportType = &#63; from the database.
	*
	* @param groupId the group ID
	* @param reportType the report type
	*/
	public void removeByF_reportType(long groupId, String reportType);

	/**
	* Returns the number of dynamic reports where groupId = &#63; and reportType = &#63;.
	*
	* @param groupId the group ID
	* @param reportType the report type
	* @return the number of matching dynamic reports
	*/
	public int countByF_reportType(long groupId, String reportType);

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
}