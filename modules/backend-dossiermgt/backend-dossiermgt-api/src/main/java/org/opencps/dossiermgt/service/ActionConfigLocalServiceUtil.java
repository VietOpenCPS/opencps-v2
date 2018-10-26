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
 * Provides the local service utility for ActionConfig. This utility wraps
 * {@link org.opencps.dossiermgt.service.impl.ActionConfigLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author huymq
 * @see ActionConfigLocalService
 * @see org.opencps.dossiermgt.service.base.ActionConfigLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.ActionConfigLocalServiceImpl
 * @generated
 */
@ProviderType
public class ActionConfigLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.ActionConfigLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the action config to the database. Also notifies the appropriate model listeners.
	*
	* @param actionConfig the action config
	* @return the action config that was added
	*/
	public static org.opencps.dossiermgt.model.ActionConfig addActionConfig(
		org.opencps.dossiermgt.model.ActionConfig actionConfig) {
		return getService().addActionConfig(actionConfig);
	}

	public static org.opencps.dossiermgt.model.ActionConfig addActionConfig(
		long userId, long groupId, String actionCode, String actionName,
		Boolean extraForm, String formScript, String sampleData,
		Boolean insideProcess, Integer userNote, Integer syncType,
		Boolean pending, Boolean rollbackable, String notificationType,
		String documentType, String mappingAction)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addActionConfig(userId, groupId, actionCode, actionName,
			extraForm, formScript, sampleData, insideProcess, userNote,
			syncType, pending, rollbackable, notificationType, documentType,
			mappingAction);
	}

	public static org.opencps.dossiermgt.model.ActionConfig adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return getService().adminProcessData(objectData);
	}

	public static org.opencps.dossiermgt.model.ActionConfig adminProcessDelete(
		Long id) {
		return getService().adminProcessDelete(id);
	}

	/**
	* Creates a new action config with the primary key. Does not add the action config to the database.
	*
	* @param actionConfigId the primary key for the new action config
	* @return the new action config
	*/
	public static org.opencps.dossiermgt.model.ActionConfig createActionConfig(
		long actionConfigId) {
		return getService().createActionConfig(actionConfigId);
	}

	/**
	* Deletes the action config from the database. Also notifies the appropriate model listeners.
	*
	* @param actionConfig the action config
	* @return the action config that was removed
	*/
	public static org.opencps.dossiermgt.model.ActionConfig deleteActionConfig(
		org.opencps.dossiermgt.model.ActionConfig actionConfig) {
		return getService().deleteActionConfig(actionConfig);
	}

	/**
	* Deletes the action config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param actionConfigId the primary key of the action config
	* @return the action config that was removed
	* @throws PortalException if a action config with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.ActionConfig deleteActionConfig(
		long actionConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteActionConfig(actionConfigId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.dossiermgt.model.ActionConfig fetchActionConfig(
		long actionConfigId) {
		return getService().fetchActionConfig(actionConfigId);
	}

	/**
	* Returns the action config matching the UUID and group.
	*
	* @param uuid the action config's UUID
	* @param groupId the primary key of the group
	* @return the matching action config, or <code>null</code> if a matching action config could not be found
	*/
	public static org.opencps.dossiermgt.model.ActionConfig fetchActionConfigByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchActionConfigByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the action config with the primary key.
	*
	* @param actionConfigId the primary key of the action config
	* @return the action config
	* @throws PortalException if a action config with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.ActionConfig getActionConfig(
		long actionConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getActionConfig(actionConfigId);
	}

	/**
	* Returns the action config matching the UUID and group.
	*
	* @param uuid the action config's UUID
	* @param groupId the primary key of the group
	* @return the matching action config
	* @throws PortalException if a matching action config could not be found
	*/
	public static org.opencps.dossiermgt.model.ActionConfig getActionConfigByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getActionConfigByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the action configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ActionConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of action configs
	* @param end the upper bound of the range of action configs (not inclusive)
	* @return the range of action configs
	*/
	public static java.util.List<org.opencps.dossiermgt.model.ActionConfig> getActionConfigs(
		int start, int end) {
		return getService().getActionConfigs(start, end);
	}

	/**
	* Returns all the action configs matching the UUID and company.
	*
	* @param uuid the UUID of the action configs
	* @param companyId the primary key of the company
	* @return the matching action configs, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.dossiermgt.model.ActionConfig> getActionConfigsByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getActionConfigsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of action configs matching the UUID and company.
	*
	* @param uuid the UUID of the action configs
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of action configs
	* @param end the upper bound of the range of action configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching action configs, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.dossiermgt.model.ActionConfig> getActionConfigsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.ActionConfig> orderByComparator) {
		return getService()
				   .getActionConfigsByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of action configs.
	*
	* @return the number of action configs
	*/
	public static int getActionConfigsCount() {
		return getService().getActionConfigsCount();
	}

	public static org.opencps.dossiermgt.model.ActionConfig getByCode(
		long groupId, String actionCode) {
		return getService().getByCode(groupId, actionCode);
	}

	public static java.util.List<org.opencps.dossiermgt.model.ActionConfig> getByGroupId(
		long groupId) {
		return getService().getByGroupId(groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.json.JSONObject getForm(
		long groupId, String actionCode) {
		return getService().getForm(groupId, actionCode);
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

	public static org.opencps.dossiermgt.model.ActionConfig removeActionConfig(
		long actionConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().removeActionConfig(actionConfigId);
	}

	/**
	* Updates the action config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param actionConfig the action config
	* @return the action config that was updated
	*/
	public static org.opencps.dossiermgt.model.ActionConfig updateActionConfig(
		org.opencps.dossiermgt.model.ActionConfig actionConfig) {
		return getService().updateActionConfig(actionConfig);
	}

	public static org.opencps.dossiermgt.model.ActionConfig updateActionConfig(
		long actionConfigId, long userId, long groupId, String actionCode,
		String actionName, Boolean extraForm, String formScript,
		String sampleData, Boolean insideProcess, Integer userNote,
		Integer syncType, Boolean pending, Boolean rollbackable,
		String notificationType, String documentType, String mappingAction)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateActionConfig(actionConfigId, userId, groupId,
			actionCode, actionName, extraForm, formScript, sampleData,
			insideProcess, userNote, syncType, pending, rollbackable,
			notificationType, documentType, mappingAction);
	}

	public static org.opencps.dossiermgt.model.ActionConfig updateActionConfigDB(
		long userId, long groupId, String actionCode, String actionName,
		Boolean extraForm, String sampleData, Boolean insideProcess,
		Integer userNote, Integer syncType, Integer eventType,
		Integer infoType, Boolean rollbackable, String notificationType,
		String documentType, String formConfig, String mappingAction)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateActionConfigDB(userId, groupId, actionCode,
			actionName, extraForm, sampleData, insideProcess, userNote,
			syncType, eventType, infoType, rollbackable, notificationType,
			documentType, formConfig, mappingAction);
	}

	public static ActionConfigLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ActionConfigLocalService, ActionConfigLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ActionConfigLocalService.class);

		ServiceTracker<ActionConfigLocalService, ActionConfigLocalService> serviceTracker =
			new ServiceTracker<ActionConfigLocalService, ActionConfigLocalService>(bundle.getBundleContext(),
				ActionConfigLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}