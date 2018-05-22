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

package org.opencps.dossiermgt.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.constants.DossierStatusConstants;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.model.DossierTemplate;
import org.opencps.dossiermgt.model.PaymentFile;
import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.service.ProcessOptionLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.base.DossierLocalServiceBaseImpl;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.IndexSearcherHelperUtil;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.TermQuery;
import com.liferay.portal.kernel.search.TermQueryFactoryUtil;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.search.generic.MultiMatchQuery;
import com.liferay.portal.kernel.search.generic.StringQuery;
import com.liferay.portal.kernel.search.generic.TermRangeQueryImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the dossier local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.DossierLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see DossierLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.DossierLocalServiceUtil
 */
@ProviderType
public class DossierLocalServiceImpl extends DossierLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.dossiermgt.service.DossierLocalServiceUtil} to access the
	 * dossier local service.
	 */
	protected Log _log = LogFactoryUtil.getLog(DossierLocalServiceImpl.class);
	@Indexable(type = IndexableType.REINDEX)
	public Dossier syncDossier(Dossier dossier) throws PortalException {

		dossierPersistence.update(dossier);

		return dossier;
	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier initDossier(long groupId, long dossierId, String referenceUid, int counter, String serviceCode,
			String serviceName, String govAgencyCode, String govAgencyName, String applicantName,
			String applicantIdType, String applicantIdNo, Date applicantIdDate, String address, String cityCode,
			String cityName, String districtCode, String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String contactEmail, String dossierTemplateNo, String password,
			int viaPostal, String postalAddress, String postalCityCode, String postalCityName, String postalTelNo,
			boolean online, boolean notification, String applicantNote, ServiceContext context) throws PortalException {

		Date now = new Date();

		long userId = context.getUserId();

		User auditUser = userPersistence.fetchByPrimaryKey(userId);

		validateInit(groupId, dossierId, referenceUid, serviceCode, govAgencyCode, address, cityCode, districtCode,
				wardCode, contactName, contactTelNo, contactEmail, dossierTemplateNo);

		Dossier dossier = null;

		if (dossierId == 0) {
			String dossierTemplateName = getDossierTemplateName(groupId, dossierTemplateNo);

			dossierId = counterLocalService.increment(Dossier.class.getName());

			String dossierNote = getDossierNote(serviceCode, govAgencyCode, dossierTemplateNo, groupId);

			dossier = dossierPersistence.create(dossierId);

			dossier.setCreateDate(now);
			dossier.setModifiedDate(now);
			dossier.setCompanyId(context.getCompanyId());
			dossier.setGroupId(groupId);
			dossier.setUserId(userId);
			dossier.setUserName(auditUser.getFullName());

			// Add extent fields
			dossier.setReferenceUid(referenceUid);
			dossier.setCounter(counter);
			dossier.setServiceCode(serviceCode);
			dossier.setServiceName(serviceName);
			dossier.setGovAgencyCode(govAgencyCode);
			dossier.setGovAgencyName(govAgencyName);
			dossier.setDossierTemplateNo(dossierTemplateNo);
			dossier.setDossierTemplateName(dossierTemplateName);

			dossier.setApplicantName(applicantName);
			dossier.setApplicantIdType(applicantIdType);
			dossier.setApplicantIdNo(applicantIdNo);
			dossier.setApplicantIdDate(applicantIdDate);
			dossier.setPassword(password);
			dossier.setOnline(online);
			dossier.setDossierNote(dossierNote);

			dossier.setAddress(address);
			dossier.setCityCode(cityCode);
			dossier.setCityName(cityName);
			dossier.setDistrictCode(districtCode);
			dossier.setDistrictName(districtName);
			dossier.setWardCode(wardCode);
			dossier.setWardName(wardName);
			dossier.setContactName(contactName);
			dossier.setContactEmail(contactEmail);
			dossier.setContactTelNo(contactTelNo);

			dossier.setViaPostal(viaPostal);
			dossier.setPostalAddress(postalAddress);
			dossier.setPostalCityCode(postalCityCode);
			dossier.setPostalCityName(postalCityName);
			dossier.setPostalTelNo(postalTelNo);
			dossier.setApplicantNote(applicantNote);
			dossier.setServerNo(getServerNo(groupId));

			dossierPersistence.update(dossier);

			// create DossierFile if it is eForm

			List<DossierPart> dossierParts = new ArrayList<DossierPart>();

			dossierParts = dossierPartPersistence.findByTP_NO(groupId, dossierTemplateNo);

			for (DossierPart part : dossierParts) {
				if (Validator.isNotNull(part.getFormScript()) && part.getPartType() != 2) {
					String dossierFileUUID = PortalUUIDUtil.generate();

					// TODO HotFix

					if (groupId != 55301) {

					dossierFileLocalService.addDossierFile(groupId, dossierId, dossierFileUUID, dossierTemplateNo,
							part.getPartNo(), part.getFileTemplateNo(), part.getPartName(), StringPool.BLANK, 0l,
							null, StringPool.BLANK, StringPool.TRUE, context);
					}
				}
			}

		} else {

			dossier = dossierPersistence.fetchByPrimaryKey(dossierId);

			dossier.setModifiedDate(now);

			String dossierNote = getDossierNote(serviceCode, govAgencyCode, dossierTemplateNo, groupId);
			dossier.setDossierNote(dossierNote);

			if (Validator.isNotNull(address))
				dossier.setAddress(address);
			if (Validator.isNotNull(cityCode))
				dossier.setCityCode(cityCode);
			if (Validator.isNotNull(cityName))
				dossier.setCityName(cityName);
			if (Validator.isNotNull(districtCode))
				dossier.setDistrictCode(districtCode);
			if (Validator.isNotNull(districtName))
				dossier.setDistrictName(districtName);
			if (Validator.isNotNull(wardCode))
				dossier.setWardCode(wardCode);
			if (Validator.isNotNull(wardName))
				dossier.setWardName(wardName);
			if (Validator.isNotNull(contactName))
				dossier.setContactName(contactName);
			if (Validator.isNotNull(contactEmail))
				dossier.setContactEmail(contactEmail);
			if (Validator.isNotNull(contactTelNo))
				dossier.setContactTelNo(contactTelNo);

			dossier.setViaPostal(viaPostal);

			if (viaPostal == 1) {
				dossier.setPostalAddress(StringPool.BLANK);
				dossier.setPostalCityCode(StringPool.BLANK);
				dossier.setPostalTelNo(StringPool.BLANK);

			} else if (viaPostal == 2) {
				if (Validator.isNotNull(postalAddress))
					dossier.setPostalAddress(postalAddress);
				if (Validator.isNotNull(postalCityCode))
					dossier.setPostalCityCode(postalCityCode);
				if (Validator.isNotNull(postalTelNo))
					dossier.setPostalTelNo(postalTelNo);
				if (Validator.isNotNull(postalCityName))
					dossier.setPostalCityName(postalCityName);

			} else {
				dossier.setPostalAddress(StringPool.BLANK);
				dossier.setPostalCityCode(StringPool.BLANK);
				dossier.setPostalTelNo(StringPool.BLANK);
			}

//			if (Validator.isNotNull(applicantNote))
			dossier.setApplicantNote(applicantNote);

			dossierPersistence.update(dossier);

		}

		return dossier;
	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier assignToProcess(long dossierId, String dossierNote, String submissionNote, String briefNote,
			String dossierNo, long folderId, long dossierActionId, String serverNo, ServiceContext context) {

		Dossier dossier = dossierPersistence.fetchByPrimaryKey(dossierId);

		dossier.setDossierNote(dossierNote);
		dossier.setSubmissionNote(submissionNote);
		dossier.setBriefNote(briefNote);
		dossier.setDossierNo(dossierNo);
		dossier.setFolderId(folderId);
		dossier.setDossierActionId(dossierActionId);
		dossier.setServerNo(serverNo);

		dossierPersistence.update(dossier);

		return dossier;
	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateDossier(long groupId, long dossierId, String referenceUid, int counter, String serviceCode,
			String serviceName, String govAgencyCode, String govAgencyName, String applicantName,
			String applicantIdType, String applicantIdNo, Date applicantIdDate, String address, String cityCode,
			String cityName, String districtCode, String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String contactEmail, String dossierTemplateNo, String dossierNote,
			String submissionNote, String applicantNote, String briefNote, String dossierNo, boolean submitting,
			Date correctingDate, String dossierStatus, String dossierStatusText, String dossierSubStatus,
			String dossierSubStatusText, long folderId, long dossierActionId, int viaPostal, String postalAddress,
			String postalCityCode, String postalCityName, String postalTelNo, String password, boolean notification,
			boolean online, String serverNo, ServiceContext context) throws PortalException {

		Date now = new Date();

		long userId = context.getUserId();

		User auditUser = userPersistence.fetchByPrimaryKey(userId);

		validateUpdateDossier(groupId, dossierId, referenceUid, serviceCode, govAgencyCode, address, cityCode,
				districtCode, wardCode, contactName, contactTelNo, contactEmail, dossierTemplateNo, dossierNote,
				submissionNote, dossierNo, submitting, dossierStatusText, dossierSubStatusText, postalAddress,
				postalCityCode, postalTelNo, serverNo);

		Dossier dossier = null;

		if (dossierId == 0) {
			dossierId = counterLocalService.increment(Dossier.class.getName());

			dossier = dossierPersistence.create(dossierId);

			dossier.setCreateDate(now);
			dossier.setModifiedDate(now);
			dossier.setCompanyId(context.getCompanyId());
			dossier.setGroupId(groupId);
			dossier.setUserId(userId);
			if (Validator.isNotNull(auditUser))
				dossier.setUserName(auditUser.getFullName());

			// Add extent fields
			dossier.setReferenceUid(referenceUid);
			dossier.setCounter(counter);
			dossier.setServiceCode(serviceCode);
			dossier.setServiceName(serviceName);
			dossier.setGovAgencyCode(govAgencyCode);
			dossier.setGovAgencyName(govAgencyName);
			dossier.setDossierTemplateNo(dossierTemplateNo);

			DossierTemplate dt = dossierTemplatePersistence.findByG_DT_TPLNO(groupId, dossierTemplateNo);

			if (Validator.isNotNull(dt)) {
				dossier.setDossierTemplateName(dt.getTemplateName());
			}

			dossier.setApplicantName(applicantName);
			dossier.setApplicantIdType(applicantIdType);
			dossier.setApplicantIdNo(applicantIdNo);
			dossier.setApplicantIdDate(applicantIdDate);
			dossier.setDossierNo(dossierNo);
			dossier.setApplicantNote(applicantNote);
			dossier.setBriefNote(briefNote);

			dossier.setDossierStatus(dossierStatus);
			dossier.setDossierStatusText(dossierStatusText);
			dossier.setDossierSubStatus(dossierSubStatus);
			dossier.setDossierSubStatusText(dossierSubStatusText);

			dossier.setAddress(address);
			dossier.setCityCode(cityCode);
			dossier.setCityName(cityName);
			dossier.setDistrictCode(districtCode);
			dossier.setDistrictName(districtName);
			dossier.setWardCode(wardCode);
			dossier.setWardName(wardName);
			dossier.setContactName(contactName);
			dossier.setContactEmail(contactEmail);

			dossier.setFolderId(folderId);
			dossier.setDossierActionId(dossierActionId);
			dossier.setViaPostal(viaPostal);
			dossier.setPostalAddress(postalAddress);
			dossier.setPostalCityCode(postalCityCode);
			dossier.setPostalCityName(postalCityName);
			dossier.setPostalTelNo(postalTelNo);
			dossier.setPassword(password);
			dossier.setNotification(notification);
			dossier.setOnline(online);
			dossier.setServerNo(serverNo);

		} else {

			dossier = dossierPersistence.fetchByPrimaryKey(dossierId);

			dossier.setModifiedDate(now);

			dossier.setApplicantName(applicantName);
			dossier.setApplicantIdType(applicantIdType);
			dossier.setApplicantIdNo(applicantIdNo);
			dossier.setApplicantIdDate(applicantIdDate);
			dossier.setAddress(address);
			dossier.setCityCode(cityCode);
			dossier.setCityName(cityName);
			dossier.setDistrictCode(districtCode);
			dossier.setDistrictName(districtName);
			dossier.setWardCode(wardCode);
			dossier.setWardName(wardName);
			dossier.setContactName(contactName);
			dossier.setContactEmail(contactEmail);
			dossier.setViaPostal(viaPostal);
			dossier.setPostalAddress(postalAddress);
			dossier.setPostalCityCode(postalCityCode);
			dossier.setPostalCityName(postalCityName);
			dossier.setPostalTelNo(postalTelNo);
			dossier.setApplicantNote(applicantNote);
			dossier.setNotification(notification);

		}

		dossierPersistence.update(dossier);

		return dossier;
	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateDossier(long groupId, long dossierId, String referenceUid, int counter, String serviceCode,
			String serviceName, String govAgencyCode, String govAgencyName, String applicantName,
			String applicantIdType, String applicantIdNo, Date applicantIdDate, String address, String cityCode,
			String cityName, String districtCode, String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String contactEmail, String dossierTemplateNo, String dossierNote,
			String submissionNote, String applicantNote, String briefNote, String dossierNo, boolean submitting,
			Date correctingDate, String dossierStatus, String dossierStatusText, String dossierSubStatus,
			String dossierSubStatusText, long folderId, long dossierActionId, int viaPostal, String postalAddress,
			String postalCityCode, String postalCityName, String postalTelNo, String password, boolean notification,
			boolean online, String serverNo, Date submitDate, ServiceContext context) throws PortalException {

		Date now = new Date();

		long userId = context.getUserId();

		User auditUser = userPersistence.fetchByPrimaryKey(userId);

		validateUpdateDossier(groupId, dossierId, referenceUid, serviceCode, govAgencyCode, address, cityCode,
				districtCode, wardCode, contactName, contactTelNo, contactEmail, dossierTemplateNo, dossierNote,
				submissionNote, dossierNo, submitting, dossierStatusText, dossierSubStatusText, postalAddress,
				postalCityCode, postalTelNo, serverNo);

		Dossier dossier = null;

		if (dossierId == 0) {
			dossierId = counterLocalService.increment(Dossier.class.getName());

			dossier = dossierPersistence.create(dossierId);

			dossier.setCreateDate(now);
			dossier.setModifiedDate(now);
			dossier.setCompanyId(context.getCompanyId());
			dossier.setGroupId(groupId);
			dossier.setUserId(userId);
			if (Validator.isNotNull(auditUser))
				dossier.setUserName(auditUser.getFullName());

			// Add extent fields
			dossier.setReferenceUid(referenceUid);
			dossier.setCounter(counter);
			dossier.setServiceCode(serviceCode);
			dossier.setServiceName(serviceName);
			dossier.setGovAgencyCode(govAgencyCode);
			dossier.setGovAgencyName(govAgencyName);
			dossier.setDossierTemplateNo(dossierTemplateNo);

			DossierTemplate dt = dossierTemplatePersistence.findByG_DT_TPLNO(groupId, dossierTemplateNo);

			if (Validator.isNotNull(dt)) {
				dossier.setDossierTemplateName(dt.getTemplateName());
			}

			dossier.setApplicantName(applicantName);
			dossier.setApplicantIdType(applicantIdType);
			dossier.setApplicantIdNo(applicantIdNo);
			dossier.setApplicantIdDate(applicantIdDate);
			dossier.setDossierNo(dossierNo);
			dossier.setApplicantNote(applicantNote);
			dossier.setBriefNote(briefNote);

			dossier.setDossierStatus(dossierStatus);
			dossier.setDossierStatusText(dossierStatusText);
			dossier.setDossierSubStatus(dossierSubStatus);
			dossier.setDossierSubStatusText(dossierSubStatusText);

			dossier.setAddress(address);
			dossier.setCityCode(cityCode);
			dossier.setCityName(cityName);
			dossier.setDistrictCode(districtCode);
			dossier.setDistrictName(districtName);
			dossier.setWardCode(wardCode);
			dossier.setWardName(wardName);
			dossier.setContactName(contactName);
			dossier.setContactEmail(contactEmail);

			dossier.setFolderId(folderId);
			dossier.setDossierActionId(dossierActionId);
			dossier.setViaPostal(viaPostal);
			dossier.setPostalAddress(postalAddress);
			dossier.setPostalCityCode(postalCityCode);
			dossier.setPostalCityName(postalCityName);
			dossier.setPostalTelNo(postalTelNo);
			dossier.setPassword(password);
			dossier.setNotification(notification);
			dossier.setOnline(online);
			dossier.setServerNo(serverNo);
			dossier.setSubmitDate(submitDate);

		} else {

			dossier = dossierPersistence.fetchByPrimaryKey(dossierId);

			dossier.setModifiedDate(now);

			dossier.setApplicantName(applicantName);
			dossier.setApplicantIdType(applicantIdType);
			dossier.setApplicantIdNo(applicantIdNo);
			dossier.setApplicantIdDate(applicantIdDate);
			dossier.setAddress(address);
			dossier.setCityCode(cityCode);
			dossier.setCityName(cityName);
			dossier.setDistrictCode(districtCode);
			dossier.setDistrictName(districtName);
			dossier.setWardCode(wardCode);
			dossier.setWardName(wardName);
			dossier.setContactName(contactName);
			dossier.setContactEmail(contactEmail);
			dossier.setViaPostal(viaPostal);
			dossier.setPostalAddress(postalAddress);
			dossier.setPostalCityCode(postalCityCode);
			dossier.setPostalCityName(postalCityName);
			dossier.setPostalTelNo(postalTelNo);
			dossier.setApplicantNote(applicantNote);
			dossier.setNotification(notification);

		}

		dossierPersistence.update(dossier);

		return dossier;
	}

	private static final String LOCK_ALL = "LOCK ALL";

	@Indexable(type = IndexableType.REINDEX)
	public Dossier submitting(long groupId, long id, String refId, ServiceContext context) throws PortalException {

		validateSubmitting(groupId, id, refId);

		Date now = new Date();

		Dossier dossier = null;

		if (id != 0) {
			dossier = dossierPersistence.fetchByPrimaryKey(id);
		} else {
			dossier = dossierPersistence.fetchByG_REF(groupId, refId);
		}

		dossier.setModifiedDate(now);

		dossier.setSubmitting(true);

/*		if (Validator.isNull(dossier.getSubmitDate())) {
			dossier.setSubmitDate(now);
		}
*/
		if (dossier.getDossierStatus().contentEquals(DossierStatusConstants.NEW)) {
			dossier.setSubmitDate(now);
		}

//		long dActionId = 0;
//		String stepCode = StringPool.BLANK;
//		long serviceProcessId = 0;
//		String lockState = StringPool.BLANK;
//		if (dossier != null) {
//			dActionId = dossier.getDossierActionId();
//		}
//		if (dActionId > 0) {
//			DossierAction dAction = DossierActionLocalServiceUtil.fetchDossierAction(dActionId);
//			if (dAction != null) {
//				stepCode = dAction.getStepCode();
//				serviceProcessId = dAction.getServiceProcessId();
//			}
//		}
//		if (Validator.isNotNull(stepCode) && serviceProcessId > 0) {
//			ProcessStep proStep = ProcessStepLocalServiceUtil.fetchBySC_GID(stepCode, groupId, serviceProcessId);
//			if (proStep != null) {
//				lockState = proStep.getLockState();
//			}
//		}
		dossier.setLockState(LOCK_ALL);

		dossierPersistence.update(dossier);

		return dossier;
	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier reset(long groupId, long id, String refId, ServiceContext context) throws PortalException {

		validateReset(groupId, id, refId);

		Date now = new Date();

		Dossier dossier = null;

		if (id != 0) {
			dossier = dossierPersistence.fetchByPrimaryKey(id);
		} else {
			dossier = dossierPersistence.fetchByG_REF(groupId, refId);
		}

		dossier.setModifiedDate(now);
		dossier.setSubmitting(false);
		// dossier.setSubmitDate(null);

		dossierPersistence.update(dossier);

		// TODO add reset for DossierFile and PaymentFile (isNew => false)

		// TODO add remove DossierFile out system

		List<DossierFile> lsDF = dossierFileLocalService.getDossierFilesByDossierId(id);

		for (DossierFile df : lsDF) {
			if (df.getIsNew()) {

				df.setIsNew(false);

				dossierFileLocalService.updateDossierFile(df);
			}
		}

		List<PaymentFile> lsPF = paymentFileLocalService.getByDossierId(id);

		for (PaymentFile pf : lsPF) {
			if (pf.getIsNew()) {
				pf.setIsNew(false);

				paymentFileLocalService.updatePaymentFile(pf);
			}
		}

		return dossier;
	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateStatus(long groupId, long id, String refId, String status, String statusText, String subStatus,
			String subStatusText, String lockState, ServiceContext context) throws PortalException {

		validateUpdateStatus(groupId, id, refId, status, statusText, subStatus, subStatusText);

		Date now = new Date();

		Dossier dossier = null;

		if (id != 0) {
			dossier = dossierPersistence.findByPrimaryKey(id);
		} else {
			dossier = dossierPersistence.fetchByG_REF(groupId, refId);
		}

		dossier.setModifiedDate(now);

		dossier.setDossierStatus(status);
		dossier.setDossierStatusText(statusText);
		dossier.setDossierSubStatus(subStatus);
		dossier.setDossierSubStatusText(subStatusText);
		dossier.setLockState(lockState);

/*		if (status.equalsIgnoreCase(DossierStatusConstants.RECEIVING)) {
			dossier.setReceiveDate(now);
		}
*/
		if (status.equalsIgnoreCase(DossierStatusConstants.RELEASING)) {
			dossier.setReleaseDate(now);
		}

		if (status.equalsIgnoreCase(DossierStatusConstants.DONE)) {
			dossier.setFinishDate(now);
		}

		dossierPersistence.update(dossier);

		return dossier;

	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateSubmittingDate(long groupId, long id, String refId, Date date, ServiceContext context)
			throws PortalException {

		validateSubmittingDate(groupId, id, refId, date);

		Date now = new Date();

		Dossier dossier = null;

		if (id != 0) {
			dossier = dossierPersistence.fetchByPrimaryKey(id);
		} else {
			dossier = dossierPersistence.fetchByG_REF(groupId, refId);
		}

		dossier.setModifiedDate(now);

		dossier.setSubmitDate(date);

		dossierPersistence.update(dossier);

		return dossier;

	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateReceivingDate(long groupId, long id, String refId, Date date, ServiceContext context)
			throws PortalException {

		validateReceivingDate(groupId, id, refId, date);

		Date now = new Date();

		Dossier dossier = null;

		if (id != 0) {
			dossier = dossierPersistence.fetchByPrimaryKey(id);
		} else {
			dossier = dossierPersistence.fetchByG_REF(groupId, refId);
		}

		dossier.setModifiedDate(now);

		dossier.setReceiveDate(date);

		dossierPersistence.update(dossier);

		return dossier;

	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateDueDate(long groupId, long id, String refId, Date date, ServiceContext context)
			throws PortalException {

		validateDueDate(groupId, id, refId, date);

		Date now = new Date();

		Dossier dossier = null;

		if (id != 0) {
			dossier = dossierPersistence.fetchByPrimaryKey(id);
		} else {
			dossier = dossierPersistence.fetchByG_REF(groupId, refId);
		}

		dossier.setModifiedDate(now);

		dossier.setDueDate(date);

		dossierPersistence.update(dossier);

		return dossier;

	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateReleaseDate(long groupId, long id, String refId, Date date, ServiceContext context)
			throws PortalException {

		validateReleaseDate(groupId, id, refId, date);

		Date now = new Date();

		Dossier dossier = null;

		if (id != 0) {
			dossier = dossierPersistence.fetchByPrimaryKey(id);
		} else {
			dossier = dossierPersistence.fetchByG_REF(groupId, refId);
		}

		dossier.setModifiedDate(now);

		dossier.setReleaseDate(date);

		dossierPersistence.update(dossier);

		return dossier;

	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateCancellingDate(long groupId, long id, String refId, Date date, ServiceContext context)
			throws PortalException {

		validateCancellingDate(groupId, id, refId, date);

		Date now = new Date();

		Dossier dossier = null;

		if (id != 0) {
			dossier = dossierPersistence.fetchByPrimaryKey(id);
		} else {
			dossier = dossierPersistence.fetchByG_REF(groupId, refId);
		}

		dossier.setModifiedDate(now);

		dossier.setCancellingDate(date);

		dossier.setSubmitting(true);

		dossierPersistence.update(dossier);

		return dossier;

	}
	
	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateEndosementDate(long groupId, long id, String refId, Date date, ServiceContext context)
			throws PortalException {

		validateCancellingDate(groupId, id, refId, date);

		Date now = new Date();

		Dossier dossier = null;

		if (id != 0) {
			dossier = dossierPersistence.fetchByPrimaryKey(id);
		} else {
			dossier = dossierPersistence.fetchByG_REF(groupId, refId);
		}

		dossier.setModifiedDate(now);

		//dossier.setCancellingDate(date);
		
		dossier.setEndorsementDate(date);

		dossier.setSubmitting(true);

		dossierPersistence.update(dossier);

		return dossier;

	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateFinishDate(long groupId, long id, String refId, Date date, ServiceContext context)
			throws PortalException {

		validateFinishDate(groupId, id, refId, date);

		Date now = new Date();

		Dossier dossier = null;

		if (id != 0) {
			dossier = dossierPersistence.fetchByPrimaryKey(id);
		} else {
			dossier = dossierPersistence.fetchByG_REF(groupId, refId);
		}

		dossier.setModifiedDate(now);

		dossier.setFinishDate(date);

		dossierPersistence.update(dossier);

		return dossier;

	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateCorrectingDate(long groupId, long id, String refId, Date date, ServiceContext context)
			throws PortalException {

		validateCorrectingDate(groupId, id, refId, date);

		Date now = new Date();

		Dossier dossier = null;

		if (id != 0) {
			dossier = dossierPersistence.fetchByPrimaryKey(id);
		} else {
			dossier = dossierPersistence.fetchByG_REF(groupId, refId);
		}

		dossier.setModifiedDate(now);

		dossier.setCorrecttingDate(date);

		dossier.setSubmitting(true);

		dossierPersistence.update(dossier);

		return dossier;

	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateDossierAction(long groupId, long id, String refId, long dossierActionId,
			ServiceContext context) throws PortalException {

		validateDossierAction(groupId, id, refId, dossierActionId);

		Date now = new Date();

		Dossier dossier = null;

		if (id != 0) {
			dossier = dossierPersistence.fetchByPrimaryKey(id);
		} else {
			dossier = dossierPersistence.fetchByG_REF(groupId, refId);
		}

		dossier.setModifiedDate(now);

		dossier.setDossierActionId(dossierActionId);

		dossierPersistence.update(dossier);

		return dossier;

	}

	public Dossier getByRef(long groupId, String refId) {
		return dossierPersistence.fetchByG_REF(groupId, refId);
	}

	@Indexable(type = IndexableType.DELETE)
	public Dossier removeDossier(long groupId, long dossierId, String refId) throws PortalException {
		// TODO remove dossierLog

		// TODO remove dossierFile

		// TODO remove dossierAction

		// TODO remove PaymentFile

		validateRemoveDossier(groupId, dossierId, refId);

		Dossier dossier = null;

		if (dossierId != 0) {
			dossier = dossierPersistence.fetchByPrimaryKey(dossierId);
		} else {
			dossier = dossierPersistence.findByG_REF(groupId, refId);
		}

		return dossierPersistence.remove(dossier);

	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateDossierBriefNote(long dossierId, String dossierBriefNote) throws PortalException {
		Dossier dossier = dossierPersistence.findByPrimaryKey(dossierId);

		dossier.setBriefNote(dossierBriefNote);

		return dossierPersistence.update(dossier);

	}

	public int countByUserId(long userId, long groupId) {
		return dossierPersistence.countByG_UID(groupId, userId);
	}

	private void validateRemoveDossier(long groupId, long dossierId, String refId) throws PortalException {
		// TODO add validate for remove Dossier
	}

	private void validateDossierAction(long groupId, long id, String refId, long dossierActionId)
			throws PortalException {
		// TODO add validate for submitting
	}

	private void validateSubmittingDate(long groupId, long id, String refId, Date date) throws PortalException {
		// TODO add validate
	}

	private void validateReceivingDate(long groupId, long id, String refId, Date date) throws PortalException {
		// TODO add validate
	}

	private void validateReleaseDate(long groupId, long id, String refId, Date date) throws PortalException {
		// TODO add validate
	}

	private void validateFinishDate(long groupId, long id, String refId, Date date) throws PortalException {
		// TODO add validate
	}

	private void validateCancellingDate(long groupId, long id, String refId, Date date) throws PortalException {
		// TODO add validate
	}

	private void validateDueDate(long groupId, long id, String refId, Date date) throws PortalException {
		// TODO add validate
	}

	private void validateCorrectingDate(long groupId, long id, String refId, Date date) throws PortalException {
		// TODO add validate
	}

	private void validateUpdateStatus(long groupId, long id, String refId, String status, String statusText,
			String subStatus, String subStatusText) throws PortalException {
		// TODO add validate
	}

	private void validateSubmitting(long groupId, long id, String refId) throws PortalException {
		// TODO add validate for submitting

		// Check dossier status

		// Check DossierFile, PaymentFile

	}

	private void validateReset(long groupId, long id, String refId) throws PortalException {
		// TODO add validate for submitting
	}

	private void validateInit(long groupId, long dossierId, String referenceUid, String serviceCode,
			String govAgencyCode, String address, String cityCode, String districtCode, String wardCode,
			String contactName, String contactTelNo, String contactEmail, String dossierTemplateNo)
			throws PortalException {

	}

	private void validateUpdateDossier(long groupId, long dossierId, String referenceUid, String serviceCode,
			String govAgencyCode, String address, String cityCode, String districtCode, String wardCode,
			String contactName, String contactTelNo, String contactEmail, String dossierTemplateNo, String dossierNote,
			String submissionNote, String dossierNo, boolean submitting, String dossierStatus, String dossierSubStatus,
			String postalAddress, String postalCityCode, String postalTelNo, String serverNo) throws PortalException {

	}

	public Document getDossierById(long dossierId, long companyId) throws PortalException {
		//Document document = null;

		Indexer<Dossier> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Dossier.class);

		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		// SearchContext searchContext =
		// SearchContextFactory.getInstance(request);

		searchContext.setEnd(QueryUtil.ALL_POS);
		searchContext.setKeywords(StringPool.BLANK);
		searchContext.setStart(QueryUtil.ALL_POS);
		//searchContext.set

		BooleanQuery booleanQuery = null;

		booleanQuery = indexer.getFullQuery(searchContext);
		
		
		if (dossierId != 0) {
			MultiMatchQuery query = new MultiMatchQuery(String.valueOf(dossierId));

			query.addField(DossierTerm.DOSSIER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}
		
		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		Hits hits = IndexSearcherHelperUtil.search(searchContext, booleanQuery);
		
		List<Document> documents = hits.toList();
		
		if (documents.size() > 0) {
			return documents.get(0);
		} else {
			return null;
		}
		
	}

	@SuppressWarnings("deprecation")
	public Hits searchLucene(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		_log.info("keywords Search: "+keywords);
		String groupId = (String) params.get(Field.GROUP_ID);
		String secetKey = GetterUtil.getString(params.get("secetKey"));

		String status = GetterUtil.getString(params.get(DossierTerm.STATUS));
		String subStatus = GetterUtil.getString(params.get(DossierTerm.SUBSTATUS));
		String agency = GetterUtil.getString(params.get(DossierTerm.AGENCY));
		String service = GetterUtil.getString(params.get(DossierTerm.SERVICE));
		String template = GetterUtil.getString(params.get(DossierTerm.TEMPLATE));
		String step = GetterUtil.getString(params.get(DossierTerm.STEP));
		String state = GetterUtil.getString(params.get(DossierTerm.STATE));
		String follow = GetterUtil.getString(params.get(DossierTerm.FOLLOW));
		String dossierNo = GetterUtil.getString(params.get(DossierTerm.DOSSIER_NO));
		// Get by certificate number
		String certificateNo = (String) params.get(DossierTerm.DOSSIER_ID_CTN);

		// String top = GetterUtil.getString(params.get(DossierTerm.TOP));

		String owner = GetterUtil.getString(params.get(DossierTerm.OWNER));
		String submitting = GetterUtil.getString(params.get(DossierTerm.SUBMITTING));

		int year = GetterUtil.getInteger(params.get(DossierTerm.YEAR));
		int month = GetterUtil.getInteger(params.get(DossierTerm.MONTH));
		long userId = GetterUtil.getLong(params.get(DossierTerm.USER_ID));
				
		//TODO
		String strDossierActionId = GetterUtil.getString(params.get(DossierTerm.DOSSIER_ACTION_ID));

		String fromReceiveDate = GetterUtil.getString(params.get(DossierTerm.FROM_RECEIVEDATE));
		String toReceiveDate = GetterUtil.getString(params.get(DossierTerm.TO_RECEIVEDATE));
		String certNo = GetterUtil.getString(params.get(DossierTerm.CERT_NO));
		String fromCertDate = GetterUtil.getString(params.get(DossierTerm.FROM_CERT_DATE));
		String toCertDate = GetterUtil.getString(params.get(DossierTerm.TO_CERT_DATE));
		String fromSubmitDate = GetterUtil.getString(params.get(DossierTerm.FROM_SUBMIT_DATE));
		String toSubmitDate = GetterUtil.getString(params.get(DossierTerm.TO_SUBMIT_DATE));
		String notState = GetterUtil.getString(params.get(DossierTerm.NOT_STATE));
		Long statusReg = GetterUtil.getLong(params.get(DossierTerm.STATUS_REG));
//		_log.info("STATUS_REG Local Search: "+statusReg);
		Long notStatusReg = GetterUtil.getLong(params.get(DossierTerm.NOT_STATUS_REG));
//		_log.info("notStatusReg_REG Local Search: "+notStatusReg);
		String keySearch = GetterUtil.getString(params.get(DossierTerm.KEYWORD_SEARCH_LIKE));
		_log.info("keySearch Local Search: "+keySearch); 

		Indexer<Dossier> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Dossier.class);

		searchContext.addFullQueryEntryClassName(CLASS_NAME);
		searchContext.setEntryClassNames(new String[] { CLASS_NAME });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setStart(start);
		searchContext.setEnd(end);
		searchContext.setAndSearch(true);
		searchContext.setSorts(sorts);

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		} else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		if (Validator.isNotNull(keywords)) {

			BooleanQuery query = new BooleanQueryImpl(); 
			//BooleanQueryFactoryUtil.create(searchContext);
			
//			StringQuery sque = new StringQuery(keywords);
			
			
//			BooleanQuery qee = new BooleanQuery();
			_log.info("keywords Search: "+keywords);

			String[] subQuerieArr = new String[] { DossierTerm.SERVICE_NAME, DossierTerm.APPLICANT_NAME,
					DossierTerm.DOSSIER_NO_SEARCH, DossierTerm.DOSSIER_ID, DossierTerm.DOSSIER_ID_CTN,
					DossierTerm.BRIEF_NOTE };

			query.addTerms(subQuerieArr, keywords, true);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
			
			
//			TermQuery termQuery = TermQueryFactoryUtil.create(searchContext, Field.TITLE, "%"+keywords);
//			String[] keyword = keywords.split(StringPool.SPACE);
//
//			for (String string : keyword) {
//
//				MultiMatchQuery query = new MultiMatchQuery(string);
//
//				query.addFields(
//						new String[] { DossierTerm.DOSSIER_ID, DossierTerm.SERVICE_NAME, DossierTerm.DOSSIER_NO_SEARCH, "dossierIdCTN"});
//
//				booleanQuery.add(query, BooleanClauseOccur.MUST);
//
//			}
		}
		if (!(Validator.isNotNull(secetKey) && secetKey.contentEquals("OPENCPSV2"))) {
			if (Validator.isNotNull(groupId)) {
				MultiMatchQuery query = new MultiMatchQuery(groupId);

				query.addFields(Field.GROUP_ID);

				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}

		}

		if (Validator.isNotNull(owner) && Boolean.parseBoolean(owner) && userId > 0) {
			MultiMatchQuery query = new MultiMatchQuery(String.valueOf(userId));

			query.addField(DossierTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(follow) && Boolean.parseBoolean(follow) && userId > 0) {

			MultiMatchQuery query = new MultiMatchQuery(String.valueOf(userId));

			query.addField(DossierTerm.ACTION_MAPPING_USERID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(state)) {
			if (state.equals("cancelling")) {

				BooleanQuery subQuery = new BooleanQueryImpl();

				MultiMatchQuery query1 = new MultiMatchQuery(String.valueOf(0));

				query1.addField(DossierTerm.CANCELLING_DATE_TIMESTAMP);

				MultiMatchQuery query2 = new MultiMatchQuery("cancelled");

				query2.addField(DossierTerm.DOSSIER_STATUS);

				subQuery.add(query1, BooleanClauseOccur.MUST_NOT);

				subQuery.add(query2, BooleanClauseOccur.MUST_NOT);

				booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
			}
			if (state.equals("correcting")) {

				MultiMatchQuery query = new MultiMatchQuery(String.valueOf(0));

				query.addField(DossierTerm.CORRECTING_DATE_TIMESTAMP);

				booleanQuery.add(query, BooleanClauseOccur.MUST_NOT);
			}
			if (state.equals("endorsement")) {

				MultiMatchQuery query = new MultiMatchQuery(String.valueOf(0));

				query.addField(DossierTerm.ENDORSEMENT_DATE_TIMESTAMP);

				booleanQuery.add(query, BooleanClauseOccur.MUST_NOT);
			}
		}

		if (Validator.isNotNull(submitting) && Boolean.parseBoolean(submitting)) {
			
			MultiMatchQuery query = new MultiMatchQuery(String.valueOf(submitting));

			query.addField(DossierTerm.SUBMITTING);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(status)) {

			String[] lstStatus = StringUtil.split(status);

			if (lstStatus != null && lstStatus.length > 0) {
				BooleanQuery subQuery = new BooleanQueryImpl();
				for (int i = 0; i < lstStatus.length; i++) {
					MultiMatchQuery query = new MultiMatchQuery(lstStatus[i]);

					query.addField(DossierTerm.DOSSIER_STATUS);

					subQuery.add(query, BooleanClauseOccur.SHOULD);
				}

				booleanQuery.add(subQuery, BooleanClauseOccur.MUST);

			} else {
				MultiMatchQuery query = new MultiMatchQuery(status);

				query.addFields(DossierTerm.DOSSIER_STATUS);

				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}

		}

		if (Validator.isNotNull(subStatus)) {

			String[] lstSubStatus = StringUtil.split(subStatus);

			if (lstSubStatus != null && lstSubStatus.length > 0) {

				BooleanQuery subQuery = new BooleanQueryImpl();

				for (int i = 0; i < lstSubStatus.length; i++) {
					MultiMatchQuery query = new MultiMatchQuery(lstSubStatus[i]);

					query.addField(DossierTerm.DOSSIER_SUB_STATUS);

					subQuery.add(query, BooleanClauseOccur.SHOULD);
				}

				booleanQuery.add(subQuery, BooleanClauseOccur.MUST);

			} else {
				MultiMatchQuery query = new MultiMatchQuery(subStatus);

				query.addFields(DossierTerm.DOSSIER_SUB_STATUS);

				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
		}

		if (Validator.isNotNull(agency)) {
			MultiMatchQuery query = new MultiMatchQuery(agency);

			query.addFields(DossierTerm.GOV_AGENCY_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(service)) {
			MultiMatchQuery query = new MultiMatchQuery(service);

			query.addFields(DossierTerm.SERVICE_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(template)) {
			MultiMatchQuery query = new MultiMatchQuery(template);

			query.addFields(DossierTerm.DOSSIER_TEMPLATE_NO);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (year > 0) {
			MultiMatchQuery query = new MultiMatchQuery(String.valueOf(year));

			query.addFields(DossierTerm.YEAR_DOSSIER);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (month > 0) {
			MultiMatchQuery query = new MultiMatchQuery(String.valueOf(month));

			query.addFields(DossierTerm.MONTH_DOSSIER);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}
		if (Validator.isNotNull(step)) {
			MultiMatchQuery query = new MultiMatchQuery(step);

			query.addFields(DossierTerm.STEP);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(dossierNo)) {

			String[] keyDossier = dossierNo.split(StringPool.SPACE);

			for (String key : keyDossier) {

				MultiMatchQuery query = new MultiMatchQuery(key);

				query.addFields(
						new String[] { DossierTerm.DOSSIER_NO_SEARCH, "dossierIdCTN"});

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(certificateNo)) {
			MultiMatchQuery query = new MultiMatchQuery(certificateNo);

			query.addFields(DossierTerm.DOSSIER_ID_CTN);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(strDossierActionId)) {
			String[] sliptDossierActionId = StringUtil.split(strDossierActionId);
			if (sliptDossierActionId != null && sliptDossierActionId.length > 0) {
			BooleanQuery subQuery = new BooleanQueryImpl();
				for (String dossierActionId : sliptDossierActionId) {
					if (Validator.isNotNull(dossierActionId)) {
	
						MultiMatchQuery query = new MultiMatchQuery(dossierActionId);
	
						query.addFields(DossierTerm.DOSSIER_ACTION_ID);
						subQuery.add(query, BooleanClauseOccur.SHOULD);
					}
				}
				booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
			} else {
				MultiMatchQuery query = new MultiMatchQuery(strDossierActionId);

				query.addFields(DossierTerm.DOSSIER_ACTION_ID);

				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
		}

		String fromReceiveDateFilter = fromReceiveDate + "000000";
		String toReceiveDateFilter = toReceiveDate + "235959";

		if (Validator.isNotNull(fromReceiveDate)) {
			if (Validator.isNotNull(toReceiveDate)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
					DossierTerm.RECEIVE_DATE, fromReceiveDateFilter, toReceiveDateFilter, true,
					true);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);				
			}
			else {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
						DossierTerm.RECEIVE_DATE, fromReceiveDateFilter, toReceiveDateFilter, 
						true,
						false);

					booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);								
			}
		}
		else {
			if (Validator.isNotNull(toReceiveDate)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
						DossierTerm.RECEIVE_DATE, fromReceiveDateFilter, toReceiveDateFilter, 
						false,
						true);

					booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			}
		}
		
		if (Validator.isNotNull(certNo)) {
			MultiMatchQuery query = new MultiMatchQuery(String.valueOf(certNo));

			query.addField(DossierTerm.CERT_NO_SEARCH);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}
		
		String fromCertDateFilter = fromCertDate + "000000";
		String toCertDateFilter = toCertDate + "235959";
		
		if (Validator.isNotNull(fromCertDate)) {
			if (Validator.isNotNull(toCertDate)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
					DossierTerm.CERT_DATE, fromCertDateFilter, toCertDateFilter, true,
					true);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);				
			}
			else {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
						DossierTerm.CERT_DATE, fromCertDateFilter, toCertDateFilter, 
						true,
						false);

					booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);								
			}
		}
		else {
			if (Validator.isNotNull(toCertDate)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
						DossierTerm.CERT_DATE, fromCertDateFilter, toCertDateFilter, 
						false,
						true);

					booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);								
			}
		}

		String fromSubmitDateFilter = fromSubmitDate + "000000";
		String toSubmitDateFilter = toSubmitDate + "235959";

		if (Validator.isNotNull(fromSubmitDate)) {
			if (Validator.isNotNull(toSubmitDate)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
					DossierTerm.SUBMIT_DATE, fromSubmitDateFilter, toSubmitDateFilter, true,
					true);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);				
			}
			else {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
						DossierTerm.SUBMIT_DATE, fromSubmitDateFilter, toSubmitDateFilter, 
						true,
						false);

					booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);								
			}
		}
		else {
			if (Validator.isNotNull(toSubmitDate)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
						DossierTerm.RECEIVE_DATE, fromSubmitDateFilter, toSubmitDateFilter, 
						false,
						true);

					booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			}
		}

		if (Validator.isNotNull(notState)) {
			//LamTV: Case not have flag cancel
			if (notState.equals("cancelling")) {

				MultiMatchQuery query = new MultiMatchQuery(String.valueOf(0));

				query.addField(DossierTerm.CANCELLING_DATE_TIMESTAMP);

				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
			//LamTV: Case not have flag correct and endorsement
			if (notState.contains("correcting") ) {

				MultiMatchQuery query = new MultiMatchQuery(String.valueOf(0));

				query.addField(DossierTerm.CORRECTING_DATE_TIMESTAMP);

				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
			if (notState.contains("endorsement")) {

				MultiMatchQuery query = new MultiMatchQuery(String.valueOf(0));

				query.addField(DossierTerm.ENDORSEMENT_DATE_TIMESTAMP);

				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
		}

		if (Validator.isNotNull(statusReg)) {
			MultiMatchQuery query = new MultiMatchQuery(String.valueOf(statusReg));

			query.addField(DossierTerm.STATUS_REG);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(notStatusReg)) {
			MultiMatchQuery query = new MultiMatchQuery(String.valueOf(notStatusReg));

			query.addField(DossierTerm.STATUS_REG);

			booleanQuery.add(query, BooleanClauseOccur.MUST_NOT);
		}

		if (Validator.isNotNull(keySearch)) {
			BooleanQuery query = new BooleanQueryImpl();

			String[] subQuerieArr = new String[] { DossierTerm.SERVICE_NAME, DossierTerm.APPLICANT_NAME,
					DossierTerm.DOSSIER_NO_SEARCH, DossierTerm.DOSSIER_ID, DossierTerm.DOSSIER_ID_CTN,
					DossierTerm.BRIEF_NOTE };

			query.addTerms(subQuerieArr, keySearch.toLowerCase(), true);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);
	}

	@SuppressWarnings("deprecation")
	public long countLucene(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);
		String secetKey = GetterUtil.getString(params.get("secetKey"));
		String status = GetterUtil.getString(params.get(DossierTerm.STATUS));
		String subStatus = GetterUtil.getString(params.get(DossierTerm.SUBSTATUS));
		String agency = GetterUtil.getString(params.get(DossierTerm.AGENCY));
		String service = GetterUtil.getString(params.get(DossierTerm.SERVICE));
		String template = GetterUtil.getString(params.get(DossierTerm.TEMPLATE));
		String state = GetterUtil.getString(params.get(DossierTerm.STATE));
		String step = GetterUtil.getString(params.get(DossierTerm.STEP));
		String dossierNo = GetterUtil.getString(params.get(DossierTerm.DOSSIER_NO));
		// Get by certificate number
		String certificateNo = (String) params.get(DossierTerm.DOSSIER_ID_CTN);

		// TODO add more logic here
		String follow = GetterUtil.getString(params.get(DossierTerm.FOLLOW));
		// String top = GetterUtil.getString(params.get(DossierTerm.TOP));

		String owner = GetterUtil.getString(params.get(DossierTerm.OWNER));
		String submitting = GetterUtil.getString(params.get(DossierTerm.SUBMITTING));

		long userId = GetterUtil.getLong(params.get(DossierTerm.USER_ID));

		int year = GetterUtil.getInteger(params.get(DossierTerm.YEAR));
		int month = GetterUtil.getInteger(params.get(DossierTerm.MONTH));
		//TODO
		String strDossierActionId = GetterUtil.getString(params.get(DossierTerm.DOSSIER_ACTION_ID));

		String fromReceiveDate = GetterUtil.getString(params.get(DossierTerm.FROM_RECEIVEDATE));
		String toReceiveDate = GetterUtil.getString(params.get(DossierTerm.TO_RECEIVEDATE));
		String certNo = GetterUtil.getString(params.get(DossierTerm.CERT_NO));
		String fromCertDate = GetterUtil.getString(params.get(DossierTerm.FROM_CERT_DATE));
		String toCertDate = GetterUtil.getString(params.get(DossierTerm.TO_CERT_DATE));
		String fromSubmitDate = GetterUtil.getString(params.get(DossierTerm.FROM_SUBMIT_DATE));
		String toSubmitDate = GetterUtil.getString(params.get(DossierTerm.TO_SUBMIT_DATE));
		String notState = GetterUtil.getString(params.get(DossierTerm.NOT_STATE));
		Long statusReg = GetterUtil.getLong(params.get(DossierTerm.STATUS_REG));
		Long notStatusReg = GetterUtil.getLong(params.get(DossierTerm.NOT_STATUS_REG));
//		_log.info("statusReg: "+statusReg);
//		_log.info("notStatusReg: "+notStatusReg);
		String keySearch = GetterUtil.getString(params.get(DossierTerm.KEYWORD_SEARCH_LIKE));
		_log.info("keySearch Local Search: "+keySearch);

		Indexer<Dossier> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Dossier.class);

		searchContext.addFullQueryEntryClassName(CLASS_NAME);
		searchContext.setEntryClassNames(new String[] { CLASS_NAME });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setAndSearch(true);

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		} else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		if (Validator.isNotNull(keywords)) {

			BooleanQuery query = new BooleanQueryImpl();

			String[] subQuerieArr = new String[] { DossierTerm.SERVICE_NAME, DossierTerm.APPLICANT_NAME,
					DossierTerm.DOSSIER_NO_SEARCH, DossierTerm.DOSSIER_ID, DossierTerm.DOSSIER_ID_CTN,
					DossierTerm.BRIEF_NOTE };

			query.addTerms(subQuerieArr, keywords, true);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (!(Validator.isNotNull(secetKey) && secetKey.contentEquals("OPENCPSV2"))) {
			if (Validator.isNotNull(groupId)) {
				MultiMatchQuery query = new MultiMatchQuery(groupId);

				query.addFields(Field.GROUP_ID);

				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}

		}

		if (Validator.isNotNull(owner) && Boolean.parseBoolean(owner) && userId > 0) {
			MultiMatchQuery query = new MultiMatchQuery(String.valueOf(userId));

			query.addField(DossierTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(follow) && Boolean.parseBoolean(follow) && userId > 0) {
			MultiMatchQuery query = new MultiMatchQuery(String.valueOf(userId));

			query.addField(DossierTerm.ACTION_MAPPING_USERID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(state)) {
			if (state.equals("cancelling")) {

				BooleanQuery subQuery = new BooleanQueryImpl();

				MultiMatchQuery query1 = new MultiMatchQuery(String.valueOf(0));

				query1.addField(DossierTerm.CANCELLING_DATE_TIMESTAMP);

				MultiMatchQuery query2 = new MultiMatchQuery("cancelled");

				query2.addField(DossierTerm.DOSSIER_STATUS);

				subQuery.add(query1, BooleanClauseOccur.MUST_NOT);

				subQuery.add(query2, BooleanClauseOccur.MUST_NOT);

				booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
			}
			if (state.equals("correcting")) {

				MultiMatchQuery query = new MultiMatchQuery(String.valueOf(0));

				query.addField(DossierTerm.CORRECTING_DATE_TIMESTAMP);

				booleanQuery.add(query, BooleanClauseOccur.MUST_NOT);
			}
			if (state.equals("endorsement")) {

				MultiMatchQuery query = new MultiMatchQuery(String.valueOf(0));

				query.addField(DossierTerm.ENDORSEMENT_DATE_TIMESTAMP);

				booleanQuery.add(query, BooleanClauseOccur.MUST_NOT);
			}
		}

		if (Validator.isNotNull(submitting) && Boolean.parseBoolean(submitting)) {
			
			MultiMatchQuery query = new MultiMatchQuery(String.valueOf(submitting));

			query.addField(DossierTerm.SUBMITTING);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(status)) {

			String[] lstStatus = StringUtil.split(status);

			if (lstStatus != null && lstStatus.length > 0) {
				BooleanQuery subQuery = new BooleanQueryImpl();
				for (int i = 0; i < lstStatus.length; i++) {
					MultiMatchQuery query = new MultiMatchQuery(lstStatus[i]);

					query.addField(DossierTerm.DOSSIER_STATUS);

					subQuery.add(query, BooleanClauseOccur.SHOULD);
				}

				booleanQuery.add(subQuery, BooleanClauseOccur.MUST);

			} else {
				MultiMatchQuery query = new MultiMatchQuery(status);

				query.addFields(DossierTerm.DOSSIER_STATUS);

				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}

		}

		if (Validator.isNotNull(subStatus)) {

			String[] lstSubStatus = StringUtil.split(subStatus);

			if (lstSubStatus != null && lstSubStatus.length > 0) {

				BooleanQuery subQuery = new BooleanQueryImpl();

				for (int i = 0; i < lstSubStatus.length; i++) {
					MultiMatchQuery query = new MultiMatchQuery(lstSubStatus[i]);

					query.addField(DossierTerm.DOSSIER_SUB_STATUS);

					subQuery.add(query, BooleanClauseOccur.SHOULD);
				}

				booleanQuery.add(subQuery, BooleanClauseOccur.MUST);

			} else {
				MultiMatchQuery query = new MultiMatchQuery(subStatus);

				query.addFields(DossierTerm.DOSSIER_SUB_STATUS);

				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
		}

		if (Validator.isNotNull(agency)) {
			MultiMatchQuery query = new MultiMatchQuery(agency);

			query.addFields(DossierTerm.GOV_AGENCY_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(service)) {
			MultiMatchQuery query = new MultiMatchQuery(service);

			query.addFields(DossierTerm.SERVICE_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(template)) {
			MultiMatchQuery query = new MultiMatchQuery(template);

			query.addFields(DossierTerm.DOSSIER_TEMPLATE_NO);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (year > 0) {
			MultiMatchQuery query = new MultiMatchQuery(String.valueOf(year));

			query.addFields(DossierTerm.YEAR_DOSSIER);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (month > 0) {
			MultiMatchQuery query = new MultiMatchQuery(String.valueOf(month));

			query.addFields(DossierTerm.MONTH_DOSSIER);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}
		if (Validator.isNotNull(step)) {
			MultiMatchQuery query = new MultiMatchQuery(step);

			query.addFields(DossierTerm.STEP_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(dossierNo)) {

			String[] keyDossier = dossierNo.split(StringPool.SPACE);

			for (String key : keyDossier) {

				MultiMatchQuery query = new MultiMatchQuery(key);

				query.addFields(
						new String[] { DossierTerm.DOSSIER_NO_SEARCH, "dossierIdCTN"});

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(certificateNo)) {
			MultiMatchQuery query = new MultiMatchQuery(certificateNo);

			query.addFields(DossierTerm.DOSSIER_ID_CTN);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(strDossierActionId)) {
			String[] sliptDossierActionId = StringUtil.split(strDossierActionId);
			if (sliptDossierActionId != null && sliptDossierActionId.length > 0) {
			BooleanQuery subQuery = new BooleanQueryImpl();
				for (String dossierActionId : sliptDossierActionId) {
					if (Validator.isNotNull(dossierActionId)) {
	
						MultiMatchQuery query = new MultiMatchQuery(dossierActionId);
	
						query.addFields(DossierTerm.DOSSIER_ACTION_ID);
						subQuery.add(query, BooleanClauseOccur.SHOULD);
					}
				}
				booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
			} else {
				MultiMatchQuery query = new MultiMatchQuery(strDossierActionId);

				query.addFields(DossierTerm.DOSSIER_ACTION_ID);

				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
		}

		String fromReceiveDateFilter = fromReceiveDate + "000000";
		String toReceiveDateFilter = toReceiveDate + "235959";

		if (Validator.isNotNull(fromReceiveDate)) {
			if (Validator.isNotNull(toReceiveDate)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
					DossierTerm.RECEIVE_DATE, fromReceiveDateFilter, toReceiveDateFilter, true,
					true);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);				
			}
			else {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
						DossierTerm.RECEIVE_DATE, fromReceiveDateFilter, toReceiveDateFilter, 
						true,
						false);

					booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);								
			}
		}
		else {
			if (Validator.isNotNull(toReceiveDate)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
						DossierTerm.RECEIVE_DATE, fromReceiveDateFilter, toReceiveDateFilter, 
						false,
						true);

					booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);								
			}
		}
		
		if (Validator.isNotNull(certNo)) {
			MultiMatchQuery query = new MultiMatchQuery(String.valueOf(certNo));

			query.addField(DossierTerm.CERT_NO_SEARCH);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}
		
		String fromCertDateFilter = fromCertDate + "000000";
		String toCertDateFilter = toCertDate + "235959";

		if (Validator.isNotNull(fromCertDate)) {
			if (Validator.isNotNull(toCertDate)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
					DossierTerm.CERT_DATE, fromCertDateFilter, toCertDateFilter, true,
					true);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);				
			}
			else {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
						DossierTerm.CERT_DATE, fromCertDateFilter, toCertDateFilter, 
						true,
						false);

					booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);								
			}
		}
		else {
			if (Validator.isNotNull(toCertDate)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
						DossierTerm.CERT_DATE, fromCertDateFilter, toCertDateFilter, 
						false,
						true);

					booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);								
			}
		}

		String fromSubmitDateFilter = fromSubmitDate + "000000";
		String toSubmitDateFilter = toSubmitDate + "235959";

		if (Validator.isNotNull(fromSubmitDate)) {
			if (Validator.isNotNull(toSubmitDate)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
					DossierTerm.SUBMIT_DATE, fromSubmitDateFilter, toSubmitDateFilter, true,
					true);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);				
			}
			else {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
						DossierTerm.SUBMIT_DATE, fromSubmitDateFilter, toSubmitDateFilter, 
						true,
						false);

					booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);								
			}
		}
		else {
			if (Validator.isNotNull(toSubmitDate)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
						DossierTerm.RECEIVE_DATE, fromSubmitDateFilter, toSubmitDateFilter, 
						false,
						true);

					booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			}
		}

		if (Validator.isNotNull(notState)) {
			//LamTV: Case not have flag cancel
			if (notState.equals("cancelling")) {

				MultiMatchQuery query = new MultiMatchQuery(String.valueOf(0));

				query.addField(DossierTerm.CANCELLING_DATE_TIMESTAMP);

				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
			//LamTV: Case not have flag correct and endorsement
			if (notState.contains("correcting") ) {

				MultiMatchQuery query = new MultiMatchQuery(String.valueOf(0));

				query.addField(DossierTerm.CORRECTING_DATE_TIMESTAMP);

				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
			if (notState.contains("endorsement")) {

				MultiMatchQuery query = new MultiMatchQuery(String.valueOf(0));

				query.addField(DossierTerm.ENDORSEMENT_DATE_TIMESTAMP);

				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
		}

		//LamTV: Add process case abnormal
		if (Validator.isNotNull(statusReg)) {
			MultiMatchQuery query = new MultiMatchQuery(String.valueOf(statusReg));

			query.addField(DossierTerm.STATUS_REG);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(notStatusReg)) {
			MultiMatchQuery query = new MultiMatchQuery(String.valueOf(notStatusReg));

			query.addField(DossierTerm.STATUS_REG);

			booleanQuery.add(query, BooleanClauseOccur.MUST_NOT);
		}
		
		if (Validator.isNotNull(keySearch)) {
			BooleanQuery query = new BooleanQueryImpl();

			String[] subQuerieArr = new String[] { DossierTerm.SERVICE_NAME, DossierTerm.APPLICANT_NAME,
					DossierTerm.DOSSIER_NO_SEARCH, DossierTerm.DOSSIER_ID, DossierTerm.DOSSIER_ID_CTN,
					DossierTerm.BRIEF_NOTE };

			query.addTerms(subQuerieArr, keySearch.toLowerCase(), true);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);
	}

	private String getDossierTemplateName(long groupId, String dossierTemplateCode) {
		String name = StringPool.BLANK;

		DossierTemplate template = dossierTemplatePersistence.fetchByG_DT_TPLNO(groupId, dossierTemplateCode);

		if (Validator.isNotNull(template)) {
			name = template.getTemplateName();
		}

		return name;
	}

	private String getDossierNote(String serviceInfoCode, String govAgencyCode, String dossierTemplateNo,
			long groupId) {

		String dossierNote = StringPool.BLANK;

		ServiceInfo serviceInfo = serviceInfoPersistence.fetchBySC_GI(serviceInfoCode, groupId);

		try {

			ServiceConfig config = ServiceConfigLocalServiceUtil.getBySICodeAndGAC(groupId, serviceInfoCode,
					govAgencyCode);

			ProcessOption option = ProcessOptionLocalServiceUtil.getByDTPLNoAndServiceCF(groupId, dossierTemplateNo,
					config.getServiceConfigId());

			dossierNote = option.getInstructionNote();

			if (Validator.isNull(dossierNote)) {
				throw new Exception();
			}

		} catch (Exception e) {
			if (Validator.isNotNull(serviceInfo)) {
				dossierNote = serviceInfo.getProcessText();
			}
		}

		return dossierNote;
	}

	public long countDossierByG_C_GAC_SC_DTNO_NOTDS(long groupId, long companyId, String govAgencyCode,
			String serviceCode, String dossierTemplateNo, String dossierStatus) {
		return dossierPersistence.countByG_C_GAC_SC_DTNO_NOTDS(groupId, companyId, govAgencyCode, serviceCode,
				dossierTemplateNo, dossierStatus);
	}

	private String getServerNo(long groupId) {

		try {
			List<ServerConfig> sc = ServerConfigLocalServiceUtil.getGroupId(groupId);
			_log.info("sc.get(0).getServerNo():"+sc.get(0).getServerNo());
			return sc.get(0).getServerNo();
		} catch (Exception e) {
			return StringPool.BLANK;
		}

	}

	public static final String CLASS_NAME = Dossier.class.getName();

	//TODO: TEST
//	public FullTextQuery getFullTextQueryForKeywordOnAllAnnotatedFields(String keyword) throws ParseException {
//
////	    String[] indexedFields = findAllIndexedFields(entity);
//		String[] indexedFields = new String[] { DossierTerm.DOSSIER_ID, DossierTerm.SERVICE_NAME, DossierTerm.DOSSIER_NO_SEARCH, "dossierIdCTN"};
//
//	    final QueryParser parser = new MultiFieldQueryParser(Version.LUCENE_35, indexedFields, analyzer);
//	    parser.setAllowLeadingWildcard(true);
//
//	    // Add wildcards when not already in search
//	    if (!(StringUtils.containsAny(keyword, new char[] { '*', '?', ':' }) || keyword.startsWith("\"") && keyword.endsWith("\""))) {
//	      keyword = "*" + keyword + "*";
//	    }
//
//	    Query luceneQuery = parser.parse(keyword);
//	    FullTextQuery fullTextQuery = getFullTextQuery(luceneQuery);
//	    return fullTextQuery;
//	  }

	/////////////
	
//	private static List<BooleanQuery> LuceneSearchLike(String keywordsTest, SearchContext searchContext) {
//		List<BooleanQuery> booleanQueries = new ArrayList<BooleanQuery>();
//		BooleanQuery booleanQuerie = BooleanQueryFactoryUtil.create(searchContext);
////		Searcher searcher;
//		if (Validator.isNotNull(keywordsTest)) {
//			try {
////				searcher.searchUsingWildCardQuery(keywordsTest);
////				Query query = (Query) QueryParser.parse(keywordsTest); 
//				String[] subQuerieArr = new String[] { DossierTerm.SERVICE_NAME, DossierTerm.SERVICE_NAME,
//						DossierTerm.DOSSIER_NO_SEARCH, DossierTerm.DOSSIER_ID};
//				
//				
//				//create a term to search file name 
////				@SuppressWarnings("deprecation")
////				TermQuery term = TermQueryFactoryUtil.create(searchContext,DossierTerm.SERVICE_NAME, keywordsTest);
//				
////				TermQuery term1 = new TermQueryImpl(DossierTerm.SERVICE_NAME, keywordsTest); 
//				//create the term query object 
////				Query query = new WildcardQueryImpl(term1.getQueryTerm()); 
////				   return queryParser.parse(searchQuery); 
////			   } 
//
////				String strValueSearch = SpecialCharacterUtils.splitSpecial(keywordsTest.toString());
////				Query query1 = new WildcardQueryImpl(DossierTerm.SERVICE_NAME, "*"+keywordsTest+"*");
//
//				// Search LIKE
//				Map<String, Query> queryMap = query.addTerms(subQuerieArr, keywordsTest, true);
////
////				Iterator<Map.Entry<String, Query>> iterator = queryMap.entrySet().iterator();
////				while (iterator.hasNext()) {
////					Map.Entry<String, Query> entry = iterator.next();
////					_log.info("entryKey Local Search: "+entry.getKey()); 
////					_log.info("entry Local Search: "+entry.getValue()); 
////					BooleanQuery query11 = BooleanQueryFactoryUtil.create(searchContext);
////					query11.add(entry.getValue(), BooleanClauseOccur.SHOULD);
//
//				booleanQuerie.add(query, BooleanClauseOccur.SHOULD);
//				booleanQueries.add(booleanQuerie);
//			} catch (Exception e) {
//
//			}
//		}

//		return booleanQueries;
//	}

//	public static void main(String[] args){
//		List<String> subQueries = new ArrayList<String>();
//		SearchContext searchContext = new SearchContext();
//		LuceneSearchLike("6842", searchContext);
//	}
	
//	  String indexDir = "E:\\Lucene\\Index"; 
//	   String dataDir = "E:\\Lucene\\Data"; 
////	   Searcher searcher; 
//		
//	   public static void main(String[] args) { 
//	      LuceneTester tester; 
//	      try { 
//	         tester = new LuceneTester(); 
//	         tester.searchUsingWildCardQuery("record1*"); 
//	      } catch (IOException e) { 
//	         e.printStackTrace(); 
//	      } catch (ParseException e) { 
//	         e.printStackTrace(); 
//	      } 
//	   } 
//		
//	   private void searchUsingWildCardQuery(String searchQuery) 
//	      throws IOException, ParseException { 
////	      searcher = new Searcher(indexDir); 
//	      long startTime = System.currentTimeMillis(); 
//			
//	      //create a term to search file name 
//	      Term term = new Term(LuceneConstants.FILE_NAME, searchQuery); 
//	      //create the term query object 
//	      Query query = new WildcardQuery(term); 
//	      //do the search 
//	      TopDocs hits = searcher.search(query); 
//	      long endTime = System.currentTimeMillis(); 
//			
//	      System.out.println(hits.totalHits + 
//	         " documents found. Time :" + (endTime - startTime) + "ms"); 
//				
//	      for(ScoreDoc scoreDoc : hits.scoreDocs) { 
//	         Document doc = searcher.getDocument(scoreDoc); 
//	         System.out.println("File: "+ doc.get(LuceneConstants.FILE_PATH)); 
//	      } 
//			
//	      searcher.close(); 
//	   } 

}