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
 * Provides the local service utility for ProcessStepRole. This utility wraps
 * {@link org.opencps.dossiermgt.service.impl.ProcessStepRoleLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author huymq
 * @see ProcessStepRoleLocalService
 * @see org.opencps.dossiermgt.service.base.ProcessStepRoleLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.ProcessStepRoleLocalServiceImpl
 * @generated
 */
@ProviderType
public class ProcessStepRoleLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.ProcessStepRoleLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the process step role to the database. Also notifies the appropriate model listeners.
	*
	* @param processStepRole the process step role
	* @return the process step role that was added
	*/
	public static org.opencps.dossiermgt.model.ProcessStepRole addProcessStepRole(
		org.opencps.dossiermgt.model.ProcessStepRole processStepRole) {
		return getService().addProcessStepRole(processStepRole);
	}

	/**
	* Creates a new process step role with the primary key. Does not add the process step role to the database.
	*
	* @param processStepRolePK the primary key for the new process step role
	* @return the new process step role
	*/
	public static org.opencps.dossiermgt.model.ProcessStepRole createProcessStepRole(
		org.opencps.dossiermgt.service.persistence.ProcessStepRolePK processStepRolePK) {
		return getService().createProcessStepRole(processStepRolePK);
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
	* Deletes the process step role from the database. Also notifies the appropriate model listeners.
	*
	* @param processStepRole the process step role
	* @return the process step role that was removed
	*/
	public static org.opencps.dossiermgt.model.ProcessStepRole deleteProcessStepRole(
		org.opencps.dossiermgt.model.ProcessStepRole processStepRole) {
		return getService().deleteProcessStepRole(processStepRole);
	}

	/**
	* Deletes the process step role with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param processStepRolePK the primary key of the process step role
	* @return the process step role that was removed
	* @throws PortalException if a process step role with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.ProcessStepRole deleteProcessStepRole(
		org.opencps.dossiermgt.service.persistence.ProcessStepRolePK processStepRolePK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteProcessStepRole(processStepRolePK);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ProcessStepRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ProcessStepRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.dossiermgt.model.ProcessStepRole fetchProcessStepRole(
		org.opencps.dossiermgt.service.persistence.ProcessStepRolePK processStepRolePK) {
		return getService().fetchProcessStepRole(processStepRolePK);
	}

	public static java.util.List<org.opencps.dossiermgt.model.ProcessStepRole> findByP_S_ID(
		long processStepId) {
		return getService().findByP_S_ID(processStepId);
	}

	public static org.opencps.dossiermgt.model.ProcessStepRole findByStepAndRole(
		long processStepId, long roleId) {
		return getService().findByStepAndRole(processStepId, roleId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static long getByRoleCode(String roleCode) {
		return getService().getByRoleCode(roleCode);
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
	* Returns the process step role with the primary key.
	*
	* @param processStepRolePK the primary key of the process step role
	* @return the process step role
	* @throws PortalException if a process step role with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.ProcessStepRole getProcessStepRole(
		org.opencps.dossiermgt.service.persistence.ProcessStepRolePK processStepRolePK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getProcessStepRole(processStepRolePK);
	}

	/**
	* Returns a range of all the process step roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ProcessStepRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of process step roles
	* @param end the upper bound of the range of process step roles (not inclusive)
	* @return the range of process step roles
	*/
	public static java.util.List<org.opencps.dossiermgt.model.ProcessStepRole> getProcessStepRoles(
		int start, int end) {
		return getService().getProcessStepRoles(start, end);
	}

	/**
	* Returns the number of process step roles.
	*
	* @return the number of process step roles
	*/
	public static int getProcessStepRolesCount() {
		return getService().getProcessStepRolesCount();
	}

	public static org.opencps.dossiermgt.model.ProcessStepRole removeProcessStepRole(
		long processStepId, long roleId) {
		return getService().removeProcessStepRole(processStepId, roleId);
	}

	public static org.opencps.dossiermgt.model.ProcessStepRole updateProcessStepRole(
		long processStepId, long roleId, boolean moderator, String condition) {
		return getService()
				   .updateProcessStepRole(processStepId, roleId, moderator,
			condition);
	}

	/**
	* Updates the process step role in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param processStepRole the process step role
	* @return the process step role that was updated
	*/
	public static org.opencps.dossiermgt.model.ProcessStepRole updateProcessStepRole(
		org.opencps.dossiermgt.model.ProcessStepRole processStepRole) {
		return getService().updateProcessStepRole(processStepRole);
	}

	public static org.opencps.dossiermgt.model.ProcessStepRole updateProcessStepRoleDB(
		long userId, long groupId, long processStepId, long roleId,
		String roleCode, String roleName, boolean moderator, String condition,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		return getService()
				   .updateProcessStepRoleDB(userId, groupId, processStepId,
			roleId, roleCode, roleName, moderator, condition, serviceContext);
	}

	public static ProcessStepRoleLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ProcessStepRoleLocalService, ProcessStepRoleLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ProcessStepRoleLocalService.class);

		ServiceTracker<ProcessStepRoleLocalService, ProcessStepRoleLocalService> serviceTracker =
			new ServiceTracker<ProcessStepRoleLocalService, ProcessStepRoleLocalService>(bundle.getBundleContext(),
				ProcessStepRoleLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}