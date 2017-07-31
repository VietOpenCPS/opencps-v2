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

package org.mobilink.backend.usermgt.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Partner. This utility wraps
 * {@link org.mobilink.backend.usermgt.service.impl.PartnerLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Binhth
 * @see PartnerLocalService
 * @see org.mobilink.backend.usermgt.service.base.PartnerLocalServiceBaseImpl
 * @see org.mobilink.backend.usermgt.service.impl.PartnerLocalServiceImpl
 * @generated
 */
@ProviderType
public class PartnerLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.mobilink.backend.usermgt.service.impl.PartnerLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
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

	public static com.liferay.portal.kernel.search.Hits luceneSearchEngine(
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return getService()
				   .luceneSearchEngine(params, sorts, start, end, searchContext);
	}

	/**
	* Returns the number of partners.
	*
	* @return the number of partners
	*/
	public static int getPartnersCount() {
		return getService().getPartnersCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.usermgt.model.impl.PartnerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.usermgt.model.impl.PartnerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the partners.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.usermgt.model.impl.PartnerModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of partners
	* @param end the upper bound of the range of partners (not inclusive)
	* @return the range of partners
	*/
	public static java.util.List<org.mobilink.backend.usermgt.model.Partner> getPartners(
		int start, int end) {
		return getService().getPartners(start, end);
	}

	/**
	* Returns all the partners matching the UUID and company.
	*
	* @param uuid the UUID of the partners
	* @param companyId the primary key of the company
	* @return the matching partners, or an empty list if no matches were found
	*/
	public static java.util.List<org.mobilink.backend.usermgt.model.Partner> getPartnersByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getPartnersByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of partners matching the UUID and company.
	*
	* @param uuid the UUID of the partners
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of partners
	* @param end the upper bound of the range of partners (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching partners, or an empty list if no matches were found
	*/
	public static java.util.List<org.mobilink.backend.usermgt.model.Partner> getPartnersByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.mobilink.backend.usermgt.model.Partner> orderByComparator) {
		return getService()
				   .getPartnersByUuidAndCompanyId(uuid, companyId, start, end,
			orderByComparator);
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

	public static org.mobilink.backend.usermgt.model.Partner addPartner(
		long userId, long groupId, long organizationId, java.lang.String name,
		java.lang.String address, java.lang.String telNo,
		java.lang.String faxNo, java.lang.String email,
		java.lang.String website, int partnerClass, java.lang.String docFileId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addPartner(userId, groupId, organizationId, name, address,
			telNo, faxNo, email, website, partnerClass, docFileId,
			serviceContext);
	}

	/**
	* Adds the partner to the database. Also notifies the appropriate model listeners.
	*
	* @param partner the partner
	* @return the partner that was added
	*/
	public static org.mobilink.backend.usermgt.model.Partner addPartner(
		org.mobilink.backend.usermgt.model.Partner partner) {
		return getService().addPartner(partner);
	}

	/**
	* Creates a new partner with the primary key. Does not add the partner to the database.
	*
	* @param partnerId the primary key for the new partner
	* @return the new partner
	*/
	public static org.mobilink.backend.usermgt.model.Partner createPartner(
		long partnerId) {
		return getService().createPartner(partnerId);
	}

	/**
	* Deletes the partner with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param partnerId the primary key of the partner
	* @return the partner that was removed
	* @throws PortalException if a partner with the primary key could not be found
	*/
	public static org.mobilink.backend.usermgt.model.Partner deletePartner(
		long partnerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePartner(partnerId);
	}

	public static org.mobilink.backend.usermgt.model.Partner deletePartner(
		long partnerId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws java.lang.Exception {
		return getService().deletePartner(partnerId, serviceContext);
	}

	/**
	* Deletes the partner from the database. Also notifies the appropriate model listeners.
	*
	* @param partner the partner
	* @return the partner that was removed
	*/
	public static org.mobilink.backend.usermgt.model.Partner deletePartner(
		org.mobilink.backend.usermgt.model.Partner partner) {
		return getService().deletePartner(partner);
	}

	public static org.mobilink.backend.usermgt.model.Partner fetchPartner(
		long partnerId) {
		return getService().fetchPartner(partnerId);
	}

	/**
	* Returns the partner matching the UUID and group.
	*
	* @param uuid the partner's UUID
	* @param groupId the primary key of the group
	* @return the matching partner, or <code>null</code> if a matching partner could not be found
	*/
	public static org.mobilink.backend.usermgt.model.Partner fetchPartnerByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchPartnerByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the partner with the primary key.
	*
	* @param partnerId the primary key of the partner
	* @return the partner
	* @throws PortalException if a partner with the primary key could not be found
	*/
	public static org.mobilink.backend.usermgt.model.Partner getPartner(
		long partnerId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPartner(partnerId);
	}

	/**
	* Returns the partner matching the UUID and group.
	*
	* @param uuid the partner's UUID
	* @param groupId the primary key of the group
	* @return the matching partner
	* @throws PortalException if a matching partner could not be found
	*/
	public static org.mobilink.backend.usermgt.model.Partner getPartnerByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPartnerByUuidAndGroupId(uuid, groupId);
	}

	public static org.mobilink.backend.usermgt.model.Partner updatePartner(
		long userId, long partnerId, java.lang.String name,
		java.lang.String address, java.lang.String telNo,
		java.lang.String faxNo, java.lang.String email,
		java.lang.String website, int partnerClass, java.lang.String docFileId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updatePartner(userId, partnerId, name, address, telNo,
			faxNo, email, website, partnerClass, docFileId, serviceContext);
	}

	/**
	* Updates the partner in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param partner the partner
	* @return the partner that was updated
	*/
	public static org.mobilink.backend.usermgt.model.Partner updatePartner(
		org.mobilink.backend.usermgt.model.Partner partner) {
		return getService().updatePartner(partner);
	}

	public static PartnerLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<PartnerLocalService, PartnerLocalService> _serviceTracker =
		ServiceTrackerFactory.open(PartnerLocalService.class);
}