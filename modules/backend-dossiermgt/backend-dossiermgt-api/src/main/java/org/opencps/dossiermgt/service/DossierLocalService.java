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

import com.liferay.exportimport.kernel.lar.PortletDataContext;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.opencps.dossiermgt.exception.NoSuchDossierException;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;

import java.io.Serializable;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Provides the local service interface for Dossier. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author huymq
 * @see DossierLocalServiceUtil
 * @see org.opencps.dossiermgt.service.base.DossierLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.impl.DossierLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface DossierLocalService extends BaseLocalService,
	PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DossierLocalServiceUtil} to access the dossier local service. Add custom service methods to {@link org.opencps.dossiermgt.service.impl.DossierLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the dossier to the database. Also notifies the appropriate model listeners.
	*
	* @param dossier the dossier
	* @return the dossier that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Dossier addDossier(Dossier dossier);

	@Indexable(type = IndexableType.REINDEX)
	public Dossier assignToProcess(long dossierId, String dossierNote,
		String submissionNote, String briefNote, String dossierNo,
		long folderId, long dossierActionId, String serverNo,
		ServiceContext context);

	@Indexable(type = IndexableType.REINDEX)
	public Dossier cloneDossier(Dossier srcDossier) throws PortalException;

	public int countByUserId(long userId, long groupId);

	public long countDossierByG_C_GAC_SC_DTNO_NOTDS(long groupId,
		long companyId, String govAgencyCode, String serviceCode,
		String dossierTemplateNo, String dossierStatus);

	public long countDossierByGroup(long groupId);

	public long countLucene(LinkedHashMap<String, Object> params,
		SearchContext searchContext) throws ParseException, SearchException;

	/**
	* Creates a new dossier with the primary key. Does not add the dossier to the database.
	*
	* @param dossierId the primary key for the new dossier
	* @return the new dossier
	*/
	@Transactional(enabled = false)
	public Dossier createDossier(long dossierId);

	@Indexable(type = IndexableType.REINDEX)
	public Dossier createDossier(long groupId, String serviceCode,
		String govAgencyCode, String applicantName, String applicantIdType,
		String applicantIdNo, Date applicantIdDate, String address,
		String cityCode, String districtCode, String wardCode,
		String contactName, String contactTelNo, String contactEmail,
		boolean isSameAsApplicant, String delegateName, String delegateIdNo,
		String delegateTelNo, String delegateEmail, String delegateAddress,
		String delegateCityCode, String delegateDistrictCode,
		String delegateWardCode, String applicantNote, String briefNote,
		String dossierNo, String dossierTemplateNo, int viaPostal,
		String postalServiceCode, String postalServiceName,
		String postalAddress, String postalCityCode, String postalDistrictCode,
		String postalWardCode, String postalTelNo, int originality,
		ServiceContext context) throws PortalException;

	/**
	* Deletes the dossier from the database. Also notifies the appropriate model listeners.
	*
	* @param dossier the dossier
	* @return the dossier that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public Dossier deleteDossier(Dossier dossier);

	/**
	* Deletes the dossier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param dossierId the primary key of the dossier
	* @return the dossier that was removed
	* @throws PortalException if a dossier with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public Dossier deleteDossier(long dossierId) throws PortalException;

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	public DynamicQuery dynamicQuery();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Dossier fetchDossier(long dossierId);

	/**
	* Returns the dossier matching the UUID and group.
	*
	* @param uuid the dossier's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier, or <code>null</code> if a matching dossier could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Dossier fetchDossierByUuidAndGroupId(String uuid, long groupId);

	public List<Dossier> findByDN_AN(String dossierNo, String applicantIdNo);

	public List<Dossier> findByVIAPOSTAL(int viaPostal);

	public List<Dossier> findDossierByGroup(long groupId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Dossier getByDossierNo(long groupId, String dossierNo);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Dossier getByIdAndGovService(long groupId, String serviceCode,
		String govAgencyCode, long dossierId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Dossier> getByNotO_DS_SC_GC(long groupId, int originality,
		String dossierStatus, String serviceCode, String govAgencyCode);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Dossier getByOrigin(long groupId, long originDossierId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Dossier getByRef(long groupId, String refId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Dossier> getByU_G_C_DS_SC_GC_O(long userId, long groupId,
		String serviceCode, String govAgencyCode, long dossierActionId,
		int originality);

	/**
	* Returns the dossier with the primary key.
	*
	* @param dossierId the primary key of the dossier
	* @return the dossier
	* @throws PortalException if a dossier with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Dossier getDossier(long dossierId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Dossier> getDossierByG_NOTO_DS(int originality,
		String dossierStatus);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Document getDossierById(long dossierId, long companyId)
		throws PortalException;

	/**
	* Returns the dossier matching the UUID and group.
	*
	* @param uuid the dossier's UUID
	* @param groupId the primary key of the group
	* @return the matching dossier
	* @throws PortalException if a matching dossier could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Dossier getDossierByUuidAndGroupId(String uuid, long groupId)
		throws PortalException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Dossier> getDossiers(int start, int end);

	/**
	* Returns all the dossiers matching the UUID and company.
	*
	* @param uuid the UUID of the dossiers
	* @param companyId the primary key of the company
	* @return the matching dossiers, or an empty list if no matches were found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Dossier> getDossiersByUuidAndCompanyId(String uuid,
		long companyId);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Dossier> getDossiersByUuidAndCompanyId(String uuid,
		long companyId, int start, int end,
		OrderByComparator<Dossier> orderByComparator);

	/**
	* Returns the number of dossiers.
	*
	* @return the number of dossiers
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getDossiersCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public Dossier initDossier(long groupId, long dossierId,
		String referenceUid, int counter, String serviceCode,
		String serviceName, String govAgencyCode, String govAgencyName,
		String applicantName, String applicantIdType, String applicantIdNo,
		Date applicantIdDate, String address, String cityCode, String cityName,
		String districtCode, String districtName, String wardCode,
		String wardName, String contactName, String contactTelNo,
		String contactEmail, String dossierTemplateNo, String password,
		int viaPostal, String postalAddress, String postalCityCode,
		String postalCityName, String postalTelNo, boolean online,
		boolean notification, String applicantNote, int originality,
		ServiceContext context) throws PortalException;

	public Dossier initUpdateDossier(long groupId, long id,
		String applicantName, String applicantIdType, String applicantIdNo,
		String applicantIdDate, String address, String cityCode,
		String cityName, String districtCode, String districtName,
		String wardCode, String wardName, String contactName,
		String contactTelNo, String contactEmail, String dossierTemplateNo,
		int viaPostal, String postalAddress, String postalCityCode,
		String postalCityName, String postalTelNo, String applicantNote,
		boolean isSameAsApplicant, String delegateName, String delegateIdNo,
		String delegateTelNo, String delegateEmail, String delegateAddress,
		String delegateCityCode, String delegateDistrictCode,
		String delegateWardCode, Long sampleCount, ServiceContext serviceContext);

	@Indexable(type = IndexableType.REINDEX)
	public Dossier postDossier(long groupId, long dossierId,
		String referenceUid, int counter, String serviceCode,
		String serviceName, String govAgencyCode, String govAgencyName,
		String applicantName, String applicantIdType, String applicantIdNo,
		Date applicantIdDate, String address, String cityCode, String cityName,
		String districtCode, String districtName, String wardCode,
		String wardName, String contactName, String contactTelNo,
		String contactEmail, String dossierTemplateNo, String password,
		int viaPostal, String postalAddress, String postalCityCode,
		String postalCityName, String postalTelNo, boolean online,
		boolean notification, String applicantNote, int originality,
		ServiceContext context) throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public Dossier publishDossier(long groupId, long dossierId,
		String referenceUid, int counter, String serviceCode,
		String serviceName, String govAgencyCode, String govAgencyName,
		String applicantName, String applicantIdType, String applicantIdNo,
		Date applicantIdDate, String address, String cityCode, String cityName,
		String districtCode, String districtName, String wardCode,
		String wardName, String contactName, String contactTelNo,
		String contactEmail, String dossierTemplateNo, String password,
		int viaPostal, String postalAddress, String postalCityCode,
		String postalCityName, String postalTelNo, boolean online,
		boolean notification, String applicantNote, int originality,
		Date createDate, Date modifiedDate, Date submitDate, Date receiveDate,
		Date dueDate, Date releaseDate, Date finishDate, Date cancellingDate,
		Date correctingDate, Date endorsementDate, Date extendDate,
		Date processDate, ServiceContext context) throws PortalException;

	@Indexable(type = IndexableType.DELETE)
	public Dossier removeDossier(long groupId, long dossierId, String refId)
		throws PortalException;

	public void removeDossierByG_NOTO_DS(int originality, String dossierStatus);

	@Indexable(type = IndexableType.REINDEX)
	public Dossier reset(long groupId, long id, String refId,
		ServiceContext context) throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public Dossier rollback(Dossier dossier, DossierAction dossierAction);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Hits searchLucene(LinkedHashMap<String, Object> params,
		Sort[] sorts, int start, int end, SearchContext searchContext)
		throws ParseException, SearchException;

	@Indexable(type = IndexableType.REINDEX)
	public Dossier submitting(long groupId, long id, String refId,
		ServiceContext context) throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public Dossier syncDossier(Dossier dossier) throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateApplicantInfo(long dossierId, Date applicantIdDate,
		String applicantIdNo, String applicantIdType, String applicantName,
		String address, String cityCode, String cityName, String districtCode,
		String districtName, String wardCode, String wardName,
		String contactEmail, String contactTelNo) throws NoSuchDossierException;

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateCancellingDate(long groupId, long id, String refId,
		Date date, ServiceContext context) throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateCorrectingDate(long groupId, long id, String refId,
		Date date, ServiceContext context) throws PortalException;

	/**
	* Updates the dossier in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param dossier the dossier
	* @return the dossier that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateDossier(Dossier dossier);

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateDossier(long dossierId, JSONObject obj)
		throws NoSuchDossierException;

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateDossier(long groupId, long dossierId,
		String referenceUid, int counter, String serviceCode,
		String serviceName, String govAgencyCode, String govAgencyName,
		String applicantName, String applicantIdType, String applicantIdNo,
		Date applicantIdDate, String address, String cityCode, String cityName,
		String districtCode, String districtName, String wardCode,
		String wardName, String contactName, String contactTelNo,
		String contactEmail, String dossierTemplateNo, String dossierNote,
		String submissionNote, String applicantNote, String briefNote,
		String dossierNo, boolean submitting, Date correctingDate,
		String dossierStatus, String dossierStatusText,
		String dossierSubStatus, String dossierSubStatusText, long folderId,
		long dossierActionId, int viaPostal, String postalAddress,
		String postalCityCode, String postalCityName, String postalTelNo,
		String password, boolean notification, boolean online, String serverNo,
		Date submitDate, ServiceContext context) throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateDossier(long groupId, long dossierId,
		String referenceUid, int counter, String serviceCode,
		String serviceName, String govAgencyCode, String govAgencyName,
		String applicantName, String applicantIdType, String applicantIdNo,
		Date applicantIdDate, String address, String cityCode, String cityName,
		String districtCode, String districtName, String wardCode,
		String wardName, String contactName, String contactTelNo,
		String contactEmail, String dossierTemplateNo, String dossierNote,
		String submissionNote, String applicantNote, String briefNote,
		String dossierNo, boolean submitting, Date correctingDate,
		String dossierStatus, String dossierStatusText,
		String dossierSubStatus, String dossierSubStatusText, long folderId,
		long dossierActionId, int viaPostal, String postalAddress,
		String postalCityCode, String postalCityName, String postalTelNo,
		String password, boolean notification, boolean online, String serverNo,
		ServiceContext context) throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateDossierAction(long groupId, long id, String refId,
		long dossierActionId, ServiceContext context) throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateDossierBriefNote(long dossierId,
		String dossierBriefNote) throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateDossierOneGate(long dossierId, String applicantName,
		String applicantIdType, String applicantIdNo, Date applicantIdDate,
		String address, String cityCode, String districtCode, String wardCode,
		String contactName, String contactTelNo, String contactEmail,
		boolean isSameAsApplicant, String delegateName, String delegateIdNo,
		String delegateTelNo, String delegateEmail, String delegateAddress,
		String delegateCityCode, String delegateDistrictCode,
		String delegateWardCode, String applicantNote, String briefNote,
		String dossierNo, int viaPostal, String postalServiceCode,
		String postalServiceName, String postalAddress, String postalCityCode,
		String postalDistrictCode, String postalWardCode, String postalTelNo,
		long dossierActionId, String paymentFee, String paymentFeeNote,
		ServiceContext context) throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateDueDate(long groupId, long id, String refId,
		Date date, ServiceContext context) throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateEndosementDate(long groupId, long id, String refId,
		Date date, ServiceContext context) throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateFinishDate(long groupId, long id, String refId,
		Date date, ServiceContext context) throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateProcessDate(long groupId, long id, String refId,
		Date date, ServiceContext context) throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateReceivingDate(long groupId, long id, String refId,
		Date date, ServiceContext context) throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateReleaseDate(long groupId, long id, String refId,
		Date date, ServiceContext context) throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateStatus(long groupId, long id, String refId,
		String status, String statusText, String subStatus,
		String subStatusText, String lockState, String stepInstruction,
		ServiceContext context) throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateSubmittingDate(long groupId, long id, String refId,
		Date date, ServiceContext context) throws PortalException;

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateViaPostal(long groupId, long id, String refId,
		int viaPostal, ServiceContext context) throws PortalException;
}