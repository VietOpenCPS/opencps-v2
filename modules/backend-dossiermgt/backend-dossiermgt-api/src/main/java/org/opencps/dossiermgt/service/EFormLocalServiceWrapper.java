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
 * Provides a wrapper for {@link EFormLocalService}.
 *
 * @author huymq
 * @see EFormLocalService
 * @generated
 */
@ProviderType
public class EFormLocalServiceWrapper implements EFormLocalService,
	ServiceWrapper<EFormLocalService> {
	public EFormLocalServiceWrapper(EFormLocalService eFormLocalService) {
		_eFormLocalService = eFormLocalService;
	}

	/**
	* Adds the e form to the database. Also notifies the appropriate model listeners.
	*
	* @param eForm the e form
	* @return the e form that was added
	*/
	@Override
	public org.opencps.dossiermgt.model.EForm addEForm(
		org.opencps.dossiermgt.model.EForm eForm) {
		return _eFormLocalService.addEForm(eForm);
	}

	@Override
	public long countLucene(java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _eFormLocalService.countLucene(params, searchContext);
	}

	/**
	* Creates a new e form with the primary key. Does not add the e form to the database.
	*
	* @param eFormId the primary key for the new e form
	* @return the new e form
	*/
	@Override
	public org.opencps.dossiermgt.model.EForm createEForm(long eFormId) {
		return _eFormLocalService.createEForm(eFormId);
	}

	/**
	* Deletes the e form from the database. Also notifies the appropriate model listeners.
	*
	* @param eForm the e form
	* @return the e form that was removed
	*/
	@Override
	public org.opencps.dossiermgt.model.EForm deleteEForm(
		org.opencps.dossiermgt.model.EForm eForm) {
		return _eFormLocalService.deleteEForm(eForm);
	}

	/**
	* Deletes the e form with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param eFormId the primary key of the e form
	* @return the e form that was removed
	* @throws PortalException if a e form with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.EForm deleteEForm(long eFormId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _eFormLocalService.deleteEForm(eFormId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _eFormLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _eFormLocalService.dynamicQuery();
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
		return _eFormLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.EFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _eFormLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.EFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _eFormLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _eFormLocalService.dynamicQueryCount(dynamicQuery);
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
		return _eFormLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public org.opencps.dossiermgt.model.EForm fetchEForm(long eFormId) {
		return _eFormLocalService.fetchEForm(eFormId);
	}

	/**
	* Returns the e form matching the UUID and group.
	*
	* @param uuid the e form's UUID
	* @param groupId the primary key of the group
	* @return the matching e form, or <code>null</code> if a matching e form could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.EForm fetchEFormByUuidAndGroupId(
		String uuid, long groupId) {
		return _eFormLocalService.fetchEFormByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _eFormLocalService.getActionableDynamicQuery();
	}

	@Override
	public org.opencps.dossiermgt.model.EForm getByEFormNo(long groupId,
		String eFormNo) {
		return _eFormLocalService.getByEFormNo(groupId, eFormNo);
	}

	/**
	* Returns the e form with the primary key.
	*
	* @param eFormId the primary key of the e form
	* @return the e form
	* @throws PortalException if a e form with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.EForm getEForm(long eFormId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _eFormLocalService.getEForm(eFormId);
	}

	/**
	* Returns the e form matching the UUID and group.
	*
	* @param uuid the e form's UUID
	* @param groupId the primary key of the group
	* @return the matching e form
	* @throws PortalException if a matching e form could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.EForm getEFormByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _eFormLocalService.getEFormByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the e forms.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.EFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of e forms
	* @param end the upper bound of the range of e forms (not inclusive)
	* @return the range of e forms
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.EForm> getEForms(
		int start, int end) {
		return _eFormLocalService.getEForms(start, end);
	}

	/**
	* Returns all the e forms matching the UUID and company.
	*
	* @param uuid the UUID of the e forms
	* @param companyId the primary key of the company
	* @return the matching e forms, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.EForm> getEFormsByUuidAndCompanyId(
		String uuid, long companyId) {
		return _eFormLocalService.getEFormsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of e forms matching the UUID and company.
	*
	* @param uuid the UUID of the e forms
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of e forms
	* @param end the upper bound of the range of e forms (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching e forms, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.EForm> getEFormsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.EForm> orderByComparator) {
		return _eFormLocalService.getEFormsByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of e forms.
	*
	* @return the number of e forms
	*/
	@Override
	public int getEFormsCount() {
		return _eFormLocalService.getEFormsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _eFormLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _eFormLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _eFormLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _eFormLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits searchLucene(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _eFormLocalService.searchLucene(params, sorts, start, end,
			searchContext);
	}

	@Override
	public org.opencps.dossiermgt.model.EForm updateDataByEFormNo(
		long eFormId, String eFormData,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		return _eFormLocalService.updateDataByEFormNo(eFormId, eFormData,
			serviceContext);
	}

	/**
	* Updates the e form in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param eForm the e form
	* @return the e form that was updated
	*/
	@Override
	public org.opencps.dossiermgt.model.EForm updateEForm(
		org.opencps.dossiermgt.model.EForm eForm) {
		return _eFormLocalService.updateEForm(eForm);
	}

	@Override
	public org.opencps.dossiermgt.model.EForm updateEForm(long userId,
		long groupId, long eFormId, String eFormNo, String serviceCode,
		String fileTemplateNo, String eFormName, long formScriptFileId,
		long formReportFileId, String eFormData, String email, String secret,
		String govAgencyCode,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		return _eFormLocalService.updateEForm(userId, groupId, eFormId,
			eFormNo, serviceCode, fileTemplateNo, eFormName, formScriptFileId,
			formReportFileId, eFormData, email, secret, govAgencyCode,
			serviceContext);
	}

	@Override
	public EFormLocalService getWrappedService() {
		return _eFormLocalService;
	}

	@Override
	public void setWrappedService(EFormLocalService eFormLocalService) {
		_eFormLocalService = eFormLocalService;
	}

	private EFormLocalService _eFormLocalService;
}