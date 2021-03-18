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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SystemLogLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SystemLogLocalService
 * @generated
 */
@ProviderType
public class SystemLogLocalServiceWrapper implements SystemLogLocalService,
	ServiceWrapper<SystemLogLocalService> {
	public SystemLogLocalServiceWrapper(
		SystemLogLocalService systemLogLocalService) {
		_systemLogLocalService = systemLogLocalService;
	}

	/**
	* Adds the system log to the database. Also notifies the appropriate model listeners.
	*
	* @param systemLog the system log
	* @return the system log that was added
	*/
	@Override
	public org.opencps.backend.systemlogmgt.model.SystemLog addSystemLog(
		org.opencps.backend.systemlogmgt.model.SystemLog systemLog) {
		return _systemLogLocalService.addSystemLog(systemLog);
	}

	/**
	* Creates a new system log with the primary key. Does not add the system log to the database.
	*
	* @param logId the primary key for the new system log
	* @return the new system log
	*/
	@Override
	public org.opencps.backend.systemlogmgt.model.SystemLog createSystemLog(
		long logId) {
		return _systemLogLocalService.createSystemLog(logId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _systemLogLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the system log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param logId the primary key of the system log
	* @return the system log that was removed
	* @throws PortalException if a system log with the primary key could not be found
	*/
	@Override
	public org.opencps.backend.systemlogmgt.model.SystemLog deleteSystemLog(
		long logId) throws com.liferay.portal.kernel.exception.PortalException {
		return _systemLogLocalService.deleteSystemLog(logId);
	}

	/**
	* Deletes the system log from the database. Also notifies the appropriate model listeners.
	*
	* @param systemLog the system log
	* @return the system log that was removed
	*/
	@Override
	public org.opencps.backend.systemlogmgt.model.SystemLog deleteSystemLog(
		org.opencps.backend.systemlogmgt.model.SystemLog systemLog) {
		return _systemLogLocalService.deleteSystemLog(systemLog);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _systemLogLocalService.dynamicQuery();
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
		return _systemLogLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _systemLogLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _systemLogLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _systemLogLocalService.dynamicQueryCount(dynamicQuery);
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
		return _systemLogLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public org.opencps.backend.systemlogmgt.model.SystemLog fetchSystemLog(
		long logId) {
		return _systemLogLocalService.fetchSystemLog(logId);
	}

	/**
	* Returns the system log matching the UUID and group.
	*
	* @param uuid the system log's UUID
	* @param groupId the primary key of the group
	* @return the matching system log, or <code>null</code> if a matching system log could not be found
	*/
	@Override
	public org.opencps.backend.systemlogmgt.model.SystemLog fetchSystemLogByUuidAndGroupId(
		String uuid, long groupId) {
		return _systemLogLocalService.fetchSystemLogByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _systemLogLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _systemLogLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _systemLogLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _systemLogLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the system log with the primary key.
	*
	* @param logId the primary key of the system log
	* @return the system log
	* @throws PortalException if a system log with the primary key could not be found
	*/
	@Override
	public org.opencps.backend.systemlogmgt.model.SystemLog getSystemLog(
		long logId) throws com.liferay.portal.kernel.exception.PortalException {
		return _systemLogLocalService.getSystemLog(logId);
	}

	/**
	* Returns the system log matching the UUID and group.
	*
	* @param uuid the system log's UUID
	* @param groupId the primary key of the group
	* @return the matching system log
	* @throws PortalException if a matching system log could not be found
	*/
	@Override
	public org.opencps.backend.systemlogmgt.model.SystemLog getSystemLogByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _systemLogLocalService.getSystemLogByUuidAndGroupId(uuid, groupId);
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
	@Override
	public java.util.List<org.opencps.backend.systemlogmgt.model.SystemLog> getSystemLogs(
		int start, int end) {
		return _systemLogLocalService.getSystemLogs(start, end);
	}

	/**
	* Returns the number of system logs.
	*
	* @return the number of system logs
	*/
	@Override
	public int getSystemLogsCount() {
		return _systemLogLocalService.getSystemLogsCount();
	}

	/**
	* Updates the system log in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param systemLog the system log
	* @return the system log that was updated
	*/
	@Override
	public org.opencps.backend.systemlogmgt.model.SystemLog updateSystemLog(
		org.opencps.backend.systemlogmgt.model.SystemLog systemLog) {
		return _systemLogLocalService.updateSystemLog(systemLog);
	}

	@Override
	public SystemLogLocalService getWrappedService() {
		return _systemLogLocalService;
	}

	@Override
	public void setWrappedService(SystemLogLocalService systemLogLocalService) {
		_systemLogLocalService = systemLogLocalService;
	}

	private SystemLogLocalService _systemLogLocalService;
}