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

package org.opencps.deliverable.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for OpenCPSDeliverableTypeRole. This utility wraps
 * {@link org.opencps.deliverable.service.impl.OpenCPSDeliverableTypeRoleLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author binhth
 * @see OpenCPSDeliverableTypeRoleLocalService
 * @see org.opencps.deliverable.service.base.OpenCPSDeliverableTypeRoleLocalServiceBaseImpl
 * @see org.opencps.deliverable.service.impl.OpenCPSDeliverableTypeRoleLocalServiceImpl
 * @generated
 */
@ProviderType
public class OpenCPSDeliverableTypeRoleLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.deliverable.service.impl.OpenCPSDeliverableTypeRoleLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the open cps deliverable type role to the database. Also notifies the appropriate model listeners.
	*
	* @param openCPSDeliverableTypeRole the open cps deliverable type role
	* @return the open cps deliverable type role that was added
	*/
	public static org.opencps.deliverable.model.OpenCPSDeliverableTypeRole addOpenCPSDeliverableTypeRole(
		org.opencps.deliverable.model.OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole) {
		return getService()
				   .addOpenCPSDeliverableTypeRole(openCPSDeliverableTypeRole);
	}

	public static org.opencps.deliverable.model.OpenCPSDeliverableTypeRole adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return getService().adminProcessData(objectData);
	}

	public static org.opencps.deliverable.model.OpenCPSDeliverableTypeRole adminProcessDelete(
		Long id) {
		return getService().adminProcessDelete(id);
	}

	/**
	* Creates a new open cps deliverable type role with the primary key. Does not add the open cps deliverable type role to the database.
	*
	* @param deliverableTypeRoleId the primary key for the new open cps deliverable type role
	* @return the new open cps deliverable type role
	*/
	public static org.opencps.deliverable.model.OpenCPSDeliverableTypeRole createOpenCPSDeliverableTypeRole(
		long deliverableTypeRoleId) {
		return getService()
				   .createOpenCPSDeliverableTypeRole(deliverableTypeRoleId);
	}

	/**
	* Deletes the open cps deliverable type role with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param deliverableTypeRoleId the primary key of the open cps deliverable type role
	* @return the open cps deliverable type role that was removed
	* @throws PortalException if a open cps deliverable type role with the primary key could not be found
	*/
	public static org.opencps.deliverable.model.OpenCPSDeliverableTypeRole deleteOpenCPSDeliverableTypeRole(
		long deliverableTypeRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .deleteOpenCPSDeliverableTypeRole(deliverableTypeRoleId);
	}

	/**
	* Deletes the open cps deliverable type role from the database. Also notifies the appropriate model listeners.
	*
	* @param openCPSDeliverableTypeRole the open cps deliverable type role
	* @return the open cps deliverable type role that was removed
	*/
	public static org.opencps.deliverable.model.OpenCPSDeliverableTypeRole deleteOpenCPSDeliverableTypeRole(
		org.opencps.deliverable.model.OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole) {
		return getService()
				   .deleteOpenCPSDeliverableTypeRole(openCPSDeliverableTypeRole);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.deliverable.model.impl.OpenCPSDeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.deliverable.model.impl.OpenCPSDeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.deliverable.model.OpenCPSDeliverableTypeRole fetchOpenCPSDeliverableTypeRole(
		long deliverableTypeRoleId) {
		return getService()
				   .fetchOpenCPSDeliverableTypeRole(deliverableTypeRoleId);
	}

	/**
	* Returns the open cps deliverable type role matching the UUID and group.
	*
	* @param uuid the open cps deliverable type role's UUID
	* @param groupId the primary key of the group
	* @return the matching open cps deliverable type role, or <code>null</code> if a matching open cps deliverable type role could not be found
	*/
	public static org.opencps.deliverable.model.OpenCPSDeliverableTypeRole fetchOpenCPSDeliverableTypeRoleByUuidAndGroupId(
		String uuid, long groupId) {
		return getService()
				   .fetchOpenCPSDeliverableTypeRoleByUuidAndGroupId(uuid,
			groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the open cps deliverable type role with the primary key.
	*
	* @param deliverableTypeRoleId the primary key of the open cps deliverable type role
	* @return the open cps deliverable type role
	* @throws PortalException if a open cps deliverable type role with the primary key could not be found
	*/
	public static org.opencps.deliverable.model.OpenCPSDeliverableTypeRole getOpenCPSDeliverableTypeRole(
		long deliverableTypeRoleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getOpenCPSDeliverableTypeRole(deliverableTypeRoleId);
	}

	/**
	* Returns the open cps deliverable type role matching the UUID and group.
	*
	* @param uuid the open cps deliverable type role's UUID
	* @param groupId the primary key of the group
	* @return the matching open cps deliverable type role
	* @throws PortalException if a matching open cps deliverable type role could not be found
	*/
	public static org.opencps.deliverable.model.OpenCPSDeliverableTypeRole getOpenCPSDeliverableTypeRoleByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getOpenCPSDeliverableTypeRoleByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the open cps deliverable type roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.deliverable.model.impl.OpenCPSDeliverableTypeRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of open cps deliverable type roles
	* @param end the upper bound of the range of open cps deliverable type roles (not inclusive)
	* @return the range of open cps deliverable type roles
	*/
	public static java.util.List<org.opencps.deliverable.model.OpenCPSDeliverableTypeRole> getOpenCPSDeliverableTypeRoles(
		int start, int end) {
		return getService().getOpenCPSDeliverableTypeRoles(start, end);
	}

	/**
	* Returns all the open cps deliverable type roles matching the UUID and company.
	*
	* @param uuid the UUID of the open cps deliverable type roles
	* @param companyId the primary key of the company
	* @return the matching open cps deliverable type roles, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.deliverable.model.OpenCPSDeliverableTypeRole> getOpenCPSDeliverableTypeRolesByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService()
				   .getOpenCPSDeliverableTypeRolesByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of open cps deliverable type roles matching the UUID and company.
	*
	* @param uuid the UUID of the open cps deliverable type roles
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of open cps deliverable type roles
	* @param end the upper bound of the range of open cps deliverable type roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching open cps deliverable type roles, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.deliverable.model.OpenCPSDeliverableTypeRole> getOpenCPSDeliverableTypeRolesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.deliverable.model.OpenCPSDeliverableTypeRole> orderByComparator) {
		return getService()
				   .getOpenCPSDeliverableTypeRolesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of open cps deliverable type roles.
	*
	* @return the number of open cps deliverable type roles
	*/
	public static int getOpenCPSDeliverableTypeRolesCount() {
		return getService().getOpenCPSDeliverableTypeRolesCount();
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
	* Updates the open cps deliverable type role in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param openCPSDeliverableTypeRole the open cps deliverable type role
	* @return the open cps deliverable type role that was updated
	*/
	public static org.opencps.deliverable.model.OpenCPSDeliverableTypeRole updateOpenCPSDeliverableTypeRole(
		org.opencps.deliverable.model.OpenCPSDeliverableTypeRole openCPSDeliverableTypeRole) {
		return getService()
				   .updateOpenCPSDeliverableTypeRole(openCPSDeliverableTypeRole);
	}

	public static OpenCPSDeliverableTypeRoleLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<OpenCPSDeliverableTypeRoleLocalService, OpenCPSDeliverableTypeRoleLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(OpenCPSDeliverableTypeRoleLocalService.class);

		ServiceTracker<OpenCPSDeliverableTypeRoleLocalService, OpenCPSDeliverableTypeRoleLocalService> serviceTracker =
			new ServiceTracker<OpenCPSDeliverableTypeRoleLocalService, OpenCPSDeliverableTypeRoleLocalService>(bundle.getBundleContext(),
				OpenCPSDeliverableTypeRoleLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}