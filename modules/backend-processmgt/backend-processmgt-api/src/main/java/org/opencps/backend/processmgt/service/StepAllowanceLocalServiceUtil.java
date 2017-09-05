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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for StepAllowance. This utility wraps
 * {@link org.opencps.backend.processmgt.service.impl.StepAllowanceLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author khoavu
 * @see StepAllowanceLocalService
 * @see org.opencps.backend.processmgt.service.base.StepAllowanceLocalServiceBaseImpl
 * @see org.opencps.backend.processmgt.service.impl.StepAllowanceLocalServiceImpl
 * @generated
 */
@ProviderType
public class StepAllowanceLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.backend.processmgt.service.impl.StepAllowanceLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of step allowances.
	*
	* @return the number of step allowances
	*/
	public static int getStepAllowancesCount() {
		return getService().getStepAllowancesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.processmgt.model.impl.StepAllowanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.backend.processmgt.model.impl.StepAllowanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public static java.util.List<org.opencps.backend.processmgt.model.StepAllowance> getStepAllowances(
		int start, int end) {
		return getService().getStepAllowances(start, end);
	}

	/**
	* Returns all the step allowances matching the UUID and company.
	*
	* @param uuid the UUID of the step allowances
	* @param companyId the primary key of the company
	* @return the matching step allowances, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.backend.processmgt.model.StepAllowance> getStepAllowancesByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getStepAllowancesByUuidAndCompanyId(uuid, companyId);
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
	public static java.util.List<org.opencps.backend.processmgt.model.StepAllowance> getStepAllowancesByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.backend.processmgt.model.StepAllowance> orderByComparator) {
		return getService()
				   .getStepAllowancesByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
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

	/**
	* Adds the step allowance to the database. Also notifies the appropriate model listeners.
	*
	* @param stepAllowance the step allowance
	* @return the step allowance that was added
	*/
	public static org.opencps.backend.processmgt.model.StepAllowance addStepAllowance(
		org.opencps.backend.processmgt.model.StepAllowance stepAllowance) {
		return getService().addStepAllowance(stepAllowance);
	}

	/**
	* Creates a new step allowance with the primary key. Does not add the step allowance to the database.
	*
	* @param stepAllowancePK the primary key for the new step allowance
	* @return the new step allowance
	*/
	public static org.opencps.backend.processmgt.model.StepAllowance createStepAllowance(
		org.opencps.backend.processmgt.service.persistence.StepAllowancePK stepAllowancePK) {
		return getService().createStepAllowance(stepAllowancePK);
	}

	/**
	* Deletes the step allowance from the database. Also notifies the appropriate model listeners.
	*
	* @param stepAllowance the step allowance
	* @return the step allowance that was removed
	*/
	public static org.opencps.backend.processmgt.model.StepAllowance deleteStepAllowance(
		org.opencps.backend.processmgt.model.StepAllowance stepAllowance) {
		return getService().deleteStepAllowance(stepAllowance);
	}

	/**
	* Deletes the step allowance with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param stepAllowancePK the primary key of the step allowance
	* @return the step allowance that was removed
	* @throws PortalException if a step allowance with the primary key could not be found
	*/
	public static org.opencps.backend.processmgt.model.StepAllowance deleteStepAllowance(
		org.opencps.backend.processmgt.service.persistence.StepAllowancePK stepAllowancePK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteStepAllowance(stepAllowancePK);
	}

	public static org.opencps.backend.processmgt.model.StepAllowance fetchStepAllowance(
		org.opencps.backend.processmgt.service.persistence.StepAllowancePK stepAllowancePK) {
		return getService().fetchStepAllowance(stepAllowancePK);
	}

	/**
	* Returns the step allowance matching the UUID and group.
	*
	* @param uuid the step allowance's UUID
	* @param groupId the primary key of the group
	* @return the matching step allowance, or <code>null</code> if a matching step allowance could not be found
	*/
	public static org.opencps.backend.processmgt.model.StepAllowance fetchStepAllowanceByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchStepAllowanceByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the step allowance with the primary key.
	*
	* @param stepAllowancePK the primary key of the step allowance
	* @return the step allowance
	* @throws PortalException if a step allowance with the primary key could not be found
	*/
	public static org.opencps.backend.processmgt.model.StepAllowance getStepAllowance(
		org.opencps.backend.processmgt.service.persistence.StepAllowancePK stepAllowancePK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getStepAllowance(stepAllowancePK);
	}

	/**
	* Returns the step allowance matching the UUID and group.
	*
	* @param uuid the step allowance's UUID
	* @param groupId the primary key of the group
	* @return the matching step allowance
	* @throws PortalException if a matching step allowance could not be found
	*/
	public static org.opencps.backend.processmgt.model.StepAllowance getStepAllowanceByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getStepAllowanceByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the step allowance in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param stepAllowance the step allowance
	* @return the step allowance that was updated
	*/
	public static org.opencps.backend.processmgt.model.StepAllowance updateStepAllowance(
		org.opencps.backend.processmgt.model.StepAllowance stepAllowance) {
		return getService().updateStepAllowance(stepAllowance);
	}

	public static StepAllowanceLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<StepAllowanceLocalService, StepAllowanceLocalService> _serviceTracker =
		ServiceTrackerFactory.open(StepAllowanceLocalService.class);
}