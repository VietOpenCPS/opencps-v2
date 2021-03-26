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

package org.opencps.backend.systemlogmgt.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for SystemLog. This utility wraps
 * {@link org.opencps.backend.systemlogmgt.service.impl.SystemLogLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see SystemLogLocalService
 * @see org.opencps.backend.systemlogmgt.service.base.SystemLogLocalServiceBaseImpl
 * @see org.opencps.backend.systemlogmgt.service.impl.SystemLogLocalServiceImpl
 * @generated
 */
@ProviderType
public class SystemLogLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.backend.systemlogmgt.service.impl.SystemLogLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.opencps.backend.systemlogmgt.model.SystemLog addNewSystemLog(
		Long groupId, String moduleName, Integer preLine, String preMethod,
		Integer line, String method, String message, String type,
		String threadId) {
		return getService()
				   .addNewSystemLog(groupId, moduleName, preLine, preMethod,
			line, method, message, type, threadId);
	}

	/**
	* Adds the system log to the database. Also notifies the appropriate model listeners.
	*
	* @param systemLog the system log
	* @return the system log that was added
	*/
	public static org.opencps.backend.systemlogmgt.model.SystemLog addSystemLog(
		org.opencps.backend.systemlogmgt.model.SystemLog systemLog) {
		return getService().addSystemLog(systemLog);
	}

	/**
	* Creates a new system log with the primary key. Does not add the system log to the database.
	*
	* @param logId the primary key for the new system log
	* @return the new system log
	*/
	public static org.opencps.backend.systemlogmgt.model.SystemLog createSystemLog(
		long logId) {
		return getService().createSystemLog(logId);
	}

	public static org.opencps.backend.systemlogmgt.model.SystemLog deleteOldSystemLog(
		long logId) throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteOldSystemLog(logId);
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
	* Deletes the system log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param logId the primary key of the system log
	* @return the system log that was removed
	* @throws PortalException if a system log with the primary key could not be found
	*/
	public static org.opencps.backend.systemlogmgt.model.SystemLog deleteSystemLog(
		long logId) throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteSystemLog(logId);
	}

	/**
	* Deletes the system log from the database. Also notifies the appropriate model listeners.
	*
	* @param systemLog the system log
	* @return the system log that was removed
	*/
	public static org.opencps.backend.systemlogmgt.model.SystemLog deleteSystemLog(
		org.opencps.backend.systemlogmgt.model.SystemLog systemLog) {
		return getService().deleteSystemLog(systemLog);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.systemlogmgt.model.impl.SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.systemlogmgt.model.impl.SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.backend.systemlogmgt.model.SystemLog fetchSystemLog(
		long logId) {
		return getService().fetchSystemLog(logId);
	}

	/**
	* Returns the system log matching the UUID and group.
	*
	* @param uuid the system log's UUID
	* @param groupId the primary key of the group
	* @return the matching system log, or <code>null</code> if a matching system log could not be found
	*/
	public static org.opencps.backend.systemlogmgt.model.SystemLog fetchSystemLogByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchSystemLogByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
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
	* Returns the system log with the primary key.
	*
	* @param logId the primary key of the system log
	* @return the system log
	* @throws PortalException if a system log with the primary key could not be found
	*/
	public static org.opencps.backend.systemlogmgt.model.SystemLog getSystemLog(
		long logId) throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSystemLog(logId);
	}

	public static java.util.List<org.opencps.backend.systemlogmgt.model.SystemLog> getSystemLogByDynamicQuery(
		Long logId, String groupId, String moduleName, String createDate,
		Integer preLine, String preMethod, Integer line, String method,
		String message, String type, String threadId) {
		return getService()
				   .getSystemLogByDynamicQuery(logId, groupId, moduleName,
			createDate, preLine, preMethod, line, method, message, type,
			threadId);
	}

	public static java.util.List<org.opencps.backend.systemlogmgt.model.SystemLog> getSystemLogByLikeMessage(
		String message) {
		return getService().getSystemLogByLikeMessage(message);
	}

	public static org.opencps.backend.systemlogmgt.model.SystemLog getSystemLogByLogId(
		long logId) throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSystemLogByLogId(logId);
	}

	public static java.util.List<org.opencps.backend.systemlogmgt.model.SystemLog> getSystemLogByMultipleGroupId(
		long[] groupIds) {
		return getService().getSystemLogByMultipleGroupId(groupIds);
	}

	public static java.util.List<org.opencps.backend.systemlogmgt.model.SystemLog> getSystemLogByMultipleMethod(
		String[] methods) {
		return getService().getSystemLogByMultipleMethod(methods);
	}

	public static java.util.List<org.opencps.backend.systemlogmgt.model.SystemLog> getSystemLogByMultipleModuleName(
		String[] moduleNames) {
		return getService().getSystemLogByMultipleModuleName(moduleNames);
	}

	public static java.util.List<org.opencps.backend.systemlogmgt.model.SystemLog> getSystemLogByMultiplePreMethod(
		String[] preMethods) {
		return getService().getSystemLogByMultiplePreMethod(preMethods);
	}

	public static java.util.List<org.opencps.backend.systemlogmgt.model.SystemLog> getSystemLogByMultipleThreadId(
		String[] threadIds) {
		return getService().getSystemLogByMultipleThreadId(threadIds);
	}

	public static java.util.List<org.opencps.backend.systemlogmgt.model.SystemLog> getSystemLogByMultipleType(
		String[] types) {
		return getService().getSystemLogByMultipleType(types);
	}

	/**
	* Returns the system log matching the UUID and group.
	*
	* @param uuid the system log's UUID
	* @param groupId the primary key of the group
	* @return the matching system log
	* @throws PortalException if a matching system log could not be found
	*/
	public static org.opencps.backend.systemlogmgt.model.SystemLog getSystemLogByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSystemLogByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the system logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.systemlogmgt.model.impl.SystemLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of system logs
	* @param end the upper bound of the range of system logs (not inclusive)
	* @return the range of system logs
	*/
	public static java.util.List<org.opencps.backend.systemlogmgt.model.SystemLog> getSystemLogs(
		int start, int end) {
		return getService().getSystemLogs(start, end);
	}

	/**
	* Returns the number of system logs.
	*
	* @return the number of system logs
	*/
	public static int getSystemLogsCount() {
		return getService().getSystemLogsCount();
	}

	public static org.opencps.backend.systemlogmgt.model.SystemLog updateOldSystemLog(
		Long logId, Long groupId, String moduleName, Integer preLine,
		String preMethod, Integer line, String method, String message,
		String type, String threadId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateOldSystemLog(logId, groupId, moduleName, preLine,
			preMethod, line, method, message, type, threadId);
	}

	/**
	* Updates the system log in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param systemLog the system log
	* @return the system log that was updated
	*/
	public static org.opencps.backend.systemlogmgt.model.SystemLog updateSystemLog(
		org.opencps.backend.systemlogmgt.model.SystemLog systemLog) {
		return getService().updateSystemLog(systemLog);
	}

	public static SystemLogLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<SystemLogLocalService, SystemLogLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(SystemLogLocalService.class);

		ServiceTracker<SystemLogLocalService, SystemLogLocalService> serviceTracker =
			new ServiceTracker<SystemLogLocalService, SystemLogLocalService>(bundle.getBundleContext(),
				SystemLogLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}