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
 * Provides the local service utility for ServiceProcessRole. This utility wraps
 * {@link org.opencps.dossiermgt.service.impl.ServiceProcessRoleLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author huymq
 * @see ServiceProcessRoleLocalService
 * @see org.opencps.dossiermgt.service.base.ServiceProcessRoleLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.ServiceProcessRoleLocalServiceImpl
 * @generated
 */
@ProviderType
public class ServiceProcessRoleLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.ServiceProcessRoleLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the service process role to the database. Also notifies the appropriate model listeners.
	*
	* @param serviceProcessRole the service process role
	* @return the service process role that was added
	*/
	public static org.opencps.dossiermgt.model.ServiceProcessRole addServiceProcessRole(
		org.opencps.dossiermgt.model.ServiceProcessRole serviceProcessRole) {
		return getService().addServiceProcessRole(serviceProcessRole);
	}

	/**
	* Creates a new service process role with the primary key. Does not add the service process role to the database.
	*
	* @param serviceProcessRolePK the primary key for the new service process role
	* @return the new service process role
	*/
	public static org.opencps.dossiermgt.model.ServiceProcessRole createServiceProcessRole(
		org.opencps.dossiermgt.service.persistence.ServiceProcessRolePK serviceProcessRolePK) {
		return getService().createServiceProcessRole(serviceProcessRolePK);
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
	* Deletes the service process role from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceProcessRole the service process role
	* @return the service process role that was removed
	*/
	public static org.opencps.dossiermgt.model.ServiceProcessRole deleteServiceProcessRole(
		org.opencps.dossiermgt.model.ServiceProcessRole serviceProcessRole) {
		return getService().deleteServiceProcessRole(serviceProcessRole);
	}

	/**
	* Deletes the service process role with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param serviceProcessRolePK the primary key of the service process role
	* @return the service process role that was removed
	* @throws PortalException if a service process role with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.ServiceProcessRole deleteServiceProcessRole(
		org.opencps.dossiermgt.service.persistence.ServiceProcessRolePK serviceProcessRolePK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteServiceProcessRole(serviceProcessRolePK);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ServiceProcessRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ServiceProcessRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.dossiermgt.model.ServiceProcessRole fetchServiceProcessRole(
		org.opencps.dossiermgt.service.persistence.ServiceProcessRolePK serviceProcessRolePK) {
		return getService().fetchServiceProcessRole(serviceProcessRolePK);
	}

	public static java.util.List<org.opencps.dossiermgt.model.ServiceProcessRole> findByS_P_ID(
		long serviceProcessId) {
		return getService().findByS_P_ID(serviceProcessId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static long getByServiceRoleCode(String roleCode) {
		return getService().getByServiceRoleCode(roleCode);
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
	* Returns the service process role with the primary key.
	*
	* @param serviceProcessRolePK the primary key of the service process role
	* @return the service process role
	* @throws PortalException if a service process role with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.ServiceProcessRole getServiceProcessRole(
		org.opencps.dossiermgt.service.persistence.ServiceProcessRolePK serviceProcessRolePK)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getServiceProcessRole(serviceProcessRolePK);
	}

	/**
	* Returns a range of all the service process roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ServiceProcessRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of service process roles
	* @param end the upper bound of the range of service process roles (not inclusive)
	* @return the range of service process roles
	*/
	public static java.util.List<org.opencps.dossiermgt.model.ServiceProcessRole> getServiceProcessRoles(
		int start, int end) {
		return getService().getServiceProcessRoles(start, end);
	}

	/**
	* Returns the number of service process roles.
	*
	* @return the number of service process roles
	*/
	public static int getServiceProcessRolesCount() {
		return getService().getServiceProcessRolesCount();
	}

	public static org.opencps.dossiermgt.model.ServiceProcessRole removeServiceProcessRole(
		long serviceProcessId, long roleId) {
		return getService().removeServiceProcessRole(serviceProcessId, roleId);
	}

	public static org.opencps.dossiermgt.model.ServiceProcessRole updateServiceProcessRole(
		long groupId, long serviceProcessId, long roleId, boolean moderator,
		String condition) {
		return getService()
				   .updateServiceProcessRole(groupId, serviceProcessId, roleId,
			moderator, condition);
	}

	/**
	* Updates the service process role in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param serviceProcessRole the service process role
	* @return the service process role that was updated
	*/
	public static org.opencps.dossiermgt.model.ServiceProcessRole updateServiceProcessRole(
		org.opencps.dossiermgt.model.ServiceProcessRole serviceProcessRole) {
		return getService().updateServiceProcessRole(serviceProcessRole);
	}

	public static void updateServiceProcessRoleDB(long groupId,
		long serviceProcessId, long roleId, String roleCode, String roleName,
		boolean moderator, String condition,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		getService()
			.updateServiceProcessRoleDB(groupId, serviceProcessId, roleId,
			roleCode, roleName, moderator, condition, serviceContext);
	}

	public static ServiceProcessRoleLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ServiceProcessRoleLocalService, ServiceProcessRoleLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ServiceProcessRoleLocalService.class);

		ServiceTracker<ServiceProcessRoleLocalService, ServiceProcessRoleLocalService> serviceTracker =
			new ServiceTracker<ServiceProcessRoleLocalService, ServiceProcessRoleLocalService>(bundle.getBundleContext(),
				ServiceProcessRoleLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}