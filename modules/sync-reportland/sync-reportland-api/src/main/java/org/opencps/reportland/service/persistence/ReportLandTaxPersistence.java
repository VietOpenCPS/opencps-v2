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

package org.opencps.reportland.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.opencps.reportland.exception.NoSuchReportLandTaxException;
import org.opencps.reportland.model.ReportLandTax;

/**
 * The persistence interface for the report land tax service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see org.opencps.reportland.service.persistence.impl.ReportLandTaxPersistenceImpl
 * @see ReportLandTaxUtil
 * @generated
 */
@ProviderType
public interface ReportLandTaxPersistence extends BasePersistence<ReportLandTax> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ReportLandTaxUtil} to access the report land tax persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the report land taxs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching report land taxs
	*/
	public java.util.List<ReportLandTax> findByUuid(String uuid);

	/**
	* Returns a range of all the report land taxs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportLandTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of report land taxs
	* @param end the upper bound of the range of report land taxs (not inclusive)
	* @return the range of matching report land taxs
	*/
	public java.util.List<ReportLandTax> findByUuid(String uuid, int start,
		int end);

	/**
	* Returns an ordered range of all the report land taxs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportLandTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of report land taxs
	* @param end the upper bound of the range of report land taxs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching report land taxs
	*/
	public java.util.List<ReportLandTax> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReportLandTax> orderByComparator);

	/**
	* Returns an ordered range of all the report land taxs where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportLandTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of report land taxs
	* @param end the upper bound of the range of report land taxs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching report land taxs
	*/
	public java.util.List<ReportLandTax> findByUuid(String uuid, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReportLandTax> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first report land tax in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching report land tax
	* @throws NoSuchReportLandTaxException if a matching report land tax could not be found
	*/
	public ReportLandTax findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ReportLandTax> orderByComparator)
		throws NoSuchReportLandTaxException;

	/**
	* Returns the first report land tax in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching report land tax, or <code>null</code> if a matching report land tax could not be found
	*/
	public ReportLandTax fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ReportLandTax> orderByComparator);

	/**
	* Returns the last report land tax in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching report land tax
	* @throws NoSuchReportLandTaxException if a matching report land tax could not be found
	*/
	public ReportLandTax findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ReportLandTax> orderByComparator)
		throws NoSuchReportLandTaxException;

	/**
	* Returns the last report land tax in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching report land tax, or <code>null</code> if a matching report land tax could not be found
	*/
	public ReportLandTax fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ReportLandTax> orderByComparator);

	/**
	* Returns the report land taxs before and after the current report land tax in the ordered set where uuid = &#63;.
	*
	* @param reportId the primary key of the current report land tax
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next report land tax
	* @throws NoSuchReportLandTaxException if a report land tax with the primary key could not be found
	*/
	public ReportLandTax[] findByUuid_PrevAndNext(long reportId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ReportLandTax> orderByComparator)
		throws NoSuchReportLandTaxException;

	/**
	* Removes all the report land taxs where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of report land taxs where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching report land taxs
	*/
	public int countByUuid(String uuid);

	/**
	* Returns the report land tax where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchReportLandTaxException} if it could not be found.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching report land tax
	* @throws NoSuchReportLandTaxException if a matching report land tax could not be found
	*/
	public ReportLandTax findByUUID_G(String uuid, long groupId)
		throws NoSuchReportLandTaxException;

	/**
	* Returns the report land tax where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the matching report land tax, or <code>null</code> if a matching report land tax could not be found
	*/
	public ReportLandTax fetchByUUID_G(String uuid, long groupId);

	/**
	* Returns the report land tax where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching report land tax, or <code>null</code> if a matching report land tax could not be found
	*/
	public ReportLandTax fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache);

	/**
	* Removes the report land tax where uuid = &#63; and groupId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the report land tax that was removed
	*/
	public ReportLandTax removeByUUID_G(String uuid, long groupId)
		throws NoSuchReportLandTaxException;

	/**
	* Returns the number of report land taxs where uuid = &#63; and groupId = &#63;.
	*
	* @param uuid the uuid
	* @param groupId the group ID
	* @return the number of matching report land taxs
	*/
	public int countByUUID_G(String uuid, long groupId);

	/**
	* Returns all the report land taxs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching report land taxs
	*/
	public java.util.List<ReportLandTax> findByUuid_C(String uuid,
		long companyId);

	/**
	* Returns a range of all the report land taxs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportLandTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of report land taxs
	* @param end the upper bound of the range of report land taxs (not inclusive)
	* @return the range of matching report land taxs
	*/
	public java.util.List<ReportLandTax> findByUuid_C(String uuid,
		long companyId, int start, int end);

	/**
	* Returns an ordered range of all the report land taxs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportLandTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of report land taxs
	* @param end the upper bound of the range of report land taxs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching report land taxs
	*/
	public java.util.List<ReportLandTax> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReportLandTax> orderByComparator);

	/**
	* Returns an ordered range of all the report land taxs where uuid = &#63; and companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportLandTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param start the lower bound of the range of report land taxs
	* @param end the upper bound of the range of report land taxs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching report land taxs
	*/
	public java.util.List<ReportLandTax> findByUuid_C(String uuid,
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReportLandTax> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first report land tax in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching report land tax
	* @throws NoSuchReportLandTaxException if a matching report land tax could not be found
	*/
	public ReportLandTax findByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ReportLandTax> orderByComparator)
		throws NoSuchReportLandTaxException;

	/**
	* Returns the first report land tax in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching report land tax, or <code>null</code> if a matching report land tax could not be found
	*/
	public ReportLandTax fetchByUuid_C_First(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ReportLandTax> orderByComparator);

	/**
	* Returns the last report land tax in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching report land tax
	* @throws NoSuchReportLandTaxException if a matching report land tax could not be found
	*/
	public ReportLandTax findByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ReportLandTax> orderByComparator)
		throws NoSuchReportLandTaxException;

	/**
	* Returns the last report land tax in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching report land tax, or <code>null</code> if a matching report land tax could not be found
	*/
	public ReportLandTax fetchByUuid_C_Last(String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ReportLandTax> orderByComparator);

	/**
	* Returns the report land taxs before and after the current report land tax in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param reportId the primary key of the current report land tax
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next report land tax
	* @throws NoSuchReportLandTaxException if a report land tax with the primary key could not be found
	*/
	public ReportLandTax[] findByUuid_C_PrevAndNext(long reportId, String uuid,
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<ReportLandTax> orderByComparator)
		throws NoSuchReportLandTaxException;

	/**
	* Removes all the report land taxs where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	*/
	public void removeByUuid_C(String uuid, long companyId);

	/**
	* Returns the number of report land taxs where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching report land taxs
	*/
	public int countByUuid_C(String uuid, long companyId);

	/**
	* Caches the report land tax in the entity cache if it is enabled.
	*
	* @param reportLandTax the report land tax
	*/
	public void cacheResult(ReportLandTax reportLandTax);

	/**
	* Caches the report land taxs in the entity cache if it is enabled.
	*
	* @param reportLandTaxs the report land taxs
	*/
	public void cacheResult(java.util.List<ReportLandTax> reportLandTaxs);

	/**
	* Creates a new report land tax with the primary key. Does not add the report land tax to the database.
	*
	* @param reportId the primary key for the new report land tax
	* @return the new report land tax
	*/
	public ReportLandTax create(long reportId);

	/**
	* Removes the report land tax with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param reportId the primary key of the report land tax
	* @return the report land tax that was removed
	* @throws NoSuchReportLandTaxException if a report land tax with the primary key could not be found
	*/
	public ReportLandTax remove(long reportId)
		throws NoSuchReportLandTaxException;

	public ReportLandTax updateImpl(ReportLandTax reportLandTax);

	/**
	* Returns the report land tax with the primary key or throws a {@link NoSuchReportLandTaxException} if it could not be found.
	*
	* @param reportId the primary key of the report land tax
	* @return the report land tax
	* @throws NoSuchReportLandTaxException if a report land tax with the primary key could not be found
	*/
	public ReportLandTax findByPrimaryKey(long reportId)
		throws NoSuchReportLandTaxException;

	/**
	* Returns the report land tax with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param reportId the primary key of the report land tax
	* @return the report land tax, or <code>null</code> if a report land tax with the primary key could not be found
	*/
	public ReportLandTax fetchByPrimaryKey(long reportId);

	@Override
	public java.util.Map<java.io.Serializable, ReportLandTax> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the report land taxs.
	*
	* @return the report land taxs
	*/
	public java.util.List<ReportLandTax> findAll();

	/**
	* Returns a range of all the report land taxs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportLandTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of report land taxs
	* @param end the upper bound of the range of report land taxs (not inclusive)
	* @return the range of report land taxs
	*/
	public java.util.List<ReportLandTax> findAll(int start, int end);

	/**
	* Returns an ordered range of all the report land taxs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportLandTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of report land taxs
	* @param end the upper bound of the range of report land taxs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of report land taxs
	*/
	public java.util.List<ReportLandTax> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReportLandTax> orderByComparator);

	/**
	* Returns an ordered range of all the report land taxs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportLandTaxModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of report land taxs
	* @param end the upper bound of the range of report land taxs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of report land taxs
	*/
	public java.util.List<ReportLandTax> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReportLandTax> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the report land taxs from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of report land taxs.
	*
	* @return the number of report land taxs
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}