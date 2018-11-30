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
 * Provides the local service utility for OpenCPSDeliverableType. This utility wraps
 * {@link org.opencps.deliverable.service.impl.OpenCPSDeliverableTypeLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author binhth
 * @see OpenCPSDeliverableTypeLocalService
 * @see org.opencps.deliverable.service.base.OpenCPSDeliverableTypeLocalServiceBaseImpl
 * @see org.opencps.deliverable.service.impl.OpenCPSDeliverableTypeLocalServiceImpl
 * @generated
 */
@ProviderType
public class OpenCPSDeliverableTypeLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.deliverable.service.impl.OpenCPSDeliverableTypeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the open cps deliverable type to the database. Also notifies the appropriate model listeners.
	*
	* @param openCPSDeliverableType the open cps deliverable type
	* @return the open cps deliverable type that was added
	*/
	public static org.opencps.deliverable.model.OpenCPSDeliverableType addOpenCPSDeliverableType(
		org.opencps.deliverable.model.OpenCPSDeliverableType openCPSDeliverableType) {
		return getService().addOpenCPSDeliverableType(openCPSDeliverableType);
	}

	public static org.opencps.deliverable.model.OpenCPSDeliverableType adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return getService().adminProcessData(objectData);
	}

	public static org.opencps.deliverable.model.OpenCPSDeliverableType adminProcessDelete(
		Long id) {
		return getService().adminProcessDelete(id);
	}

	/**
	* Creates a new open cps deliverable type with the primary key. Does not add the open cps deliverable type to the database.
	*
	* @param deliverableTypeId the primary key for the new open cps deliverable type
	* @return the new open cps deliverable type
	*/
	public static org.opencps.deliverable.model.OpenCPSDeliverableType createOpenCPSDeliverableType(
		long deliverableTypeId) {
		return getService().createOpenCPSDeliverableType(deliverableTypeId);
	}

	/**
	* Deletes the open cps deliverable type with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param deliverableTypeId the primary key of the open cps deliverable type
	* @return the open cps deliverable type that was removed
	* @throws PortalException if a open cps deliverable type with the primary key could not be found
	*/
	public static org.opencps.deliverable.model.OpenCPSDeliverableType deleteOpenCPSDeliverableType(
		long deliverableTypeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteOpenCPSDeliverableType(deliverableTypeId);
	}

	/**
	* Deletes the open cps deliverable type from the database. Also notifies the appropriate model listeners.
	*
	* @param openCPSDeliverableType the open cps deliverable type
	* @return the open cps deliverable type that was removed
	*/
	public static org.opencps.deliverable.model.OpenCPSDeliverableType deleteOpenCPSDeliverableType(
		org.opencps.deliverable.model.OpenCPSDeliverableType openCPSDeliverableType) {
		return getService().deleteOpenCPSDeliverableType(openCPSDeliverableType);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.deliverable.model.impl.OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.deliverable.model.impl.OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.deliverable.model.OpenCPSDeliverableType fetchOpenCPSDeliverableType(
		long deliverableTypeId) {
		return getService().fetchOpenCPSDeliverableType(deliverableTypeId);
	}

	/**
	* Returns the open cps deliverable type matching the UUID and group.
	*
	* @param uuid the open cps deliverable type's UUID
	* @param groupId the primary key of the group
	* @return the matching open cps deliverable type, or <code>null</code> if a matching open cps deliverable type could not be found
	*/
	public static org.opencps.deliverable.model.OpenCPSDeliverableType fetchOpenCPSDeliverableTypeByUuidAndGroupId(
		String uuid, long groupId) {
		return getService()
				   .fetchOpenCPSDeliverableTypeByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static org.opencps.deliverable.model.OpenCPSDeliverableType getByTypeCode(
		String typeCode, long groupId) {
		return getService().getByTypeCode(typeCode, groupId);
	}

	public static java.util.List<org.opencps.deliverable.model.OpenCPSDeliverableType> getDeliverableTypes(
		long groupId, int start, int end) {
		return getService().getDeliverableTypes(groupId, start, end);
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the open cps deliverable type with the primary key.
	*
	* @param deliverableTypeId the primary key of the open cps deliverable type
	* @return the open cps deliverable type
	* @throws PortalException if a open cps deliverable type with the primary key could not be found
	*/
	public static org.opencps.deliverable.model.OpenCPSDeliverableType getOpenCPSDeliverableType(
		long deliverableTypeId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getOpenCPSDeliverableType(deliverableTypeId);
	}

	/**
	* Returns the open cps deliverable type matching the UUID and group.
	*
	* @param uuid the open cps deliverable type's UUID
	* @param groupId the primary key of the group
	* @return the matching open cps deliverable type
	* @throws PortalException if a matching open cps deliverable type could not be found
	*/
	public static org.opencps.deliverable.model.OpenCPSDeliverableType getOpenCPSDeliverableTypeByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getOpenCPSDeliverableTypeByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the open cps deliverable types.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.deliverable.model.impl.OpenCPSDeliverableTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of open cps deliverable types
	* @param end the upper bound of the range of open cps deliverable types (not inclusive)
	* @return the range of open cps deliverable types
	*/
	public static java.util.List<org.opencps.deliverable.model.OpenCPSDeliverableType> getOpenCPSDeliverableTypes(
		int start, int end) {
		return getService().getOpenCPSDeliverableTypes(start, end);
	}

	/**
	* Returns all the open cps deliverable types matching the UUID and company.
	*
	* @param uuid the UUID of the open cps deliverable types
	* @param companyId the primary key of the company
	* @return the matching open cps deliverable types, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.deliverable.model.OpenCPSDeliverableType> getOpenCPSDeliverableTypesByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService()
				   .getOpenCPSDeliverableTypesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of open cps deliverable types matching the UUID and company.
	*
	* @param uuid the UUID of the open cps deliverable types
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of open cps deliverable types
	* @param end the upper bound of the range of open cps deliverable types (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching open cps deliverable types, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.deliverable.model.OpenCPSDeliverableType> getOpenCPSDeliverableTypesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.deliverable.model.OpenCPSDeliverableType> orderByComparator) {
		return getService()
				   .getOpenCPSDeliverableTypesByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of open cps deliverable types.
	*
	* @return the number of open cps deliverable types
	*/
	public static int getOpenCPSDeliverableTypesCount() {
		return getService().getOpenCPSDeliverableTypesCount();
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
	* Updates the open cps deliverable type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param openCPSDeliverableType the open cps deliverable type
	* @return the open cps deliverable type that was updated
	*/
	public static org.opencps.deliverable.model.OpenCPSDeliverableType updateOpenCPSDeliverableType(
		org.opencps.deliverable.model.OpenCPSDeliverableType openCPSDeliverableType) {
		return getService().updateOpenCPSDeliverableType(openCPSDeliverableType);
	}

	public static OpenCPSDeliverableTypeLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<OpenCPSDeliverableTypeLocalService, OpenCPSDeliverableTypeLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(OpenCPSDeliverableTypeLocalService.class);

		ServiceTracker<OpenCPSDeliverableTypeLocalService, OpenCPSDeliverableTypeLocalService> serviceTracker =
			new ServiceTracker<OpenCPSDeliverableTypeLocalService, OpenCPSDeliverableTypeLocalService>(bundle.getBundleContext(),
				OpenCPSDeliverableTypeLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}