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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for RegistrationForm. This utility wraps
 * {@link org.opencps.dossiermgt.service.impl.RegistrationFormLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author huymq
 * @see RegistrationFormLocalService
 * @see org.opencps.dossiermgt.service.base.RegistrationFormLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.RegistrationFormLocalServiceImpl
 * @generated
 */
@ProviderType
public class RegistrationFormLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.RegistrationFormLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static org.opencps.dossiermgt.model.RegistrationForm addRegistrationForm(
		long groupId, long companyId, long registrationId, String referenceUid,
		String formNo, String formName, String formData, String formScript,
		String formReport, long fileEntryId, boolean isNew, boolean removed,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addRegistrationForm(groupId, companyId, registrationId,
			referenceUid, formNo, formName, formData, formScript, formReport,
			fileEntryId, isNew, removed, serviceContext);
	}

	/**
	* Adds the registration form to the database. Also notifies the appropriate model listeners.
	*
	* @param registrationForm the registration form
	* @return the registration form that was added
	*/
	public static org.opencps.dossiermgt.model.RegistrationForm addRegistrationForm(
		org.opencps.dossiermgt.model.RegistrationForm registrationForm) {
		return getService().addRegistrationForm(registrationForm);
	}

	public static long countLucene(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return getService().countLucene(params, searchContext);
	}

	/**
	* Creates a new registration form with the primary key. Does not add the registration form to the database.
	*
	* @param registrationFormId the primary key for the new registration form
	* @return the new registration form
	*/
	public static org.opencps.dossiermgt.model.RegistrationForm createRegistrationForm(
		long registrationFormId) {
		return getService().createRegistrationForm(registrationFormId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the registration form with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param registrationFormId the primary key of the registration form
	* @return the registration form that was removed
	* @throws PortalException if a registration form with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.RegistrationForm deleteRegistrationForm(
		long registrationFormId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteRegistrationForm(registrationFormId);
	}

	/**
	* Deletes the registration form from the database. Also notifies the appropriate model listeners.
	*
	* @param registrationForm the registration form
	* @return the registration form that was removed
	*/
	public static org.opencps.dossiermgt.model.RegistrationForm deleteRegistrationForm(
		org.opencps.dossiermgt.model.RegistrationForm registrationForm) {
		return getService().deleteRegistrationForm(registrationForm);
	}

	public static boolean deleteRegistrationForm(String referenceUid)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteRegistrationForm(referenceUid);
	}

	public static java.util.List<org.opencps.dossiermgt.model.RegistrationForm> deleteRegistrationForms(
		long groupId, long registrationId) {
		return getService().deleteRegistrationForms(groupId, registrationId);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.RegistrationFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.dossiermgt.model.RegistrationForm fetchRegistrationForm(
		long registrationFormId) {
		return getService().fetchRegistrationForm(registrationFormId);
	}

	/**
	* Returns the registration form matching the UUID and group.
	*
	* @param uuid the registration form's UUID
	* @param groupId the primary key of the group
	* @return the matching registration form, or <code>null</code> if a matching registration form could not be found
	*/
	public static org.opencps.dossiermgt.model.RegistrationForm fetchRegistrationFormByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchRegistrationFormByUuidAndGroupId(uuid, groupId);
	}

	public static java.util.List<org.opencps.dossiermgt.model.RegistrationForm> findByG_REGID_ISNEW(
		long registrationId, boolean isNew) {
		return getService().findByG_REGID_ISNEW(registrationId, isNew);
	}

	public static org.opencps.dossiermgt.model.RegistrationForm findFormbyRegidRefid(
		long groupId, long registrationId, String referenceUid) {
		return getService()
				   .findFormbyRegidRefid(groupId, registrationId, referenceUid);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static org.opencps.dossiermgt.model.RegistrationForm getByRegIdAndFormNo(
		long registrationId, String formNo) {
		return getService().getByRegIdAndFormNo(registrationId, formNo);
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static java.util.List<org.opencps.dossiermgt.model.RegistrationForm> getFormDataByFormNo(
		long groupId, long registrationId, String formNo) {
		return getService().getFormDataByFormNo(groupId, registrationId, formNo);
	}

	public static java.util.List<org.opencps.dossiermgt.model.RegistrationForm> getFormsbyRegId(
		long groupId, long registrationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getFormsbyRegId(groupId, registrationId);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	public static java.util.List<com.liferay.portal.kernel.search.BooleanClauseOccur> getOccurs() {
		return getService().getOccurs();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static java.util.List<String> getParamNames() {
		return getService().getParamNames();
	}

	public static java.util.List<Object> getParams() {
		return getService().getParams();
	}

	public static java.util.List<Class<?>> getParamTypes() {
		return getService().getParamTypes();
	}

	public static String getPattern() {
		return getService().getPattern();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	public static com.liferay.portal.kernel.search.BooleanQuery getQuery() {
		return getService().getQuery();
	}

	/**
	* Returns the registration form with the primary key.
	*
	* @param registrationFormId the primary key of the registration form
	* @return the registration form
	* @throws PortalException if a registration form with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.RegistrationForm getRegistrationForm(
		long registrationFormId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getRegistrationForm(registrationFormId);
	}

	/**
	* Returns the registration form matching the UUID and group.
	*
	* @param uuid the registration form's UUID
	* @param groupId the primary key of the group
	* @return the matching registration form
	* @throws PortalException if a matching registration form could not be found
	*/
	public static org.opencps.dossiermgt.model.RegistrationForm getRegistrationFormByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getRegistrationFormByUuidAndGroupId(uuid, groupId);
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
	public static java.util.List<org.opencps.dossiermgt.model.RegistrationForm> getRegistrationForms(
		int start, int end) {
		return getService().getRegistrationForms(start, end);
	}

	/**
	* Returns all the registration forms matching the UUID and company.
	*
	* @param uuid the UUID of the registration forms
	* @param companyId the primary key of the company
	* @return the matching registration forms, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.dossiermgt.model.RegistrationForm> getRegistrationFormsByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService()
				   .getRegistrationFormsByUuidAndCompanyId(uuid, companyId);
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
	public static java.util.List<org.opencps.dossiermgt.model.RegistrationForm> getRegistrationFormsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.RegistrationForm> orderByComparator) {
		return getService()
				   .getRegistrationFormsByUuidAndCompanyId(uuid, companyId,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of registration forms.
	*
	* @return the number of registration forms
	*/
	public static int getRegistrationFormsCount() {
		return getService().getRegistrationFormsCount();
	}

	public static com.liferay.portal.kernel.search.SearchContext getSearchContext() {
		return getService().getSearchContext();
	}

	public static java.util.List<String> getSubPatterns() {
		return getService().getSubPatterns();
	}

	public static java.util.List<com.liferay.portal.kernel.search.BooleanQuery> getSubQueries() {
		return getService().getSubQueries();
	}

	public static void LuceneQuery(String pattern, String paramValues,
		String paramTypes,
		com.liferay.portal.kernel.search.SearchContext searchContext) {
		getService().LuceneQuery(pattern, paramValues, paramTypes, searchContext);
	}

	public static org.opencps.dossiermgt.model.RegistrationForm registrationFormSync(
		long groupId, String uuidRegistration, String referenceUid,
		String formNo, String formName, String formData, String formScript,
		String formReport, Boolean removed,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .registrationFormSync(groupId, uuidRegistration,
			referenceUid, formNo, formName, formData, formScript, formReport,
			removed, serviceContext);
	}

	public static com.liferay.portal.kernel.search.Hits searchLucene(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return getService()
				   .searchLucene(params, sorts, start, end, searchContext);
	}

	public static void setOccurs(
		java.util.List<com.liferay.portal.kernel.search.BooleanClauseOccur> occurs) {
		getService().setOccurs(occurs);
	}

	public static void setParamNames(java.util.List<String> paramNames) {
		getService().setParamNames(paramNames);
	}

	public static void setParams(java.util.List<Object> params) {
		getService().setParams(params);
	}

	public static void setParamTypes(java.util.List<Class<?>> paramTypes) {
		getService().setParamTypes(paramTypes);
	}

	public static void setPattern(String pattern) {
		getService().setPattern(pattern);
	}

	public static void setQuery(
		com.liferay.portal.kernel.search.BooleanQuery query) {
		getService().setQuery(query);
	}

	public static void setSearchContext(
		com.liferay.portal.kernel.search.SearchContext searchContext) {
		getService().setSearchContext(searchContext);
	}

	public static void setSubPatterns(java.util.List<String> subPatterns) {
		getService().setSubPatterns(subPatterns);
	}

	public static void setSubQueries(
		java.util.List<com.liferay.portal.kernel.search.BooleanQuery> subQueries) {
		getService().setSubQueries(subQueries);
	}

	public static org.opencps.dossiermgt.model.RegistrationForm updateFormData(
		long groupId, long registrationId, String referenceUid,
		String formData,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateFormData(groupId, registrationId, referenceUid,
			formData, serviceContext);
	}

	public static org.opencps.dossiermgt.model.RegistrationForm updateIsNew(
		long groupId, long registrationId, String referenceUid, boolean isNew,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateIsNew(groupId, registrationId, referenceUid, isNew,
			serviceContext);
	}

	public static org.opencps.dossiermgt.model.RegistrationForm updateRegistrationForm(
		long groupId, long registrationId, String referenceUid, String formNo,
		String formName, String formData, String formScript, String formReport,
		long fileEntryId, boolean isNew, boolean removed,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateRegistrationForm(groupId, registrationId,
			referenceUid, formNo, formName, formData, formScript, formReport,
			fileEntryId, isNew, removed, serviceContext);
	}

	/**
	* Updates the registration form in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param registrationForm the registration form
	* @return the registration form that was updated
	*/
	public static org.opencps.dossiermgt.model.RegistrationForm updateRegistrationForm(
		org.opencps.dossiermgt.model.RegistrationForm registrationForm) {
		return getService().updateRegistrationForm(registrationForm);
	}

	public static RegistrationFormLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<RegistrationFormLocalService, RegistrationFormLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(RegistrationFormLocalService.class);

		ServiceTracker<RegistrationFormLocalService, RegistrationFormLocalService> serviceTracker =
			new ServiceTracker<RegistrationFormLocalService, RegistrationFormLocalService>(bundle.getBundleContext(),
				RegistrationFormLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}