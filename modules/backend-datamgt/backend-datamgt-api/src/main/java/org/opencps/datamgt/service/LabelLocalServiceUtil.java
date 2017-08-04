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

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for Label. This utility wraps
 * {@link org.mobilink.backend.datamgt.service.impl.LabelLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Binhth
 * @see LabelLocalService
 * @see org.mobilink.backend.datamgt.service.base.LabelLocalServiceBaseImpl
 * @see org.mobilink.backend.datamgt.service.impl.LabelLocalServiceImpl
 * @generated
 */
@ProviderType
public class LabelLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.mobilink.backend.datamgt.service.impl.LabelLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	public static com.liferay.portal.kernel.search.Hits luceneSearchEngine(
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return getService()
				   .luceneSearchEngine(params, sorts, start, end, searchContext);
	}

	/**
	* Returns the number of labels.
	*
	* @return the number of labels
	*/
	public static int getLabelsCount() {
		return getService().getLabelsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
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
	public static java.util.List<org.opencps.datamgt.model.Label> getLabels(
		int start, int end) {
		return getService().getLabels(start, end);
	}

	/**
	* Returns all the labels matching the UUID and company.
	*
	* @param uuid the UUID of the labels
	* @param companyId the primary key of the company
	* @return the matching labels, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.datamgt.model.Label> getLabelsByUuidAndCompanyId(
		java.lang.String uuid, long companyId) {
		return getService().getLabelsByUuidAndCompanyId(uuid, companyId);
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
	public static java.util.List<org.opencps.datamgt.model.Label> getLabelsByUuidAndCompanyId(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.datamgt.model.Label> orderByComparator) {
		return getService()
				   .getLabelsByUuidAndCompanyId(uuid, companyId, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	/**
	* Adds the label to the database. Also notifies the appropriate model listeners.
	*
	* @param label the label
	* @return the label that was added
	*/
	public static org.opencps.datamgt.model.Label addLabel(
		org.opencps.datamgt.model.Label label) {
		return getService().addLabel(label);
	}

	public static org.opencps.datamgt.model.Label addLable(
		long userId, long groupId, java.lang.String name,
		java.lang.String color, int scope,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws java.lang.Exception {
		return getService()
				   .addLable(userId, groupId, name, color, scope, serviceContext);
	}

	/**
	* Creates a new label with the primary key. Does not add the label to the database.
	*
	* @param labelId the primary key for the new label
	* @return the new label
	*/
	public static org.opencps.datamgt.model.Label createLabel(
		long labelId) {
		return getService().createLabel(labelId);
	}

	/**
	* Deletes the label with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param labelId the primary key of the label
	* @return the label that was removed
	* @throws PortalException if a label with the primary key could not be found
	*/
	public static org.opencps.datamgt.model.Label deleteLabel(
		long labelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteLabel(labelId);
	}

	/**
	* @param dictCollectionId
	* @param serviceContext
	* @return
	* @throws Exception
	*/
	public static org.opencps.datamgt.model.Label deleteLabel(
		long labelId,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws java.lang.Exception {
		return getService().deleteLabel(labelId, serviceContext);
	}

	/**
	* Deletes the label from the database. Also notifies the appropriate model listeners.
	*
	* @param label the label
	* @return the label that was removed
	*/
	public static org.opencps.datamgt.model.Label deleteLabel(
		org.opencps.datamgt.model.Label label) {
		return getService().deleteLabel(label);
	}

	public static org.opencps.datamgt.model.Label fetchLabel(
		long labelId) {
		return getService().fetchLabel(labelId);
	}

	/**
	* Returns the label matching the UUID and group.
	*
	* @param uuid the label's UUID
	* @param groupId the primary key of the group
	* @return the matching label, or <code>null</code> if a matching label could not be found
	*/
	public static org.opencps.datamgt.model.Label fetchLabelByUuidAndGroupId(
		java.lang.String uuid, long groupId) {
		return getService().fetchLabelByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns the label with the primary key.
	*
	* @param labelId the primary key of the label
	* @return the label
	* @throws PortalException if a label with the primary key could not be found
	*/
	public static org.opencps.datamgt.model.Label getLabel(
		long labelId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLabel(labelId);
	}

	/**
	* Returns the label matching the UUID and group.
	*
	* @param uuid the label's UUID
	* @param groupId the primary key of the group
	* @return the matching label
	* @throws PortalException if a matching label could not be found
	*/
	public static org.opencps.datamgt.model.Label getLabelByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLabelByUuidAndGroupId(uuid, groupId);
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
	public static org.opencps.datamgt.model.Label updateLabel(
		long userId, long groupId, long labelId, java.lang.String name,
		java.lang.String color, int scope,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws java.lang.Exception {
		return getService()
				   .updateLabel(userId, groupId, labelId, name, color, scope,
			serviceContext);
	}

	/**
	* Updates the label in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param label the label
	* @return the label that was updated
	*/
	public static org.opencps.datamgt.model.Label updateLabel(
		org.opencps.datamgt.model.Label label) {
		return getService().updateLabel(label);
	}

	public static LabelLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LabelLocalService, LabelLocalService> _serviceTracker =
		ServiceTrackerFactory.open(LabelLocalService.class);
}