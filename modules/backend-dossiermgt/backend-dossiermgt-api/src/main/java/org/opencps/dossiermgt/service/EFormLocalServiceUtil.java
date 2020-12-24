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
 * Provides the local service utility for EForm. This utility wraps
 * {@link org.opencps.dossiermgt.service.impl.EFormLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author huymq
 * @see EFormLocalService
 * @see org.opencps.dossiermgt.service.base.EFormLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.EFormLocalServiceImpl
 * @generated
 */
@ProviderType
public class EFormLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.EFormLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the e form to the database. Also notifies the appropriate model listeners.
	*
	* @param eForm the e form
	* @return the e form that was added
	*/
	public static org.opencps.dossiermgt.model.EForm addEForm(
		org.opencps.dossiermgt.model.EForm eForm) {
		return getService().addEForm(eForm);
	}

	public static long countLucene(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return getService().countLucene(params, searchContext);
	}

	/**
	* Creates a new e form with the primary key. Does not add the e form to the database.
	*
	* @param eFormId the primary key for the new e form
	* @return the new e form
	*/
	public static org.opencps.dossiermgt.model.EForm createEForm(long eFormId) {
		return getService().createEForm(eFormId);
	}

	/**
	* Deletes the e form from the database. Also notifies the appropriate model listeners.
	*
	* @param eForm the e form
	* @return the e form that was removed
	*/
	public static org.opencps.dossiermgt.model.EForm deleteEForm(
		org.opencps.dossiermgt.model.EForm eForm) {
		return getService().deleteEForm(eForm);
	}

	/**
	* Deletes the e form with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param eFormId the primary key of the e form
	* @return the e form that was removed
	* @throws PortalException if a e form with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.EForm deleteEForm(long eFormId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteEForm(eFormId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.EFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.EFormModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.dossiermgt.model.EForm fetchEForm(long eFormId) {
		return getService().fetchEForm(eFormId);
	}

	/**
	* Returns the e form matching the UUID and group.
	*
	* @param uuid the e form's UUID
	* @param groupId the primary key of the group
	* @return the matching e form, or <code>null</code> if a matching e form could not be found
	*/
	public static org.opencps.dossiermgt.model.EForm fetchEFormByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchEFormByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static org.opencps.dossiermgt.model.EForm getByEFormNo(
		long groupId, String eFormNo) {
		return getService().getByEFormNo(groupId, eFormNo);
	}

	/**
	* Returns the e form with the primary key.
	*
	* @param eFormId the primary key of the e form
	* @return the e form
	* @throws PortalException if a e form with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.EForm getEForm(long eFormId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getEForm(eFormId);
	}

	/**
	* Returns the e form matching the UUID and group.
	*
	* @param uuid the e form's UUID
	* @param groupId the primary key of the group
	* @return the matching e form
	* @throws PortalException if a matching e form could not be found
	*/
	public static org.opencps.dossiermgt.model.EForm getEFormByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getEFormByUuidAndGroupId(uuid, groupId);
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
	public static java.util.List<org.opencps.dossiermgt.model.EForm> getEForms(
		int start, int end) {
		return getService().getEForms(start, end);
	}

	/**
	* Returns all the e forms matching the UUID and company.
	*
	* @param uuid the UUID of the e forms
	* @param companyId the primary key of the company
	* @return the matching e forms, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.dossiermgt.model.EForm> getEFormsByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getEFormsByUuidAndCompanyId(uuid, companyId);
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
	public static java.util.List<org.opencps.dossiermgt.model.EForm> getEFormsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.EForm> orderByComparator) {
		return getService()
				   .getEFormsByUuidAndCompanyId(uuid, companyId, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of e forms.
	*
	* @return the number of e forms
	*/
	public static int getEFormsCount() {
		return getService().getEFormsCount();
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
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

	public static org.opencps.dossiermgt.model.EForm updateDataByEFormNo(
		long eFormId, String eFormData,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		return getService()
				   .updateDataByEFormNo(eFormId, eFormData, serviceContext);
	}

	/**
	* Updates the e form in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param eForm the e form
	* @return the e form that was updated
	*/
	public static org.opencps.dossiermgt.model.EForm updateEForm(
		org.opencps.dossiermgt.model.EForm eForm) {
		return getService().updateEForm(eForm);
	}

	public static org.opencps.dossiermgt.model.EForm updateEForm(long userId,
		long groupId, long eFormId, String eFormNo, String serviceCode,
		String fileTemplateNo, String eFormName, long formScriptFileId,
		long formReportFileId, String eFormData, String email, String secret,String govAgencyCode,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		return getService()
				   .updateEForm(userId, groupId, eFormId, eFormNo, serviceCode,
			fileTemplateNo, eFormName, formScriptFileId, formReportFileId,
			eFormData, email, secret,govAgencyCode, serviceContext);
	}

	public static EFormLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<EFormLocalService, EFormLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(EFormLocalService.class);

		ServiceTracker<EFormLocalService, EFormLocalService> serviceTracker = new ServiceTracker<EFormLocalService, EFormLocalService>(bundle.getBundleContext(),
				EFormLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}