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
 * Provides a wrapper for {@link DictGroupLocalService}.
 *
 * @author Binhth
 * @see DictGroupLocalService
 * @generated
 */
@ProviderType
public class DictGroupLocalServiceWrapper implements DictGroupLocalService,
	ServiceWrapper<DictGroupLocalService> {
	public DictGroupLocalServiceWrapper(
		DictGroupLocalService dictGroupLocalService) {
		_dictGroupLocalService = dictGroupLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _dictGroupLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _dictGroupLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _dictGroupLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _dictGroupLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dictGroupLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dictGroupLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of dict groups.
	*
	* @return the number of dict groups
	*/
	@Override
	public int getDictGroupsCount() {
		return _dictGroupLocalService.getDictGroupsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _dictGroupLocalService.getOSGiServiceIdentifier();
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
		return _dictGroupLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.datamgt.model.impl.DictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dictGroupLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.datamgt.model.impl.DictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dictGroupLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the dict groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.datamgt.model.impl.DictGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dict groups
	* @param end the upper bound of the range of dict groups (not inclusive)
	* @return the range of dict groups
	*/
	@Override
	public java.util.List<org.opencps.datamgt.model.DictGroup> getDictGroups(
		int start, int end) {
		return _dictGroupLocalService.getDictGroups(start, end);
	}

	/**
	* Returns all the dict groups matching the UUID and company.
	*
	* @param uuid the UUID of the dict groups
	* @param companyId the primary key of the company
	* @return the matching dict groups, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.datamgt.model.DictGroup> getDictGroupsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _dictGroupLocalService.getDictGroupsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of dict groups matching the UUID and company.
	*
	* @param uuid the UUID of the dict groups
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of dict groups
	* @param end the upper bound of the range of dict groups (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching dict groups, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.datamgt.model.DictGroup> getDictGroupsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.datamgt.model.DictGroup> orderByComparator) {
		return _dictGroupLocalService.getDictGroupsByUuidAndCompanyId(uuid,
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
		return _dictGroupLocalService.dynamicQueryCount(dynamicQuery);
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
		return _dictGroupLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	/**
	* Adds the dict group to the database. Also notifies the appropriate model listeners.
	*
	* @param dictGroup the dict group
	* @return the dict group that was added
	*/
	@Override
	public org.opencps.datamgt.model.DictGroup addDictGroup(
		org.opencps.datamgt.model.DictGroup dictGroup) {
		return _dictGroupLocalService.addDictGroup(dictGroup);
	}

	/**
	* Creates a new dict group with the primary key. Does not add the dict group to the database.
	*
	* @param dictGroupId the primary key for the new dict group
	* @return the new dict group
	*/
	@Override
	public org.opencps.datamgt.model.DictGroup createDictGroup(
		long dictGroupId) {
		return _dictGroupLocalService.createDictGroup(dictGroupId);
	}

	/**
	* Deletes the dict group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dictGroupId the primary key of the dict group
	* @return the dict group that was removed
	* @throws PortalException if a dict group with the primary key could not be found
	*/
	@Override
	public org.opencps.datamgt.model.DictGroup deleteDictGroup(
		long dictGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dictGroupLocalService.deleteDictGroup(dictGroupId);
	}

	/**
	* Deletes the dict group from the database. Also notifies the appropriate model listeners.
	*
	* @param dictGroup the dict group
	* @return the dict group that was removed
	*/
	@Override
	public org.opencps.datamgt.model.DictGroup deleteDictGroup(
		org.opencps.datamgt.model.DictGroup dictGroup) {
		return _dictGroupLocalService.deleteDictGroup(dictGroup);
	}

	@Override
	public org.opencps.datamgt.model.DictGroup fetchDictGroup(
		long dictGroupId) {
		return _dictGroupLocalService.fetchDictGroup(dictGroupId);
	}

	/**
	* Returns the dict group matching the UUID and group.
	*
	* @param uuid the dict group's UUID
	* @param groupId the primary key of the group
	* @return the matching dict group, or <code>null</code> if a matching dict group could not be found
	*/
	@Override
	public org.opencps.datamgt.model.DictGroup fetchDictGroupByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _dictGroupLocalService.fetchDictGroupByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns the dict group with the primary key.
	*
	* @param dictGroupId the primary key of the dict group
	* @return the dict group
	* @throws PortalException if a dict group with the primary key could not be found
	*/
	@Override
	public org.opencps.datamgt.model.DictGroup getDictGroup(
		long dictGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dictGroupLocalService.getDictGroup(dictGroupId);
	}

	/**
	* Returns the dict group matching the UUID and group.
	*
	* @param uuid the dict group's UUID
	* @param groupId the primary key of the group
	* @return the matching dict group
	* @throws PortalException if a matching dict group could not be found
	*/
	@Override
	public org.opencps.datamgt.model.DictGroup getDictGroupByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dictGroupLocalService.getDictGroupByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Updates the dict group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dictGroup the dict group
	* @return the dict group that was updated
	*/
	@Override
	public org.opencps.datamgt.model.DictGroup updateDictGroup(
		org.opencps.datamgt.model.DictGroup dictGroup) {
		return _dictGroupLocalService.updateDictGroup(dictGroup);
	}

	@Override
	public DictGroupLocalService getWrappedService() {
		return _dictGroupLocalService;
	}

	@Override
	public void setWrappedService(DictGroupLocalService dictGroupLocalService) {
		_dictGroupLocalService = dictGroupLocalService;
	}

	private DictGroupLocalService _dictGroupLocalService;
}