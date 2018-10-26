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
 * Provides the local service utility for StepConfig. This utility wraps
 * {@link org.opencps.dossiermgt.service.impl.StepConfigLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author huymq
 * @see StepConfigLocalService
 * @see org.opencps.dossiermgt.service.base.StepConfigLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.StepConfigLocalServiceImpl
 * @generated
 */
@ProviderType
public class StepConfigLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.StepConfigLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.opencps.dossiermgt.model.StepConfig addStepConfig(
		long userId, long groupId, String stepCode, String stepName,
		Integer stepType, String dossierStatus, String dossierSubStatus,
		String menuGroup, String menuStepName, String buttonConfig)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addStepConfig(userId, groupId, stepCode, stepName,
			stepType, dossierStatus, dossierSubStatus, menuGroup, menuStepName,
			buttonConfig);
	}

	/**
	* Adds the step config to the database. Also notifies the appropriate model listeners.
	*
	* @param stepConfig the step config
	* @return the step config that was added
	*/
	public static org.opencps.dossiermgt.model.StepConfig addStepConfig(
		org.opencps.dossiermgt.model.StepConfig stepConfig) {
		return getService().addStepConfig(stepConfig);
	}

	public static org.opencps.dossiermgt.model.StepConfig adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return getService().adminProcessData(objectData);
	}

	public static org.opencps.dossiermgt.model.StepConfig adminProcessDelete(
		Long id) {
		return getService().adminProcessDelete(id);
	}

	/**
	* Creates a new step config with the primary key. Does not add the step config to the database.
	*
	* @param stepConfigId the primary key for the new step config
	* @return the new step config
	*/
	public static org.opencps.dossiermgt.model.StepConfig createStepConfig(
		long stepConfigId) {
		return getService().createStepConfig(stepConfigId);
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
	* Deletes the step config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stepConfigId the primary key of the step config
	* @return the step config that was removed
	* @throws PortalException if a step config with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.StepConfig deleteStepConfig(
		long stepConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteStepConfig(stepConfigId);
	}

	/**
	* Deletes the step config from the database. Also notifies the appropriate model listeners.
	*
	* @param stepConfig the step config
	* @return the step config that was removed
	*/
	public static org.opencps.dossiermgt.model.StepConfig deleteStepConfig(
		org.opencps.dossiermgt.model.StepConfig stepConfig) {
		return getService().deleteStepConfig(stepConfig);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.dossiermgt.model.StepConfig fetchStepConfig(
		long stepConfigId) {
		return getService().fetchStepConfig(stepConfigId);
	}

	/**
	* Returns the step config matching the UUID and group.
	*
	* @param uuid the step config's UUID
	* @param groupId the primary key of the group
	* @return the matching step config, or <code>null</code> if a matching step config could not be found
	*/
	public static org.opencps.dossiermgt.model.StepConfig fetchStepConfigByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchStepConfigByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static org.opencps.dossiermgt.model.StepConfig getByCode(
		long groupId, String stepCode) {
		return getService().getByCode(groupId, stepCode);
	}

	public static java.util.List<org.opencps.dossiermgt.model.StepConfig> getByStepType(
		long groupId, int stepType) {
		return getService().getByStepType(groupId, stepType);
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

	public static java.util.List<org.opencps.dossiermgt.model.StepConfig> getStepByGroupId(
		long groupId) {
		return getService().getStepByGroupId(groupId);
	}

	public static java.util.List<org.opencps.dossiermgt.model.StepConfig> getStepByMainStatusAndSubStatus(
		long groupId, String mainStatus, String subStatus) {
		return getService()
				   .getStepByMainStatusAndSubStatus(groupId, mainStatus,
			subStatus);
	}

	/**
	* Returns the step config with the primary key.
	*
	* @param stepConfigId the primary key of the step config
	* @return the step config
	* @throws PortalException if a step config with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.StepConfig getStepConfig(
		long stepConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getStepConfig(stepConfigId);
	}

	/**
	* Returns the step config matching the UUID and group.
	*
	* @param uuid the step config's UUID
	* @param groupId the primary key of the group
	* @return the matching step config
	* @throws PortalException if a matching step config could not be found
	*/
	public static org.opencps.dossiermgt.model.StepConfig getStepConfigByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getStepConfigByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the step configs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.StepConfigModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of step configs
	* @param end the upper bound of the range of step configs (not inclusive)
	* @return the range of step configs
	*/
	public static java.util.List<org.opencps.dossiermgt.model.StepConfig> getStepConfigs(
		int start, int end) {
		return getService().getStepConfigs(start, end);
	}

	/**
	* Returns all the step configs matching the UUID and company.
	*
	* @param uuid the UUID of the step configs
	* @param companyId the primary key of the company
	* @return the matching step configs, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.dossiermgt.model.StepConfig> getStepConfigsByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getStepConfigsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of step configs matching the UUID and company.
	*
	* @param uuid the UUID of the step configs
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of step configs
	* @param end the upper bound of the range of step configs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching step configs, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.dossiermgt.model.StepConfig> getStepConfigsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.StepConfig> orderByComparator) {
		return getService()
				   .getStepConfigsByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of step configs.
	*
	* @return the number of step configs
	*/
	public static int getStepConfigsCount() {
		return getService().getStepConfigsCount();
	}

	public static org.opencps.dossiermgt.model.StepConfig removeStepConfig(
		long stepConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().removeStepConfig(stepConfigId);
	}

	public static org.opencps.dossiermgt.model.StepConfig updateStepConfig(
		long stepConfigId, long userId, long groupId, String stepCode,
		String stepName, Integer stepType, String dossierStatus,
		String dossierSubStatus, String menuGroup, String menuStepName,
		String buttonConfig)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateStepConfig(stepConfigId, userId, groupId, stepCode,
			stepName, stepType, dossierStatus, dossierSubStatus, menuGroup,
			menuStepName, buttonConfig);
	}

	/**
	* Updates the step config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param stepConfig the step config
	* @return the step config that was updated
	*/
	public static org.opencps.dossiermgt.model.StepConfig updateStepConfig(
		org.opencps.dossiermgt.model.StepConfig stepConfig) {
		return getService().updateStepConfig(stepConfig);
	}

	public static org.opencps.dossiermgt.model.StepConfig updateStepConfigDB(
		long userId, long groupId, String stepCode, String stepName,
		Integer stepType, String dossierStatus, String dossierSubStatus,
		String menuGroup, String menuStepName, String buttonConfig)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateStepConfigDB(userId, groupId, stepCode, stepName,
			stepType, dossierStatus, dossierSubStatus, menuGroup, menuStepName,
			buttonConfig);
	}

	public static StepConfigLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<StepConfigLocalService, StepConfigLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(StepConfigLocalService.class);

		ServiceTracker<StepConfigLocalService, StepConfigLocalService> serviceTracker =
			new ServiceTracker<StepConfigLocalService, StepConfigLocalService>(bundle.getBundleContext(),
				StepConfigLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}