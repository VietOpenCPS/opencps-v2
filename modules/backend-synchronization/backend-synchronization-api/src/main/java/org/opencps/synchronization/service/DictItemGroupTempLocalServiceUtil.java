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
 * Provides the local service utility for DictItemGroupTemp. This utility wraps
 * {@link org.opencps.synchronization.service.impl.DictItemGroupTempLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author trungdk
 * @see DictItemGroupTempLocalService
 * @see org.opencps.synchronization.service.base.DictItemGroupTempLocalServiceBaseImpl
 * @see org.opencps.synchronization.service.impl.DictItemGroupTempLocalServiceImpl
 * @generated
 */
@ProviderType
public class DictItemGroupTempLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.synchronization.service.impl.DictItemGroupTempLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dict item group temp to the database. Also notifies the appropriate model listeners.
	*
	* @param dictItemGroupTemp the dict item group temp
	* @return the dict item group temp that was added
	*/
	public static org.opencps.synchronization.model.DictItemGroupTemp addDictItemGroupTemp(
		org.opencps.synchronization.model.DictItemGroupTemp dictItemGroupTemp) {
		return getService().addDictItemGroupTemp(dictItemGroupTemp);
	}

	/**
	* @author binhth
	* @param userId
	* @param groupId
	* @param DictItemGroupId
	* @param dictItemId
	* @param serviceContext
	* @return DictItemGroupTemp
	* @throws DuplicateCategoryException
	* @throws UnauthenticationException
	* @throws UnauthorizationException
	* @throws NoSuchUserException
	*/
	public static org.opencps.synchronization.model.DictItemGroupTemp addDictItemGroupTemp(
		long userId, long groupId, long dictGroupId, long dictItemId,
		String groupCode,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.asset.kernel.exception.DuplicateCategoryException,
			org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			com.liferay.portal.kernel.exception.NoSuchUserException {
		return getService()
				   .addDictItemGroupTemp(userId, groupId, dictGroupId,
			dictItemId, groupCode, serviceContext);
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
	* Creates a new dict item group temp with the primary key. Does not add the dict item group temp to the database.
	*
	* @param dictItemGroupId the primary key for the new dict item group temp
	* @return the new dict item group temp
	*/
	public static org.opencps.synchronization.model.DictItemGroupTemp createDictItemGroupTemp(
		long dictItemGroupId) {
		return getService().createDictItemGroupTemp(dictItemGroupId);
	}

	/**
	* Deletes the dict item group temp from the database. Also notifies the appropriate model listeners.
	*
	* @param dictItemGroupTemp the dict item group temp
	* @return the dict item group temp that was removed
	*/
	public static org.opencps.synchronization.model.DictItemGroupTemp deleteDictItemGroupTemp(
		org.opencps.synchronization.model.DictItemGroupTemp dictItemGroupTemp) {
		return getService().deleteDictItemGroupTemp(dictItemGroupTemp);
	}

	/**
	* Deletes the dict item group temp with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dictItemGroupId the primary key of the dict item group temp
	* @return the dict item group temp that was removed
	* @throws PortalException if a dict item group temp with the primary key could not be found
	*/
	public static org.opencps.synchronization.model.DictItemGroupTemp deleteDictItemGroupTemp(
		long dictItemGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteDictItemGroupTemp(dictItemGroupId);
	}

	/**
	* @author binhth
	* @param dictItemGroupId
	* @param serviceContext
	* @throws UnauthenticationException,
	UnauthorizationException, NotFoundException
	*/
	public static org.opencps.synchronization.model.DictItemGroupTemp deleteDictItemGroupTemp(
		long dictItemGroupId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			org.opencps.auth.api.exception.NotFoundException {
		return getService()
				   .deleteDictItemGroupTemp(dictItemGroupId, serviceContext);
	}

	public static org.opencps.synchronization.model.DictItemGroupTemp deleteDictItemGroupTempNoneAuthen(
		long dictItemGroupId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			org.opencps.auth.api.exception.NotFoundException {
		return getService()
				   .deleteDictItemGroupTempNoneAuthen(dictItemGroupId,
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synchronization.model.impl.DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synchronization.model.impl.DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* @return DictItemGroupTemp
	*/
	public static org.opencps.synchronization.model.DictItemGroupTemp fetchByF_dictItemId_dictGroupId(
		long groupId, long dictGroupId, long dictItemId) {
		return getService()
				   .fetchByF_dictItemId_dictGroupId(groupId, dictGroupId,
			dictItemId);
	}

	public static org.opencps.synchronization.model.DictItemGroupTemp fetchDictItemGroupTemp(
		long dictItemGroupId) {
		return getService().fetchDictItemGroupTemp(dictItemGroupId);
	}

	/**
	* Returns the dict item group temp matching the UUID and group.
	*
	* @param uuid the dict item group temp's UUID
	* @param groupId the primary key of the group
	* @return the matching dict item group temp, or <code>null</code> if a matching dict item group temp could not be found
	*/
	public static org.opencps.synchronization.model.DictItemGroupTemp fetchDictItemGroupTempByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchDictItemGroupTempByUuidAndGroupId(uuid, groupId);
	}

	public static java.util.List<org.opencps.synchronization.model.DictItemGroupTemp> findByDictGroupId(
		long groupId, long dictGroupId) {
		return getService().findByDictGroupId(groupId, dictGroupId);
	}

	public static java.util.List<org.opencps.synchronization.model.DictItemGroupTemp> findByDictItemTemp(
		long groupId, long dictItemId, int start, int end) {
		return getService().findByDictItemTemp(groupId, dictItemId, start, end);
	}

	/**
	* @author binhth
	* @param groupId
	* @param dictGroupId
	* @param dictItemId
	* @return List<DictItemGroupTemp>
	*/
	public static java.util.List<org.opencps.synchronization.model.DictItemGroupTemp> findByF_dictItemId(
		long groupId, long dictItemId) {
		return getService().findByF_dictItemId(groupId, dictItemId);
	}

	public static java.util.List<org.opencps.synchronization.model.DictItemGroupTemp> findOlderThanDate(
		java.util.Date date, long groupId, int start, int end) {
		return getService().findOlderThanDate(date, groupId, start, end);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the dict item group temp with the primary key.
	*
	* @param dictItemGroupId the primary key of the dict item group temp
	* @return the dict item group temp
	* @throws PortalException if a dict item group temp with the primary key could not be found
	*/
	public static org.opencps.synchronization.model.DictItemGroupTemp getDictItemGroupTemp(
		long dictItemGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDictItemGroupTemp(dictItemGroupId);
	}

	/**
	* Returns the dict item group temp matching the UUID and group.
	*
	* @param uuid the dict item group temp's UUID
	* @param groupId the primary key of the group
	* @return the matching dict item group temp
	* @throws PortalException if a matching dict item group temp could not be found
	*/
	public static org.opencps.synchronization.model.DictItemGroupTemp getDictItemGroupTempByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDictItemGroupTempByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the dict item group temps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synchronization.model.impl.DictItemGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict item group temps
	* @param end the upper bound of the range of dict item group temps (not inclusive)
	* @return the range of dict item group temps
	*/
	public static java.util.List<org.opencps.synchronization.model.DictItemGroupTemp> getDictItemGroupTemps(
		int start, int end) {
		return getService().getDictItemGroupTemps(start, end);
	}

	/**
	* Returns all the dict item group temps matching the UUID and company.
	*
	* @param uuid the UUID of the dict item group temps
	* @param companyId the primary key of the company
	* @return the matching dict item group temps, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.synchronization.model.DictItemGroupTemp> getDictItemGroupTempsByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService()
				   .getDictItemGroupTempsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of dict item group temps matching the UUID and company.
	*
	* @param uuid the UUID of the dict item group temps
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of dict item group temps
	* @param end the upper bound of the range of dict item group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching dict item group temps, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.synchronization.model.DictItemGroupTemp> getDictItemGroupTempsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.synchronization.model.DictItemGroupTemp> orderByComparator) {
		return getService()
				   .getDictItemGroupTempsByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of dict item group temps.
	*
	* @return the number of dict item group temps
	*/
	public static int getDictItemGroupTempsCount() {
		return getService().getDictItemGroupTempsCount();
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
	* Updates the dict item group temp in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dictItemGroupTemp the dict item group temp
	* @return the dict item group temp that was updated
	*/
	public static org.opencps.synchronization.model.DictItemGroupTemp updateDictItemGroupTemp(
		org.opencps.synchronization.model.DictItemGroupTemp dictItemGroupTemp) {
		return getService().updateDictItemGroupTemp(dictItemGroupTemp);
	}

	/**
	* @author binhth
	* @param userId
	* @param dictItemGroupId
	* @param dictItemId
	* @param serviceContext
	* @return DictItemGroupTemp
	* @throws DuplicateCategoryException
	* @throws UnauthenticationException
	* @throws UnauthorizationException
	* @throws NoSuchUserException
	*/
	public static org.opencps.synchronization.model.DictItemGroupTemp updateDictItemGroupTemp(
		long userId, long dictItemGroupId, long dictItemId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			org.opencps.auth.api.exception.NotFoundException,
			com.liferay.portal.kernel.exception.NoSuchUserException,
			com.liferay.asset.kernel.exception.DuplicateCategoryException {
		return getService()
				   .updateDictItemGroupTemp(userId, dictItemGroupId,
			dictItemId, serviceContext);
	}

	public static DictItemGroupTempLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DictItemGroupTempLocalService, DictItemGroupTempLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DictItemGroupTempLocalService.class);

		ServiceTracker<DictItemGroupTempLocalService, DictItemGroupTempLocalService> serviceTracker =
			new ServiceTracker<DictItemGroupTempLocalService, DictItemGroupTempLocalService>(bundle.getBundleContext(),
				DictItemGroupTempLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}