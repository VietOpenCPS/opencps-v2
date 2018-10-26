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
 * Provides a wrapper for {@link RegistrationLocalService}.
 *
 * @author huymq
 * @see RegistrationLocalService
 * @generated
 */
@ProviderType
public class RegistrationLocalServiceWrapper implements RegistrationLocalService,
	ServiceWrapper<RegistrationLocalService> {
	public RegistrationLocalServiceWrapper(
		RegistrationLocalService registrationLocalService) {
		_registrationLocalService = registrationLocalService;
	}

	/**
	* Adds the registration to the database. Also notifies the appropriate model listeners.
	*
	* @param registration the registration
	* @return the registration that was added
	*/
	@Override
	public org.opencps.dossiermgt.model.Registration addRegistration(
		org.opencps.dossiermgt.model.Registration registration) {
		return _registrationLocalService.addRegistration(registration);
	}

	@Override
	public org.opencps.dossiermgt.model.Registration adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return _registrationLocalService.adminProcessData(objectData);
	}

	@Override
	public org.opencps.dossiermgt.model.Registration adminProcessDelete(Long id) {
		return _registrationLocalService.adminProcessDelete(id);
	}

	@Override
	public long countLucense(long userId,
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _registrationLocalService.countLucense(userId, params, sorts,
			start, end, searchContext);
	}

	/**
	* Creates a new registration with the primary key. Does not add the registration to the database.
	*
	* @param registrationId the primary key for the new registration
	* @return the new registration
	*/
	@Override
	public org.opencps.dossiermgt.model.Registration createRegistration(
		long registrationId) {
		return _registrationLocalService.createRegistration(registrationId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _registrationLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the registration with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param registrationId the primary key of the registration
	* @return the registration that was removed
	* @throws PortalException if a registration with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.Registration deleteRegistration(
		long registrationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _registrationLocalService.deleteRegistration(registrationId);
	}

	/**
	* Deletes the registration from the database. Also notifies the appropriate model listeners.
	*
	* @param registration the registration
	* @return the registration that was removed
	*/
	@Override
	public org.opencps.dossiermgt.model.Registration deleteRegistration(
		org.opencps.dossiermgt.model.Registration registration) {
		return _registrationLocalService.deleteRegistration(registration);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _registrationLocalService.dynamicQuery();
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
		return _registrationLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return _registrationLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return _registrationLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _registrationLocalService.dynamicQueryCount(dynamicQuery);
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
		return _registrationLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public org.opencps.dossiermgt.model.Registration fetchRegistration(
		long registrationId) {
		return _registrationLocalService.fetchRegistration(registrationId);
	}

	/**
	* Returns the registration matching the UUID and group.
	*
	* @param uuid the registration's UUID
	* @param groupId the primary key of the group
	* @return the matching registration, or <code>null</code> if a matching registration could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.Registration fetchRegistrationByUuidAndGroupId(
		String uuid, long groupId) {
		return _registrationLocalService.fetchRegistrationByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _registrationLocalService.getActionableDynamicQuery();
	}

	/**
	* Get registration of applicant has registrationState in use
	*/
	@Override
	public org.opencps.dossiermgt.model.Registration getByApplicantAndAgency(
		long groupId, String applicantNo, String agencyNo) {
		return _registrationLocalService.getByApplicantAndAgency(groupId,
			applicantNo, agencyNo);
	}

	/**
	* Get registration form ApplicantIdNo using output DB
	*/
	@Override
	public org.opencps.dossiermgt.model.Registration getByApplicantIdNo(
		String applicantIdNo) {
		return _registrationLocalService.getByApplicantIdNo(applicantIdNo);
	}

	/**
	* Get list registrations have state = 2
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.Registration> getByRegistrationState(
		long groupId, long userId, int state) {
		return _registrationLocalService.getByRegistrationState(groupId,
			userId, state);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.Registration> getdByF_submitting(
		long groupId, boolean submitting) {
		return _registrationLocalService.getdByF_submitting(groupId, submitting);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _registrationLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public int getfileEntryId(String formdata, String formScript,
		String formReport) {
		return _registrationLocalService.getfileEntryId(formdata, formScript,
			formReport);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _registrationLocalService.getIndexableActionableDynamicQuery();
	}

	@Override
	public org.opencps.dossiermgt.model.Registration getLastSubmittingByUser(
		long groupId, long userId, boolean submitting) {
		return _registrationLocalService.getLastSubmittingByUser(groupId,
			userId, submitting);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _registrationLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _registrationLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the registration with the primary key.
	*
	* @param registrationId the primary key of the registration
	* @return the registration
	* @throws PortalException if a registration with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.Registration getRegistration(
		long registrationId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _registrationLocalService.getRegistration(registrationId);
	}

	@Override
	public org.opencps.dossiermgt.model.Registration getRegistrationByG_REGID(
		long groupId, long registrationId) {
		return _registrationLocalService.getRegistrationByG_REGID(groupId,
			registrationId);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.Registration> getRegistrationByGID_UID(
		long groupId, long userId) {
		return _registrationLocalService.getRegistrationByGID_UID(groupId,
			userId);
	}

	@Override
	public org.opencps.dossiermgt.model.Registration getRegistrationByGID_UID_Last(
		long groupId, long userId) {
		return _registrationLocalService.getRegistrationByGID_UID_Last(groupId,
			userId);
	}

	/**
	* Returns the registration matching the UUID and group.
	*
	* @param uuid the registration's UUID
	* @param groupId the primary key of the group
	* @return the matching registration
	* @throws PortalException if a matching registration could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.Registration getRegistrationByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _registrationLocalService.getRegistrationByUuidAndGroupId(uuid,
			groupId);
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
	@Override
	public java.util.List<org.opencps.dossiermgt.model.Registration> getRegistrations(
		int start, int end) {
		return _registrationLocalService.getRegistrations(start, end);
	}

	/**
	* Returns all the registrations matching the UUID and company.
	*
	* @param uuid the UUID of the registrations
	* @param companyId the primary key of the company
	* @return the matching registrations, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.Registration> getRegistrationsByUuidAndCompanyId(
		String uuid, long companyId) {
		return _registrationLocalService.getRegistrationsByUuidAndCompanyId(uuid,
			companyId);
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
	@Override
	public java.util.List<org.opencps.dossiermgt.model.Registration> getRegistrationsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.Registration> orderByComparator) {
		return _registrationLocalService.getRegistrationsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of registrations.
	*
	* @return the number of registrations
	*/
	@Override
	public int getRegistrationsCount() {
		return _registrationLocalService.getRegistrationsCount();
	}

	@Override
	public org.opencps.dossiermgt.model.Registration insert(long groupId,
		long companyId, String applicantName, String applicantIdType,
		String applicantIdNo, String applicantIdDate, String address,
		String cityCode, String cityName, String districtCode,
		String districtName, String wardCode, String wardName,
		String contactName, String contactTelNo, String contactEmail,
		String govAgencyCode, String govAgencyName, int registrationState,
		String registrationClass, String representativeEnterprise,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _registrationLocalService.insert(groupId, companyId,
			applicantName, applicantIdType, applicantIdNo, applicantIdDate,
			address, cityCode, cityName, districtCode, districtName, wardCode,
			wardName, contactName, contactTelNo, contactEmail, govAgencyCode,
			govAgencyName, registrationState, registrationClass,
			representativeEnterprise, serviceContext);
	}

	@Override
	public org.opencps.dossiermgt.model.Registration registrationSync(
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
		return _registrationLocalService.registrationSync(groupId, uuid,
			applicantName, applicantIdType, applicantIdNo, applicantIdDate,
			address, cityCode, cityName, districtCode, districtName, wardCode,
			wardName, contactName, contactTelNo, contactEmail, govAgencyCode,
			govAgencyName, registrationState, registrationClass,
			representativeEnterprise, serviceContext);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits searchLucene(long userId,
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _registrationLocalService.searchLucene(userId, params, sorts,
			start, end, searchContext);
	}

	@Override
	public org.opencps.dossiermgt.model.Registration updateRegistration(
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
		return _registrationLocalService.updateRegistration(groupId,
			registrationId, applicantName, applicantIdType, applicantIdNo,
			applicantIdDate, address, cityCode, cityName, districtCode,
			districtName, wardCode, wardName, contactName, contactTelNo,
			contactEmail, govAgencyCode, govAgencyName, registrationState,
			registrationClass, representativeEnterprise, serviceContext);
	}

	/**
	* Updates the registration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param registration the registration
	* @return the registration that was updated
	*/
	@Override
	public org.opencps.dossiermgt.model.Registration updateRegistration(
		org.opencps.dossiermgt.model.Registration registration) {
		return _registrationLocalService.updateRegistration(registration);
	}

	@Override
	public org.opencps.dossiermgt.model.Registration updateSubmitting(
		long registrationId, boolean submitting)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _registrationLocalService.updateSubmitting(registrationId,
			submitting);
	}

	@Override
	public RegistrationLocalService getWrappedService() {
		return _registrationLocalService;
	}

	@Override
	public void setWrappedService(
		RegistrationLocalService registrationLocalService) {
		_registrationLocalService = registrationLocalService;
	}

	private RegistrationLocalService _registrationLocalService;
}