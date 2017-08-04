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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Employee. This utility wraps
 * {@link org.mobilink.backend.usermgt.service.impl.EmployeeLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Binhth
 * @see EmployeeLocalService
 * @see org.mobilink.backend.usermgt.service.base.EmployeeLocalServiceBaseImpl
 * @see org.mobilink.backend.usermgt.service.impl.EmployeeLocalServiceImpl
 * @generated
 */
@ProviderType
public class EmployeeLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.mobilink.backend.usermgt.service.impl.EmployeeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	public static com.liferay.portal.kernel.search.Hits luceneSearchEngine(
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return getService()
				   .luceneSearchEngine(params, sorts, start, end, searchContext);
	}

	/**
	* Returns the number of employees.
	*
	* @return the number of employees
	*/
	public static int getEmployeesCount() {
		return getService().getEmployeesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.usermgt.model.impl.EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.usermgt.model.impl.EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns a range of all the employees.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.usermgt.model.impl.EmployeeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of employees
	* @param end the upper bound of the range of employees (not inclusive)
	* @return the range of employees
	*/
	public static java.util.List<org.opencps.usermgt.model.Employee> getEmployees(
		int start, int end) {
		return getService().getEmployees(start, end);
	}

	/**
	* Returns all the employees matching the UUID and company.
	*
	* @param uuid the UUID of the employees
	* @param companyId the primary key of the company
	* @return the matching employees, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.usermgt.model.Employee> getEmployeesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getEmployeesByUuidAndCompanyId(uuid, companyId);
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
	public static java.util.List<org.opencps.usermgt.model.Employee> getEmployeesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.usermgt.model.Employee> orderByComparator) {
		return getService()
				   .getEmployeesByUuidAndCompanyId(uuid, companyId, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static org.opencps.usermgt.model.Employee addEmployee(
		long userId, long groupId, long organizationId,
		java.lang.String fullName, java.lang.String employeeNo, int gender,
		java.util.Date birthDate, java.lang.String telNo,
		java.lang.String mobile, java.lang.String email, int workingStatus,
		long mappingUserId, long mainJobPostId, java.lang.String formScript,
		java.lang.String formData, boolean isCreateUser,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addEmployee(userId, groupId, organizationId, fullName,
			employeeNo, gender, birthDate, telNo, mobile, email, workingStatus,
			mappingUserId, mainJobPostId, formScript, formData, isCreateUser,
			serviceContext);
	}

	/**
	* Adds the employee to the database. Also notifies the appropriate model listeners.
	*
	* @param employee the employee
	* @return the employee that was added
	*/
	public static org.opencps.usermgt.model.Employee addEmployee(
		org.opencps.usermgt.model.Employee employee) {
		return getService().addEmployee(employee);
	}

	public static org.opencps.usermgt.model.Employee changeAvatar(
		long userId, long employeeId, long photoFileEntryId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .changeAvatar(userId, employeeId, photoFileEntryId,
			serviceContext);
	}

	public static org.opencps.usermgt.model.Employee cloneEmployee(
		long userId, long groupId, long employeeId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .cloneEmployee(userId, groupId, employeeId, serviceContext);
	}

	/**
	* Creates a new employee with the primary key. Does not add the employee to the database.
	*
	* @param employeeId the primary key for the new employee
	* @return the new employee
	*/
	public static org.opencps.usermgt.model.Employee createEmployee(
		long employeeId) {
		return getService().createEmployee(employeeId);
	}

	/**
	* Deletes the employee with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param employeeId the primary key of the employee
	* @return the employee that was removed
	* @throws PortalException if a employee with the primary key could not be found
	*/
	public static org.opencps.usermgt.model.Employee deleteEmployee(
		long employeeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteEmployee(employeeId);
	}

	public static org.opencps.usermgt.model.Employee deleteEmployee(
		long employeeId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws java.lang.Exception {
		return getService().deleteEmployee(employeeId, serviceContext);
	}

	/**
	* Deletes the employee from the database. Also notifies the appropriate model listeners.
	*
	* @param employee the employee
	* @return the employee that was removed
	*/
	public static org.opencps.usermgt.model.Employee deleteEmployee(
		org.opencps.usermgt.model.Employee employee) {
		return getService().deleteEmployee(employee);
	}

	public static org.opencps.usermgt.model.Employee fetchEmployee(
		long employeeId) {
		return getService().fetchEmployee(employeeId);
	}

	/**
	* Returns the employee matching the UUID and group.
	*
	* @param uuid the employee's UUID
	* @param groupId the primary key of the group
	* @return the matching employee, or <code>null</code> if a matching employee could not be found
	*/
	public static org.opencps.usermgt.model.Employee fetchEmployeeByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchEmployeeByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the employee with the primary key.
	*
	* @param employeeId the primary key of the employee
	* @return the employee
	* @throws PortalException if a employee with the primary key could not be found
	*/
	public static org.opencps.usermgt.model.Employee getEmployee(
		long employeeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getEmployee(employeeId);
	}

	/**
	* Returns the employee matching the UUID and group.
	*
	* @param uuid the employee's UUID
	* @param groupId the primary key of the group
	* @return the matching employee
	* @throws PortalException if a matching employee could not be found
	*/
	public static org.opencps.usermgt.model.Employee getEmployeeByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getEmployeeByUuidAndGroupId(uuid, groupId);
	}

	public static org.opencps.usermgt.model.Employee updateEmployee(
		long userId, long employeeId, java.lang.String fullName,
		java.lang.String employeeNo, int gender, java.util.Date birthDate,
		java.lang.String telNo, java.lang.String mobile,
		java.lang.String email, int workingStatus, long mainJobPostId,
		java.lang.String formScript, java.lang.String formData,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateEmployee(userId, employeeId, fullName, employeeNo,
			gender, birthDate, telNo, mobile, email, workingStatus,
			mainJobPostId, formScript, formData, serviceContext);
	}

	/**
	* Updates the employee in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param employee the employee
	* @return the employee that was updated
	*/
	public static org.opencps.usermgt.model.Employee updateEmployee(
		org.opencps.usermgt.model.Employee employee) {
		return getService().updateEmployee(employee);
	}

	public static EmployeeLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<EmployeeLocalService, EmployeeLocalService> _serviceTracker =
		ServiceTrackerFactory.open(EmployeeLocalService.class);
}