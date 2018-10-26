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
 * Provides the local service utility for Registration. This utility wraps
 * {@link org.opencps.dossiermgt.service.impl.RegistrationLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author huymq
 * @see RegistrationLocalService
 * @see org.opencps.dossiermgt.service.base.RegistrationLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.RegistrationLocalServiceImpl
 * @generated
 */
@ProviderType
public class RegistrationLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.RegistrationLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the registration to the database. Also notifies the appropriate model listeners.
	*
	* @param registration the registration
	* @return the registration that was added
	*/
	public static org.opencps.dossiermgt.model.Registration addRegistration(
		org.opencps.dossiermgt.model.Registration registration) {
		return getService().addRegistration(registration);
	}

	public static org.opencps.dossiermgt.model.Registration adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return getService().adminProcessData(objectData);
	}

	public static org.opencps.dossiermgt.model.Registration adminProcessDelete(
		Long id) {
		return getService().adminProcessDelete(id);
	}

	public static long countLucense(long userId,
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return getService()
				   .countLucense(userId, params, sorts, start, end,
			searchContext);
	}

	/**
	* Creates a new registration with the primary key. Does not add the registration to the database.
	*
	* @param registrationId the primary key for the new registration
	* @return the new registration
	*/
	public static org.opencps.dossiermgt.model.Registration createRegistration(
		long registrationId) {
		return getService().createRegistration(registrationId);
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
	* Deletes the registration with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param registrationId the primary key of the registration
	* @return the registration that was removed
	* @throws PortalException if a registration with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.Registration deleteRegistration(
		long registrationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteRegistration(registrationId);
	}

	/**
	* Deletes the registration from the database. Also notifies the appropriate model listeners.
	*
	* @param registration the registration
	* @return the registration that was removed
	*/
	public static org.opencps.dossiermgt.model.Registration deleteRegistration(
		org.opencps.dossiermgt.model.Registration registration) {
		return getService().deleteRegistration(registration);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.dossiermgt.model.Registration fetchRegistration(
		long registrationId) {
		return getService().fetchRegistration(registrationId);
	}

	/**
	* Returns the registration matching the UUID and group.
	*
	* @param uuid the registration's UUID
	* @param groupId the primary key of the group
	* @return the matching registration, or <code>null</code> if a matching registration could not be found
	*/
	public static org.opencps.dossiermgt.model.Registration fetchRegistrationByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchRegistrationByUuidAndGroupId(uuid, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Get registration of applicant has registrationState in use
	*/
	public static org.opencps.dossiermgt.model.Registration getByApplicantAndAgency(
		long groupId, String applicantNo, String agencyNo) {
		return getService()
				   .getByApplicantAndAgency(groupId, applicantNo, agencyNo);
	}

	/**
	* Get registration form ApplicantIdNo using output DB
	*/
	public static org.opencps.dossiermgt.model.Registration getByApplicantIdNo(
		String applicantIdNo) {
		return getService().getByApplicantIdNo(applicantIdNo);
	}

	/**
	* Get list registrations have state = 2
	*/
	public static java.util.List<org.opencps.dossiermgt.model.Registration> getByRegistrationState(
		long groupId, long userId, int state) {
		return getService().getByRegistrationState(groupId, userId, state);
	}

	public static java.util.List<org.opencps.dossiermgt.model.Registration> getdByF_submitting(
		long groupId, boolean submitting) {
		return getService().getdByF_submitting(groupId, submitting);
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static int getfileEntryId(String formdata, String formScript,
		String formReport) {
		return getService().getfileEntryId(formdata, formScript, formReport);
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	public static org.opencps.dossiermgt.model.Registration getLastSubmittingByUser(
		long groupId, long userId, boolean submitting) {
		return getService().getLastSubmittingByUser(groupId, userId, submitting);
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

	/**
	* Returns the registration with the primary key.
	*
	* @param registrationId the primary key of the registration
	* @return the registration
	* @throws PortalException if a registration with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.Registration getRegistration(
		long registrationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getRegistration(registrationId);
	}

	public static org.opencps.dossiermgt.model.Registration getRegistrationByG_REGID(
		long groupId, long registrationId) {
		return getService().getRegistrationByG_REGID(groupId, registrationId);
	}

	public static java.util.List<org.opencps.dossiermgt.model.Registration> getRegistrationByGID_UID(
		long groupId, long userId) {
		return getService().getRegistrationByGID_UID(groupId, userId);
	}

	public static org.opencps.dossiermgt.model.Registration getRegistrationByGID_UID_Last(
		long groupId, long userId) {
		return getService().getRegistrationByGID_UID_Last(groupId, userId);
	}

	/**
	* Returns the registration matching the UUID and group.
	*
	* @param uuid the registration's UUID
	* @param groupId the primary key of the group
	* @return the matching registration
	* @throws PortalException if a matching registration could not be found
	*/
	public static org.opencps.dossiermgt.model.Registration getRegistrationByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getRegistrationByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the registrations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.RegistrationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of registrations
	* @param end the upper bound of the range of registrations (not inclusive)
	* @return the range of registrations
	*/
	public static java.util.List<org.opencps.dossiermgt.model.Registration> getRegistrations(
		int start, int end) {
		return getService().getRegistrations(start, end);
	}

	/**
	* Returns all the registrations matching the UUID and company.
	*
	* @param uuid the UUID of the registrations
	* @param companyId the primary key of the company
	* @return the matching registrations, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.dossiermgt.model.Registration> getRegistrationsByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getRegistrationsByUuidAndCompanyId(uuid, companyId);
	}

	/**
	* Returns a range of registrations matching the UUID and company.
	*
	* @param uuid the UUID of the registrations
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of registrations
	* @param end the upper bound of the range of registrations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching registrations, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.dossiermgt.model.Registration> getRegistrationsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.Registration> orderByComparator) {
		return getService()
				   .getRegistrationsByUuidAndCompanyId(uuid, companyId, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of registrations.
	*
	* @return the number of registrations
	*/
	public static int getRegistrationsCount() {
		return getService().getRegistrationsCount();
	}

	public static org.opencps.dossiermgt.model.Registration insert(
		long groupId, long companyId, String applicantName,
		String applicantIdType, String applicantIdNo, String applicantIdDate,
		String address, String cityCode, String cityName, String districtCode,
		String districtName, String wardCode, String wardName,
		String contactName, String contactTelNo, String contactEmail,
		String govAgencyCode, String govAgencyName, int registrationState,
		String registrationClass, String representativeEnterprise,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .insert(groupId, companyId, applicantName, applicantIdType,
			applicantIdNo, applicantIdDate, address, cityCode, cityName,
			districtCode, districtName, wardCode, wardName, contactName,
			contactTelNo, contactEmail, govAgencyCode, govAgencyName,
			registrationState, registrationClass, representativeEnterprise,
			serviceContext);
	}

	public static org.opencps.dossiermgt.model.Registration registrationSync(
		long groupId, String uuid, String applicantName,
		String applicantIdType, String applicantIdNo, String applicantIdDate,
		String address, String cityCode, String cityName, String districtCode,
		String districtName, String wardCode, String wardName,
		String contactName, String contactTelNo, String contactEmail,
		String govAgencyCode, String govAgencyName, int registrationState,
		String registrationClass, String representativeEnterprise,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .registrationSync(groupId, uuid, applicantName,
			applicantIdType, applicantIdNo, applicantIdDate, address, cityCode,
			cityName, districtCode, districtName, wardCode, wardName,
			contactName, contactTelNo, contactEmail, govAgencyCode,
			govAgencyName, registrationState, registrationClass,
			representativeEnterprise, serviceContext);
	}

	public static com.liferay.portal.kernel.search.Hits searchLucene(
		long userId, java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return getService()
				   .searchLucene(userId, params, sorts, start, end,
			searchContext);
	}

	public static org.opencps.dossiermgt.model.Registration updateRegistration(
		long groupId, long registrationId, String applicantName,
		String applicantIdType, String applicantIdNo, String applicantIdDate,
		String address, String cityCode, String cityName, String districtCode,
		String districtName, String wardCode, String wardName,
		String contactName, String contactTelNo, String contactEmail,
		String govAgencyCode, String govAgencyName, int registrationState,
		String registrationClass, String representativeEnterprise,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateRegistration(groupId, registrationId, applicantName,
			applicantIdType, applicantIdNo, applicantIdDate, address, cityCode,
			cityName, districtCode, districtName, wardCode, wardName,
			contactName, contactTelNo, contactEmail, govAgencyCode,
			govAgencyName, registrationState, registrationClass,
			representativeEnterprise, serviceContext);
	}

	/**
	* Updates the registration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param registration the registration
	* @return the registration that was updated
	*/
	public static org.opencps.dossiermgt.model.Registration updateRegistration(
		org.opencps.dossiermgt.model.Registration registration) {
		return getService().updateRegistration(registration);
	}

	public static org.opencps.dossiermgt.model.Registration updateSubmitting(
		long registrationId, boolean submitting)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateSubmitting(registrationId, submitting);
	}

	public static RegistrationLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<RegistrationLocalService, RegistrationLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(RegistrationLocalService.class);

		ServiceTracker<RegistrationLocalService, RegistrationLocalService> serviceTracker =
			new ServiceTracker<RegistrationLocalService, RegistrationLocalService>(bundle.getBundleContext(),
				RegistrationLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}