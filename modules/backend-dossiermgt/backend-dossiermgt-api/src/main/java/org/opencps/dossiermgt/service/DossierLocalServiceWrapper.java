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
 * Provides a wrapper for {@link DossierLocalService}.
 *
 * @author huymq
 * @see DossierLocalService
 * @generated
 */
@ProviderType
public class DossierLocalServiceWrapper implements DossierLocalService,
	ServiceWrapper<DossierLocalService> {
	public DossierLocalServiceWrapper(DossierLocalService dossierLocalService) {
		_dossierLocalService = dossierLocalService;
	}

	/**
	* Adds the dossier to the database. Also notifies the appropriate model listeners.
	*
	* @param dossier the dossier
	* @return the dossier that was added
	*/
	@Override
	public org.opencps.dossiermgt.model.Dossier addDossier(
		org.opencps.dossiermgt.model.Dossier dossier) {
		return _dossierLocalService.addDossier(dossier);
	}

	@Override
	public org.opencps.dossiermgt.model.Dossier assignToProcess(
		long dossierId, String dossierNote, String submissionNote,
		String briefNote, String dossierNo, long folderId,
		long dossierActionId, String serverNo,
		com.liferay.portal.kernel.service.ServiceContext context) {
		return _dossierLocalService.assignToProcess(dossierId, dossierNote,
			submissionNote, briefNote, dossierNo, folderId, dossierActionId,
			serverNo, context);
	}

	@Override
	public org.opencps.dossiermgt.model.Dossier cloneDossier(
		org.opencps.dossiermgt.model.Dossier srcDossier)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierLocalService.cloneDossier(srcDossier);
	}

	@Override
	public int countByUserId(long userId, long groupId) {
		return _dossierLocalService.countByUserId(userId, groupId);
	}

	@Override
	public long countDossierByG_C_GAC_SC_DTNO_NOTDS(long groupId,
		long companyId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus) {
		return _dossierLocalService.countDossierByG_C_GAC_SC_DTNO_NOTDS(groupId,
			companyId, govAgencyCode, serviceCode, dossierTemplateNo,
			dossierStatus);
	}

	@Override
	public long countDossierByGroup(long groupId) {
		return _dossierLocalService.countDossierByGroup(groupId);
	}

	@Override
	public long countLucene(java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _dossierLocalService.countLucene(params, searchContext);
	}

	/**
	* Creates a new dossier with the primary key. Does not add the dossier to the database.
	*
	* @param dossierId the primary key for the new dossier
	* @return the new dossier
	*/
	@Override
	public org.opencps.dossiermgt.model.Dossier createDossier(long dossierId) {
		return _dossierLocalService.createDossier(dossierId);
	}

	@Override
	public org.opencps.dossiermgt.model.Dossier createDossier(long groupId,
		String serviceCode, String govAgencyCode, String applicantName,
		String applicantIdType, String applicantIdNo,
		java.util.Date applicantIdDate, String address, String cityCode,
		String districtCode, String wardCode, String contactName,
		String contactTelNo, String contactEmail, boolean isSameAsApplicant,
		String delegateName, String delegateIdNo, String delegateTelNo,
		String delegateEmail, String delegateAddress, String delegateCityCode,
		String delegateDistrictCode, String delegateWardCode,
		String applicantNote, String briefNote, String dossierNo,
		String dossierTemplateNo, int viaPostal, String postalServiceCode,
		String postalServiceName, String postalAddress, String postalCityCode,
		String postalDistrictCode, String postalWardCode, String postalTelNo,
		int originality,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierLocalService.createDossier(groupId, serviceCode,
			govAgencyCode, applicantName, applicantIdType, applicantIdNo,
			applicantIdDate, address, cityCode, districtCode, wardCode,
			contactName, contactTelNo, contactEmail, isSameAsApplicant,
			delegateName, delegateIdNo, delegateTelNo, delegateEmail,
			delegateAddress, delegateCityCode, delegateDistrictCode,
			delegateWardCode, applicantNote, briefNote, dossierNo,
			dossierTemplateNo, viaPostal, postalServiceCode, postalServiceName,
			postalAddress, postalCityCode, postalDistrictCode, postalWardCode,
			postalTelNo, originality, context);
	}

	/**
	* Deletes the dossier from the database. Also notifies the appropriate model listeners.
	*
	* @param dossier the dossier
	* @return the dossier that was removed
	*/
	@Override
	public org.opencps.dossiermgt.model.Dossier deleteDossier(
		org.opencps.dossiermgt.model.Dossier dossier) {
		return _dossierLocalService.deleteDossier(dossier);
	}

	/**
	* Deletes the dossier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierId the primary key of the dossier
	* @return the dossier that was removed
	* @throws PortalException if a dossier with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.Dossier deleteDossier(long dossierId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierLocalService.deleteDossier(dossierId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _dossierLocalService.dynamicQuery();
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
		return _dossierLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dossierLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _dossierLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _dossierLocalService.dynamicQueryCount(dynamicQuery);
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
		return _dossierLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public org.opencps.dossiermgt.model.Dossier fetchDossier(long dossierId) {
		return _dossierLocalService.fetchDossier(dossierId);
	}

	/**
	* Returns the dossier matching the UUID and group.
	*
	* @param uuid the dossier's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.Dossier fetchDossierByUuidAndGroupId(
		String uuid, long groupId) {
		return _dossierLocalService.fetchDossierByUuidAndGroupId(uuid, groupId);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.Dossier> findByDN_AN(
		String dossierNo, String applicantIdNo) {
		return _dossierLocalService.findByDN_AN(dossierNo, applicantIdNo);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.Dossier> findByVIAPOSTAL(
		int viaPostal) {
		return _dossierLocalService.findByVIAPOSTAL(viaPostal);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.Dossier> findDossierByGroup(
		long groupId) {
		return _dossierLocalService.findDossierByGroup(groupId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _dossierLocalService.getActionableDynamicQuery();
	}

	@Override
	public org.opencps.dossiermgt.model.Dossier getByIdAndGovService(
		long groupId, String serviceCode, String govAgencyCode, long dossierId) {
		return _dossierLocalService.getByIdAndGovService(groupId, serviceCode,
			govAgencyCode, dossierId);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.Dossier> getByNotO_DS_SC_GC(
		long groupId, int originality, String dossierStatus,
		String serviceCode, String govAgencyCode) {
		return _dossierLocalService.getByNotO_DS_SC_GC(groupId, originality,
			dossierStatus, serviceCode, govAgencyCode);
	}

	@Override
	public org.opencps.dossiermgt.model.Dossier getByOrigin(long groupId,
		long originDossierId) {
		return _dossierLocalService.getByOrigin(groupId, originDossierId);
	}

	@Override
	public org.opencps.dossiermgt.model.Dossier getByRef(long groupId,
		String refId) {
		return _dossierLocalService.getByRef(groupId, refId);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.Dossier> getByU_G_C_DS_SC_GC_O(
		long userId, long groupId, String serviceCode, String govAgencyCode,
		long dossierActionId, int originality) {
		return _dossierLocalService.getByU_G_C_DS_SC_GC_O(userId, groupId,
			serviceCode, govAgencyCode, dossierActionId, originality);
	}

	/**
	* Returns the dossier with the primary key.
	*
	* @param dossierId the primary key of the dossier
	* @return the dossier
	* @throws PortalException if a dossier with the primary key could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.Dossier getDossier(long dossierId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierLocalService.getDossier(dossierId);
	}

	@Override
	public java.util.List<org.opencps.dossiermgt.model.Dossier> getDossierByG_NOTO_DS(
		int originality, String dossierStatus) {
		return _dossierLocalService.getDossierByG_NOTO_DS(originality,
			dossierStatus);
	}

	@Override
	public com.liferay.portal.kernel.search.Document getDossierById(
		long dossierId, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierLocalService.getDossierById(dossierId, companyId);
	}

	/**
	* Returns the dossier matching the UUID and group.
	*
	* @param uuid the dossier's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier
	* @throws PortalException if a matching dossier could not be found
	*/
	@Override
	public org.opencps.dossiermgt.model.Dossier getDossierByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierLocalService.getDossierByUuidAndGroupId(uuid, groupId);
	}

	/**
	* Returns a range of all the dossiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @return the range of dossiers
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.Dossier> getDossiers(
		int start, int end) {
		return _dossierLocalService.getDossiers(start, end);
	}

	/**
	* Returns all the dossiers matching the UUID and company.
	*
	* @param uuid the UUID of the dossiers
	* @param companyId the primary key of the company
	* @return the matching dossiers, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.Dossier> getDossiersByUuidAndCompanyId(
		String uuid, long companyId) {
		return _dossierLocalService.getDossiersByUuidAndCompanyId(uuid,
			companyId);
	}

	/**
	* Returns a range of dossiers matching the UUID and company.
	*
	* @param uuid the UUID of the dossiers
	* @param companyId the primary key of the company
	* @param start the lower bound of the range of dossiers
	* @param end the upper bound of the range of dossiers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the range of matching dossiers, or an empty list if no matches were found
	*/
	@Override
	public java.util.List<org.opencps.dossiermgt.model.Dossier> getDossiersByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.Dossier> orderByComparator) {
		return _dossierLocalService.getDossiersByUuidAndCompanyId(uuid,
			companyId, start, end, orderByComparator);
	}

	/**
	* Returns the number of dossiers.
	*
	* @return the number of dossiers
	*/
	@Override
	public int getDossiersCount() {
		return _dossierLocalService.getDossiersCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery getExportActionableDynamicQuery(
		com.liferay.exportimport.kernel.lar.PortletDataContext portletDataContext) {
		return _dossierLocalService.getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _dossierLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _dossierLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public org.opencps.dossiermgt.model.Dossier initDossier(long groupId,
		long dossierId, String referenceUid, int counter, String serviceCode,
		String serviceName, String govAgencyCode, String govAgencyName,
		String applicantName, String applicantIdType, String applicantIdNo,
		java.util.Date applicantIdDate, String address, String cityCode,
		String cityName, String districtCode, String districtName,
		String wardCode, String wardName, String contactName,
		String contactTelNo, String contactEmail, String dossierTemplateNo,
		String password, int viaPostal, String postalAddress,
		String postalCityCode, String postalCityName, String postalTelNo,
		boolean online, boolean notification, String applicantNote,
		int originality,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierLocalService.initDossier(groupId, dossierId,
			referenceUid, counter, serviceCode, serviceName, govAgencyCode,
			govAgencyName, applicantName, applicantIdType, applicantIdNo,
			applicantIdDate, address, cityCode, cityName, districtCode,
			districtName, wardCode, wardName, contactName, contactTelNo,
			contactEmail, dossierTemplateNo, password, viaPostal,
			postalAddress, postalCityCode, postalCityName, postalTelNo, online,
			notification, applicantNote, originality, context);
	}

	@Override
	public org.opencps.dossiermgt.model.Dossier initUpdateDossier(
		long groupId, long id, String applicantName, String applicantIdType,
		String applicantIdNo, String applicantIdDate, String address,
		String cityCode, String cityName, String districtCode,
		String districtName, String wardCode, String wardName,
		String contactName, String contactTelNo, String contactEmail,
		String dossierTemplateNo, int viaPostal, String postalAddress,
		String postalCityCode, String postalCityName, String postalTelNo,
		String applicantNote, boolean isSameAsApplicant, String delegateName,
		String delegateIdNo, String delegateTelNo, String delegateEmail,
		String delegateAddress, String delegateCityCode,
		String delegateDistrictCode, String delegateWardCode, Long sampleCount,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		return _dossierLocalService.initUpdateDossier(groupId, id,
			applicantName, applicantIdType, applicantIdNo, applicantIdDate,
			address, cityCode, cityName, districtCode, districtName, wardCode,
			wardName, contactName, contactTelNo, contactEmail,
			dossierTemplateNo, viaPostal, postalAddress, postalCityCode,
			postalCityName, postalTelNo, applicantNote, isSameAsApplicant,
			delegateName, delegateIdNo, delegateTelNo, delegateEmail,
			delegateAddress, delegateCityCode, delegateDistrictCode,
			delegateWardCode, sampleCount, serviceContext);
	}

	@Override
	public org.opencps.dossiermgt.model.Dossier postDossier(long groupId,
		long dossierId, String referenceUid, int counter, String serviceCode,
		String serviceName, String govAgencyCode, String govAgencyName,
		String applicantName, String applicantIdType, String applicantIdNo,
		java.util.Date applicantIdDate, String address, String cityCode,
		String cityName, String districtCode, String districtName,
		String wardCode, String wardName, String contactName,
		String contactTelNo, String contactEmail, String dossierTemplateNo,
		String password, int viaPostal, String postalAddress,
		String postalCityCode, String postalCityName, String postalTelNo,
		boolean online, boolean notification, String applicantNote,
		int originality,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierLocalService.postDossier(groupId, dossierId,
			referenceUid, counter, serviceCode, serviceName, govAgencyCode,
			govAgencyName, applicantName, applicantIdType, applicantIdNo,
			applicantIdDate, address, cityCode, cityName, districtCode,
			districtName, wardCode, wardName, contactName, contactTelNo,
			contactEmail, dossierTemplateNo, password, viaPostal,
			postalAddress, postalCityCode, postalCityName, postalTelNo, online,
			notification, applicantNote, originality, context);
	}

	@Override
	public org.opencps.dossiermgt.model.Dossier publishDossier(long groupId,
		long dossierId, String referenceUid, int counter, String serviceCode,
		String serviceName, String govAgencyCode, String govAgencyName,
		String applicantName, String applicantIdType, String applicantIdNo,
		java.util.Date applicantIdDate, String address, String cityCode,
		String cityName, String districtCode, String districtName,
		String wardCode, String wardName, String contactName,
		String contactTelNo, String contactEmail, String dossierTemplateNo,
		String password, int viaPostal, String postalAddress,
		String postalCityCode, String postalCityName, String postalTelNo,
		boolean online, boolean notification, String applicantNote,
		int originality, java.util.Date createDate,
		java.util.Date modifiedDate, java.util.Date submitDate,
		java.util.Date receiveDate, java.util.Date dueDate,
		java.util.Date releaseDate, java.util.Date finishDate,
		java.util.Date cancellingDate, java.util.Date correctingDate,
		java.util.Date endorsementDate, java.util.Date extendDate,
		java.util.Date processDate,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierLocalService.publishDossier(groupId, dossierId,
			referenceUid, counter, serviceCode, serviceName, govAgencyCode,
			govAgencyName, applicantName, applicantIdType, applicantIdNo,
			applicantIdDate, address, cityCode, cityName, districtCode,
			districtName, wardCode, wardName, contactName, contactTelNo,
			contactEmail, dossierTemplateNo, password, viaPostal,
			postalAddress, postalCityCode, postalCityName, postalTelNo, online,
			notification, applicantNote, originality, createDate, modifiedDate,
			submitDate, receiveDate, dueDate, releaseDate, finishDate,
			cancellingDate, correctingDate, endorsementDate, extendDate,
			processDate, context);
	}

	@Override
	public org.opencps.dossiermgt.model.Dossier removeDossier(long groupId,
		long dossierId, String refId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierLocalService.removeDossier(groupId, dossierId, refId);
	}

	@Override
	public void removeDossierByG_NOTO_DS(int originality, String dossierStatus) {
		_dossierLocalService.removeDossierByG_NOTO_DS(originality, dossierStatus);
	}

	@Override
	public org.opencps.dossiermgt.model.Dossier reset(long groupId, long id,
		String refId, com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierLocalService.reset(groupId, id, refId, context);
	}

	@Override
	public org.opencps.dossiermgt.model.Dossier rollback(
		org.opencps.dossiermgt.model.Dossier dossier,
		org.opencps.dossiermgt.model.DossierAction dossierAction) {
		return _dossierLocalService.rollback(dossier, dossierAction);
	}

	@Override
	public com.liferay.portal.kernel.search.Hits searchLucene(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.Sort[] sorts, int start, int end,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return _dossierLocalService.searchLucene(params, sorts, start, end,
			searchContext);
	}

	@Override
	public org.opencps.dossiermgt.model.Dossier submitting(long groupId,
		long id, String refId,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierLocalService.submitting(groupId, id, refId, context);
	}

	@Override
	public org.opencps.dossiermgt.model.Dossier syncDossier(
		org.opencps.dossiermgt.model.Dossier dossier)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierLocalService.syncDossier(dossier);
	}

	@Override
	public org.opencps.dossiermgt.model.Dossier updateApplicantInfo(
		long dossierId, java.util.Date applicantIdDate, String applicantIdNo,
		String applicantIdType, String applicantName, String address,
		String cityCode, String cityName, String districtCode,
		String districtName, String wardCode, String wardName,
		String contactEmail, String contactTelNo)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return _dossierLocalService.updateApplicantInfo(dossierId,
			applicantIdDate, applicantIdNo, applicantIdType, applicantName,
			address, cityCode, cityName, districtCode, districtName, wardCode,
			wardName, contactEmail, contactTelNo);
	}

	@Override
	public org.opencps.dossiermgt.model.Dossier updateCancellingDate(
		long groupId, long id, String refId, java.util.Date date,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierLocalService.updateCancellingDate(groupId, id, refId,
			date, context);
	}

	@Override
	public org.opencps.dossiermgt.model.Dossier updateCorrectingDate(
		long groupId, long id, String refId, java.util.Date date,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierLocalService.updateCorrectingDate(groupId, id, refId,
			date, context);
	}

	/**
	* Updates the dossier in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dossier the dossier
	* @return the dossier that was updated
	*/
	@Override
	public org.opencps.dossiermgt.model.Dossier updateDossier(
		org.opencps.dossiermgt.model.Dossier dossier) {
		return _dossierLocalService.updateDossier(dossier);
	}

	@Override
	public org.opencps.dossiermgt.model.Dossier updateDossier(long dossierId,
		com.liferay.portal.kernel.json.JSONObject obj)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return _dossierLocalService.updateDossier(dossierId, obj);
	}

	@Override
	public org.opencps.dossiermgt.model.Dossier updateDossier(long groupId,
		long dossierId, String referenceUid, int counter, String serviceCode,
		String serviceName, String govAgencyCode, String govAgencyName,
		String applicantName, String applicantIdType, String applicantIdNo,
		java.util.Date applicantIdDate, String address, String cityCode,
		String cityName, String districtCode, String districtName,
		String wardCode, String wardName, String contactName,
		String contactTelNo, String contactEmail, String dossierTemplateNo,
		String dossierNote, String submissionNote, String applicantNote,
		String briefNote, String dossierNo, boolean submitting,
		java.util.Date correctingDate, String dossierStatus,
		String dossierStatusText, String dossierSubStatus,
		String dossierSubStatusText, long folderId, long dossierActionId,
		int viaPostal, String postalAddress, String postalCityCode,
		String postalCityName, String postalTelNo, String password,
		boolean notification, boolean online, String serverNo,
		java.util.Date submitDate,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierLocalService.updateDossier(groupId, dossierId,
			referenceUid, counter, serviceCode, serviceName, govAgencyCode,
			govAgencyName, applicantName, applicantIdType, applicantIdNo,
			applicantIdDate, address, cityCode, cityName, districtCode,
			districtName, wardCode, wardName, contactName, contactTelNo,
			contactEmail, dossierTemplateNo, dossierNote, submissionNote,
			applicantNote, briefNote, dossierNo, submitting, correctingDate,
			dossierStatus, dossierStatusText, dossierSubStatus,
			dossierSubStatusText, folderId, dossierActionId, viaPostal,
			postalAddress, postalCityCode, postalCityName, postalTelNo,
			password, notification, online, serverNo, submitDate, context);
	}

	@Override
	public org.opencps.dossiermgt.model.Dossier updateDossier(long groupId,
		long dossierId, String referenceUid, int counter, String serviceCode,
		String serviceName, String govAgencyCode, String govAgencyName,
		String applicantName, String applicantIdType, String applicantIdNo,
		java.util.Date applicantIdDate, String address, String cityCode,
		String cityName, String districtCode, String districtName,
		String wardCode, String wardName, String contactName,
		String contactTelNo, String contactEmail, String dossierTemplateNo,
		String dossierNote, String submissionNote, String applicantNote,
		String briefNote, String dossierNo, boolean submitting,
		java.util.Date correctingDate, String dossierStatus,
		String dossierStatusText, String dossierSubStatus,
		String dossierSubStatusText, long folderId, long dossierActionId,
		int viaPostal, String postalAddress, String postalCityCode,
		String postalCityName, String postalTelNo, String password,
		boolean notification, boolean online, String serverNo,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierLocalService.updateDossier(groupId, dossierId,
			referenceUid, counter, serviceCode, serviceName, govAgencyCode,
			govAgencyName, applicantName, applicantIdType, applicantIdNo,
			applicantIdDate, address, cityCode, cityName, districtCode,
			districtName, wardCode, wardName, contactName, contactTelNo,
			contactEmail, dossierTemplateNo, dossierNote, submissionNote,
			applicantNote, briefNote, dossierNo, submitting, correctingDate,
			dossierStatus, dossierStatusText, dossierSubStatus,
			dossierSubStatusText, folderId, dossierActionId, viaPostal,
			postalAddress, postalCityCode, postalCityName, postalTelNo,
			password, notification, online, serverNo, context);
	}

	@Override
	public org.opencps.dossiermgt.model.Dossier updateDossierAction(
		long groupId, long id, String refId, long dossierActionId,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierLocalService.updateDossierAction(groupId, id, refId,
			dossierActionId, context);
	}

	@Override
	public org.opencps.dossiermgt.model.Dossier updateDossierBriefNote(
		long dossierId, String dossierBriefNote)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierLocalService.updateDossierBriefNote(dossierId,
			dossierBriefNote);
	}

	@Override
	public org.opencps.dossiermgt.model.Dossier updateDossierOneGate(
		long dossierId, String applicantName, String applicantIdType,
		String applicantIdNo, java.util.Date applicantIdDate, String address,
		String cityCode, String districtCode, String wardCode,
		String contactName, String contactTelNo, String contactEmail,
		boolean isSameAsApplicant, String delegateName, String delegateIdNo,
		String delegateTelNo, String delegateEmail, String delegateAddress,
		String delegateCityCode, String delegateDistrictCode,
		String delegateWardCode, String applicantNote, String briefNote,
		String dossierNo, int viaPostal, String postalServiceCode,
		String postalServiceName, String postalAddress, String postalCityCode,
		String postalDistrictCode, String postalWardCode, String postalTelNo,
		long dossierActionId, String paymentFee, String paymentFeeNote,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierLocalService.updateDossierOneGate(dossierId,
			applicantName, applicantIdType, applicantIdNo, applicantIdDate,
			address, cityCode, districtCode, wardCode, contactName,
			contactTelNo, contactEmail, isSameAsApplicant, delegateName,
			delegateIdNo, delegateTelNo, delegateEmail, delegateAddress,
			delegateCityCode, delegateDistrictCode, delegateWardCode,
			applicantNote, briefNote, dossierNo, viaPostal, postalServiceCode,
			postalServiceName, postalAddress, postalCityCode,
			postalDistrictCode, postalWardCode, postalTelNo, dossierActionId,
			paymentFee, paymentFeeNote, context);
	}

	@Override
	public org.opencps.dossiermgt.model.Dossier updateDueDate(long groupId,
		long id, String refId, java.util.Date date,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierLocalService.updateDueDate(groupId, id, refId, date,
			context);
	}

	@Override
	public org.opencps.dossiermgt.model.Dossier updateEndosementDate(
		long groupId, long id, String refId, java.util.Date date,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierLocalService.updateEndosementDate(groupId, id, refId,
			date, context);
	}

	@Override
	public org.opencps.dossiermgt.model.Dossier updateFinishDate(long groupId,
		long id, String refId, java.util.Date date,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierLocalService.updateFinishDate(groupId, id, refId, date,
			context);
	}

	@Override
	public org.opencps.dossiermgt.model.Dossier updateProcessDate(
		long groupId, long id, String refId, java.util.Date date,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierLocalService.updateProcessDate(groupId, id, refId, date,
			context);
	}

	@Override
	public org.opencps.dossiermgt.model.Dossier updateReceivingDate(
		long groupId, long id, String refId, java.util.Date date,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierLocalService.updateReceivingDate(groupId, id, refId,
			date, context);
	}

	@Override
	public org.opencps.dossiermgt.model.Dossier updateReleaseDate(
		long groupId, long id, String refId, java.util.Date date,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierLocalService.updateReleaseDate(groupId, id, refId, date,
			context);
	}

	@Override
	public org.opencps.dossiermgt.model.Dossier updateStatus(long groupId,
		long id, String refId, String status, String statusText,
		String subStatus, String subStatusText, String lockState,
		String stepInstruction,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierLocalService.updateStatus(groupId, id, refId, status,
			statusText, subStatus, subStatusText, lockState, stepInstruction,
			context);
	}

	@Override
	public org.opencps.dossiermgt.model.Dossier updateSubmittingDate(
		long groupId, long id, String refId, java.util.Date date,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierLocalService.updateSubmittingDate(groupId, id, refId,
			date, context);
	}

	@Override
	public org.opencps.dossiermgt.model.Dossier updateViaPostal(long groupId,
		long id, String refId, int viaPostal,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _dossierLocalService.updateViaPostal(groupId, id, refId,
			viaPostal, context);
	}

	@Override
	public DossierLocalService getWrappedService() {
		return _dossierLocalService;
	}

	@Override
	public void setWrappedService(DossierLocalService dossierLocalService) {
		_dossierLocalService = dossierLocalService;
	}

	private DossierLocalService _dossierLocalService;
}