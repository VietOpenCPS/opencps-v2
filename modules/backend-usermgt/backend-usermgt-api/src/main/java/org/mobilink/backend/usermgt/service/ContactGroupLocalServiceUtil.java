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
 * Provides the local service utility for ContactGroup. This utility wraps
 * {@link org.mobilink.backend.usermgt.service.impl.ContactGroupLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Binhth
 * @see ContactGroupLocalService
 * @see org.mobilink.backend.usermgt.service.base.ContactGroupLocalServiceBaseImpl
 * @see org.mobilink.backend.usermgt.service.impl.ContactGroupLocalServiceImpl
 * @generated
 */
@ProviderType
public class ContactGroupLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.mobilink.backend.usermgt.service.impl.ContactGroupLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
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
	* Returns the number of contact groups.
	*
	* @return the number of contact groups
	*/
	public static int getContactGroupsCount() {
		return getService().getContactGroupsCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.usermgt.model.impl.ContactGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.usermgt.model.impl.ContactGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the contact groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.usermgt.model.impl.ContactGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of contact groups
	* @param end the upper bound of the range of contact groups (not inclusive)
	* @return the range of contact groups
	*/
	public static java.util.List<org.mobilink.backend.usermgt.model.ContactGroup> getContactGroups(
		int start, int end) {
		return getService().getContactGroups(start, end);
	}

	/**
	* Returns all the contact groups matching the UUID and company.
	*
	* @param uuid the UUID of the contact groups
	* @param companyId the primary key of the company
	* @return the matching contact groups, or an empty list if no matches were found
	*/
	public static java.util.List<org.mobilink.backend.usermgt.model.ContactGroup> getContactGroupsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getContactGroupsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of contact groups matching the UUID and company.
	*
	* @param uuid the UUID of the contact groups
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of contact groups
	* @param end the upper bound of the range of contact groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching contact groups, or an empty list if no matches were found
	*/
	public static java.util.List<org.mobilink.backend.usermgt.model.ContactGroup> getContactGroupsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.mobilink.backend.usermgt.model.ContactGroup> orderByComparator) {
		return getService()
				   .getContactGroupsByUuidAndCompanyId(uuid, companyId, start,
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

	public static org.mobilink.backend.usermgt.model.ContactGroup addContactGroup(
		long userId, long groupId, java.lang.String groupName,
		java.lang.String contactList, int shared,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addContactGroup(userId, groupId, groupName, contactList,
			shared, serviceContext);
	}

	/**
	* Adds the contact group to the database. Also notifies the appropriate model listeners.
	*
	* @param contactGroup the contact group
	* @return the contact group that was added
	*/
	public static org.mobilink.backend.usermgt.model.ContactGroup addContactGroup(
		org.mobilink.backend.usermgt.model.ContactGroup contactGroup) {
		return getService().addContactGroup(contactGroup);
	}

	/**
	* Creates a new contact group with the primary key. Does not add the contact group to the database.
	*
	* @param contactGroupId the primary key for the new contact group
	* @return the new contact group
	*/
	public static org.mobilink.backend.usermgt.model.ContactGroup createContactGroup(
		long contactGroupId) {
		return getService().createContactGroup(contactGroupId);
	}

	/**
	* Deletes the contact group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contactGroupId the primary key of the contact group
	* @return the contact group that was removed
	* @throws PortalException if a contact group with the primary key could not be found
	*/
	public static org.mobilink.backend.usermgt.model.ContactGroup deleteContactGroup(
		long contactGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteContactGroup(contactGroupId);
	}

	public static org.mobilink.backend.usermgt.model.ContactGroup deleteContactGroup(
		long contactGroupId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteContactGroup(contactGroupId, serviceContext);
	}

	/**
	* Deletes the contact group from the database. Also notifies the appropriate model listeners.
	*
	* @param contactGroup the contact group
	* @return the contact group that was removed
	*/
	public static org.mobilink.backend.usermgt.model.ContactGroup deleteContactGroup(
		org.mobilink.backend.usermgt.model.ContactGroup contactGroup) {
		return getService().deleteContactGroup(contactGroup);
	}

	public static org.mobilink.backend.usermgt.model.ContactGroup fetchContactGroup(
		long contactGroupId) {
		return getService().fetchContactGroup(contactGroupId);
	}

	/**
	* Returns the contact group matching the UUID and group.
	*
	* @param uuid the contact group's UUID
	* @param groupId the primary key of the group
	* @return the matching contact group, or <code>null</code> if a matching contact group could not be found
	*/
	public static org.mobilink.backend.usermgt.model.ContactGroup fetchContactGroupByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchContactGroupByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the contact group with the primary key.
	*
	* @param contactGroupId the primary key of the contact group
	* @return the contact group
	* @throws PortalException if a contact group with the primary key could not be found
	*/
	public static org.mobilink.backend.usermgt.model.ContactGroup getContactGroup(
		long contactGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getContactGroup(contactGroupId);
	}

	/**
	* Returns the contact group matching the UUID and group.
	*
	* @param uuid the contact group's UUID
	* @param groupId the primary key of the group
	* @return the matching contact group
	* @throws PortalException if a matching contact group could not be found
	*/
	public static org.mobilink.backend.usermgt.model.ContactGroup getContactGroupByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getContactGroupByUuidAndGroupId(uuid, groupId);
	}

	public static org.mobilink.backend.usermgt.model.ContactGroup updateContactGroup(
		long userId, long contactGroupId, java.lang.String groupName,
		java.lang.String contactList, int shared,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateContactGroup(userId, contactGroupId, groupName,
			contactList, shared, serviceContext);
	}

	/**
	* Updates the contact group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param contactGroup the contact group
	* @return the contact group that was updated
	*/
	public static org.mobilink.backend.usermgt.model.ContactGroup updateContactGroup(
		org.mobilink.backend.usermgt.model.ContactGroup contactGroup) {
		return getService().updateContactGroup(contactGroup);
	}

	public static ContactGroupLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ContactGroupLocalService, ContactGroupLocalService> _serviceTracker =
		ServiceTrackerFactory.open(ContactGroupLocalService.class);
}