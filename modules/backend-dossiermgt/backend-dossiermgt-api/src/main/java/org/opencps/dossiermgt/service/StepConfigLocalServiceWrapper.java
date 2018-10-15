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
 * Provides a wrapper for {@link StepConfigLocalService}.
 *
 * @author huymq
 * @see StepConfigLocalService
 * @generated
 */
@ProviderType
public class StepConfigLocalServiceWrapper implements StepConfigLocalService,
	ServiceWrapper<StepConfigLocalService> {
	public StepConfigLocalServiceWrapper(
		StepConfigLocalService stepConfigLocalService) {
		_stepConfigLocalService = stepConfigLocalService;
	}

	@Override
	public org.opencps.dossiermgt.model.StepConfig addStepConfig(long userId,
		long groupId, String stepCode, String stepName, Integer stepType,
		String dossierStatus, String dossierSubStatus, String menuGroup,
		String menuStepName, String buttonConfig)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stepConfigLocalService.addStepConfig(userId, groupId, stepCode,
			stepName, stepType, dossierStatus, dossierSubStatus, menuGroup,
			menuStepName, buttonConfig);
	}

	/**
	* Adds the step config to the database. Also notifies the appropriate model listeners.
	*
	* @param stepConfig the step config
	* @return the step config that was added
	*/
	@Override
	public org.opencps.dossiermgt.model.StepConfig addStepConfig(
		org.opencps.dossiermgt.model.StepConfig stepConfig) {
		return _stepConfigLocalService.addStepConfig(stepConfig);
	}

	/**
	* Creates a new step config with the primary key. Does not add the step config to the database.
	*
	* @param stepConfigId the primary key for the new step config
	* @return the new step config
	*/
	@Override
	public org.opencps.dossiermgt.model.StepConfig createStepConfig(
		long stepConfigId) {
		return _stepConfigLocalService.createStepConfig(stepConfigId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stepConfigLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the step config with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stepConfigId the primary key of the step config
	* @return the step config that was removed
	* @throws PortalException if a step config with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.StepConfig deleteStepConfig(
		long stepConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stepConfigLocalService.deleteStepConfig(stepConfigId);
	}

	/**
	* Deletes the step config from the database. Also notifies the appropriate model listeners.
	*
	* @param stepConfig the step config
	* @return the step config that was removed
	*/
	@Override
	public org.opencps.dossiermgt.model.StepConfig deleteStepConfig(
		org.opencps.dossiermgt.model.StepConfig stepConfig) {
		return _stepConfigLocalService.deleteStepConfig(stepConfig);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _stepConfigLocalService.dynamicQuery();
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
		return _stepConfigLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _stepConfigLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _stepConfigLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _stepConfigLocalService.dynamicQueryCount(dynamicQuery);
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
		return _stepConfigLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.dossiermgt.model.StepConfig fetchStepConfig(
		long stepConfigId) {
		return _stepConfigLocalService.fetchStepConfig(stepConfigId);
	}

	/**
	* Returns the step config matching the UUID and group.
	*
	* @param uuid the step config's UUID
	* @param groupId the primary key of the group
	* @return the matching step config, or <code>null</code> if a matching step config could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.StepConfig fetchStepConfigByUuidAndGroupId(
		String uuid, long groupId) {
		return _stepConfigLocalService.fetchStepConfigByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _stepConfigLocalService.getActionableDynamicQuery();
	}

	@Override
	public org.opencps.dossiermgt.model.StepConfig getByCode(long groupId,
		String stepCode) {
		return _stepConfigLocalService.getByCode(groupId, stepCode);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.StepConfig> getByStepType(
		long groupId, int stepType) {
		return _stepConfigLocalService.getByStepType(groupId, stepType);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _stepConfigLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _stepConfigLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _stepConfigLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stepConfigLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.StepConfig> getStepByGroupId(
		long groupId) {
		return _stepConfigLocalService.getStepByGroupId(groupId);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.StepConfig> getStepByMainStatusAndSubStatus(
		long groupId, String mainStatus, String subStatus) {
		return _stepConfigLocalService.getStepByMainStatusAndSubStatus(groupId,
			mainStatus, subStatus);
	}

	/**
	* Returns the step config with the primary key.
	*
	* @param stepConfigId the primary key of the step config
	* @return the step config
	* @throws PortalException if a step config with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.StepConfig getStepConfig(
		long stepConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stepConfigLocalService.getStepConfig(stepConfigId);
	}

	/**
	* Returns the step config matching the UUID and group.
	*
	* @param uuid the step config's UUID
	* @param groupId the primary key of the group
	* @return the matching step config
	* @throws PortalException if a matching step config could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.StepConfig getStepConfigByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stepConfigLocalService.getStepConfigByUuidAndGroupId(uuid,
			groupId);
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
	@Override
	public java.util.List<org.opencps.dossiermgt.model.StepConfig> getStepConfigs(
		int start, int end) {
		return _stepConfigLocalService.getStepConfigs(start, end);
	}

	/**
	* Returns all the step configs matching the UUID and company.
	*
	* @param uuid the UUID of the step configs
	* @param companyId the primary key of the company
	* @return the matching step configs, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.StepConfig> getStepConfigsByUuidAndCompanyId(
		String uuid, long companyId) {
		return _stepConfigLocalService.getStepConfigsByUuidAndCompanyId(uuid,
			companyId);
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
	@Override
	public java.util.List<org.opencps.dossiermgt.model.StepConfig> getStepConfigsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.StepConfig> orderByComparator) {
		return _stepConfigLocalService.getStepConfigsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of step configs.
	*
	* @return the number of step configs
	*/
	@Override
	public int getStepConfigsCount() {
		return _stepConfigLocalService.getStepConfigsCount();
	}

	@Override
	public org.opencps.dossiermgt.model.StepConfig removeStepConfig(
		long stepConfigId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stepConfigLocalService.removeStepConfig(stepConfigId);
	}

	@Override
	public org.opencps.dossiermgt.model.StepConfig updateStepConfig(
		long stepConfigId, long userId, long groupId, String stepCode,
		String stepName, Integer stepType, String dossierStatus,
		String dossierSubStatus, String menuGroup, String menuStepName,
		String buttonConfig)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stepConfigLocalService.updateStepConfig(stepConfigId, userId,
			groupId, stepCode, stepName, stepType, dossierStatus,
			dossierSubStatus, menuGroup, menuStepName, buttonConfig);
	}

	/**
	* Updates the step config in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param stepConfig the step config
	* @return the step config that was updated
	*/
	@Override
	public org.opencps.dossiermgt.model.StepConfig updateStepConfig(
		org.opencps.dossiermgt.model.StepConfig stepConfig) {
		return _stepConfigLocalService.updateStepConfig(stepConfig);
	}

	@Override
	public org.opencps.dossiermgt.model.StepConfig updateStepConfigDB(
		long userId, long groupId, String stepCode, String stepName,
		Integer stepType, String dossierStatus, String dossierSubStatus,
		String menuGroup, String menuStepName, String buttonConfig)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stepConfigLocalService.updateStepConfigDB(userId, groupId,
			stepCode, stepName, stepType, dossierStatus, dossierSubStatus,
			menuGroup, menuStepName, buttonConfig);
	}

	@Override
	public StepConfigLocalService getWrappedService() {
		return _stepConfigLocalService;
	}

	@Override
	public void setWrappedService(StepConfigLocalService stepConfigLocalService) {
		_stepConfigLocalService = stepConfigLocalService;
	}

	private StepConfigLocalService _stepConfigLocalService;
}