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
 * Provides a wrapper for {@link WorkingUnitLocalService}.
 *
 * @author Binhth
 * @see WorkingUnitLocalService
 * @generated
 */
@ProviderType
public class WorkingUnitLocalServiceWrapper implements WorkingUnitLocalService,
	ServiceWrapper<WorkingUnitLocalService> {
	public WorkingUnitLocalServiceWrapper(
		WorkingUnitLocalService workingUnitLocalService) {
		_workingUnitLocalService = workingUnitLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _workingUnitLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _workingUnitLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _workingUnitLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _workingUnitLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workingUnitLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workingUnitLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits luceneSearchEngine(
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _workingUnitLocalService.luceneSearchEngine(params, sorts,
			start, end, searchContext);
	}

	/**
	* Returns the number of working units.
	*
	* @return the number of working units
	*/
	@Override
	public int getWorkingUnitsCount() {
		return _workingUnitLocalService.getWorkingUnitsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _workingUnitLocalService.getOSGiServiceIdentifier();
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
		return _workingUnitLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _workingUnitLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _workingUnitLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
	@Override
	public java.util.List<org.opencps.usermgt.model.WorkingUnit> getWorkingUnits(
		int start, int end) {
		return _workingUnitLocalService.getWorkingUnits(start, end);
	}

	/**
	* Returns all the working units matching the UUID and company.
	*
	* @param uuid the UUID of the working units
	* @param companyId the primary key of the company
	* @return the matching working units, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.usermgt.model.WorkingUnit> getWorkingUnitsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _workingUnitLocalService.getWorkingUnitsByUuidAndCompanyId(uuid,
			companyId);
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
	@Override
	public java.util.List<org.opencps.usermgt.model.WorkingUnit> getWorkingUnitsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.usermgt.model.WorkingUnit> orderByComparator) {
		return _workingUnitLocalService.getWorkingUnitsByUuidAndCompanyId(uuid,
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
		return _workingUnitLocalService.dynamicQueryCount(dynamicQuery);
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
		return _workingUnitLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.usermgt.model.WorkingUnit addWorkingUnit(
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
		return _workingUnitLocalService.addWorkingUnit(userId, groupId, name,
			enName, govAgencyCode, parentWorkingUnitId, sibling, address,
			cityCode, districtCode, wardCode, telNo, faxNo, email, website,
			isEmployer, managerWorkingUnitId, siteTemplateId,
			employeeTemplateId, partnerTemplateId, adminUserId, serviceContext);
	}

	/**
	* Adds the working unit to the database. Also notifies the appropriate model listeners.
	*
	* @param workingUnit the working unit
	* @return the working unit that was added
	*/
	@Override
	public org.opencps.usermgt.model.WorkingUnit addWorkingUnit(
		org.opencps.usermgt.model.WorkingUnit workingUnit) {
		return _workingUnitLocalService.addWorkingUnit(workingUnit);
	}

	/**
	* Creates a new working unit with the primary key. Does not add the working unit to the database.
	*
	* @param workingUnitId the primary key for the new working unit
	* @return the new working unit
	*/
	@Override
	public org.opencps.usermgt.model.WorkingUnit createWorkingUnit(
		long workingUnitId) {
		return _workingUnitLocalService.createWorkingUnit(workingUnitId);
	}

	/**
	* Deletes the working unit with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param workingUnitId the primary key of the working unit
	* @return the working unit that was removed
	* @throws PortalException if a working unit with the primary key could not be found
	*/
	@Override
	public org.opencps.usermgt.model.WorkingUnit deleteWorkingUnit(
		long workingUnitId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workingUnitLocalService.deleteWorkingUnit(workingUnitId);
	}

	@Override
	public org.opencps.usermgt.model.WorkingUnit deleteWorkingUnit(
		long workingUnitId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws java.lang.Exception {
		return _workingUnitLocalService.deleteWorkingUnit(workingUnitId,
			serviceContext);
	}

	/**
	* Deletes the working unit from the database. Also notifies the appropriate model listeners.
	*
	* @param workingUnit the working unit
	* @return the working unit that was removed
	*/
	@Override
	public org.opencps.usermgt.model.WorkingUnit deleteWorkingUnit(
		org.opencps.usermgt.model.WorkingUnit workingUnit) {
		return _workingUnitLocalService.deleteWorkingUnit(workingUnit);
	}

	@Override
	public org.opencps.usermgt.model.WorkingUnit fetchWorkingUnit(
		long workingUnitId) {
		return _workingUnitLocalService.fetchWorkingUnit(workingUnitId);
	}

	/**
	* Returns the working unit matching the UUID and group.
	*
	* @param uuid the working unit's UUID
	* @param groupId the primary key of the group
	* @return the matching working unit, or <code>null</code> if a matching working unit could not be found
	*/
	@Override
	public org.opencps.usermgt.model.WorkingUnit fetchWorkingUnitByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _workingUnitLocalService.fetchWorkingUnitByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the working unit with the primary key.
	*
	* @param workingUnitId the primary key of the working unit
	* @return the working unit
	* @throws PortalException if a working unit with the primary key could not be found
	*/
	@Override
	public org.opencps.usermgt.model.WorkingUnit getWorkingUnit(
		long workingUnitId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workingUnitLocalService.getWorkingUnit(workingUnitId);
	}

	/**
	* Returns the working unit matching the UUID and group.
	*
	* @param uuid the working unit's UUID
	* @param groupId the primary key of the group
	* @return the matching working unit
	* @throws PortalException if a matching working unit could not be found
	*/
	@Override
	public org.opencps.usermgt.model.WorkingUnit getWorkingUnitByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workingUnitLocalService.getWorkingUnitByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public org.opencps.usermgt.model.WorkingUnit updateWorkingUnit(
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
		return _workingUnitLocalService.updateWorkingUnit(userId,
			workingUnitId, name, enName, govAgencyCode, parentWorkingUnitId,
			sibling, address, cityCode, districtCode, wardCode, telNo, faxNo,
			email, website, isEmployer, managerWorkingUnitId, siteTemplateId,
			employeeTemplateId, partnerTemplateId, adminUserId, serviceContext);
	}

	/**
	* Updates the working unit in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param workingUnit the working unit
	* @return the working unit that was updated
	*/
	@Override
	public org.opencps.usermgt.model.WorkingUnit updateWorkingUnit(
		org.opencps.usermgt.model.WorkingUnit workingUnit) {
		return _workingUnitLocalService.updateWorkingUnit(workingUnit);
	}

	@Override
	public WorkingUnitLocalService getWrappedService() {
		return _workingUnitLocalService;
	}

	@Override
	public void setWrappedService(
		WorkingUnitLocalService workingUnitLocalService) {
		_workingUnitLocalService = workingUnitLocalService;
	}

	private WorkingUnitLocalService _workingUnitLocalService;
}