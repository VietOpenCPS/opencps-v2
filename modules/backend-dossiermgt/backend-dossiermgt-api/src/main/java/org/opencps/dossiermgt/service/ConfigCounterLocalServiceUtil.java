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
 * Provides the local service utility for ConfigCounter. This utility wraps
 * {@link org.opencps.dossiermgt.service.impl.ConfigCounterLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author huymq
 * @see ConfigCounterLocalService
 * @see org.opencps.dossiermgt.service.base.ConfigCounterLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.ConfigCounterLocalServiceImpl
 * @generated
 */
@ProviderType
public class ConfigCounterLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.ConfigCounterLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the config counter to the database. Also notifies the appropriate model listeners.
	*
	* @param configCounter the config counter
	* @return the config counter that was added
	*/
	public static org.opencps.dossiermgt.model.ConfigCounter addConfigCounter(
		org.opencps.dossiermgt.model.ConfigCounter configCounter) {
		return getService().addConfigCounter(configCounter);
	}

	/**
	* Creates a new config counter with the primary key. Does not add the config counter to the database.
	*
	* @param configCounterId the primary key for the new config counter
	* @return the new config counter
	*/
	public static org.opencps.dossiermgt.model.ConfigCounter createConfigCounter(
		long configCounterId) {
		return getService().createConfigCounter(configCounterId);
	}

	/**
	* Deletes the config counter from the database. Also notifies the appropriate model listeners.
	*
	* @param configCounter the config counter
	* @return the config counter that was removed
	*/
	public static org.opencps.dossiermgt.model.ConfigCounter deleteConfigCounter(
		org.opencps.dossiermgt.model.ConfigCounter configCounter) {
		return getService().deleteConfigCounter(configCounter);
	}

	/**
	* Deletes the config counter with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param configCounterId the primary key of the config counter
	* @return the config counter that was removed
	* @throws PortalException if a config counter with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.ConfigCounter deleteConfigCounter(
		long configCounterId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteConfigCounter(configCounterId);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.dossiermgt.model.ConfigCounter fetchByCountrCode(
		long groupId, String counterCode) {
		return getService().fetchByCountrCode(groupId, counterCode);
	}

	public static org.opencps.dossiermgt.model.ConfigCounter fetchConfigCounter(
		long configCounterId) {
		return getService().fetchConfigCounter(configCounterId);
	}

	/**
	* Returns the config counter matching the UUID and group.
	*
	* @param uuid the config counter's UUID
	* @param groupId the primary key of the group
	* @return the matching config counter, or <code>null</code> if a matching config counter could not be found
	*/
	public static org.opencps.dossiermgt.model.ConfigCounter fetchConfigCounterByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchConfigCounterByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the config counter with the primary key.
	*
	* @param configCounterId the primary key of the config counter
	* @return the config counter
	* @throws PortalException if a config counter with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.ConfigCounter getConfigCounter(
		long configCounterId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getConfigCounter(configCounterId);
	}

	/**
	* Returns the config counter matching the UUID and group.
	*
	* @param uuid the config counter's UUID
	* @param groupId the primary key of the group
	* @return the matching config counter
	* @throws PortalException if a matching config counter could not be found
	*/
	public static org.opencps.dossiermgt.model.ConfigCounter getConfigCounterByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getConfigCounterByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the config counters.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.ConfigCounterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of config counters
	* @param end the upper bound of the range of config counters (not inclusive)
	* @return the range of config counters
	*/
	public static java.util.List<org.opencps.dossiermgt.model.ConfigCounter> getConfigCounters(
		int start, int end) {
		return getService().getConfigCounters(start, end);
	}

	/**
	* Returns all the config counters matching the UUID and company.
	*
	* @param uuid the UUID of the config counters
	* @param companyId the primary key of the company
	* @return the matching config counters, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.dossiermgt.model.ConfigCounter> getConfigCountersByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getConfigCountersByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of config counters matching the UUID and company.
	*
	* @param uuid the UUID of the config counters
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of config counters
	* @param end the upper bound of the range of config counters (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching config counters, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.dossiermgt.model.ConfigCounter> getConfigCountersByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.ConfigCounter> orderByComparator) {
		return getService()
				   .getConfigCountersByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of config counters.
	*
	* @return the number of config counters
	*/
	public static int getConfigCountersCount() {
		return getService().getConfigCountersCount();
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

	/**
	* Updates the config counter in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param configCounter the config counter
	* @return the config counter that was updated
	*/
	public static org.opencps.dossiermgt.model.ConfigCounter updateConfigCounter(
		org.opencps.dossiermgt.model.ConfigCounter configCounter) {
		return getService().updateConfigCounter(configCounter);
	}

	public static ConfigCounterLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ConfigCounterLocalService, ConfigCounterLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ConfigCounterLocalService.class);

		ServiceTracker<ConfigCounterLocalService, ConfigCounterLocalService> serviceTracker =
			new ServiceTracker<ConfigCounterLocalService, ConfigCounterLocalService>(bundle.getBundleContext(),
				ConfigCounterLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}