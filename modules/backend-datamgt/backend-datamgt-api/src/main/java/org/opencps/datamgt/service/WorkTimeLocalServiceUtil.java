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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for WorkTime. This utility wraps
 * {@link org.opencps.datamgt.service.impl.WorkTimeLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author khoavu
 * @see WorkTimeLocalService
 * @see org.opencps.datamgt.service.base.WorkTimeLocalServiceBaseImpl
 * @see org.opencps.datamgt.service.impl.WorkTimeLocalServiceImpl
 * @generated
 */
@ProviderType
public class WorkTimeLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.datamgt.service.impl.WorkTimeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.opencps.datamgt.model.WorkTime addWorkTime(long userId,
		long groupId, int day, String hours,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			com.liferay.portal.kernel.exception.NoSuchUserException {
		return getService()
				   .addWorkTime(userId, groupId, day, hours, serviceContext);
	}

	/**
	* Adds the work time to the database. Also notifies the appropriate model listeners.
	*
	* @param workTime the work time
	* @return the work time that was added
	*/
	public static org.opencps.datamgt.model.WorkTime addWorkTime(
		org.opencps.datamgt.model.WorkTime workTime) {
		return getService().addWorkTime(workTime);
	}

	public static long countLuceneSearchEngine(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return getService().countLuceneSearchEngine(params, searchContext);
	}

	/**
	* Creates a new work time with the primary key. Does not add the work time to the database.
	*
	* @param workTimeId the primary key for the new work time
	* @return the new work time
	*/
	public static org.opencps.datamgt.model.WorkTime createWorkTime(
		long workTimeId) {
		return getService().createWorkTime(workTimeId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the work time with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param workTimeId the primary key of the work time
	* @return the work time that was removed
	* @throws PortalException if a work time with the primary key could not be found
	*/
	public static org.opencps.datamgt.model.WorkTime deleteWorkTime(
		long workTimeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteWorkTime(workTimeId);
	}

	/**
	* @param dictCollectionId
	* @param serviceContext
	* @return
	* @throws Exception
	*/
	public static org.opencps.datamgt.model.WorkTime deleteWorkTime(
		long workTimeId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			org.opencps.auth.api.exception.NotFoundException {
		return getService().deleteWorkTime(workTimeId, serviceContext);
	}

	/**
	* Deletes the work time from the database. Also notifies the appropriate model listeners.
	*
	* @param workTime the work time
	* @return the work time that was removed
	*/
	public static org.opencps.datamgt.model.WorkTime deleteWorkTime(
		org.opencps.datamgt.model.WorkTime workTime) {
		return getService().deleteWorkTime(workTime);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.WorkTimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.WorkTimeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.datamgt.model.WorkTime fetchByF_day(
		long groupId, int day) {
		return getService().fetchByF_day(groupId, day);
	}

	public static org.opencps.datamgt.model.WorkTime fetchWorkTime(
		long workTimeId) {
		return getService().fetchWorkTime(workTimeId);
	}

	/**
	* Returns the work time matching the UUID and group.
	*
	* @param uuid the work time's UUID
	* @param groupId the primary key of the group
	* @return the matching work time, or <code>null</code> if a matching work time could not be found
	*/
	public static org.opencps.datamgt.model.WorkTime fetchWorkTimeByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchWorkTimeByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static java.util.List<org.opencps.datamgt.model.WorkTime> getByGroupId(
		long groupId) {
		return getService().getByGroupId(groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the work time with the primary key.
	*
	* @param workTimeId the primary key of the work time
	* @return the work time
	* @throws PortalException if a work time with the primary key could not be found
	*/
	public static org.opencps.datamgt.model.WorkTime getWorkTime(
		long workTimeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getWorkTime(workTimeId);
	}

	/**
	* Returns the work time matching the UUID and group.
	*
	* @param uuid the work time's UUID
	* @param groupId the primary key of the group
	* @return the matching work time
	* @throws PortalException if a matching work time could not be found
	*/
	public static org.opencps.datamgt.model.WorkTime getWorkTimeByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getWorkTimeByUuidAndGroupId(uuid, groupId);
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
	public static java.util.List<org.opencps.datamgt.model.WorkTime> getWorkTimes(
		int start, int end) {
		return getService().getWorkTimes(start, end);
	}

	/**
	* Returns all the work times matching the UUID and company.
	*
	* @param uuid the UUID of the work times
	* @param companyId the primary key of the company
	* @return the matching work times, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.datamgt.model.WorkTime> getWorkTimesByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getWorkTimesByUuidAndCompanyId(uuid, companyId);
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
	public static java.util.List<org.opencps.datamgt.model.WorkTime> getWorkTimesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.datamgt.model.WorkTime> orderByComparator) {
		return getService()
				   .getWorkTimesByUuidAndCompanyId(uuid, companyId, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of work times.
	*
	* @return the number of work times
	*/
	public static int getWorkTimesCount() {
		return getService().getWorkTimesCount();
	}

	public static com.liferay.portal.kernel.search.Hits luceneSearchEngine(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return getService()
				   .luceneSearchEngine(params, sorts, start, end, searchContext);
	}

	public static org.opencps.datamgt.model.WorkTime updateWorkTime(
		long userId, long workTimeId, int day, String hours,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			org.opencps.auth.api.exception.NotFoundException,
			com.liferay.portal.kernel.exception.NoSuchUserException {
		return getService()
				   .updateWorkTime(userId, workTimeId, day, hours,
			serviceContext);
	}

	/**
	* Updates the work time in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param workTime the work time
	* @return the work time that was updated
	*/
	public static org.opencps.datamgt.model.WorkTime updateWorkTime(
		org.opencps.datamgt.model.WorkTime workTime) {
		return getService().updateWorkTime(workTime);
	}

	public static WorkTimeLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<WorkTimeLocalService, WorkTimeLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(WorkTimeLocalService.class);

		ServiceTracker<WorkTimeLocalService, WorkTimeLocalService> serviceTracker =
			new ServiceTracker<WorkTimeLocalService, WorkTimeLocalService>(bundle.getBundleContext(),
				WorkTimeLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}