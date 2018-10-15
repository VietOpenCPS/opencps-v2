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
 * Provides the local service utility for DictCollectionTemp. This utility wraps
 * {@link org.opencps.synchronization.service.impl.DictCollectionTempLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author trungdk
 * @see DictCollectionTempLocalService
 * @see org.opencps.synchronization.service.base.DictCollectionTempLocalServiceBaseImpl
 * @see org.opencps.synchronization.service.impl.DictCollectionTempLocalServiceImpl
 * @generated
 */
@ProviderType
public class DictCollectionTempLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.synchronization.service.impl.DictCollectionTempLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dict collection temp to the database. Also notifies the appropriate model listeners.
	*
	* @param dictCollectionTemp the dict collection temp
	* @return the dict collection temp that was added
	*/
	public static org.opencps.synchronization.model.DictCollectionTemp addDictCollectionTemp(
		org.opencps.synchronization.model.DictCollectionTemp dictCollectionTemp) {
		return getService().addDictCollectionTemp(dictCollectionTemp);
	}

	/**
	* @author binhth
	* @param userId
	* @param groupId
	* @param collectionCode
	* @param collectionName
	* @param collectionNameEN
	* @param description
	* @param serviceContext
	* @return DictCollectionTemp
	* @throws DuplicateCategoryException
	* @throws UnauthenticationException
	* @throws UnauthorizationException
	* @throws NoSuchUserException
	*/
	public static org.opencps.synchronization.model.DictCollectionTemp addDictCollectionTemp(
		long userId, long groupId, String collectionCode,
		String collectionName, String collectionNameEN, String description,
		int status, int mustSync,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.asset.kernel.exception.DuplicateCategoryException,
			org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			com.liferay.portal.kernel.exception.NoSuchUserException {
		return getService()
				   .addDictCollectionTemp(userId, groupId, collectionCode,
			collectionName, collectionNameEN, description, status, mustSync,
			serviceContext);
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
	* Creates a new dict collection temp with the primary key. Does not add the dict collection temp to the database.
	*
	* @param dictCollectionId the primary key for the new dict collection temp
	* @return the new dict collection temp
	*/
	public static org.opencps.synchronization.model.DictCollectionTemp createDictCollectionTemp(
		long dictCollectionId) {
		return getService().createDictCollectionTemp(dictCollectionId);
	}

	/**
	* Deletes the dict collection temp from the database. Also notifies the appropriate model listeners.
	*
	* @param dictCollectionTemp the dict collection temp
	* @return the dict collection temp that was removed
	*/
	public static org.opencps.synchronization.model.DictCollectionTemp deleteDictCollectionTemp(
		org.opencps.synchronization.model.DictCollectionTemp dictCollectionTemp) {
		return getService().deleteDictCollectionTemp(dictCollectionTemp);
	}

	/**
	* Deletes the dict collection temp with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dictCollectionId the primary key of the dict collection temp
	* @return the dict collection temp that was removed
	* @throws PortalException if a dict collection temp with the primary key could not be found
	*/
	public static org.opencps.synchronization.model.DictCollectionTemp deleteDictCollectionTemp(
		long dictCollectionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteDictCollectionTemp(dictCollectionId);
	}

	/**
	* @author binhth
	* @param dictCollectionId
	* @param serviceContext
	*/
	public static org.opencps.synchronization.model.DictCollectionTemp deleteDictCollectionTemp(
		long dictCollectionId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			org.opencps.auth.api.exception.NotFoundException {
		return getService()
				   .deleteDictCollectionTemp(dictCollectionId, serviceContext);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synchronization.model.impl.DictCollectionTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synchronization.model.impl.DictCollectionTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* @param collectionCode
	* @param groupId
	* @return DictCollectionTemp
	*/
	public static org.opencps.synchronization.model.DictCollectionTemp fetchByF_dictCollectionCode(
		String collectionCode, long groupId) {
		return getService().fetchByF_dictCollectionCode(collectionCode, groupId);
	}

	public static org.opencps.synchronization.model.DictCollectionTemp fetchDictCollectionTemp(
		long dictCollectionId) {
		return getService().fetchDictCollectionTemp(dictCollectionId);
	}

	/**
	* Returns the dict collection temp matching the UUID and group.
	*
	* @param uuid the dict collection temp's UUID
	* @param groupId the primary key of the group
	* @return the matching dict collection temp, or <code>null</code> if a matching dict collection temp could not be found
	*/
	public static org.opencps.synchronization.model.DictCollectionTemp fetchDictCollectionTempByUuidAndGroupId(
		String uuid, long groupId) {
		return getService()
				   .fetchDictCollectionTempByUuidAndGroupId(uuid, groupId);
	}

	public static java.util.List<org.opencps.synchronization.model.DictCollectionTemp> findOlderThanDate(
		java.util.Date date, long groupId, int start, int end) {
		return getService().findOlderThanDate(date, groupId, start, end);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the dict collection temp with the primary key.
	*
	* @param dictCollectionId the primary key of the dict collection temp
	* @return the dict collection temp
	* @throws PortalException if a dict collection temp with the primary key could not be found
	*/
	public static org.opencps.synchronization.model.DictCollectionTemp getDictCollectionTemp(
		long dictCollectionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDictCollectionTemp(dictCollectionId);
	}

	/**
	* Returns the dict collection temp matching the UUID and group.
	*
	* @param uuid the dict collection temp's UUID
	* @param groupId the primary key of the group
	* @return the matching dict collection temp
	* @throws PortalException if a matching dict collection temp could not be found
	*/
	public static org.opencps.synchronization.model.DictCollectionTemp getDictCollectionTempByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDictCollectionTempByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the dict collection temps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synchronization.model.impl.DictCollectionTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict collection temps
	* @param end the upper bound of the range of dict collection temps (not inclusive)
	* @return the range of dict collection temps
	*/
	public static java.util.List<org.opencps.synchronization.model.DictCollectionTemp> getDictCollectionTemps(
		int start, int end) {
		return getService().getDictCollectionTemps(start, end);
	}

	/**
	* Returns all the dict collection temps matching the UUID and company.
	*
	* @param uuid the UUID of the dict collection temps
	* @param companyId the primary key of the company
	* @return the matching dict collection temps, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.synchronization.model.DictCollectionTemp> getDictCollectionTempsByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService()
				   .getDictCollectionTempsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of dict collection temps matching the UUID and company.
	*
	* @param uuid the UUID of the dict collection temps
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of dict collection temps
	* @param end the upper bound of the range of dict collection temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching dict collection temps, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.synchronization.model.DictCollectionTemp> getDictCollectionTempsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.synchronization.model.DictCollectionTemp> orderByComparator) {
		return getService()
				   .getDictCollectionTempsByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of dict collection temps.
	*
	* @return the number of dict collection temps
	*/
	public static int getDictCollectionTempsCount() {
		return getService().getDictCollectionTempsCount();
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
	* @param groupId
	* @return true if dict collection not found, init Record, false not thing todo
	*/
	public static boolean initDictCollectionTemp(long groupId) {
		return getService().initDictCollectionTemp(groupId);
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
	* Updates the dict collection temp in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dictCollectionTemp the dict collection temp
	* @return the dict collection temp that was updated
	*/
	public static org.opencps.synchronization.model.DictCollectionTemp updateDictCollectionTemp(
		org.opencps.synchronization.model.DictCollectionTemp dictCollectionTemp) {
		return getService().updateDictCollectionTemp(dictCollectionTemp);
	}

	/**
	* @author binhth
	* @param userId
	* @param dictCollectionId
	* @param collectionCode
	* @param collectionName
	* @param collectionNameEN
	* @param description
	* @param serviceContext
	* @return DictCollectionTemp
	*/
	public static org.opencps.synchronization.model.DictCollectionTemp updateDictCollectionTemp(
		long userId, long dictCollectionId, String collectionCode,
		String collectionName, String collectionNameEN, String description,
		int status, int mustSync, String dataForm,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws org.opencps.auth.api.exception.UnauthenticationException,
			org.opencps.auth.api.exception.UnauthorizationException,
			org.opencps.auth.api.exception.NotFoundException,
			com.liferay.portal.kernel.exception.NoSuchUserException,
			com.liferay.asset.kernel.exception.DuplicateCategoryException {
		return getService()
				   .updateDictCollectionTemp(userId, dictCollectionId,
			collectionCode, collectionName, collectionNameEN, description,
			status, mustSync, dataForm, serviceContext);
	}

	public static DictCollectionTempLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DictCollectionTempLocalService, DictCollectionTempLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DictCollectionTempLocalService.class);

		ServiceTracker<DictCollectionTempLocalService, DictCollectionTempLocalService> serviceTracker =
			new ServiceTracker<DictCollectionTempLocalService, DictCollectionTempLocalService>(bundle.getBundleContext(),
				DictCollectionTempLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}