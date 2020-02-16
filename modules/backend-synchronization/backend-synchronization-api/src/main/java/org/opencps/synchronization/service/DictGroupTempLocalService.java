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

import org.opencps.synchronization.model.DictGroupTemp;

import java.io.Serializable;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Provides the local service interface for DictGroupTemp. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author trungdk
 * @see DictGroupTempLocalServiceUtil
 * @see org.opencps.synchronization.service.base.DictGroupTempLocalServiceBaseImpl
 * @see org.opencps.synchronization.service.impl.DictGroupTempLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface DictGroupTempLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DictGroupTempLocalServiceUtil} to access the dict group temp local service. Add custom service methods to {@link org.opencps.synchronization.service.impl.DictGroupTempLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the dict group temp to the database. Also notifies the appropriate model listeners.
	*
	* @param dictGroupTemp the dict group temp
	* @return the dict group temp that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public DictGroupTemp addDictGroupTemp(DictGroupTemp dictGroupTemp);

	@Indexable(type = IndexableType.REINDEX)
	public DictGroupTemp addDictGroupTemp(long userId, long groupId,
		long dictCollectionId, String groupCode, String groupName,
		String groupNameEN, String groupDescription, int status,
		ServiceContext serviceContext)
		throws DuplicateCategoryException, UnauthenticationException,
			UnauthorizationException, NoSuchUserException;

	public long countLuceneSearchEngine(LinkedHashMap<String, Object> params,
		SearchContext searchContext) throws ParseException, SearchException;

	public long countOlderThanDate(Date date, long groupId);

	/**
	* Creates a new dict group temp with the primary key. Does not add the dict group temp to the database.
	*
	* @param dictGroupId the primary key for the new dict group temp
	* @return the new dict group temp
	*/
	@Transactional(enabled = false)
	public DictGroupTemp createDictGroupTemp(long dictGroupId);

	/**
	* Deletes the dict group temp from the database. Also notifies the appropriate model listeners.
	*
	* @param dictGroupTemp the dict group temp
	* @return the dict group temp that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public DictGroupTemp deleteDictGroupTemp(DictGroupTemp dictGroupTemp);

	/**
	* Deletes the dict group temp with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dictGroupId the primary key of the dict group temp
	* @return the dict group temp that was removed
	* @throws PortalException if a dict group temp with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public DictGroupTemp deleteDictGroupTemp(long dictGroupId)
		throws PortalException;

	/**
	* @author binhth
	* @param dictGroupId
	* @param serviceContext
	*/
	@Indexable(type = IndexableType.DELETE)
	public DictGroupTemp deleteDictGroupTemp(long dictGroupId,
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synchronization.model.impl.DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synchronization.model.impl.DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* @param groupCode
	* @param groupId
	* @return DictGroupTemp
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DictGroupTemp fetchByF_DictGroupCode(String groupCode, long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DictGroupTemp fetchDictGroupTemp(long dictGroupId);

	/**
	* Returns the dict group temp matching the UUID and group.
	*
	* @param uuid the dict group temp's UUID
	* @param groupId the primary key of the group
	* @return the matching dict group temp, or <code>null</code> if a matching dict group temp could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DictGroupTemp fetchDictGroupTempByUuidAndGroupId(String uuid,
		long groupId);

	public List<DictGroupTemp> findOlderThanDate(Date date, long groupId,
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DictGroupTemp getByGC_GI_DCI(String groupCode, long groupId,
		long dictCollectionId);

	/**
	* Returns the dict group temp with the primary key.
	*
	* @param dictGroupId the primary key of the dict group temp
	* @return the dict group temp
	* @throws PortalException if a dict group temp with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DictGroupTemp getDictGroupTemp(long dictGroupId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DictGroupTemp> getDictGroupTempByDictCollection(long groupId,
		long dictCollectionId, int start, int end);

	/**
	* Returns the dict group temp matching the UUID and group.
	*
	* @param uuid the dict group temp's UUID
	* @param groupId the primary key of the group
	* @return the matching dict group temp
	* @throws PortalException if a matching dict group temp could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DictGroupTemp getDictGroupTempByUuidAndGroupId(String uuid,
		long groupId) throws PortalException;

	/**
	* Returns a range of all the dict group temps.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.synchronization.model.impl.DictGroupTempModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict group temps
	* @param end the upper bound of the range of dict group temps (not inclusive)
	* @return the range of dict group temps
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DictGroupTemp> getDictGroupTemps(int start, int end);

	/**
	* Returns all the dict group temps matching the UUID and company.
	*
	* @param uuid the UUID of the dict group temps
	* @param companyId the primary key of the company
	* @return the matching dict group temps, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DictGroupTemp> getDictGroupTempsByUuidAndCompanyId(
		String uuid, long companyId);

	/**
	* Returns a range of dict group temps matching the UUID and company.
	*
	* @param uuid the UUID of the dict group temps
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of dict group temps
	* @param end the upper bound of the range of dict group temps (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching dict group temps, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DictGroupTemp> getDictGroupTempsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		OrderByComparator<DictGroupTemp> orderByComparator);

	/**
	* Returns the number of dict group temps.
	*
	* @return the number of dict group temps
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getDictGroupTempsCount();

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
	public Hits luceneSearchEngine(LinkedHashMap<String, Object> params,
		Sort[] sorts, int start, int end, SearchContext searchContext)
		throws ParseException, SearchException;

	/**
	* Updates the dict group temp in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dictGroupTemp the dict group temp
	* @return the dict group temp that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public DictGroupTemp updateDictGroupTemp(DictGroupTemp dictGroupTemp);

	/**
	* @author binhth
	* @param userId
	* @param dictGroupId
	* @param dictCollectionId
	* @param groupCode
	* @param groupName
	* @param groupNameEN
	* @param groupDescription
	* @param serviceContext
	* @return DictGroupTemp
	* @throws DuplicateCategoryException
	* @throws UnauthenticationException
	* @throws UnauthorizationException
	* @throws NoSuchUserException
	*/
	@Indexable(type = IndexableType.REINDEX)
	public DictGroupTemp updateDictGroupTemp(long userId, long dictGroupId,
		long dictCollectionId, String groupCode, String groupName,
		String groupNameEN, String groupDescription, int status,
		ServiceContext serviceContext)
		throws UnauthenticationException, UnauthorizationException,
			NotFoundException, NoSuchUserException, DuplicateCategoryException;
}