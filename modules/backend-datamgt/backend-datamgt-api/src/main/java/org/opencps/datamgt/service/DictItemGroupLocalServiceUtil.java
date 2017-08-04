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

package org.opencps.datamgt.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for DictItemGroup. This utility wraps
 * {@link org.mobilink.backend.datamgt.service.impl.DictItemGroupLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Binhth
 * @see DictItemGroupLocalService
 * @see org.mobilink.backend.datamgt.service.base.DictItemGroupLocalServiceBaseImpl
 * @see org.mobilink.backend.datamgt.service.impl.DictItemGroupLocalServiceImpl
 * @generated
 */
@ProviderType
public class DictItemGroupLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.mobilink.backend.datamgt.service.impl.DictItemGroupLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
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
	* Returns the number of dict item groups.
	*
	* @return the number of dict item groups
	*/
	public static int getDictItemGroupsCount() {
		return getService().getDictItemGroupsCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.datamgt.model.impl.DictItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.datamgt.model.impl.DictItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the dict item groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.datamgt.model.impl.DictItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict item groups
	* @param end the upper bound of the range of dict item groups (not inclusive)
	* @return the range of dict item groups
	*/
	public static java.util.List<org.opencps.datamgt.model.DictItemGroup> getDictItemGroups(
		int start, int end) {
		return getService().getDictItemGroups(start, end);
	}

	/**
	* Returns all the dict item groups matching the UUID and company.
	*
	* @param uuid the UUID of the dict item groups
	* @param companyId the primary key of the company
	* @return the matching dict item groups, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.datamgt.model.DictItemGroup> getDictItemGroupsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getDictItemGroupsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of dict item groups matching the UUID and company.
	*
	* @param uuid the UUID of the dict item groups
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of dict item groups
	* @param end the upper bound of the range of dict item groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching dict item groups, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.datamgt.model.DictItemGroup> getDictItemGroupsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.datamgt.model.DictItemGroup> orderByComparator) {
		return getService()
				   .getDictItemGroupsByUuidAndCompanyId(uuid, companyId, start,
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
	* Adds the dict item group to the database. Also notifies the appropriate model listeners.
	*
	* @param dictItemGroup the dict item group
	* @return the dict item group that was added
	*/
	public static org.opencps.datamgt.model.DictItemGroup addDictItemGroup(
		org.opencps.datamgt.model.DictItemGroup dictItemGroup) {
		return getService().addDictItemGroup(dictItemGroup);
	}

	/**
	* Creates a new dict item group with the primary key. Does not add the dict item group to the database.
	*
	* @param dictItemGroupId the primary key for the new dict item group
	* @return the new dict item group
	*/
	public static org.opencps.datamgt.model.DictItemGroup createDictItemGroup(
		long dictItemGroupId) {
		return getService().createDictItemGroup(dictItemGroupId);
	}

	/**
	* Deletes the dict item group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dictItemGroupId the primary key of the dict item group
	* @return the dict item group that was removed
	* @throws PortalException if a dict item group with the primary key could not be found
	*/
	public static org.opencps.datamgt.model.DictItemGroup deleteDictItemGroup(
		long dictItemGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteDictItemGroup(dictItemGroupId);
	}

	/**
	* Deletes the dict item group from the database. Also notifies the appropriate model listeners.
	*
	* @param dictItemGroup the dict item group
	* @return the dict item group that was removed
	*/
	public static org.opencps.datamgt.model.DictItemGroup deleteDictItemGroup(
		org.opencps.datamgt.model.DictItemGroup dictItemGroup) {
		return getService().deleteDictItemGroup(dictItemGroup);
	}

	public static org.opencps.datamgt.model.DictItemGroup fetchDictItemGroup(
		long dictItemGroupId) {
		return getService().fetchDictItemGroup(dictItemGroupId);
	}

	/**
	* Returns the dict item group matching the UUID and group.
	*
	* @param uuid the dict item group's UUID
	* @param groupId the primary key of the group
	* @return the matching dict item group, or <code>null</code> if a matching dict item group could not be found
	*/
	public static org.opencps.datamgt.model.DictItemGroup fetchDictItemGroupByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchDictItemGroupByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the dict item group with the primary key.
	*
	* @param dictItemGroupId the primary key of the dict item group
	* @return the dict item group
	* @throws PortalException if a dict item group with the primary key could not be found
	*/
	public static org.opencps.datamgt.model.DictItemGroup getDictItemGroup(
		long dictItemGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDictItemGroup(dictItemGroupId);
	}

	/**
	* Returns the dict item group matching the UUID and group.
	*
	* @param uuid the dict item group's UUID
	* @param groupId the primary key of the group
	* @return the matching dict item group
	* @throws PortalException if a matching dict item group could not be found
	*/
	public static org.opencps.datamgt.model.DictItemGroup getDictItemGroupByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDictItemGroupByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the dict item group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dictItemGroup the dict item group
	* @return the dict item group that was updated
	*/
	public static org.opencps.datamgt.model.DictItemGroup updateDictItemGroup(
		org.opencps.datamgt.model.DictItemGroup dictItemGroup) {
		return getService().updateDictItemGroup(dictItemGroup);
	}

	public static DictItemGroupLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DictItemGroupLocalService, DictItemGroupLocalService> _serviceTracker =
		ServiceTrackerFactory.open(DictItemGroupLocalService.class);
}