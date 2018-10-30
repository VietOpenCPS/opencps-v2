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

import org.opencps.usermgt.exception.DuplicateEmployeeEmailException;
import org.opencps.usermgt.exception.DuplicateEmployeeNoException;
import org.opencps.usermgt.model.Employee;

import java.io.Serializable;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Provides the local service interface for Employee. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author khoavu
 * @see EmployeeLocalServiceUtil
 * @see org.opencps.usermgt.service.base.EmployeeLocalServiceBaseImpl
 * @see org.opencps.usermgt.service.impl.EmployeeLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface EmployeeLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EmployeeLocalServiceUtil} to access the employee local service. Add custom service methods to {@link org.opencps.usermgt.service.impl.EmployeeLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the employee to the database. Also notifies the appropriate model listeners.
	*
	* @param employee the employee
	* @return the employee that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Employee addEmployee(Employee employee);

	@Indexable(type = IndexableType.REINDEX)
	public Employee addEmployee(long userId, long groupId, String fullName,
		String employeeNo, int gender, Date birthDate, String telNo,
		String mobile, String email, int workingStatus, long mainJobPostId,
		String title, boolean isCreateUser, Date recruitDate, Date leaveDate,
		ServiceContext serviceContext)
		throws DuplicateEmployeeNoException, DuplicateEmployeeEmailException,
			UnauthenticationException, UnauthorizationException,
			NoSuchUserException, PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public Employee adminProcessData(JSONObject objectData);

	@Indexable(type = IndexableType.DELETE)
	public Employee adminProcessDelete(Long id);

	public long countLuceneSearchEngine(LinkedHashMap<String, Object> params,
		SearchContext searchContext) throws ParseException, SearchException;

	/**
	* Creates a new employee with the primary key. Does not add the employee to the database.
	*
	* @param employeeId the primary key for the new employee
	* @return the new employee
	*/
	@Transactional(enabled = false)
	public Employee createEmployee(long employeeId);

	/**
	* Deletes the employee from the database. Also notifies the appropriate model listeners.
	*
	* @param employee the employee
	* @return the employee that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public Employee deleteEmployee(Employee employee);

	/**
	* Deletes the employee with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param employeeId the primary key of the employee
	* @return the employee that was removed
	* @throws PortalException if a employee with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public Employee deleteEmployee(long employeeId) throws PortalException;

	@Indexable(type = IndexableType.DELETE)
	public Employee deleteEmployee(long employeeId,
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public Employee fetchByF_mappingUserId(long groupId, long mappingUserId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Employee fetchByFB_MUID(long mappingUserId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Employee fetchEmployee(long employeeId);

	/**
	* Returns the employee matching the UUID and group.
	*
	* @param uuid the employee's UUID
	* @param groupId the primary key of the group
	* @return the matching employee, or <code>null</code> if a matching employee could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Employee fetchEmployeeByUuidAndGroupId(String uuid, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	/**
	* Returns the employee with the primary key.
	*
	* @param employeeId the primary key of the employee
	* @return the employee
	* @throws PortalException if a employee with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Employee getEmployee(long employeeId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Employee getEmployeeByEmpNo(long groupId, String employeeNo);

	/**
	* Returns the employee matching the UUID and group.
	*
	* @param uuid the employee's UUID
	* @param groupId the primary key of the group
	* @return the matching employee
	* @throws PortalException if a matching employee could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Employee getEmployeeByUuidAndGroupId(String uuid, long groupId)
		throws PortalException;

	/**
	* Returns a range of all the employees.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of employees
	* @param end the upper bound of the range of employees (not inclusive)
	* @return the range of employees
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Employee> getEmployees(int start, int end);

	/**
	* Returns all the employees matching the UUID and company.
	*
	* @param uuid the UUID of the employees
	* @param companyId the primary key of the company
	* @return the matching employees, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Employee> getEmployeesByUuidAndCompanyId(String uuid,
		long companyId);

	/**
	* Returns a range of employees matching the UUID and company.
	*
	* @param uuid the UUID of the employees
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of employees
	* @param end the upper bound of the range of employees (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching employees, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Employee> getEmployeesByUuidAndCompanyId(String uuid,
		long companyId, int start, int end,
		OrderByComparator<Employee> orderByComparator);

	/**
	* Returns the number of employees.
	*
	* @return the number of employees
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getEmployeesCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Employee> getLstEmployee(long groupId, long userId);

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

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public void isExits(long groupId, String employeeNo, String email)
		throws DuplicateEmployeeNoException, DuplicateEmployeeEmailException;

	public Hits luceneSearchEngine(LinkedHashMap<String, Object> params,
		Sort[] sorts, int start, int end, SearchContext searchContext)
		throws ParseException, SearchException;

	/**
	* Updates the employee in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param employee the employee
	* @return the employee that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Employee updateEmployee(Employee employee);

	@Indexable(type = IndexableType.REINDEX)
	public Employee updateEmployee(long userId, long employeeId,
		String fullName, String employeeNo, int gender, Date birthDate,
		String telNo, String mobile, String email, int workingStatus,
		long mainJobPostId, long photoFileEntryId, long mappingUserId,
		String title, Date recruitDate, Date leaveDate,
		ServiceContext serviceContext)
		throws DuplicateEmployeeNoException, DuplicateEmployeeEmailException,
			UnauthenticationException, UnauthorizationException,
			NoSuchUserException, NotFoundException, PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public Employee updatePayload(long userId, long groupId, long fileCertId,
		long fileSignId, String fileCertPath, String fileSignPath,
		ServiceContext serviceContext) throws PortalException;
}