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

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.datamgt.model.DictItemMapping;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for DictItemMapping. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author khoavu
 * @see DictItemMappingLocalServiceUtil
 * @see org.opencps.datamgt.service.base.DictItemMappingLocalServiceBaseImpl
 * @see org.opencps.datamgt.service.impl.DictItemMappingLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface DictItemMappingLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DictItemMappingLocalServiceUtil} to access the dict item mapping local service. Add custom service methods to {@link org.opencps.datamgt.service.impl.DictItemMappingLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the dict item mapping to the database. Also notifies the appropriate model listeners.
	*
	* @param dictItemMapping the dict item mapping
	* @return the dict item mapping that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public DictItemMapping addDictItemMapping(DictItemMapping dictItemMapping);

	@Indexable(type = IndexableType.REINDEX)
	public DictItemMapping adminProcessData(JSONObject objectData);

	@Indexable(type = IndexableType.DELETE)
	public DictItemMapping adminProcessDelete(Long id);

	/**
	* Creates a new dict item mapping with the primary key. Does not add the dict item mapping to the database.
	*
	* @param mappingId the primary key for the new dict item mapping
	* @return the new dict item mapping
	*/
	@Transactional(enabled = false)
	public DictItemMapping createDictItemMapping(long mappingId);

	public DictItemMapping createDictItemMapping(long companyId, long groupId,
		long userId, String itemCode, String itemCodeDVCQG, long collectionId);

	/**
	* Deletes the dict item mapping from the database. Also notifies the appropriate model listeners.
	*
	* @param dictItemMapping the dict item mapping
	* @return the dict item mapping that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public DictItemMapping deleteDictItemMapping(
		DictItemMapping dictItemMapping);

	/**
	* Deletes the dict item mapping with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param mappingId the primary key of the dict item mapping
	* @return the dict item mapping that was removed
	* @throws PortalException if a dict item mapping with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public DictItemMapping deleteDictItemMapping(long mappingId)
		throws PortalException;

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.DictItemMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.DictItemMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	public DictItemMapping fetchByF_GID_IC_CID(long groupId, String itemCode,
		long collectionId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DictItemMapping fetchByF_GID_ICDVCQG_CID(long groupId,
		String itemCodeDVCQG, long collectionId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DictItemMapping fetchByF_IC(String itemCode);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DictItemMapping fetchDictItemMapping(long mappingId);

	public List<DictItemMapping> findByF_GID_CID(long groupId, long collectionId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	/**
	* Returns the dict item mapping with the primary key.
	*
	* @param mappingId the primary key of the dict item mapping
	* @return the dict item mapping
	* @throws PortalException if a dict item mapping with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DictItemMapping getDictItemMapping(long mappingId)
		throws PortalException;

	/**
	* Returns a range of all the dict item mappings.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.datamgt.model.impl.DictItemMappingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict item mappings
	* @param end the upper bound of the range of dict item mappings (not inclusive)
	* @return the range of dict item mappings
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DictItemMapping> getDictItemMappings(int start, int end);

	/**
	* Returns the number of dict item mappings.
	*
	* @return the number of dict item mappings
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getDictItemMappingsCount();

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
	* Updates the dict item mapping in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dictItemMapping the dict item mapping
	* @return the dict item mapping that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public DictItemMapping updateDictItemMapping(
		DictItemMapping dictItemMapping);
}