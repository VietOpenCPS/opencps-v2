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

package org.opencps.backend.processmgt.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link StepAllowanceLocalService}.
 *
 * @author khoavu
 * @see StepAllowanceLocalService
 * @generated
 */
@ProviderType
public class StepAllowanceLocalServiceWrapper
	implements StepAllowanceLocalService,
		ServiceWrapper<StepAllowanceLocalService> {
	public StepAllowanceLocalServiceWrapper(
		StepAllowanceLocalService stepAllowanceLocalService) {
		_stepAllowanceLocalService = stepAllowanceLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _stepAllowanceLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _stepAllowanceLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _stepAllowanceLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _stepAllowanceLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stepAllowanceLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stepAllowanceLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of step allowances.
	*
	* @return the number of step allowances
	*/
	@Override
	public int getStepAllowancesCount() {
		return _stepAllowanceLocalService.getStepAllowancesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _stepAllowanceLocalService.getOSGiServiceIdentifier();
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
		return _stepAllowanceLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.processmgt.model.impl.StepAllowanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _stepAllowanceLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.processmgt.model.impl.StepAllowanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _stepAllowanceLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the step allowances.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.processmgt.model.impl.StepAllowanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of step allowances
	* @param end the upper bound of the range of step allowances (not inclusive)
	* @return the range of step allowances
	*/
	@Override
	public java.util.List<org.opencps.backend.processmgt.model.StepAllowance> getStepAllowances(
		int start, int end) {
		return _stepAllowanceLocalService.getStepAllowances(start, end);
	}

	/**
	* Returns all the step allowances matching the UUID and company.
	*
	* @param uuid the UUID of the step allowances
	* @param companyId the primary key of the company
	* @return the matching step allowances, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.backend.processmgt.model.StepAllowance> getStepAllowancesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _stepAllowanceLocalService.getStepAllowancesByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of step allowances matching the UUID and company.
	*
	* @param uuid the UUID of the step allowances
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of step allowances
	* @param end the upper bound of the range of step allowances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching step allowances, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.backend.processmgt.model.StepAllowance> getStepAllowancesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.backend.processmgt.model.StepAllowance> orderByComparator) {
		return _stepAllowanceLocalService.getStepAllowancesByUuidAndCompanyId(uuid,
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
		return _stepAllowanceLocalService.dynamicQueryCount(dynamicQuery);
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
		return _stepAllowanceLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	/**
	* Adds the step allowance to the database. Also notifies the appropriate model listeners.
	*
	* @param stepAllowance the step allowance
	* @return the step allowance that was added
	*/
	@Override
	public org.opencps.backend.processmgt.model.StepAllowance addStepAllowance(
		org.opencps.backend.processmgt.model.StepAllowance stepAllowance) {
		return _stepAllowanceLocalService.addStepAllowance(stepAllowance);
	}

	/**
	* Creates a new step allowance with the primary key. Does not add the step allowance to the database.
	*
	* @param stepAllowancePK the primary key for the new step allowance
	* @return the new step allowance
	*/
	@Override
	public org.opencps.backend.processmgt.model.StepAllowance createStepAllowance(
		org.opencps.backend.processmgt.service.persistence.StepAllowancePK stepAllowancePK) {
		return _stepAllowanceLocalService.createStepAllowance(stepAllowancePK);
	}

	/**
	* Deletes the step allowance from the database. Also notifies the appropriate model listeners.
	*
	* @param stepAllowance the step allowance
	* @return the step allowance that was removed
	*/
	@Override
	public org.opencps.backend.processmgt.model.StepAllowance deleteStepAllowance(
		org.opencps.backend.processmgt.model.StepAllowance stepAllowance) {
		return _stepAllowanceLocalService.deleteStepAllowance(stepAllowance);
	}

	/**
	* Deletes the step allowance with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stepAllowancePK the primary key of the step allowance
	* @return the step allowance that was removed
	* @throws PortalException if a step allowance with the primary key could not be found
	*/
	@Override
	public org.opencps.backend.processmgt.model.StepAllowance deleteStepAllowance(
		org.opencps.backend.processmgt.service.persistence.StepAllowancePK stepAllowancePK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stepAllowanceLocalService.deleteStepAllowance(stepAllowancePK);
	}

	@Override
	public org.opencps.backend.processmgt.model.StepAllowance fetchStepAllowance(
		org.opencps.backend.processmgt.service.persistence.StepAllowancePK stepAllowancePK) {
		return _stepAllowanceLocalService.fetchStepAllowance(stepAllowancePK);
	}

	/**
	* Returns the step allowance matching the UUID and group.
	*
	* @param uuid the step allowance's UUID
	* @param groupId the primary key of the group
	* @return the matching step allowance, or <code>null</code> if a matching step allowance could not be found
	*/
	@Override
	public org.opencps.backend.processmgt.model.StepAllowance fetchStepAllowanceByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _stepAllowanceLocalService.fetchStepAllowanceByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the step allowance with the primary key.
	*
	* @param stepAllowancePK the primary key of the step allowance
	* @return the step allowance
	* @throws PortalException if a step allowance with the primary key could not be found
	*/
	@Override
	public org.opencps.backend.processmgt.model.StepAllowance getStepAllowance(
		org.opencps.backend.processmgt.service.persistence.StepAllowancePK stepAllowancePK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stepAllowanceLocalService.getStepAllowance(stepAllowancePK);
	}

	/**
	* Returns the step allowance matching the UUID and group.
	*
	* @param uuid the step allowance's UUID
	* @param groupId the primary key of the group
	* @return the matching step allowance
	* @throws PortalException if a matching step allowance could not be found
	*/
	@Override
	public org.opencps.backend.processmgt.model.StepAllowance getStepAllowanceByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _stepAllowanceLocalService.getStepAllowanceByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Updates the step allowance in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param stepAllowance the step allowance
	* @return the step allowance that was updated
	*/
	@Override
	public org.opencps.backend.processmgt.model.StepAllowance updateStepAllowance(
		org.opencps.backend.processmgt.model.StepAllowance stepAllowance) {
		return _stepAllowanceLocalService.updateStepAllowance(stepAllowance);
	}

	@Override
	public StepAllowanceLocalService getWrappedService() {
		return _stepAllowanceLocalService;
	}

	@Override
	public void setWrappedService(
		StepAllowanceLocalService stepAllowanceLocalService) {
		_stepAllowanceLocalService = stepAllowanceLocalService;
	}

	private StepAllowanceLocalService _stepAllowanceLocalService;
}