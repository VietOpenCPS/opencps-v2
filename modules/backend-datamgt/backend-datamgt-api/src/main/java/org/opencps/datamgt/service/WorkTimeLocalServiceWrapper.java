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

package org.opencps.datamgt.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link WorkTimeLocalService}.
 *
 * @author khoavu
 * @see WorkTimeLocalService
 * @generated
 */
@ProviderType
public class WorkTimeLocalServiceWrapper implements WorkTimeLocalService,
	ServiceWrapper<WorkTimeLocalService> {
	public WorkTimeLocalServiceWrapper(
		WorkTimeLocalService workTimeLocalService) {
		_workTimeLocalService = workTimeLocalService;
	}

	@Override
	public org.opencps.datamgt.model.WorkTime addWorkTime(long userId,
		long groupId, int day, String hours,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			com.liferay.portal.kernel.exception.NoSuchUserException {
		return _workTimeLocalService.addWorkTime(userId, groupId, day, hours,
			serviceContext);
	}

	/**
	* Adds the work time to the database. Also notifies the appropriate model listeners.
	*
	* @param workTime the work time
	* @return the work time that was added
	*/
	@Override
	public org.opencps.datamgt.model.WorkTime addWorkTime(
		org.opencps.datamgt.model.WorkTime workTime) {
		return _workTimeLocalService.addWorkTime(workTime);
	}

	@Override
	public org.opencps.datamgt.model.WorkTime adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return _workTimeLocalService.adminProcessData(objectData);
	}

	@Override
	public org.opencps.datamgt.model.WorkTime adminProcessDelete(Long id) {
		return _workTimeLocalService.adminProcessDelete(id);
	}

	@Override
	public long countLuceneSearchEngine(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _workTimeLocalService.countLuceneSearchEngine(params,
			searchContext);
	}

	/**
	* Creates a new work time with the primary key. Does not add the work time to the database.
	*
	* @param workTimeId the primary key for the new work time
	* @return the new work time
	*/
	@Override
	public org.opencps.datamgt.model.WorkTime createWorkTime(long workTimeId) {
		return _workTimeLocalService.createWorkTime(workTimeId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workTimeLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the work time with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param workTimeId the primary key of the work time
	* @return the work time that was removed
	* @throws PortalException if a work time with the primary key could not be found
	*/
	@Override
	public org.opencps.datamgt.model.WorkTime deleteWorkTime(long workTimeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workTimeLocalService.deleteWorkTime(workTimeId);
	}

	/**
	* @param dictCollectionId
	* @param serviceContext
	* @return
	* @throws Exception
	*/
	@Override
	public org.opencps.datamgt.model.WorkTime deleteWorkTime(long workTimeId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			org.opencps.auth.api.exception.NotFoundException {
		return _workTimeLocalService.deleteWorkTime(workTimeId, serviceContext);
	}

	/**
	* Deletes the work time from the database. Also notifies the appropriate model listeners.
	*
	* @param workTime the work time
	* @return the work time that was removed
	*/
	@Override
	public org.opencps.datamgt.model.WorkTime deleteWorkTime(
		org.opencps.datamgt.model.WorkTime workTime) {
		return _workTimeLocalService.deleteWorkTime(workTime);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _workTimeLocalService.dynamicQuery();
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
		return _workTimeLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.WorkTimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _workTimeLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.WorkTimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _workTimeLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _workTimeLocalService.dynamicQueryCount(dynamicQuery);
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
		return _workTimeLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public org.opencps.datamgt.model.WorkTime fetchByF_day(long groupId, int day) {
		return _workTimeLocalService.fetchByF_day(groupId, day);
	}

	@Override
	public org.opencps.datamgt.model.WorkTime fetchWorkTime(long workTimeId) {
		return _workTimeLocalService.fetchWorkTime(workTimeId);
	}

	/**
	* Returns the work time matching the UUID and group.
	*
	* @param uuid the work time's UUID
	* @param groupId the primary key of the group
	* @return the matching work time, or <code>null</code> if a matching work time could not be found
	*/
	@Override
	public org.opencps.datamgt.model.WorkTime fetchWorkTimeByUuidAndGroupId(
		String uuid, long groupId) {
		return _workTimeLocalService.fetchWorkTimeByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _workTimeLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<org.opencps.datamgt.model.WorkTime> getByGroupId(
		long groupId) {
		return _workTimeLocalService.getByGroupId(groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _workTimeLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _workTimeLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _workTimeLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workTimeLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the work time with the primary key.
	*
	* @param workTimeId the primary key of the work time
	* @return the work time
	* @throws PortalException if a work time with the primary key could not be found
	*/
	@Override
	public org.opencps.datamgt.model.WorkTime getWorkTime(long workTimeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workTimeLocalService.getWorkTime(workTimeId);
	}

	/**
	* Returns the work time matching the UUID and group.
	*
	* @param uuid the work time's UUID
	* @param groupId the primary key of the group
	* @return the matching work time
	* @throws PortalException if a matching work time could not be found
	*/
	@Override
	public org.opencps.datamgt.model.WorkTime getWorkTimeByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _workTimeLocalService.getWorkTimeByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the work times.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.WorkTimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of work times
	* @param end the upper bound of the range of work times (not inclusive)
	* @return the range of work times
	*/
	@Override
	public java.util.List<org.opencps.datamgt.model.WorkTime> getWorkTimes(
		int start, int end) {
		return _workTimeLocalService.getWorkTimes(start, end);
	}

	/**
	* Returns all the work times matching the UUID and company.
	*
	* @param uuid the UUID of the work times
	* @param companyId the primary key of the company
	* @return the matching work times, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.datamgt.model.WorkTime> getWorkTimesByUuidAndCompanyId(
		String uuid, long companyId) {
		return _workTimeLocalService.getWorkTimesByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of work times matching the UUID and company.
	*
	* @param uuid the UUID of the work times
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of work times
	* @param end the upper bound of the range of work times (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching work times, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.datamgt.model.WorkTime> getWorkTimesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.datamgt.model.WorkTime> orderByComparator) {
		return _workTimeLocalService.getWorkTimesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of work times.
	*
	* @return the number of work times
	*/
	@Override
	public int getWorkTimesCount() {
		return _workTimeLocalService.getWorkTimesCount();
	}

	@Override
	public com.liferay.portal.kernel.search.Hits luceneSearchEngine(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _workTimeLocalService.luceneSearchEngine(params, sorts, start,
			end, searchContext);
	}

	@Override
	public org.opencps.datamgt.model.WorkTime updateWorkTime(long userId,
		long workTimeId, int day, String hours,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			org.opencps.auth.api.exception.NotFoundException,
			com.liferay.portal.kernel.exception.NoSuchUserException {
		return _workTimeLocalService.updateWorkTime(userId, workTimeId, day,
			hours, serviceContext);
	}

	/**
	* Updates the work time in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param workTime the work time
	* @return the work time that was updated
	*/
	@Override
	public org.opencps.datamgt.model.WorkTime updateWorkTime(
		org.opencps.datamgt.model.WorkTime workTime) {
		return _workTimeLocalService.updateWorkTime(workTime);
	}

	@Override
	public WorkTimeLocalService getWrappedService() {
		return _workTimeLocalService;
	}

	@Override
	public void setWrappedService(WorkTimeLocalService workTimeLocalService) {
		_workTimeLocalService = workTimeLocalService;
	}

	private WorkTimeLocalService _workTimeLocalService;
}