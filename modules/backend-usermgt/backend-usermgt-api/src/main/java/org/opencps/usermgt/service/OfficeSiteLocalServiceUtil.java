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
 * Provides the local service utility for OfficeSite. This utility wraps
 * {@link org.opencps.usermgt.service.impl.OfficeSiteLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author khoavu
 * @see OfficeSiteLocalService
 * @see org.opencps.usermgt.service.base.OfficeSiteLocalServiceBaseImpl
 * @see org.opencps.usermgt.service.impl.OfficeSiteLocalServiceImpl
 * @generated
 */
@ProviderType
public class OfficeSiteLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.usermgt.service.impl.OfficeSiteLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.opencps.usermgt.model.OfficeSite addOfficeSite(
		long userId, long groupId, String name, String enName,
		String govAgencyCode, String address, String telNo, String faxNo,
		String email, String website, long logoFileEntryId, long siteGroupId,
		long adminUserId, String preferences,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws backend.auth.api.exception.UnauthenticationException,
			backend.auth.api.exception.UnauthorizationException,
			com.liferay.portal.kernel.exception.NoSuchUserException {
		return getService()
				   .addOfficeSite(userId, groupId, name, enName, govAgencyCode,
			address, telNo, faxNo, email, website, logoFileEntryId,
			siteGroupId, adminUserId, preferences, serviceContext);
	}

	/**
	* Adds the office site to the database. Also notifies the appropriate model listeners.
	*
	* @param officeSite the office site
	* @return the office site that was added
	*/
	public static org.opencps.usermgt.model.OfficeSite addOfficeSite(
		org.opencps.usermgt.model.OfficeSite officeSite) {
		return getService().addOfficeSite(officeSite);
	}

	public static org.opencps.usermgt.model.OfficeSite adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return getService().adminProcessData(objectData);
	}

	public static org.opencps.usermgt.model.OfficeSite adminProcessDelete(
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

	/**
	* Creates a new office site with the primary key. Does not add the office site to the database.
	*
	* @param officeSiteId the primary key for the new office site
	* @return the new office site
	*/
	public static org.opencps.usermgt.model.OfficeSite createOfficeSite(
		long officeSiteId) {
		return getService().createOfficeSite(officeSiteId);
	}

	/**
	* Deletes the office site with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param officeSiteId the primary key of the office site
	* @return the office site that was removed
	* @throws PortalException if a office site with the primary key could not be found
	*/
	public static org.opencps.usermgt.model.OfficeSite deleteOfficeSite(
		long officeSiteId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteOfficeSite(officeSiteId);
	}

	public static org.opencps.usermgt.model.OfficeSite deleteOfficeSite(
		long officeSiteId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws backend.auth.api.exception.UnauthenticationException,
			backend.auth.api.exception.UnauthorizationException,
			backend.auth.api.exception.NotFoundException {
		return getService().deleteOfficeSite(officeSiteId, serviceContext);
	}

	/**
	* Deletes the office site from the database. Also notifies the appropriate model listeners.
	*
	* @param officeSite the office site
	* @return the office site that was removed
	*/
	public static org.opencps.usermgt.model.OfficeSite deleteOfficeSite(
		org.opencps.usermgt.model.OfficeSite officeSite) {
		return getService().deleteOfficeSite(officeSite);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.OfficeSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.OfficeSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.usermgt.model.OfficeSite fetchF_groupId_siteGroupId(
		long groupId, long siteGroupId) {
		return getService().fetchF_groupId_siteGroupId(groupId, siteGroupId);
	}

	public static org.opencps.usermgt.model.OfficeSite fetchOfficeSite(
		long officeSiteId) {
		return getService().fetchOfficeSite(officeSiteId);
	}

	/**
	* Returns the office site matching the UUID and group.
	*
	* @param uuid the office site's UUID
	* @param groupId the primary key of the group
	* @return the matching office site, or <code>null</code> if a matching office site could not be found
	*/
	public static org.opencps.usermgt.model.OfficeSite fetchOfficeSiteByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchOfficeSiteByUuidAndGroupId(uuid, groupId);
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
	* Returns the office site with the primary key.
	*
	* @param officeSiteId the primary key of the office site
	* @return the office site
	* @throws PortalException if a office site with the primary key could not be found
	*/
	public static org.opencps.usermgt.model.OfficeSite getOfficeSite(
		long officeSiteId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getOfficeSite(officeSiteId);
	}

	/**
	* Returns the office site matching the UUID and group.
	*
	* @param uuid the office site's UUID
	* @param groupId the primary key of the group
	* @return the matching office site
	* @throws PortalException if a matching office site could not be found
	*/
	public static org.opencps.usermgt.model.OfficeSite getOfficeSiteByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getOfficeSiteByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the office sites.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.OfficeSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of office sites
	* @param end the upper bound of the range of office sites (not inclusive)
	* @return the range of office sites
	*/
	public static java.util.List<org.opencps.usermgt.model.OfficeSite> getOfficeSites(
		int start, int end) {
		return getService().getOfficeSites(start, end);
	}

	/**
	* Returns all the office sites matching the UUID and company.
	*
	* @param uuid the UUID of the office sites
	* @param companyId the primary key of the company
	* @return the matching office sites, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.usermgt.model.OfficeSite> getOfficeSitesByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getOfficeSitesByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of office sites matching the UUID and company.
	*
	* @param uuid the UUID of the office sites
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of office sites
	* @param end the upper bound of the range of office sites (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching office sites, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.usermgt.model.OfficeSite> getOfficeSitesByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.usermgt.model.OfficeSite> orderByComparator) {
		return getService()
				   .getOfficeSitesByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of office sites.
	*
	* @return the number of office sites
	*/
	public static int getOfficeSitesCount() {
		return getService().getOfficeSitesCount();
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

	public static org.opencps.usermgt.model.OfficeSite updateOfficeSite(
		long userId, long officeSiteId, String name, String enName,
		String govAgencyCode, String address, String telNo, String faxNo,
		String email, String website, long logoFileEntryId, long siteGroupId,
		long adminUserId, String preferences,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws backend.auth.api.exception.UnauthenticationException,
			backend.auth.api.exception.UnauthorizationException,
			backend.auth.api.exception.NotFoundException,
			com.liferay.portal.kernel.exception.NoSuchUserException {
		return getService()
				   .updateOfficeSite(userId, officeSiteId, name, enName,
			govAgencyCode, address, telNo, faxNo, email, website,
			logoFileEntryId, siteGroupId, adminUserId, preferences,
			serviceContext);
	}

	/**
	* Updates the office site in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param officeSite the office site
	* @return the office site that was updated
	*/
	public static org.opencps.usermgt.model.OfficeSite updateOfficeSite(
		org.opencps.usermgt.model.OfficeSite officeSite) {
		return getService().updateOfficeSite(officeSite);
	}

	public static OfficeSiteLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<OfficeSiteLocalService, OfficeSiteLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(OfficeSiteLocalService.class);

		ServiceTracker<OfficeSiteLocalService, OfficeSiteLocalService> serviceTracker =
			new ServiceTracker<OfficeSiteLocalService, OfficeSiteLocalService>(bundle.getBundleContext(),
				OfficeSiteLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}