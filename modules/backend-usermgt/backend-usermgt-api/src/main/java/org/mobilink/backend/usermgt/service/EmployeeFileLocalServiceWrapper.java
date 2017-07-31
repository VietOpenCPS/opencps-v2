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

package org.mobilink.backend.usermgt.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link EmployeeFileLocalService}.
 *
 * @author Binhth
 * @see EmployeeFileLocalService
 * @generated
 */
@ProviderType
public class EmployeeFileLocalServiceWrapper implements EmployeeFileLocalService,
	ServiceWrapper<EmployeeFileLocalService> {
	public EmployeeFileLocalServiceWrapper(
		EmployeeFileLocalService employeeFileLocalService) {
		_employeeFileLocalService = employeeFileLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _employeeFileLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _employeeFileLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _employeeFileLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _employeeFileLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _employeeFileLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _employeeFileLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits luceneSearchEngine(
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _employeeFileLocalService.luceneSearchEngine(params, sorts,
			start, end, searchContext);
	}

	/**
	* Returns the number of employee files.
	*
	* @return the number of employee files
	*/
	@Override
	public int getEmployeeFilesCount() {
		return _employeeFileLocalService.getEmployeeFilesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _employeeFileLocalService.getOSGiServiceIdentifier();
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
		return _employeeFileLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.usermgt.model.impl.EmployeeFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _employeeFileLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.usermgt.model.impl.EmployeeFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _employeeFileLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the employee files.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.usermgt.model.impl.EmployeeFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of employee files
	* @param end the upper bound of the range of employee files (not inclusive)
	* @return the range of employee files
	*/
	@Override
	public java.util.List<org.mobilink.backend.usermgt.model.EmployeeFile> getEmployeeFiles(
		int start, int end) {
		return _employeeFileLocalService.getEmployeeFiles(start, end);
	}

	/**
	* Returns all the employee files matching the UUID and company.
	*
	* @param uuid the UUID of the employee files
	* @param companyId the primary key of the company
	* @return the matching employee files, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.mobilink.backend.usermgt.model.EmployeeFile> getEmployeeFilesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _employeeFileLocalService.getEmployeeFilesByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of employee files matching the UUID and company.
	*
	* @param uuid the UUID of the employee files
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of employee files
	* @param end the upper bound of the range of employee files (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching employee files, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.mobilink.backend.usermgt.model.EmployeeFile> getEmployeeFilesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.mobilink.backend.usermgt.model.EmployeeFile> orderByComparator) {
		return _employeeFileLocalService.getEmployeeFilesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
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
		return _employeeFileLocalService.dynamicQueryCount(dynamicQuery);
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
		return _employeeFileLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.mobilink.backend.usermgt.model.EmployeeFile addEmployeeFile(
		long userId, long groupId, long employeeId, java.io.File file,
		java.lang.String documentName, java.lang.String title,
		java.lang.String mimeType,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _employeeFileLocalService.addEmployeeFile(userId, groupId,
			employeeId, file, documentName, title, mimeType, serviceContext);
	}

	/**
	* Adds the employee file to the database. Also notifies the appropriate model listeners.
	*
	* @param employeeFile the employee file
	* @return the employee file that was added
	*/
	@Override
	public org.mobilink.backend.usermgt.model.EmployeeFile addEmployeeFile(
		org.mobilink.backend.usermgt.model.EmployeeFile employeeFile) {
		return _employeeFileLocalService.addEmployeeFile(employeeFile);
	}

	/**
	* Creates a new employee file with the primary key. Does not add the employee file to the database.
	*
	* @param employeeFileId the primary key for the new employee file
	* @return the new employee file
	*/
	@Override
	public org.mobilink.backend.usermgt.model.EmployeeFile createEmployeeFile(
		long employeeFileId) {
		return _employeeFileLocalService.createEmployeeFile(employeeFileId);
	}

	/**
	* Deletes the employee file with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param employeeFileId the primary key of the employee file
	* @return the employee file that was removed
	* @throws PortalException if a employee file with the primary key could not be found
	*/
	@Override
	public org.mobilink.backend.usermgt.model.EmployeeFile deleteEmployeeFile(
		long employeeFileId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _employeeFileLocalService.deleteEmployeeFile(employeeFileId);
	}

	@Override
	public org.mobilink.backend.usermgt.model.EmployeeFile deleteEmployeeFile(
		long employeeFileId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws java.lang.Exception {
		return _employeeFileLocalService.deleteEmployeeFile(employeeFileId,
			serviceContext);
	}

	/**
	* Deletes the employee file from the database. Also notifies the appropriate model listeners.
	*
	* @param employeeFile the employee file
	* @return the employee file that was removed
	*/
	@Override
	public org.mobilink.backend.usermgt.model.EmployeeFile deleteEmployeeFile(
		org.mobilink.backend.usermgt.model.EmployeeFile employeeFile) {
		return _employeeFileLocalService.deleteEmployeeFile(employeeFile);
	}

	@Override
	public org.mobilink.backend.usermgt.model.EmployeeFile fetchEmployeeFile(
		long employeeFileId) {
		return _employeeFileLocalService.fetchEmployeeFile(employeeFileId);
	}

	/**
	* Returns the employee file matching the UUID and group.
	*
	* @param uuid the employee file's UUID
	* @param groupId the primary key of the group
	* @return the matching employee file, or <code>null</code> if a matching employee file could not be found
	*/
	@Override
	public org.mobilink.backend.usermgt.model.EmployeeFile fetchEmployeeFileByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _employeeFileLocalService.fetchEmployeeFileByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the employee file with the primary key.
	*
	* @param employeeFileId the primary key of the employee file
	* @return the employee file
	* @throws PortalException if a employee file with the primary key could not be found
	*/
	@Override
	public org.mobilink.backend.usermgt.model.EmployeeFile getEmployeeFile(
		long employeeFileId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _employeeFileLocalService.getEmployeeFile(employeeFileId);
	}

	/**
	* Returns the employee file matching the UUID and group.
	*
	* @param uuid the employee file's UUID
	* @param groupId the primary key of the group
	* @return the matching employee file
	* @throws PortalException if a matching employee file could not be found
	*/
	@Override
	public org.mobilink.backend.usermgt.model.EmployeeFile getEmployeeFileByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _employeeFileLocalService.getEmployeeFileByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Updates the employee file in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param employeeFile the employee file
	* @return the employee file that was updated
	*/
	@Override
	public org.mobilink.backend.usermgt.model.EmployeeFile updateEmployeeFile(
		org.mobilink.backend.usermgt.model.EmployeeFile employeeFile) {
		return _employeeFileLocalService.updateEmployeeFile(employeeFile);
	}

	@Override
	public EmployeeFileLocalService getWrappedService() {
		return _employeeFileLocalService;
	}

	@Override
	public void setWrappedService(
		EmployeeFileLocalService employeeFileLocalService) {
		_employeeFileLocalService = employeeFileLocalService;
	}

	private EmployeeFileLocalService _employeeFileLocalService;
}