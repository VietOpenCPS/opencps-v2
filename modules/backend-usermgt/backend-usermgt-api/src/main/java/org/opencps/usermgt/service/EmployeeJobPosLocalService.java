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

package org.opencps.usermgt.service;

import aQute.bnd.annotation.ProviderType;

import backend.auth.api.exception.NotFoundException;
import backend.auth.api.exception.UnauthenticationException;
import backend.auth.api.exception.UnauthorizationException;

import com.liferay.asset.kernel.exception.DuplicateCategoryException;

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.usermgt.model.EmployeeJobPos;

import java.io.Serializable;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Provides the local service interface for EmployeeJobPos. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author khoavu
 * @see EmployeeJobPosLocalServiceUtil
 * @see org.opencps.usermgt.service.base.EmployeeJobPosLocalServiceBaseImpl
 * @see org.opencps.usermgt.service.impl.EmployeeJobPosLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface EmployeeJobPosLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EmployeeJobPosLocalServiceUtil} to access the employee job pos local service. Add custom service methods to {@link org.opencps.usermgt.service.impl.EmployeeJobPosLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the employee job pos to the database. Also notifies the appropriate model listeners.
	*
	* @param employeeJobPos the employee job pos
	* @return the employee job pos that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public EmployeeJobPos addEmployeeJobPos(EmployeeJobPos employeeJobPos);

	@Indexable(type = IndexableType.REINDEX)
	public EmployeeJobPos addEmployeeJobPos(long userId, long groupId,
		long employeeId, long jobPostId, long workingUnitId,
		ServiceContext serviceContext)
		throws DuplicateCategoryException, UnauthenticationException,
			UnauthorizationException, NoSuchUserException, PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public EmployeeJobPos adminProcessData(JSONObject objectData);

	@Indexable(type = IndexableType.DELETE)
	public EmployeeJobPos adminProcessDelete(Long id);

	public long countLuceneSearchEngine(LinkedHashMap<String, Object> params,
		SearchContext searchContext) throws ParseException, SearchException;

	/**
	* Creates a new employee job pos with the primary key. Does not add the employee job pos to the database.
	*
	* @param employeeJobPosId the primary key for the new employee job pos
	* @return the new employee job pos
	*/
	@Transactional(enabled = false)
	public EmployeeJobPos createEmployeeJobPos(long employeeJobPosId);

	/**
	* Deletes the employee job pos from the database. Also notifies the appropriate model listeners.
	*
	* @param employeeJobPos the employee job pos
	* @return the employee job pos that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public EmployeeJobPos deleteEmployeeJobPos(EmployeeJobPos employeeJobPos);

	/**
	* Deletes the employee job pos with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param employeeJobPosId the primary key of the employee job pos
	* @return the employee job pos that was removed
	* @throws PortalException if a employee job pos with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public EmployeeJobPos deleteEmployeeJobPos(long employeeJobPosId)
		throws PortalException;

	@Indexable(type = IndexableType.DELETE)
	public EmployeeJobPos deleteEmployeeJobPos(long employeeJobPosId,
		ServiceContext serviceContext)
		throws UnauthenticationException, UnauthorizationException,
			NotFoundException;

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	public DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.EmployeeJobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.EmployeeJobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public EmployeeJobPos fetchByF_EmployeeId_jobPostId(long groupId,
		long employeeId, long jobPostId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public EmployeeJobPos fetchEmployeeJobPos(long employeeJobPosId);

	/**
	* Returns the employee job pos matching the UUID and group.
	*
	* @param uuid the employee job pos's UUID
	* @param groupId the primary key of the group
	* @return the matching employee job pos, or <code>null</code> if a matching employee job pos could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public EmployeeJobPos fetchEmployeeJobPosByUuidAndGroupId(String uuid,
		long groupId);

	public List<EmployeeJobPos> findByF_EmployeeId(long employeeId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<EmployeeJobPos> getByJobPostId(long groupId, long jobPostId);

	/**
	* Returns the employee job pos with the primary key.
	*
	* @param employeeJobPosId the primary key of the employee job pos
	* @return the employee job pos
	* @throws PortalException if a employee job pos with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public EmployeeJobPos getEmployeeJobPos(long employeeJobPosId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public EmployeeJobPos getEmployeeJobPosbyGidEmpId(long groupId,
		long employeeId);

	/**
	* Returns the employee job pos matching the UUID and group.
	*
	* @param uuid the employee job pos's UUID
	* @param groupId the primary key of the group
	* @return the matching employee job pos
	* @throws PortalException if a matching employee job pos could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public EmployeeJobPos getEmployeeJobPosByUuidAndGroupId(String uuid,
		long groupId) throws PortalException;

	/**
	* Returns a range of all the employee job poses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.EmployeeJobPosModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of employee job poses
	* @param end the upper bound of the range of employee job poses (not inclusive)
	* @return the range of employee job poses
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<EmployeeJobPos> getEmployeeJobPoses(int start, int end);

	/**
	* Returns all the employee job poses matching the UUID and company.
	*
	* @param uuid the UUID of the employee job poses
	* @param companyId the primary key of the company
	* @return the matching employee job poses, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<EmployeeJobPos> getEmployeeJobPosesByUuidAndCompanyId(
		String uuid, long companyId);

	/**
	* Returns a range of employee job poses matching the UUID and company.
	*
	* @param uuid the UUID of the employee job poses
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of employee job poses
	* @param end the upper bound of the range of employee job poses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching employee job poses, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<EmployeeJobPos> getEmployeeJobPosesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<EmployeeJobPos> orderByComparator);

	/**
	* Returns the number of employee job poses.
	*
	* @return the number of employee job poses
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getEmployeeJobPosesCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	public Hits luceneSearchEngine(LinkedHashMap<String, Object> params,
		Sort[] sorts, int start, int end, SearchContext searchContext)
		throws ParseException, SearchException;

	/**
	* Updates the employee job pos in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param employeeJobPos the employee job pos
	* @return the employee job pos that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public EmployeeJobPos updateEmployeeJobPos(EmployeeJobPos employeeJobPos);

	@Indexable(type = IndexableType.REINDEX)
	public EmployeeJobPos updateEmployeeJobPos(long userId,
		long employeeJobPosId, long jobPostId, long workingUnitId,
		ServiceContext serviceContext)
		throws DuplicateCategoryException, UnauthenticationException,
			UnauthorizationException, NoSuchUserException, NotFoundException;
}