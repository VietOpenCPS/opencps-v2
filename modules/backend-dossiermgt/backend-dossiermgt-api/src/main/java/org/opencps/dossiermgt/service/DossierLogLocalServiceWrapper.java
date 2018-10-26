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

package org.opencps.dossiermgt.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DossierLogLocalService}.
 *
 * @author huymq
 * @see DossierLogLocalService
 * @generated
 */
@ProviderType
public class DossierLogLocalServiceWrapper implements DossierLogLocalService,
	ServiceWrapper<DossierLogLocalService> {
	public DossierLogLocalServiceWrapper(
		DossierLogLocalService dossierLogLocalService) {
		_dossierLogLocalService = dossierLogLocalService;
	}

	/**
	* Adds the dossier log to the database. Also notifies the appropriate model listeners.
	*
	* @param dossierLog the dossier log
	* @return the dossier log that was added
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierLog addDossierLog(
		org.opencps.dossiermgt.model.DossierLog dossierLog) {
		return _dossierLogLocalService.addDossierLog(dossierLog);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierLog addDossierLog(long groupId,
		long dossierId, String author, String content, String notificationType,
		String payload,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dossierLogLocalService.addDossierLog(groupId, dossierId,
			author, content, notificationType, payload, serviceContext);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierLog adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return _dossierLogLocalService.adminProcessData(objectData);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierLog adminProcessDelete(Long id) {
		return _dossierLogLocalService.adminProcessDelete(id);
	}

	@Override
	public long countLucene(java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _dossierLogLocalService.countLucene(params, searchContext);
	}

	/**
	* Creates a new dossier log with the primary key. Does not add the dossier log to the database.
	*
	* @param dossierLogId the primary key for the new dossier log
	* @return the new dossier log
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierLog createDossierLog(
		long dossierLogId) {
		return _dossierLogLocalService.createDossierLog(dossierLogId);
	}

	/**
	* Deletes the dossier log from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierLog the dossier log
	* @return the dossier log that was removed
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierLog deleteDossierLog(
		org.opencps.dossiermgt.model.DossierLog dossierLog) {
		return _dossierLogLocalService.deleteDossierLog(dossierLog);
	}

	/**
	* Deletes the dossier log with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierLogId the primary key of the dossier log
	* @return the dossier log that was removed
	* @throws PortalException if a dossier log with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierLog deleteDossierLog(
		long dossierLogId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierLogLocalService.deleteDossierLog(dossierLogId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierLogLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _dossierLogLocalService.dynamicQuery();
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
		return _dossierLogLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dossierLogLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dossierLogLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _dossierLogLocalService.dynamicQueryCount(dynamicQuery);
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
		return _dossierLogLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierLog fetchDossierLog(
		long dossierLogId) {
		return _dossierLogLocalService.fetchDossierLog(dossierLogId);
	}

	/**
	* Returns the dossier log matching the UUID and group.
	*
	* @param uuid the dossier log's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier log, or <code>null</code> if a matching dossier log could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierLog fetchDossierLogByUuidAndGroupId(
		String uuid, long groupId) {
		return _dossierLogLocalService.fetchDossierLogByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierLog> findByGroup(
		long groupId) {
		return _dossierLogLocalService.findByGroup(groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _dossierLogLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierLog> getByDossierAndType(
		long dossierId, String type, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierLogLocalService.getByDossierAndType(dossierId, type,
			start, end);
	}

	/**
	* Returns the dossier log with the primary key.
	*
	* @param dossierLogId the primary key of the dossier log
	* @return the dossier log
	* @throws PortalException if a dossier log with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierLog getDossierLog(
		long dossierLogId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierLogLocalService.getDossierLog(dossierLogId);
	}

	/**
	* Returns the dossier log matching the UUID and group.
	*
	* @param uuid the dossier log's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier log
	* @throws PortalException if a matching dossier log could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierLog getDossierLogByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierLogLocalService.getDossierLogByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the dossier logs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier logs
	* @param end the upper bound of the range of dossier logs (not inclusive)
	* @return the range of dossier logs
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierLog> getDossierLogs(
		int start, int end) {
		return _dossierLogLocalService.getDossierLogs(start, end);
	}

	/**
	* Returns all the dossier logs matching the UUID and company.
	*
	* @param uuid the UUID of the dossier logs
	* @param companyId the primary key of the company
	* @return the matching dossier logs, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierLog> getDossierLogsByUuidAndCompanyId(
		String uuid, long companyId) {
		return _dossierLogLocalService.getDossierLogsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of dossier logs matching the UUID and company.
	*
	* @param uuid the UUID of the dossier logs
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of dossier logs
	* @param end the upper bound of the range of dossier logs (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching dossier logs, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierLog> getDossierLogsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.DossierLog> orderByComparator) {
		return _dossierLogLocalService.getDossierLogsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of dossier logs.
	*
	* @return the number of dossier logs
	*/
	@Override
	public int getDossierLogsCount() {
		return _dossierLogLocalService.getDossierLogsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _dossierLogLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _dossierLogLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _dossierLogLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierLogLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits searchLucene(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _dossierLogLocalService.searchLucene(params, sorts, start, end,
			searchContext);
	}

	/**
	* Updates the dossier log in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dossierLog the dossier log
	* @return the dossier log that was updated
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierLog updateDossierLog(
		org.opencps.dossiermgt.model.DossierLog dossierLog) {
		return _dossierLogLocalService.updateDossierLog(dossierLog);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierLog updateDossierLog(
		long groupId, long dossierId, long dossierLogId, String author,
		String content, String notificationType, String payload,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _dossierLogLocalService.updateDossierLog(groupId, dossierId,
			dossierLogId, author, content, notificationType, payload,
			serviceContext);
	}

	@Override
	public DossierLogLocalService getWrappedService() {
		return _dossierLogLocalService;
	}

	@Override
	public void setWrappedService(DossierLogLocalService dossierLogLocalService) {
		_dossierLogLocalService = dossierLogLocalService;
	}

	private DossierLogLocalService _dossierLogLocalService;
}