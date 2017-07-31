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

package org.mobilink.backend.datamgt.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LabelLocalService}.
 *
 * @author Binhth
 * @see LabelLocalService
 * @generated
 */
@ProviderType
public class LabelLocalServiceWrapper implements LabelLocalService,
	ServiceWrapper<LabelLocalService> {
	public LabelLocalServiceWrapper(LabelLocalService labelLocalService) {
		_labelLocalService = labelLocalService;
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _labelLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _labelLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _labelLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _labelLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _labelLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _labelLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits luceneSearchEngine(
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _labelLocalService.luceneSearchEngine(params, sorts, start, end,
			searchContext);
	}

	/**
	* Returns the number of labels.
	*
	* @return the number of labels
	*/
	@Override
	public int getLabelsCount() {
		return _labelLocalService.getLabelsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _labelLocalService.getOSGiServiceIdentifier();
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
		return _labelLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.datamgt.model.impl.LabelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _labelLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.datamgt.model.impl.LabelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _labelLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the labels.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.mobilink.backend.datamgt.model.impl.LabelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of labels
	* @param end the upper bound of the range of labels (not inclusive)
	* @return the range of labels
	*/
	@Override
	public java.util.List<org.mobilink.backend.datamgt.model.Label> getLabels(
		int start, int end) {
		return _labelLocalService.getLabels(start, end);
	}

	/**
	* Returns all the labels matching the UUID and company.
	*
	* @param uuid the UUID of the labels
	* @param companyId the primary key of the company
	* @return the matching labels, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.mobilink.backend.datamgt.model.Label> getLabelsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return _labelLocalService.getLabelsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of labels matching the UUID and company.
	*
	* @param uuid the UUID of the labels
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of labels
	* @param end the upper bound of the range of labels (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching labels, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.mobilink.backend.datamgt.model.Label> getLabelsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.mobilink.backend.datamgt.model.Label> orderByComparator) {
		return _labelLocalService.getLabelsByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
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
		return _labelLocalService.dynamicQueryCount(dynamicQuery);
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
		return _labelLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	/**
	* Adds the label to the database. Also notifies the appropriate model listeners.
	*
	* @param label the label
	* @return the label that was added
	*/
	@Override
	public org.mobilink.backend.datamgt.model.Label addLabel(
		org.mobilink.backend.datamgt.model.Label label) {
		return _labelLocalService.addLabel(label);
	}

	@Override
	public org.mobilink.backend.datamgt.model.Label addLable(long userId,
		long groupId, java.lang.String name, java.lang.String color, int scope,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws java.lang.Exception {
		return _labelLocalService.addLable(userId, groupId, name, color, scope,
			serviceContext);
	}

	/**
	* Creates a new label with the primary key. Does not add the label to the database.
	*
	* @param labelId the primary key for the new label
	* @return the new label
	*/
	@Override
	public org.mobilink.backend.datamgt.model.Label createLabel(long labelId) {
		return _labelLocalService.createLabel(labelId);
	}

	/**
	* Deletes the label with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param labelId the primary key of the label
	* @return the label that was removed
	* @throws PortalException if a label with the primary key could not be found
	*/
	@Override
	public org.mobilink.backend.datamgt.model.Label deleteLabel(long labelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _labelLocalService.deleteLabel(labelId);
	}

	/**
	* @param dictCollectionId
	* @param serviceContext
	* @return
	* @throws Exception
	*/
	@Override
	public org.mobilink.backend.datamgt.model.Label deleteLabel(long labelId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws java.lang.Exception {
		return _labelLocalService.deleteLabel(labelId, serviceContext);
	}

	/**
	* Deletes the label from the database. Also notifies the appropriate model listeners.
	*
	* @param label the label
	* @return the label that was removed
	*/
	@Override
	public org.mobilink.backend.datamgt.model.Label deleteLabel(
		org.mobilink.backend.datamgt.model.Label label) {
		return _labelLocalService.deleteLabel(label);
	}

	@Override
	public org.mobilink.backend.datamgt.model.Label fetchLabel(long labelId) {
		return _labelLocalService.fetchLabel(labelId);
	}

	/**
	* Returns the label matching the UUID and group.
	*
	* @param uuid the label's UUID
	* @param groupId the primary key of the group
	* @return the matching label, or <code>null</code> if a matching label could not be found
	*/
	@Override
	public org.mobilink.backend.datamgt.model.Label fetchLabelByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return _labelLocalService.fetchLabelByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the label with the primary key.
	*
	* @param labelId the primary key of the label
	* @return the label
	* @throws PortalException if a label with the primary key could not be found
	*/
	@Override
	public org.mobilink.backend.datamgt.model.Label getLabel(long labelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _labelLocalService.getLabel(labelId);
	}

	/**
	* Returns the label matching the UUID and group.
	*
	* @param uuid the label's UUID
	* @param groupId the primary key of the group
	* @return the matching label
	* @throws PortalException if a matching label could not be found
	*/
	@Override
	public org.mobilink.backend.datamgt.model.Label getLabelByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _labelLocalService.getLabelByUuidAndGroupId(uuid, groupId);
	}

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
	@Override
	public org.mobilink.backend.datamgt.model.Label updateLabel(long userId,
		long groupId, long labelId, java.lang.String name,
		java.lang.String color, int scope,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws java.lang.Exception {
		return _labelLocalService.updateLabel(userId, groupId, labelId, name,
			color, scope, serviceContext);
	}

	/**
	* Updates the label in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param label the label
	* @return the label that was updated
	*/
	@Override
	public org.mobilink.backend.datamgt.model.Label updateLabel(
		org.mobilink.backend.datamgt.model.Label label) {
		return _labelLocalService.updateLabel(label);
	}

	@Override
	public LabelLocalService getWrappedService() {
		return _labelLocalService;
	}

	@Override
	public void setWrappedService(LabelLocalService labelLocalService) {
		_labelLocalService = labelLocalService;
	}

	private LabelLocalService _labelLocalService;
}