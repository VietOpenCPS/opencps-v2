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
import java.util.concurrent.TimeUnit;

import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.datamgt.util.HolidayUtils;
import org.opencps.dossiermgt.action.util.DossierNumberGenerator;
import org.opencps.dossiermgt.constants.ConstantsTerm;
import org.opencps.dossiermgt.constants.DossierActionTerm;
import org.opencps.dossiermgt.constants.DossierStatusConstants;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.exception.NoSuchDossierException;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.model.DossierTemplate;
import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.model.ServiceProcess;
import org.opencps.dossiermgt.service.DossierActionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessOptionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.base.DossierLocalServiceBaseImpl;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
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
import com.liferay.portal.kernel.search.WildcardQuery;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.search.generic.MultiMatchQuery;
import com.liferay.portal.kernel.search.generic.TermRangeQueryImpl;
import com.liferay.portal.kernel.search.generic.WildcardQueryImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PwdGenerator;
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
			boolean online, boolean notification, String applicantNote, int originality, ServiceContext context) throws PortalException {

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
			dossier.setOriginality(originality);
			//Update sampleCount
			ProcessOption option = getProcessOption(serviceCode, govAgencyCode, dossierTemplateNo, groupId);
			if (option != null) {
				dossier.setSampleCount(option.getSampleCount());
			}
			
			dossierPersistence.update(dossier);

			// create DossierFile if it is eForm

//			List<DossierPart> dossierParts = new ArrayList<DossierPart>();
//
//			dossierParts = dossierPartPersistence.findByTP_NO(groupId, dossierTemplateNo);

//			for (DossierPart part : dossierParts) {
//				if (Validator.isNotNull(part.getFormScript()) && part.getPartType() != 2) {
//					String dossierFileUUID = PortalUUIDUtil.generate();

					// TODO HotFix

//					if (groupId != 55301) {
//					if (originality == DossierTerm.ORIGINALITY_DVCTT || originality == DossierTerm.ORIGINALITY_MOTCUA) {
//						dossierFileLocalService.addDossierFile(groupId, dossierId, dossierFileUUID, dossierTemplateNo,
//								part.getPartNo(), part.getFileTemplateNo(), part.getPartName(), StringPool.BLANK, 0l,
//								null, StringPool.BLANK, StringPool.TRUE, context);
//					}
//				}
//			}

//			if (originality == DossierTerm.ORIGINALITY_MOTCUA) {
				LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
				params.put(DossierTerm.GOV_AGENCY_CODE, dossier.getGovAgencyCode());
				params.put(DossierTerm.SERVICE_CODE, dossier.getServiceCode());
				params.put(DossierTerm.DOSSIER_TEMPLATE_NO, dossier.getDossierTemplateNo());
				params.put(DossierTerm.DOSSIER_STATUS, StringPool.BLANK);

				ServiceProcess serviceProcess = null;
				if (option != null) {
					long serviceProcessId = option.getServiceProcessId();
					serviceProcess = serviceProcessPersistence.findByPrimaryKey(serviceProcessId);

					String dossierRef = DossierNumberGenerator.generateDossierNumber(groupId, dossier.getCompanyId(),
							dossierId, option.getProcessOptionId(), serviceProcess.getDossierNoPattern(), params);

					dossier.setDossierNo(dossierRef.trim());
				}
				
				//Update submit date
				now = new Date();
//				dossier.setSubmitDate(now);
				Double durationCount = 0d;
				Integer durationUnit = 0;
				if (serviceProcess != null ) {
					durationCount = serviceProcess.getDurationCount();
					durationUnit = serviceProcess.getDurationUnit();
					_log.info("durationCount: "+durationCount);
					_log.info("durationUnit: "+durationUnit);
//					int durationDays = 0;
//
//					if (durationUnit == 0) {
//						durationDays = durationCount;
//					} else {
//						durationDays = Math.round(durationCount / 8);
//					}
//					Date dueDate = null;
//					if (Validator.isNotNull(durationCount) && durationCount > 0) {
//						dueDate = HolidayUtils.getDueDate(now, durationCount, durationUnit, groupId);
//					}
//					
//					_log.info("dueDate: "+dueDate);
//					if (durationDays > 0) {
//						dueDate = DossierOverDueUtils.calculateEndDate(now, durationDays);
//					}

//					dossier.setDueDate(dueDate);
//					dossier.setReceiveDate(now);
					dossier.setDurationCount(durationCount);
					dossier.setDurationUnit(durationUnit);
//				}
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

			// if (Validator.isNotNull(applicantNote))
			dossier.setApplicantNote(applicantNote);

			dossierPersistence.update(dossier);

		}

		return dossier;
	}

	private final String ADMINISTRATIVE_REGION = "ADMINISTRATIVE_REGION";
	private final String POSTAL_ADMINISTRATIVE_REGION = "VNPOST_CODE";
	private final String GOVERNMENT_AGENCY = "GOVERNMENT_AGENCY";
	private final int DUE_DATE_DEFAULT = 5;

	private String getDictItemName(long groupId, String collectionCode, String itemCode) {

		DictCollection dc = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(collectionCode, groupId);

		DictItem it = DictItemLocalServiceUtil.fetchByF_dictItemCode(itemCode, dc.getPrimaryKey(), groupId);

		return it.getItemName();

	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateDossierOneGate(long dossierId, String applicantName, String applicantIdType,
			String applicantIdNo, Date applicantIdDate, String address, String cityCode, String districtCode,
			String wardCode, String contactName, String contactTelNo, String contactEmail, boolean isSameAsApplicant,
			String delegateName, String delegateIdNo, String delegateTelNo, String delegateEmail,
			String delegateAddress, String delegateCityCode, String delegateDistrictCode, String delegateWardCode,
			String applicantNote, String briefNote, String dossierNo, int viaPostal, String postalServiceCode,
			String postalServiceName, String postalAddress, String postalCityCode, String postalDistrictCode,
			String postalWardCode, String postalTelNo, long dossierActionId, String paymentFee, String paymentFeeNote,
			ServiceContext context) throws PortalException {

		Date now = new Date();

		Dossier dossier = dossierLocalService.fetchDossier(dossierId);

		dossier.setModifiedDate(now);

		// create dossierRegister

		String dossierRegister = PwdGenerator.getPassword(10).toUpperCase();

		dossier.setDossierRegister(dossierRegister);

		dossier.setApplicantName(applicantName);
		dossier.setApplicantIdType(applicantIdType);
		dossier.setApplicantIdNo(applicantIdNo);
		dossier.setApplicantIdDate(applicantIdDate);
		dossier.setAddress(address);

		if (Validator.isNotNull(cityCode)) {
			dossier.setCityCode(cityCode);
			dossier.setCityName(getDictItemName(dossier.getGroupId(), ADMINISTRATIVE_REGION, cityCode));
		}

		if (Validator.isNotNull(districtCode)) {
			dossier.setDistrictCode(districtCode);
			dossier.setDistrictName(getDictItemName(dossier.getGroupId(), ADMINISTRATIVE_REGION, districtCode));
		}

		if (Validator.isNotNull(wardCode)) {
			dossier.setWardCode(wardCode);
			dossier.setWardName(getDictItemName(dossier.getGroupId(), ADMINISTRATIVE_REGION, wardCode));
		}

		dossier.setContactEmail(contactEmail);
		dossier.setContactName(contactName);
		dossier.setContactTelNo(contactTelNo);

		if (isSameAsApplicant) {
			dossier.setDelegateName(applicantName);
			dossier.setDelegateIdNo(applicantIdNo);
			dossier.setDelegateTelNo(contactTelNo);
			dossier.setDelegateAddress(address);
			if (Validator.isNotNull(cityCode)) {
				dossier.setDelegateCityCode(cityCode);
				dossier.setDelegateCityName(getDictItemName(dossier.getGroupId(), ADMINISTRATIVE_REGION, cityCode));
			}

			if (Validator.isNotNull(districtCode)) {
				dossier.setDelegateDistrictCode(districtCode);
				dossier.setDelegateDistrictName(
						getDictItemName(dossier.getGroupId(), ADMINISTRATIVE_REGION, districtCode));
			}

			if (Validator.isNotNull(wardCode)) {
				dossier.setDelegateWardCode(wardCode);
				dossier.setDelegateWardName(getDictItemName(dossier.getGroupId(), ADMINISTRATIVE_REGION, wardCode));
			}
		} else {
			dossier.setDelegateName(delegateName);
			dossier.setDelegateIdNo(delegateIdNo);
			dossier.setDelegateTelNo(delegateTelNo);
			dossier.setDelegateAddress(delegateAddress);

			if (Validator.isNotNull(delegateCityCode)) {
				dossier.setDelegateCityCode(delegateCityCode);
				dossier.setDelegateCityName(
						getDictItemName(dossier.getGroupId(), ADMINISTRATIVE_REGION, delegateCityCode));
			}

			if (Validator.isNotNull(delegateDistrictCode)) {
				dossier.setDelegateDistrictCode(delegateDistrictCode);
				dossier.setDelegateDistrictName(
						getDictItemName(dossier.getGroupId(), ADMINISTRATIVE_REGION, delegateDistrictCode));
			}

			if (Validator.isNotNull(delegateWardCode)) {
				dossier.setDelegateWardCode(delegateWardCode);
				dossier.setDelegateWardName(
						getDictItemName(dossier.getGroupId(), ADMINISTRATIVE_REGION, delegateWardCode));
			}
		}

		dossier.setApplicantNote(applicantNote);
		dossier.setBriefNote(briefNote);
		dossier.setDossierNo(dossierNo);

		// viaPortal: 0 disable, 1: unselected, 2: selected

		if (viaPostal == 1) {
			dossier.setViaPostal(viaPostal);
			dossier.setPostalServiceCode(postalServiceCode);
			dossier.setPostalServiceName(postalServiceName);
			dossier.setPostalAddress(postalAddress);
			dossier.setPostalCityCode(postalCityCode);
			dossier.setPostalCityName(
					getDictItemName(dossier.getGroupId(), ADMINISTRATIVE_REGION, postalCityCode));
			dossier.setPostalDistrictCode(postalDistrictCode);
			dossier.setPostalDistrictName(
					getDictItemName(dossier.getGroupId(), ADMINISTRATIVE_REGION, postalDistrictCode));
			dossier.setPostalWardCode(postalWardCode);
			dossier.setPostalWardName(
					getDictItemName(dossier.getGroupId(), ADMINISTRATIVE_REGION, postalWardCode));
			dossier.setPostalTelNo(postalTelNo);
		}

		String password = PwdGenerator.getPassword(8).toUpperCase();

		dossier.setPassword(password);
		dossier.setOnline(false);

		//LamTV_Process
//		if (dossierActionId > 0) {
//			DossierAction dAction = DossierActionLocalServiceUtil.fetchDossierAction(dossierActionId);
//			ProcessAction process = ProcessActionLocalServiceUtil.getByServiceProcess(dAction.getServiceProcessId(),
//					dAction.getActionCode());
//			if (process != null) {
//				process.setPaymentFee(paymentFee);
//				ProcessActionLocalServiceUtil.updateProcessAction(process);
//			}
//		} else {
//		ServiceProcess serProcess = ServiceProcessLocalServiceUtil.getServiceByCode(dossier.getGroupId(), dossier.getServiceCode(), dossier.getGovAgencyCode(),
//				dossier.getDossierTemplateNo());
//		if (serProcess != null) {
//			ProcessAction process = ProcessActionLocalServiceUtil.getByServiceProcess(serProcess.getServiceProcessId(),
//					String.valueOf(10000));
//			if (process != null) {
//				process.setPaymentFee(paymentFee);
//				ProcessActionLocalServiceUtil.updateProcessAction(process);
//			}
//		}
//		}
		//LamTV_ Process Post payment
		long userId = context.getUserId();
		long groupId = dossier.getGroupId();
		String referenceUid = StringPool.BLANK;
		if (Validator.isNull(referenceUid)) {
			referenceUid = PortalUUIDUtil.generate();
		}
		String govAgencyCode = dossier.getGovAgencyCode();
		String govAgencyName = dossier.getGovAgencyName();
		long paymentAmount = 0;
		String epaymentProfile = StringPool.BLANK;
		String bankInfo = StringPool.BLANK;
//		PaymentFileLocalServiceUtil.createPaymentFiles(userId, groupId, dossierId,
//				referenceUid, govAgencyCode, govAgencyName, applicantName, applicantIdNo, paymentFee, paymentAmount,
//				paymentFeeNote, epaymentProfile, bankInfo, context);
		

		dossierPersistence.update(dossier);

		return dossier;
	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier createDossier(long groupId, String serviceCode, String govAgencyCode, String applicantName,
			String applicantIdType, String applicantIdNo, Date applicantIdDate, String address, String cityCode,
			String districtCode, String wardCode, String contactName, String contactTelNo, String contactEmail,
			boolean isSameAsApplicant, String delegateName, String delegateIdNo, String delegateTelNo,
			String delegateEmail, String delegateAddress, String delegateCityCode, String delegateDistrictCode,
			String delegateWardCode, String applicantNote, String briefNote, String dossierNo, String dossierTemplateNo,
			int viaPostal, String postalServiceCode, String postalServiceName, String postalAddress,
			String postalCityCode, String postalDistrictCode, String postalWardCode, String postalTelNo,
			int originality,
			ServiceContext context) throws PortalException {

		Date now = new Date();

		long dossierId = counterLocalService.increment(Dossier.class.getName());

		long userId = context.getUserId();

		// create referentUid

		String referenceUid = PortalUUIDUtil.generate();

		// create counterId

		int counter = DossierNumberGenerator.counterDossier(userId, groupId);

		Dossier dossier = dossierLocalService.createDossier(dossierId);
		
		//setDossierStatus = new
		
		dossier.setDossierStatus(DossierStatusConstants.NEW);

		dossier.setCreateDate(now);
		dossier.setModifiedDate(now);
		dossier.setCompanyId(context.getCompanyId());
		dossier.setGroupId(groupId);

		dossier.setReferenceUid(referenceUid);
		dossier.setCounter(counter);

		String dossierTemplateName = getDossierTemplateName(groupId, dossierTemplateNo);

		// create dossierRegister

		String dossierRegister = PwdGenerator.getPassword(10).toUpperCase();

		dossier.setDossierRegister(dossierRegister);

		ServiceInfo serviceInfo = serviceInfoLocalService.getByCode(groupId, serviceCode);

		dossier.setServiceCode(serviceCode);
		dossier.setServiceName(serviceInfo.getServiceName());
		dossier.setGovAgencyCode(govAgencyCode);
		dossier.setGovAgencyName(getDictItemName(groupId, GOVERNMENT_AGENCY, govAgencyCode));
		dossier.setApplicantName(applicantName);
		dossier.setApplicantIdType(applicantIdType);
		dossier.setApplicantIdNo(applicantIdNo);
		dossier.setApplicantIdDate(applicantIdDate);
		dossier.setAddress(address);
		dossier.setDossierTemplateNo(dossierTemplateNo);
		dossier.setDossierTemplateName(dossierTemplateName);
		dossier.setOriginality(originality);
		

		if (Validator.isNotNull(cityCode)) {
		dossier.setCityCode(cityCode);
		dossier.setCityName(getDictItemName(groupId, ADMINISTRATIVE_REGION, cityCode));
		}

		if (Validator.isNotNull(districtCode)) {
		dossier.setDistrictCode(districtCode);
		dossier.setDistrictName(getDictItemName(groupId, ADMINISTRATIVE_REGION, districtCode));
		}

		if (Validator.isNotNull(wardCode)) {
		dossier.setWardCode(wardCode);
		dossier.setWardName(getDictItemName(groupId, ADMINISTRATIVE_REGION, wardCode));
		}

		dossier.setContactEmail(contactEmail);
		dossier.setContactName(contactName);
		dossier.setContactTelNo(contactTelNo);

		if (isSameAsApplicant) {
			dossier.setDelegateName(applicantName);
			dossier.setDelegateIdNo(applicantIdNo);
			dossier.setDelegateTelNo(contactTelNo);
			dossier.setDelegateAddress(address);
			if (Validator.isNotNull(cityCode)) {
			dossier.setDelegateCityCode(cityCode);
			dossier.setDelegateCityName(getDictItemName(groupId, ADMINISTRATIVE_REGION, cityCode));
			}

			if (Validator.isNotNull(districtCode)) {
			dossier.setDelegateDistrictCode(districtCode);
			dossier.setDelegateDistrictName(getDictItemName(groupId, ADMINISTRATIVE_REGION, districtCode));
			}

			if (Validator.isNotNull(wardCode)) {
			dossier.setDelegateWardCode(wardCode);
			dossier.setDelegateWardName(getDictItemName(groupId, ADMINISTRATIVE_REGION, wardCode));
			}
		} else {
			dossier.setDelegateName(delegateName);
			dossier.setDelegateIdNo(delegateIdNo);
			dossier.setDelegateTelNo(delegateTelNo);
			dossier.setDelegateAddress(delegateAddress);

			if (Validator.isNotNull(delegateCityCode)) {
			dossier.setDelegateCityCode(delegateCityCode);
			dossier.setDelegateCityName(getDictItemName(groupId, ADMINISTRATIVE_REGION, delegateCityCode));
			}

			if (Validator.isNotNull(delegateDistrictCode)) {
			dossier.setDelegateDistrictCode(delegateDistrictCode);
			dossier.setDelegateDistrictName(getDictItemName(groupId, ADMINISTRATIVE_REGION, delegateDistrictCode));
			}

			if (Validator.isNotNull(delegateWardCode)) {
			dossier.setDelegateWardCode(delegateWardCode);
			dossier.setDelegateWardName(getDictItemName(groupId, ADMINISTRATIVE_REGION, delegateWardCode));
		}
		}

		ProcessOption option = getProcessOption(serviceCode, govAgencyCode, dossierTemplateNo, groupId);

		long serviceProcessId = option.getServiceProcessId();

		ServiceProcess serviceProcess = serviceProcessPersistence.findByPrimaryKey(serviceProcessId);
		
		double durationCount = 0;
		int durationUnit = 0;
		if (serviceProcess != null ) {
			durationCount = serviceProcess.getDurationCount();
			durationUnit = serviceProcess.getDurationUnit();
		}

		_log.info("durationCount: "+durationCount);
		_log.info("durationUnit: "+durationUnit);
		
		Date dueDate = HolidayUtils.getDueDate(now, durationCount, durationUnit, groupId);

		// set dueDate
		dossier.setDueDate(dueDate);

		// set receivedDate
		dossier.setReceiveDate(now);

		dossier.setDossierNote(option.getInstructionNote());
		dossier.setSubmissionNote(option.getSubmissionNote());
		dossier.setApplicantNote(applicantNote);
		dossier.setBriefNote(briefNote);
		//dossier.setDossierNo(dossierNo);

		// viaPortal: 0 disable, 1: unselected, 2: selected
//		if (viaPostal == 2) {
		//LamTV_Hot fix
		if (viaPostal == 1) {
			dossier.setViaPostal(viaPostal);
			dossier.setPostalServiceCode(postalServiceCode);
			dossier.setPostalServiceName(postalServiceName);
			dossier.setPostalAddress(postalAddress);
			dossier.setPostalCityCode(postalCityCode);
			dossier.setPostalCityName(getDictItemName(groupId, ADMINISTRATIVE_REGION, postalCityCode));
			dossier.setPostalDistrictCode(postalDistrictCode);
			dossier.setPostalDistrictName(getDictItemName(groupId, ADMINISTRATIVE_REGION, postalDistrictCode));
			dossier.setPostalWardCode(postalWardCode);
			dossier.setPostalWardName(getDictItemName(groupId, ADMINISTRATIVE_REGION, postalWardCode));
			dossier.setPostalTelNo(postalTelNo);
		}

		String password = PwdGenerator.getPassword(8).toUpperCase();

		dossier.setPassword(password);
		dossier.setOnline(false);

		dossierPersistence.update(dossier);

		// init DossierFile
		List<DossierPart> dossierParts = new ArrayList<DossierPart>();

		dossierParts = dossierPartPersistence.findByTP_NO(groupId, dossierTemplateNo);

		for (DossierPart part : dossierParts) {
			if (Validator.isNotNull(part.getFormScript()) && part.getPartType() != 2) {
				String dossierFileUUID = PortalUUIDUtil.generate();

				dossierFileLocalService.addDossierFile(groupId, dossierId, dossierFileUUID, dossierTemplateNo,
						part.getPartNo(), part.getFileTemplateNo(), part.getPartName(), StringPool.BLANK, 0l, null,
						StringPool.BLANK, StringPool.TRUE, context);
			}
		}
		
		LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
		params.put(DossierTerm.GOV_AGENCY_CODE, dossier.getGovAgencyCode());
		params.put(DossierTerm.SERVICE_CODE, dossier.getServiceCode());
		params.put(DossierTerm.DOSSIER_TEMPLATE_NO, dossier.getDossierTemplateNo());
		params.put(DossierTerm.DOSSIER_STATUS, StringPool.BLANK);

		String dossierRef = DossierNumberGenerator.generateDossierNumber(groupId, dossier.getCompanyId(),
				dossierId, option.getProcessOptionId(), serviceProcess.getDossierNoPattern(), params);


		//LamTV_ Process Post payment
//		String referenceUid = StringPool.BLANK;
//		if (Validator.isNull(referenceUid)) {
//			referenceUid = PortalUUIDUtil.generate();
//		}
//		String govAgencyCode = dossier.getGovAgencyCode();
		String govAgencyName = dossier.getGovAgencyName();
		String paymentNote = StringPool.BLANK;
		String epaymentProfile = StringPool.BLANK;
		String bankInfo = StringPool.BLANK;
		String paymentFee = StringPool.BLANK;
		long paymentAmount = 0;
		if (serviceProcess != null) {
			paymentFee = serviceProcess.getPaymentFee();
			_log.info("paymentFee: "+paymentFee);
		}
//		PaymentFileLocalServiceUtil.createPaymentFiles(userId, groupId, dossierId, referenceUid, govAgencyCode,
//				govAgencyName, applicantName, applicantIdNo, paymentFee, paymentAmount, paymentNote, epaymentProfile,
//				bankInfo, context);

		_log.info("SERVICEPROCESS"+ serviceProcess.getDossierNoPattern());
		
		_log.info("DOSSIER_NO_"+ dossierRef);

		dossier.setDossierNo(dossierRef.trim());

		dossierPersistence.update(dossier);

		return dossier;
	}

	private ProcessOption getProcessOption(String serviceInfoCode, String govAgencyCode, String dossierTemplateNo,
			long groupId) throws PortalException {

		ServiceConfig config = ServiceConfigLocalServiceUtil.getBySICodeAndGAC(groupId, serviceInfoCode, govAgencyCode);

		return ProcessOptionLocalServiceUtil.getByDTPLNoAndServiceCF(groupId, dossierTemplateNo,
				config.getServiceConfigId());
	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier postDossier(long groupId, long dossierId, String referenceUid, int counter, String serviceCode,
			String serviceName, String govAgencyCode, String govAgencyName, String applicantName,
			String applicantIdType, String applicantIdNo, Date applicantIdDate, String address, String cityCode,
			String cityName, String districtCode, String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String contactEmail, String dossierTemplateNo, String password,
			int viaPostal, String postalAddress, String postalCityCode, String postalCityName, String postalTelNo,
			boolean online, boolean notification, String applicantNote, int originality, ServiceContext context) throws PortalException {

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
			dossier.setOriginality(originality);
			
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

			// if (Validator.isNotNull(applicantNote))
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

		/*
		 * if (Validator.isNull(dossier.getSubmitDate())) {
		 * dossier.setSubmitDate(now); }
		 */
		if (dossier.getDossierStatus().contentEquals(DossierStatusConstants.NEW)) {
			dossier.setSubmitDate(now);
		}

		// long dActionId = 0;
		// String stepCode = StringPool.BLANK;
		// long serviceProcessId = 0;
		// String lockState = StringPool.BLANK;
		// if (dossier != null) {
		// dActionId = dossier.getDossierActionId();
		// }
		// if (dActionId > 0) {
		// DossierAction dAction =
		// DossierActionLocalServiceUtil.fetchDossierAction(dActionId);
		// if (dAction != null) {
		// stepCode = dAction.getStepCode();
		// serviceProcessId = dAction.getServiceProcessId();
		// }
		// }
		// if (Validator.isNotNull(stepCode) && serviceProcessId > 0) {
		// ProcessStep proStep =
		// ProcessStepLocalServiceUtil.fetchBySC_GID(stepCode, groupId,
		// serviceProcessId);
		// if (proStep != null) {
		// lockState = proStep.getLockState();
		// }
		// }
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

//		List<PaymentFile> lsPF = paymentFileLocalService.getByDossierId(id);

//		for (PaymentFile pf : lsPF) {
//			if (pf.getIsNew()) {
//				pf.setIsNew(false);
//
//				paymentFileLocalService.updatePaymentFile(pf);
//			}
//		}

		return dossier;
	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateStatus(long groupId, long id, String refId, String status, String statusText, String subStatus,
			String subStatusText, String lockState, String stepInstruction, ServiceContext context)
			throws PortalException {

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
		dossier.setDossierNote(stepInstruction);

		/*
		 * if (status.equalsIgnoreCase(DossierStatusConstants.RECEIVING)) {
		 * dossier.setReceiveDate(now); }
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
	public Dossier updateProcessDate(long groupId, long id, String refId, Date date, ServiceContext context)
			throws PortalException {

		Date now = new Date();

		Dossier dossier = null;

		if (id != 0) {
			dossier = dossierPersistence.fetchByPrimaryKey(id);
		} else {
			dossier = dossierPersistence.fetchByG_REF(groupId, refId);
		}

		dossier.setModifiedDate(now);

		dossier.setProcessDate(date);

		return dossierPersistence.update(dossier);
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

		return dossierPersistence.update(dossier);
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

		return dossierPersistence.update(dossier);
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

		return dossierPersistence.update(dossier);
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

		return dossierPersistence.update(dossier);
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

		return dossierPersistence.update(dossier);
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

		// dossier.setCancellingDate(date);

		dossier.setEndorsementDate(date);

		dossier.setSubmitting(true);

		return dossierPersistence.update(dossier);
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

		return dossierPersistence.update(dossier);
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

		return dossierPersistence.update(dossier);
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
		// Document document = null;

		Indexer<Dossier> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Dossier.class);

		SearchContext searchContext = new SearchContext();
		searchContext.setCompanyId(companyId);

		// SearchContext searchContext =
		// SearchContextFactory.getInstance(request);

		searchContext.setEnd(QueryUtil.ALL_POS);
		searchContext.setKeywords(StringPool.BLANK);
		searchContext.setStart(QueryUtil.ALL_POS);
		// searchContext.set

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
		String top = GetterUtil.getString(params.get(DossierTerm.TOP));
		String owner = GetterUtil.getString(params.get(DossierTerm.OWNER));
		String submitting = GetterUtil.getString(params.get(DossierTerm.SUBMITTING));
		int year = GetterUtil.getInteger(params.get(DossierTerm.YEAR));
		int month = GetterUtil.getInteger(params.get(DossierTerm.MONTH));
		long userId = GetterUtil.getLong(params.get(DossierTerm.USER_ID));
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
		String online = GetterUtil.getString(params.get(DossierTerm.ONLINE));
		String originality = GetterUtil.getString(params.get(DossierTerm.ORIGINALLITY));
		String assigned = GetterUtil.getString(params.get(DossierTerm.ASSIGNED));
		//LamTV_ADD
		String statusStep = GetterUtil.getString(params.get(DossierTerm.DOSSIER_STATUS_STEP));
		String subStatusStep = GetterUtil.getString(params.get(DossierTerm.DOSSIER_SUBSTATUS_STEP));
		String permission = GetterUtil.getString(params.get(DossierTerm.MAPPING_PERMISSION));
		String domain = GetterUtil.getString(params.get(DossierTerm.DOMAIN_CODE));
		
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

		//Search follow params default
		BooleanQuery booleanCommon = processSearchCommon(keywords, secetKey, groupId, owner, userId, follow, step,
				template, booleanQuery);
		// Search follow param input
		BooleanQuery booleanInput = processSearchInput(status, subStatus, state, online, submitting, agency, service,
				year, month, dossierNo, certificateNo, strDossierActionId, fromReceiveDate, toReceiveDate, certNo,
				fromCertDate, toCertDate, fromSubmitDate, toSubmitDate, notState, statusReg, notStatusReg, originality,
				assigned, statusStep, subStatusStep, permission, domain, booleanCommon);
		
		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.search(searchContext, booleanInput);
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
		String online = GetterUtil.getString(params.get(DossierTerm.ONLINE));
		String follow = GetterUtil.getString(params.get(DossierTerm.FOLLOW));
		String top = GetterUtil.getString(params.get(DossierTerm.TOP));
		String owner = GetterUtil.getString(params.get(DossierTerm.OWNER));
		String submitting = GetterUtil.getString(params.get(DossierTerm.SUBMITTING));
		long userId = GetterUtil.getLong(params.get(DossierTerm.USER_ID));

		int year = GetterUtil.getInteger(params.get(DossierTerm.YEAR));
		int month = GetterUtil.getInteger(params.get(DossierTerm.MONTH));

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
		String originality = GetterUtil.getString(params.get(DossierTerm.ORIGINALLITY));
		String assigned = GetterUtil.getString(params.get(DossierTerm.ASSIGNED));
		//LamTV_ADD
		String statusStep = GetterUtil.getString(params.get(DossierTerm.DOSSIER_STATUS_STEP));
		String subStatusStep = GetterUtil.getString(params.get(DossierTerm.DOSSIER_SUBSTATUS_STEP));
		String permission = GetterUtil.getString(params.get(DossierTerm.MAPPING_PERMISSION));
		String domain = GetterUtil.getString(params.get(DossierTerm.DOMAIN_CODE));
		
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

		//Search follow params default
		BooleanQuery booleanCommon = processSearchCommon(keywords, secetKey, groupId, owner, userId, follow, step,
				template, booleanQuery);
		// Search follow param input
		BooleanQuery booleanInput = processSearchInput(status, subStatus, state, online, submitting, agency, service,
				year, month, dossierNo, certificateNo, strDossierActionId, fromReceiveDate, toReceiveDate, certNo,
				fromCertDate, toCertDate, fromSubmitDate, toSubmitDate, notState, statusReg, notStatusReg, originality,
				assigned, statusStep, subStatusStep, permission, domain, booleanCommon);

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanInput);
	}

	private BooleanQuery processSearchCommon(String keywords, String secetKey, String groupId, String owner,
			long userId, String follow, String step, String template, BooleanQuery booleanQuery) throws ParseException {
		// LamTV: Process search LIKE
		if (Validator.isNotNull(keywords)) {
			BooleanQuery queryBool = new BooleanQueryImpl();
			String[] subQuerieArr = new String[] { DossierTerm.SERVICE_NAME, DossierTerm.APPLICANT_NAME,
					DossierTerm.DOSSIER_NO_SEARCH, DossierTerm.DOSSIER_ID_CTN, DossierTerm.BRIEF_NOTE };

			String[] keywordArr = keywords.split(StringPool.SPACE);
			for (String fieldSearch : subQuerieArr) {
				BooleanQuery query = new BooleanQueryImpl();
				for (String key : keywordArr) {
					WildcardQuery wildQuery = new WildcardQueryImpl(fieldSearch,
							StringPool.STAR + key.toLowerCase() + StringPool.STAR);
					query.add(wildQuery, BooleanClauseOccur.MUST);
				}
				queryBool.add(query, BooleanClauseOccur.SHOULD);
			}
			booleanQuery.add(queryBool, BooleanClauseOccur.MUST);
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

		if (Validator.isNotNull(step)) {
			String[] stepArr = StringUtil.split(step);

			if (stepArr != null && stepArr.length > 0) {
				BooleanQuery subQuery = new BooleanQueryImpl();
				for (int i = 0; i < stepArr.length; i++) {
					MultiMatchQuery query = new MultiMatchQuery(stepArr[i]);
					query.addField(DossierTerm.STEP_CODE);
					subQuery.add(query, BooleanClauseOccur.SHOULD);
				}
				booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
			} else {
				MultiMatchQuery query = new MultiMatchQuery(step);
				query.addFields(DossierTerm.STEP_CODE);
				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
		}

		if (Validator.isNotNull(template)) {
			MultiMatchQuery query = new MultiMatchQuery(template);
			query.addFields(DossierTerm.DOSSIER_TEMPLATE_NO);
			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		//DossierActionId = 0
		MultiMatchQuery queryDossierAction = new MultiMatchQuery(String.valueOf(0));
		queryDossierAction.addField(DossierTerm.DOSSIER_ACTION_ID);
		booleanQuery.add(queryDossierAction, BooleanClauseOccur.MUST_NOT);
		//OriginDossierId = 0
		MultiMatchQuery queryOrigin = new MultiMatchQuery(String.valueOf(0));
		queryOrigin.addField(DossierTerm.ORIGIN_DOSSIER_ID);
		booleanQuery.add(queryOrigin, BooleanClauseOccur.MUST);

		return booleanQuery;
	}

	private BooleanQuery processSearchInput(String status, String subStatus, String state, String online,
			String submitting, String agency, String service, int year, int month, String dossierNo,
			String certificateNo, String strDossierActionId, String fromReceiveDate, String toReceiveDate,
			String certNo, String fromCertDate, String toCertDate, String fromSubmitDate, String toSubmitDate,
			String notState, Long statusReg, Long notStatusReg, String originality, String assigned,
			String statusStep, String subStatusStep, String permission, String domain, BooleanQuery booleanQuery) throws ParseException {

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

		if (Validator.isNotNull(state)) {
			if (state.equals(ConstantsTerm.CANCELLING)) {

				BooleanQuery subQuery = new BooleanQueryImpl();

				MultiMatchQuery query1 = new MultiMatchQuery(String.valueOf(0));

				query1.addField(DossierTerm.CANCELLING_DATE_TIMESTAMP);

				MultiMatchQuery query2 = new MultiMatchQuery(ConstantsTerm.CANCELLED);

				query2.addField(DossierTerm.DOSSIER_STATUS);

				subQuery.add(query1, BooleanClauseOccur.MUST_NOT);

				subQuery.add(query2, BooleanClauseOccur.MUST_NOT);

				booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
			}

			if (state.equals(ConstantsTerm.CORRECTING)) {

				MultiMatchQuery query = new MultiMatchQuery(String.valueOf(0));
				query.addField(DossierTerm.CORRECTING_DATE_TIMESTAMP);
				booleanQuery.add(query, BooleanClauseOccur.MUST_NOT);
			}
			
			if (state.equals(ConstantsTerm.ENDORSEMENT)) {
				MultiMatchQuery query = new MultiMatchQuery(String.valueOf(0));
				query.addField(DossierTerm.ENDORSEMENT_DATE_TIMESTAMP);
				booleanQuery.add(query, BooleanClauseOccur.MUST_NOT);
			}
		}

		if (Validator.isNotNull(online)) {
			MultiMatchQuery query = new MultiMatchQuery(String.valueOf(online));
			query.addField(DossierTerm.ONLINE);
			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(submitting) && Boolean.parseBoolean(submitting)) {

			MultiMatchQuery query = new MultiMatchQuery(String.valueOf(submitting));

			query.addField(DossierTerm.SUBMITTING);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
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

		if (Validator.isNotNull(dossierNo)) {

			String[] keyDossier = dossierNo.split(StringPool.SPACE);
			for (String key : keyDossier) {
				MultiMatchQuery query = new MultiMatchQuery(key);
				query.addFields(new String[] { DossierTerm.DOSSIER_NO_SEARCH, DossierTerm.DOSSIER_ID_CTN });
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

		String fromReceiveDateFilter = fromReceiveDate + ConstantsTerm.HOUR_START;
		String toReceiveDateFilter = toReceiveDate + ConstantsTerm.HOUR_END;

		if (Validator.isNotNull(fromReceiveDate)) {
			if (Validator.isNotNull(toReceiveDate)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(DossierTerm.RECEIVE_DATE,
						fromReceiveDateFilter, toReceiveDateFilter, true, true);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			} else {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(DossierTerm.RECEIVE_DATE,
						fromReceiveDateFilter, toReceiveDateFilter, true, false);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			}
		} else {
			if (Validator.isNotNull(toReceiveDate)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(DossierTerm.RECEIVE_DATE,
						fromReceiveDateFilter, toReceiveDateFilter, false, true);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			}
		}

		if (Validator.isNotNull(certNo)) {
			MultiMatchQuery query = new MultiMatchQuery(String.valueOf(certNo));
			query.addField(DossierTerm.CERT_NO_SEARCH);
			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		String fromCertDateFilter = fromCertDate + ConstantsTerm.HOUR_START;
		String toCertDateFilter = toCertDate + ConstantsTerm.HOUR_END;

		if (Validator.isNotNull(fromCertDate)) {
			if (Validator.isNotNull(toCertDate)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(DossierTerm.CERT_DATE, fromCertDateFilter,
						toCertDateFilter, true, true);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			} else {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(DossierTerm.CERT_DATE, fromCertDateFilter,
						toCertDateFilter, true, false);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			}
		} else {
			if (Validator.isNotNull(toCertDate)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(DossierTerm.CERT_DATE, fromCertDateFilter,
						toCertDateFilter, false, true);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			}
		}

		String fromSubmitDateFilter = fromSubmitDate + ConstantsTerm.HOUR_START;
		String toSubmitDateFilter = toSubmitDate + ConstantsTerm.HOUR_END;

		if (Validator.isNotNull(fromSubmitDate)) {
			if (Validator.isNotNull(toSubmitDate)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(DossierTerm.SUBMIT_DATE,
						fromSubmitDateFilter, toSubmitDateFilter, true, true);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			} else {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(DossierTerm.SUBMIT_DATE,
						fromSubmitDateFilter, toSubmitDateFilter, true, false);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			}
		} else {
			if (Validator.isNotNull(toSubmitDate)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(DossierTerm.RECEIVE_DATE,
						fromSubmitDateFilter, toSubmitDateFilter, false, true);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			}
		}

		if (Validator.isNotNull(notState)) {
			// LamTV: Case not have flag cancel
			if (notState.equals(ConstantsTerm.CANCELLING)) {
				MultiMatchQuery query = new MultiMatchQuery(String.valueOf(0));
				query.addField(DossierTerm.CANCELLING_DATE_TIMESTAMP);
				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
			// LamTV: Case not have flag correct and endorsement
			if (notState.contains(ConstantsTerm.CORRECTING)) {
				MultiMatchQuery query = new MultiMatchQuery(String.valueOf(0));
				query.addField(DossierTerm.CORRECTING_DATE_TIMESTAMP);
				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
			if (notState.contains(ConstantsTerm.ENDORSEMENT)) {
				MultiMatchQuery query = new MultiMatchQuery(String.valueOf(0));
				query.addField(DossierTerm.ENDORSEMENT_DATE_TIMESTAMP);
				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
		}

		// LamTV: Add process case abnormal
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

		// LamTV: Process originality and assigned
		if (Validator.isNotNull(originality)) {
			String[] originalArr = StringUtil.split(originality);

			if (originalArr != null && originalArr.length > 0) {
				BooleanQuery subQuery = new BooleanQueryImpl();
				for (int i = 0; i < originalArr.length; i++) {
					MultiMatchQuery query = new MultiMatchQuery(originalArr[i]);
					query.addField(DossierTerm.ORIGINALLITY);
					subQuery.add(query, BooleanClauseOccur.SHOULD);
				}
				booleanQuery.add(subQuery, BooleanClauseOccur.MUST);

			} else {
				MultiMatchQuery query = new MultiMatchQuery(originality);
				query.addFields(DossierTerm.ORIGINALLITY);
				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
		}

		if (Validator.isNotNull(assigned)) {
			MultiMatchQuery query = new MultiMatchQuery(String.valueOf(assigned));
			query.addField(DossierTerm.ASSIGNED);
			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		//LamTV_Test
		if (Validator.isNotNull(statusStep)) {
			String[] statusStepArr = StringUtil.split(statusStep);

			if (statusStepArr != null && statusStepArr.length > 0) {
				BooleanQuery subQuery = new BooleanQueryImpl();
				for (int i = 0; i < statusStepArr.length; i++) {
					MultiMatchQuery query = new MultiMatchQuery(statusStepArr[i]);
					query.addField(DossierTerm.DOSSIER_STATUS);
					subQuery.add(query, BooleanClauseOccur.SHOULD);
				}
				booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
			} else {
				MultiMatchQuery query = new MultiMatchQuery(statusStep);
				query.addFields(DossierTerm.DOSSIER_STATUS);
				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
		}

		if (Validator.isNotNull(subStatusStep)) {
			String[] subStatusStepArr = StringUtil.split(subStatusStep);

			if (subStatusStepArr != null && subStatusStepArr.length > 0) {
				BooleanQuery subQuery = new BooleanQueryImpl();
				for (int i = 0; i < subStatusStepArr.length; i++) {
					String subStatusStepDetail = subStatusStepArr[i];
					if (!"empty".equals(subStatusStepDetail)) {
						MultiMatchQuery query = new MultiMatchQuery(subStatusStepArr[i]);
						query.addField(DossierTerm.DOSSIER_SUB_STATUS);
						subQuery.add(query, BooleanClauseOccur.SHOULD);
					}
				}
				booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
			} else {
				if (!"empty".equals(subStatusStep)) {
					MultiMatchQuery query = new MultiMatchQuery(subStatusStep);
					query.addFields(DossierTerm.DOSSIER_SUB_STATUS);
					booleanQuery.add(query, BooleanClauseOccur.MUST);
				}
			}
		}

//		_log.info("Permission: " + permission);
		if (Validator.isNotNull(permission)) {
			MultiMatchQuery query = new MultiMatchQuery(permission);
			query.addField(DossierTerm.MAPPING_PERMISSION);
			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(domain)) {
			MultiMatchQuery query = new MultiMatchQuery(domain);

			query.addFields(DossierTerm.DOMAIN_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}
		
		return booleanQuery;
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
			_log.info("sc.get(0).getServerNo():" + sc.get(0).getServerNo());
			return sc.get(0).getServerNo();
		} catch (Exception e) {
			return StringPool.BLANK;
		}

	}

	// TrungDK: Process
	public List<Dossier> getDossierByG_NOTO_DS(int originality, String dossierStatus) {
		return dossierPersistence.findByNOTO_DS(originality, dossierStatus);
	}
	
	public void removeDossierByG_NOTO_DS(int originality, String dossierStatus) {
		List<Dossier> lstDossiers = dossierPersistence.findByNOTO_DS(originality, dossierStatus);
		Date now = new Date();
		
		for (Dossier dossier : lstDossiers) {
		    long diffInMillies = Math.abs(now.getTime() - dossier.getCreateDate().getTime());
		    long diff = TimeUnit.MILLISECONDS.convert(diffInMillies, TimeUnit.MINUTES);
		    
			try {
				if (diff > DossierTerm.GARBAGE_COLLECTOR_TIME)
					dossierPersistence.remove(dossier.getDossierId());
			} catch (NoSuchDossierException e) {
//				e.printStackTrace();
			}
		}
//		dossierPersistence.removeByNOTO_DS(originality, dossierStatus);
	}
	
	public static final String CLASS_NAME = Dossier.class.getName();

	//LamTV: Process get Dossier by dossierId, govAgency, serviceProcess
	public Dossier getByIdAndGovService(long groupId, String serviceCode, String govAgencyCode, long dossierId) {

		return dossierPersistence.fetchByF_GID_GOV_DID(groupId, govAgencyCode, serviceCode, dossierId);
	}

	public List<Dossier> getByNotO_DS_SC_GC(long groupId, int originality, String dossierStatus, String serviceCode, String govAgencyCode) {
		return dossierPersistence.findByG_NOTO_DS_SC_GC(groupId, originality, dossierStatus, serviceCode, govAgencyCode);
	}

	//LamTV_Process update dossier
	public Dossier initUpdateDossier(long groupId, long id, String applicantName, String applicantIdType,
			String applicantIdNo, String applicantIdDate, String address, String cityCode, String cityName,
			String districtCode, String districtName, String wardCode, String wardName, String contactName,
			String contactTelNo, String contactEmail, String dossierTemplateNo, int viaPostal, String postalAddress,
			String postalCityCode, String postalCityName, String postalTelNo, String applicantNote,
			boolean isSameAsApplicant, String delegateName, String delegateIdNo, String delegateTelNo,
			String delegateEmail, String delegateAddress, String delegateCityCode, String delegateDistrictCode,
			String delegateWardCode, Long sampleCount, ServiceContext serviceContext) {

		Date now = new Date();
		long userId = serviceContext.getUserId();

		User auditUser = userPersistence.fetchByPrimaryKey(userId);
		Dossier dossier = dossierPersistence.fetchByPrimaryKey(id);

		dossier.setModifiedDate(now);
		dossier.setUserId(userId);
		dossier.setUserName(auditUser.getFullName());
		//
		if (Validator.isNotNull(applicantName))
			dossier.setApplicantName(applicantName);
		if (Validator.isNotNull(applicantIdType))
			dossier.setApplicantIdType(applicantIdType);
		if (Validator.isNotNull(applicantIdNo))
			dossier.setApplicantIdNo(applicantIdNo);
		if (Validator.isNotNull(applicantIdDate))
			dossier.setApplicantIdDate(
					APIDateTimeUtils.convertStringToDate(applicantIdDate, APIDateTimeUtils._NORMAL_PARTTERN));
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
		if (Validator.isNotNull(sampleCount))
			dossier.setSampleCount(sampleCount);

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

		if (isSameAsApplicant) {
			dossier.setDelegateName(applicantName);
			dossier.setDelegateIdNo(applicantIdNo);
			dossier.setDelegateTelNo(contactTelNo);
			dossier.setDelegateAddress(address);
			if (Validator.isNotNull(cityCode)) {
				dossier.setDelegateCityCode(cityCode);
				dossier.setDelegateCityName(getDictItemName(dossier.getGroupId(), ADMINISTRATIVE_REGION, cityCode));
			}

			if (Validator.isNotNull(districtCode)) {
				dossier.setDelegateDistrictCode(districtCode);
				dossier.setDelegateDistrictName(
						getDictItemName(dossier.getGroupId(), ADMINISTRATIVE_REGION, districtCode));
			}

			if (Validator.isNotNull(wardCode)) {
				dossier.setDelegateWardCode(wardCode);
				dossier.setDelegateWardName(getDictItemName(dossier.getGroupId(), ADMINISTRATIVE_REGION, wardCode));
			}
		} else {
			dossier.setDelegateName(delegateName);
			dossier.setDelegateIdNo(delegateIdNo);
			dossier.setDelegateTelNo(delegateTelNo);
			dossier.setDelegateAddress(delegateAddress);

			if (Validator.isNotNull(delegateCityCode)) {
				dossier.setDelegateCityCode(delegateCityCode);
				dossier.setDelegateCityName(
						getDictItemName(dossier.getGroupId(), ADMINISTRATIVE_REGION, delegateCityCode));
			}

			if (Validator.isNotNull(delegateDistrictCode)) {
				dossier.setDelegateDistrictCode(delegateDistrictCode);
				dossier.setDelegateDistrictName(
						getDictItemName(dossier.getGroupId(), ADMINISTRATIVE_REGION, delegateDistrictCode));
			}

			if (Validator.isNotNull(delegateWardCode)) {
				dossier.setDelegateWardCode(delegateWardCode);
				dossier.setDelegateWardName(
						getDictItemName(dossier.getGroupId(), ADMINISTRATIVE_REGION, delegateWardCode));
			}
		}

		dossier.setApplicantNote(applicantNote);

		return dossierPersistence.update(dossier);

	}
	
	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateApplicantInfo(long dossierId, 
			Date applicantIdDate,
			String applicantIdNo,
			String applicantIdType,
			String applicantName,
			String address,
			String cityCode,
			String cityName,
			String districtCode,
			String districtName,
			String wardCode,
			String wardName,
			String contactEmail,
			String contactTelNo) throws NoSuchDossierException {
		Dossier dossier = dossierPersistence.findByPrimaryKey(dossierId);
		
		dossier.setApplicantIdDate(applicantIdDate);
		dossier.setApplicantIdNo(applicantIdNo);
		dossier.setApplicantIdType(applicantIdType);
		dossier.setApplicantName(applicantName);
		dossier.setAddress(address);
		dossier.setCityCode(cityCode);
		dossier.setCityName(cityName);
		dossier.setDistrictCode(districtCode);
		dossier.setDistrictName(districtName);
		dossier.setWardCode(wardCode);
		dossier.setWardName(wardName);
		dossier.setContactEmail(contactEmail);
		dossier.setContactTelNo(contactTelNo);
		
		dossier.setDelegateAddress(address);
		dossier.setDelegateCityCode(cityCode);
		dossier.setDelegateCityName(cityName);
		dossier.setDelegateDistrictCode(districtCode);
		dossier.setDelegateDistrictName(districtName);
		dossier.setDelegateEmail(contactEmail);
		dossier.setDelegateIdNo(applicantIdNo);
		dossier.setDelegateName(applicantName);
		dossier.setDelegateTelNo(contactTelNo);
		dossier.setDelegateWardCode(wardCode);
		dossier.setDelegateWardName(wardName);
			
		return dossierPersistence.update(dossier);
	}
	
	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateDossier(long dossierId, JSONObject obj) throws NoSuchDossierException {
		_log.info("Object dossier update: " + obj.toJSONString());
		Dossier dossier = dossierPersistence.findByPrimaryKey(dossierId);
		
		if (obj.has(DossierTerm.DOSSIER_NOTE)) {
			if (!obj.getString(DossierTerm.DOSSIER_NOTE).equals(dossier.getDossierNote())) {
				dossier.setDossierNote(obj.getString(DossierTerm.DOSSIER_NOTE));
			}
		}
		if (obj.has(DossierTerm.EXTEND_DATE)) {
			if (dossier.getExtendDate() == null || obj.getLong(DossierTerm.EXTEND_DATE) != dossier.getExtendDate().getTime()) {
				dossier.setExtendDate(new Date(obj.getLong(DossierTerm.EXTEND_DATE)));
			}
		}
		if (obj.has(DossierTerm.DOSSIER_NO)) {
			_log.info("Sync dossier no");
			if (Validator.isNotNull(obj.getString(DossierTerm.DOSSIER_NO)) && !obj.getString(DossierTerm.DOSSIER_NO).equals(dossier.getDossierNo())) {
				_log.info("Sync set dossier no");
				dossier.setDossierNo(obj.getString(DossierTerm.DOSSIER_NO));
			}
		}
		if (obj.has(DossierTerm.DUE_DATE)) {
			if (dossier.getDueDate() == null || obj.getLong(DossierTerm.DUE_DATE) != dossier.getDueDate().getTime()) {
				dossier.setDueDate(new Date(obj.getLong(DossierTerm.DUE_DATE)));
			}
		}
		if (obj.has(DossierTerm.FINISH_DATE)) {
			if (dossier.getFinishDate() == null || obj.getLong(DossierTerm.FINISH_DATE) != dossier.getFinishDate().getTime()) {
				dossier.setFinishDate(new Date(obj.getLong(DossierTerm.FINISH_DATE)));	
			}
		}
		if (obj.has(DossierTerm.RECEIVE_DATE)) {
			if (dossier.getReceiveDate() == null || obj.getLong(DossierTerm.RECEIVE_DATE) != dossier.getReceiveDate().getTime()) {
				dossier.setReceiveDate(new Date(obj.getLong(DossierTerm.RECEIVE_DATE)));	
			}
		}
		if (obj.has(DossierTerm.SUBMIT_DATE)) {
			if (dossier.getSubmitDate() == null || obj.getLong(DossierTerm.SUBMIT_DATE) != dossier.getSubmitDate().getTime()) {
				dossier.setSubmitDate(new Date(obj.getLong(DossierTerm.SUBMIT_DATE)));	
			}
		}
		if (obj.has(DossierTerm.EXTEND_DATE)) {
			if (dossier.getExtendDate() == null || obj.getLong(DossierTerm.EXTEND_DATE) != dossier.getExtendDate().getTime()) {
				dossier.setExtendDate(new Date(obj.getLong(DossierTerm.EXTEND_DATE)));	
			}
		}
		
		return dossierPersistence.update(dossier);
	}
	
	public Dossier getByOrigin(long groupId, long originDossierId) {
		return dossierPersistence.fetchByG_O_DID_First(groupId, originDossierId, null);
	}	
	
	@Indexable(type = IndexableType.REINDEX)
	public Dossier publishDossier(long groupId, long dossierId, String referenceUid, int counter, String serviceCode,
			String serviceName, String govAgencyCode, String govAgencyName, String applicantName,
			String applicantIdType, String applicantIdNo, Date applicantIdDate, String address, String cityCode,
			String cityName, String districtCode, String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String contactEmail, String dossierTemplateNo, String password,
			int viaPostal, String postalAddress, String postalCityCode, String postalCityName, String postalTelNo,
			boolean online, boolean notification, String applicantNote, int originality, ServiceContext context) throws PortalException {

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
			dossier.setOriginality(originality);
			
			dossier = dossierPersistence.update(dossier);
		} else {

			dossier = dossierPersistence.fetchByPrimaryKey(dossierId);

			dossier.setModifiedDate(now);

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

			// if (Validator.isNotNull(applicantNote))
			dossier.setApplicantNote(applicantNote);

			dossier = dossierPersistence.update(dossier);

		}

		return dossier;
	}	
	
	@Indexable(type = IndexableType.REINDEX)
	public Dossier rollback(Dossier dossier, DossierAction dossierAction) {
		ProcessStep processStep = ProcessStepLocalServiceUtil.fetchBySC_GID(dossierAction.getStepCode(), dossier.getGroupId(), dossierAction.getServiceProcessId());
		if (processStep != null) {
			dossierAction.setState(DossierActionTerm.STATE_WAITING_PROCESSING);
			dossierAction = DossierActionLocalServiceUtil.updateState(dossierAction.getDossierActionId(), DossierActionTerm.STATE_WAITING_PROCESSING);
			JSONObject jsonDataStatusText = getStatusText(dossier.getGroupId(), DOSSIER_SATUS_DC_CODE, processStep.getDossierStatus(), processStep.getDossierSubStatus());

			dossier.setDossierActionId(dossierAction.getDossierActionId());
			dossier.setDossierStatus(processStep.getDossierStatus());
			dossier.setDossierStatusText(jsonDataStatusText.getString(processStep.getDossierStatus()));
			dossier.setDossierSubStatus(processStep.getDossierSubStatus());
			dossier.setDossierSubStatusText(jsonDataStatusText.getString(processStep.getDossierSubStatus()));
		}
		return dossierPersistence.update(dossier);
	}
	
	private JSONObject getStatusText(long groupId, String collectionCode, String curStatus, String curSubStatus) {

		JSONObject jsonData = null;
		DictCollection dc = DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(collectionCode, groupId);

		if (Validator.isNotNull(dc) && Validator.isNotNull(curStatus)) {
			jsonData = JSONFactoryUtil.createJSONObject();
			DictItem it = DictItemLocalServiceUtil.fetchByF_dictItemCode(curStatus, dc.getPrimaryKey(), groupId);
			if (Validator.isNotNull(it)) {
				jsonData.put(curStatus, it.getItemName());
				if (Validator.isNotNull(curSubStatus)) {
					DictItem dItem = DictItemLocalServiceUtil.fetchByF_dictItemCode(curSubStatus, dc.getPrimaryKey(),
							groupId);
					if (Validator.isNotNull(dItem)) {
						jsonData.put(curSubStatus, dItem.getItemName());
					}
				}
			}
		}

		return jsonData;
	}
	
	private String DOSSIER_SATUS_DC_CODE = "DOSSIER_STATUS";
}