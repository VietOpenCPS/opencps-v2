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
import com.liferay.portal.kernel.json.JSONObject;
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

import org.opencps.usermgt.model.ResourceRole;

import java.io.Serializable;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Provides the local service interface for ResourceRole. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author khoavu
 * @see ResourceRoleLocalServiceUtil
 * @see org.opencps.usermgt.service.base.ResourceRoleLocalServiceBaseImpl
 * @see org.opencps.usermgt.service.impl.ResourceRoleLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface ResourceRoleLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ResourceRoleLocalServiceUtil} to access the resource role local service. Add custom service methods to {@link org.opencps.usermgt.service.impl.ResourceRoleLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Indexable(type = IndexableType.REINDEX)
	public ResourceRole addResourceRole(long userId, long groupId,
		String className, String classPK, long roleId,
		ServiceContext serviceContext)
		throws UnauthenticationException, UnauthorizationException,
			NoSuchUserException, NotFoundException;

	/**
	* Adds the resource role to the database. Also notifies the appropriate model listeners.
	*
	* @param resourceRole the resource role
	* @return the resource role that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ResourceRole addResourceRole(ResourceRole resourceRole);

	@Indexable(type = IndexableType.REINDEX)
	public ResourceRole adminProcessData(JSONObject objectData);

	@Indexable(type = IndexableType.DELETE)
	public ResourceRole adminProcessDelete(Long id);

	public long countLuceneSearchEngine(LinkedHashMap<String, Object> params,
		SearchContext searchContext) throws ParseException, SearchException;

	/**
	* Creates a new resource role with the primary key. Does not add the resource role to the database.
	*
	* @param resourceRoleId the primary key for the new resource role
	* @return the new resource role
	*/
	@Transactional(enabled = false)
	public ResourceRole createResourceRole(long resourceRoleId);

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	* Deletes the resource role with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param resourceRoleId the primary key of the resource role
	* @return the resource role that was removed
	* @throws PortalException if a resource role with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public ResourceRole deleteResourceRole(long resourceRoleId)
		throws PortalException;

	/**
	* @param dictCollectionId
	* @param serviceContext
	* @return
	* @throws Exception
	*/
	@Indexable(type = IndexableType.DELETE)
	public ResourceRole deleteResourceRole(long resourceRoleId,
		ServiceContext serviceContext)
		throws UnauthenticationException, UnauthorizationException,
			NotFoundException;

	/**
	* Deletes the resource role from the database. Also notifies the appropriate model listeners.
	*
	* @param resourceRole the resource role
	* @return the resource role that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public ResourceRole deleteResourceRole(ResourceRole resourceRole);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.ResourceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.ResourceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public ResourceRole fetchByF_className_classPK_roleId(long groupId,
		String className, String classPK, long roleId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ResourceRole fetchResourceRole(long resourceRoleId);

	/**
	* Returns the resource role matching the UUID and group.
	*
	* @param uuid the resource role's UUID
	* @param groupId the primary key of the group
	* @return the matching resource role, or <code>null</code> if a matching resource role could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ResourceRole fetchResourceRoleByUuidAndGroupId(String uuid,
		long groupId);

	public List<ResourceRole> findByF_className_classPK(long groupId,
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
	* Returns the resource role with the primary key.
	*
	* @param resourceRoleId the primary key of the resource role
	* @return the resource role
	* @throws PortalException if a resource role with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ResourceRole getResourceRole(long resourceRoleId)
		throws PortalException;

	/**
	* Returns the resource role matching the UUID and group.
	*
	* @param uuid the resource role's UUID
	* @param groupId the primary key of the group
	* @return the matching resource role
	* @throws PortalException if a matching resource role could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ResourceRole getResourceRoleByUuidAndGroupId(String uuid,
		long groupId) throws PortalException;

	/**
	* Returns a range of all the resource roles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.ResourceRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of resource roles
	* @param end the upper bound of the range of resource roles (not inclusive)
	* @return the range of resource roles
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ResourceRole> getResourceRoles(int start, int end);

	/**
	* Returns all the resource roles matching the UUID and company.
	*
	* @param uuid the UUID of the resource roles
	* @param companyId the primary key of the company
	* @return the matching resource roles, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ResourceRole> getResourceRolesByUuidAndCompanyId(String uuid,
		long companyId);

	/**
	* Returns a range of resource roles matching the UUID and company.
	*
	* @param uuid the UUID of the resource roles
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of resource roles
	* @param end the upper bound of the range of resource roles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching resource roles, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<ResourceRole> getResourceRolesByUuidAndCompanyId(String uuid,
		long companyId, int start, int end,
		OrderByComparator<ResourceRole> orderByComparator);

	/**
	* Returns the number of resource roles.
	*
	* @return the number of resource roles
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getResourceRolesCount();

	public Hits luceneSearchEngine(LinkedHashMap<String, Object> params,
		Sort[] sorts, int start, int end, SearchContext searchContext)
		throws ParseException, SearchException;

	@Indexable(type = IndexableType.REINDEX)
	public ResourceRole updateResourceRole(long userId, long resourceRoleId,
		String className, String classPK, long roleId,
		ServiceContext serviceContext)
		throws UnauthenticationException, UnauthorizationException,
			NotFoundException, NoSuchUserException;

	/**
	* Updates the resource role in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param resourceRole the resource role
	* @return the resource role that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public ResourceRole updateResourceRole(ResourceRole resourceRole);
}