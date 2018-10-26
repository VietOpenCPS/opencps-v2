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
 * Provides a wrapper for {@link RegistrationFormLocalService}.
 *
 * @author huymq
 * @see RegistrationFormLocalService
 * @generated
 */
@ProviderType
public class RegistrationFormLocalServiceWrapper
	implements RegistrationFormLocalService,
		ServiceWrapper<RegistrationFormLocalService> {
	public RegistrationFormLocalServiceWrapper(
		RegistrationFormLocalService registrationFormLocalService) {
		_registrationFormLocalService = registrationFormLocalService;
	}

	@Override
	public org.opencps.dossiermgt.model.RegistrationForm addRegistrationForm(
		long groupId, long companyId, long registrationId, String referenceUid,
		String formNo, String formName, String formData, String formScript,
		String formReport, long fileEntryId, boolean isNew, boolean removed,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _registrationFormLocalService.addRegistrationForm(groupId,
			companyId, registrationId, referenceUid, formNo, formName,
			formData, formScript, formReport, fileEntryId, isNew, removed,
			serviceContext);
	}

	/**
	* Adds the registration form to the database. Also notifies the appropriate model listeners.
	*
	* @param registrationForm the registration form
	* @return the registration form that was added
	*/
	@Override
	public org.opencps.dossiermgt.model.RegistrationForm addRegistrationForm(
		org.opencps.dossiermgt.model.RegistrationForm registrationForm) {
		return _registrationFormLocalService.addRegistrationForm(registrationForm);
	}

	@Override
	public org.opencps.dossiermgt.model.RegistrationForm adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return _registrationFormLocalService.adminProcessData(objectData);
	}

	@Override
	public org.opencps.dossiermgt.model.RegistrationForm adminProcessDelete(
		Long id) {
		return _registrationFormLocalService.adminProcessDelete(id);
	}

	@Override
	public long countLucene(java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _registrationFormLocalService.countLucene(params, searchContext);
	}

	/**
	* Creates a new registration form with the primary key. Does not add the registration form to the database.
	*
	* @param registrationFormId the primary key for the new registration form
	* @return the new registration form
	*/
	@Override
	public org.opencps.dossiermgt.model.RegistrationForm createRegistrationForm(
		long registrationFormId) {
		return _registrationFormLocalService.createRegistrationForm(registrationFormId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _registrationFormLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the registration form with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param registrationFormId the primary key of the registration form
	* @return the registration form that was removed
	* @throws PortalException if a registration form with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.RegistrationForm deleteRegistrationForm(
		long registrationFormId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _registrationFormLocalService.deleteRegistrationForm(registrationFormId);
	}

	/**
	* Deletes the registration form from the database. Also notifies the appropriate model listeners.
	*
	* @param registrationForm the registration form
	* @return the registration form that was removed
	*/
	@Override
	public org.opencps.dossiermgt.model.RegistrationForm deleteRegistrationForm(
		org.opencps.dossiermgt.model.RegistrationForm registrationForm) {
		return _registrationFormLocalService.deleteRegistrationForm(registrationForm);
	}

	@Override
	public boolean deleteRegistrationForm(String referenceUid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _registrationFormLocalService.deleteRegistrationForm(referenceUid);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.RegistrationForm> deleteRegistrationForms(
		long groupId, long registrationId) {
		return _registrationFormLocalService.deleteRegistrationForms(groupId,
			registrationId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _registrationFormLocalService.dynamicQuery();
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
		return _registrationFormLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _registrationFormLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _registrationFormLocalService.dynamicQuery(dynamicQuery, start,
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
		return _registrationFormLocalService.dynamicQueryCount(dynamicQuery);
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
		return _registrationFormLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.dossiermgt.model.RegistrationForm fetchRegistrationForm(
		long registrationFormId) {
		return _registrationFormLocalService.fetchRegistrationForm(registrationFormId);
	}

	/**
	* Returns the registration form matching the UUID and group.
	*
	* @param uuid the registration form's UUID
	* @param groupId the primary key of the group
	* @return the matching registration form, or <code>null</code> if a matching registration form could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.RegistrationForm fetchRegistrationFormByUuidAndGroupId(
		String uuid, long groupId) {
		return _registrationFormLocalService.fetchRegistrationFormByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.RegistrationForm> findByG_REGID_ISNEW(
		long registrationId, boolean isNew) {
		return _registrationFormLocalService.findByG_REGID_ISNEW(registrationId,
			isNew);
	}

	@Override
	public org.opencps.dossiermgt.model.RegistrationForm findFormbyRegidRefid(
		long groupId, long registrationId, String referenceUid) {
		return _registrationFormLocalService.findFormbyRegidRefid(groupId,
			registrationId, referenceUid);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _registrationFormLocalService.getActionableDynamicQuery();
	}

	@Override
	public org.opencps.dossiermgt.model.RegistrationForm getByRegIdAndFormNo(
		long registrationId, String formNo) {
		return _registrationFormLocalService.getByRegIdAndFormNo(registrationId,
			formNo);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _registrationFormLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.RegistrationForm> getFormDataByFormNo(
		long groupId, long registrationId, String formNo) {
		return _registrationFormLocalService.getFormDataByFormNo(groupId,
			registrationId, formNo);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.RegistrationForm> getFormsbyRegId(
		long groupId, long registrationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _registrationFormLocalService.getFormsbyRegId(groupId,
			registrationId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _registrationFormLocalService.getIndexableActionableDynamicQuery();
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.search.BooleanClauseOccur> getOccurs() {
		return _registrationFormLocalService.getOccurs();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _registrationFormLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<String> getParamNames() {
		return _registrationFormLocalService.getParamNames();
	}

	@Override
	public java.util.List<Object> getParams() {
		return _registrationFormLocalService.getParams();
	}

	@Override
	public java.util.List<Class<?>> getParamTypes() {
		return _registrationFormLocalService.getParamTypes();
	}

	@Override
	public String getPattern() {
		return _registrationFormLocalService.getPattern();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _registrationFormLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.search.BooleanQuery getQuery() {
		return _registrationFormLocalService.getQuery();
	}

	/**
	* Returns the registration form with the primary key.
	*
	* @param registrationFormId the primary key of the registration form
	* @return the registration form
	* @throws PortalException if a registration form with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.RegistrationForm getRegistrationForm(
		long registrationFormId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _registrationFormLocalService.getRegistrationForm(registrationFormId);
	}

	/**
	* Returns the registration form matching the UUID and group.
	*
	* @param uuid the registration form's UUID
	* @param groupId the primary key of the group
	* @return the matching registration form
	* @throws PortalException if a matching registration form could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.RegistrationForm getRegistrationFormByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _registrationFormLocalService.getRegistrationFormByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the registration forms.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of registration forms
	* @param end the upper bound of the range of registration forms (not inclusive)
	* @return the range of registration forms
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.RegistrationForm> getRegistrationForms(
		int start, int end) {
		return _registrationFormLocalService.getRegistrationForms(start, end);
	}

	/**
	* Returns all the registration forms matching the UUID and company.
	*
	* @param uuid the UUID of the registration forms
	* @param companyId the primary key of the company
	* @return the matching registration forms, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.RegistrationForm> getRegistrationFormsByUuidAndCompanyId(
		String uuid, long companyId) {
		return _registrationFormLocalService.getRegistrationFormsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of registration forms matching the UUID and company.
	*
	* @param uuid the UUID of the registration forms
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of registration forms
	* @param end the upper bound of the range of registration forms (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching registration forms, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.RegistrationForm> getRegistrationFormsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.RegistrationForm> orderByComparator) {
		return _registrationFormLocalService.getRegistrationFormsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of registration forms.
	*
	* @return the number of registration forms
	*/
	@Override
	public int getRegistrationFormsCount() {
		return _registrationFormLocalService.getRegistrationFormsCount();
	}

	@Override
	public com.liferay.portal.kernel.search.SearchContext getSearchContext() {
		return _registrationFormLocalService.getSearchContext();
	}

	@Override
	public java.util.List<String> getSubPatterns() {
		return _registrationFormLocalService.getSubPatterns();
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.search.BooleanQuery> getSubQueries() {
		return _registrationFormLocalService.getSubQueries();
	}

	@Override
	public void LuceneQuery(String pattern, String paramValues,
		String paramTypes,
		com.liferay.portal.kernel.search.SearchContext searchContext) {
		_registrationFormLocalService.LuceneQuery(pattern, paramValues,
			paramTypes, searchContext);
	}

	@Override
	public org.opencps.dossiermgt.model.RegistrationForm registrationFormSync(
		long groupId, String uuidRegistration, String referenceUid,
		String formNo, String formName, String formData, String formScript,
		String formReport, Boolean removed,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _registrationFormLocalService.registrationFormSync(groupId,
			uuidRegistration, referenceUid, formNo, formName, formData,
			formScript, formReport, removed, serviceContext);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits searchLucene(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _registrationFormLocalService.searchLucene(params, sorts, start,
			end, searchContext);
	}

	@Override
	public void setOccurs(
		java.util.List<com.liferay.portal.kernel.search.BooleanClauseOccur> occurs) {
		_registrationFormLocalService.setOccurs(occurs);
	}

	@Override
	public void setParamNames(java.util.List<String> paramNames) {
		_registrationFormLocalService.setParamNames(paramNames);
	}

	@Override
	public void setParams(java.util.List<Object> params) {
		_registrationFormLocalService.setParams(params);
	}

	@Override
	public void setParamTypes(java.util.List<Class<?>> paramTypes) {
		_registrationFormLocalService.setParamTypes(paramTypes);
	}

	@Override
	public void setPattern(String pattern) {
		_registrationFormLocalService.setPattern(pattern);
	}

	@Override
	public void setQuery(com.liferay.portal.kernel.search.BooleanQuery query) {
		_registrationFormLocalService.setQuery(query);
	}

	@Override
	public void setSearchContext(
		com.liferay.portal.kernel.search.SearchContext searchContext) {
		_registrationFormLocalService.setSearchContext(searchContext);
	}

	@Override
	public void setSubPatterns(java.util.List<String> subPatterns) {
		_registrationFormLocalService.setSubPatterns(subPatterns);
	}

	@Override
	public void setSubQueries(
		java.util.List<com.liferay.portal.kernel.search.BooleanQuery> subQueries) {
		_registrationFormLocalService.setSubQueries(subQueries);
	}

	@Override
	public org.opencps.dossiermgt.model.RegistrationForm updateFormData(
		long groupId, long registrationId, String referenceUid,
		String formData,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _registrationFormLocalService.updateFormData(groupId,
			registrationId, referenceUid, formData, serviceContext);
	}

	@Override
	public org.opencps.dossiermgt.model.RegistrationForm updateIsNew(
		long groupId, long registrationId, String referenceUid, boolean isNew,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _registrationFormLocalService.updateIsNew(groupId,
			registrationId, referenceUid, isNew, serviceContext);
	}

	@Override
	public org.opencps.dossiermgt.model.RegistrationForm updateRegistrationForm(
		long groupId, long registrationId, String referenceUid, String formNo,
		String formName, String formData, String formScript, String formReport,
		long fileEntryId, boolean isNew, boolean removed,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _registrationFormLocalService.updateRegistrationForm(groupId,
			registrationId, referenceUid, formNo, formName, formData,
			formScript, formReport, fileEntryId, isNew, removed, serviceContext);
	}

	/**
	* Updates the registration form in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param registrationForm the registration form
	* @return the registration form that was updated
	*/
	@Override
	public org.opencps.dossiermgt.model.RegistrationForm updateRegistrationForm(
		org.opencps.dossiermgt.model.RegistrationForm registrationForm) {
		return _registrationFormLocalService.updateRegistrationForm(registrationForm);
	}

	@Override
	public RegistrationFormLocalService getWrappedService() {
		return _registrationFormLocalService;
	}

	@Override
	public void setWrappedService(
		RegistrationFormLocalService registrationFormLocalService) {
		_registrationFormLocalService = registrationFormLocalService;
	}

	private RegistrationFormLocalService _registrationFormLocalService;
}