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
 * Provides the local service utility for OpenCPSDeliverable. This utility wraps
 * {@link org.opencps.deliverable.service.impl.OpenCPSDeliverableLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author binhth
 * @see OpenCPSDeliverableLocalService
 * @see org.opencps.deliverable.service.base.OpenCPSDeliverableLocalServiceBaseImpl
 * @see org.opencps.deliverable.service.impl.OpenCPSDeliverableLocalServiceImpl
 * @generated
 */
@ProviderType
public class OpenCPSDeliverableLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.deliverable.service.impl.OpenCPSDeliverableLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the open cps deliverable to the database. Also notifies the appropriate model listeners.
	*
	* @param openCPSDeliverable the open cps deliverable
	* @return the open cps deliverable that was added
	*/
	public static org.opencps.deliverable.model.OpenCPSDeliverable addOpenCPSDeliverable(
		org.opencps.deliverable.model.OpenCPSDeliverable openCPSDeliverable) {
		return getService().addOpenCPSDeliverable(openCPSDeliverable);
	}

	public static org.opencps.deliverable.model.OpenCPSDeliverable adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return getService().adminProcessData(objectData);
	}

	public static org.opencps.deliverable.model.OpenCPSDeliverable adminProcessDelete(
		Long id) {
		return getService().adminProcessDelete(id);
	}

	/**
	* Creates a new open cps deliverable with the primary key. Does not add the open cps deliverable to the database.
	*
	* @param deliverableId the primary key for the new open cps deliverable
	* @return the new open cps deliverable
	*/
	public static org.opencps.deliverable.model.OpenCPSDeliverable createOpenCPSDeliverable(
		long deliverableId) {
		return getService().createOpenCPSDeliverable(deliverableId);
	}

	/**
	* Deletes the open cps deliverable with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param deliverableId the primary key of the open cps deliverable
	* @return the open cps deliverable that was removed
	* @throws PortalException if a open cps deliverable with the primary key could not be found
	*/
	public static org.opencps.deliverable.model.OpenCPSDeliverable deleteOpenCPSDeliverable(
		long deliverableId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteOpenCPSDeliverable(deliverableId);
	}

	/**
	* Deletes the open cps deliverable from the database. Also notifies the appropriate model listeners.
	*
	* @param openCPSDeliverable the open cps deliverable
	* @return the open cps deliverable that was removed
	*/
	public static org.opencps.deliverable.model.OpenCPSDeliverable deleteOpenCPSDeliverable(
		org.opencps.deliverable.model.OpenCPSDeliverable openCPSDeliverable) {
		return getService().deleteOpenCPSDeliverable(openCPSDeliverable);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.deliverable.model.impl.OpenCPSDeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.deliverable.model.impl.OpenCPSDeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.deliverable.model.OpenCPSDeliverable fetchOpenCPSDeliverable(
		long deliverableId) {
		return getService().fetchOpenCPSDeliverable(deliverableId);
	}

	/**
	* Returns the open cps deliverable matching the UUID and group.
	*
	* @param uuid the open cps deliverable's UUID
	* @param groupId the primary key of the group
	* @return the matching open cps deliverable, or <code>null</code> if a matching open cps deliverable could not be found
	*/
	public static org.opencps.deliverable.model.OpenCPSDeliverable fetchOpenCPSDeliverableByUuidAndGroupId(
		String uuid, long groupId) {
		return getService()
				   .fetchOpenCPSDeliverableByUuidAndGroupId(uuid, groupId);
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
	* Returns the open cps deliverable with the primary key.
	*
	* @param deliverableId the primary key of the open cps deliverable
	* @return the open cps deliverable
	* @throws PortalException if a open cps deliverable with the primary key could not be found
	*/
	public static org.opencps.deliverable.model.OpenCPSDeliverable getOpenCPSDeliverable(
		long deliverableId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getOpenCPSDeliverable(deliverableId);
	}

	/**
	* Returns the open cps deliverable matching the UUID and group.
	*
	* @param uuid the open cps deliverable's UUID
	* @param groupId the primary key of the group
	* @return the matching open cps deliverable
	* @throws PortalException if a matching open cps deliverable could not be found
	*/
	public static org.opencps.deliverable.model.OpenCPSDeliverable getOpenCPSDeliverableByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getOpenCPSDeliverableByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the open cps deliverables.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.deliverable.model.impl.OpenCPSDeliverableModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of open cps deliverables
	* @param end the upper bound of the range of open cps deliverables (not inclusive)
	* @return the range of open cps deliverables
	*/
	public static java.util.List<org.opencps.deliverable.model.OpenCPSDeliverable> getOpenCPSDeliverables(
		int start, int end) {
		return getService().getOpenCPSDeliverables(start, end);
	}

	/**
	* Returns all the open cps deliverables matching the UUID and company.
	*
	* @param uuid the UUID of the open cps deliverables
	* @param companyId the primary key of the company
	* @return the matching open cps deliverables, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.deliverable.model.OpenCPSDeliverable> getOpenCPSDeliverablesByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService()
				   .getOpenCPSDeliverablesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of open cps deliverables matching the UUID and company.
	*
	* @param uuid the UUID of the open cps deliverables
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of open cps deliverables
	* @param end the upper bound of the range of open cps deliverables (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching open cps deliverables, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.deliverable.model.OpenCPSDeliverable> getOpenCPSDeliverablesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.deliverable.model.OpenCPSDeliverable> orderByComparator) {
		return getService()
				   .getOpenCPSDeliverablesByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of open cps deliverables.
	*
	* @return the number of open cps deliverables
	*/
	public static int getOpenCPSDeliverablesCount() {
		return getService().getOpenCPSDeliverablesCount();
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
	* Updates the open cps deliverable in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param openCPSDeliverable the open cps deliverable
	* @return the open cps deliverable that was updated
	*/
	public static org.opencps.deliverable.model.OpenCPSDeliverable updateOpenCPSDeliverable(
		org.opencps.deliverable.model.OpenCPSDeliverable openCPSDeliverable) {
		return getService().updateOpenCPSDeliverable(openCPSDeliverable);
	}

	public static OpenCPSDeliverableLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<OpenCPSDeliverableLocalService, OpenCPSDeliverableLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(OpenCPSDeliverableLocalService.class);

		ServiceTracker<OpenCPSDeliverableLocalService, OpenCPSDeliverableLocalService> serviceTracker =
			new ServiceTracker<OpenCPSDeliverableLocalService, OpenCPSDeliverableLocalService>(bundle.getBundleContext(),
				OpenCPSDeliverableLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}