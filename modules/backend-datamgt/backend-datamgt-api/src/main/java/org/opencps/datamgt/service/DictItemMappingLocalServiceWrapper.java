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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DictItemMappingLocalService}.
 *
 * @author khoavu
 * @see DictItemMappingLocalService
 * @generated
 */
@ProviderType
public class DictItemMappingLocalServiceWrapper
	implements DictItemMappingLocalService,
		ServiceWrapper<DictItemMappingLocalService> {
	public DictItemMappingLocalServiceWrapper(
		DictItemMappingLocalService dictItemMappingLocalService) {
		_dictItemMappingLocalService = dictItemMappingLocalService;
	}

	/**
	* Adds the dict item mapping to the database. Also notifies the appropriate model listeners.
	*
	* @param dictItemMapping the dict item mapping
	* @return the dict item mapping that was added
	*/
	@Override
	public org.opencps.datamgt.model.DictItemMapping addDictItemMapping(
		org.opencps.datamgt.model.DictItemMapping dictItemMapping) {
		return _dictItemMappingLocalService.addDictItemMapping(dictItemMapping);
	}

	/**
	* Creates a new dict item mapping with the primary key. Does not add the dict item mapping to the database.
	*
	* @param mappingId the primary key for the new dict item mapping
	* @return the new dict item mapping
	*/
	@Override
	public org.opencps.datamgt.model.DictItemMapping createDictItemMapping(
		long mappingId) {
		return _dictItemMappingLocalService.createDictItemMapping(mappingId);
	}

	@Override
	public org.opencps.datamgt.model.DictItemMapping createDictItemMapping(
		long companyId, long groupId, long userId, String itemCode,
		String itemCodeDVCQG, long collectionId) {
		return _dictItemMappingLocalService.createDictItemMapping(companyId,
			groupId, userId, itemCode, itemCodeDVCQG, collectionId);
	}

	/**
	* Deletes the dict item mapping from the database. Also notifies the appropriate model listeners.
	*
	* @param dictItemMapping the dict item mapping
	* @return the dict item mapping that was removed
	*/
	@Override
	public org.opencps.datamgt.model.DictItemMapping deleteDictItemMapping(
		org.opencps.datamgt.model.DictItemMapping dictItemMapping) {
		return _dictItemMappingLocalService.deleteDictItemMapping(dictItemMapping);
	}

	/**
	* Deletes the dict item mapping with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param mappingId the primary key of the dict item mapping
	* @return the dict item mapping that was removed
	* @throws PortalException if a dict item mapping with the primary key could not be found
	*/
	@Override
	public org.opencps.datamgt.model.DictItemMapping deleteDictItemMapping(
		long mappingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dictItemMappingLocalService.deleteDictItemMapping(mappingId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dictItemMappingLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _dictItemMappingLocalService.dynamicQuery();
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
		return _dictItemMappingLocalService.dynamicQuery(dynamicQuery);
	}

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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _dictItemMappingLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _dictItemMappingLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _dictItemMappingLocalService.dynamicQueryCount(dynamicQuery);
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
		return _dictItemMappingLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.datamgt.model.DictItemMapping fetchByF_GID_IC_CID(
		long groupId, String itemCode, long collectionId) {
		return _dictItemMappingLocalService.fetchByF_GID_IC_CID(groupId,
			itemCode, collectionId);
	}

	@Override
	public org.opencps.datamgt.model.DictItemMapping fetchByF_GID_ICDVCQG_CID(
		long groupId, String itemCodeDVCQG, long collectionId) {
		return _dictItemMappingLocalService.fetchByF_GID_ICDVCQG_CID(groupId,
			itemCodeDVCQG, collectionId);
	}

	@Override
	public org.opencps.datamgt.model.DictItemMapping fetchDictItemMapping(
		long mappingId) {
		return _dictItemMappingLocalService.fetchDictItemMapping(mappingId);
	}

	@Override
	public java.util.List<org.opencps.datamgt.model.DictItemMapping> findByF_GID_CID(
		long groupId, long collectionId) {
		return _dictItemMappingLocalService.findByF_GID_CID(groupId,
			collectionId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _dictItemMappingLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the dict item mapping with the primary key.
	*
	* @param mappingId the primary key of the dict item mapping
	* @return the dict item mapping
	* @throws PortalException if a dict item mapping with the primary key could not be found
	*/
	@Override
	public org.opencps.datamgt.model.DictItemMapping getDictItemMapping(
		long mappingId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dictItemMappingLocalService.getDictItemMapping(mappingId);
	}

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
	@Override
	public java.util.List<org.opencps.datamgt.model.DictItemMapping> getDictItemMappings(
		int start, int end) {
		return _dictItemMappingLocalService.getDictItemMappings(start, end);
	}

	/**
	* Returns the number of dict item mappings.
	*
	* @return the number of dict item mappings
	*/
	@Override
	public int getDictItemMappingsCount() {
		return _dictItemMappingLocalService.getDictItemMappingsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _dictItemMappingLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _dictItemMappingLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dictItemMappingLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the dict item mapping in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dictItemMapping the dict item mapping
	* @return the dict item mapping that was updated
	*/
	@Override
	public org.opencps.datamgt.model.DictItemMapping updateDictItemMapping(
		org.opencps.datamgt.model.DictItemMapping dictItemMapping) {
		return _dictItemMappingLocalService.updateDictItemMapping(dictItemMapping);
	}

	@Override
	public DictItemMappingLocalService getWrappedService() {
		return _dictItemMappingLocalService;
	}

	@Override
	public void setWrappedService(
		DictItemMappingLocalService dictItemMappingLocalService) {
		_dictItemMappingLocalService = dictItemMappingLocalService;
	}

	private DictItemMappingLocalService _dictItemMappingLocalService;
}