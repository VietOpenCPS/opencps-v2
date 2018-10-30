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

import org.opencps.datamgt.exception.NoSuchDictItemException;
import org.opencps.datamgt.model.DictItem;

import java.io.Serializable;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Provides the local service interface for DictItem. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author khoavu
 * @see DictItemLocalServiceUtil
 * @see org.opencps.datamgt.service.base.DictItemLocalServiceBaseImpl
 * @see org.opencps.datamgt.service.impl.DictItemLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface DictItemLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DictItemLocalServiceUtil} to access the dict item local service. Add custom service methods to {@link org.opencps.datamgt.service.impl.DictItemLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the dict item to the database. Also notifies the appropriate model listeners.
	*
	* @param dictItem the dict item
	* @return the dict item that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public DictItem addDictItem(DictItem dictItem);

	@Indexable(type = IndexableType.REINDEX)
	public DictItem addDictItem(long userId, long groupId,
		long dictCollectionId, String itemCode, String itemName,
		String itemNameEN, String itemDescription, long parentItemId,
		String sibling, int level, String metaData,
		ServiceContext serviceContext)
		throws DuplicateCategoryException, UnauthenticationException,
			UnauthorizationException, NoSuchUserException,
			NoSuchDictItemException, SystemException;

	@Indexable(type = IndexableType.REINDEX)
	public DictItem adminProcessData(JSONObject objectData);

	@Indexable(type = IndexableType.DELETE)
	public DictItem adminProcessDelete(Long id);

	public long countByOlderThanDate(Date date, long groupId);

	public long countLuceneSearchEngine(LinkedHashMap<String, Object> params,
		SearchContext searchContext) throws ParseException, SearchException;

	/**
	* Creates a new dict item with the primary key. Does not add the dict item to the database.
	*
	* @param dictItemId the primary key for the new dict item
	* @return the new dict item
	*/
	@Transactional(enabled = false)
	public DictItem createDictItem(long dictItemId);

	/**
	* Deletes the dict item from the database. Also notifies the appropriate model listeners.
	*
	* @param dictItem the dict item
	* @return the dict item that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public DictItem deleteDictItem(DictItem dictItem);

	/**
	* Deletes the dict item with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dictItemId the primary key of the dict item
	* @return the dict item that was removed
	* @throws PortalException if a dict item with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public DictItem deleteDictItem(long dictItemId) throws PortalException;

	@Indexable(type = IndexableType.DELETE)
	public DictItem deleteDictItem(long dictItemId,
		ServiceContext serviceContext)
		throws UnauthenticationException, UnauthorizationException,
			NotFoundException;

	public void deleteDictItem(long groupId, String itemCode,
		ServiceContext serviceContext)
		throws DuplicateCategoryException, UnauthenticationException,
			UnauthorizationException, NoSuchUserException, NotFoundException,
			PortalException;

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public DictItem fetchByF_dictItemCode(String itemCode,
		long dictCollectionId, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DictItem fetchDictItem(long dictItemId);

	/**
	* Returns the dict item matching the UUID and group.
	*
	* @param uuid the dict item's UUID
	* @param groupId the primary key of the group
	* @return the matching dict item, or <code>null</code> if a matching dict item could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DictItem fetchDictItemByUuidAndGroupId(String uuid, long groupId);

	public List<DictItem> findByF_dictCollectionId(long dictCollectionId);

	public List<DictItem> findByF_dictCollectionId(long dictCollectionId,
		int stat, int end, OrderByComparator<DictItem> comparator);

	public List<DictItem> findByF_dictCollectionId_parentItemId(
		long dictCollectionId, long parentItemId);

	/**
	* @param parentItemId
	* @return
	*/
	public List<DictItem> findByF_parentItemId(long parentItemId);

	public List<DictItem> findByF_treeIndex(long dictCollectionId,
		long parentItemId, String treeIndex,
		OrderByComparator<DictItem> comparator);

	public List<DictItem> findByOlderThanDate(Date date, long groupId,
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	/**
	* Returns the dict item with the primary key.
	*
	* @param dictItemId the primary key of the dict item
	* @return the dict item
	* @throws PortalException if a dict item with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DictItem getDictItem(long dictItemId) throws PortalException;

	/**
	* Returns the dict item matching the UUID and group.
	*
	* @param uuid the dict item's UUID
	* @param groupId the primary key of the group
	* @return the matching dict item
	* @throws PortalException if a matching dict item could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DictItem getDictItemByUuidAndGroupId(String uuid, long groupId)
		throws PortalException;

	/**
	* Returns a range of all the dict items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.DictItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @return the range of dict items
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DictItem> getDictItems(int start, int end);

	/**
	* Returns all the dict items matching the UUID and company.
	*
	* @param uuid the UUID of the dict items
	* @param companyId the primary key of the company
	* @return the matching dict items, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DictItem> getDictItemsByUuidAndCompanyId(String uuid,
		long companyId);

	/**
	* Returns a range of dict items matching the UUID and company.
	*
	* @param uuid the UUID of the dict items
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of dict items
	* @param end the upper bound of the range of dict items (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching dict items, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DictItem> getDictItemsByUuidAndCompanyId(String uuid,
		long companyId, int start, int end,
		OrderByComparator<DictItem> orderByComparator);

	/**
	* Returns the number of dict items.
	*
	* @return the number of dict items
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getDictItemsCount();

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

	public boolean initDictItem(long groupId, long dictCollectionId);

	public Hits luceneSearchEngine(LinkedHashMap<String, Object> params,
		Sort[] sorts, int start, int end, SearchContext searchContext)
		throws ParseException, SearchException;

	/**
	* Updates the dict item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dictItem the dict item
	* @return the dict item that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public DictItem updateDictItem(DictItem dictItem);

	/**
	* @param userId
	* @param dictCollectionId
	* @param fullName
	* @param companyName
	* @param telNo
	* @param email
	* @param mobilinkId
	* @param userMappingId
	* @param outSide
	* @param isOrg
	* @param serviceContext
	* @return
	* @throws Exception
	*/
	@Indexable(type = IndexableType.REINDEX)
	public DictItem updateDictItem(long userId, long dictItemId,
		long dictCollectionId, String itemCode, String itemName,
		String itemNameEN, String itemDescription, long parentItemId,
		String sibling, int level, String metaData,
		ServiceContext serviceContext)
		throws DuplicateCategoryException, UnauthenticationException,
			UnauthorizationException, NoSuchUserException, NotFoundException,
			PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public DictItem updateDictItemDB(long userId, long groupId,
		long dictCollectionId, String itemCode, String itemName,
		String itemNameEN, String itemDescription, long dictItemParentId,
		Integer level, String sibling, String metadata)
		throws NoSuchUserException;

	@Indexable(type = IndexableType.REINDEX)
	public DictItem updateDictItemListener(long userId, long dictItemId,
		long dictCollectionId, String itemCode, String itemName,
		String itemNameEN, String itemDescription, long parentItemId,
		String sibling, int level, String metaData,
		ServiceContext serviceContext)
		throws DuplicateCategoryException, UnauthenticationException,
			UnauthorizationException, NoSuchUserException, NotFoundException,
			PortalException;
}