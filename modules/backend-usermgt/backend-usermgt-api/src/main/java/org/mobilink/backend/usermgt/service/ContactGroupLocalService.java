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

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.mobilink.backend.usermgt.model.ContactGroup;

import java.io.Serializable;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Provides the local service interface for ContactGroup. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Binhth
 * @see ContactGroupLocalServiceUtil
 * @see org.mobilink.backend.usermgt.service.base.ContactGroupLocalServiceBaseImpl
 * @see org.mobilink.backend.usermgt.service.impl.ContactGroupLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ContactGroupLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ContactGroupLocalServiceUtil} to access the contact group local service. Add custom service methods to {@link org.mobilink.backend.usermgt.service.impl.ContactGroupLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	public Hits luceneSearchEngine(
		LinkedHashMap<java.lang.String, java.lang.Object> params, Sort[] sorts,
		int start, int end, SearchContext searchContext)
		throws ParseException, SearchException;

	/**
	* Returns the number of contact groups.
	*
	* @return the number of contact groups
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getContactGroupsCount();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ContactGroup> getContactGroups(int start, int end);

	/**
	* Returns all the contact groups matching the UUID and company.
	*
	* @param uuid the UUID of the contact groups
	* @param companyId the primary key of the company
	* @return the matching contact groups, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ContactGroup> getContactGroupsByUuidAndCompanyId(
		java.lang.String uuid, long companyId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ContactGroup> getContactGroupsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		OrderByComparator<ContactGroup> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	@Indexable(type = IndexableType.REINDEX)
	public ContactGroup addContactGroup(long userId, long groupId,
		java.lang.String groupName, java.lang.String contactList, int shared,
		ServiceContext serviceContext) throws PortalException;

	/**
	* Adds the contact group to the database. Also notifies the appropriate model listeners.
	*
	* @param contactGroup the contact group
	* @return the contact group that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ContactGroup addContactGroup(ContactGroup contactGroup);

	/**
	* Creates a new contact group with the primary key. Does not add the contact group to the database.
	*
	* @param contactGroupId the primary key for the new contact group
	* @return the new contact group
	*/
	public ContactGroup createContactGroup(long contactGroupId);

	/**
	* Deletes the contact group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contactGroupId the primary key of the contact group
	* @return the contact group that was removed
	* @throws PortalException if a contact group with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public ContactGroup deleteContactGroup(long contactGroupId)
		throws PortalException;

	@Indexable(type = IndexableType.DELETE)
	public ContactGroup deleteContactGroup(long contactGroupId,
		ServiceContext serviceContext) throws PortalException;

	/**
	* Deletes the contact group from the database. Also notifies the appropriate model listeners.
	*
	* @param contactGroup the contact group
	* @return the contact group that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public ContactGroup deleteContactGroup(ContactGroup contactGroup);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ContactGroup fetchContactGroup(long contactGroupId);

	/**
	* Returns the contact group matching the UUID and group.
	*
	* @param uuid the contact group's UUID
	* @param groupId the primary key of the group
	* @return the matching contact group, or <code>null</code> if a matching contact group could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ContactGroup fetchContactGroupByUuidAndGroupId(
		java.lang.String uuid, long groupId);

	/**
	* Returns the contact group with the primary key.
	*
	* @param contactGroupId the primary key of the contact group
	* @return the contact group
	* @throws PortalException if a contact group with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ContactGroup getContactGroup(long contactGroupId)
		throws PortalException;

	/**
	* Returns the contact group matching the UUID and group.
	*
	* @param uuid the contact group's UUID
	* @param groupId the primary key of the group
	* @return the matching contact group
	* @throws PortalException if a matching contact group could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ContactGroup getContactGroupByUuidAndGroupId(java.lang.String uuid,
		long groupId) throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public ContactGroup updateContactGroup(long userId, long contactGroupId,
		java.lang.String groupName, java.lang.String contactList, int shared,
		ServiceContext serviceContext) throws PortalException;

	/**
	* Updates the contact group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param contactGroup the contact group
	* @return the contact group that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ContactGroup updateContactGroup(ContactGroup contactGroup);
}