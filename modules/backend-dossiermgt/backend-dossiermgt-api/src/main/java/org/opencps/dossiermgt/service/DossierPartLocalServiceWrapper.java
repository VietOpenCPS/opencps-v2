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
 * Provides a wrapper for {@link DossierPartLocalService}.
 *
 * @author huymq
 * @see DossierPartLocalService
 * @generated
 */
@ProviderType
public class DossierPartLocalServiceWrapper implements DossierPartLocalService,
	ServiceWrapper<DossierPartLocalService> {
	public DossierPartLocalServiceWrapper(
		DossierPartLocalService dossierPartLocalService) {
		_dossierPartLocalService = dossierPartLocalService;
	}

	/**
	* Adds the dossier part to the database. Also notifies the appropriate model listeners.
	*
	* @param dossierPart the dossier part
	* @return the dossier part that was added
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierPart addDossierPart(
		org.opencps.dossiermgt.model.DossierPart dossierPart) {
		return _dossierPartLocalService.addDossierPart(dossierPart);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierPart adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return _dossierPartLocalService.adminProcessData(objectData);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierPart adminProcessDelete(Long id) {
		return _dossierPartLocalService.adminProcessDelete(id);
	}

	@Override
	public long countLucene(java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _dossierPartLocalService.countLucene(params, searchContext);
	}

	/**
	* Creates a new dossier part with the primary key. Does not add the dossier part to the database.
	*
	* @param dossierPartId the primary key for the new dossier part
	* @return the new dossier part
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierPart createDossierPart(
		long dossierPartId) {
		return _dossierPartLocalService.createDossierPart(dossierPartId);
	}

	/**
	* Deletes the dossier part from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierPart the dossier part
	* @return the dossier part that was removed
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierPart deleteDossierPart(
		org.opencps.dossiermgt.model.DossierPart dossierPart) {
		return _dossierPartLocalService.deleteDossierPart(dossierPart);
	}

	/**
	* Deletes the dossier part with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierPartId the primary key of the dossier part
	* @return the dossier part that was removed
	* @throws PortalException if a dossier part with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierPart deleteDossierPart(
		long dossierPartId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierPartLocalService.deleteDossierPart(dossierPartId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierPartLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _dossierPartLocalService.dynamicQuery();
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
		return _dossierPartLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierPartModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dossierPartLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierPartModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dossierPartLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _dossierPartLocalService.dynamicQueryCount(dynamicQuery);
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
		return _dossierPartLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierPart fetchByTemplatePartNo(
		long groupId, String templateNo, String partNo)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierPartLocalService.fetchByTemplatePartNo(groupId,
			templateNo, partNo);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierPart fetchDossierPart(
		long dossierPartId) {
		return _dossierPartLocalService.fetchDossierPart(dossierPartId);
	}

	/**
	* Returns the dossier part matching the UUID and group.
	*
	* @param uuid the dossier part's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier part, or <code>null</code> if a matching dossier part could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierPart fetchDossierPartByUuidAndGroupId(
		String uuid, long groupId) {
		return _dossierPartLocalService.fetchDossierPartByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _dossierPartLocalService.getActionableDynamicQuery();
	}

	@Override
	public org.opencps.dossiermgt.model.DossierPart getByFileTemplateNo(
		long groupId, String fileTemplateNo) {
		return _dossierPartLocalService.getByFileTemplateNo(groupId,
			fileTemplateNo);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierPart getByPartTypeEsign(
		String templateNo, String partNo, int partType, boolean eSign) {
		return _dossierPartLocalService.getByPartTypeEsign(templateNo, partNo,
			partType, eSign);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierPart getByTempAndPartNo(
		long groupId, String templateNo, String partNo) {
		return _dossierPartLocalService.getByTempAndPartNo(groupId, templateNo,
			partNo);
	}

	/**
	* @param groupId
	* @param templateNo
	* @return
	* @throws PortalException
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierPart> getByTemplateNo(
		long groupId, String templateNo)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierPartLocalService.getByTemplateNo(groupId, templateNo);
	}

	/**
	* @param dossierPartId
	* @param contentType
	* @return
	* @throws PortalException
	*/
	@Override
	public String getContent(long dossierPartId, int contentType)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierPartLocalService.getContent(dossierPartId, contentType);
	}

	/**
	* Returns the dossier part with the primary key.
	*
	* @param dossierPartId the primary key of the dossier part
	* @return the dossier part
	* @throws PortalException if a dossier part with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierPart getDossierPart(
		long dossierPartId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierPartLocalService.getDossierPart(dossierPartId);
	}

	/**
	* Returns the dossier part matching the UUID and group.
	*
	* @param uuid the dossier part's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier part
	* @throws PortalException if a matching dossier part could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierPart getDossierPartByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierPartLocalService.getDossierPartByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the dossier parts.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierPartModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossier parts
	* @param end the upper bound of the range of dossier parts (not inclusive)
	* @return the range of dossier parts
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierPart> getDossierParts(
		int start, int end) {
		return _dossierPartLocalService.getDossierParts(start, end);
	}

	/**
	* Returns all the dossier parts matching the UUID and company.
	*
	* @param uuid the UUID of the dossier parts
	* @param companyId the primary key of the company
	* @return the matching dossier parts, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierPart> getDossierPartsByUuidAndCompanyId(
		String uuid, long companyId) {
		return _dossierPartLocalService.getDossierPartsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of dossier parts matching the UUID and company.
	*
	* @param uuid the UUID of the dossier parts
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of dossier parts
	* @param end the upper bound of the range of dossier parts (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching dossier parts, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.DossierPart> getDossierPartsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.DossierPart> orderByComparator) {
		return _dossierPartLocalService.getDossierPartsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of dossier parts.
	*
	* @return the number of dossier parts
	*/
	@Override
	public int getDossierPartsCount() {
		return _dossierPartLocalService.getDossierPartsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _dossierPartLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _dossierPartLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _dossierPartLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierPartLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierPart removeDossierPart(
		long dossierPartId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierPartLocalService.removeDossierPart(dossierPartId);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits searchLucene(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _dossierPartLocalService.searchLucene(params, sorts, start, end,
			searchContext);
	}

	/**
	* @param dossierPartId
	* @param contentType
	* @param input
	* @param context
	* @return
	* @throws PortalException
	*/
	@Override
	public String updateContent(long dossierPartId, int contentType,
		String input, com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierPartLocalService.updateContent(dossierPartId,
			contentType, input, context);
	}

	/**
	* Updates the dossier part in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dossierPart the dossier part
	* @return the dossier part that was updated
	*/
	@Override
	public org.opencps.dossiermgt.model.DossierPart updateDossierPart(
		org.opencps.dossiermgt.model.DossierPart dossierPart) {
		return _dossierPartLocalService.updateDossierPart(dossierPart);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierPart updateDossierPart(
		long groupId, long dossierPartId, String templateNo, String partNo,
		String partName, String partTip, int partType, boolean multiple,
		String formScript, String formReport, String sampleData,
		boolean required, String fileTemplateNo, boolean eSign,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierPartLocalService.updateDossierPart(groupId,
			dossierPartId, templateNo, partNo, partName, partTip, partType,
			multiple, formScript, formReport, sampleData, required,
			fileTemplateNo, eSign, context);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierPart updateDossierPart(
		long groupId, long dossierPartId, String templateNo, String partNo,
		String partName, String partTip, int partType, boolean multiple,
		String formScript, String formReport, String sampleData,
		boolean required, String fileTemplateNo, boolean eSign,
		String deliverableType, int deliverableAction,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierPartLocalService.updateDossierPart(groupId,
			dossierPartId, templateNo, partNo, partName, partTip, partType,
			multiple, formScript, formReport, sampleData, required,
			fileTemplateNo, eSign, deliverableType, deliverableAction, context);
	}

	@Override
	public org.opencps.dossiermgt.model.DossierPart updateDossierPartDB(
		long userId, long groupId, String templateNo, String partNo,
		String partName, String partTip, Integer partType, boolean multiple,
		String formScript, String formReport, boolean required, boolean esign,
		String fileTemplateNo, String deliverableType,
		Integer deliverableAction, boolean eForm, String sampleData,
		Integer fileMark,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierPartLocalService.updateDossierPartDB(userId, groupId,
			templateNo, partNo, partName, partTip, partType, multiple,
			formScript, formReport, required, esign, fileTemplateNo,
			deliverableType, deliverableAction, eForm, sampleData, fileMark,
			serviceContext);
	}

	@Override
	public DossierPartLocalService getWrappedService() {
		return _dossierPartLocalService;
	}

	@Override
	public void setWrappedService(
		DossierPartLocalService dossierPartLocalService) {
		_dossierPartLocalService = dossierPartLocalService;
	}

	private DossierPartLocalService _dossierPartLocalService;
}