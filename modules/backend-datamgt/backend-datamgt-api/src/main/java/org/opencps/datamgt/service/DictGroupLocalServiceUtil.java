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
 * Provides the local service utility for DictGroup. This utility wraps
 * {@link org.opencps.datamgt.service.impl.DictGroupLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author khoavu
 * @see DictGroupLocalService
 * @see org.opencps.datamgt.service.base.DictGroupLocalServiceBaseImpl
 * @see org.opencps.datamgt.service.impl.DictGroupLocalServiceImpl
 * @generated
 */
@ProviderType
public class DictGroupLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.datamgt.service.impl.DictGroupLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dict group to the database. Also notifies the appropriate model listeners.
	*
	* @param dictGroup the dict group
	* @return the dict group that was added
	*/
	public static org.opencps.datamgt.model.DictGroup addDictGroup(
		org.opencps.datamgt.model.DictGroup dictGroup) {
		return getService().addDictGroup(dictGroup);
	}

	/**
	* @author binhth
	* @param userId
	* @param groupId
	* @param dictCollectionId
	* @param groupCode
	* @param groupName
	* @param groupNameEN
	* @param groupDescription
	* @param serviceContext
	* @return DictGroup
	* @throws DuplicateCategoryException
	* @throws UnauthenticationException
	* @throws UnauthorizationException
	* @throws NoSuchUserException
	*/
	public static org.opencps.datamgt.model.DictGroup addDictGroup(
		long userId, long groupId, long dictCollectionId, String groupCode,
		String groupName, String groupNameEN, String groupDescription,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.asset.kernel.exception.DuplicateCategoryException,
			org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			com.liferay.portal.kernel.exception.NoSuchUserException {
		return getService()
				   .addDictGroup(userId, groupId, dictCollectionId, groupCode,
			groupName, groupNameEN, groupDescription, serviceContext);
	}

	public static org.opencps.datamgt.model.DictGroup adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return getService().adminProcessData(objectData);
	}

	public static org.opencps.datamgt.model.DictGroup adminProcessDelete(
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
	* Creates a new dict group with the primary key. Does not add the dict group to the database.
	*
	* @param dictGroupId the primary key for the new dict group
	* @return the new dict group
	*/
	public static org.opencps.datamgt.model.DictGroup createDictGroup(
		long dictGroupId) {
		return getService().createDictGroup(dictGroupId);
	}

	/**
	* Deletes the dict group from the database. Also notifies the appropriate model listeners.
	*
	* @param dictGroup the dict group
	* @return the dict group that was removed
	*/
	public static org.opencps.datamgt.model.DictGroup deleteDictGroup(
		org.opencps.datamgt.model.DictGroup dictGroup) {
		return getService().deleteDictGroup(dictGroup);
	}

	/**
	* Deletes the dict group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dictGroupId the primary key of the dict group
	* @return the dict group that was removed
	* @throws PortalException if a dict group with the primary key could not be found
	*/
	public static org.opencps.datamgt.model.DictGroup deleteDictGroup(
		long dictGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteDictGroup(dictGroupId);
	}

	/**
	* @author binhth
	* @param dictGroupId
	* @param serviceContext
	*/
	public static org.opencps.datamgt.model.DictGroup deleteDictGroup(
		long dictGroupId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			org.opencps.auth.api.exception.NotFoundException,
			org.opencps.auth.api.exception.DataInUsedException {
		return getService().deleteDictGroup(dictGroupId, serviceContext);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.DictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.DictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* @param groupCode
	* @param groupId
	* @return DictGroup
	*/
	public static org.opencps.datamgt.model.DictGroup fetchByF_DictGroupCode(
		String groupCode, long groupId) {
		return getService().fetchByF_DictGroupCode(groupCode, groupId);
	}

	public static org.opencps.datamgt.model.DictGroup fetchDictGroup(
		long dictGroupId) {
		return getService().fetchDictGroup(dictGroupId);
	}

	/**
	* Returns the dict group matching the UUID and group.
	*
	* @param uuid the dict group's UUID
	* @param groupId the primary key of the group
	* @return the matching dict group, or <code>null</code> if a matching dict group could not be found
	*/
	public static org.opencps.datamgt.model.DictGroup fetchDictGroupByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchDictGroupByUuidAndGroupId(uuid, groupId);
	}

	public static java.util.List<org.opencps.datamgt.model.DictGroup> findOlderThanDate(
		java.util.Date date, long groupId, int start, int end) {
		return getService().findOlderThanDate(date, groupId, start, end);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static org.opencps.datamgt.model.DictGroup getByGC_GI_DCI(
		String groupCode, long groupId, long dictCollectionId) {
		return getService().getByGC_GI_DCI(groupCode, groupId, dictCollectionId);
	}

	/**
	* Returns the dict group with the primary key.
	*
	* @param dictGroupId the primary key of the dict group
	* @return the dict group
	* @throws PortalException if a dict group with the primary key could not be found
	*/
	public static org.opencps.datamgt.model.DictGroup getDictGroup(
		long dictGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDictGroup(dictGroupId);
	}

	public static java.util.List<org.opencps.datamgt.model.DictGroup> getDictGroupByDictCollection(
		long groupId, long dictCollectionId, int start, int end) {
		return getService()
				   .getDictGroupByDictCollection(groupId, dictCollectionId,
			start, end);
	}

	/**
	* Returns the dict group matching the UUID and group.
	*
	* @param uuid the dict group's UUID
	* @param groupId the primary key of the group
	* @return the matching dict group
	* @throws PortalException if a matching dict group could not be found
	*/
	public static org.opencps.datamgt.model.DictGroup getDictGroupByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDictGroupByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the dict groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.DictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict groups
	* @param end the upper bound of the range of dict groups (not inclusive)
	* @return the range of dict groups
	*/
	public static java.util.List<org.opencps.datamgt.model.DictGroup> getDictGroups(
		int start, int end) {
		return getService().getDictGroups(start, end);
	}

	/**
	* Returns all the dict groups matching the UUID and company.
	*
	* @param uuid the UUID of the dict groups
	* @param companyId the primary key of the company
	* @return the matching dict groups, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.datamgt.model.DictGroup> getDictGroupsByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getDictGroupsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of dict groups matching the UUID and company.
	*
	* @param uuid the UUID of the dict groups
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of dict groups
	* @param end the upper bound of the range of dict groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching dict groups, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.datamgt.model.DictGroup> getDictGroupsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.datamgt.model.DictGroup> orderByComparator) {
		return getService()
				   .getDictGroupsByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of dict groups.
	*
	* @return the number of dict groups
	*/
	public static int getDictGroupsCount() {
		return getService().getDictGroupsCount();
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
	* @author binhth
	* @param params

	<pre>
	<ol>
	<li> keywords </li>
	<li> groupId </li>
	<li> userId </li>
	<li> collectionCode </li>
	</ol>
	</pre>
	* @param sorts
	* @param start
	* @param end
	* @param searchContext
	* @throws ParseException,
	SearchException
	*/
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
	* Updates the dict group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dictGroup the dict group
	* @return the dict group that was updated
	*/
	public static org.opencps.datamgt.model.DictGroup updateDictGroup(
		org.opencps.datamgt.model.DictGroup dictGroup) {
		return getService().updateDictGroup(dictGroup);
	}

	/**
	* @author binhth
	* @param userId
	* @param dictGroupId
	* @param dictCollectionId
	* @param groupCode
	* @param groupName
	* @param groupNameEN
	* @param groupDescription
	* @param serviceContext
	* @return DictGroup
	* @throws DuplicateCategoryException
	* @throws UnauthenticationException
	* @throws UnauthorizationException
	* @throws NoSuchUserException
	*/
	public static org.opencps.datamgt.model.DictGroup updateDictGroup(
		long userId, long dictGroupId, long dictCollectionId, String groupCode,
		String groupName, String groupNameEN, String groupDescription,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			org.opencps.auth.api.exception.NotFoundException,
			com.liferay.portal.kernel.exception.NoSuchUserException,
			com.liferay.asset.kernel.exception.DuplicateCategoryException {
		return getService()
				   .updateDictGroup(userId, dictGroupId, dictCollectionId,
			groupCode, groupName, groupNameEN, groupDescription, serviceContext);
	}

	public static org.opencps.datamgt.model.DictGroup updateDictGroupDB(
		long userId, long groupId, long dictCollectionId, String groupCode,
		String groupName, String groupNameEN, String groupDescription,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.NoSuchUserException {
		return getService()
				   .updateDictGroupDB(userId, groupId, dictCollectionId,
			groupCode, groupName, groupNameEN, groupDescription, serviceContext);
	}

	public static DictGroupLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DictGroupLocalService, DictGroupLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DictGroupLocalService.class);

		ServiceTracker<DictGroupLocalService, DictGroupLocalService> serviceTracker =
			new ServiceTracker<DictGroupLocalService, DictGroupLocalService>(bundle.getBundleContext(),
				DictGroupLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}