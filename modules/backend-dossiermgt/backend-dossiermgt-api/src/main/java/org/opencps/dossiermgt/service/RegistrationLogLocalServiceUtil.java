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

package org.opencps.dossiermgt.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for RegistrationLog. This utility wraps
 * {@link org.opencps.dossiermgt.service.impl.RegistrationLogLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author huymq
 * @see RegistrationLogLocalService
 * @see org.opencps.dossiermgt.service.base.RegistrationLogLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.RegistrationLogLocalServiceImpl
 * @generated
 */
@ProviderType
public class RegistrationLogLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.RegistrationLogLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.opencps.dossiermgt.model.RegistrationLog addLog(
		String author, long groupId, long userId, long registrationId,
		String content, String payload) {
		return getService()
				   .addLog(author, groupId, userId, registrationId, content,
			payload);
	}

	/**
	* Adds the registration log to the database. Also notifies the appropriate model listeners.
	*
	* @param registrationLog the registration log
	* @return the registration log that was added
	*/
	public static org.opencps.dossiermgt.model.RegistrationLog addRegistrationLog(
		org.opencps.dossiermgt.model.RegistrationLog registrationLog) {
		return getService().addRegistrationLog(registrationLog);
	}

	public static long countLucense(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return getService()
				   .countLucense(params, sorts, start, end, searchContext);
	}

	/**
	* Creates a new registration log with the primary key. Does not add the registration log to the database.
	*
	* @param registrationLogId the primary key for the new registration log
	* @return the new registration log
	*/
	public static org.opencps.dossiermgt.model.RegistrationLog createRegistrationLog(
		long registrationLogId) {
		return getService().createRegistrationLog(registrationLogId);
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
	* Deletes the registration log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param registrationLogId the primary key of the registration log
	* @return the registration log that was removed
	* @throws PortalException if a registration log with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.RegistrationLog deleteRegistrationLog(
		long registrationLogId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteRegistrationLog(registrationLogId);
	}

	/**
	* Deletes the registration log from the database. Also notifies the appropriate model listeners.
	*
	* @param registrationLog the registration log
	* @return the registration log that was removed
	*/
	public static org.opencps.dossiermgt.model.RegistrationLog deleteRegistrationLog(
		org.opencps.dossiermgt.model.RegistrationLog registrationLog) {
		return getService().deleteRegistrationLog(registrationLog);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.RegistrationLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.RegistrationLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.dossiermgt.model.RegistrationLog fetchRegistrationLog(
		long registrationLogId) {
		return getService().fetchRegistrationLog(registrationLogId);
	}

	/**
	* Returns the registration log matching the UUID and group.
	*
	* @param uuid the registration log's UUID
	* @param groupId the primary key of the group
	* @return the matching registration log, or <code>null</code> if a matching registration log could not be found
	*/
	public static org.opencps.dossiermgt.model.RegistrationLog fetchRegistrationLogByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchRegistrationLogByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
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
	* Returns the registration log with the primary key.
	*
	* @param registrationLogId the primary key of the registration log
	* @return the registration log
	* @throws PortalException if a registration log with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.RegistrationLog getRegistrationLog(
		long registrationLogId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getRegistrationLog(registrationLogId);
	}

	public static java.util.List<org.opencps.dossiermgt.model.RegistrationLog> getRegistrationLogbyRegId(
		long groupId, long registrationId) {
		return getService().getRegistrationLogbyRegId(groupId, registrationId);
	}

	/**
	* Returns the registration log matching the UUID and group.
	*
	* @param uuid the registration log's UUID
	* @param groupId the primary key of the group
	* @return the matching registration log
	* @throws PortalException if a matching registration log could not be found
	*/
	public static org.opencps.dossiermgt.model.RegistrationLog getRegistrationLogByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getRegistrationLogByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the registration logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.RegistrationLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of registration logs
	* @param end the upper bound of the range of registration logs (not inclusive)
	* @return the range of registration logs
	*/
	public static java.util.List<org.opencps.dossiermgt.model.RegistrationLog> getRegistrationLogs(
		int start, int end) {
		return getService().getRegistrationLogs(start, end);
	}

	/**
	* Returns all the registration logs matching the UUID and company.
	*
	* @param uuid the UUID of the registration logs
	* @param companyId the primary key of the company
	* @return the matching registration logs, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.dossiermgt.model.RegistrationLog> getRegistrationLogsByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService()
				   .getRegistrationLogsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of registration logs matching the UUID and company.
	*
	* @param uuid the UUID of the registration logs
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of registration logs
	* @param end the upper bound of the range of registration logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching registration logs, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.dossiermgt.model.RegistrationLog> getRegistrationLogsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.RegistrationLog> orderByComparator) {
		return getService()
				   .getRegistrationLogsByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of registration logs.
	*
	* @return the number of registration logs
	*/
	public static int getRegistrationLogsCount() {
		return getService().getRegistrationLogsCount();
	}

	public static com.liferay.portal.kernel.search.Hits searchLucene(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return getService()
				   .searchLucene(params, sorts, start, end, searchContext);
	}

	/**
	* Updates the registration log in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param registrationLog the registration log
	* @return the registration log that was updated
	*/
	public static org.opencps.dossiermgt.model.RegistrationLog updateRegistrationLog(
		org.opencps.dossiermgt.model.RegistrationLog registrationLog) {
		return getService().updateRegistrationLog(registrationLog);
	}

	public static RegistrationLogLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<RegistrationLogLocalService, RegistrationLogLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(RegistrationLogLocalService.class);

		ServiceTracker<RegistrationLogLocalService, RegistrationLogLocalService> serviceTracker =
			new ServiceTracker<RegistrationLogLocalService, RegistrationLogLocalService>(bundle.getBundleContext(),
				RegistrationLogLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}