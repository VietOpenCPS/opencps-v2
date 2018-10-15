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

package org.opencps.synchronization.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for DictItemTemp. This utility wraps
 * {@link org.opencps.synchronization.service.impl.DictItemTempLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author trungdk
 * @see DictItemTempLocalService
 * @see org.opencps.synchronization.service.base.DictItemTempLocalServiceBaseImpl
 * @see org.opencps.synchronization.service.impl.DictItemTempLocalServiceImpl
 * @generated
 */
@ProviderType
public class DictItemTempLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.synchronization.service.impl.DictItemTempLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dict item temp to the database. Also notifies the appropriate model listeners.
	*
	* @param dictItemTemp the dict item temp
	* @return the dict item temp that was added
	*/
	public static org.opencps.synchronization.model.DictItemTemp addDictItemTemp(
		org.opencps.synchronization.model.DictItemTemp dictItemTemp) {
		return getService().addDictItemTemp(dictItemTemp);
	}

	public static org.opencps.synchronization.model.DictItemTemp addDictItemTemp(
		long userId, long groupId, long dictCollectionId, String itemCode,
		String itemName, String itemNameEN, String itemDescription,
		long parentItemId, String sibling, int level, String metaData,
		int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.asset.kernel.exception.DuplicateCategoryException,
			org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			com.liferay.portal.kernel.exception.NoSuchUserException,
			org.opencps.synchronization.exception.NoSuchDictItemTempException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addDictItemTemp(userId, groupId, dictCollectionId,
			itemCode, itemName, itemNameEN, itemDescription, parentItemId,
			sibling, level, metaData, status, serviceContext);
	}

	public static long countByOlderThanDate(java.util.Date date, long groupId) {
		return getService().countByOlderThanDate(date, groupId);
	}

	public static long countLuceneSearchEngine(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return getService().countLuceneSearchEngine(params, searchContext);
	}

	/**
	* Creates a new dict item temp with the primary key. Does not add the dict item temp to the database.
	*
	* @param dictItemId the primary key for the new dict item temp
	* @return the new dict item temp
	*/
	public static org.opencps.synchronization.model.DictItemTemp createDictItemTemp(
		long dictItemId) {
		return getService().createDictItemTemp(dictItemId);
	}

	/**
	* Deletes the dict item temp from the database. Also notifies the appropriate model listeners.
	*
	* @param dictItemTemp the dict item temp
	* @return the dict item temp that was removed
	*/
	public static org.opencps.synchronization.model.DictItemTemp deleteDictItemTemp(
		org.opencps.synchronization.model.DictItemTemp dictItemTemp) {
		return getService().deleteDictItemTemp(dictItemTemp);
	}

	/**
	* Deletes the dict item temp with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dictItemId the primary key of the dict item temp
	* @return the dict item temp that was removed
	* @throws PortalException if a dict item temp with the primary key could not be found
	*/
	public static org.opencps.synchronization.model.DictItemTemp deleteDictItemTemp(
		long dictItemId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteDictItemTemp(dictItemId);
	}

	public static org.opencps.synchronization.model.DictItemTemp deleteDictItemTemp(
		long dictItemId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			org.opencps.auth.api.exception.NotFoundException {
		return getService().deleteDictItemTemp(dictItemId, serviceContext);
	}

	public static void deleteDictItemTemp(long groupId, String itemCode,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.asset.kernel.exception.DuplicateCategoryException,
			org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			com.liferay.portal.kernel.exception.NoSuchUserException,
			org.opencps.auth.api.exception.NotFoundException,
			com.liferay.portal.kernel.exception.PortalException {
		getService().deleteDictItemTemp(groupId, itemCode, serviceContext);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synchronization.model.impl.DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synchronization.model.impl.DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.synchronization.model.DictItemTemp fetchByF_dictItemCode(
		String itemCode, long dictCollectionId, long groupId) {
		return getService()
				   .fetchByF_dictItemCode(itemCode, dictCollectionId, groupId);
	}

	public static org.opencps.synchronization.model.DictItemTemp fetchDictItemTemp(
		long dictItemId) {
		return getService().fetchDictItemTemp(dictItemId);
	}

	/**
	* Returns the dict item temp matching the UUID and group.
	*
	* @param uuid the dict item temp's UUID
	* @param groupId the primary key of the group
	* @return the matching dict item temp, or <code>null</code> if a matching dict item temp could not be found
	*/
	public static org.opencps.synchronization.model.DictItemTemp fetchDictItemTempByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchDictItemTempByUuidAndGroupId(uuid, groupId);
	}

	public static java.util.List<org.opencps.synchronization.model.DictItemTemp> findByF_dictCollectionId(
		long dictCollectionId) {
		return getService().findByF_dictCollectionId(dictCollectionId);
	}

	public static java.util.List<org.opencps.synchronization.model.DictItemTemp> findByF_dictCollectionId(
		long dictCollectionId, int stat, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.synchronization.model.DictItemTemp> comparator) {
		return getService()
				   .findByF_dictCollectionId(dictCollectionId, stat, end,
			comparator);
	}

	public static java.util.List<org.opencps.synchronization.model.DictItemTemp> findByF_dictCollectionId_parentItemId(
		long dictCollectionId, long parentItemId) {
		return getService()
				   .findByF_dictCollectionId_parentItemId(dictCollectionId,
			parentItemId);
	}

	/**
	* @param parentItemId
	* @return
	*/
	public static java.util.List<org.opencps.synchronization.model.DictItemTemp> findByF_parentItemId(
		long parentItemId) {
		return getService().findByF_parentItemId(parentItemId);
	}

	public static java.util.List<org.opencps.synchronization.model.DictItemTemp> findByF_treeIndex(
		long dictCollectionId, long parentItemId, String treeIndex,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.synchronization.model.DictItemTemp> comparator) {
		return getService()
				   .findByF_treeIndex(dictCollectionId, parentItemId,
			treeIndex, comparator);
	}

	public static java.util.List<org.opencps.synchronization.model.DictItemTemp> findByOlderThanDate(
		java.util.Date date, long groupId, int start, int end) {
		return getService().findByOlderThanDate(date, groupId, start, end);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the dict item temp with the primary key.
	*
	* @param dictItemId the primary key of the dict item temp
	* @return the dict item temp
	* @throws PortalException if a dict item temp with the primary key could not be found
	*/
	public static org.opencps.synchronization.model.DictItemTemp getDictItemTemp(
		long dictItemId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDictItemTemp(dictItemId);
	}

	/**
	* Returns the dict item temp matching the UUID and group.
	*
	* @param uuid the dict item temp's UUID
	* @param groupId the primary key of the group
	* @return the matching dict item temp
	* @throws PortalException if a matching dict item temp could not be found
	*/
	public static org.opencps.synchronization.model.DictItemTemp getDictItemTempByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDictItemTempByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the dict item temps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synchronization.model.impl.DictItemTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict item temps
	* @param end the upper bound of the range of dict item temps (not inclusive)
	* @return the range of dict item temps
	*/
	public static java.util.List<org.opencps.synchronization.model.DictItemTemp> getDictItemTemps(
		int start, int end) {
		return getService().getDictItemTemps(start, end);
	}

	/**
	* Returns all the dict item temps matching the UUID and company.
	*
	* @param uuid the UUID of the dict item temps
	* @param companyId the primary key of the company
	* @return the matching dict item temps, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.synchronization.model.DictItemTemp> getDictItemTempsByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getDictItemTempsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of dict item temps matching the UUID and company.
	*
	* @param uuid the UUID of the dict item temps
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of dict item temps
	* @param end the upper bound of the range of dict item temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching dict item temps, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.synchronization.model.DictItemTemp> getDictItemTempsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.synchronization.model.DictItemTemp> orderByComparator) {
		return getService()
				   .getDictItemTempsByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of dict item temps.
	*
	* @return the number of dict item temps
	*/
	public static int getDictItemTempsCount() {
		return getService().getDictItemTempsCount();
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

	public static boolean initDictItemTemp(long groupId, long dictCollectionId) {
		return getService().initDictItemTemp(groupId, dictCollectionId);
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
	* Updates the dict item temp in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dictItemTemp the dict item temp
	* @return the dict item temp that was updated
	*/
	public static org.opencps.synchronization.model.DictItemTemp updateDictItemTemp(
		org.opencps.synchronization.model.DictItemTemp dictItemTemp) {
		return getService().updateDictItemTemp(dictItemTemp);
	}

	/**
	* @param userId
	* @param dictCollectionId
	* @param fullName
	* @param companyName
	* @param telNo
	* @param email
	* @param mobilinkId
	* @param userMappingId
	* @param outSide
	* @param isOrg
	* @param serviceContext
	* @return
	* @throws Exception
	*/
	public static org.opencps.synchronization.model.DictItemTemp updateDictItemTemp(
		long userId, long dictItemId, long dictCollectionId, String itemCode,
		String itemName, String itemNameEN, String itemDescription,
		long parentItemId, String sibling, int level, String metaData,
		int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.asset.kernel.exception.DuplicateCategoryException,
			org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			com.liferay.portal.kernel.exception.NoSuchUserException,
			org.opencps.auth.api.exception.NotFoundException,
			com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateDictItemTemp(userId, dictItemId, dictCollectionId,
			itemCode, itemName, itemNameEN, itemDescription, parentItemId,
			sibling, level, metaData, status, serviceContext);
	}

	public static org.opencps.synchronization.model.DictItemTemp updateDictItemTempListener(
		long userId, long dictItemId, long dictCollectionId, String itemCode,
		String itemName, String itemNameEN, String itemDescription,
		long parentItemId, String sibling, int level, String metaData,
		int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.asset.kernel.exception.DuplicateCategoryException,
			org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			com.liferay.portal.kernel.exception.NoSuchUserException,
			org.opencps.auth.api.exception.NotFoundException,
			com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateDictItemTempListener(userId, dictItemId,
			dictCollectionId, itemCode, itemName, itemNameEN, itemDescription,
			parentItemId, sibling, level, metaData, status, serviceContext);
	}

	public static DictItemTempLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DictItemTempLocalService, DictItemTempLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DictItemTempLocalService.class);

		ServiceTracker<DictItemTempLocalService, DictItemTempLocalService> serviceTracker =
			new ServiceTracker<DictItemTempLocalService, DictItemTempLocalService>(bundle.getBundleContext(),
				DictItemTempLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}