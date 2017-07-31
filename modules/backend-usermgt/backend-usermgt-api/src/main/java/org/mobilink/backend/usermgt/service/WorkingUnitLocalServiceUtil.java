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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for WorkingUnit. This utility wraps
 * {@link org.mobilink.backend.usermgt.service.impl.WorkingUnitLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Binhth
 * @see WorkingUnitLocalService
 * @see org.mobilink.backend.usermgt.service.base.WorkingUnitLocalServiceBaseImpl
 * @see org.mobilink.backend.usermgt.service.impl.WorkingUnitLocalServiceImpl
 * @generated
 */
@ProviderType
public class WorkingUnitLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.mobilink.backend.usermgt.service.impl.WorkingUnitLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
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
	* Returns the number of working units.
	*
	* @return the number of working units
	*/
	public static int getWorkingUnitsCount() {
		return getService().getWorkingUnitsCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.usermgt.model.impl.WorkingUnitModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.usermgt.model.impl.WorkingUnitModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the working units.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.usermgt.model.impl.WorkingUnitModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of working units
	* @param end the upper bound of the range of working units (not inclusive)
	* @return the range of working units
	*/
	public static java.util.List<org.mobilink.backend.usermgt.model.WorkingUnit> getWorkingUnits(
		int start, int end) {
		return getService().getWorkingUnits(start, end);
	}

	/**
	* Returns all the working units matching the UUID and company.
	*
	* @param uuid the UUID of the working units
	* @param companyId the primary key of the company
	* @return the matching working units, or an empty list if no matches were found
	*/
	public static java.util.List<org.mobilink.backend.usermgt.model.WorkingUnit> getWorkingUnitsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getWorkingUnitsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of working units matching the UUID and company.
	*
	* @param uuid the UUID of the working units
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of working units
	* @param end the upper bound of the range of working units (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching working units, or an empty list if no matches were found
	*/
	public static java.util.List<org.mobilink.backend.usermgt.model.WorkingUnit> getWorkingUnitsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.mobilink.backend.usermgt.model.WorkingUnit> orderByComparator) {
		return getService()
				   .getWorkingUnitsByUuidAndCompanyId(uuid, companyId, start,
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

	public static org.mobilink.backend.usermgt.model.WorkingUnit addWorkingUnit(
		long userId, long groupId, java.lang.String name,
		java.lang.String enName, java.lang.String govAgencyCode,
		long parentWorkingUnitId, java.lang.String sibling,
		java.lang.String address, java.lang.String cityCode,
		java.lang.String districtCode, java.lang.String wardCode,
		java.lang.String telNo, java.lang.String faxNo, java.lang.String email,
		java.lang.String website, boolean isEmployer,
		long managerWorkingUnitId, long siteTemplateId,
		long employeeTemplateId, long partnerTemplateId, long adminUserId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws java.lang.Exception {
		return getService()
				   .addWorkingUnit(userId, groupId, name, enName,
			govAgencyCode, parentWorkingUnitId, sibling, address, cityCode,
			districtCode, wardCode, telNo, faxNo, email, website, isEmployer,
			managerWorkingUnitId, siteTemplateId, employeeTemplateId,
			partnerTemplateId, adminUserId, serviceContext);
	}

	/**
	* Adds the working unit to the database. Also notifies the appropriate model listeners.
	*
	* @param workingUnit the working unit
	* @return the working unit that was added
	*/
	public static org.mobilink.backend.usermgt.model.WorkingUnit addWorkingUnit(
		org.mobilink.backend.usermgt.model.WorkingUnit workingUnit) {
		return getService().addWorkingUnit(workingUnit);
	}

	/**
	* Creates a new working unit with the primary key. Does not add the working unit to the database.
	*
	* @param workingUnitId the primary key for the new working unit
	* @return the new working unit
	*/
	public static org.mobilink.backend.usermgt.model.WorkingUnit createWorkingUnit(
		long workingUnitId) {
		return getService().createWorkingUnit(workingUnitId);
	}

	/**
	* Deletes the working unit with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param workingUnitId the primary key of the working unit
	* @return the working unit that was removed
	* @throws PortalException if a working unit with the primary key could not be found
	*/
	public static org.mobilink.backend.usermgt.model.WorkingUnit deleteWorkingUnit(
		long workingUnitId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteWorkingUnit(workingUnitId);
	}

	public static org.mobilink.backend.usermgt.model.WorkingUnit deleteWorkingUnit(
		long workingUnitId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws java.lang.Exception {
		return getService().deleteWorkingUnit(workingUnitId, serviceContext);
	}

	/**
	* Deletes the working unit from the database. Also notifies the appropriate model listeners.
	*
	* @param workingUnit the working unit
	* @return the working unit that was removed
	*/
	public static org.mobilink.backend.usermgt.model.WorkingUnit deleteWorkingUnit(
		org.mobilink.backend.usermgt.model.WorkingUnit workingUnit) {
		return getService().deleteWorkingUnit(workingUnit);
	}

	public static org.mobilink.backend.usermgt.model.WorkingUnit fetchWorkingUnit(
		long workingUnitId) {
		return getService().fetchWorkingUnit(workingUnitId);
	}

	/**
	* Returns the working unit matching the UUID and group.
	*
	* @param uuid the working unit's UUID
	* @param groupId the primary key of the group
	* @return the matching working unit, or <code>null</code> if a matching working unit could not be found
	*/
	public static org.mobilink.backend.usermgt.model.WorkingUnit fetchWorkingUnitByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchWorkingUnitByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the working unit with the primary key.
	*
	* @param workingUnitId the primary key of the working unit
	* @return the working unit
	* @throws PortalException if a working unit with the primary key could not be found
	*/
	public static org.mobilink.backend.usermgt.model.WorkingUnit getWorkingUnit(
		long workingUnitId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getWorkingUnit(workingUnitId);
	}

	/**
	* Returns the working unit matching the UUID and group.
	*
	* @param uuid the working unit's UUID
	* @param groupId the primary key of the group
	* @return the matching working unit
	* @throws PortalException if a matching working unit could not be found
	*/
	public static org.mobilink.backend.usermgt.model.WorkingUnit getWorkingUnitByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getWorkingUnitByUuidAndGroupId(uuid, groupId);
	}

	public static org.mobilink.backend.usermgt.model.WorkingUnit updateWorkingUnit(
		long userId, long workingUnitId, java.lang.String name,
		java.lang.String enName, java.lang.String govAgencyCode,
		long parentWorkingUnitId, java.lang.String sibling,
		java.lang.String address, java.lang.String cityCode,
		java.lang.String districtCode, java.lang.String wardCode,
		java.lang.String telNo, java.lang.String faxNo, java.lang.String email,
		java.lang.String website, boolean isEmployer,
		long managerWorkingUnitId, long siteTemplateId,
		long employeeTemplateId, long partnerTemplateId, long adminUserId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateWorkingUnit(userId, workingUnitId, name, enName,
			govAgencyCode, parentWorkingUnitId, sibling, address, cityCode,
			districtCode, wardCode, telNo, faxNo, email, website, isEmployer,
			managerWorkingUnitId, siteTemplateId, employeeTemplateId,
			partnerTemplateId, adminUserId, serviceContext);
	}

	/**
	* Updates the working unit in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param workingUnit the working unit
	* @return the working unit that was updated
	*/
	public static org.mobilink.backend.usermgt.model.WorkingUnit updateWorkingUnit(
		org.mobilink.backend.usermgt.model.WorkingUnit workingUnit) {
		return getService().updateWorkingUnit(workingUnit);
	}

	public static WorkingUnitLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<WorkingUnitLocalService, WorkingUnitLocalService> _serviceTracker =
		ServiceTrackerFactory.open(WorkingUnitLocalService.class);
}