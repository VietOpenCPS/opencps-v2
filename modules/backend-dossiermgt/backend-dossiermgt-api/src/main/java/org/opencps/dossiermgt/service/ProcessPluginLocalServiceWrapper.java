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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProcessPluginLocalService}.
 *
 * @author huymq
 * @see ProcessPluginLocalService
 * @generated
 */
@ProviderType
public class ProcessPluginLocalServiceWrapper
	implements ProcessPluginLocalService,
		ServiceWrapper<ProcessPluginLocalService> {
	public ProcessPluginLocalServiceWrapper(
		ProcessPluginLocalService processPluginLocalService) {
		_processPluginLocalService = processPluginLocalService;
	}

	/**
	* Adds the process plugin to the database. Also notifies the appropriate model listeners.
	*
	* @param processPlugin the process plugin
	* @return the process plugin that was added
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessPlugin addProcessPlugin(
		org.opencps.dossiermgt.model.ProcessPlugin processPlugin) {
		return _processPluginLocalService.addProcessPlugin(processPlugin);
	}

	@Override
	public org.opencps.dossiermgt.model.ProcessPlugin adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return _processPluginLocalService.adminProcessData(objectData);
	}

	@Override
	public org.opencps.dossiermgt.model.ProcessPlugin adminProcessDelete(
		Long id) {
		return _processPluginLocalService.adminProcessDelete(id);
	}

	/**
	* Creates a new process plugin with the primary key. Does not add the process plugin to the database.
	*
	* @param processPluginId the primary key for the new process plugin
	* @return the new process plugin
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessPlugin createProcessPlugin(
		long processPluginId) {
		return _processPluginLocalService.createProcessPlugin(processPluginId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processPluginLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the process plugin with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param processPluginId the primary key of the process plugin
	* @return the process plugin that was removed
	* @throws PortalException if a process plugin with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessPlugin deleteProcessPlugin(
		long processPluginId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processPluginLocalService.deleteProcessPlugin(processPluginId);
	}

	/**
	* Deletes the process plugin from the database. Also notifies the appropriate model listeners.
	*
	* @param processPlugin the process plugin
	* @return the process plugin that was removed
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessPlugin deleteProcessPlugin(
		org.opencps.dossiermgt.model.ProcessPlugin processPlugin) {
		return _processPluginLocalService.deleteProcessPlugin(processPlugin);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _processPluginLocalService.dynamicQuery();
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
		return _processPluginLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ProcessPluginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _processPluginLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ProcessPluginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _processPluginLocalService.dynamicQuery(dynamicQuery, start,
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
		return _processPluginLocalService.dynamicQueryCount(dynamicQuery);
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
		return _processPluginLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.dossiermgt.model.ProcessPlugin fetchProcessPlugin(
		long processPluginId) {
		return _processPluginLocalService.fetchProcessPlugin(processPluginId);
	}

	/**
	* Returns the process plugin matching the UUID and group.
	*
	* @param uuid the process plugin's UUID
	* @param groupId the primary key of the group
	* @return the matching process plugin, or <code>null</code> if a matching process plugin could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessPlugin fetchProcessPluginByUuidAndGroupId(
		String uuid, long groupId) {
		return _processPluginLocalService.fetchProcessPluginByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _processPluginLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _processPluginLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _processPluginLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _processPluginLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processPluginLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the process plugin with the primary key.
	*
	* @param processPluginId the primary key of the process plugin
	* @return the process plugin
	* @throws PortalException if a process plugin with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessPlugin getProcessPlugin(
		long processPluginId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processPluginLocalService.getProcessPlugin(processPluginId);
	}

	/**
	* Returns the process plugin matching the UUID and group.
	*
	* @param uuid the process plugin's UUID
	* @param groupId the primary key of the group
	* @return the matching process plugin
	* @throws PortalException if a matching process plugin could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessPlugin getProcessPluginByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _processPluginLocalService.getProcessPluginByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the process plugins.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ProcessPluginModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of process plugins
	* @param end the upper bound of the range of process plugins (not inclusive)
	* @return the range of process plugins
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.ProcessPlugin> getProcessPlugins(
		int start, int end) {
		return _processPluginLocalService.getProcessPlugins(start, end);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.ProcessPlugin> getProcessPlugins(
		long serviceProcessId, String stepCode) {
		return _processPluginLocalService.getProcessPlugins(serviceProcessId,
			stepCode);
	}

	/**
	* Returns all the process plugins matching the UUID and company.
	*
	* @param uuid the UUID of the process plugins
	* @param companyId the primary key of the company
	* @return the matching process plugins, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.ProcessPlugin> getProcessPluginsByUuidAndCompanyId(
		String uuid, long companyId) {
		return _processPluginLocalService.getProcessPluginsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of process plugins matching the UUID and company.
	*
	* @param uuid the UUID of the process plugins
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of process plugins
	* @param end the upper bound of the range of process plugins (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching process plugins, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.ProcessPlugin> getProcessPluginsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.ProcessPlugin> orderByComparator) {
		return _processPluginLocalService.getProcessPluginsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of process plugins.
	*
	* @return the number of process plugins
	*/
	@Override
	public int getProcessPluginsCount() {
		return _processPluginLocalService.getProcessPluginsCount();
	}

	/**
	* Updates the process plugin in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param processPlugin the process plugin
	* @return the process plugin that was updated
	*/
	@Override
	public org.opencps.dossiermgt.model.ProcessPlugin updateProcessPlugin(
		org.opencps.dossiermgt.model.ProcessPlugin processPlugin) {
		return _processPluginLocalService.updateProcessPlugin(processPlugin);
	}

	@Override
	public ProcessPluginLocalService getWrappedService() {
		return _processPluginLocalService;
	}

	@Override
	public void setWrappedService(
		ProcessPluginLocalService processPluginLocalService) {
		_processPluginLocalService = processPluginLocalService;
	}

	private ProcessPluginLocalService _processPluginLocalService;
}