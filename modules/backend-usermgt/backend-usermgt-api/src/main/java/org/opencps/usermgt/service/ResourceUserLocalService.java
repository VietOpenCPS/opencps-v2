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

import backend.auth.api.exception.NotFoundException;
import backend.auth.api.exception.UnauthenticationException;
import backend.auth.api.exception.UnauthorizationException;

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.NoSuchUserException;
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

import org.opencps.usermgt.model.ResourceUser;

import java.io.Serializable;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Provides the local service interface for ResourceUser. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author khoavu
 * @see ResourceUserLocalServiceUtil
 * @see org.opencps.usermgt.service.base.ResourceUserLocalServiceBaseImpl
 * @see org.opencps.usermgt.service.impl.ResourceUserLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ResourceUserLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ResourceUserLocalServiceUtil} to access the resource user local service. Add custom service methods to {@link org.opencps.usermgt.service.impl.ResourceUserLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Indexable(type = IndexableType.REINDEX)
	public ResourceUser addResourceUser(long userId, long groupId,
		String className, String classPK, long toUserId, String fullname,
		String email, boolean readonly, ServiceContext serviceContext)
		throws UnauthenticationException, UnauthorizationException,
			NoSuchUserException, NotFoundException;

	/**
	* Adds the resource user to the database. Also notifies the appropriate model listeners.
	*
	* @param resourceUser the resource user
	* @return the resource user that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ResourceUser addResourceUser(ResourceUser resourceUser);

	public long countLuceneSearchEngine(LinkedHashMap<String, Object> params,
		SearchContext searchContext) throws ParseException, SearchException;

	/**
	* Creates a new resource user with the primary key. Does not add the resource user to the database.
	*
	* @param resourceUserId the primary key for the new resource user
	* @return the new resource user
	*/
	@Transactional(enabled = false)
	public ResourceUser createResourceUser(long resourceUserId);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	* Deletes the resource user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param resourceUserId the primary key of the resource user
	* @return the resource user that was removed
	* @throws PortalException if a resource user with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public ResourceUser deleteResourceUser(long resourceUserId)
		throws PortalException;

	/**
	* @param dictCollectionId
	* @param serviceContext
	* @return
	* @throws Exception
	*/
	@Indexable(type = IndexableType.DELETE)
	public ResourceUser deleteResourceUser(long resourceUserId,
		ServiceContext serviceContext)
		throws UnauthenticationException, UnauthorizationException,
			NotFoundException;

	/**
	* Deletes the resource user from the database. Also notifies the appropriate model listeners.
	*
	* @param resourceUser the resource user
	* @return the resource user that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public ResourceUser deleteResourceUser(ResourceUser resourceUser);

	public DynamicQuery dynamicQuery();

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.ResourceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.ResourceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ResourceUser fetchByF_className_classPK_toUserId(long groupId,
		String className, String classPK, long toUserId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ResourceUser fetchResourceUser(long resourceUserId);

	/**
	* Returns the resource user matching the UUID and group.
	*
	* @param uuid the resource user's UUID
	* @param groupId the primary key of the group
	* @return the matching resource user, or <code>null</code> if a matching resource user could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ResourceUser fetchResourceUserByUuidAndGroupId(String uuid,
		long groupId);

	public List<ResourceUser> findByF_className_classPK(long groupId,
		String className, String classPK);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	* Returns the resource user with the primary key.
	*
	* @param resourceUserId the primary key of the resource user
	* @return the resource user
	* @throws PortalException if a resource user with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ResourceUser getResourceUser(long resourceUserId)
		throws PortalException;

	/**
	* Returns the resource user matching the UUID and group.
	*
	* @param uuid the resource user's UUID
	* @param groupId the primary key of the group
	* @return the matching resource user
	* @throws PortalException if a matching resource user could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ResourceUser getResourceUserByUuidAndGroupId(String uuid,
		long groupId) throws PortalException;

	/**
	* Returns a range of all the resource users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.ResourceUserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of resource users
	* @param end the upper bound of the range of resource users (not inclusive)
	* @return the range of resource users
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ResourceUser> getResourceUsers(int start, int end);

	/**
	* Returns all the resource users matching the UUID and company.
	*
	* @param uuid the UUID of the resource users
	* @param companyId the primary key of the company
	* @return the matching resource users, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ResourceUser> getResourceUsersByUuidAndCompanyId(String uuid,
		long companyId);

	/**
	* Returns a range of resource users matching the UUID and company.
	*
	* @param uuid the UUID of the resource users
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of resource users
	* @param end the upper bound of the range of resource users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching resource users, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ResourceUser> getResourceUsersByUuidAndCompanyId(String uuid,
		long companyId, int start, int end,
		OrderByComparator<ResourceUser> orderByComparator);

	/**
	* Returns the number of resource users.
	*
	* @return the number of resource users
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getResourceUsersCount();

	public Hits luceneSearchEngine(LinkedHashMap<String, Object> params,
		Sort[] sorts, int start, int end, SearchContext searchContext)
		throws ParseException, SearchException;

	@Indexable(type = IndexableType.REINDEX)
	public ResourceUser updateResourceUser(long userId, long resourceUserId,
		String className, String classPK, long toUserId, String fullname,
		String email, boolean readonly, ServiceContext serviceContext)
		throws UnauthenticationException, UnauthorizationException,
			NotFoundException, NoSuchUserException;

	/**
	* Updates the resource user in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param resourceUser the resource user
	* @return the resource user that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ResourceUser updateResourceUser(ResourceUser resourceUser);
}