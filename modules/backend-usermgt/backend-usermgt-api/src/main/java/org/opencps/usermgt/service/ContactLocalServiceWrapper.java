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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ContactLocalService}.
 *
 * @author Binhth
 * @see ContactLocalService
 * @generated
 */
@ProviderType
public class ContactLocalServiceWrapper implements ContactLocalService,
	ServiceWrapper<ContactLocalService> {
	public ContactLocalServiceWrapper(ContactLocalService contactLocalService) {
		_contactLocalService = contactLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _contactLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _contactLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _contactLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _contactLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _contactLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _contactLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits luceneSearchEngine(
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _contactLocalService.luceneSearchEngine(params, sorts, start,
			end, searchContext);
	}

	/**
	* Returns the number of contacts.
	*
	* @return the number of contacts
	*/
	@Override
	public int getContactsCount() {
		return _contactLocalService.getContactsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _contactLocalService.getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _contactLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.usermgt.model.impl.ContactModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _contactLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.usermgt.model.impl.ContactModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _contactLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the contacts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.usermgt.model.impl.ContactModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of contacts
	* @param end the upper bound of the range of contacts (not inclusive)
	* @return the range of contacts
	*/
	@Override
	public java.util.List<org.opencps.usermgt.model.Contact> getContacts(
		int start, int end) {
		return _contactLocalService.getContacts(start, end);
	}

	/**
	* Returns all the contacts matching the UUID and company.
	*
	* @param uuid the UUID of the contacts
	* @param companyId the primary key of the company
	* @return the matching contacts, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.usermgt.model.Contact> getContactsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _contactLocalService.getContactsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of contacts matching the UUID and company.
	*
	* @param uuid the UUID of the contacts
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of contacts
	* @param end the upper bound of the range of contacts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching contacts, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.usermgt.model.Contact> getContactsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.usermgt.model.Contact> orderByComparator) {
		return _contactLocalService.getContactsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return _contactLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return _contactLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public org.opencps.usermgt.model.Contact addContact(long userId,
		long groupId, java.lang.String fullName, java.lang.String companyName,
		java.lang.String telNo, java.lang.String email, long userMappingId,
		boolean isOrg, int shared,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _contactLocalService.addContact(userId, groupId, fullName,
			companyName, telNo, email, userMappingId, isOrg, shared,
			serviceContext);
	}

	/**
	* Adds the contact to the database. Also notifies the appropriate model listeners.
	*
	* @param contact the contact
	* @return the contact that was added
	*/
	@Override
	public org.opencps.usermgt.model.Contact addContact(
		org.opencps.usermgt.model.Contact contact) {
		return _contactLocalService.addContact(contact);
	}

	/**
	* Creates a new contact with the primary key. Does not add the contact to the database.
	*
	* @param contactId the primary key for the new contact
	* @return the new contact
	*/
	@Override
	public org.opencps.usermgt.model.Contact createContact(
		long contactId) {
		return _contactLocalService.createContact(contactId);
	}

	/**
	* Deletes the contact with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contactId the primary key of the contact
	* @return the contact that was removed
	* @throws PortalException if a contact with the primary key could not be found
	*/
	@Override
	public org.opencps.usermgt.model.Contact deleteContact(
		long contactId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _contactLocalService.deleteContact(contactId);
	}

	@Override
	public org.opencps.usermgt.model.Contact deleteContact(
		long contactId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _contactLocalService.deleteContact(contactId, serviceContext);
	}

	/**
	* Deletes the contact from the database. Also notifies the appropriate model listeners.
	*
	* @param contact the contact
	* @return the contact that was removed
	*/
	@Override
	public org.opencps.usermgt.model.Contact deleteContact(
		org.opencps.usermgt.model.Contact contact) {
		return _contactLocalService.deleteContact(contact);
	}

	@Override
	public org.opencps.usermgt.model.Contact fetchByUserMappingId(
		long userMappingId) {
		return _contactLocalService.fetchByUserMappingId(userMappingId);
	}

	@Override
	public org.opencps.usermgt.model.Contact fetchContact(
		long contactId) {
		return _contactLocalService.fetchContact(contactId);
	}

	/**
	* Returns the contact matching the UUID and group.
	*
	* @param uuid the contact's UUID
	* @param groupId the primary key of the group
	* @return the matching contact, or <code>null</code> if a matching contact could not be found
	*/
	@Override
	public org.opencps.usermgt.model.Contact fetchContactByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _contactLocalService.fetchContactByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the contact with the primary key.
	*
	* @param contactId the primary key of the contact
	* @return the contact
	* @throws PortalException if a contact with the primary key could not be found
	*/
	@Override
	public org.opencps.usermgt.model.Contact getContact(long contactId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _contactLocalService.getContact(contactId);
	}

	/**
	* Returns the contact matching the UUID and group.
	*
	* @param uuid the contact's UUID
	* @param groupId the primary key of the group
	* @return the matching contact
	* @throws PortalException if a matching contact could not be found
	*/
	@Override
	public org.opencps.usermgt.model.Contact getContactByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _contactLocalService.getContactByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public org.opencps.usermgt.model.Contact updateContact(
		long userId, long contactId, java.lang.String fullName,
		java.lang.String companyName, java.lang.String telNo,
		java.lang.String email, long userMappingId, boolean isOrg, int shared,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _contactLocalService.updateContact(userId, contactId, fullName,
			companyName, telNo, email, userMappingId, isOrg, shared,
			serviceContext);
	}

	/**
	* Updates the contact in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param contact the contact
	* @return the contact that was updated
	*/
	@Override
	public org.opencps.usermgt.model.Contact updateContact(
		org.opencps.usermgt.model.Contact contact) {
		return _contactLocalService.updateContact(contact);
	}

	@Override
	public org.opencps.usermgt.model.Contact updateContactGroupId(
		long contactId, long groupId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _contactLocalService.updateContactGroupId(contactId, groupId,
			serviceContext);
	}

	@Override
	public ContactLocalService getWrappedService() {
		return _contactLocalService;
	}

	@Override
	public void setWrappedService(ContactLocalService contactLocalService) {
		_contactLocalService = contactLocalService;
	}

	private ContactLocalService _contactLocalService;
}