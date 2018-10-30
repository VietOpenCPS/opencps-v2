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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for DictItemGroup. This utility wraps
 * {@link org.opencps.datamgt.service.impl.DictItemGroupLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author khoavu
 * @see DictItemGroupLocalService
 * @see org.opencps.datamgt.service.base.DictItemGroupLocalServiceBaseImpl
 * @see org.opencps.datamgt.service.impl.DictItemGroupLocalServiceImpl
 * @generated
 */
@ProviderType
public class DictItemGroupLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.datamgt.service.impl.DictItemGroupLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

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
	* @author binhth
	* @param userId
	* @param groupId
	* @param DictItemGroupId
	* @param dictItemId
	* @param serviceContext
	* @return DictItemGroup
	* @throws DuplicateCategoryException
	* @throws UnauthenticationException
	* @throws UnauthorizationException
	* @throws NoSuchUserException
	*/
	public static org.opencps.datamgt.model.DictItemGroup addDictItemGroup(
		long userId, long groupId, long dictGroupId, long dictItemId,
		String groupCode,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.asset.kernel.exception.DuplicateCategoryException,
			org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			com.liferay.portal.kernel.exception.NoSuchUserException {
		return getService()
				   .addDictItemGroup(userId, groupId, dictGroupId, dictItemId,
			groupCode, serviceContext);
	}

	public static org.opencps.datamgt.model.DictItemGroup adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return getService().adminProcessData(objectData);
	}

	public static org.opencps.datamgt.model.DictItemGroup adminProcessDelete(
		Long id) {
		return getService().adminProcessDelete(id);
	}

	public static long countLuceneSearchEngine(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return getService().countLuceneSearchEngine(params, searchContext);
	}

	public static long countOlderThanDate(java.util.Date date, long groupId) {
		return getService().countOlderThanDate(date, groupId);
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
	* Deletes the dict item group from the database. Also notifies the appropriate model listeners.
	*
	* @param dictItemGroup the dict item group
	* @return the dict item group that was removed
	*/
	public static org.opencps.datamgt.model.DictItemGroup deleteDictItemGroup(
		org.opencps.datamgt.model.DictItemGroup dictItemGroup) {
		return getService().deleteDictItemGroup(dictItemGroup);
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
	* @author binhth
	* @param dictItemGroupId
	* @param serviceContext
	* @throws UnauthenticationException,
	UnauthorizationException, NotFoundException
	*/
	public static org.opencps.datamgt.model.DictItemGroup deleteDictItemGroup(
		long dictItemGroupId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			org.opencps.auth.api.exception.NotFoundException {
		return getService().deleteDictItemGroup(dictItemGroupId, serviceContext);
	}

	public static org.opencps.datamgt.model.DictItemGroup deleteDictItemGroupNoneAuthen(
		long dictItemGroupId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			org.opencps.auth.api.exception.NotFoundException {
		return getService()
				   .deleteDictItemGroupNoneAuthen(dictItemGroupId,
			serviceContext);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.DictItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.DictItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	/**
	* @author binhth
	* @param groupId
	* @param dictItemId
	* @param DictItemGroupId
	* @return DictItemGroup
	*/
	public static org.opencps.datamgt.model.DictItemGroup fetchByF_dictItemId_dictGroupId(
		long groupId, long dictGroupId, long dictItemId) {
		return getService()
				   .fetchByF_dictItemId_dictGroupId(groupId, dictGroupId,
			dictItemId);
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
		String uuid, long groupId) {
		return getService().fetchDictItemGroupByUuidAndGroupId(uuid, groupId);
	}

	public static java.util.List<org.opencps.datamgt.model.DictItemGroup> findByDictGroupId(
		long groupId, long dictGroupId) {
		return getService().findByDictGroupId(groupId, dictGroupId);
	}

	/**
	* @author binhth
	* @param groupId
	* @param dictGroupId
	* @param dictItemId
	* @return List<DictItemGroup>
	*/
	public static java.util.List<org.opencps.datamgt.model.DictItemGroup> findByF_dictItemId(
		long groupId, long dictItemId) {
		return getService().findByF_dictItemId(groupId, dictItemId);
	}

	public static java.util.List<org.opencps.datamgt.model.DictItemGroup> findOlderThanDate(
		java.util.Date date, long groupId, int start, int end) {
		return getService().findOlderThanDate(date, groupId, start, end);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
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
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDictItemGroupByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the dict item groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.DictItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		String uuid, long companyId) {
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
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.datamgt.model.DictItemGroup> orderByComparator) {
		return getService()
				   .getDictItemGroupsByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of dict item groups.
	*
	* @return the number of dict item groups
	*/
	public static int getDictItemGroupsCount() {
		return getService().getDictItemGroupsCount();
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

	public static com.liferay.portal.kernel.search.Hits luceneSearchEngine(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return getService()
				   .luceneSearchEngine(params, sorts, start, end, searchContext);
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

	/**
	* @author binhth
	* @param userId
	* @param dictItemGroupId
	* @param dictItemId
	* @param serviceContext
	* @return DictItemGroup
	* @throws DuplicateCategoryException
	* @throws UnauthenticationException
	* @throws UnauthorizationException
	* @throws NoSuchUserException
	*/
	public static org.opencps.datamgt.model.DictItemGroup updateDictItemGroup(
		long userId, long dictItemGroupId, long dictItemId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			org.opencps.auth.api.exception.NotFoundException,
			com.liferay.portal.kernel.exception.NoSuchUserException,
			com.liferay.asset.kernel.exception.DuplicateCategoryException {
		return getService()
				   .updateDictItemGroup(userId, dictItemGroupId, dictItemId,
			serviceContext);
	}

	public static DictItemGroupLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DictItemGroupLocalService, DictItemGroupLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DictItemGroupLocalService.class);

		ServiceTracker<DictItemGroupLocalService, DictItemGroupLocalService> serviceTracker =
			new ServiceTracker<DictItemGroupLocalService, DictItemGroupLocalService>(bundle.getBundleContext(),
				DictItemGroupLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}