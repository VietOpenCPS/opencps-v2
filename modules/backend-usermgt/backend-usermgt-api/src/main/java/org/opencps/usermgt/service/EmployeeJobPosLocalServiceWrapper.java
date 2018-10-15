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
 * Provides a wrapper for {@link EmployeeJobPosLocalService}.
 *
 * @author khoavu
 * @see EmployeeJobPosLocalService
 * @generated
 */
@ProviderType
public class EmployeeJobPosLocalServiceWrapper
	implements EmployeeJobPosLocalService,
		ServiceWrapper<EmployeeJobPosLocalService> {
	public EmployeeJobPosLocalServiceWrapper(
		EmployeeJobPosLocalService employeeJobPosLocalService) {
		_employeeJobPosLocalService = employeeJobPosLocalService;
	}

	/**
	* Adds the employee job pos to the database. Also notifies the appropriate model listeners.
	*
	* @param employeeJobPos the employee job pos
	* @return the employee job pos that was added
	*/
	@Override
	public org.opencps.usermgt.model.EmployeeJobPos addEmployeeJobPos(
		org.opencps.usermgt.model.EmployeeJobPos employeeJobPos) {
		return _employeeJobPosLocalService.addEmployeeJobPos(employeeJobPos);
	}

	@Override
	public org.opencps.usermgt.model.EmployeeJobPos addEmployeeJobPos(
		long userId, long groupId, long employeeId, long jobPostId,
		long workingUnitId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.asset.kernel.exception.DuplicateCategoryException,
			backend.auth.api.exception.UnauthenticationException,
			backend.auth.api.exception.UnauthorizationException,
			com.liferay.portal.kernel.exception.NoSuchUserException,
			com.liferay.portal.kernel.exception.PortalException {
		return _employeeJobPosLocalService.addEmployeeJobPos(userId, groupId,
			employeeId, jobPostId, workingUnitId, serviceContext);
	}

	@Override
	public long countLuceneSearchEngine(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _employeeJobPosLocalService.countLuceneSearchEngine(params,
			searchContext);
	}

	/**
	* Creates a new employee job pos with the primary key. Does not add the employee job pos to the database.
	*
	* @param employeeJobPosId the primary key for the new employee job pos
	* @return the new employee job pos
	*/
	@Override
	public org.opencps.usermgt.model.EmployeeJobPos createEmployeeJobPos(
		long employeeJobPosId) {
		return _employeeJobPosLocalService.createEmployeeJobPos(employeeJobPosId);
	}

	/**
	* Deletes the employee job pos from the database. Also notifies the appropriate model listeners.
	*
	* @param employeeJobPos the employee job pos
	* @return the employee job pos that was removed
	*/
	@Override
	public org.opencps.usermgt.model.EmployeeJobPos deleteEmployeeJobPos(
		org.opencps.usermgt.model.EmployeeJobPos employeeJobPos) {
		return _employeeJobPosLocalService.deleteEmployeeJobPos(employeeJobPos);
	}

	/**
	* Deletes the employee job pos with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param employeeJobPosId the primary key of the employee job pos
	* @return the employee job pos that was removed
	* @throws PortalException if a employee job pos with the primary key could not be found
	*/
	@Override
	public org.opencps.usermgt.model.EmployeeJobPos deleteEmployeeJobPos(
		long employeeJobPosId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _employeeJobPosLocalService.deleteEmployeeJobPos(employeeJobPosId);
	}

	@Override
	public org.opencps.usermgt.model.EmployeeJobPos deleteEmployeeJobPos(
		long employeeJobPosId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws backend.auth.api.exception.UnauthenticationException,
			backend.auth.api.exception.UnauthorizationException,
			backend.auth.api.exception.NotFoundException {
		return _employeeJobPosLocalService.deleteEmployeeJobPos(employeeJobPosId,
			serviceContext);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _employeeJobPosLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _employeeJobPosLocalService.dynamicQuery();
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
		return _employeeJobPosLocalService.dynamicQuery(dynamicQuery);
	}

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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _employeeJobPosLocalService.dynamicQuery(dynamicQuery, start, end);
	}

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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _employeeJobPosLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _employeeJobPosLocalService.dynamicQueryCount(dynamicQuery);
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
		return _employeeJobPosLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.usermgt.model.EmployeeJobPos fetchByF_EmployeeId_jobPostId(
		long groupId, long employeeId, long jobPostId) {
		return _employeeJobPosLocalService.fetchByF_EmployeeId_jobPostId(groupId,
			employeeId, jobPostId);
	}

	@Override
	public org.opencps.usermgt.model.EmployeeJobPos fetchEmployeeJobPos(
		long employeeJobPosId) {
		return _employeeJobPosLocalService.fetchEmployeeJobPos(employeeJobPosId);
	}

	/**
	* Returns the employee job pos matching the UUID and group.
	*
	* @param uuid the employee job pos's UUID
	* @param groupId the primary key of the group
	* @return the matching employee job pos, or <code>null</code> if a matching employee job pos could not be found
	*/
	@Override
	public org.opencps.usermgt.model.EmployeeJobPos fetchEmployeeJobPosByUuidAndGroupId(
		String uuid, long groupId) {
		return _employeeJobPosLocalService.fetchEmployeeJobPosByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public java.util.List<org.opencps.usermgt.model.EmployeeJobPos> findByF_EmployeeId(
		long employeeId) {
		return _employeeJobPosLocalService.findByF_EmployeeId(employeeId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _employeeJobPosLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<org.opencps.usermgt.model.EmployeeJobPos> getByJobPostId(
		long groupId, long jobPostId) {
		return _employeeJobPosLocalService.getByJobPostId(groupId, jobPostId);
	}

	/**
	* Returns the employee job pos with the primary key.
	*
	* @param employeeJobPosId the primary key of the employee job pos
	* @return the employee job pos
	* @throws PortalException if a employee job pos with the primary key could not be found
	*/
	@Override
	public org.opencps.usermgt.model.EmployeeJobPos getEmployeeJobPos(
		long employeeJobPosId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _employeeJobPosLocalService.getEmployeeJobPos(employeeJobPosId);
	}

	@Override
	public org.opencps.usermgt.model.EmployeeJobPos getEmployeeJobPosbyGidEmpId(
		long groupId, long employeeId) {
		return _employeeJobPosLocalService.getEmployeeJobPosbyGidEmpId(groupId,
			employeeId);
	}

	/**
	* Returns the employee job pos matching the UUID and group.
	*
	* @param uuid the employee job pos's UUID
	* @param groupId the primary key of the group
	* @return the matching employee job pos
	* @throws PortalException if a matching employee job pos could not be found
	*/
	@Override
	public org.opencps.usermgt.model.EmployeeJobPos getEmployeeJobPosByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _employeeJobPosLocalService.getEmployeeJobPosByUuidAndGroupId(uuid,
			groupId);
	}

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
	@Override
	public java.util.List<org.opencps.usermgt.model.EmployeeJobPos> getEmployeeJobPoses(
		int start, int end) {
		return _employeeJobPosLocalService.getEmployeeJobPoses(start, end);
	}

	/**
	* Returns all the employee job poses matching the UUID and company.
	*
	* @param uuid the UUID of the employee job poses
	* @param companyId the primary key of the company
	* @return the matching employee job poses, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.usermgt.model.EmployeeJobPos> getEmployeeJobPosesByUuidAndCompanyId(
		String uuid, long companyId) {
		return _employeeJobPosLocalService.getEmployeeJobPosesByUuidAndCompanyId(uuid,
			companyId);
	}

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
	@Override
	public java.util.List<org.opencps.usermgt.model.EmployeeJobPos> getEmployeeJobPosesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.usermgt.model.EmployeeJobPos> orderByComparator) {
		return _employeeJobPosLocalService.getEmployeeJobPosesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of employee job poses.
	*
	* @return the number of employee job poses
	*/
	@Override
	public int getEmployeeJobPosesCount() {
		return _employeeJobPosLocalService.getEmployeeJobPosesCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _employeeJobPosLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _employeeJobPosLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _employeeJobPosLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _employeeJobPosLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits luceneSearchEngine(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _employeeJobPosLocalService.luceneSearchEngine(params, sorts,
			start, end, searchContext);
	}

	/**
	* Updates the employee job pos in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param employeeJobPos the employee job pos
	* @return the employee job pos that was updated
	*/
	@Override
	public org.opencps.usermgt.model.EmployeeJobPos updateEmployeeJobPos(
		org.opencps.usermgt.model.EmployeeJobPos employeeJobPos) {
		return _employeeJobPosLocalService.updateEmployeeJobPos(employeeJobPos);
	}

	@Override
	public org.opencps.usermgt.model.EmployeeJobPos updateEmployeeJobPos(
		long userId, long employeeJobPosId, long jobPostId, long workingUnitId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.asset.kernel.exception.DuplicateCategoryException,
			backend.auth.api.exception.UnauthenticationException,
			backend.auth.api.exception.UnauthorizationException,
			com.liferay.portal.kernel.exception.NoSuchUserException,
			backend.auth.api.exception.NotFoundException {
		return _employeeJobPosLocalService.updateEmployeeJobPos(userId,
			employeeJobPosId, jobPostId, workingUnitId, serviceContext);
	}

	@Override
	public EmployeeJobPosLocalService getWrappedService() {
		return _employeeJobPosLocalService;
	}

	@Override
	public void setWrappedService(
		EmployeeJobPosLocalService employeeJobPosLocalService) {
		_employeeJobPosLocalService = employeeJobPosLocalService;
	}

	private EmployeeJobPosLocalService _employeeJobPosLocalService;
}