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

package org.opencps.datamgt.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.asset.kernel.exception.DuplicateCategoryException;

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

import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;

import org.opencps.datamgt.model.DictItemGroup;

import java.io.Serializable;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Provides the local service interface for DictItemGroup. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author khoavu
 * @see DictItemGroupLocalServiceUtil
 * @see org.opencps.datamgt.service.base.DictItemGroupLocalServiceBaseImpl
 * @see org.opencps.datamgt.service.impl.DictItemGroupLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface DictItemGroupLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DictItemGroupLocalServiceUtil} to access the dict item group local service. Add custom service methods to {@link org.opencps.datamgt.service.impl.DictItemGroupLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the dict item group to the database. Also notifies the appropriate model listeners.
	*
	* @param dictItemGroup the dict item group
	* @return the dict item group that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public DictItemGroup addDictItemGroup(DictItemGroup dictItemGroup);

	/**
	* @author binhth
	* @param userId
	* @param groupId
	* @param DictItemGroupId
	* @param dictItemId
	* @param serviceContext
	* @return DictItemGroup
	* @throws DuplicateCategoryException
	* @throws UnauthenticationException
	* @throws UnauthorizationException
	* @throws NoSuchUserException
	*/
	@Indexable(type = IndexableType.REINDEX)
	public DictItemGroup addDictItemGroup(long userId, long groupId,
		long dictGroupId, long dictItemId, String groupCode,
		ServiceContext serviceContext)
		throws DuplicateCategoryException, UnauthenticationException,
			UnauthorizationException, NoSuchUserException;

	@Indexable(type = IndexableType.REINDEX)
	public DictItemGroup adminProcessData(JSONObject objectData);

	@Indexable(type = IndexableType.DELETE)
	public DictItemGroup adminProcessDelete(Long id);

	public long countLuceneSearchEngine(LinkedHashMap<String, Object> params,
		SearchContext searchContext) throws ParseException, SearchException;

	public long countOlderThanDate(Date date, long groupId);

	/**
	* Creates a new dict item group with the primary key. Does not add the dict item group to the database.
	*
	* @param dictItemGroupId the primary key for the new dict item group
	* @return the new dict item group
	*/
	@Transactional(enabled = false)
	public DictItemGroup createDictItemGroup(long dictItemGroupId);

	/**
	* Deletes the dict item group from the database. Also notifies the appropriate model listeners.
	*
	* @param dictItemGroup the dict item group
	* @return the dict item group that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public DictItemGroup deleteDictItemGroup(DictItemGroup dictItemGroup);

	/**
	* Deletes the dict item group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dictItemGroupId the primary key of the dict item group
	* @return the dict item group that was removed
	* @throws PortalException if a dict item group with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public DictItemGroup deleteDictItemGroup(long dictItemGroupId)
		throws PortalException;

	/**
	* @author binhth
	* @param dictItemGroupId
	* @param serviceContext
	* @throws UnauthenticationException,
	UnauthorizationException, NotFoundException
	*/
	@Indexable(type = IndexableType.DELETE)
	public DictItemGroup deleteDictItemGroup(long dictItemGroupId,
		ServiceContext serviceContext)
		throws UnauthenticationException, UnauthorizationException,
			NotFoundException;

	@Indexable(type = IndexableType.DELETE)
	public DictItemGroup deleteDictItemGroupNoneAuthen(long dictItemGroupId,
		ServiceContext serviceContext)
		throws UnauthenticationException, UnauthorizationException,
			NotFoundException;

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.DictItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.DictItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	/**
	* @author binhth
	* @param groupId
	* @param dictItemId
	* @param DictItemGroupId
	* @return DictItemGroup
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DictItemGroup fetchByF_dictItemId_dictGroupId(long groupId,
		long dictGroupId, long dictItemId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DictItemGroup fetchDictItemGroup(long dictItemGroupId);

	/**
	* Returns the dict item group matching the UUID and group.
	*
	* @param uuid the dict item group's UUID
	* @param groupId the primary key of the group
	* @return the matching dict item group, or <code>null</code> if a matching dict item group could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DictItemGroup fetchDictItemGroupByUuidAndGroupId(String uuid,
		long groupId);

	public List<DictItemGroup> findByDictGroupId(long groupId, long dictGroupId);

	/**
	* @author binhth
	* @param groupId
	* @param dictGroupId
	* @param dictItemId
	* @return List<DictItemGroup>
	*/
	public List<DictItemGroup> findByF_dictItemId(long groupId, long dictItemId);

	public List<DictItemGroup> findOlderThanDate(Date date, long groupId,
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	/**
	* Returns the dict item group with the primary key.
	*
	* @param dictItemGroupId the primary key of the dict item group
	* @return the dict item group
	* @throws PortalException if a dict item group with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DictItemGroup getDictItemGroup(long dictItemGroupId)
		throws PortalException;

	/**
	* Returns the dict item group matching the UUID and group.
	*
	* @param uuid the dict item group's UUID
	* @param groupId the primary key of the group
	* @return the matching dict item group
	* @throws PortalException if a matching dict item group could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DictItemGroup getDictItemGroupByUuidAndGroupId(String uuid,
		long groupId) throws PortalException;

	/**
	* Returns a range of all the dict item groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.DictItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict item groups
	* @param end the upper bound of the range of dict item groups (not inclusive)
	* @return the range of dict item groups
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DictItemGroup> getDictItemGroups(int start, int end);

	/**
	* Returns all the dict item groups matching the UUID and company.
	*
	* @param uuid the UUID of the dict item groups
	* @param companyId the primary key of the company
	* @return the matching dict item groups, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DictItemGroup> getDictItemGroupsByUuidAndCompanyId(
		String uuid, long companyId);

	/**
	* Returns a range of dict item groups matching the UUID and company.
	*
	* @param uuid the UUID of the dict item groups
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of dict item groups
	* @param end the upper bound of the range of dict item groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching dict item groups, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DictItemGroup> getDictItemGroupsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<DictItemGroup> orderByComparator);

	/**
	* Returns the number of dict item groups.
	*
	* @return the number of dict item groups
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getDictItemGroupsCount();

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

	public Hits luceneSearchEngine(LinkedHashMap<String, Object> params,
		Sort[] sorts, int start, int end, SearchContext searchContext)
		throws ParseException, SearchException;

	/**
	* Updates the dict item group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dictItemGroup the dict item group
	* @return the dict item group that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public DictItemGroup updateDictItemGroup(DictItemGroup dictItemGroup);

	/**
	* @author binhth
	* @param userId
	* @param dictItemGroupId
	* @param dictItemId
	* @param serviceContext
	* @return DictItemGroup
	* @throws DuplicateCategoryException
	* @throws UnauthenticationException
	* @throws UnauthorizationException
	* @throws NoSuchUserException
	*/
	@Indexable(type = IndexableType.REINDEX)
	public DictItemGroup updateDictItemGroup(long userId, long dictItemGroupId,
		long dictItemId, ServiceContext serviceContext)
		throws UnauthenticationException, UnauthorizationException,
			NotFoundException, NoSuchUserException, DuplicateCategoryException;
}