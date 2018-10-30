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

package org.opencps.usermgt.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ApplicantLocalService}.
 *
 * @author khoavu
 * @see ApplicantLocalService
 * @generated
 */
@ProviderType
public class ApplicantLocalServiceWrapper implements ApplicantLocalService,
	ServiceWrapper<ApplicantLocalService> {
	public ApplicantLocalServiceWrapper(
		ApplicantLocalService applicantLocalService) {
		_applicantLocalService = applicantLocalService;
	}

	@Override
	public org.opencps.usermgt.model.Applicant activateApplicant(
		long applicantId,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _applicantLocalService.activateApplicant(applicantId, context);
	}

	/**
	* Adds the applicant to the database. Also notifies the appropriate model listeners.
	*
	* @param applicant the applicant
	* @return the applicant that was added
	*/
	@Override
	public org.opencps.usermgt.model.Applicant addApplicant(
		org.opencps.usermgt.model.Applicant applicant) {
		return _applicantLocalService.addApplicant(applicant);
	}

	@Override
	public org.opencps.usermgt.model.Applicant adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return _applicantLocalService.adminProcessData(objectData);
	}

	@Override
	public org.opencps.usermgt.model.Applicant adminProcessDelete(Long id) {
		return _applicantLocalService.adminProcessDelete(id);
	}

	@Override
	public long countLucene(java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _applicantLocalService.countLucene(params, searchContext);
	}

	/**
	* Creates a new applicant with the primary key. Does not add the applicant to the database.
	*
	* @param applicantId the primary key for the new applicant
	* @return the new applicant
	*/
	@Override
	public org.opencps.usermgt.model.Applicant createApplicant(long applicantId) {
		return _applicantLocalService.createApplicant(applicantId);
	}

	/**
	* Deletes the applicant from the database. Also notifies the appropriate model listeners.
	*
	* @param applicant the applicant
	* @return the applicant that was removed
	*/
	@Override
	public org.opencps.usermgt.model.Applicant deleteApplicant(
		org.opencps.usermgt.model.Applicant applicant) {
		return _applicantLocalService.deleteApplicant(applicant);
	}

	/**
	* Deletes the applicant with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param applicantId the primary key of the applicant
	* @return the applicant that was removed
	* @throws PortalException if a applicant with the primary key could not be found
	*/
	@Override
	public org.opencps.usermgt.model.Applicant deleteApplicant(long applicantId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _applicantLocalService.deleteApplicant(applicantId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _applicantLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _applicantLocalService.dynamicQuery();
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
		return _applicantLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.ApplicantModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _applicantLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.ApplicantModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _applicantLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _applicantLocalService.dynamicQueryCount(dynamicQuery);
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
		return _applicantLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public org.opencps.usermgt.model.Applicant fetchApplicant(long applicantId) {
		return _applicantLocalService.fetchApplicant(applicantId);
	}

	/**
	* Returns the applicant matching the UUID and group.
	*
	* @param uuid the applicant's UUID
	* @param groupId the primary key of the group
	* @return the matching applicant, or <code>null</code> if a matching applicant could not be found
	*/
	@Override
	public org.opencps.usermgt.model.Applicant fetchApplicantByUuidAndGroupId(
		String uuid, long groupId) {
		return _applicantLocalService.fetchApplicantByUuidAndGroupId(uuid,
			groupId);
	}

	@Override
	public org.opencps.usermgt.model.Applicant fetchByAppId(String appId) {
		return _applicantLocalService.fetchByAppId(appId);
	}

	@Override
	public org.opencps.usermgt.model.Applicant fetchByEmail(String email) {
		return _applicantLocalService.fetchByEmail(email);
	}

	@Override
	public org.opencps.usermgt.model.Applicant fetchByMappingID(long mappingID) {
		return _applicantLocalService.fetchByMappingID(mappingID);
	}

	@Override
	public org.opencps.usermgt.model.Applicant fetchByTelNo(String telNo) {
		return _applicantLocalService.fetchByTelNo(telNo);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _applicantLocalService.getActionableDynamicQuery();
	}

	/**
	* Returns the applicant with the primary key.
	*
	* @param applicantId the primary key of the applicant
	* @return the applicant
	* @throws PortalException if a applicant with the primary key could not be found
	*/
	@Override
	public org.opencps.usermgt.model.Applicant getApplicant(long applicantId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _applicantLocalService.getApplicant(applicantId);
	}

	/**
	* Returns the applicant matching the UUID and group.
	*
	* @param uuid the applicant's UUID
	* @param groupId the primary key of the group
	* @return the matching applicant
	* @throws PortalException if a matching applicant could not be found
	*/
	@Override
	public org.opencps.usermgt.model.Applicant getApplicantByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _applicantLocalService.getApplicantByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the applicants.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.usermgt.model.impl.ApplicantModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of applicants
	* @param end the upper bound of the range of applicants (not inclusive)
	* @return the range of applicants
	*/
	@Override
	public java.util.List<org.opencps.usermgt.model.Applicant> getApplicants(
		int start, int end) {
		return _applicantLocalService.getApplicants(start, end);
	}

	/**
	* Returns all the applicants matching the UUID and company.
	*
	* @param uuid the UUID of the applicants
	* @param companyId the primary key of the company
	* @return the matching applicants, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.usermgt.model.Applicant> getApplicantsByUuidAndCompanyId(
		String uuid, long companyId) {
		return _applicantLocalService.getApplicantsByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of applicants matching the UUID and company.
	*
	* @param uuid the UUID of the applicants
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of applicants
	* @param end the upper bound of the range of applicants (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching applicants, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.usermgt.model.Applicant> getApplicantsByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.usermgt.model.Applicant> orderByComparator) {
		return _applicantLocalService.getApplicantsByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of applicants.
	*
	* @return the number of applicants
	*/
	@Override
	public int getApplicantsCount() {
		return _applicantLocalService.getApplicantsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _applicantLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _applicantLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _applicantLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _applicantLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public org.opencps.usermgt.model.Applicant lockoutApplicant(
		long applicantId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _applicantLocalService.lockoutApplicant(applicantId);
	}

	@Override
	public org.opencps.usermgt.model.Applicant removeApplicant(long applicantId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _applicantLocalService.removeApplicant(applicantId);
	}

	@Override
	public org.opencps.usermgt.model.Applicant removeProfile(long applicantId) {
		return _applicantLocalService.removeProfile(applicantId);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits searchLucene(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _applicantLocalService.searchLucene(params, sorts, start, end,
			searchContext);
	}

	/**
	* Updates the applicant in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param applicant the applicant
	* @return the applicant that was updated
	*/
	@Override
	public org.opencps.usermgt.model.Applicant updateApplicant(
		org.opencps.usermgt.model.Applicant applicant) {
		return _applicantLocalService.updateApplicant(applicant);
	}

	@Override
	public org.opencps.usermgt.model.Applicant updateApplicant(long groupId,
		long userId, long companyId, String applicantName,
		String applicantIdType, String applicantIdNo,
		java.util.Date applicantIdDate, String address, String cityCode,
		String cityName, String districtCode, String districtName,
		String wardCode, String wardName, String contactName,
		String contactTelNo, String contactEmail) {
		return _applicantLocalService.updateApplicant(groupId, userId,
			companyId, applicantName, applicantIdType, applicantIdNo,
			applicantIdDate, address, cityCode, cityName, districtCode,
			districtName, wardCode, wardName, contactName, contactTelNo,
			contactEmail);
	}

	/**
	* @param context
	* @param appicantId
	* @param applicantName
	* @param applicantIdNo
	* @param applicantIdDate
	* @param address
	* @param cityCode
	* @param cityName
	* @param districtCode
	* @param districtName
	* @param wardCode
	* @param wardName
	* @param contactName
	* @param contactTelNo
	* @param contactEmail
	* @param mappingUserId
	* @param profile
	* @return
	* @throws PortalException
	* @throws SystemException
	*/
	@Override
	public org.opencps.usermgt.model.Applicant updateApplication(
		com.liferay.portal.kernel.service.ServiceContext context, long groupId,
		long applicantId, String applicantName, String applicantIdType,
		String applicantIdNo, String applicantIdDate, String address,
		String cityCode, String cityName, String districtCode,
		String districtName, String wardCode, String wardName,
		String contactName, String contactTelNo, String contactEmail,
		String profile, String password)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _applicantLocalService.updateApplication(context, groupId,
			applicantId, applicantName, applicantIdType, applicantIdNo,
			applicantIdDate, address, cityCode, cityName, districtCode,
			districtName, wardCode, wardName, contactName, contactTelNo,
			contactEmail, profile, password);
	}

	@Override
	public ApplicantLocalService getWrappedService() {
		return _applicantLocalService;
	}

	@Override
	public void setWrappedService(ApplicantLocalService applicantLocalService) {
		_applicantLocalService = applicantLocalService;
	}

	private ApplicantLocalService _applicantLocalService;
}