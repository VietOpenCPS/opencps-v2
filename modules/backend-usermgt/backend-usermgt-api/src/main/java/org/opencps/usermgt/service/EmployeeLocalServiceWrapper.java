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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link EmployeeLocalService}.
 *
 * @author khoavu
 * @see EmployeeLocalService
 * @generated
 */
@ProviderType
public class EmployeeLocalServiceWrapper implements EmployeeLocalService,
	ServiceWrapper<EmployeeLocalService> {
	public EmployeeLocalServiceWrapper(
		EmployeeLocalService employeeLocalService) {
		_employeeLocalService = employeeLocalService;
	}

	/**
	* Adds the employee to the database. Also notifies the appropriate model listeners.
	*
	* @param employee the employee
	* @return the employee that was added
	*/
	@Override
	public org.opencps.usermgt.model.Employee addEmployee(
		org.opencps.usermgt.model.Employee employee) {
		return _employeeLocalService.addEmployee(employee);
	}

	@Override
	public org.opencps.usermgt.model.Employee addEmployee(long userId,
		long groupId, String fullName, String employeeNo, int gender,
		java.util.Date birthDate, String telNo, String mobile, String email,
		int workingStatus, long mainJobPostId, String title,
		boolean isCreateUser, java.util.Date recruitDate,
		java.util.Date leaveDate,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.usermgt.exception.DuplicateEmployeeNoException,
			org.opencps.usermgt.exception.DuplicateEmployeeEmailException,
			backend.auth.api.exception.UnauthenticationException,
			backend.auth.api.exception.UnauthorizationException,
			com.liferay.portal.kernel.exception.NoSuchUserException,
			com.liferay.portal.kernel.exception.PortalException {
		return _employeeLocalService.addEmployee(userId, groupId, fullName,
			employeeNo, gender, birthDate, telNo, mobile, email, workingStatus,
			mainJobPostId, title, isCreateUser, recruitDate, leaveDate,
			serviceContext);
	}

	@Override
	public org.opencps.usermgt.model.Employee adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return _employeeLocalService.adminProcessData(objectData);
	}

	@Override
	public org.opencps.usermgt.model.Employee adminProcessDelete(Long id) {
		return _employeeLocalService.adminProcessDelete(id);
	}

	@Override
	public long countLuceneSearchEngine(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _employeeLocalService.countLuceneSearchEngine(params,
			searchContext);
	}

	/**
	* Creates a new employee with the primary key. Does not add the employee to the database.
	*
	* @param employeeId the primary key for the new employee
	* @return the new employee
	*/
	@Override
	public org.opencps.usermgt.model.Employee createEmployee(long employeeId) {
		return _employeeLocalService.createEmployee(employeeId);
	}

	/**
	* Deletes the employee from the database. Also notifies the appropriate model listeners.
	*
	* @param employee the employee
	* @return the employee that was removed
	*/
	@Override
	public org.opencps.usermgt.model.Employee deleteEmployee(
		org.opencps.usermgt.model.Employee employee) {
		return _employeeLocalService.deleteEmployee(employee);
	}

	/**
	* Deletes the employee with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param employeeId the primary key of the employee
	* @return the employee that was removed
	* @throws PortalException if a employee with the primary key could not be found
	*/
	@Override
	public org.opencps.usermgt.model.Employee deleteEmployee(long employeeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _employeeLocalService.deleteEmployee(employeeId);
	}

	@Override
	public org.opencps.usermgt.model.Employee deleteEmployee(long employeeId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws backend.auth.api.exception.UnauthenticationException,
			backend.auth.api.exception.UnauthorizationException,
			backend.auth.api.exception.NotFoundException {
		return _employeeLocalService.deleteEmployee(employeeId, serviceContext);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _employeeLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _employeeLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _employeeLocalService.dynamicQuery(dynamicQuery);
	}

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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _employeeLocalService.dynamicQuery(dynamicQuery, start, end);
	}

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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _employeeLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _employeeLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _employeeLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public org.opencps.usermgt.model.Employee fetchByF_mappingUserId(
		long groupId, long mappingUserId) {
		return _employeeLocalService.fetchByF_mappingUserId(groupId,
			mappingUserId);
	}

	@Override
	public org.opencps.usermgt.model.Employee fetchByFB_MUID(long mappingUserId) {
		return _employeeLocalService.fetchByFB_MUID(mappingUserId);
	}

	@Override
	public org.opencps.usermgt.model.Employee fetchEmployee(long employeeId) {
		return _employeeLocalService.fetchEmployee(employeeId);
	}

	/**
	* Returns the employee matching the UUID and group.
	*
	* @param uuid the employee's UUID
	* @param groupId the primary key of the group
	* @return the matching employee, or <code>null</code> if a matching employee could not be found
	*/
	@Override
	public org.opencps.usermgt.model.Employee fetchEmployeeByUuidAndGroupId(
		String uuid, long groupId) {
		return _employeeLocalService.fetchEmployeeByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _employeeLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the employee with the primary key.
	*
	* @param employeeId the primary key of the employee
	* @return the employee
	* @throws PortalException if a employee with the primary key could not be found
	*/
	@Override
	public org.opencps.usermgt.model.Employee getEmployee(long employeeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _employeeLocalService.getEmployee(employeeId);
	}

	@Override
	public org.opencps.usermgt.model.Employee getEmployeeByEmpNo(long groupId,
		String employeeNo) {
		return _employeeLocalService.getEmployeeByEmpNo(groupId, employeeNo);
	}

	/**
	* Returns the employee matching the UUID and group.
	*
	* @param uuid the employee's UUID
	* @param groupId the primary key of the group
	* @return the matching employee
	* @throws PortalException if a matching employee could not be found
	*/
	@Override
	public org.opencps.usermgt.model.Employee getEmployeeByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _employeeLocalService.getEmployeeByUuidAndGroupId(uuid, groupId);
	}

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
	@Override
	public java.util.List<org.opencps.usermgt.model.Employee> getEmployees(
		int start, int end) {
		return _employeeLocalService.getEmployees(start, end);
	}

	/**
	* Returns all the employees matching the UUID and company.
	*
	* @param uuid the UUID of the employees
	* @param companyId the primary key of the company
	* @return the matching employees, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.usermgt.model.Employee> getEmployeesByUuidAndCompanyId(
		String uuid, long companyId) {
		return _employeeLocalService.getEmployeesByUuidAndCompanyId(uuid,
			companyId);
	}

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
	@Override
	public java.util.List<org.opencps.usermgt.model.Employee> getEmployeesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.usermgt.model.Employee> orderByComparator) {
		return _employeeLocalService.getEmployeesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of employees.
	*
	* @return the number of employees
	*/
	@Override
	public int getEmployeesCount() {
		return _employeeLocalService.getEmployeesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _employeeLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _employeeLocalService.getIndexableActionableDynamicQuery();
	}

	@Override
	public java.util.List<org.opencps.usermgt.model.Employee> getLstEmployee(
		long groupId, long userId) {
		return _employeeLocalService.getLstEmployee(groupId, userId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _employeeLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _employeeLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public void isExits(long groupId, String employeeNo, String email)
		throws org.opencps.usermgt.exception.DuplicateEmployeeNoException,
			org.opencps.usermgt.exception.DuplicateEmployeeEmailException {
		_employeeLocalService.isExits(groupId, employeeNo, email);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits luceneSearchEngine(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _employeeLocalService.luceneSearchEngine(params, sorts, start,
			end, searchContext);
	}

	/**
	* Updates the employee in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param employee the employee
	* @return the employee that was updated
	*/
	@Override
	public org.opencps.usermgt.model.Employee updateEmployee(
		org.opencps.usermgt.model.Employee employee) {
		return _employeeLocalService.updateEmployee(employee);
	}

	@Override
	public org.opencps.usermgt.model.Employee updateEmployee(long userId,
		long employeeId, String fullName, String employeeNo, int gender,
		java.util.Date birthDate, String telNo, String mobile, String email,
		int workingStatus, long mainJobPostId, long photoFileEntryId,
		long mappingUserId, String title, java.util.Date recruitDate,
		java.util.Date leaveDate,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.usermgt.exception.DuplicateEmployeeNoException,
			org.opencps.usermgt.exception.DuplicateEmployeeEmailException,
			backend.auth.api.exception.UnauthenticationException,
			backend.auth.api.exception.UnauthorizationException,
			com.liferay.portal.kernel.exception.NoSuchUserException,
			backend.auth.api.exception.NotFoundException,
			com.liferay.portal.kernel.exception.PortalException {
		return _employeeLocalService.updateEmployee(userId, employeeId,
			fullName, employeeNo, gender, birthDate, telNo, mobile, email,
			workingStatus, mainJobPostId, photoFileEntryId, mappingUserId,
			title, recruitDate, leaveDate, serviceContext);
	}

	@Override
	public org.opencps.usermgt.model.Employee updatePayload(long userId,
		long groupId, long fileCertId, long fileSignId, String fileCertPath,
		String fileSignPath,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _employeeLocalService.updatePayload(userId, groupId, fileCertId,
			fileSignId, fileCertPath, fileSignPath, serviceContext);
	}

	@Override
	public EmployeeLocalService getWrappedService() {
		return _employeeLocalService;
	}

	@Override
	public void setWrappedService(EmployeeLocalService employeeLocalService) {
		_employeeLocalService = employeeLocalService;
	}

	private EmployeeLocalService _employeeLocalService;
}