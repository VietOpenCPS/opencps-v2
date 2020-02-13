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

import org.opencps.adminconfig.exception.NoSuchReportRoleException;
import org.opencps.adminconfig.model.ReportRole;

/**
 * The persistence interface for the report role service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author binhth
 * @see org.opencps.adminconfig.service.persistence.impl.ReportRolePersistenceImpl
 * @see ReportRoleUtil
 * @generated
 */
@ProviderType
public interface ReportRolePersistence extends BasePersistence<ReportRole> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ReportRoleUtil} to access the report role persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the report roles where dynamicReportId = &#63;.
	*
	* @param dynamicReportId the dynamic report ID
	* @return the matching report roles
	*/
	public java.util.List<ReportRole> findByF_DRID(long dynamicReportId);

	/**
	* Returns a range of all the report roles where dynamicReportId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicReportId the dynamic report ID
	* @param start the lower bound of the range of report roles
	* @param end the upper bound of the range of report roles (not inclusive)
	* @return the range of matching report roles
	*/
	public java.util.List<ReportRole> findByF_DRID(long dynamicReportId,
		int start, int end);

	/**
	* Returns an ordered range of all the report roles where dynamicReportId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicReportId the dynamic report ID
	* @param start the lower bound of the range of report roles
	* @param end the upper bound of the range of report roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching report roles
	*/
	public java.util.List<ReportRole> findByF_DRID(long dynamicReportId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReportRole> orderByComparator);

	/**
	* Returns an ordered range of all the report roles where dynamicReportId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicReportId the dynamic report ID
	* @param start the lower bound of the range of report roles
	* @param end the upper bound of the range of report roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching report roles
	*/
	public java.util.List<ReportRole> findByF_DRID(long dynamicReportId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReportRole> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first report role in the ordered set where dynamicReportId = &#63;.
	*
	* @param dynamicReportId the dynamic report ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching report role
	* @throws NoSuchReportRoleException if a matching report role could not be found
	*/
	public ReportRole findByF_DRID_First(long dynamicReportId,
		com.liferay.portal.kernel.util.OrderByComparator<ReportRole> orderByComparator)
		throws NoSuchReportRoleException;

	/**
	* Returns the first report role in the ordered set where dynamicReportId = &#63;.
	*
	* @param dynamicReportId the dynamic report ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching report role, or <code>null</code> if a matching report role could not be found
	*/
	public ReportRole fetchByF_DRID_First(long dynamicReportId,
		com.liferay.portal.kernel.util.OrderByComparator<ReportRole> orderByComparator);

	/**
	* Returns the last report role in the ordered set where dynamicReportId = &#63;.
	*
	* @param dynamicReportId the dynamic report ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching report role
	* @throws NoSuchReportRoleException if a matching report role could not be found
	*/
	public ReportRole findByF_DRID_Last(long dynamicReportId,
		com.liferay.portal.kernel.util.OrderByComparator<ReportRole> orderByComparator)
		throws NoSuchReportRoleException;

	/**
	* Returns the last report role in the ordered set where dynamicReportId = &#63;.
	*
	* @param dynamicReportId the dynamic report ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching report role, or <code>null</code> if a matching report role could not be found
	*/
	public ReportRole fetchByF_DRID_Last(long dynamicReportId,
		com.liferay.portal.kernel.util.OrderByComparator<ReportRole> orderByComparator);

	/**
	* Returns the report roles before and after the current report role in the ordered set where dynamicReportId = &#63;.
	*
	* @param reportRoleId the primary key of the current report role
	* @param dynamicReportId the dynamic report ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next report role
	* @throws NoSuchReportRoleException if a report role with the primary key could not be found
	*/
	public ReportRole[] findByF_DRID_PrevAndNext(long reportRoleId,
		long dynamicReportId,
		com.liferay.portal.kernel.util.OrderByComparator<ReportRole> orderByComparator)
		throws NoSuchReportRoleException;

	/**
	* Removes all the report roles where dynamicReportId = &#63; from the database.
	*
	* @param dynamicReportId the dynamic report ID
	*/
	public void removeByF_DRID(long dynamicReportId);

	/**
	* Returns the number of report roles where dynamicReportId = &#63;.
	*
	* @param dynamicReportId the dynamic report ID
	* @return the number of matching report roles
	*/
	public int countByF_DRID(long dynamicReportId);

	/**
	* Returns the report role where dynamicReportId = &#63; and roleId = &#63; or throws a {@link NoSuchReportRoleException} if it could not be found.
	*
	* @param dynamicReportId the dynamic report ID
	* @param roleId the role ID
	* @return the matching report role
	* @throws NoSuchReportRoleException if a matching report role could not be found
	*/
	public ReportRole findByF_DRID_RID(long dynamicReportId, long roleId)
		throws NoSuchReportRoleException;

	/**
	* Returns the report role where dynamicReportId = &#63; and roleId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param dynamicReportId the dynamic report ID
	* @param roleId the role ID
	* @return the matching report role, or <code>null</code> if a matching report role could not be found
	*/
	public ReportRole fetchByF_DRID_RID(long dynamicReportId, long roleId);

	/**
	* Returns the report role where dynamicReportId = &#63; and roleId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param dynamicReportId the dynamic report ID
	* @param roleId the role ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching report role, or <code>null</code> if a matching report role could not be found
	*/
	public ReportRole fetchByF_DRID_RID(long dynamicReportId, long roleId,
		boolean retrieveFromCache);

	/**
	* Removes the report role where dynamicReportId = &#63; and roleId = &#63; from the database.
	*
	* @param dynamicReportId the dynamic report ID
	* @param roleId the role ID
	* @return the report role that was removed
	*/
	public ReportRole removeByF_DRID_RID(long dynamicReportId, long roleId)
		throws NoSuchReportRoleException;

	/**
	* Returns the number of report roles where dynamicReportId = &#63; and roleId = &#63;.
	*
	* @param dynamicReportId the dynamic report ID
	* @param roleId the role ID
	* @return the number of matching report roles
	*/
	public int countByF_DRID_RID(long dynamicReportId, long roleId);

	/**
	* Caches the report role in the entity cache if it is enabled.
	*
	* @param reportRole the report role
	*/
	public void cacheResult(ReportRole reportRole);

	/**
	* Caches the report roles in the entity cache if it is enabled.
	*
	* @param reportRoles the report roles
	*/
	public void cacheResult(java.util.List<ReportRole> reportRoles);

	/**
	* Creates a new report role with the primary key. Does not add the report role to the database.
	*
	* @param reportRoleId the primary key for the new report role
	* @return the new report role
	*/
	public ReportRole create(long reportRoleId);

	/**
	* Removes the report role with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param reportRoleId the primary key of the report role
	* @return the report role that was removed
	* @throws NoSuchReportRoleException if a report role with the primary key could not be found
	*/
	public ReportRole remove(long reportRoleId)
		throws NoSuchReportRoleException;

	public ReportRole updateImpl(ReportRole reportRole);

	/**
	* Returns the report role with the primary key or throws a {@link NoSuchReportRoleException} if it could not be found.
	*
	* @param reportRoleId the primary key of the report role
	* @return the report role
	* @throws NoSuchReportRoleException if a report role with the primary key could not be found
	*/
	public ReportRole findByPrimaryKey(long reportRoleId)
		throws NoSuchReportRoleException;

	/**
	* Returns the report role with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param reportRoleId the primary key of the report role
	* @return the report role, or <code>null</code> if a report role with the primary key could not be found
	*/
	public ReportRole fetchByPrimaryKey(long reportRoleId);

	@Override
	public java.util.Map<java.io.Serializable, ReportRole> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the report roles.
	*
	* @return the report roles
	*/
	public java.util.List<ReportRole> findAll();

	/**
	* Returns a range of all the report roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of report roles
	* @param end the upper bound of the range of report roles (not inclusive)
	* @return the range of report roles
	*/
	public java.util.List<ReportRole> findAll(int start, int end);

	/**
	* Returns an ordered range of all the report roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of report roles
	* @param end the upper bound of the range of report roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of report roles
	*/
	public java.util.List<ReportRole> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReportRole> orderByComparator);

	/**
	* Returns an ordered range of all the report roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReportRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of report roles
	* @param end the upper bound of the range of report roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of report roles
	*/
	public java.util.List<ReportRole> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReportRole> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the report roles from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of report roles.
	*
	* @return the number of report roles
	*/
	public int countAll();
}