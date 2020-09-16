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
 * Provides the local service utility for Dossier. This utility wraps
 * {@link org.opencps.dossiermgt.service.impl.DossierLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author huymq
 * @see DossierLocalService
 * @see org.opencps.dossiermgt.service.base.DossierLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.DossierLocalServiceImpl
 * @generated
 */
@ProviderType
public class DossierLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.DossierLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the dossier to the database. Also notifies the appropriate model listeners.
	*
	* @param dossier the dossier
	* @return the dossier that was added
	*/
	public static org.opencps.dossiermgt.model.Dossier addDossier(
		org.opencps.dossiermgt.model.Dossier dossier) {
		return getService().addDossier(dossier);
	}

	public static org.opencps.dossiermgt.model.Dossier adminProcessData(
		com.liferay.portal.kernel.json.JSONObject objectData) {
		return getService().adminProcessData(objectData);
	}

	public static org.opencps.dossiermgt.model.Dossier adminProcessDelete(
		Long id) {
		return getService().adminProcessDelete(id);
	}

	public static org.opencps.dossiermgt.model.Dossier assignToProcess(
		long dossierId, String dossierNote, String submissionNote,
		String briefNote, String dossierNo, long folderId,
		long dossierActionId, String serverNo,
		com.liferay.portal.kernel.service.ServiceContext context) {
		return getService()
				   .assignToProcess(dossierId, dossierNote, submissionNote,
			briefNote, dossierNo, folderId, dossierActionId, serverNo, context);
	}

	public static org.opencps.dossiermgt.model.Dossier cloneDossier(
		org.opencps.dossiermgt.model.Dossier srcDossier)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().cloneDossier(srcDossier);
	}

	public static int countByG_NOTS_O_DTN(long groupId,
		String[] dossierStatuses, int originality, String dossierTemplateNo) {
		return getService()
				   .countByG_NOTS_O_DTN(groupId, dossierStatuses, originality,
			dossierTemplateNo);
	}

	public static int countByG_NOTS_O_PN(long groupId,
		String[] dossierStatuses, int originality, String processNo) {
		return getService()
				   .countByG_NOTS_O_PN(groupId, dossierStatuses, originality,
			processNo);
	}

	public static int countByG_NOTS_O_SC(long groupId,
		String[] dossierStatuses, int originality, String serviceCode) {
		return getService()
				   .countByG_NOTS_O_SC(groupId, dossierStatuses, originality,
			serviceCode);
	}

	public static int countByG_UID_DS(long groupId, long userId,
		String dossierStatus) {
		return getService().countByG_UID_DS(groupId, userId, dossierStatus);
	}

	public static int countByGroupAndOriginDossierNo(long groupId,
		String originDossierNo) {
		return getService()
				   .countByGroupAndOriginDossierNo(groupId, originDossierNo);
	}

	public static int countByOriginDossierNo(String originDossierNo) {
		return getService().countByOriginDossierNo(originDossierNo);
	}

	public static int countByUserId(long userId, long groupId) {
		return getService().countByUserId(userId, groupId);
	}

	public static long countDossierByG_C_GAC_SC_DTNO_NOTDS(long groupId,
		long companyId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus) {
		return getService()
				   .countDossierByG_C_GAC_SC_DTNO_NOTDS(groupId, companyId,
			govAgencyCode, serviceCode, dossierTemplateNo, dossierStatus);
	}

	public static long countDossierByGroup(long groupId) {
		return getService().countDossierByGroup(groupId);
	}

	public static long countLucene(
		java.util.LinkedHashMap<String, Object> params,
		com.liferay.portal.kernel.search.SearchContext searchContext)
		throws com.liferay.portal.kernel.search.ParseException,
			com.liferay.portal.kernel.search.SearchException {
		return getService().countLucene(params, searchContext);
	}

	/**
	* Creates a new dossier with the primary key. Does not add the dossier to the database.
	*
	* @param dossierId the primary key for the new dossier
	* @return the new dossier
	*/
	public static org.opencps.dossiermgt.model.Dossier createDossier(
		long dossierId) {
		return getService().createDossier(dossierId);
	}

	public static org.opencps.dossiermgt.model.Dossier createDossier(
		long groupId, String serviceCode, String govAgencyCode,
		String applicantName, String applicantIdType, String applicantIdNo,
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
		return getService()
				   .createDossier(groupId, serviceCode, govAgencyCode,
			applicantName, applicantIdType, applicantIdNo, applicantIdDate,
			address, cityCode, districtCode, wardCode, contactName,
			contactTelNo, contactEmail, isSameAsApplicant, delegateName,
			delegateIdNo, delegateTelNo, delegateEmail, delegateAddress,
			delegateCityCode, delegateDistrictCode, delegateWardCode,
			applicantNote, briefNote, dossierNo, dossierTemplateNo, viaPostal,
			postalServiceCode, postalServiceName, postalAddress,
			postalCityCode, postalDistrictCode, postalWardCode, postalTelNo,
			originality, context);
	}

	/**
	* Deletes the dossier from the database. Also notifies the appropriate model listeners.
	*
	* @param dossier the dossier
	* @return the dossier that was removed
	*/
	public static org.opencps.dossiermgt.model.Dossier deleteDossier(
		org.opencps.dossiermgt.model.Dossier dossier) {
		return getService().deleteDossier(dossier);
	}

	/**
	* Deletes the dossier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierId the primary key of the dossier
	* @return the dossier that was removed
	* @throws PortalException if a dossier with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.Dossier deleteDossier(
		long dossierId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteDossier(dossierId);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link org.opencps.dossiermgt.model.impl.DossierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static org.opencps.dossiermgt.model.Dossier eparPublishDossier(
		long groupId, long dossierId, String referenceUid, int counter,
		String serviceCode, String serviceName, String govAgencyCode,
		String govAgencyName, String applicantName, String applicantIdType,
		String applicantIdNo, java.util.Date applicantIdDate, String address,
		String cityCode, String cityName, String districtCode,
		String districtName, String wardCode, String wardName,
		String contactName, String contactTelNo, String contactEmail,
		String dossierTemplateNo, String password, int viaPostal,
		String postalAddress, String postalCityCode, String postalCityName,
		String postalDistrictCode, String postalDistrictName,
		String postalTelNo, boolean online, boolean notification,
		String applicantNote, int originality, java.util.Date createDate,
		java.util.Date modifiedDate, java.util.Date submitDate,
		java.util.Date receiveDate, java.util.Date dueDate,
		java.util.Date releaseDate, java.util.Date finishDate,
		java.util.Date cancellingDate, java.util.Date correctingDate,
		java.util.Date endorsementDate, java.util.Date extendDate,
		java.util.Date processDate, String dossierNo, String dossierStatus,
		String dossierStatusText, String dossierSubStatus,
		String dossierSubStatusText, long dossierActionId,
		String submissionNote, String lockState, String delegateName,
		String delegateIdNo, String delegateTelNo, String delegateEmail,
		String delegateAddress, String delegateCityCode,
		String delegateCityName, String delegateDistrictCode,
		String delegateDistrictName, String delegateWardCode,
		String delegateWardName, double durationCount, int durationUnit,
		String dossierName, String processNo, String metaData,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .eparPublishDossier(groupId, dossierId, referenceUid,
			counter, serviceCode, serviceName, govAgencyCode, govAgencyName,
			applicantName, applicantIdType, applicantIdNo, applicantIdDate,
			address, cityCode, cityName, districtCode, districtName, wardCode,
			wardName, contactName, contactTelNo, contactEmail,
			dossierTemplateNo, password, viaPostal, postalAddress,
			postalCityCode, postalCityName, postalDistrictCode,
			postalDistrictName, postalTelNo, online, notification,
			applicantNote, originality, createDate, modifiedDate, submitDate,
			receiveDate, dueDate, releaseDate, finishDate, cancellingDate,
			correctingDate, endorsementDate, extendDate, processDate,
			dossierNo, dossierStatus, dossierStatusText, dossierSubStatus,
			dossierSubStatusText, dossierActionId, submissionNote, lockState,
			delegateName, delegateIdNo, delegateTelNo, delegateEmail,
			delegateAddress, delegateCityCode, delegateCityName,
			delegateDistrictCode, delegateDistrictName, delegateWardCode,
			delegateWardName, durationCount, durationUnit, dossierName,
			processNo, metaData, context);
	}

	public static java.util.List<org.opencps.dossiermgt.model.Dossier> fetchByD_OR_D(
		long[] dossierId) {
		return getService().fetchByD_OR_D(dossierId);
	}

	public static org.opencps.dossiermgt.model.Dossier fetchByDO_NO(
		String dossierNo) {
		return getService().fetchByDO_NO(dossierNo);
	}

	public static org.opencps.dossiermgt.model.Dossier fetchByDO_NO_GROUP(
		String dossierNo, long groupId) {
		return getService().fetchByDO_NO_GROUP(dossierNo, groupId);
	}

	public static org.opencps.dossiermgt.model.Dossier fetchByDO_POST_RECEIVED_GROUP(
		String postalCodeReceived, long groupId) {
		return getService()
				   .fetchByDO_POST_RECEIVED_GROUP(postalCodeReceived, groupId);
	}

	public static org.opencps.dossiermgt.model.Dossier fetchByDO_POST_SEND_GROUP(
		String postpostalCodeSend, long groupId) {
		return getService()
				   .fetchByDO_POST_SEND_GROUP(postpostalCodeSend, groupId);
	}

	public static java.util.List<org.opencps.dossiermgt.model.Dossier> fetchByDOC_OR_NO(
		String[] documentNo) {
		return getService().fetchByDOC_OR_NO(documentNo);
	}

	public static org.opencps.dossiermgt.model.Dossier fetchDossier(
		long dossierId) {
		return getService().fetchDossier(dossierId);
	}

	/**
	* Returns the dossier matching the UUID and group.
	*
	* @param uuid the dossier's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	public static org.opencps.dossiermgt.model.Dossier fetchDossierByUuidAndGroupId(
		String uuid, long groupId) {
		return getService().fetchDossierByUuidAndGroupId(uuid, groupId);
	}

	public static org.opencps.dossiermgt.model.Dossier fetchOnePublicService() {
		return getService().fetchOnePublicService();
	}

	public static java.util.List<org.opencps.dossiermgt.model.Dossier> findByDN_AN(
		String dossierNo, String applicantIdNo) {
		return getService().findByDN_AN(dossierNo, applicantIdNo);
	}

	public static java.util.List<org.opencps.dossiermgt.model.Dossier> findByG_GDID(
		long groupId, String groupDossierId) {
		return getService().findByG_GDID(groupId, groupDossierId);
	}

	public static java.util.List<org.opencps.dossiermgt.model.Dossier> findByG_UID_DS(
		long groupId, long userId, String dossierStatus) {
		return getService().findByG_UID_DS(groupId, userId, dossierStatus);
	}

	public static java.util.List<org.opencps.dossiermgt.model.Dossier> findByGID(
		long groupId, int start, int end) {
		return getService().findByGID(groupId, start, end);
	}

	public static java.util.List<org.opencps.dossiermgt.model.ServiceInfo> findByGID_PUB_SID(
		long groupId, boolean public_, long[] serviceInfoArr) {
		return getService().findByGID_PUB_SID(groupId, public_, serviceInfoArr);
	}

	public static java.util.List<org.opencps.dossiermgt.model.Dossier> findByNOT_ST_GT_MD(
		String[] statuses, java.util.Date d, int start, int end) {
		return getService().findByNOT_ST_GT_MD(statuses, d, start, end);
	}

	public static java.util.List<org.opencps.dossiermgt.model.Dossier> findByVIAPOSTAL(
		int viaPostal) {
		return getService().findByVIAPOSTAL(viaPostal);
	}

	public static java.util.List<org.opencps.dossiermgt.model.Dossier> findByVnpostalStatus(
		long groupId, int vnpostalStatus) {
		return getService().findByVnpostalStatus(groupId, vnpostalStatus);
	}

	public static java.util.List<org.opencps.dossiermgt.input.model.DictItemModel> findDictItemByServiceDomain(
		String serviceLevel, long groupId) {
		return getService().findDictItemByServiceDomain(serviceLevel, groupId);
	}

	public static java.util.List<org.opencps.dossiermgt.model.Dossier> findDossierByDay(
		String date) {
		return getService().findDossierByDay(date);
	}

	public static org.opencps.dossiermgt.model.Dossier findDossierByDeclarationCode(
		String code, long groupId) {
		return getService().findDossierByDeclarationCode(code, groupId);
	}

	public static java.util.List<org.opencps.dossiermgt.model.Dossier> findDossierByGroup(
		long groupId) {
		return getService().findDossierByGroup(groupId);
	}

	public static java.util.List<org.opencps.dossiermgt.input.model.DictItemModel> findServiceConfigByServiceLevel(
		String serviceLevel, long groupId) {
		return getService()
				   .findServiceConfigByServiceLevel(serviceLevel, groupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static org.opencps.dossiermgt.model.Dossier getByCpsRef(
		long groupId, String refId) {
		return getService().getByCpsRef(groupId, refId);
	}

	public static org.opencps.dossiermgt.model.Dossier getByDossierNo(
		long groupId, String dossierNo) {
		return getService().getByDossierNo(groupId, dossierNo);
	}

	public static java.util.List<org.opencps.dossiermgt.model.Dossier> getByF_GID_AN_DS(
		long groupId, String applicantIdNo, String dossierStatus) {
		return getService()
				   .getByF_GID_AN_DS(groupId, applicantIdNo, dossierStatus);
	}

	public static java.util.List<org.opencps.dossiermgt.model.Dossier> getByG_AN(
		long groupId, String applicantIdNo) {
		return getService().getByG_AN(groupId, applicantIdNo);
	}

	public static org.opencps.dossiermgt.model.Dossier getByG_AN_SC_GAC_DTNO_ODID(
		long groupId, String applicantIdNo, String serviceCode,
		String govAgencyCode, String dossierTemplateNo, long originDossierId) {
		return getService()
				   .getByG_AN_SC_GAC_DTNO_ODID(groupId, applicantIdNo,
			serviceCode, govAgencyCode, dossierTemplateNo, originDossierId);
	}

	public static java.util.List<org.opencps.dossiermgt.model.Dossier> getByGID_GC_SC_DTN_DS_APP_ORI(
		long groupId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String[] statusArr, String applicantIdType,
		int originality) {
		return getService()
				   .getByGID_GC_SC_DTN_DS_APP_ORI(groupId, govAgencyCode,
			serviceCode, dossierTemplateNo, statusArr, applicantIdType,
			originality);
	}

	public static java.util.List<org.opencps.dossiermgt.model.Dossier> getByGroupAndOriginDossierNo(
		long groupId, String originDossierNo) {
		return getService()
				   .getByGroupAndOriginDossierNo(groupId, originDossierNo);
	}

	public static org.opencps.dossiermgt.model.Dossier getByIdAndGovService(
		long groupId, String serviceCode, String govAgencyCode, long dossierId) {
		return getService()
				   .getByIdAndGovService(groupId, serviceCode, govAgencyCode,
			dossierId);
	}

	public static java.util.List<org.opencps.dossiermgt.model.Dossier> getByNotO_DS_SC_GC(
		long groupId, int originality, String dossierStatus,
		String serviceCode, String govAgencyCode) {
		return getService()
				   .getByNotO_DS_SC_GC(groupId, originality, dossierStatus,
			serviceCode, govAgencyCode);
	}

	public static org.opencps.dossiermgt.model.Dossier getByOrigin(
		long groupId, long originDossierId) {
		return getService().getByOrigin(groupId, originDossierId);
	}

	public static org.opencps.dossiermgt.model.Dossier getByRef(long groupId,
		String refId) {
		return getService().getByRef(groupId, refId);
	}

	public static java.util.List<org.opencps.dossiermgt.model.Dossier> getByU_G_C_DS_SC_GC_O(
		long userId, long groupId, String serviceCode, String govAgencyCode,
		long dossierActionId, int originality) {
		return getService()
				   .getByU_G_C_DS_SC_GC_O(userId, groupId, serviceCode,
			govAgencyCode, dossierActionId, originality);
	}

	public static java.util.List<org.opencps.dossiermgt.model.Dossier> getByU_G_GAC_SC_DTNO_DS_O(
		long userId, long groupId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus, int originality) {
		return getService()
				   .getByU_G_GAC_SC_DTNO_DS_O(userId, groupId, govAgencyCode,
			serviceCode, dossierTemplateNo, dossierStatus, originality);
	}

	/**
	* Returns the dossier with the primary key.
	*
	* @param dossierId the primary key of the dossier
	* @return the dossier
	* @throws PortalException if a dossier with the primary key could not be found
	*/
	public static org.opencps.dossiermgt.model.Dossier getDossier(
		long dossierId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDossier(dossierId);
	}

	public static java.util.List<org.opencps.dossiermgt.model.Dossier> getDossierByG_NOTO_DS(
		int originality, String dossierStatus) {
		return getService().getDossierByG_NOTO_DS(originality, dossierStatus);
	}

	public static java.util.List<org.opencps.dossiermgt.model.Dossier> getDossierByG_NOTO_DS(
		int[] originalityArr, String dossierStatus) {
		return getService().getDossierByG_NOTO_DS(originalityArr, dossierStatus);
	}

	public static com.liferay.portal.kernel.search.Document getDossierById(
		long dossierId, long companyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDossierById(dossierId, companyId);
	}

	/**
	* Returns the dossier matching the UUID and group.
	*
	* @param uuid the dossier's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier
	* @throws PortalException if a matching dossier could not be found
	*/
	public static org.opencps.dossiermgt.model.Dossier getDossierByUuidAndGroupId(
		String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getDossierByUuidAndGroupId(uuid, groupId);
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
	public static java.util.List<org.opencps.dossiermgt.model.Dossier> getDossiers(
		int start, int end) {
		return getService().getDossiers(start, end);
	}

	/**
	* Returns all the dossiers matching the UUID and company.
	*
	* @param uuid the UUID of the dossiers
	* @param companyId the primary key of the company
	* @return the matching dossiers, or an empty list if no matches were found
	*/
	public static java.util.List<org.opencps.dossiermgt.model.Dossier> getDossiersByUuidAndCompanyId(
		String uuid, long companyId) {
		return getService().getDossiersByUuidAndCompanyId(uuid, companyId);
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
	public static java.util.List<org.opencps.dossiermgt.model.Dossier> getDossiersByUuidAndCompanyId(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<org.opencps.dossiermgt.model.Dossier> orderByComparator) {
		return getService()
				   .getDossiersByUuidAndCompanyId(uuid, companyId, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of dossiers.
	*
	* @return the number of dossiers
	*/
	public static int getDossiersCount() {
		return getService().getDossiersCount();
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

	public static org.opencps.dossiermgt.model.Dossier initDossier(
		long groupId, long dossierId, String referenceUid, int counter,
		String serviceCode, String serviceName, String govAgencyCode,
		String govAgencyName, String applicantName, String applicantIdType,
		String applicantIdNo, java.util.Date applicantIdDate, String address,
		String cityCode, String cityName, String districtCode,
		String districtName, String wardCode, String wardName,
		String contactName, String contactTelNo, String contactEmail,
		String dossierTemplateNo, String password, int viaPostal,
		String postalAddress, String postalCityCode, String postalCityName,
		String postalDistrictCode, String postalDistrictName,
		String postalTelNo, boolean online, boolean notification,
		String applicantNote, int originality,
		org.opencps.dossiermgt.model.ServiceInfo service,
		org.opencps.dossiermgt.model.ServiceProcess serviceProcess,
		org.opencps.dossiermgt.model.ProcessOption processOption,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .initDossier(groupId, dossierId, referenceUid, counter,
			serviceCode, serviceName, govAgencyCode, govAgencyName,
			applicantName, applicantIdType, applicantIdNo, applicantIdDate,
			address, cityCode, cityName, districtCode, districtName, wardCode,
			wardName, contactName, contactTelNo, contactEmail,
			dossierTemplateNo, password, viaPostal, postalAddress,
			postalCityCode, postalCityName, postalDistrictCode,
			postalDistrictName, postalTelNo, online, notification,
			applicantNote, originality, service, serviceProcess, processOption,
			context);
	}

	public static org.opencps.dossiermgt.model.Dossier initDossier(
		long groupId, long dossierId, String referenceUid, long counter,
		String serviceCode, String serviceName, String govAgencyCode,
		String govAgencyName, String applicantName, String applicantIdType,
		String applicantIdNo, java.util.Date applicantIdDate, String address,
		String cityCode, String cityName, String districtCode,
		String districtName, String wardCode, String wardName,
		String contactName, String contactTelNo, String contactEmail,
		String dossierTemplateNo, String password, int viaPostal,
		String postalAddress, String postalCityCode, String postalCityName,
		String postalDistrictCode, String postalDistrictName,
		String postalTelNo, boolean online, boolean notification,
		String applicantNote, int originality,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .initDossier(groupId, dossierId, referenceUid, counter,
			serviceCode, serviceName, govAgencyCode, govAgencyName,
			applicantName, applicantIdType, applicantIdNo, applicantIdDate,
			address, cityCode, cityName, districtCode, districtName, wardCode,
			wardName, contactName, contactTelNo, contactEmail,
			dossierTemplateNo, password, viaPostal, postalAddress,
			postalCityCode, postalCityName, postalDistrictCode,
			postalDistrictName, postalTelNo, online, notification,
			applicantNote, originality, context);
	}

	public static org.opencps.dossiermgt.model.Dossier initFullDossier(
		long groupId, long dossierId, String referenceUid, int counter,
		String serviceCode, String serviceName, String govAgencyCode,
		String govAgencyName, String applicantName, String applicantIdType,
		String applicantIdNo, java.util.Date applicantIdDate, String address,
		String contactName, String contactTelNo, String contactEmail,
		String dossierTemplateNo, String password, int viaPostal,
		String postalServiceCode, String postalServiceName,
		String postalAddress, String postalCityCode, String postalCityName,
		String postalDistrictCode, String postalDistrictName,
		String postalWardCode, String postalWardName, String postalTelNo,
		boolean online, boolean notification, String applicantNote,
		int originality, String delegateIdNo, String delegateName,
		String delegateTelNo, String delegateEmail, String delegateAddress,
		String delegateCityCode, String delegateCityName,
		String delegateDistrictCode, String delegateDistrictName,
		String delegateWardCode, String delegateWardName,
		String registerBookCode, String registerBookName, int sampleCount,
		String dossierName, org.opencps.dossiermgt.model.ServiceInfo service,
		org.opencps.dossiermgt.model.ServiceProcess process,
		org.opencps.dossiermgt.model.ProcessOption option,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .initFullDossier(groupId, dossierId, referenceUid, counter,
			serviceCode, serviceName, govAgencyCode, govAgencyName,
			applicantName, applicantIdType, applicantIdNo, applicantIdDate,
			address, contactName, contactTelNo, contactEmail,
			dossierTemplateNo, password, viaPostal, postalServiceCode,
			postalServiceName, postalAddress, postalCityCode, postalCityName,
			postalDistrictCode, postalDistrictName, postalWardCode,
			postalWardName, postalTelNo, online, notification, applicantNote,
			originality, delegateIdNo, delegateName, delegateTelNo,
			delegateEmail, delegateAddress, delegateCityCode, delegateCityName,
			delegateDistrictCode, delegateDistrictName, delegateWardCode,
			delegateWardName, registerBookCode, registerBookName, sampleCount,
			dossierName, service, process, option, context);
	}

	public static org.opencps.dossiermgt.model.Dossier initMultipleDossier(
		long groupId, long dossierId, String referenceUid, int counter,
		String serviceCode, String serviceName, String govAgencyCode,
		String govAgencyName, String applicantName, String applicantIdType,
		String applicantIdNo, java.util.Date applicantIdDate, String address,
		String contactName, String contactTelNo, String contactEmail,
		String dossierTemplateNo, String password, int viaPostal,
		String postalServiceCode, String postalServiceName,
		String postalAddress, String postalCityCode, String postalCityName,
		String postalDistrictCode, String postalDistrictName,
		String postalWardCode, String postalWardName, String postalTelNo,
		boolean online, boolean notification, String applicantNote,
		int originality, String delegateIdNo, String delegateName,
		String delegateTelNo, String delegateEmail, String delegateAddress,
		String delegateCityCode, String delegateCityName,
		String delegateDistrictCode, String delegateDistrictName,
		String delegateWardCode, String delegateWardName,
		String registerBookCode, String registerBookName, int sampleCount,
		String dossierName, int durationCount,
		org.opencps.dossiermgt.model.ServiceInfo service,
		org.opencps.dossiermgt.model.ServiceProcess process,
		org.opencps.dossiermgt.model.ProcessOption option,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .initMultipleDossier(groupId, dossierId, referenceUid,
			counter, serviceCode, serviceName, govAgencyCode, govAgencyName,
			applicantName, applicantIdType, applicantIdNo, applicantIdDate,
			address, contactName, contactTelNo, contactEmail,
			dossierTemplateNo, password, viaPostal, postalServiceCode,
			postalServiceName, postalAddress, postalCityCode, postalCityName,
			postalDistrictCode, postalDistrictName, postalWardCode,
			postalWardName, postalTelNo, online, notification, applicantNote,
			originality, delegateIdNo, delegateName, delegateTelNo,
			delegateEmail, delegateAddress, delegateCityCode, delegateCityName,
			delegateDistrictCode, delegateDistrictName, delegateWardCode,
			delegateWardName, registerBookCode, registerBookName, sampleCount,
			dossierName, durationCount, service, process, option, context);
	}

	public static org.opencps.dossiermgt.model.Dossier initUpdateDossier(
		long groupId, long id, String applicantName, String applicantIdType,
		String applicantIdNo, String applicantIdDate, String address,
		String cityCode, String cityName, String districtCode,
		String districtName, String wardCode, String wardName,
		String contactName, String contactTelNo, String contactEmail,
		String dossierTemplateNo, Integer viaPostal, String postalAddress,
		String postalCityCode, String postalCityName,
		String postalDistrictCode, String postalDistrictName,
		String postalTelNo, String applicantNote, boolean isSameAsApplicant,
		String delegateName, String delegateIdNo, String delegateTelNo,
		String delegateEmail, String delegateAddress, String delegateCityCode,
		String delegateDistrictCode, String delegateWardCode, Long sampleCount,
		Integer vnpostalStatus, String vnpostalProfile, Integer fromViaPostal,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		return getService()
				   .initUpdateDossier(groupId, id, applicantName,
			applicantIdType, applicantIdNo, applicantIdDate, address, cityCode,
			cityName, districtCode, districtName, wardCode, wardName,
			contactName, contactTelNo, contactEmail, dossierTemplateNo,
			viaPostal, postalAddress, postalCityCode, postalCityName,
			postalDistrictCode, postalDistrictName, postalTelNo, applicantNote,
			isSameAsApplicant, delegateName, delegateIdNo, delegateTelNo,
			delegateEmail, delegateAddress, delegateCityCode,
			delegateDistrictCode, delegateWardCode, sampleCount,
			vnpostalStatus, vnpostalProfile, fromViaPostal, serviceContext);
	}

	public static org.opencps.dossiermgt.model.Dossier initUpdateDossier(
		long groupId, long id, String applicantName, String applicantIdType,
		String applicantIdNo, String applicantIdDate, String address,
		String cityCode, String cityName, String districtCode,
		String districtName, String wardCode, String wardName,
		String contactName, String contactTelNo, String contactEmail,
		String dossierTemplateNo, Integer viaPostal, String postalAddress,
		String postalCityCode, String postalCityName,
		String postalDistrictCode, String postalDistrictName,
		String postalTelNo, String applicantNote, boolean isSameAsApplicant,
		String delegateName, String delegateIdNo, String delegateTelNo,
		String delegateEmail, String delegateAddress, String delegateCityCode,
		String delegateDistrictCode, String delegateWardCode, Long sampleCount,
		String dossierName, Integer vnpostalStatus, String vnpostalProfile,
		Integer fromViaPostal,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		return getService()
				   .initUpdateDossier(groupId, id, applicantName,
			applicantIdType, applicantIdNo, applicantIdDate, address, cityCode,
			cityName, districtCode, districtName, wardCode, wardName,
			contactName, contactTelNo, contactEmail, dossierTemplateNo,
			viaPostal, postalAddress, postalCityCode, postalCityName,
			postalDistrictCode, postalDistrictName, postalTelNo, applicantNote,
			isSameAsApplicant, delegateName, delegateIdNo, delegateTelNo,
			delegateEmail, delegateAddress, delegateCityCode,
			delegateDistrictCode, delegateWardCode, sampleCount, dossierName,
			vnpostalStatus, vnpostalProfile, fromViaPostal, serviceContext);
	}

	public static org.opencps.dossiermgt.model.Dossier initUpdateDossier(
		long groupId, long id, String applicantName, String applicantIdType,
		String applicantIdNo, String applicantIdDate, String address,
		String cityCode, String cityName, String districtCode,
		String districtName, String wardCode, String wardName,
		String contactName, String contactTelNo, String contactEmail,
		String dossierTemplateNo, Integer viaPostal, String postalAddress,
		String postalCityCode, String postalCityName,
		String postalDistrictCode, String postalDistrictName,
		String postalTelNo, String applicantNote, boolean isSameAsApplicant,
		String delegateName, String delegateIdNo, String delegateTelNo,
		String delegateEmail, String delegateAddress, String delegateCityCode,
		String delegateDistrictCode, String delegateWardCode, Long sampleCount,
		String dossierName, String briefNote,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		return getService()
				   .initUpdateDossier(groupId, id, applicantName,
			applicantIdType, applicantIdNo, applicantIdDate, address, cityCode,
			cityName, districtCode, districtName, wardCode, wardName,
			contactName, contactTelNo, contactEmail, dossierTemplateNo,
			viaPostal, postalAddress, postalCityCode, postalCityName,
			postalDistrictCode, postalDistrictName, postalTelNo, applicantNote,
			isSameAsApplicant, delegateName, delegateIdNo, delegateTelNo,
			delegateEmail, delegateAddress, delegateCityCode,
			delegateDistrictCode, delegateWardCode, sampleCount, dossierName,
			briefNote, serviceContext);
	}

	public static org.opencps.dossiermgt.model.Dossier initUpdateDossierFull(
		long groupId, long id, String applicantName, String applicantIdType,
		String applicantIdNo, String applicantIdDate, String address,
		String cityCode, String cityName, String districtCode,
		String districtName, String wardCode, String wardName,
		String contactName, String contactTelNo, String contactEmail,
		String dossierTemplateNo, Integer viaPostal, String postalAddress,
		String postalCityCode, String postalCityName,
		String postalDistrictCode, String postalDistrictName,
		String postalTelNo, String applicantNote, boolean isSameAsApplicant,
		String delegateName, String delegateIdNo, String delegateTelNo,
		String delegateEmail, String delegateAddress, String delegateCityCode,
		String delegateDistrictCode, String delegateWardCode, Long sampleCount,
		String dossierName, String briefNote, Integer delegateType,
		String documentNo, java.util.Date documentDate, int systemId,
		Integer vnpostalStatus, String vnpostalProfile, Integer fromViaPostal,
		java.util.Date dueDate, int durationCount,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		return getService()
				   .initUpdateDossierFull(groupId, id, applicantName,
			applicantIdType, applicantIdNo, applicantIdDate, address, cityCode,
			cityName, districtCode, districtName, wardCode, wardName,
			contactName, contactTelNo, contactEmail, dossierTemplateNo,
			viaPostal, postalAddress, postalCityCode, postalCityName,
			postalDistrictCode, postalDistrictName, postalTelNo, applicantNote,
			isSameAsApplicant, delegateName, delegateIdNo, delegateTelNo,
			delegateEmail, delegateAddress, delegateCityCode,
			delegateDistrictCode, delegateWardCode, sampleCount, dossierName,
			briefNote, delegateType, documentNo, documentDate, systemId,
			vnpostalStatus, vnpostalProfile, fromViaPostal, dueDate,
			durationCount, serviceContext);
	}

	public static org.opencps.dossiermgt.model.Dossier initUpdateDossierMeta(
		long groupId, long id, String applicantName, String applicantIdType,
		String applicantIdNo, String applicantIdDate, String address,
		String cityCode, String cityName, String districtCode,
		String districtName, String wardCode, String wardName,
		String contactName, String contactTelNo, String contactEmail,
		String dossierTemplateNo, Integer viaPostal, String postalAddress,
		String postalCityCode, String postalCityName,
		String postalDistrictCode, String postalDistrictName,
		String postalTelNo, String applicantNote, boolean isSameAsApplicant,
		String delegateName, String delegateIdNo, String delegateTelNo,
		String delegateEmail, String delegateAddress, String delegateCityCode,
		String delegateDistrictCode, String delegateWardCode, Long sampleCount,
		String dossierName, String briefNote, Integer delegateType,
		String documentNo, java.util.Date documentDate, int systemId,
		Integer vnpostalStatus, String vnpostalProfile, Integer fromViaPostal,
		String metaData, java.util.Date dueDate, int durationCount,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		return getService()
				   .initUpdateDossierMeta(groupId, id, applicantName,
			applicantIdType, applicantIdNo, applicantIdDate, address, cityCode,
			cityName, districtCode, districtName, wardCode, wardName,
			contactName, contactTelNo, contactEmail, dossierTemplateNo,
			viaPostal, postalAddress, postalCityCode, postalCityName,
			postalDistrictCode, postalDistrictName, postalTelNo, applicantNote,
			isSameAsApplicant, delegateName, delegateIdNo, delegateTelNo,
			delegateEmail, delegateAddress, delegateCityCode,
			delegateDistrictCode, delegateWardCode, sampleCount, dossierName,
			briefNote, delegateType, documentNo, documentDate, systemId,
			vnpostalStatus, vnpostalProfile, fromViaPostal, metaData, dueDate,
			durationCount, serviceContext);
	}

	public static org.opencps.dossiermgt.model.Dossier postDossier(
		long groupId, long dossierId, String referenceUid, int counter,
		String serviceCode, String serviceName, String govAgencyCode,
		String govAgencyName, String applicantName, String applicantIdType,
		String applicantIdNo, java.util.Date applicantIdDate, String address,
		String cityCode, String cityName, String districtCode,
		String districtName, String wardCode, String wardName,
		String contactName, String contactTelNo, String contactEmail,
		String dossierTemplateNo, String password, int viaPostal,
		String postalAddress, String postalCityCode, String postalCityName,
		String postalDistrictCode, String postalDistrictName,
		String postalTelNo, boolean online, boolean notification,
		String applicantNote, int originality,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .postDossier(groupId, dossierId, referenceUid, counter,
			serviceCode, serviceName, govAgencyCode, govAgencyName,
			applicantName, applicantIdType, applicantIdNo, applicantIdDate,
			address, cityCode, cityName, districtCode, districtName, wardCode,
			wardName, contactName, contactTelNo, contactEmail,
			dossierTemplateNo, password, viaPostal, postalAddress,
			postalCityCode, postalCityName, postalDistrictCode,
			postalDistrictName, postalTelNo, online, notification,
			applicantNote, originality, context);
	}

	public static org.opencps.dossiermgt.model.Dossier publishDossier(
		long groupId, long dossierId, String referenceUid, int counter,
		String serviceCode, String serviceName, String govAgencyCode,
		String govAgencyName, String applicantName, String applicantIdType,
		String applicantIdNo, java.util.Date applicantIdDate, String address,
		String cityCode, String cityName, String districtCode,
		String districtName, String wardCode, String wardName,
		String contactName, String contactTelNo, String contactEmail,
		String dossierTemplateNo, String password, int viaPostal,
		String postalAddress, String postalCityCode, String postalCityName,
		String postalDistrictCode, String postalDistrictName,
		String postalTelNo, boolean online, boolean notification,
		String applicantNote, int originality, java.util.Date createDate,
		java.util.Date modifiedDate, java.util.Date submitDate,
		java.util.Date receiveDate, java.util.Date dueDate,
		java.util.Date releaseDate, java.util.Date finishDate,
		java.util.Date cancellingDate, java.util.Date correctingDate,
		java.util.Date endorsementDate, java.util.Date extendDate,
		java.util.Date processDate, String dossierNo, String dossierStatus,
		String dossierStatusText, String dossierSubStatus,
		String dossierSubStatusText, long dossierActionId,
		String submissionNote, String lockState, String delegateName,
		String delegateIdNo, String delegateTelNo, String delegateEmail,
		String delegateAddress, String delegateCityCode,
		String delegateCityName, String delegateDistrictCode,
		String delegateDistrictName, String delegateWardCode,
		String delegateWardName, double durationCount, int durationUnit,
		String dossierName, String processNo, String metaData,
		Integer vnpostalStatus, String vnpostalProfile, Integer fromViaPostal,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .publishDossier(groupId, dossierId, referenceUid, counter,
			serviceCode, serviceName, govAgencyCode, govAgencyName,
			applicantName, applicantIdType, applicantIdNo, applicantIdDate,
			address, cityCode, cityName, districtCode, districtName, wardCode,
			wardName, contactName, contactTelNo, contactEmail,
			dossierTemplateNo, password, viaPostal, postalAddress,
			postalCityCode, postalCityName, postalDistrictCode,
			postalDistrictName, postalTelNo, online, notification,
			applicantNote, originality, createDate, modifiedDate, submitDate,
			receiveDate, dueDate, releaseDate, finishDate, cancellingDate,
			correctingDate, endorsementDate, extendDate, processDate,
			dossierNo, dossierStatus, dossierStatusText, dossierSubStatus,
			dossierSubStatusText, dossierActionId, submissionNote, lockState,
			delegateName, delegateIdNo, delegateTelNo, delegateEmail,
			delegateAddress, delegateCityCode, delegateCityName,
			delegateDistrictCode, delegateDistrictName, delegateWardCode,
			delegateWardName, durationCount, durationUnit, dossierName,
			processNo, metaData, vnpostalStatus, vnpostalProfile,
			fromViaPostal, context);
	}

	public static org.opencps.dossiermgt.model.Dossier publishImportDossier(
		long groupId, long dossierId, String referenceUid, int counter,
		String serviceCode, String serviceName, String govAgencyCode,
		String govAgencyName, String applicantName, String applicantIdType,
		String applicantIdNo, java.util.Date applicantIdDate, String address,
		String contactName, String contactTelNo, String contactEmail,
		Boolean online, int originality, String dossierNo,
		String dossierStatus, String dossierStatusText, long dossierActionId,
		Double durationCount, Integer durationUnit, Integer sampleCount,
		java.util.Date createDate, java.util.Date modifiedDate,
		java.util.Date submitDate, java.util.Date receiveDate,
		java.util.Date dueDate, java.util.Date releaseDate,
		java.util.Date finishDate, String dossierTemplateNo,
		String dossierTemplateName, Integer vnpostalStatus,
		String vnpostalProfile, Integer fromViaPostal,
		com.liferay.portal.kernel.service.ServiceContext serviceContext) {
		return getService()
				   .publishImportDossier(groupId, dossierId, referenceUid,
			counter, serviceCode, serviceName, govAgencyCode, govAgencyName,
			applicantName, applicantIdType, applicantIdNo, applicantIdDate,
			address, contactName, contactTelNo, contactEmail, online,
			originality, dossierNo, dossierStatus, dossierStatusText,
			dossierActionId, durationCount, durationUnit, sampleCount,
			createDate, modifiedDate, submitDate, receiveDate, dueDate,
			releaseDate, finishDate, dossierTemplateNo, dossierTemplateName,
			vnpostalStatus, vnpostalProfile, fromViaPostal, serviceContext);
	}

	public static org.opencps.dossiermgt.model.Dossier removeDossier(
		long groupId, long dossierId, String refId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().removeDossier(groupId, dossierId, refId);
	}

	public static void removeDossierByF_OG_DS(int originality,
		String dossierStatus) {
		getService().removeDossierByF_OG_DS(originality, dossierStatus);
	}

	public static void removeDossierByG_NOTO_DS(int[] originalityArr,
		String dossierStatus) {
		getService().removeDossierByG_NOTO_DS(originalityArr, dossierStatus);
	}

	public static org.opencps.dossiermgt.model.Dossier reset(long groupId,
		long id, String refId,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().reset(groupId, id, refId, context);
	}

	public static org.opencps.dossiermgt.model.Dossier rollback(
		org.opencps.dossiermgt.model.Dossier dossier,
		org.opencps.dossiermgt.model.DossierAction dossierAction) {
		return getService().rollback(dossier, dossierAction);
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

	public static org.opencps.dossiermgt.model.Dossier submitting(
		long groupId, long id, String refId,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().submitting(groupId, id, refId, context);
	}

	public static org.opencps.dossiermgt.model.Dossier syncDossier(
		org.opencps.dossiermgt.model.Dossier dossier)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().syncDossier(dossier);
	}

	public static org.opencps.dossiermgt.model.Dossier updateApplicantInfo(
		long dossierId, java.util.Date applicantIdDate, String applicantIdNo,
		String applicantIdType, String applicantName, String address,
		String cityCode, String cityName, String districtCode,
		String districtName, String wardCode, String wardName,
		String contactEmail, String contactTelNo)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getService()
				   .updateApplicantInfo(dossierId, applicantIdDate,
			applicantIdNo, applicantIdType, applicantName, address, cityCode,
			cityName, districtCode, districtName, wardCode, wardName,
			contactEmail, contactTelNo);
	}

	public static org.opencps.dossiermgt.model.Dossier updateCancellingDate(
		long groupId, long id, String refId, java.util.Date date,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCancellingDate(groupId, id, refId, date, context);
	}

	public static org.opencps.dossiermgt.model.Dossier updateCorrectingDate(
		long groupId, long id, String refId, java.util.Date date,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCorrectingDate(groupId, id, refId, date, context);
	}

	/**
	* Updates the dossier in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dossier the dossier
	* @return the dossier that was updated
	*/
	public static org.opencps.dossiermgt.model.Dossier updateDossier(
		org.opencps.dossiermgt.model.Dossier dossier) {
		return getService().updateDossier(dossier);
	}

	public static org.opencps.dossiermgt.model.Dossier updateDossier(
		long dossierId, com.liferay.portal.kernel.json.JSONObject obj)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getService().updateDossier(dossierId, obj);
	}

	public static org.opencps.dossiermgt.model.Dossier updateDossier(
		long groupId, long dossierId, String referenceUid, int counter,
		String serviceCode, String serviceName, String govAgencyCode,
		String govAgencyName, String applicantName, String applicantIdType,
		String applicantIdNo, java.util.Date applicantIdDate, String address,
		String cityCode, String cityName, String districtCode,
		String districtName, String wardCode, String wardName,
		String contactName, String contactTelNo, String contactEmail,
		String dossierTemplateNo, String dossierNote, String submissionNote,
		String applicantNote, String briefNote, String dossierNo,
		boolean submitting, java.util.Date correctingDate,
		String dossierStatus, String dossierStatusText,
		String dossierSubStatus, String dossierSubStatusText, long folderId,
		long dossierActionId, int viaPostal, String postalAddress,
		String postalCityCode, String postalCityName,
		String postalDistrictCode, String postalDistrictName,
		String postalTelNo, String password, boolean notification,
		boolean online, String serverNo, java.util.Date submitDate,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateDossier(groupId, dossierId, referenceUid, counter,
			serviceCode, serviceName, govAgencyCode, govAgencyName,
			applicantName, applicantIdType, applicantIdNo, applicantIdDate,
			address, cityCode, cityName, districtCode, districtName, wardCode,
			wardName, contactName, contactTelNo, contactEmail,
			dossierTemplateNo, dossierNote, submissionNote, applicantNote,
			briefNote, dossierNo, submitting, correctingDate, dossierStatus,
			dossierStatusText, dossierSubStatus, dossierSubStatusText,
			folderId, dossierActionId, viaPostal, postalAddress,
			postalCityCode, postalCityName, postalDistrictCode,
			postalDistrictName, postalTelNo, password, notification, online,
			serverNo, submitDate, context);
	}

	public static org.opencps.dossiermgt.model.Dossier updateDossier(
		long groupId, long dossierId, String referenceUid, int counter,
		String serviceCode, String serviceName, String govAgencyCode,
		String govAgencyName, String applicantName, String applicantIdType,
		String applicantIdNo, java.util.Date applicantIdDate, String address,
		String cityCode, String cityName, String districtCode,
		String districtName, String wardCode, String wardName,
		String contactName, String contactTelNo, String contactEmail,
		String dossierTemplateNo, String dossierNote, String submissionNote,
		String applicantNote, String briefNote, String dossierNo,
		boolean submitting, java.util.Date correctingDate,
		String dossierStatus, String dossierStatusText,
		String dossierSubStatus, String dossierSubStatusText, long folderId,
		long dossierActionId, int viaPostal, String postalAddress,
		String postalCityCode, String postalCityName,
		String postalDistrictCode, String postalDistrictName,
		String postalTelNo, String password, boolean notification,
		boolean online, String serverNo,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateDossier(groupId, dossierId, referenceUid, counter,
			serviceCode, serviceName, govAgencyCode, govAgencyName,
			applicantName, applicantIdType, applicantIdNo, applicantIdDate,
			address, cityCode, cityName, districtCode, districtName, wardCode,
			wardName, contactName, contactTelNo, contactEmail,
			dossierTemplateNo, dossierNote, submissionNote, applicantNote,
			briefNote, dossierNo, submitting, correctingDate, dossierStatus,
			dossierStatusText, dossierSubStatus, dossierSubStatusText,
			folderId, dossierActionId, viaPostal, postalAddress,
			postalCityCode, postalCityName, postalDistrictCode,
			postalDistrictName, postalTelNo, password, notification, online,
			serverNo, context);
	}

	public static org.opencps.dossiermgt.model.Dossier updateDossierAction(
		long groupId, long id, String refId, long dossierActionId,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateDossierAction(groupId, id, refId, dossierActionId,
			context);
	}

	public static org.opencps.dossiermgt.model.Dossier updateDossierBriefNote(
		long dossierId, String dossierBriefNote)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateDossierBriefNote(dossierId, dossierBriefNote);
	}

	public static org.opencps.dossiermgt.model.Dossier updateDossierOneGate(
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
		return getService()
				   .updateDossierOneGate(dossierId, applicantName,
			applicantIdType, applicantIdNo, applicantIdDate, address, cityCode,
			districtCode, wardCode, contactName, contactTelNo, contactEmail,
			isSameAsApplicant, delegateName, delegateIdNo, delegateTelNo,
			delegateEmail, delegateAddress, delegateCityCode,
			delegateDistrictCode, delegateWardCode, applicantNote, briefNote,
			dossierNo, viaPostal, postalServiceCode, postalServiceName,
			postalAddress, postalCityCode, postalDistrictCode, postalWardCode,
			postalTelNo, dossierActionId, paymentFee, paymentFeeNote, context);
	}

	public static org.opencps.dossiermgt.model.Dossier updateDossierSpecial(
		long dossierId, com.liferay.portal.kernel.json.JSONObject obj)
		throws org.opencps.dossiermgt.exception.NoSuchDossierException {
		return getService().updateDossierSpecial(dossierId, obj);
	}

	public static org.opencps.dossiermgt.model.Dossier updateDueDate(
		long groupId, long id, String refId, java.util.Date date,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateDueDate(groupId, id, refId, date, context);
	}

	public static org.opencps.dossiermgt.model.Dossier updateEndosementDate(
		long groupId, long id, String refId, java.util.Date date,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateEndosementDate(groupId, id, refId, date, context);
	}

	public static org.opencps.dossiermgt.model.Dossier updateFinishDate(
		long groupId, long id, String refId, java.util.Date date,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateFinishDate(groupId, id, refId, date, context);
	}

	public static org.opencps.dossiermgt.model.Dossier updateGroupDossier(
		org.opencps.dossiermgt.model.Dossier dossier, String groupDossierId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateGroupDossier(dossier, groupDossierId);
	}

	public static org.opencps.dossiermgt.model.Dossier updateProcessDate(
		long groupId, long id, String refId, java.util.Date date,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateProcessDate(groupId, id, refId, date, context);
	}

	public static org.opencps.dossiermgt.model.Dossier updateReceivingDate(
		long groupId, long id, String refId, java.util.Date date,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateReceivingDate(groupId, id, refId, date, context);
	}

	public static org.opencps.dossiermgt.model.Dossier updateReleaseDate(
		long groupId, long id, String refId, java.util.Date date,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateReleaseDate(groupId, id, refId, date, context);
	}

	public static org.opencps.dossiermgt.model.Dossier updateStatus(
		org.opencps.dossiermgt.model.Dossier dossier, String status,
		String statusText, String subStatus, String subStatusText,
		String lockState, String stepInstruction,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateStatus(dossier, status, statusText, subStatus,
			subStatusText, lockState, stepInstruction, context);
	}

	public static org.opencps.dossiermgt.model.Dossier updateStatus(
		long groupId, long id, String refId, String status, String statusText,
		String subStatus, String subStatusText, String lockState,
		String stepInstruction,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateStatus(groupId, id, refId, status, statusText,
			subStatus, subStatusText, lockState, stepInstruction, context);
	}

	public static org.opencps.dossiermgt.model.Dossier updateSubmittingDate(
		long groupId, long id, String refId, java.util.Date date,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateSubmittingDate(groupId, id, refId, date, context);
	}

	public static org.opencps.dossiermgt.model.Dossier updateViaPostal(
		long groupId, long id, String refId, int viaPostal,
		com.liferay.portal.kernel.service.ServiceContext context)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateViaPostal(groupId, id, refId, viaPostal, context);
	}

	public static DossierLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DossierLocalService, DossierLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DossierLocalService.class);

		ServiceTracker<DossierLocalService, DossierLocalService> serviceTracker = new ServiceTracker<DossierLocalService, DossierLocalService>(bundle.getBundleContext(),
				DossierLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}