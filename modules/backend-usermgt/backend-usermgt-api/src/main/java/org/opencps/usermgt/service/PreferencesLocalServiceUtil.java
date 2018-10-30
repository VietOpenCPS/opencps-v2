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

package org.opencps.usermgt.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Preferences. This utility wraps
 * {@link org.opencps.usermgt.service.impl.PreferencesLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author khoavu
 * @see PreferencesLocalService
 * @see org.opencps.usermgt.service.base.PreferencesLocalServiceBaseImpl
 * @see org.opencps.usermgt.service.impl.PreferencesLocalServiceImpl
 * @generated
 */
@ProviderType
public class PreferencesLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.usermgt.service.impl.PreferencesLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.opencps.usermgt.model.Preferences addPreferences(
		long userId, long groupId, String preferencesData,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.asset.kernel.exception.DuplicateCategoryException,
			backend.auth.api.exception.UnauthenticationException,
			backend.auth.api.exception.UnauthorizationException,
			com.liferay.portal.kernel.exception.NoSuchUserException {
		return getService()
				   .addPreferences(userId, groupId, preferencesData,
			serviceContext);
	}

	/**
	* Adds the preferences to the database. Also notifies the appropriate model listeners.
	*
	* @param preferences the preferences
	* @return the preferences that was added
	*/
	public static org.opencps.usermgt.model.Preferences addPreferences(
		org.opencps.usermgt.model.Preferences preferences) {
		return getService().addPreferences(preferences);
	}

	public static org.opencps.usermgt.model.Preferences adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return getService().adminProcessData(objectData);
	}

	public static org.opencps.usermgt.model.Preferences adminProcessDelete(
		Long id) {
		return getService().adminProcessDelete(id);
	}

	/**
	* Creates a new preferences with the primary key. Does not add the preferences to the database.
	*
	* @param preferencesId the primary key for the new preferences
	* @return the new preferences
	*/
	public static org.opencps.usermgt.model.Preferences createPreferences(
		long preferencesId) {
		return getService().createPreferences(preferencesId);
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
	* Deletes the preferences with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param preferencesId the primary key of the preferences
	* @return the preferences that was removed
	* @throws PortalException if a preferences with the primary key could not be found
	*/
	public static org.opencps.usermgt.model.Preferences deletePreferences(
		long preferencesId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePreferences(preferencesId);
	}

	public static org.opencps.usermgt.model.Preferences deletePreferences(
		long preferencesId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws backend.auth.api.exception.UnauthenticationException,
			backend.auth.api.exception.UnauthorizationException,
			backend.auth.api.exception.NotFoundException {
		return getService().deletePreferences(preferencesId, serviceContext);
	}

	/**
	* Deletes the preferences from the database. Also notifies the appropriate model listeners.
	*
	* @param preferences the preferences
	* @return the preferences that was removed
	*/
	public static org.opencps.usermgt.model.Preferences deletePreferences(
		org.opencps.usermgt.model.Preferences preferences) {
		return getService().deletePreferences(preferences);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.PreferencesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.PreferencesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.usermgt.model.Preferences fetchByF_userId(
		long groupId, long userId) {
		return getService().fetchByF_userId(groupId, userId);
	}

	public static org.opencps.usermgt.model.Preferences fetchPreferences(
		long preferencesId) {
		return getService().fetchPreferences(preferencesId);
	}

	/**
	* Returns the preferences matching the UUID and group.
	*
	* @param uuid the preferences's UUID
	* @param groupId the primary key of the group
	* @return the matching preferences, or <code>null</code> if a matching preferences could not be found
	*/
	public static org.opencps.usermgt.model.Preferences fetchPreferencesByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchPreferencesByUuidAndGroupId(uuid, groupId);
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
	* Returns the preferences with the primary key.
	*
	* @param preferencesId the primary key of the preferences
	* @return the preferences
	* @throws PortalException if a preferences with the primary key could not be found
	*/
	public static org.opencps.usermgt.model.Preferences getPreferences(
		long preferencesId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPreferences(preferencesId);
	}

	/**
	* Returns the preferences matching the UUID and group.
	*
	* @param uuid the preferences's UUID
	* @param groupId the primary key of the group
	* @return the matching preferences
	* @throws PortalException if a matching preferences could not be found
	*/
	public static org.opencps.usermgt.model.Preferences getPreferencesByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPreferencesByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the preferenceses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.PreferencesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of preferenceses
	* @param end the upper bound of the range of preferenceses (not inclusive)
	* @return the range of preferenceses
	*/
	public static java.util.List<org.opencps.usermgt.model.Preferences> getPreferenceses(
		int start, int end) {
		return getService().getPreferenceses(start, end);
	}

	/**
	* Returns all the preferenceses matching the UUID and company.
	*
	* @param uuid the UUID of the preferenceses
	* @param companyId the primary key of the company
	* @return the matching preferenceses, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.usermgt.model.Preferences> getPreferencesesByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getPreferencesesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of preferenceses matching the UUID and company.
	*
	* @param uuid the UUID of the preferenceses
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of preferenceses
	* @param end the upper bound of the range of preferenceses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching preferenceses, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.usermgt.model.Preferences> getPreferencesesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.usermgt.model.Preferences> orderByComparator) {
		return getService()
				   .getPreferencesesByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of preferenceses.
	*
	* @return the number of preferenceses
	*/
	public static int getPreferencesesCount() {
		return getService().getPreferencesesCount();
	}

	public static org.opencps.usermgt.model.Preferences updatePreferences(
		long userId, long preferencesId, String preferencesData,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.asset.kernel.exception.DuplicateCategoryException,
			backend.auth.api.exception.UnauthenticationException,
			backend.auth.api.exception.UnauthorizationException,
			com.liferay.portal.kernel.exception.NoSuchUserException,
			backend.auth.api.exception.NotFoundException {
		return getService()
				   .updatePreferences(userId, preferencesId, preferencesData,
			serviceContext);
	}

	/**
	* Updates the preferences in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param preferences the preferences
	* @return the preferences that was updated
	*/
	public static org.opencps.usermgt.model.Preferences updatePreferences(
		org.opencps.usermgt.model.Preferences preferences) {
		return getService().updatePreferences(preferences);
	}

	public static PreferencesLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PreferencesLocalService, PreferencesLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(PreferencesLocalService.class);

		ServiceTracker<PreferencesLocalService, PreferencesLocalService> serviceTracker =
			new ServiceTracker<PreferencesLocalService, PreferencesLocalService>(bundle.getBundleContext(),
				PreferencesLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}