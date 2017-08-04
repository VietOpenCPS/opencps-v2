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
 * Provides the local service utility for EmployeeFile. This utility wraps
 * {@link org.mobilink.backend.usermgt.service.impl.EmployeeFileLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Binhth
 * @see EmployeeFileLocalService
 * @see org.mobilink.backend.usermgt.service.base.EmployeeFileLocalServiceBaseImpl
 * @see org.mobilink.backend.usermgt.service.impl.EmployeeFileLocalServiceImpl
 * @generated
 */
@ProviderType
public class EmployeeFileLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.mobilink.backend.usermgt.service.impl.EmployeeFileLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
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
	* Returns the number of employee files.
	*
	* @return the number of employee files
	*/
	public static int getEmployeeFilesCount() {
		return getService().getEmployeeFilesCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.usermgt.model.impl.EmployeeFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.usermgt.model.impl.EmployeeFileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public static java.util.List<org.opencps.usermgt.model.EmployeeFile> getEmployeeFiles(
		int start, int end) {
		return getService().getEmployeeFiles(start, end);
	}

	/**
	* Returns all the employee files matching the UUID and company.
	*
	* @param uuid the UUID of the employee files
	* @param companyId the primary key of the company
	* @return the matching employee files, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.usermgt.model.EmployeeFile> getEmployeeFilesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getEmployeeFilesByUuidAndCompanyId(uuid, companyId);
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
	public static java.util.List<org.opencps.usermgt.model.EmployeeFile> getEmployeeFilesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.usermgt.model.EmployeeFile> orderByComparator) {
		return getService()
				   .getEmployeeFilesByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
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

	public static org.opencps.usermgt.model.EmployeeFile addEmployeeFile(
		long userId, long groupId, long employeeId, java.io.File file,
		java.lang.String documentName, java.lang.String title,
		java.lang.String mimeType,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addEmployeeFile(userId, groupId, employeeId, file,
			documentName, title, mimeType, serviceContext);
	}

	/**
	* Adds the employee file to the database. Also notifies the appropriate model listeners.
	*
	* @param employeeFile the employee file
	* @return the employee file that was added
	*/
	public static org.opencps.usermgt.model.EmployeeFile addEmployeeFile(
		org.opencps.usermgt.model.EmployeeFile employeeFile) {
		return getService().addEmployeeFile(employeeFile);
	}

	/**
	* Creates a new employee file with the primary key. Does not add the employee file to the database.
	*
	* @param employeeFileId the primary key for the new employee file
	* @return the new employee file
	*/
	public static org.opencps.usermgt.model.EmployeeFile createEmployeeFile(
		long employeeFileId) {
		return getService().createEmployeeFile(employeeFileId);
	}

	/**
	* Deletes the employee file with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param employeeFileId the primary key of the employee file
	* @return the employee file that was removed
	* @throws PortalException if a employee file with the primary key could not be found
	*/
	public static org.opencps.usermgt.model.EmployeeFile deleteEmployeeFile(
		long employeeFileId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteEmployeeFile(employeeFileId);
	}

	public static org.opencps.usermgt.model.EmployeeFile deleteEmployeeFile(
		long employeeFileId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws java.lang.Exception {
		return getService().deleteEmployeeFile(employeeFileId, serviceContext);
	}

	/**
	* Deletes the employee file from the database. Also notifies the appropriate model listeners.
	*
	* @param employeeFile the employee file
	* @return the employee file that was removed
	*/
	public static org.opencps.usermgt.model.EmployeeFile deleteEmployeeFile(
		org.opencps.usermgt.model.EmployeeFile employeeFile) {
		return getService().deleteEmployeeFile(employeeFile);
	}

	public static org.opencps.usermgt.model.EmployeeFile fetchEmployeeFile(
		long employeeFileId) {
		return getService().fetchEmployeeFile(employeeFileId);
	}

	/**
	* Returns the employee file matching the UUID and group.
	*
	* @param uuid the employee file's UUID
	* @param groupId the primary key of the group
	* @return the matching employee file, or <code>null</code> if a matching employee file could not be found
	*/
	public static org.opencps.usermgt.model.EmployeeFile fetchEmployeeFileByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchEmployeeFileByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the employee file with the primary key.
	*
	* @param employeeFileId the primary key of the employee file
	* @return the employee file
	* @throws PortalException if a employee file with the primary key could not be found
	*/
	public static org.opencps.usermgt.model.EmployeeFile getEmployeeFile(
		long employeeFileId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getEmployeeFile(employeeFileId);
	}

	/**
	* Returns the employee file matching the UUID and group.
	*
	* @param uuid the employee file's UUID
	* @param groupId the primary key of the group
	* @return the matching employee file
	* @throws PortalException if a matching employee file could not be found
	*/
	public static org.opencps.usermgt.model.EmployeeFile getEmployeeFileByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getEmployeeFileByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the employee file in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param employeeFile the employee file
	* @return the employee file that was updated
	*/
	public static org.opencps.usermgt.model.EmployeeFile updateEmployeeFile(
		org.opencps.usermgt.model.EmployeeFile employeeFile) {
		return getService().updateEmployeeFile(employeeFile);
	}

	public static EmployeeFileLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<EmployeeFileLocalService, EmployeeFileLocalService> _serviceTracker =
		ServiceTrackerFactory.open(EmployeeFileLocalService.class);
}