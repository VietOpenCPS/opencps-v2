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

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.petra.string.StringPool;
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
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.opencps.adminconfig.model.DynamicReport;
import org.opencps.adminconfig.service.DynamicReportLocalServiceUtil;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.datamgt.constants.DataMGTConstants;
import org.opencps.datamgt.model.DictCollection;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.service.DictCollectionLocalServiceUtil;
import org.opencps.datamgt.service.DictItemLocalServiceUtil;
import org.opencps.datamgt.util.HolidayUtils;
import org.opencps.datamgt.utils.DictCollectionUtils;
import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.action.util.DossierMgtUtils;
import org.opencps.dossiermgt.action.util.DossierNumberGenerator;
import org.opencps.dossiermgt.action.util.ReadFilePropertiesUtils;
import org.opencps.dossiermgt.constants.ConstantsTerm;
import org.opencps.dossiermgt.constants.DossierActionTerm;
import org.opencps.dossiermgt.constants.DossierStatusConstants;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.PaymentFileTerm;
import org.opencps.dossiermgt.constants.ServiceInfoTerm;
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
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessOptionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.base.DossierLocalServiceBaseImpl;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the dossier local service. <p> All custom service
 * methods should be put in this class. Whenever methods are added, rerun
 * ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.DossierLocalService} interface. <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM. </p>
 *
 * @author huymq
 * @see DossierLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.DossierLocalServiceUtil
 */
@ProviderType
public class DossierLocalServiceImpl extends DossierLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS: Never reference this class directly. Always use
	 * {@link org.opencps.dossiermgt.service.DossierLocalServiceUtil} to access
	 * the dossier local service.
	 */
	protected Log _log = LogFactoryUtil.getLog(DossierLocalServiceImpl.class);

	@Indexable(type = IndexableType.REINDEX)
	public Dossier syncDossier(Dossier dossier)
			throws PortalException {

		dossierPersistence.update(dossier);

		return dossier;
	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier initDossier(
			long groupId, long dossierId, String referenceUid, long counter,
			String serviceCode, String serviceName, String govAgencyCode,
			String govAgencyName, String applicantName, String applicantIdType,
			String applicantIdNo, Date applicantIdDate, String address,
			String cityCode, String cityName, String districtCode,
			String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String contactEmail,
			String dossierTemplateNo, String password, int viaPostal,
			String postalAddress, String postalCityCode, String postalCityName,
			String postalDistrictCode, String postalDistrictName,
			String postalTelNo, boolean online, boolean notification,
			String applicantNote, int originality, ServiceContext context)
			throws PortalException {

		Date now = new Date();

		long userId = context.getUserId();

		User auditUser = userPersistence.fetchByPrimaryKey(userId);

		validateInit(
				groupId, dossierId, referenceUid, serviceCode, govAgencyCode,
				address, cityCode, districtCode, wardCode, contactName,
				contactTelNo, contactEmail, dossierTemplateNo);

		Dossier dossier = null;

		if (dossierId == 0) {
			String dossierTemplateName =
					getDossierTemplateName(groupId, dossierTemplateNo);

			dossierId = counterLocalService.increment(Dossier.class.getName());

			String dossierNote = getDossierNote(
					serviceCode, govAgencyCode, dossierTemplateNo, groupId);

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
			dossier.setPostalDistrictCode(postalDistrictCode);
			dossier.setPostalDistrictName(postalDistrictName);
			dossier.setPostalTelNo(postalTelNo);
			dossier.setApplicantNote(applicantNote);
			// dossier.setServerNo(getServerNo(groupId));
			dossier.setOriginality(originality);
			// Update sampleCount
			ProcessOption option = getProcessOption(
					serviceCode, govAgencyCode, dossierTemplateNo, groupId);
			if (option != null) {
				dossier.setSampleCount(option.getSampleCount());
			}

			dossierPersistence.update(dossier);

			// create DossierFile if it is eForm

			// List<DossierPart> dossierParts = new ArrayList<DossierPart>();
			//
			// dossierParts = dossierPartPersistence.findByTP_NO(groupId,
			// dossierTemplateNo);

			// for (DossierPart part : dossierParts) {
			// if (Validator.isNotNull(part.getFormScript()) &&
			// part.getPartType() != 2) {
			// String dossierFileUUID = PortalUUIDUtil.generate();

			// TODO HotFix

			// if (groupId != 55301) {
			// if (originality == DossierTerm.ORIGINALITY_DVCTT || originality
			// == DossierTerm.ORIGINALITY_MOTCUA) {
			// dossierFileLocalService.addDossierFile(groupId, dossierId,
			// dossierFileUUID, dossierTemplateNo,
			// part.getPartNo(), part.getFileTemplateNo(), part.getPartName(),
			// StringPool.BLANK, 0l,
			// null, StringPool.BLANK, StringPool.TRUE, context);
			// }
			// }
			// }

			// if (originality == DossierTerm.ORIGINALITY_MOTCUA) {
			LinkedHashMap<String, Object> params =
					new LinkedHashMap<String, Object>();
			params.put(DossierTerm.GOV_AGENCY_CODE, dossier.getGovAgencyCode());
			params.put(DossierTerm.SERVICE_CODE, dossier.getServiceCode());
			params.put(
					DossierTerm.DOSSIER_TEMPLATE_NO,
					dossier.getDossierTemplateNo());
			params.put(DossierTerm.DOSSIER_STATUS, StringPool.BLANK);

			ServiceProcess serviceProcess = null;
			_log.debug("option: " + option);
			if (option != null) {
				// Process submition note
				_log.debug("option: " + option.getSubmissionNote());
				dossier.setSubmissionNote(option.getSubmissionNote());
				_log.debug("option: " + true);
				long serviceProcessId = option.getServiceProcessId();
				serviceProcess = serviceProcessPersistence.findByPrimaryKey(
						serviceProcessId);

				String dossierRef =
						DossierNumberGenerator.generateDossierNumber(
								groupId, dossier.getCompanyId(), dossierId,
								option.getProcessOptionId(),
								serviceProcess.getDossierNoPattern(), params);

				dossier.setDossierNo(dossierRef.trim());

				dossier.setServerNo(serviceProcess.getServerNo());
			}

			// Update submit date
			// now = new Date();
			// dossier.setSubmitDate(now);
			Double durationCount;
			Integer durationUnit = 0;
			if (serviceProcess != null) {
				durationCount = serviceProcess.getDurationCount();
				durationUnit = serviceProcess.getDurationUnit();
				// _log.debug("durationCount: "+durationCount);
				// _log.debug("durationUnit: "+durationUnit);
				// int durationDays = 0;
				//
				// if (durationUnit == 0) {
				// durationDays = durationCount;
				// } else {
				// durationDays = Math.round(durationCount / 8);
				// }
				// Date dueDate = null;
				// if (Validator.isNotNull(durationCount) && durationCount > 0)
				// {
				// dueDate = HolidayUtils.getDueDate(now, durationCount,
				// durationUnit, groupId);
				// }
				//
				// _log.debug("dueDate: "+dueDate);
				// if (durationDays > 0) {
				// dueDate = DossierOverDueUtils.calculateEndDate(now,
				// durationDays);
				// }

				// dossier.setDueDate(dueDate);
				// dossier.setReceiveDate(now);
				dossier.setDurationCount(durationCount);
				dossier.setDurationUnit(durationUnit);
				// }
			}

			dossier.setViaPostal(viaPostal);

			if (viaPostal == 1) {
				dossier.setPostalAddress(StringPool.BLANK);
				dossier.setPostalCityCode(StringPool.BLANK);
				dossier.setPostalTelNo(StringPool.BLANK);
				dossier.setPostalDistrictCode(StringPool.BLANK);

			}
			else if (viaPostal == 2) {
				if (Validator.isNotNull(postalAddress))
					dossier.setPostalAddress(postalAddress);
				if (Validator.isNotNull(postalCityCode))
					dossier.setPostalCityCode(postalCityCode);
				if (Validator.isNotNull(postalTelNo))
					dossier.setPostalTelNo(postalTelNo);
				if (Validator.isNotNull(postalCityName))
					dossier.setPostalCityName(postalCityName);
				if (Validator.isNotNull(postalDistrictCode))
					dossier.setPostalDistrictCode(postalDistrictCode);
				if (Validator.isNotNull(postalDistrictName))
					dossier.setPostalDistrictName(postalDistrictName);

			}
			else {
				dossier.setPostalAddress(StringPool.BLANK);
				dossier.setPostalCityCode(StringPool.BLANK);
				dossier.setPostalTelNo(StringPool.BLANK);
				dossier.setPostalDistrictCode(StringPool.BLANK);
			}

			dossier = dossierPersistence.update(dossier);
		}
		else {

			dossier = dossierPersistence.fetchByPrimaryKey(dossierId);

			dossier.setModifiedDate(now);

			String dossierNote = getDossierNote(
					serviceCode, govAgencyCode, dossierTemplateNo, groupId);
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
				dossier.setPostalDistrictCode(StringPool.BLANK);

			}
			else if (viaPostal == 2) {
				if (Validator.isNotNull(postalAddress))
					dossier.setPostalAddress(postalAddress);
				if (Validator.isNotNull(postalCityCode))
					dossier.setPostalCityCode(postalCityCode);
				if (Validator.isNotNull(postalTelNo))
					dossier.setPostalTelNo(postalTelNo);
				if (Validator.isNotNull(postalCityName))
					dossier.setPostalCityName(postalCityName);
				if (Validator.isNotNull(postalDistrictCode))
					dossier.setPostalDistrictCode(postalDistrictCode);
				if (Validator.isNotNull(postalDistrictName))
					dossier.setPostalDistrictName(postalDistrictName);

			}
			else {
				dossier.setPostalAddress(StringPool.BLANK);
				dossier.setPostalCityCode(StringPool.BLANK);
				dossier.setPostalTelNo(StringPool.BLANK);
				dossier.setPostalDistrictCode(StringPool.BLANK);
			}

			// if (Validator.isNotNull(applicantNote))
			dossier.setApplicantNote(applicantNote);

			dossier = dossierPersistence.update(dossier);

		}

		return dossier;
	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier initDossier(
			long groupId, long dossierId, String referenceUid, int counter,
			String serviceCode, String serviceName, String govAgencyCode,
			String govAgencyName, String applicantName, String applicantIdType,
			String applicantIdNo, Date applicantIdDate, String address,
			String cityCode, String cityName, String districtCode,
			String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String contactEmail,
			String dossierTemplateNo, String password, int viaPostal,
			String postalAddress, String postalCityCode, String postalCityName,
			String postalDistrictCode, String postalDistrictName,
			String postalTelNo, boolean online, boolean notification,
			String applicantNote, int originality, ServiceInfo service,
			ServiceProcess serviceProcess, ProcessOption processOption,
			ServiceContext context)
			throws PortalException {

		Date now = new Date();
		long userId = context.getUserId();
		User auditUser = userPersistence.fetchByPrimaryKey(userId);

		Dossier dossier = null;
		if (originality == 9) {
			if (dossierId == 0) {
				String dossierTemplateName =
						getDossierTemplateName(groupId, dossierTemplateNo);

				dossierId =
						counterLocalService.increment(Dossier.class.getName());
				dossier = dossierPersistence.create(dossierId);

				// String dossierNote = getDossierNote(service, processOption);

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
				// dossier.setDossierNote(dossierNote);

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
				dossier.setPostalDistrictCode(postalDistrictCode);
				dossier.setPostalDistrictName(postalDistrictName);
				dossier.setPostalTelNo(postalTelNo);
				dossier.setApplicantNote(applicantNote);
				dossier.setOriginality(originality);
				dossier.setSampleCount(
						processOption != null ? processOption.getSampleCount() : 0);
				String registerBookCode = processOption != null
						? processOption.getRegisterBookCode() : StringPool.BLANK;
				dossier.setRegisterBookCode(registerBookCode);
				String registerBookName = StringPool.BLANK;
				if (Validator.isNotNull(registerBookCode)) {
					DynamicReport report =
							DynamicReportLocalServiceUtil.fetchByG_CODE(
									groupId, registerBookCode);
					if (report != null) {
						registerBookName = report.getReportName();
					}
				}
				dossier.setRegisterBookName(registerBookName);
				dossier.setProcessNo(
						serviceProcess != null
								? serviceProcess.getProcessNo() : StringPool.BLANK);

				dossierPersistence.update(dossier);

			}

			return dossier;

		}
		else {
			validateInit(
					groupId, dossierId, referenceUid, serviceCode, govAgencyCode,
					address, cityCode, districtCode, wardCode, contactName,
					contactTelNo, contactEmail, dossierTemplateNo);

			if (dossierId == 0) {
				String dossierTemplateName =
						getDossierTemplateName(groupId, dossierTemplateNo);

				dossierId =
						counterLocalService.increment(Dossier.class.getName());

				String dossierNote = getDossierNote(service, processOption);

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
				dossier.setPostalDistrictCode(postalDistrictCode);
				dossier.setPostalDistrictName(postalDistrictName);
				dossier.setPostalTelNo(postalTelNo);
				dossier.setApplicantNote(applicantNote);
				// dossier.setServerNo(getServerNo(groupId));
				dossier.setOriginality(originality);
				String registerBookCode = processOption != null
						? processOption.getRegisterBookCode() : StringPool.BLANK;
				dossier.setRegisterBookCode(registerBookCode);
				String registerBookName = StringPool.BLANK;
				if (Validator.isNotNull(registerBookCode)) {
					DynamicReport report =
							DynamicReportLocalServiceUtil.fetchByG_CODE(
									groupId, registerBookCode);
					if (report != null) {
						registerBookName = report.getReportName();
					}
				}
				dossier.setRegisterBookName(registerBookName);
				dossier.setProcessNo(
						serviceProcess != null
								? serviceProcess.getProcessNo() : StringPool.BLANK);
				// Update sampleCount
				// ProcessOption option = getProcessOption(serviceCode,
				// govAgencyCode, dossierTemplateNo, groupId);
				ProcessOption option = processOption;
				if (option != null) {
					dossier.setSampleCount(option.getSampleCount());
					dossier.setSubmissionNote(option.getSubmissionNote());
				}

				Double durationCount;
				Integer durationUnit = 0;
				if (serviceProcess != null) {
					durationCount = serviceProcess.getDurationCount();
					durationUnit = serviceProcess.getDurationUnit();
					dossier.setDurationCount(durationCount);
					dossier.setDurationUnit(durationUnit);
					dossier.setServerNo(serviceProcess.getServerNo());
				}

				dossier.setViaPostal(viaPostal);
				if (viaPostal == 1) {
					dossier.setPostalAddress(StringPool.BLANK);
					dossier.setPostalCityCode(StringPool.BLANK);
					dossier.setPostalTelNo(StringPool.BLANK);
					dossier.setPostalDistrictCode(StringPool.BLANK);

				}
				else if (viaPostal == 2) {
					if (Validator.isNotNull(postalAddress))
						dossier.setPostalAddress(postalAddress);
					if (Validator.isNotNull(postalCityCode))
						dossier.setPostalCityCode(postalCityCode);
					if (Validator.isNotNull(postalTelNo))
						dossier.setPostalTelNo(postalTelNo);
					if (Validator.isNotNull(postalCityName))
						dossier.setPostalCityName(postalCityName);
					if (Validator.isNotNull(postalDistrictCode))
						dossier.setPostalDistrictCode(postalDistrictCode);
					if (Validator.isNotNull(postalDistrictName))
						dossier.setPostalDistrictName(postalDistrictName);

				}
				else {
					dossier.setPostalAddress(StringPool.BLANK);
					dossier.setPostalCityCode(StringPool.BLANK);
					dossier.setPostalTelNo(StringPool.BLANK);
					dossier.setPostalDistrictCode(StringPool.BLANK);
				}

				dossierPersistence.update(dossier);
			}
			else {

				dossier = dossierPersistence.fetchByPrimaryKey(dossierId);

				dossier.setModifiedDate(now);

				// String dossierNote = getDossierNote(serviceCode,
				// govAgencyCode, dossierTemplateNo, groupId);
				String dossierNote = getDossierNote(service, processOption);
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
					dossier.setPostalDistrictCode(StringPool.BLANK);

				}
				else if (viaPostal == 2) {
					if (Validator.isNotNull(postalAddress))
						dossier.setPostalAddress(postalAddress);
					if (Validator.isNotNull(postalCityCode))
						dossier.setPostalCityCode(postalCityCode);
					if (Validator.isNotNull(postalTelNo))
						dossier.setPostalTelNo(postalTelNo);
					if (Validator.isNotNull(postalCityName))
						dossier.setPostalCityName(postalCityName);
					if (Validator.isNotNull(postalDistrictCode))
						dossier.setPostalDistrictCode(postalDistrictCode);
					if (Validator.isNotNull(postalDistrictName))
						dossier.setPostalDistrictName(postalDistrictName);

				}
				else {
					dossier.setPostalAddress(StringPool.BLANK);
					dossier.setPostalCityCode(StringPool.BLANK);
					dossier.setPostalTelNo(StringPool.BLANK);
					dossier.setPostalDistrictCode(StringPool.BLANK);
				}

				// if (Validator.isNotNull(applicantNote))
				dossier.setApplicantNote(applicantNote);

				dossierPersistence.update(dossier);

			}

			return dossier;
		}

	}

	// initMultipleDossier(groupId, 0l, referenceUid, counter,
	// input.getServiceCode(), serviceName,
	// input.getGovAgencyCode(), govAgencyName, applicantName, applicantIdType,
	// applicantIdNo, appIdDate, address,
	// contactName, contactTelNo, contactEmail,
	// input.getDossierTemplateNo(), password,
	// viaPostal,postalServiceCode,postalServiceName, postalAddress,
	// postalCityCode, postalCityName,
	// postalDistrictCode,postalDistrictName,postalWardCode,postalWardName,
	// postalTelNo,
	// online, process.getDirectNotification(), applicantNote,
	// input.getOriginality(),
	// delegateIdNo,
	// delegateName,delegateTelNo,delegateEmail,delegateEmail,delegateAddress,
	// delegateCityCode,delegateCityName,delegateDistrictCode,delegateDistrictName,delegateWardCode,delegateWardName,
	// registerBookCode,registerBookName,sampleCount,
	// dossierName,
	// service, process, option,
	// serviceContext);

	@Indexable(type = IndexableType.REINDEX)
	public Dossier initMultipleDossier(
			long groupId, long dossierId, String referenceUid, int counter,
			String serviceCode, String serviceName, String govAgencyCode,
			String govAgencyName, String applicantName, String applicantIdType,
			String applicantIdNo, Date applicantIdDate, String address,
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
			String dossierName, int durationCount, ServiceInfo service, ServiceProcess process,
			ProcessOption option, ServiceContext context)
			throws PortalException {

		Date now = new Date();
		long userId = context.getUserId();
		User auditUser = userPersistence.fetchByPrimaryKey(userId);

		Dossier dossier = null;
		if (dossierId == 0) {
			dossierId = counterLocalService.increment(Dossier.class.getName());
			dossier = dossierPersistence.create(dossierId);

			String dossierTemplateName =
					getDossierTemplateName(groupId, dossierTemplateNo);
			String dossierNote = getDossierNote(service, option);

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
			dossier.setAddress(address);
			dossier.setContactName(contactName);
			dossier.setContactEmail(contactEmail);
			dossier.setContactTelNo(contactTelNo);

			dossier.setPassword(password);
			dossier.setOnline(online);
			dossier.setDossierNote(dossierNote);

			dossier.setViaPostal(viaPostal);
			if (viaPostal == 1) {
				dossier.setPostalAddress(StringPool.BLANK);
				dossier.setPostalCityCode(StringPool.BLANK);
				dossier.setPostalTelNo(StringPool.BLANK);
				dossier.setPostalDistrictCode(StringPool.BLANK);

			}
			else if (viaPostal == 2) {
				if (Validator.isNotNull(postalAddress))
					dossier.setPostalAddress(postalAddress);
				if (Validator.isNotNull(postalCityCode))
					dossier.setPostalCityCode(postalCityCode);
				if (Validator.isNotNull(postalTelNo))
					dossier.setPostalTelNo(postalTelNo);
				if (Validator.isNotNull(postalCityName))
					dossier.setPostalCityName(postalCityName);
				if (Validator.isNotNull(postalDistrictCode))
					dossier.setPostalDistrictCode(postalDistrictCode);
				if (Validator.isNotNull(postalDistrictName))
					dossier.setPostalDistrictName(postalDistrictName);

			}
			else {
				dossier.setPostalAddress(StringPool.BLANK);
				dossier.setPostalCityCode(StringPool.BLANK);
				dossier.setPostalTelNo(StringPool.BLANK);
				dossier.setPostalDistrictCode(StringPool.BLANK);
			}

			dossier.setPostalServiceCode(postalServiceCode);
			dossier.setPostalServiceName(postalServiceName);
			dossier.setPostalAddress(postalAddress);
			dossier.setPostalCityCode(postalCityCode);
			dossier.setPostalCityName(postalCityName);
			dossier.setPostalDistrictCode(postalDistrictCode);
			dossier.setPostalDistrictName(postalDistrictName);
			dossier.setPostalWardCode(postalWardCode);
			dossier.setPostalWardName(postalWardName);
			dossier.setPostalTelNo(postalTelNo);
			dossier.setApplicantNote(applicantNote);
			dossier.setSampleCount(sampleCount);
			dossier.setOriginality(originality);

			dossier.setDelegateIdNo(delegateIdNo);
			dossier.setDelegateName(delegateName);
			dossier.setDelegateTelNo(delegateTelNo);
			dossier.setDelegateEmail(delegateEmail);
			dossier.setDelegateAddress(delegateAddress);
			dossier.setDelegateCityCode(delegateCityCode);
			dossier.setDelegateCityName(delegateCityName);
			dossier.setDelegateDistrictCode(delegateDistrictCode);
			dossier.setDelegateDistrictName(delegateDistrictName);
			dossier.setDelegateWardCode(delegateWardCode);
			dossier.setDelegateWardName(delegateWardName);

			dossier.setNotification(notification);
			dossier.setRegisterBookCode(registerBookCode);
			dossier.setRegisterBookName(registerBookName);
			dossier.setDossierName(dossierName);
			dossier.setProcessNo(process.getProcessNo());
			dossier.setServerNo(process.getServerNo());
			// Update submit date
			if (process != null) {
				if (durationCount > 0) {
					dossier.setDurationCount(durationCount);
				} else {
					dossier.setDurationCount(process.getDurationCount());
				}
				dossier.setDurationUnit(
						Validator.isNotNull(process.getDurationUnit())
								? process.getDurationUnit() : 0);
			}

			dossierPersistence.update(dossier);

			// LinkedHashMap<String, Object> params = new LinkedHashMap<String,
			// Object>();
			// params.put(DossierTerm.GOV_AGENCY_CODE,
			// dossier.getGovAgencyCode());
			// params.put(DossierTerm.SERVICE_CODE, dossier.getServiceCode());
			// params.put(DossierTerm.DOSSIER_TEMPLATE_NO,
			// dossier.getDossierTemplateNo());
			// params.put(DossierTerm.DOSSIER_STATUS, StringPool.BLANK);
			// if (option != null) {
			// String dossierRef =
			// DossierNumberGenerator.generateDossierNumber(groupId,
			// dossier.getCompanyId(),
			// dossierId, option.getProcessOptionId(),
			// process.getDossierNoPattern(), params);
			// dossier.setDossierNo(dossierRef.trim());
			// dossier.setSubmissionNote(option.getSubmissionNote());
			//
			// }
			// dossierPersistence.update(dossier);
		}

		return dossier;
	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier initFullDossier(
			long groupId, long dossierId, String referenceUid, int counter,
			String serviceCode, String serviceName, String govAgencyCode,
			String govAgencyName, String applicantName, String applicantIdType,
			String applicantIdNo, Date applicantIdDate, String address,
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
			String dossierName, ServiceInfo service, ServiceProcess process,
			ProcessOption option, ServiceContext context)
			throws PortalException {

		Date now = new Date();
		long userId = context.getUserId();
		User auditUser = userPersistence.fetchByPrimaryKey(userId);

		Dossier dossier = null;
		if (dossierId == 0) {
			dossierId = counterLocalService.increment(Dossier.class.getName());
			dossier = dossierPersistence.create(dossierId);

			String dossierTemplateName =
					getDossierTemplateName(groupId, dossierTemplateNo);
			String dossierNote = getDossierNote(service, option);

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
			dossier.setAddress(address);
			dossier.setContactName(contactName);
			dossier.setContactEmail(contactEmail);
			dossier.setContactTelNo(contactTelNo);

			dossier.setPassword(password);
			dossier.setOnline(online);
			dossier.setDossierNote(dossierNote);

			dossier.setViaPostal(viaPostal);
			if (viaPostal == 1) {
				dossier.setPostalAddress(StringPool.BLANK);
				dossier.setPostalCityCode(StringPool.BLANK);
				dossier.setPostalTelNo(StringPool.BLANK);
				dossier.setPostalDistrictCode(StringPool.BLANK);

			}
			else if (viaPostal == 2) {
				if (Validator.isNotNull(postalAddress))
					dossier.setPostalAddress(postalAddress);
				if (Validator.isNotNull(postalCityCode))
					dossier.setPostalCityCode(postalCityCode);
				if (Validator.isNotNull(postalTelNo))
					dossier.setPostalTelNo(postalTelNo);
				if (Validator.isNotNull(postalCityName))
					dossier.setPostalCityName(postalCityName);
				if (Validator.isNotNull(postalDistrictCode))
					dossier.setPostalDistrictCode(postalDistrictCode);
				if (Validator.isNotNull(postalDistrictName))
					dossier.setPostalDistrictName(postalDistrictName);

			}
			else {
				dossier.setPostalAddress(StringPool.BLANK);
				dossier.setPostalCityCode(StringPool.BLANK);
				dossier.setPostalTelNo(StringPool.BLANK);
				dossier.setPostalDistrictCode(StringPool.BLANK);
			}

			dossier.setPostalServiceCode(postalServiceCode);
			dossier.setPostalServiceName(postalServiceName);
			dossier.setPostalAddress(postalAddress);
			dossier.setPostalCityCode(postalCityCode);
			dossier.setPostalCityName(postalCityName);
			dossier.setPostalDistrictCode(postalDistrictCode);
			dossier.setPostalDistrictName(postalDistrictName);
			dossier.setPostalWardCode(postalWardCode);
			dossier.setPostalWardName(postalWardName);
			dossier.setPostalTelNo(postalTelNo);
			dossier.setApplicantNote(applicantNote);
			dossier.setSampleCount(sampleCount);
			dossier.setOriginality(originality);

			dossier.setDelegateIdNo(delegateIdNo);
			dossier.setDelegateName(delegateName);
			dossier.setDelegateTelNo(delegateTelNo);
			dossier.setDelegateEmail(delegateEmail);
			dossier.setDelegateAddress(delegateAddress);
			dossier.setDelegateCityCode(delegateCityCode);
			dossier.setDelegateCityName(delegateCityName);
			dossier.setDelegateDistrictCode(delegateDistrictCode);
			dossier.setDelegateDistrictName(delegateDistrictName);
			dossier.setDelegateWardCode(delegateWardCode);
			dossier.setDelegateWardName(delegateWardName);

			dossier.setNotification(notification);
			dossier.setRegisterBookCode(registerBookCode);
			dossier.setRegisterBookName(registerBookName);
			dossier.setDossierName(dossierName);
			dossier.setProcessNo(process.getProcessNo());
			dossier.setServerNo(process.getServerNo());

			// Update submit date
			if (process != null) {
				dossier.setDurationCount(process.getDurationCount());
				dossier.setDurationUnit(
						Validator.isNotNull(process.getDurationUnit())
								? process.getDurationUnit() : 0);
			}

			dossierPersistence.update(dossier);

			// LinkedHashMap<String, Object> params = new LinkedHashMap<String,
			// Object>();
			// params.put(DossierTerm.GOV_AGENCY_CODE,
			// dossier.getGovAgencyCode());
			// params.put(DossierTerm.SERVICE_CODE, dossier.getServiceCode());
			// params.put(DossierTerm.DOSSIER_TEMPLATE_NO,
			// dossier.getDossierTemplateNo());
			// params.put(DossierTerm.DOSSIER_STATUS, StringPool.BLANK);
			// if (option != null) {
			// String dossierRef =
			// DossierNumberGenerator.generateDossierNumber(groupId,
			// dossier.getCompanyId(),
			// dossierId, option.getProcessOptionId(),
			// process.getDossierNoPattern(), params);
			// dossier.setDossierNo(dossierRef.trim());
			// dossier.setSubmissionNote(option.getSubmissionNote());
			// }
			// dossierPersistence.update(dossier);
		}

		return dossier;
	}

	private final String ADMINISTRATIVE_REGION = "ADMINISTRATIVE_REGION";
	// private final String POSTAL_ADMINISTRATIVE_REGION = "VNPOST_CODE";
	private final String GOVERNMENT_AGENCY = ReadFilePropertiesUtils.get(ConstantUtils.GOVERNMENT_AGENCY);
	// private final int DUE_DATE_DEFAULT = 5;

	private String getDictItemName(
			long groupId, String collectionCode, String itemCode) {

		DictCollection dc =
				DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(
						collectionCode, groupId);
		if (dc == null)
			return StringPool.BLANK;

		_log.debug("COLLECTION UPDATE DOSSIER: " + dc + "," + collectionCode);
		if (dc != null) {
			_log.debug(
					"COLLECTION UPDATE DOSSIER: " + dc.getCollectionCode() + "," +
							dc.getDictCollectionId() + "," + dc.getPrimaryKey());
			DictItem it = DictItemLocalServiceUtil.fetchByF_dictItemCode(
					itemCode, dc.getPrimaryKey(), groupId);
			if (it == null)
				return StringPool.BLANK;
			_log.debug("ITEM: " + itemCode + "," + it);
			return it.getItemName();
		}

		return StringPool.BLANK;
	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateDossierOneGate(
			long dossierId, String applicantName, String applicantIdType,
			String applicantIdNo, Date applicantIdDate, String address,
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
			ServiceContext context)
			throws PortalException {

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
			dossier.setCityName(
					getDictItemName(
							dossier.getGroupId(), ADMINISTRATIVE_REGION, cityCode));
		}

		if (Validator.isNotNull(districtCode)) {
			dossier.setDistrictCode(districtCode);
			dossier.setDistrictName(
					getDictItemName(
							dossier.getGroupId(), ADMINISTRATIVE_REGION, districtCode));
		}

		if (Validator.isNotNull(wardCode)) {
			dossier.setWardCode(wardCode);
			dossier.setWardName(
					getDictItemName(
							dossier.getGroupId(), ADMINISTRATIVE_REGION, wardCode));
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
				dossier.setDelegateCityName(
						getDictItemName(
								dossier.getGroupId(), ADMINISTRATIVE_REGION, cityCode));
			}

			if (Validator.isNotNull(districtCode)) {
				dossier.setDelegateDistrictCode(districtCode);
				dossier.setDelegateDistrictName(
						getDictItemName(
								dossier.getGroupId(), ADMINISTRATIVE_REGION,
								districtCode));
			}

			if (Validator.isNotNull(wardCode)) {
				dossier.setDelegateWardCode(wardCode);
				dossier.setDelegateWardName(
						getDictItemName(
								dossier.getGroupId(), ADMINISTRATIVE_REGION, wardCode));
			}
		}
		else {
			dossier.setDelegateName(delegateName);
			dossier.setDelegateIdNo(delegateIdNo);
			dossier.setDelegateTelNo(delegateTelNo);
			dossier.setDelegateAddress(delegateAddress);

			if (Validator.isNotNull(delegateCityCode)) {
				dossier.setDelegateCityCode(delegateCityCode);
				dossier.setDelegateCityName(
						getDictItemName(
								dossier.getGroupId(), ADMINISTRATIVE_REGION,
								delegateCityCode));
			}

			if (Validator.isNotNull(delegateDistrictCode)) {
				dossier.setDelegateDistrictCode(delegateDistrictCode);
				dossier.setDelegateDistrictName(
						getDictItemName(
								dossier.getGroupId(), ADMINISTRATIVE_REGION,
								delegateDistrictCode));
			}

			if (Validator.isNotNull(delegateWardCode)) {
				dossier.setDelegateWardCode(delegateWardCode);
				dossier.setDelegateWardName(
						getDictItemName(
								dossier.getGroupId(), ADMINISTRATIVE_REGION,
								delegateWardCode));
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
					getDictItemName(
							dossier.getGroupId(), ADMINISTRATIVE_REGION,
							postalCityCode));
			dossier.setPostalDistrictCode(postalDistrictCode);
			dossier.setPostalDistrictName(
					getDictItemName(
							dossier.getGroupId(), ADMINISTRATIVE_REGION,
							postalDistrictCode));
			dossier.setPostalWardCode(postalWardCode);
			dossier.setPostalWardName(
					getDictItemName(
							dossier.getGroupId(), ADMINISTRATIVE_REGION,
							postalWardCode));
			dossier.setPostalTelNo(postalTelNo);
		}

		String password = PwdGenerator.getPassword(8).toUpperCase();

		dossier.setPassword(password);
		dossier.setOnline(false);

		// LamTV_Process
		// if (dossierActionId > 0) {
		// DossierAction dAction =
		// DossierActionLocalServiceUtil.fetchDossierAction(dossierActionId);
		// ProcessAction process =
		// ProcessActionLocalServiceUtil.getByServiceProcess(dAction.getServiceProcessId(),
		// dAction.getActionCode());
		// if (process != null) {
		// process.setPaymentFee(paymentFee);
		// ProcessActionLocalServiceUtil.updateProcessAction(process);
		// }
		// } else {
		// ServiceProcess serProcess =
		// ServiceProcessLocalServiceUtil.getServiceByCode(dossier.getGroupId(),
		// dossier.getServiceCode(), dossier.getGovAgencyCode(),
		// dossier.getDossierTemplateNo());
		// if (serProcess != null) {
		// ProcessAction process =
		// ProcessActionLocalServiceUtil.getByServiceProcess(serProcess.getServiceProcessId(),
		// String.valueOf(10000));
		// if (process != null) {
		// process.setPaymentFee(paymentFee);
		// ProcessActionLocalServiceUtil.updateProcessAction(process);
		// }
		// }
		// }
		// LamTV_ Process Post payment
		// long userId = context.getUserId();
		// long groupId = dossier.getGroupId();
		// String referenceUid = StringPool.BLANK;
		// if (Validator.isNull(referenceUid)) {
		// referenceUid = PortalUUIDUtil.generate();
		// }
		// String govAgencyCode = dossier.getGovAgencyCode();
		// String govAgencyName = dossier.getGovAgencyName();
		// long paymentAmount = 0;
		// String epaymentProfile = StringPool.BLANK;
		// String bankInfo = StringPool.BLANK;
		// PaymentFileLocalServiceUtil.createPaymentFiles(userId, groupId,
		// dossierId,
		// referenceUid, govAgencyCode, govAgencyName, applicantName,
		// applicantIdNo, paymentFee, paymentAmount,
		// paymentFeeNote, epaymentProfile, bankInfo, context);

		dossierPersistence.update(dossier);

		return dossier;
	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier createDossier(
			long groupId, String serviceCode, String govAgencyCode,
			String applicantName, String applicantIdType, String applicantIdNo,
			Date applicantIdDate, String address, String cityCode,
			String districtCode, String wardCode, String contactName,
			String contactTelNo, String contactEmail, boolean isSameAsApplicant,
			String delegateName, String delegateIdNo, String delegateTelNo,
			String delegateEmail, String delegateAddress, String delegateCityCode,
			String delegateDistrictCode, String delegateWardCode,
			String applicantNote, String briefNote, String dossierNo,
			String dossierTemplateNo, int viaPostal, String postalServiceCode,
			String postalServiceName, String postalAddress, String postalCityCode,
			String postalDistrictCode, String postalWardCode, String postalTelNo,
			int originality, ServiceContext context)
			throws PortalException {

		Date now = new Date();

		long dossierId = counterLocalService.increment(Dossier.class.getName());

		long userId = context.getUserId();

		// create referentUid

		String referenceUid = PortalUUIDUtil.generate();

		// create counterId

		int counter = DossierNumberGenerator.counterDossier(userId, groupId);

		Dossier dossier = dossierLocalService.createDossier(dossierId);

		// setDossierStatus = new

		dossier.setDossierStatus(DossierStatusConstants.NEW);

		dossier.setCreateDate(now);
		dossier.setModifiedDate(now);
		dossier.setCompanyId(context.getCompanyId());
		dossier.setGroupId(groupId);

		dossier.setReferenceUid(referenceUid);
		dossier.setCounter(counter);

		String dossierTemplateName =
				getDossierTemplateName(groupId, dossierTemplateNo);

		// create dossierRegister

		String dossierRegister = PwdGenerator.getPassword(10).toUpperCase();

		dossier.setDossierRegister(dossierRegister);

		ServiceInfo serviceInfo =
				serviceInfoLocalService.getByCode(groupId, serviceCode);

		dossier.setServiceCode(serviceCode);
		dossier.setServiceName(serviceInfo.getServiceName());
		dossier.setGovAgencyCode(govAgencyCode);
		dossier.setGovAgencyName(
				getDictItemName(groupId, GOVERNMENT_AGENCY, govAgencyCode));
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
			dossier.setCityName(
					getDictItemName(groupId, ADMINISTRATIVE_REGION, cityCode));
		}

		if (Validator.isNotNull(districtCode)) {
			dossier.setDistrictCode(districtCode);
			dossier.setDistrictName(
					getDictItemName(groupId, ADMINISTRATIVE_REGION, districtCode));
		}

		if (Validator.isNotNull(wardCode)) {
			dossier.setWardCode(wardCode);
			dossier.setWardName(
					getDictItemName(groupId, ADMINISTRATIVE_REGION, wardCode));
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
				dossier.setDelegateCityName(
						getDictItemName(groupId, ADMINISTRATIVE_REGION, cityCode));
			}

			if (Validator.isNotNull(districtCode)) {
				dossier.setDelegateDistrictCode(districtCode);
				dossier.setDelegateDistrictName(
						getDictItemName(
								groupId, ADMINISTRATIVE_REGION, districtCode));
			}

			if (Validator.isNotNull(wardCode)) {
				dossier.setDelegateWardCode(wardCode);
				dossier.setDelegateWardName(
						getDictItemName(groupId, ADMINISTRATIVE_REGION, wardCode));
			}
		}
		else {
			dossier.setDelegateName(delegateName);
			dossier.setDelegateIdNo(delegateIdNo);
			dossier.setDelegateTelNo(delegateTelNo);
			dossier.setDelegateAddress(delegateAddress);

			if (Validator.isNotNull(delegateCityCode)) {
				dossier.setDelegateCityCode(delegateCityCode);
				dossier.setDelegateCityName(
						getDictItemName(
								groupId, ADMINISTRATIVE_REGION, delegateCityCode));
			}

			if (Validator.isNotNull(delegateDistrictCode)) {
				dossier.setDelegateDistrictCode(delegateDistrictCode);
				dossier.setDelegateDistrictName(
						getDictItemName(
								groupId, ADMINISTRATIVE_REGION, delegateDistrictCode));
			}

			if (Validator.isNotNull(delegateWardCode)) {
				dossier.setDelegateWardCode(delegateWardCode);
				dossier.setDelegateWardName(
						getDictItemName(
								groupId, ADMINISTRATIVE_REGION, delegateWardCode));
			}
		}

		ProcessOption option = getProcessOption(
				serviceCode, govAgencyCode, dossierTemplateNo, groupId);

		ServiceProcess serviceProcess = null;
		if (option != null) {
			long serviceProcessId = option.getServiceProcessId();
			serviceProcess = serviceProcessPersistence.findByPrimaryKey(serviceProcessId);
		}

		double durationCount = 0;
		int durationUnit = 0;
		if (serviceProcess != null) {
			durationCount = serviceProcess.getDurationCount();
			durationUnit = serviceProcess.getDurationUnit();
		}

		// _log.debug("durationCount: "+durationCount);
		// _log.debug("durationUnit: "+durationUnit);

		Date dueDate =
				HolidayUtils.getDueDate(now, durationCount, durationUnit, groupId);

		// set dueDate
		dossier.setDueDate(dueDate);

		// set receivedDate
		dossier.setReceiveDate(now);

		dossier.setDossierNote(option.getInstructionNote());
		dossier.setSubmissionNote(option.getSubmissionNote());
		dossier.setApplicantNote(applicantNote);
		dossier.setBriefNote(briefNote);
		// dossier.setDossierNo(dossierNo);

		// viaPortal: 0 disable, 1: unselected, 2: selected
		// if (viaPostal == 2) {
		// LamTV_Hot fix
		if (viaPostal == 1) {
			dossier.setViaPostal(viaPostal);
			dossier.setPostalServiceCode(postalServiceCode);
			dossier.setPostalServiceName(postalServiceName);
			dossier.setPostalAddress(postalAddress);
			dossier.setPostalCityCode(postalCityCode);
			dossier.setPostalCityName(
					getDictItemName(
							groupId, ADMINISTRATIVE_REGION, postalCityCode));
			dossier.setPostalDistrictCode(postalDistrictCode);
			dossier.setPostalDistrictName(
					getDictItemName(
							groupId, ADMINISTRATIVE_REGION, postalDistrictCode));
			dossier.setPostalWardCode(postalWardCode);
			dossier.setPostalWardName(
					getDictItemName(
							groupId, ADMINISTRATIVE_REGION, postalWardCode));
			dossier.setPostalTelNo(postalTelNo);
		}

		String password = PwdGenerator.getPassword(8).toUpperCase();

		dossier.setPassword(password);
		dossier.setOnline(false);

		dossierPersistence.update(dossier);

		// init DossierFile
		List<DossierPart> dossierParts;

		dossierParts =
				dossierPartPersistence.findByTP_NO(groupId, dossierTemplateNo);

		for (DossierPart part : dossierParts) {
			if (Validator.isNotNull(part.getFormScript()) &&
					part.getPartType() != 2) {
				String dossierFileUUID = PortalUUIDUtil.generate();

				dossierFileLocalService.addDossierFile(
						groupId, dossierId, dossierFileUUID, dossierTemplateNo,
						part.getPartNo(), part.getFileTemplateNo(),
						part.getPartName(), StringPool.BLANK, 0l, null,
						StringPool.BLANK, StringPool.TRUE, context);
			}
		}

		LinkedHashMap<String, Object> params =
				new LinkedHashMap<String, Object>();
		params.put(DossierTerm.GOV_AGENCY_CODE, dossier.getGovAgencyCode());
		params.put(DossierTerm.SERVICE_CODE, dossier.getServiceCode());
		params.put(
				DossierTerm.DOSSIER_TEMPLATE_NO, dossier.getDossierTemplateNo());
		params.put(DossierTerm.DOSSIER_STATUS, StringPool.BLANK);

		String dossierRef = DossierNumberGenerator.generateDossierNumber(
				groupId, dossier.getCompanyId(), dossierId,
				option.getProcessOptionId(),
				serviceProcess != null
						? serviceProcess.getDossierNoPattern() : StringPool.BLANK,
				params);

		// LamTV_ Process Post payment
		// String referenceUid = StringPool.BLANK;
		// if (Validator.isNull(referenceUid)) {
		// referenceUid = PortalUUIDUtil.generate();
		// }
		// String govAgencyCode = dossier.getGovAgencyCode();
		// String govAgencyName = dossier.getGovAgencyName();
		// String paymentNote = StringPool.BLANK;
		// String epaymentProfile = StringPool.BLANK;
		// String bankInfo = StringPool.BLANK;
		// String paymentFee;
		// long paymentAmount = 0;
		if (serviceProcess != null) {
			// paymentFee = serviceProcess.getPaymentFee();
			// _log.debug("paymentFee: "+paymentFee);
		}
		// PaymentFileLocalServiceUtil.createPaymentFiles(userId, groupId,
		// dossierId, referenceUid, govAgencyCode,
		// govAgencyName, applicantName, applicantIdNo, paymentFee,
		// paymentAmount, paymentNote, epaymentProfile,
		// bankInfo, context);

		// _log.debug("SERVICEPROCESS"+ serviceProcess.getDossierNoPattern());
		//
		// _log.debug("DOSSIER_NO_"+ dossierRef);

		dossier.setDossierNo(dossierRef.trim());

		dossierPersistence.update(dossier);

		return dossier;
	}

	private ProcessOption getProcessOption(
			String serviceInfoCode, String govAgencyCode, String dossierTemplateNo,
			long groupId)
			throws PortalException {

		ServiceConfig config = ServiceConfigLocalServiceUtil.getBySICodeAndGAC(
				groupId, serviceInfoCode, govAgencyCode);
		if (config != null) {
			return ProcessOptionLocalServiceUtil.getByDTPLNoAndServiceCF(
					groupId, dossierTemplateNo, config.getServiceConfigId());
		}

		return null;
	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier postDossier(
			long groupId, long dossierId, String referenceUid, int counter,
			String serviceCode, String serviceName, String govAgencyCode,
			String govAgencyName, String applicantName, String applicantIdType,
			String applicantIdNo, Date applicantIdDate, String address,
			String cityCode, String cityName, String districtCode,
			String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String contactEmail,
			String dossierTemplateNo, String password, int viaPostal,
			String postalAddress, String postalCityCode, String postalCityName,
			String postalDistrictCode, String postalDistrictName,
			String postalTelNo, boolean online, boolean notification,
			String applicantNote, int originality, ServiceContext context)
			throws PortalException {

		Date now = new Date();

		long userId = context.getUserId();

		User auditUser = userPersistence.fetchByPrimaryKey(userId);

		validateInit(
				groupId, dossierId, referenceUid, serviceCode, govAgencyCode,
				address, cityCode, districtCode, wardCode, contactName,
				contactTelNo, contactEmail, dossierTemplateNo);

		Dossier dossier = null;

		if (dossierId == 0) {
			String dossierTemplateName =
					getDossierTemplateName(groupId, dossierTemplateNo);

			dossierId = counterLocalService.increment(Dossier.class.getName());

			String dossierNote = getDossierNote(
					serviceCode, govAgencyCode, dossierTemplateNo, groupId);

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
			dossier.setPostalDistrictCode(postalDistrictCode);
			dossier.setPostalDistrictName(postalDistrictName);
			dossier.setPostalTelNo(postalTelNo);
			dossier.setApplicantNote(applicantNote);
			ProcessOption option = getProcessOption(
					serviceCode, govAgencyCode, dossierTemplateNo, groupId);

			// if (originality == DossierTerm.ORIGINALITY_MOTCUA) {
			LinkedHashMap<String, Object> params =
					new LinkedHashMap<String, Object>();
			params.put(DossierTerm.GOV_AGENCY_CODE, dossier.getGovAgencyCode());
			params.put(DossierTerm.SERVICE_CODE, dossier.getServiceCode());
			params.put(
					DossierTerm.DOSSIER_TEMPLATE_NO,
					dossier.getDossierTemplateNo());
			params.put(DossierTerm.DOSSIER_STATUS, StringPool.BLANK);

			ServiceProcess serviceProcess = null;
			_log.debug("option: " + option);
			if (option != null) {
				// Process submition note
				_log.debug("option: " + option.getSubmissionNote());
				dossier.setSubmissionNote(option.getSubmissionNote());
				_log.debug("option: " + true);
				long serviceProcessId = option.getServiceProcessId();
				serviceProcess = serviceProcessPersistence.findByPrimaryKey(
						serviceProcessId);

				String dossierRef =
						DossierNumberGenerator.generateDossierNumber(
								groupId, dossier.getCompanyId(), dossierId,
								option.getProcessOptionId(),
								serviceProcess.getDossierNoPattern(), params);

				dossier.setDossierNo(dossierRef.trim());

				dossier.setServerNo(serviceProcess.getServerNo());
			}
			// dossier.setServerNo(getServerNo(groupId));
			dossier.setOriginality(originality);

			dossierPersistence.update(dossier);

			// create DossierFile if it is eForm

			List<DossierPart> dossierParts;

			dossierParts =
					dossierPartPersistence.findByTP_NO(groupId, dossierTemplateNo);

			for (DossierPart part : dossierParts) {
				if (Validator.isNotNull(part.getFormScript()) &&
						part.getPartType() != 2) {
					String dossierFileUUID = PortalUUIDUtil.generate();

					// TODO HotFix

					if (groupId != 55301) {

						dossierFileLocalService.addDossierFile(
								groupId, dossierId, dossierFileUUID,
								dossierTemplateNo, part.getPartNo(),
								part.getFileTemplateNo(), part.getPartName(),
								StringPool.BLANK, 0l, null, StringPool.BLANK,
								StringPool.TRUE, context);
					}
				}
			}

		}
		else {

			dossier = dossierPersistence.fetchByPrimaryKey(dossierId);

			dossier.setModifiedDate(now);

			String dossierNote = getDossierNote(
					serviceCode, govAgencyCode, dossierTemplateNo, groupId);
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
				dossier.setPostalDistrictCode(StringPool.BLANK);

			}
			else if (viaPostal == 2) {
				if (Validator.isNotNull(postalAddress))
					dossier.setPostalAddress(postalAddress);
				if (Validator.isNotNull(postalCityCode))
					dossier.setPostalCityCode(postalCityCode);
				if (Validator.isNotNull(postalTelNo))
					dossier.setPostalTelNo(postalTelNo);
				if (Validator.isNotNull(postalCityName))
					dossier.setPostalCityName(postalCityName);
				if (Validator.isNotNull(postalDistrictCode))
					dossier.setPostalDistrictCode(postalDistrictCode);
				if (Validator.isNotNull(postalDistrictName))
					dossier.setPostalDistrictName(postalDistrictName);

			}
			else {
				dossier.setPostalAddress(StringPool.BLANK);
				dossier.setPostalCityCode(StringPool.BLANK);
				dossier.setPostalTelNo(StringPool.BLANK);
				dossier.setPostalDistrictCode(StringPool.BLANK);
			}

			// if (Validator.isNotNull(applicantNote))
			dossier.setApplicantNote(applicantNote);

			dossierPersistence.update(dossier);

		}

		return dossier;
	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier assignToProcess(
			long dossierId, String dossierNote, String submissionNote,
			String briefNote, String dossierNo, long folderId, long dossierActionId,
			String serverNo, ServiceContext context) {

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
	public Dossier updateDossier(
			long groupId, long dossierId, String referenceUid, int counter,
			String serviceCode, String serviceName, String govAgencyCode,
			String govAgencyName, String applicantName, String applicantIdType,
			String applicantIdNo, Date applicantIdDate, String address,
			String cityCode, String cityName, String districtCode,
			String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String contactEmail,
			String dossierTemplateNo, String dossierNote, String submissionNote,
			String applicantNote, String briefNote, String dossierNo,
			boolean submitting, Date correctingDate, String dossierStatus,
			String dossierStatusText, String dossierSubStatus,
			String dossierSubStatusText, long folderId, long dossierActionId,
			int viaPostal, String postalAddress, String postalCityCode,
			String postalCityName, String postalDistrictCode,
			String postalDistrictName, String postalTelNo, String password,
			boolean notification, boolean online, String serverNo,
			ServiceContext context)
			throws PortalException {

		Date now = new Date();

		long userId = context.getUserId();

		User auditUser = userPersistence.fetchByPrimaryKey(userId);

		validateUpdateDossier(
				groupId, dossierId, referenceUid, serviceCode, govAgencyCode,
				address, cityCode, districtCode, wardCode, contactName,
				contactTelNo, contactEmail, dossierTemplateNo, dossierNote,
				submissionNote, dossierNo, submitting, dossierStatusText,
				dossierSubStatusText, postalAddress, postalCityCode,
				postalDistrictCode, postalTelNo, serverNo);

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

			DossierTemplate dt = dossierTemplatePersistence.findByG_DT_TPLNO(
					groupId, dossierTemplateNo);

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

		}
		else {

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
	public Dossier updateDossier(
			long groupId, long dossierId, String referenceUid, int counter,
			String serviceCode, String serviceName, String govAgencyCode,
			String govAgencyName, String applicantName, String applicantIdType,
			String applicantIdNo, Date applicantIdDate, String address,
			String cityCode, String cityName, String districtCode,
			String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String contactEmail,
			String dossierTemplateNo, String dossierNote, String submissionNote,
			String applicantNote, String briefNote, String dossierNo,
			boolean submitting, Date correctingDate, String dossierStatus,
			String dossierStatusText, String dossierSubStatus,
			String dossierSubStatusText, long folderId, long dossierActionId,
			int viaPostal, String postalAddress, String postalCityCode,
			String postalCityName, String postalDistrictCode,
			String postalDistrictName, String postalTelNo, String password,
			boolean notification, boolean online, String serverNo, Date submitDate,
			ServiceContext context)
			throws PortalException {

		Date now = new Date();

		long userId = context.getUserId();

		User auditUser = userPersistence.fetchByPrimaryKey(userId);

		validateUpdateDossier(
				groupId, dossierId, referenceUid, serviceCode, govAgencyCode,
				address, cityCode, districtCode, wardCode, contactName,
				contactTelNo, contactEmail, dossierTemplateNo, dossierNote,
				submissionNote, dossierNo, submitting, dossierStatusText,
				dossierSubStatusText, postalAddress, postalCityCode, postalDistrictCode,
				postalTelNo, serverNo);

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

			DossierTemplate dt = dossierTemplatePersistence.findByG_DT_TPLNO(
					groupId, dossierTemplateNo);

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
			dossier.setPostalDistrictCode(postalDistrictCode);
			dossier.setPostalDistrictName(postalDistrictName);
			dossier.setPostalTelNo(postalTelNo);
			dossier.setPassword(password);
			dossier.setNotification(notification);
			dossier.setOnline(online);
			dossier.setServerNo(serverNo);
			dossier.setSubmitDate(submitDate);

		}
		else {

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
			dossier.setPostalDistrictCode(postalDistrictCode);
			dossier.setPostalDistrictName(postalDistrictName);
			dossier.setPostalTelNo(postalTelNo);
			dossier.setApplicantNote(applicantNote);
			dossier.setNotification(notification);

		}

		dossierPersistence.update(dossier);

		return dossier;
	}

	private static final String LOCK_ALL = "LOCK ALL";

	@Indexable(type = IndexableType.REINDEX)
	public Dossier submitting(
			long groupId, long id, String refId, ServiceContext context)
			throws PortalException {

		validateSubmitting(groupId, id, refId);

		Date now = new Date();

		Dossier dossier = null;

		if (id != 0) {
			dossier = dossierPersistence.fetchByPrimaryKey(id);
		}
		else {
			dossier = dossierPersistence.fetchByG_REF(groupId, refId);
		}

		dossier.setModifiedDate(now);

		dossier.setSubmitting(true);

		/*
		 * if (Validator.isNull(dossier.getSubmitDate())) {
		 * dossier.setSubmitDate(now); }
		 */
		if (dossier.getDossierStatus().contentEquals(
				DossierStatusConstants.NEW)) {
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
	public Dossier reset(
			long groupId, long id, String refId, ServiceContext context)
			throws PortalException {

		validateReset(groupId, id, refId);

		Date now = new Date();

		Dossier dossier = null;

		if (id != 0) {
			dossier = dossierPersistence.fetchByPrimaryKey(id);
		}
		else {
			dossier = dossierPersistence.fetchByG_REF(groupId, refId);
		}

		dossier.setModifiedDate(now);
		dossier.setSubmitting(false);
		// dossier.setSubmitDate(null);

		dossierPersistence.update(dossier);

		// TODO add reset for DossierFile and PaymentFile (isNew => false)

		// TODO add remove DossierFile out system

		List<DossierFile> lsDF =
				dossierFileLocalService.getDossierFilesByDossierId(id);

		for (DossierFile df : lsDF) {
			if (df.getIsNew()) {

				df.setIsNew(false);

				dossierFileLocalService.updateDossierFile(df);
			}
		}

		// List<PaymentFile> lsPF = paymentFileLocalService.getByDossierId(id);

		// for (PaymentFile pf : lsPF) {
		// if (pf.getIsNew()) {
		// pf.setIsNew(false);
		//
		// paymentFileLocalService.updatePaymentFile(pf);
		// }
		// }

		return dossier;
	}

	// @Indexable(type = IndexableType.REINDEX)
	public Dossier updateStatus(
			long groupId, long id, String refId, String status, String statusText,
			String subStatus, String subStatusText, String lockState,
			String stepInstruction, ServiceContext context)
			throws PortalException {

		validateUpdateStatus(
				groupId, id, refId, status, statusText, subStatus, subStatusText);

		Date now = new Date();

		Dossier dossier = null;

		if (id != 0) {
			dossier = dossierPersistence.findByPrimaryKey(id);
		}
		else {
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
	public Dossier updateProcessDate(
			long groupId, long id, String refId, Date date, ServiceContext context)
			throws PortalException {

		Date now = new Date();

		Dossier dossier = null;

		if (id != 0) {
			dossier = dossierPersistence.fetchByPrimaryKey(id);
		}
		else {
			dossier = dossierPersistence.fetchByG_REF(groupId, refId);
		}

		dossier.setModifiedDate(now);

		dossier.setProcessDate(date);

		return dossierPersistence.update(dossier);
	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateSubmittingDate(
			long groupId, long id, String refId, Date date, ServiceContext context)
			throws PortalException {

		validateSubmittingDate(groupId, id, refId, date);

		Date now = new Date();

		Dossier dossier = null;

		if (id != 0) {
			dossier = dossierPersistence.fetchByPrimaryKey(id);
		}
		else {
			dossier = dossierPersistence.fetchByG_REF(groupId, refId);
		}

		dossier.setModifiedDate(now);

		dossier.setSubmitDate(date);

		return dossierPersistence.update(dossier);
	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateReceivingDate(
			long groupId, long id, String refId, Date date, ServiceContext context)
			throws PortalException {

		validateReceivingDate(groupId, id, refId, date);

		Date now = new Date();

		Dossier dossier = null;

		if (id != 0) {
			dossier = dossierPersistence.fetchByPrimaryKey(id);
		}
		else {
			dossier = dossierPersistence.fetchByG_REF(groupId, refId);
		}

		dossier.setModifiedDate(now);

		dossier.setReceiveDate(date);

		return dossierPersistence.update(dossier);
	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateDueDate(
			long groupId, long id, String refId, Date date, ServiceContext context)
			throws PortalException {

		validateDueDate(groupId, id, refId, date);

		Date now = new Date();

		Dossier dossier = null;

		if (id != 0) {
			dossier = dossierPersistence.fetchByPrimaryKey(id);
		}
		else {
			dossier = dossierPersistence.fetchByG_REF(groupId, refId);
		}

		dossier.setModifiedDate(now);

		dossier.setDueDate(date);

		return dossierPersistence.update(dossier);
	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateReleaseDate(
			long groupId, long id, String refId, Date date, ServiceContext context)
			throws PortalException {

		validateReleaseDate(groupId, id, refId, date);

		Date now = new Date();

		Dossier dossier = null;

		if (id != 0) {
			dossier = dossierPersistence.fetchByPrimaryKey(id);
		}
		else {
			dossier = dossierPersistence.fetchByG_REF(groupId, refId);
		}

		dossier.setModifiedDate(now);

		dossier.setReleaseDate(date);

		return dossierPersistence.update(dossier);
	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateCancellingDate(
			long groupId, long id, String refId, Date date, ServiceContext context)
			throws PortalException {

		validateCancellingDate(groupId, id, refId, date);

		Date now = new Date();

		Dossier dossier = null;

		if (id != 0) {
			dossier = dossierPersistence.fetchByPrimaryKey(id);
		}
		else {
			dossier = dossierPersistence.fetchByG_REF(groupId, refId);
		}

		dossier.setModifiedDate(now);

		dossier.setCancellingDate(date);

		dossier.setSubmitting(true);

		return dossierPersistence.update(dossier);
	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateEndosementDate(
			long groupId, long id, String refId, Date date, ServiceContext context)
			throws PortalException {

		validateCancellingDate(groupId, id, refId, date);

		Date now = new Date();

		Dossier dossier = null;

		if (id != 0) {
			dossier = dossierPersistence.fetchByPrimaryKey(id);
		}
		else {
			dossier = dossierPersistence.fetchByG_REF(groupId, refId);
		}

		dossier.setModifiedDate(now);

		// dossier.setCancellingDate(date);

		dossier.setEndorsementDate(date);

		dossier.setSubmitting(true);

		return dossierPersistence.update(dossier);
	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateFinishDate(
			long groupId, long id, String refId, Date date, ServiceContext context)
			throws PortalException {

		validateFinishDate(groupId, id, refId, date);

		Date now = new Date();

		Dossier dossier = null;

		if (id != 0) {
			dossier = dossierPersistence.fetchByPrimaryKey(id);
		}
		else {
			dossier = dossierPersistence.fetchByG_REF(groupId, refId);
		}

		dossier.setModifiedDate(now);

		dossier.setFinishDate(date);

		return dossierPersistence.update(dossier);
	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateCorrectingDate(
			long groupId, long id, String refId, Date date, ServiceContext context)
			throws PortalException {

		validateCorrectingDate(groupId, id, refId, date);

		Date now = new Date();

		Dossier dossier = null;

		if (id != 0) {
			dossier = dossierPersistence.fetchByPrimaryKey(id);
		}
		else {
			dossier = dossierPersistence.fetchByG_REF(groupId, refId);
		}

		dossier.setModifiedDate(now);

		dossier.setCorrecttingDate(date);

		dossier.setSubmitting(true);

		return dossierPersistence.update(dossier);
	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateGroupDossier(
			Dossier dossier, String groupDossierId)
			throws PortalException {

		dossier.setGroupDossierId(groupDossierId);

		return dossierPersistence.update(dossier);
	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateDossierAction(
			long groupId, long id, String refId, long dossierActionId,
			ServiceContext context)
			throws PortalException {

		validateDossierAction(groupId, id, refId, dossierActionId);

		Date now = new Date();

		Dossier dossier = null;

		if (id != 0) {
			dossier = dossierPersistence.fetchByPrimaryKey(id);
		}
		else {
			dossier = dossierPersistence.fetchByG_REF(groupId, refId);
		}

		dossier.setModifiedDate(now);

		dossier.setDossierActionId(dossierActionId);

		dossierPersistence.update(dossier);

		return dossier;

	}

	// sondt start
	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateViaPostal(
			long groupId, long id, String refId, int viaPostal,
			ServiceContext context)
			throws PortalException {

		validateViaPostal(groupId, id, refId, viaPostal);

		Date now = new Date();

		Dossier dossier = null;

		if (id != 0) {
			dossier = dossierPersistence.fetchByPrimaryKey(id);
		}
		else {
			dossier = dossierPersistence.fetchByG_REF(groupId, refId);
		}

		dossier.setModifiedDate(now);

		dossier.setViaPostal(viaPostal);

		dossierPersistence.update(dossier);

		return dossier;

	}
	// sondt end

	public Dossier getByRef(long groupId, String refId) {
		return dossierPersistence.fetchByG_REF(groupId, refId);
	}

	@Indexable(type = IndexableType.DELETE)
	public Dossier removeDossier(long groupId, long dossierId, String refId)
			throws PortalException {
		// TODO remove dossierLog

		// TODO remove dossierFile

		// TODO remove dossierAction

		// TODO remove PaymentFile

		validateRemoveDossier(groupId, dossierId, refId);

		Dossier dossier = null;

		if (dossierId != 0) {
			dossier = dossierPersistence.fetchByPrimaryKey(dossierId);
		}
		else {
			dossier = dossierPersistence.fetchByG_REF(groupId, refId);
		}

		return dossierPersistence.remove(dossier);

	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateDossierBriefNote(
			long dossierId, String dossierBriefNote)
			throws PortalException {

		Dossier dossier = dossierPersistence.findByPrimaryKey(dossierId);

		dossier.setBriefNote(dossierBriefNote);

		return dossierPersistence.update(dossier);

	}

	public int countByUserId(long userId, long groupId) {

		return dossierPersistence.countByG_UID(groupId, userId);
	}

	private void validateViaPostal(
			long groupId, long id, String refId, int viaPostal)
			throws PortalException {

		// TODO add validate for submitting
	}

	private void validateRemoveDossier(
			long groupId, long dossierId, String refId)
			throws PortalException {

		// TODO add validate for remove Dossier
	}

	private void validateDossierAction(
			long groupId, long id, String refId, long dossierActionId)
			throws PortalException {

		// TODO add validate for submitting
	}

	private void validateSubmittingDate(
			long groupId, long id, String refId, Date date)
			throws PortalException {

		// TODO add validate
	}

	private void validateReceivingDate(
			long groupId, long id, String refId, Date date)
			throws PortalException {

		// TODO add validate
	}

	private void validateReleaseDate(
			long groupId, long id, String refId, Date date)
			throws PortalException {

		// TODO add validate
	}

	private void validateFinishDate(
			long groupId, long id, String refId, Date date)
			throws PortalException {

		// TODO add validate
	}

	private void validateCancellingDate(
			long groupId, long id, String refId, Date date)
			throws PortalException {

		// TODO add validate
	}

	private void validateDueDate(long groupId, long id, String refId, Date date)
			throws PortalException {

		// TODO add validate
	}

	private void validateCorrectingDate(
			long groupId, long id, String refId, Date date)
			throws PortalException {

		// TODO add validate
	}

	private void validateUpdateStatus(
			long groupId, long id, String refId, String status, String statusText,
			String subStatus, String subStatusText)
			throws PortalException {

		// TODO add validate
	}

	private void validateSubmitting(long groupId, long id, String refId)
			throws PortalException {
		// TODO add validate for submitting

		// Check dossier status

		// Check DossierFile, PaymentFile

	}

	private void validateReset(long groupId, long id, String refId)
			throws PortalException {

		// TODO add validate for submitting
	}

	private void validateInit(
			long groupId, long dossierId, String referenceUid, String serviceCode,
			String govAgencyCode, String address, String cityCode,
			String districtCode, String wardCode, String contactName,
			String contactTelNo, String contactEmail, String dossierTemplateNo)
			throws PortalException {

	}

	private void validateUpdateDossier(
			long groupId, long dossierId, String referenceUid, String serviceCode,
			String govAgencyCode, String address, String cityCode,
			String districtCode, String wardCode, String contactName,
			String contactTelNo, String contactEmail, String dossierTemplateNo,
			String dossierNote, String submissionNote, String dossierNo,
			boolean submitting, String dossierStatus, String dossierSubStatus,
			String postalAddress, String postalCityCode, String postalDistrictCode,
			String postalTelNo, String serverNo)
			throws PortalException {

	}

	public Document getDossierById(long dossierId, long companyId)
			throws PortalException {
		// Document document = null;

		Indexer<Dossier> indexer =
				IndexerRegistryUtil.nullSafeGetIndexer(Dossier.class);

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
			MultiMatchQuery query =
					new MultiMatchQuery(String.valueOf(dossierId));

			query.addField(DossierTerm.DOSSIER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		Hits hits = IndexSearcherHelperUtil.search(searchContext, booleanQuery);

		List<Document> documents = hits.toList();

		if (documents.size() > 0) {
			return documents.get(0);
		}
		else {
			return null;
		}

	}

	@SuppressWarnings("deprecation")
	public Hits searchLucene(
			LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext)
			throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);
		String secetKey = GetterUtil.getString(params.get("secetKey"));

		String status = GetterUtil.getString(params.get(DossierTerm.STATUS));
		String subStatus =
				GetterUtil.getString(params.get(DossierTerm.SUBSTATUS));
		String agencys = GetterUtil.getString(params.get(DossierTerm.AGENCYS));
		String service = GetterUtil.getString(params.get(DossierTerm.SERVICE));
		String template =
				GetterUtil.getString(params.get(DossierTerm.TEMPLATE));
		String step = GetterUtil.getString(params.get(DossierTerm.STEP));
		String state = GetterUtil.getString(params.get(DossierTerm.STATE));
		String follow = GetterUtil.getString(params.get(DossierTerm.FOLLOW));
		String dossierNo =
				GetterUtil.getString(params.get(DossierTerm.DOSSIER_NO));
		// Get by certificate number
		String certificateNo = (String) params.get(DossierTerm.DOSSIER_ID_CTN);
		String top = GetterUtil.getString(params.get(DossierTerm.TOP));
		String owner = GetterUtil.getString(params.get(DossierTerm.OWNER));
		String submitting =
				GetterUtil.getString(params.get(DossierTerm.SUBMITTING));
		int year = GetterUtil.getInteger(params.get(DossierTerm.YEAR));
		int month = GetterUtil.getInteger(params.get(DossierTerm.MONTH));
		int day = GetterUtil.getInteger(params.get(DossierTerm.DAY));
		long userId = GetterUtil.getLong(params.get(DossierTerm.USER_ID));
		String strDossierActionId =
				GetterUtil.getString(params.get(DossierTerm.DOSSIER_ACTION_ID));

		String fromReceiveDate =
				GetterUtil.getString(params.get(DossierTerm.FROM_RECEIVEDATE));
		String toReceiveDate =
				GetterUtil.getString(params.get(DossierTerm.TO_RECEIVEDATE));
		String certNo = GetterUtil.getString(params.get(DossierTerm.CERT_NO));
		String fromCertDate =
				GetterUtil.getString(params.get(DossierTerm.FROM_CERT_DATE));
		String toCertDate =
				GetterUtil.getString(params.get(DossierTerm.TO_CERT_DATE));
		String fromSubmitDate =
				GetterUtil.getString(params.get(DossierTerm.FROM_SUBMIT_DATE));
		String toSubmitDate =
				GetterUtil.getString(params.get(DossierTerm.TO_SUBMIT_DATE));
		String notState =
				GetterUtil.getString(params.get(DossierTerm.NOT_STATE));
		Long statusReg = GetterUtil.getLong(params.get(DossierTerm.STATUS_REG));
		Long notStatusReg =
				GetterUtil.getLong(params.get(DossierTerm.NOT_STATUS_REG));
		String online = GetterUtil.getString(params.get(DossierTerm.ONLINE));
		String originality =
				GetterUtil.getString(params.get(DossierTerm.ORIGINALLITY));
		String assigned =
				GetterUtil.getString(params.get(DossierTerm.ASSIGNED));
		// LamTV_ADD
		String statusStep =
				GetterUtil.getString(params.get(DossierTerm.DOSSIER_STATUS_STEP));
		String subStatusStep = GetterUtil.getString(
				params.get(DossierTerm.DOSSIER_SUBSTATUS_STEP));
		String permission =
				GetterUtil.getString(params.get(DossierTerm.MAPPING_PERMISSION));
		String domain =
				GetterUtil.getString(params.get(DossierTerm.DOMAIN_CODE));
		String domainName =
				GetterUtil.getString(params.get(DossierTerm.DOMAIN_NAME));
		String applicantName =
				GetterUtil.getString(params.get(DossierTerm.APPLICANT_NAME));
		String applicantIdNo =
				GetterUtil.getString(params.get(DossierTerm.APPLICANT_ID_NO));
		String serviceName =
				GetterUtil.getString(params.get(DossierTerm.SERVICE_NAME));
		String emailLogin =
				GetterUtil.getString(params.get(DossierTerm.EMAIL_USER_LOGIN));
		String fromReleaseDate =
				GetterUtil.getString(params.get(DossierTerm.FROM_RELEASE_DATE));
		String toReleaseDate =
				GetterUtil.getString(params.get(DossierTerm.TO_RELEASE_DATE));
		//
		String fromFinishDate =
				GetterUtil.getString(params.get(DossierTerm.FROM_FINISH_DATE));
		String toFinishDate =
				GetterUtil.getString(params.get(DossierTerm.TO_FINISH_DATE));
		String fromReceiveNotDoneDate = GetterUtil.getString(
				params.get(DossierTerm.FROM_RECEIVE_NOTDONE_DATE));
		String toReceiveNotDoneDate = GetterUtil.getString(
				params.get(DossierTerm.TO_RECEIVE_NOTDONE_DATE));
		String paymentStatus =
				GetterUtil.getString(params.get(PaymentFileTerm.PAYMENT_STATUS));
		String origin = GetterUtil.getString(params.get(DossierTerm.ORIGIN));

		String fromDueDate =
				GetterUtil.getString(params.get(DossierTerm.FROM_DUEDATE));
		String toDueDate =
				GetterUtil.getString(params.get(DossierTerm.TO_DUEDATE));

		String backlogDate =
				GetterUtil.getString(params.get(DossierTerm.TO_BACKLOGDATE));
		Integer backlog =
				GetterUtil.getInteger(params.get(DossierTerm.BACKLOG));

		String fromStatisticDate =
				GetterUtil.getString(params.get(DossierTerm.FROM_STATISTIC_DATE));
		String toStatisticDate =
				GetterUtil.getString(params.get(DossierTerm.TO_STATISTIC_DATE));
		Integer originDossierId =
				(params.get(DossierTerm.ORIGIN_DOSSIER_ID) != null)
						? GetterUtil.getInteger(
						params.get(DossierTerm.ORIGIN_DOSSIER_ID))
						: null;
		String time = GetterUtil.getString(params.get(DossierTerm.TIME));
		String register =
				GetterUtil.getString(params.get(DossierTerm.REGISTER));
		String groupDossierId =
				GetterUtil.getString(params.get(DossierTerm.GROUP_DOSSIER_ID));
		String applicantFollowIdNo = GetterUtil.getString(
				params.get(DossierTerm.APPLICANT_FOLLOW_ID_NO));
		String assignedUserId =
				GetterUtil.getString(params.get(DossierTerm.ASSIGNED_USER_ID));
		String assignedUserIdSearch =
				GetterUtil.getString(params.get(DossierTerm.ASSIGNED_USER_ID_SEARCH));

		// Delegate
		Integer delegateType = params.get(DossierTerm.DELEGATE_TYPE) != null
				? GetterUtil.getInteger(params.get(DossierTerm.DELEGATE_TYPE))
				: null;
		String documentNo =
				GetterUtil.getString(params.get(DossierTerm.DOCUMENT_NO));
		String documentDate =
				GetterUtil.getString(params.get(DossierTerm.DOCUMENT_DATE));
		String strSystemId =
				GetterUtil.getString(params.get(DossierTerm.SYSTEM_ID));
		String viaPostal = params.get(DossierTerm.VIA_POSTAL) != null
				? GetterUtil.getString(params.get(DossierTerm.VIA_POSTAL)) : null;
		String dossierCounterSearch = GetterUtil.getString(params.get(DossierTerm.DOSSIER_COUNTER_SEARCH));
		String delegate = GetterUtil.getString(params.get(DossierTerm.DELEGATE));
		String vnpostalStatus = params.get(DossierTerm.VNPOSTAL_STATUS) != null
				? GetterUtil.getString(params.get(DossierTerm.VNPOSTAL_STATUS)) : null;
		Integer fromViaPostal = params.get(DossierTerm.FROM_VIA_POSTAL) != null
				? GetterUtil.getInteger(params.get(DossierTerm.FROM_VIA_POSTAL)) : null;

		String donvigui = params.get(DossierTerm.DON_VI_GUI) !=null
				? GetterUtil.getString(params.get(DossierTerm.DON_VI_GUI)) : null;

		String donvinhan = params.get(DossierTerm.DON_VI_NHAN) !=null
				? GetterUtil.getString(params.get(DossierTerm.DON_VI_NHAN)) : null;
		String groupDossierIdHs =
				GetterUtil.getString(params.get(DossierTerm.GROUP_DOSSIER_ID_HS));
		String matokhai = GetterUtil.getString(params.get(DossierTerm.MA_TO_KHAI));
		String serviceLevel = params.get(DossierTerm.SERVICE_LEVEL) != null
				? GetterUtil.getString(params.get(DossierTerm.SERVICE_LEVEL)) : null;
		Indexer<Dossier> indexer =
				IndexerRegistryUtil.nullSafeGetIndexer(Dossier.class);

		searchContext.addFullQueryEntryClassName(CLASS_NAME);
		searchContext.setEntryClassNames(new String[] {
				CLASS_NAME
		});
		searchContext.setAttribute(ConstantsTerm.PAGINATION_TYPE, ConstantsTerm.REGULAR);
		searchContext.setLike(true);
		searchContext.setStart(start);
		searchContext.setEnd(end);
		searchContext.setAndSearch(true);
		searchContext.setSorts(sorts);

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		}
		else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		// Search follow params default
		BooleanQuery booleanCommon = processSearchCommon(
				keywords, secetKey, groupId, owner, userId, follow, step, template,
				top, emailLogin, originality, applicantFollowIdNo, booleanQuery);
		// Search follow param input
		BooleanQuery booleanInput = processSearchInput(
				status, subStatus, state, online, submitting, agencys, service,
				userId, top, year, month, dossierNo, certificateNo,
				strDossierActionId, fromReceiveDate, toReceiveDate, certNo,
				fromCertDate, toCertDate, fromSubmitDate, toSubmitDate, notState,
				statusReg, notStatusReg, follow, originality, assigned, statusStep,
				subStatusStep, permission, domain, domainName, applicantName,
				applicantIdNo, serviceName, fromReleaseDate, toReleaseDate,
				fromFinishDate, toFinishDate, fromDueDate, toDueDate, fromReceiveNotDoneDate,
				toReceiveNotDoneDate, paymentStatus, origin, fromStatisticDate,
				toStatisticDate, originDossierId, time, register, day,
				groupDossierId, assignedUserId, assignedUserIdSearch, delegateType, documentNo,
				documentDate, strSystemId, viaPostal, backlogDate, backlog, dossierCounterSearch,
				delegate, vnpostalStatus, fromViaPostal,
				booleanCommon,donvigui,donvinhan,groupDossierIdHs,matokhai,serviceLevel);

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.search(searchContext, booleanInput);
	}

	@SuppressWarnings("deprecation")
	public long countLucene(
			LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);
		String secetKey = GetterUtil.getString(params.get("secetKey"));
		String status = GetterUtil.getString(params.get(DossierTerm.STATUS));
		String subStatus =
				GetterUtil.getString(params.get(DossierTerm.SUBSTATUS));
		String agencys = GetterUtil.getString(params.get(DossierTerm.AGENCYS));
		String service = GetterUtil.getString(params.get(DossierTerm.SERVICE));
		String template =
				GetterUtil.getString(params.get(DossierTerm.TEMPLATE));
		String state = GetterUtil.getString(params.get(DossierTerm.STATE));
		String step = GetterUtil.getString(params.get(DossierTerm.STEP));
		String dossierNo =
				GetterUtil.getString(params.get(DossierTerm.DOSSIER_NO));
		// Get by certificate number
		String certificateNo = (String) params.get(DossierTerm.DOSSIER_ID_CTN);
		String online = GetterUtil.getString(params.get(DossierTerm.ONLINE));
		String follow = GetterUtil.getString(params.get(DossierTerm.FOLLOW));
		String top = GetterUtil.getString(params.get(DossierTerm.TOP));
		String owner = GetterUtil.getString(params.get(DossierTerm.OWNER));
		String submitting =
				GetterUtil.getString(params.get(DossierTerm.SUBMITTING));
		long userId = GetterUtil.getLong(params.get(DossierTerm.USER_ID));

		int year = GetterUtil.getInteger(params.get(DossierTerm.YEAR));
		int month = GetterUtil.getInteger(params.get(DossierTerm.MONTH));
		int day = GetterUtil.getInteger(params.get(DossierTerm.DAY));

		String strDossierActionId =
				GetterUtil.getString(params.get(DossierTerm.DOSSIER_ACTION_ID));
		String fromReceiveDate =
				GetterUtil.getString(params.get(DossierTerm.FROM_RECEIVEDATE));
		String toReceiveDate =
				GetterUtil.getString(params.get(DossierTerm.TO_RECEIVEDATE));
		String certNo = GetterUtil.getString(params.get(DossierTerm.CERT_NO));
		String fromCertDate =
				GetterUtil.getString(params.get(DossierTerm.FROM_CERT_DATE));
		String toCertDate =
				GetterUtil.getString(params.get(DossierTerm.TO_CERT_DATE));
		String fromSubmitDate =
				GetterUtil.getString(params.get(DossierTerm.FROM_SUBMIT_DATE));
		String toSubmitDate =
				GetterUtil.getString(params.get(DossierTerm.TO_SUBMIT_DATE));
		String notState =
				GetterUtil.getString(params.get(DossierTerm.NOT_STATE));
		Long statusReg = GetterUtil.getLong(params.get(DossierTerm.STATUS_REG));
		Long notStatusReg =
				GetterUtil.getLong(params.get(DossierTerm.NOT_STATUS_REG));
		String originality =
				GetterUtil.getString(params.get(DossierTerm.ORIGINALLITY));
		String assigned =
				GetterUtil.getString(params.get(DossierTerm.ASSIGNED));
		// LamTV_ADD
		String statusStep =
				GetterUtil.getString(params.get(DossierTerm.DOSSIER_STATUS_STEP));
		String subStatusStep = GetterUtil.getString(
				params.get(DossierTerm.DOSSIER_SUBSTATUS_STEP));
		String permission =
				GetterUtil.getString(params.get(DossierTerm.MAPPING_PERMISSION));
		String domain =
				GetterUtil.getString(params.get(DossierTerm.DOMAIN_CODE));
		String domainName =
				GetterUtil.getString(params.get(DossierTerm.DOMAIN_NAME));
		String applicantName =
				GetterUtil.getString(params.get(DossierTerm.APPLICANT_NAME));
		String applicantIdNo =
				GetterUtil.getString(params.get(DossierTerm.APPLICANT_ID_NO));
		String serviceName =
				GetterUtil.getString(params.get(DossierTerm.SERVICE_NAME));
		String emailLogin =
				GetterUtil.getString(params.get(DossierTerm.EMAIL_USER_LOGIN));
		String fromReleaseDate =
				GetterUtil.getString(params.get(DossierTerm.FROM_RELEASE_DATE));
		String toReleaseDate =
				GetterUtil.getString(params.get(DossierTerm.TO_RELEASE_DATE));
		//
		String fromFinishDate =
				GetterUtil.getString(params.get(DossierTerm.FROM_FINISH_DATE));
		String toFinishDate =
				GetterUtil.getString(params.get(DossierTerm.TO_FINISH_DATE));
		String fromReceiveNotDoneDate = GetterUtil.getString(
				params.get(DossierTerm.FROM_RECEIVE_NOTDONE_DATE));
		String toReceiveNotDoneDate = GetterUtil.getString(
				params.get(DossierTerm.TO_RECEIVE_NOTDONE_DATE));
		String paymentStatus =
				GetterUtil.getString(params.get(PaymentFileTerm.PAYMENT_STATUS));
		//
		String fromStatisticDate =
				GetterUtil.getString(params.get(DossierTerm.FROM_STATISTIC_DATE));
		String toStatisticDate =
				GetterUtil.getString(params.get(DossierTerm.TO_STATISTIC_DATE));
		String origin = GetterUtil.getString(params.get(DossierTerm.ORIGIN));

		String fromDueDate =
				GetterUtil.getString(params.get(DossierTerm.FROM_DUEDATE));
		String toDueDate =
				GetterUtil.getString(params.get(DossierTerm.TO_DUEDATE));

		String backlogDate =
				GetterUtil.getString(params.get(DossierTerm.TO_BACKLOGDATE));
		Integer backlog =
				GetterUtil.getInteger(params.get(DossierTerm.BACKLOG));

		Integer originDossierId =
				(params.get(DossierTerm.ORIGIN_DOSSIER_ID) != null
						? GetterUtil.getInteger(
						params.get(DossierTerm.ORIGIN_DOSSIER_ID))
						: null);
		String time = GetterUtil.getString(params.get(DossierTerm.TIME));
		String register =
				GetterUtil.getString(params.get(DossierTerm.REGISTER));
		String groupDossierId =
				GetterUtil.getString(params.get(DossierTerm.GROUP_DOSSIER_ID));
		String applicantFollowIdNo = GetterUtil.getString(
				params.get(DossierTerm.APPLICANT_FOLLOW_ID_NO));
		String assignedUserId =
				GetterUtil.getString(params.get(DossierTerm.ASSIGNED_USER_ID));
		String assignedUserIdSearch =
				GetterUtil.getString(params.get(DossierTerm.ASSIGNED_USER_ID_SEARCH));

		// Delegate
		Integer delegateType = params.get(DossierTerm.DELEGATE_TYPE) != null
				? GetterUtil.getInteger(params.get(DossierTerm.DELEGATE_TYPE))
				: null;
		String documentNo =
				GetterUtil.getString(params.get(DossierTerm.DOCUMENT_NO));
		String documentDate =
				GetterUtil.getString(params.get(DossierTerm.DOCUMENT_DATE));
		String strSystemId =
				GetterUtil.getString(params.get(DossierTerm.SYSTEM_ID));
		String viaPostal = params.get(DossierTerm.VIA_POSTAL) != null
				? GetterUtil.getString(params.get(DossierTerm.VIA_POSTAL)) : null;
		String dossierCounterSearch = GetterUtil.getString(params.get(DossierTerm.DOSSIER_COUNTER_SEARCH));
		String delegate = GetterUtil.getString(params.get(DossierTerm.DELEGATE));

		String vnpostalStatus = params.get(DossierTerm.VNPOSTAL_STATUS) != null
				? GetterUtil.getString(params.get(DossierTerm.VNPOSTAL_STATUS))
				: null;

		Integer fromViaPostal = params.get(DossierTerm.FROM_VIA_POSTAL) != null
				? GetterUtil.getInteger(params.get(DossierTerm.FROM_VIA_POSTAL))
				: null;

		String donvigui = params.get(DossierTerm.DON_VI_GUI) != null
				? GetterUtil.getString(params.get(DossierTerm.DON_VI_GUI))
				: null;
		String donvinhan = params.get(DossierTerm.DON_VI_NHAN) != null
				? GetterUtil.getString(params.get(DossierTerm.DON_VI_NHAN))
				: null;
		String groupDossierIdHs =
				GetterUtil.getString(params.get(DossierTerm.GROUP_DOSSIER_ID_HS)) != null
						? GetterUtil.getString(params.get(DossierTerm.GROUP_DOSSIER_ID_HS))
						: null;
		String matokhai =  params.get(DossierTerm.MA_TO_KHAI) != null
				? GetterUtil.getString(params.get(DossierTerm.MA_TO_KHAI))
				: null;
		String serviceLevel = params.get(DossierTerm.SERVICE_LEVEL) != null
				? GetterUtil.getString(params.get(DossierTerm.SERVICE_LEVEL)) : null;
		Indexer<Dossier> indexer =
				IndexerRegistryUtil.nullSafeGetIndexer(Dossier.class);

		searchContext.addFullQueryEntryClassName(CLASS_NAME);
		searchContext.setEntryClassNames(new String[] {
				CLASS_NAME
		});
		searchContext.setAttribute(ConstantsTerm.PAGINATION_TYPE, ConstantsTerm.REGULAR);
		searchContext.setLike(true);
		searchContext.setAndSearch(true);

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		}
		else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		// Search follow params default
		BooleanQuery booleanCommon = processSearchCommon(
				keywords, secetKey, groupId, owner, userId, follow, step, template,
				top, emailLogin, originality, applicantFollowIdNo, booleanQuery);
		// Search follow param input
		BooleanQuery booleanInput = processSearchInput(
				status, subStatus, state, online, submitting, agencys, service,
				userId, top, year, month, dossierNo, certificateNo,
				strDossierActionId, fromReceiveDate, toReceiveDate, certNo,
				fromCertDate, toCertDate, fromSubmitDate, toSubmitDate, notState,
				statusReg, notStatusReg, follow, originality, assigned, statusStep,
				subStatusStep, permission, domain, domainName, applicantName,
				applicantIdNo, serviceName, fromReleaseDate, toReleaseDate,
				fromFinishDate, toFinishDate, fromDueDate, toDueDate, fromReceiveNotDoneDate,
				toReceiveNotDoneDate, paymentStatus, origin, fromStatisticDate,
				toStatisticDate, originDossierId, time, register, day,
				groupDossierId, assignedUserId, assignedUserIdSearch, delegateType, documentNo,
				documentDate, strSystemId, viaPostal, backlogDate, backlog, dossierCounterSearch,
				delegate, vnpostalStatus, fromViaPostal,
				booleanCommon,donvigui,donvinhan,groupDossierIdHs,matokhai,serviceLevel);

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanInput);
	}

	private BooleanQuery processSearchCommon(
			String keywords, String secetKey, String groupId, String owner,
			long userId, String follow, String step, String template, String top,
			String emailLogin, String originality, String applicantFollowIdNo,
			BooleanQuery booleanQuery)
			throws ParseException {

		// LamTV: Process search LIKE
		if (Validator.isNotNull(keywords)) {
			BooleanQuery queryBool = new BooleanQueryImpl();
			String[] subQuerieArr = new String[] {
					DossierTerm.SERVICE_NAME_SEARCH, DossierTerm.APPLICANT_NAME,
					DossierTerm.DOSSIER_NO_SEARCH, DossierTerm.DOSSIER_ID_CTN,
					DossierTerm.BRIEF_NOTE, DossierTerm.DOSSIER_NAME_SEARCH,
					DossierTerm.CURRENT_ACTION_USER,
					DossierTerm.ORIGIN_DOSSIER_NO_SEARCH,
					ServiceInfoTerm.SERVICE_CODE_SEARCH,
					DossierTerm.DELEGATE_NAME_SEARCH, DossierTerm.DOSSIER_COUNTER_SEARCH
			};

			String[] keywordArr = keywords.split(StringPool.SPACE);
			for (String fieldSearch : subQuerieArr) {
				BooleanQuery query = new BooleanQueryImpl();
				for (String key : keywordArr) {
					WildcardQuery wildQuery = new WildcardQueryImpl(
							fieldSearch,
							StringPool.STAR + key.toLowerCase() + StringPool.STAR);
					query.add(wildQuery, BooleanClauseOccur.MUST);
				}
				queryBool.add(query, BooleanClauseOccur.SHOULD);
			}
			booleanQuery.add(queryBool, BooleanClauseOccur.MUST);
		}

		if (!(Validator.isNotNull(secetKey) &&
				secetKey.contentEquals("OPENCPSV2"))) {
			if (Validator.isNotNull(groupId)) {
				MultiMatchQuery query = new MultiMatchQuery(groupId);
				query.addFields(Field.GROUP_ID);
				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
		}

		if (Validator.isNotNull(owner) && Boolean.parseBoolean(owner) &&
				userId > 0) {
			MultiMatchQuery query = new MultiMatchQuery(String.valueOf(userId));
			query.addField(DossierTerm.USER_ID);
			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		if (Validator.isNotNull(follow) && Boolean.parseBoolean(follow) &&
				userId > 0) {
			if (Validator.isNotNull(originality) &&
					Long.valueOf(originality) == DossierTerm.ORIGINALITY_PUBLISH) {
				// _log.info("applicantFollowIdNo: "+applicantFollowIdNo);
				MultiMatchQuery query =
						new MultiMatchQuery(applicantFollowIdNo);

				query.addField(DossierTerm.APPLICANT_ID_NO);
				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
			else {
				MultiMatchQuery query =
						new MultiMatchQuery(String.valueOf(userId));
				query.addField(DossierTerm.ACTION_MAPPING_USERID);
				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
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
			}
			else {
				MultiMatchQuery query = new MultiMatchQuery(step);
				query.addFields(DossierTerm.STEP_CODE);
				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
		}

		if (Validator.isNotNull(template)) {
			MultiMatchQuery query = new MultiMatchQuery(template);
			query.addFields(DossierTerm.TEMPLATE);
			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		// //OriginDossierId = 0
		// MultiMatchQuery queryOrigin = new MultiMatchQuery(String.valueOf(0));
		// queryOrigin.addField(DossierTerm.ORIGIN_DOSSIER_ID);
		// booleanQuery.add(queryOrigin, BooleanClauseOccur.MUST);

		return booleanQuery;
	}

	private BooleanQuery processSearchInput(
			String status, String subStatus, String state, String online,
			String submitting, String agencys, String service, long userId,
			String top, int year, int month, String dossierNo, String certificateNo,
			String strDossierActionId, String fromReceiveDate, String toReceiveDate,
			String certNo, String fromCertDate, String toCertDate,
			String fromSubmitDate, String toSubmitDate, String notState,
			Long statusReg, Long notStatusReg, String follow, String originality,
			String assigned, String statusStep, String subStatusStep,
			String permission, String domain, String domainName,
			String applicantName, String applicantIdNo, String serviceName,
			String fromReleaseDate, String toReleaseDate, String fromFinishDate,
			String toFinishDate, String fromDueDate, String toDueDate, String fromReceiveNotDoneDate,
			String toReceiveNotDoneDate, String paymentStatus, String origin,
			String fromStatisticDate, String toStatisticDate,
			Integer originDossierId, String time, String register, int day,
			String groupDossierId, String assignedUserId, String assignedUserIdSearch, Integer delegateType,
			String documentNo, String documentDate, String strSystemId,
			String viaPostal, String backlogDate, Integer backlog, String dossierCounterSearch,
			String delegate, String vnpostalStatus, Integer fromViaPostal,
			BooleanQuery booleanQuery,String donvigui, String donvinhan,String groupDossierIdHs,String matokhai, String serviceLevel)
			throws ParseException {

		//Dossier Counter
		if (Validator.isNotNull(dossierCounterSearch)) {
			MultiMatchQuery query =
					new MultiMatchQuery(dossierCounterSearch);
			query.addField(DossierTerm.DOSSIER_COUNTER);
			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}
		// viaPostal
//		if (Validator.isNotNull(viaPostal)) {
//			String[] viaPostalArr = viaPostal.split(StringPool.COMMA);
//			BooleanQuery subQuery = new BooleanQueryImpl();
//			for (String key : viaPostalArr) {
//				MultiMatchQuery query = new MultiMatchQuery(key);
//				query.addField(DossierTerm.VIA_POSTAL);
//				subQuery.add(query, BooleanClauseOccur.SHOULD);
//			}
//			booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
//		}
		if (Validator.isNotNull(viaPostal)) {

			String[] viaPostalArr = StringUtil.split(viaPostal);
			BooleanQuery viaPostalSubQuery = new BooleanQueryImpl();
			for (String gov : viaPostalArr) {
				MultiMatchQuery query = new MultiMatchQuery(gov);
				query.addFields(DossierTerm.VIA_POSTAL);
				viaPostalSubQuery.add(query, BooleanClauseOccur.SHOULD);
			}
			booleanQuery.add(viaPostalSubQuery, BooleanClauseOccur.MUST);
		}

		// vnpostalStatus
		if (Validator.isNotNull(vnpostalStatus)) {
			String[] statusArr = vnpostalStatus.split(StringPool.COMMA);
			BooleanQuery subQuery = new BooleanQueryImpl();
			for (String key : statusArr) {
				MultiMatchQuery query = new MultiMatchQuery(key);
				query.addField(DossierTerm.VNPOSTAL_STATUS);
				subQuery.add(query, BooleanClauseOccur.SHOULD);
			}
			booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
		}
		if (Validator.isNotNull(fromViaPostal)) {
			MultiMatchQuery query =
					new MultiMatchQuery(String.valueOf(fromViaPostal));
			query.addField(DossierTerm.FROM_VIA_POSTAL);
			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}
		if (Validator.isNotNull(donvigui)) {
			String[] keywordArr = donvigui.split(StringPool.COMMA);
			BooleanQuery subQuery = new BooleanQueryImpl();
			for (String key : keywordArr) {
				MultiMatchQuery query = new MultiMatchQuery(key);
				query.addField(DossierTerm.DON_VI_GUI);
				subQuery.add(query, BooleanClauseOccur.SHOULD);
			}
			booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
		}
		if (Validator.isNotNull(donvinhan)) {
			if (donvinhan.contains(StringPool.COMMA)) {
				String[] keywordArr = donvinhan.split(StringPool.COMMA);
				BooleanQuery subQuery = new BooleanQueryImpl();
				for (String key : keywordArr) {
					MultiMatchQuery query = new MultiMatchQuery(key);
					query.addField(DossierTerm.DON_VI_NHAN);
					subQuery.add(query, BooleanClauseOccur.SHOULD);
				}
				booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
			} else {
				MultiMatchQuery query = new MultiMatchQuery(donvinhan);
				query.addField(DossierTerm.DON_VI_NHAN);
				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}


		}
		if (Validator.isNotNull(dossierCounterSearch)) {
			MultiMatchQuery query =
					new MultiMatchQuery(dossierCounterSearch);
			query.addField(DossierTerm.DOSSIER_COUNTER);
			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}
		if (Validator.isNotNull(matokhai)) {
			MultiMatchQuery query = new MultiMatchQuery(matokhai);
			query.addField(DossierTerm.MA_TO_KHAI);
			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		// System Id
		if (Validator.isNotNull(strSystemId)) {
			String[] systemIdArr = StringUtil.split(strSystemId);

			if (systemIdArr != null && systemIdArr.length > 0) {
				BooleanQuery subQuery = new BooleanQueryImpl();
				for (int i = 0; i < systemIdArr.length; i++) {
					MultiMatchQuery query = new MultiMatchQuery(systemIdArr[i]);
					query.addField(DossierTerm.SYSTEM_ID);
					subQuery.add(query, BooleanClauseOccur.SHOULD);
				}
				booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
			}
			else {
				MultiMatchQuery query = new MultiMatchQuery(strSystemId);
				query.addFields(DossierTerm.SYSTEM_ID);
				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
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
			}
			else {
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
					MultiMatchQuery query =
							new MultiMatchQuery(lstSubStatus[i]);
					query.addField(DossierTerm.DOSSIER_SUB_STATUS);
					subQuery.add(query, BooleanClauseOccur.SHOULD);
				}
				booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
			}
			else {
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

				MultiMatchQuery query2 =
						new MultiMatchQuery(ConstantsTerm.CANCELLED);

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

//		System.out.println("====online 3511==" + online);
//		_log.info("====online 3511==" + online);
//		_log.debug("====online 3511==" + online);
		if (Validator.isNotNull(online)) {
			MultiMatchQuery query = new MultiMatchQuery(String.valueOf(online));
			query.addField(DossierTerm.ONLINE);
			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(submitting) &&
				Boolean.parseBoolean(submitting)) {

			MultiMatchQuery query =
					new MultiMatchQuery(String.valueOf(submitting));

			query.addField(DossierTerm.SUBMITTING);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(agencys)) {
			String[] govsArr = StringUtil.split(agencys);
			BooleanQuery gobSubQuery = new BooleanQueryImpl();
			for (String gov : govsArr) {
				if (Validator.isNotNull(gov)) {

					MultiMatchQuery query =
							new MultiMatchQuery(gov);

					query.addFields(DossierTerm.GOV_AGENCY_CODE);
					gobSubQuery.add(query, BooleanClauseOccur.SHOULD);
				}
			}
			booleanQuery.add(gobSubQuery, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(service)) {
			MultiMatchQuery query = new MultiMatchQuery(service);

			query.addFields(ServiceInfoTerm.SERVICE_CODE_SEARCH);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (DossierTerm.STATISTIC.equals(top.toLowerCase())) {
			if (month > 0 && year > 0) {
				int minDayOfMonth = DossierMgtUtils.minDay(month, year);
				// _log.debug("minDayOfMonth: "+minDayOfMonth);
				if (minDayOfMonth > 0) {
					String strMonth;
					String strMonthEnd;
					String strMinDay;
					int monthEnd = month + 1;
					if (month < 10) {
						strMonth = "0" + month;
					}
					else {
						strMonth = String.valueOf(month);
					}
					if (monthEnd < 10) {
						strMonthEnd = "0" + monthEnd;
					}
					else {
						strMonthEnd = String.valueOf(monthEnd);
					}
					if (minDayOfMonth < 10) {
						strMinDay = "0" + minDayOfMonth;
					}
					else {
						strMinDay = String.valueOf(minDayOfMonth);
					}

					BooleanQuery subQueryOne = new BooleanQueryImpl();
					BooleanQuery subQueryTwo = new BooleanQueryImpl();
					BooleanQuery subQueryThree = new BooleanQueryImpl();

					String fromStatisDateFilter =
							year + strMonth + strMinDay + ConstantsTerm.HOUR_START;
					String toStatisDateFilter = year + strMonthEnd + strMinDay +
							ConstantsTerm.HOUR_START;

					// Check startDate <= receiveDate < endDate
					TermRangeQueryImpl termRangeQueryOne =
							new TermRangeQueryImpl(
									DossierTerm.RECEIVE_DATE, fromStatisDateFilter,
									toStatisDateFilter, true, false);

					subQueryOne.add(
							termRangeQueryOne, BooleanClauseOccur.SHOULD);
					/**
					 * Check receiveDate < startDate and (startDate <=
					 * releaseDate or releaseDate = null) - START
					 **/
					// Check receiveDate < startDate
					TermRangeQueryImpl termRangeQueryTwo =
							new TermRangeQueryImpl(
									DossierTerm.RECEIVE_DATE, null,
									fromStatisDateFilter, false, false);
					subQueryTwo.add(termRangeQueryTwo, BooleanClauseOccur.MUST);
					// Check startDate <= releaseDate
					TermRangeQueryImpl termRangeQueryThree =
							new TermRangeQueryImpl(
									DossierTerm.RELEASE_DATE_LUCENE,
									fromStatisDateFilter, null, true, true);
					subQueryThree.add(
							termRangeQueryThree, BooleanClauseOccur.SHOULD);
					// Check releaseDate = null
					MultiMatchQuery queryRelease =
							new MultiMatchQuery(String.valueOf(0));
					queryRelease.addField(DossierTerm.RELEASE_DATE_TIMESTAMP);
					subQueryThree.add(queryRelease, BooleanClauseOccur.SHOULD);
					//
					subQueryTwo.add(subQueryThree, BooleanClauseOccur.MUST);
					/**
					 * Check receiveDate < startDate and (startDate <=
					 * releaseDate or releaseDate = null) - END
					 **/
					subQueryOne.add(subQueryTwo, BooleanClauseOccur.SHOULD);
					//
					booleanQuery.add(subQueryOne, BooleanClauseOccur.MUST);
				}
			}
			else if (Validator.isNotNull(fromStatisticDate) &&
					Validator.isNotNull(toStatisticDate)) {

				BooleanQuery subQueryOne = new BooleanQueryImpl();
				BooleanQuery subQueryTwo = new BooleanQueryImpl();
				BooleanQuery subQueryThree = new BooleanQueryImpl();

				String fromStatisDateFilter =
						fromStatisticDate + ConstantsTerm.HOUR_START;
				String toStatisDateFilter =
						toStatisticDate + ConstantsTerm.HOUR_END;
				_log.debug("fromStatisDateFilter: " + fromStatisDateFilter);
				_log.debug("toStatisDateFilter: " + toStatisDateFilter);

				// Check startDate <= receiveDate < endDate
				TermRangeQueryImpl termRangeQueryOne = new TermRangeQueryImpl(
						DossierTerm.RECEIVE_DATE, fromStatisDateFilter,
						toStatisDateFilter, true, true);

				subQueryOne.add(termRangeQueryOne, BooleanClauseOccur.SHOULD);
				/**
				 * Check receiveDate < startDate and (startDate <= releaseDate
				 * or releaseDate = null) - START
				 **/
				// Check receiveDate < startDate
				TermRangeQueryImpl termRangeQueryTwo = new TermRangeQueryImpl(
						DossierTerm.RECEIVE_DATE, null, fromStatisDateFilter, false,
						false);
				subQueryTwo.add(termRangeQueryTwo, BooleanClauseOccur.MUST);
				// Check startDate <= releaseDate
				TermRangeQueryImpl termRangeQueryThree = new TermRangeQueryImpl(
						DossierTerm.RELEASE_DATE_LUCENE, fromStatisDateFilter, null,
						true, true);
				subQueryThree.add(
						termRangeQueryThree, BooleanClauseOccur.SHOULD);
				// Check startDate <= finishDate
				TermRangeQueryImpl termRangeQueryFinish =
						new TermRangeQueryImpl(
								DossierTerm.FINISH_DATE_LUCENE, fromStatisDateFilter,
								toStatisDateFilter, true, true);
				subQueryThree.add(
						termRangeQueryFinish, BooleanClauseOccur.SHOULD);
				// Check releaseDate = null
				MultiMatchQuery queryRelease =
						new MultiMatchQuery(String.valueOf(0));
				queryRelease.addField(DossierTerm.RELEASE_DATE_TIMESTAMP);
				subQueryThree.add(queryRelease, BooleanClauseOccur.SHOULD);
				//
				subQueryTwo.add(subQueryThree, BooleanClauseOccur.MUST);
				/**
				 * Check receiveDate < startDate and (startDate <= releaseDate
				 * or releaseDate = null) - END
				 **/
				subQueryOne.add(subQueryTwo, BooleanClauseOccur.SHOULD);
				//
				booleanQuery.add(subQueryOne, BooleanClauseOccur.MUST);
			}
		}
		else {
			if (year > 0 || month > 0) {
				if (year > 0) {
					// _log.debug("year: "+year);
					MultiMatchQuery query =
							new MultiMatchQuery(String.valueOf(year));
					// MultiMatchQuery queryYearTwo = new
					// MultiMatchQuery(String.valueOf(year));

					// if (Validator.isNotNull(top) &&
					// DossierTerm.STATISTIC.equals(top.toLowerCase())) {
					// MultiMatchQuery queryReceive = new
					// MultiMatchQuery(String.valueOf(0));
					// MultiMatchQuery queryRelease = new
					// MultiMatchQuery(String.valueOf(0));
					// BooleanQuery subQueryOne = new BooleanQueryImpl();
					// BooleanQuery subQueryTwo = new BooleanQueryImpl();
					// BooleanQuery subQueryThree = new BooleanQueryImpl();
					//
					// //Check receiveDate != null
					// queryReceive.addField(DossierTerm.YEAR_DOSSIER);
					// subQueryOne.add(queryReceive,
					// BooleanClauseOccur.MUST_NOT);
					// //Check receiveDate
					// queryYearTwo.addFields(DossierTerm.YEAR_DOSSIER);
					// subQueryOne.add(queryYearTwo, BooleanClauseOccur.SHOULD);
					// /**Check receiveDate < now && releaseDate = null or
					// releaseDate = now**/
					// TermRangeQueryImpl termRangeQuery = new
					// TermRangeQueryImpl(DossierTerm.YEAR_DOSSIER,
					// String.valueOf(0), String.valueOf(month), false, false);
					// subQueryTwo.add(termRangeQuery, BooleanClauseOccur.MUST);
					//
					// queryRelease.addField(DossierTerm.YEAR_RELEASE);
					// subQueryTwo.add(queryRelease, BooleanClauseOccur.SHOULD);
					//
					// subQueryThree.add(queryYearTwo,
					// BooleanClauseOccur.SHOULD);
					//
					// queryYearTwo.addFields(DossierTerm.YEAR_RELEASE);
					// subQueryTwo.add(subQueryThree, BooleanClauseOccur.MUST);
					// //
					// subQueryOne.add(subQueryTwo, BooleanClauseOccur.SHOULD);
					// //
					// booleanQuery.add(subQueryOne, BooleanClauseOccur.MUST);
					// } else {
					query.addFields(DossierTerm.YEAR_DOSSIER);
					booleanQuery.add(query, BooleanClauseOccur.MUST);
					// }
				}

				if (month > 0) {
					// _log.debug("month: "+month);
					MultiMatchQuery query =
							new MultiMatchQuery(String.valueOf(month));
					// MultiMatchQuery queryMonthTwo = new
					// MultiMatchQuery(String.valueOf(month));

					// if (Validator.isNotNull(top) &&
					// DossierTerm.STATISTIC.equals(top.toLowerCase())) {
					// MultiMatchQuery queryReceive = new
					// MultiMatchQuery(String.valueOf(0));
					// MultiMatchQuery queryRelease = new
					// MultiMatchQuery(String.valueOf(0));
					// BooleanQuery subQueryOne = new BooleanQueryImpl();
					// BooleanQuery subQueryTwo = new BooleanQueryImpl();
					// BooleanQuery subQueryThree = new BooleanQueryImpl();
					//
					// //Check receiveDate != null
					// queryReceive.addField(DossierTerm.MONTH_DOSSIER);
					// subQueryOne.add(queryReceive,
					// BooleanClauseOccur.MUST_NOT);
					// //Check receiveDate
					// queryMonthTwo.addFields(DossierTerm.MONTH_DOSSIER);
					// subQueryOne.add(queryMonthTwo,
					// BooleanClauseOccur.SHOULD);
					// /**Check receiveDate < now && releaseDate = null or
					// releaseDate = now**/
					// // Check receiveDate < now
					//// Calendar calDate = Calendar.getInstance();
					//// calDate.setTime(new Date());
					//// int monthCurrent = calDate.get(Calendar.MONTH) + 1;
					// TermRangeQueryImpl termRangeQuery = new
					// TermRangeQueryImpl(DossierTerm.MONTH_DOSSIER,
					// String.valueOf(0), String.valueOf(month), false, false);
					// subQueryTwo.add(termRangeQuery, BooleanClauseOccur.MUST);
					//
					// queryRelease.addField(DossierTerm.MONTH_RELEASE);
					// subQueryTwo.add(queryRelease, BooleanClauseOccur.SHOULD);
					//
					// subQueryThree.add(queryMonthTwo,
					// BooleanClauseOccur.SHOULD);
					//
					// queryMonthTwo.addFields(DossierTerm.MONTH_RELEASE);
					// subQueryTwo.add(subQueryThree, BooleanClauseOccur.MUST);
					//// //
					// subQueryOne.add(subQueryTwo, BooleanClauseOccur.SHOULD);
					// //
					// booleanQuery.add(subQueryOne, BooleanClauseOccur.MUST);
					// } else {
					query.addFields(DossierTerm.MONTH_DOSSIER);
					booleanQuery.add(query, BooleanClauseOccur.MUST);
					// }
				}
			}
			// Temporatory comment for dossier has not received
			// else {
			// MultiMatchQuery query = new MultiMatchQuery(String.valueOf(0));
			// query.addField(DossierTerm.RECEIVE_DATE_TIMESTAMP);
			// booleanQuery.add(query, BooleanClauseOccur.MUST_NOT);
			// }
		}

		if (Validator.isNotNull(top)) {
			if (DossierTerm.QUA_HAN.equals(top.toLowerCase())) {
				// _log.debug("top: "+top);
				MultiMatchQuery queryAction =
						new MultiMatchQuery(String.valueOf(userId));
				queryAction.addField(DossierTerm.USER_DOSSIER_ACTION_ID);
				booleanQuery.add(queryAction, BooleanClauseOccur.MUST);

			}
			else if(DossierTerm.DANG_XU_LY.equals(top.toLowerCase())){
				MultiMatchQuery queryAction =
						new MultiMatchQuery(DossierTerm.DANG_XU_LY  + StringPool.UNDERLINE
								+ String.valueOf(userId) + StringPool.UNDERLINE + 1);
				queryAction.addField(DossierTerm.DOING_ACTION_USER_ID);
				booleanQuery.add(queryAction, BooleanClauseOccur.MUST);
			}
			else if (!DossierTerm.STATISTIC.equals(top.toLowerCase())) {
				BooleanQuery subQuery = new BooleanQueryImpl();
				if (!top.toLowerCase().equals(DossierTerm.OVER_TIME)) {
					MultiMatchQuery queryRelease =
							new MultiMatchQuery(String.valueOf(0));
					queryRelease.addField(DossierTerm.RELEASE_DATE_TIMESTAMP);
					subQuery.add(queryRelease, BooleanClauseOccur.MUST);
				}
				// Dossier is delay
				if (top.toLowerCase().equals(DossierTerm.DELAY)) {
					/** Check condition dueDate != null **/
					MultiMatchQuery querydueDate =
							new MultiMatchQuery(String.valueOf(0));
					querydueDate.addField(DossierTerm.DUE_DATE_TIMESTAMP);
					subQuery.add(querydueDate, BooleanClauseOccur.MUST_NOT);
					/** Check condition status != waiting **/
					MultiMatchQuery queryWaiting =
							new MultiMatchQuery(DossierTerm.DOSSIER_STATUS_WAITING);
					queryWaiting.addField(DossierTerm.DOSSIER_STATUS);
					subQuery.add(queryWaiting, BooleanClauseOccur.MUST_NOT);

					MultiMatchQuery query =
							new MultiMatchQuery(String.valueOf(1));
					query.addFields(DossierTerm.COMPARE_DELAY_DATE);
					subQuery.add(query, BooleanClauseOccur.MUST);
					// Dossier is overDue
				}
				else if (top.toLowerCase().equals(DossierTerm.OVER_DUE)) {
					BooleanQuery subQueryOne = new BooleanQueryImpl();
					BooleanQuery subQueryTwo = new BooleanQueryImpl();
					BooleanQuery subQueryThree = new BooleanQueryImpl();

					/** Check condition dueDate != null **/
					MultiMatchQuery querydueDate =
							new MultiMatchQuery(String.valueOf(0));
					querydueDate.addField(DossierTerm.DUE_DATE_TIMESTAMP);
					subQuery.add(querydueDate, BooleanClauseOccur.MUST_NOT);

					/** Check condition status != waiting **/
					MultiMatchQuery queryWaiting =
							new MultiMatchQuery(DossierTerm.DOSSIER_STATUS_WAITING);
					queryWaiting.addField(DossierTerm.DOSSIER_STATUS);
					subQuery.add(queryWaiting, BooleanClauseOccur.MUST_NOT);

					/** Check condition status != receiving **/
					MultiMatchQuery queryReceiving = new MultiMatchQuery(
							DossierTerm.DOSSIER_STATUS_RECEIVING);
					queryReceiving.addField(DossierTerm.DOSSIER_STATUS);
					subQuery.add(queryReceiving, BooleanClauseOccur.MUST_NOT);

					/** Check condition releaseDate > dueDate **/
					MultiMatchQuery queryCompareRelease =
							new MultiMatchQuery(String.valueOf(1));
					queryCompareRelease.addField(
							DossierTerm.VALUE_COMPARE_RELEASE);
					subQueryOne.add(
							queryCompareRelease, BooleanClauseOccur.MUST);

					/** Check condition nowDate >= dueDate **/
					Date date = new Date();
					long nowTime = date.getTime();
					TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
							DossierTerm.DUE_DATE_TIMESTAMP, String.valueOf(0),
							String.valueOf(nowTime), false, false);
					subQueryTwo.add(termRangeQuery, BooleanClauseOccur.MUST);

					/**
					 * Check condition (releaseDate > dueDate || nowDate >=
					 * dueDate)
					 **/
					subQueryThree.add(subQueryTwo, BooleanClauseOccur.SHOULD);
					subQueryThree.add(subQueryOne, BooleanClauseOccur.SHOULD);

					/**
					 * Check condition dueDate!=null && (releaseDate>=dueDate ||
					 * now>=dueDate)
					 **/
					subQuery.add(subQueryThree, BooleanClauseOccur.MUST);

					// Check lock state
					BooleanQuery pauseQuery = new BooleanQueryImpl();

					MultiMatchQuery pauseLockState =
							new MultiMatchQuery(DossierTerm.PAUSE_LOCK_STATE);
					pauseLockState.addField(DossierTerm.LOCK_STATE);
					MultiMatchQuery pauseLockStateOverdue = new MultiMatchQuery(
							DossierTerm.PAUSE_OVERDUE_LOCK_STATE);
					pauseLockStateOverdue.addField(DossierTerm.LOCK_STATE);
					pauseQuery.add(pauseLockState, BooleanClauseOccur.SHOULD);
					pauseQuery.add(
							pauseLockStateOverdue, BooleanClauseOccur.SHOULD);

					subQuery.add(pauseQuery, BooleanClauseOccur.MUST_NOT);

					// Dossier is coming
				}
				else if (top.toLowerCase().equals(DossierTerm.COMING)) {
					/** Check condition dueDate != null **/
					MultiMatchQuery querydueDateNull =
							new MultiMatchQuery(String.valueOf(0));
					querydueDateNull.addField(DossierTerm.DUE_DATE_TIMESTAMP);
					subQuery.add(querydueDateNull, BooleanClauseOccur.MUST_NOT);

					// Check lock state
					// Check lock state
					BooleanQuery pauseQuery = new BooleanQueryImpl();

					MultiMatchQuery pauseLockState =
							new MultiMatchQuery(DossierTerm.PAUSE_LOCK_STATE);
					pauseLockState.addField(DossierTerm.LOCK_STATE);
					MultiMatchQuery pauseLockStateOverdue = new MultiMatchQuery(
							DossierTerm.PAUSE_OVERDUE_LOCK_STATE);
					pauseLockStateOverdue.addField(DossierTerm.LOCK_STATE);
					pauseQuery.add(pauseLockState, BooleanClauseOccur.SHOULD);
					pauseQuery.add(
							pauseLockStateOverdue, BooleanClauseOccur.SHOULD);

					subQuery.add(pauseQuery, BooleanClauseOccur.MUST_NOT);

					// Check dossier is not dueDate
					MultiMatchQuery query =
							new MultiMatchQuery(String.valueOf(0));
					query.addFields(DossierTerm.DUE_DATE_COMING);
					subQuery.add(query, BooleanClauseOccur.MUST_NOT);
					// Check dossier has dueDate
					Date date = new Date();
					long nowTime = date.getTime();
					TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
							DossierTerm.DUE_DATE_COMING, String.valueOf(0),
							String.valueOf(nowTime), false, true);
					subQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
					// Check nowDate < dueDate
					TermRangeQueryImpl termRangeQueryNow =
							new TermRangeQueryImpl(
									DossierTerm.DUE_DATE_TIMESTAMP,
									String.valueOf(nowTime), null, true, true);
					subQuery.add(termRangeQueryNow, BooleanClauseOccur.MUST);
				}
				else if (top.toLowerCase().equals(DossierTerm.OVER_TIME)) {
					/** Check condition dueDate != null **/
					MultiMatchQuery querydueDate =
							new MultiMatchQuery(String.valueOf(0));
					querydueDate.addField(DossierTerm.DUE_DATE_TIMESTAMP);
					subQuery.add(querydueDate, BooleanClauseOccur.MUST_NOT);
					/** Check condition status != waiting **/
					MultiMatchQuery queryReleaseDate =
							new MultiMatchQuery(String.valueOf(0));
					queryReleaseDate.addField(DossierTerm.RELEASE_DATE_TIMESTAMP);
					subQuery.add(queryReleaseDate, BooleanClauseOccur.MUST_NOT);

					MultiMatchQuery query =
							new MultiMatchQuery(String.valueOf(1));
					query.addFields(DossierTerm.VALUE_COMPARE_RELEASE);
					subQuery.add(query, BooleanClauseOccur.MUST);
				}
				//
				booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
			}
		}

		if (Validator.isNotNull(dossierNo)) {
			BooleanQuery queryBool = new BooleanQueryImpl();
			String[] subQuerieArr = new String[]{DossierTerm.DOSSIER_COUNTER_SEARCH, DossierTerm.DOSSIER_NO_SEARCH,
					DossierTerm.POSTAL_CODE_RECEIVED, DossierTerm.POSTAL_CODE_SEND};

			String[] keyDossier = dossierNo.split(StringPool.SPACE);
			for (String fieldSearch : subQuerieArr) {
				BooleanQuery query = new BooleanQueryImpl();
				for (String key : keyDossier) {
					WildcardQuery wildQuery = new WildcardQueryImpl(fieldSearch, key.toLowerCase() + StringPool.STAR);
					query.add(wildQuery, BooleanClauseOccur.MUST);
				}
				queryBool.add(query, BooleanClauseOccur.SHOULD);
			}
			booleanQuery.add(queryBool, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(certificateNo)) {
			MultiMatchQuery query = new MultiMatchQuery(certificateNo);
			query.addFields(DossierTerm.DOSSIER_ID_CTN);
			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(strDossierActionId)) {
			String[] sliptDossierActionId =
					StringUtil.split(strDossierActionId);
			if (sliptDossierActionId != null &&
					sliptDossierActionId.length > 0) {
				BooleanQuery subQuery = new BooleanQueryImpl();
				for (String dossierActionId : sliptDossierActionId) {
					if (Validator.isNotNull(dossierActionId)) {

						MultiMatchQuery query =
								new MultiMatchQuery(dossierActionId);

						query.addFields(DossierTerm.DOSSIER_ACTION_ID);
						subQuery.add(query, BooleanClauseOccur.SHOULD);
					}
				}
				booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
			}
			else {
				MultiMatchQuery query = new MultiMatchQuery(strDossierActionId);

				query.addFields(DossierTerm.DOSSIER_ACTION_ID);

				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
		}

		String fromReceiveDateFilter =
				fromReceiveDate + ConstantsTerm.HOUR_START;
		String toReceiveDateFilter = toReceiveDate + ConstantsTerm.HOUR_END;
		BooleanQuery receiveDateQuery = new BooleanQueryImpl();

		if (Validator.isNotNull(fromReceiveDate)) {
			if (Validator.isNotNull(toReceiveDate)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
						DossierTerm.RECEIVE_DATE, fromReceiveDateFilter,
						toReceiveDateFilter, true, true);

				receiveDateQuery.add(termRangeQuery, BooleanClauseOccur.SHOULD);
			}
			else {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
						DossierTerm.RECEIVE_DATE, fromReceiveDateFilter,
						toReceiveDateFilter, true, false);

				receiveDateQuery.add(termRangeQuery, BooleanClauseOccur.SHOULD);
			}
		}
		else {
			if (Validator.isNotNull(toReceiveDate)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
						DossierTerm.RECEIVE_DATE, fromReceiveDateFilter,
						toReceiveDateFilter, false, true);

				receiveDateQuery.add(termRangeQuery, BooleanClauseOccur.SHOULD);
			}
		}
		if (Validator.isNotNull(backlog) && backlog > 0) {
			Date bd = APIDateTimeUtils.convertStringToDate(
					fromReceiveDateFilter, APIDateTimeUtils._LUCENE_PATTERN);
			String backLogD = Validator.isNotNull(bd)
					? APIDateTimeUtils.convertDateToString(
					bd, APIDateTimeUtils._NORMAL_DATE)
					: backlogDate;
			BooleanQuery backlogDateQuery = buildBacklogQuery(backLogD);
			receiveDateQuery.add(backlogDateQuery, BooleanClauseOccur.SHOULD);
			_log.debug("backlog: " + receiveDateQuery);
		}
		booleanQuery.add(receiveDateQuery, BooleanClauseOccur.MUST);

		if (Validator.isNotNull(certNo)) {
			MultiMatchQuery query = new MultiMatchQuery(String.valueOf(certNo));
			query.addField(DossierTerm.CERT_NO_SEARCH);
			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		String fromCertDateFilter = fromCertDate + ConstantsTerm.HOUR_START;
		String toCertDateFilter = toCertDate + ConstantsTerm.HOUR_END;

		if (Validator.isNotNull(fromCertDate)) {
			if (Validator.isNotNull(toCertDate)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
						DossierTerm.CERT_DATE, fromCertDateFilter, toCertDateFilter,
						true, true);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			}
			else {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
						DossierTerm.CERT_DATE, fromCertDateFilter, toCertDateFilter,
						true, false);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			}
		}
		else {
			if (Validator.isNotNull(toCertDate)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
						DossierTerm.CERT_DATE, fromCertDateFilter, toCertDateFilter,
						false, true);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			}
		}

		String fromSubmitDateFilter = fromSubmitDate + ConstantsTerm.HOUR_START;
		String toSubmitDateFilter = toSubmitDate + ConstantsTerm.HOUR_END;

		if (Validator.isNotNull(fromSubmitDate)) {
			if (Validator.isNotNull(toSubmitDate)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
						DossierTerm.SUBMIT_DATE, fromSubmitDateFilter,
						toSubmitDateFilter, true, true);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			}
			else {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
						DossierTerm.SUBMIT_DATE, fromSubmitDateFilter,
						toSubmitDateFilter, true, false);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			}
		}
		else {
			if (Validator.isNotNull(toSubmitDate)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
						DossierTerm.RECEIVE_DATE, fromSubmitDateFilter,
						toSubmitDateFilter, false, true);

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
			MultiMatchQuery query =
					new MultiMatchQuery(String.valueOf(statusReg));
			query.addField(DossierTerm.STATUS_REG);
			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(notStatusReg)) {
			MultiMatchQuery query =
					new MultiMatchQuery(String.valueOf(notStatusReg));
			query.addField(DossierTerm.STATUS_REG);
			booleanQuery.add(query, BooleanClauseOccur.MUST_NOT);
		}

		// LamTV: Process originality and assigned
		if (Validator.isNotNull(assigned)) {
			MultiMatchQuery query =
					new MultiMatchQuery(String.valueOf(assigned));
			query.addField(DossierTerm.ASSIGNED);
			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		// LamTV: Process originality and assigned
		if (Validator.isNotNull(assignedUserId)) {
			String[] assignedArr = StringUtil.split(assignedUserId);

			if (assignedArr != null && assignedArr.length > 0) {
				BooleanQuery subQuery = new BooleanQueryImpl();
				for (int i = 0; i < assignedArr.length; i++) {
					MultiMatchQuery query = new MultiMatchQuery(assignedArr[i]);
					query.addField(DossierTerm.ASSIGNED_USER_ID);
					subQuery.add(query, BooleanClauseOccur.SHOULD);
				}
				booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
			}
			else {
				MultiMatchQuery query = new MultiMatchQuery(assignedUserId);
				query.addFields(DossierTerm.ASSIGNED_USER_ID);
				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
		}

		// loc theo can bo da xu ly ho so
		if (Validator.isNotNull(assignedUserIdSearch)) {
			String[] assignedArr = StringUtil.split(assignedUserIdSearch);
			BooleanQuery query = new BooleanQueryImpl();
			for (String key : assignedArr) {
				WildcardQuery wildQuery = new WildcardQueryImpl(
						DossierTerm.ASSIGNED_USER_ID,
						key.toLowerCase() + StringPool.STAR);
				query.add(wildQuery, BooleanClauseOccur.MUST);
			}
			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		// _log.debug("originality: "+originality);
		if (Validator.isNotNull(originality)) {
			if (originality.contains(StringPool.COMMA)) {
				String[] originalArr = StringUtil.split(originality);

				if (originalArr != null && originalArr.length > 0) {
					BooleanQuery subQuery = new BooleanQueryImpl();
					for (int i = 0; i < originalArr.length; i++) {
						int orginalInt = GetterUtil.getInteger(originalArr[i]);
						if (orginalInt >= 0) {
							MultiMatchQuery query =
									new MultiMatchQuery(originalArr[i]);
							query.addField(DossierTerm.ORIGINALLITY);
							subQuery.add(query, BooleanClauseOccur.SHOULD);
						}
						else {
							String originalSearch = String.valueOf(
									DossierTerm.CONSTANT_INDEX_ORIGINALITY +
											orginalInt);
							MultiMatchQuery query =
									new MultiMatchQuery(originalSearch);
							query.addField(DossierTerm.ORIGINALLITY);
							subQuery.add(query, BooleanClauseOccur.SHOULD);
						}

					}
					booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
				}
			}
			else {
				Integer originalityInt = GetterUtil.getInteger(originality);
				if (originalityInt == -1) {
					TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
							DossierTerm.ORIGINALLITY,
							String.valueOf(DossierTerm.ORIGINALITY_MOTCUA),
							String.valueOf(DossierTerm.CONSTANT_INDEX_ORIGINALITY),
							false, true);

					booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
				}
				else if (originalityInt >= 0) {
					// _log.debug("originalityxxxx: "+originality);
					MultiMatchQuery query = new MultiMatchQuery(originality);
					query.addFields(DossierTerm.ORIGINALLITY);
					booleanQuery.add(query, BooleanClauseOccur.MUST);
				}
			}
		}
		else {
			// _log.debug("START originality: "+originality);
			TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
					DossierTerm.ORIGINALLITY,
					String.valueOf(DossierTerm.ORIGINALITY_PUBLISH),
					String.valueOf(DossierTerm.ORIGINALITY_HSLT), true, true);
			booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
		}

		// Check original by action
		if (Validator.isNotNull(originDossierId) && originDossierId > 0) {
			MultiMatchQuery queryOrigin =
					new MultiMatchQuery(String.valueOf(originDossierId));
			queryOrigin.addField(DossierTerm.ORIGIN_DOSSIER_ID);
			booleanQuery.add(queryOrigin, BooleanClauseOccur.MUST);
		}
		else {
			if (Validator.isNotNull(originality)) {
				Integer originalityInt = GetterUtil.getInteger(originality);
				if (Validator.isNotNull(follow) && Boolean.valueOf(follow) &&
						originalityInt == DossierTerm.ORIGINALITY_PUBLISH) {

				}
				else if (originalityInt != 9) {
					MultiMatchQuery queryDossierAction =
							new MultiMatchQuery(String.valueOf(0));
					queryDossierAction.addField(DossierTerm.DOSSIER_ACTION_ID);
					booleanQuery.add(
							queryDossierAction, BooleanClauseOccur.MUST_NOT);
					//
					// MultiMatchQuery queryOrigin = new
					// MultiMatchQuery(String.valueOf(0));
					// queryOrigin.addField(DossierTerm.ORIGIN_DOSSIER_ID);
					// booleanQuery.add(queryOrigin, BooleanClauseOccur.MUST);
				}

			}
			else {
				MultiMatchQuery queryDossierAction =
						new MultiMatchQuery(String.valueOf(0));
				queryDossierAction.addField(DossierTerm.DOSSIER_ACTION_ID);
				booleanQuery.add(
						queryDossierAction, BooleanClauseOccur.MUST_NOT);
				//
				MultiMatchQuery queryOrigin =
						new MultiMatchQuery(String.valueOf(0));
				queryOrigin.addField(DossierTerm.ORIGIN_DOSSIER_ID);
				booleanQuery.add(queryOrigin, BooleanClauseOccur.MUST);
			}
		}

		// LamTV_Test
		// if (Validator.isNotNull(statusStep)) {
		// String[] statusStepArr = StringUtil.split(statusStep);
		//
		// if (statusStepArr != null && statusStepArr.length > 0) {
		// BooleanQuery subQuery = new BooleanQueryImpl();
		// for (int i = 0; i < statusStepArr.length; i++) {
		// MultiMatchQuery query = new MultiMatchQuery(statusStepArr[i]);
		// query.addField(DossierTerm.DOSSIER_STATUS);
		// subQuery.add(query, BooleanClauseOccur.SHOULD);
		// }
		// booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
		// } else {
		// MultiMatchQuery query = new MultiMatchQuery(statusStep);
		// query.addFields(DossierTerm.DOSSIER_STATUS);
		// booleanQuery.add(query, BooleanClauseOccur.MUST);
		// }
		// }
		// Set<String> addedSubStatuses = new HashSet<>();
		// if (Validator.isNotNull(subStatusStep)) {
		// String[] subStatusStepArr = StringUtil.split(subStatusStep);
		// if (subStatusStepArr != null && subStatusStepArr.length > 0) {
		// BooleanQuery subQuery = new BooleanQueryImpl();
		// for (int i = 0; i < subStatusStepArr.length; i++) {
		// String subStatusStepDetail = subStatusStepArr[i];
		// if (!"empty".equals(subStatusStepDetail) &&
		// !addedSubStatuses.contains(subStatusStepDetail)) {
		// MultiMatchQuery query = new MultiMatchQuery(subStatusStepArr[i]);
		// query.addField(DossierTerm.DOSSIER_SUB_STATUS);
		// subQuery.add(query, BooleanClauseOccur.SHOULD);
		// addedSubStatuses.add(subStatusStepArr[i]);
		//
		// }
		// }
		// booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
		// } else {
		// if (!"empty".equals(subStatusStep)) {
		// MultiMatchQuery query = new MultiMatchQuery(subStatusStep);
		// query.addFields(DossierTerm.DOSSIER_SUB_STATUS);
		// booleanQuery.add(query, BooleanClauseOccur.MUST);
		// }
		// }
		// }

		if (Validator.isNotNull(statusStep) &&
				Validator.isNotNull(subStatusStep)) {
			String[] statusStepArr = StringUtil.split(statusStep);
			String[] subStatusStepArr = StringUtil.split(subStatusStep);

			if (statusStepArr != null && statusStepArr.length > 0) {
				BooleanQuery subQuery = new BooleanQueryImpl();
				for (int i = 0; i < statusStepArr.length; i++) {
					BooleanQuery matchedQuery = new BooleanQueryImpl();
					MultiMatchQuery query =
							new MultiMatchQuery(statusStepArr[i]);
					query.addField(DossierTerm.DOSSIER_STATUS);

					matchedQuery.add(query, BooleanClauseOccur.MUST);
					if (!"empty".equals(subStatusStepArr[i])) {
						MultiMatchQuery querySub =
								new MultiMatchQuery(subStatusStepArr[i]);
						querySub.addField(DossierTerm.DOSSIER_SUB_STATUS);
						matchedQuery.add(querySub, BooleanClauseOccur.MUST);
					}
					subQuery.add(matchedQuery, BooleanClauseOccur.SHOULD);
				}
				booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
			}
			else {
				MultiMatchQuery query = new MultiMatchQuery(statusStep);
				query.addFields(DossierTerm.DOSSIER_STATUS);
				booleanQuery.add(query, BooleanClauseOccur.MUST);

				MultiMatchQuery querySub = new MultiMatchQuery(subStatusStep);
				query.addFields(DossierTerm.DOSSIER_SUB_STATUS);
				booleanQuery.add(querySub, BooleanClauseOccur.MUST);
			}
		}

		// _log.debug("Permission: " + permission);
		if (Validator.isNotNull(permission)) {
			String[] permissionArr = StringUtil.split(permission);

			if (permissionArr != null && permissionArr.length > 0) {
				BooleanQuery subQuery = new BooleanQueryImpl();
				for (int i = 0; i < permissionArr.length; i++) {
					MultiMatchQuery query =
							new MultiMatchQuery(permissionArr[i]);
					query.addField(DossierTerm.MAPPING_PERMISSION);
					subQuery.add(query, BooleanClauseOccur.SHOULD);
				}
				booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
			}
			else {
				MultiMatchQuery query = new MultiMatchQuery(permission);
				query.addFields(DossierTerm.MAPPING_PERMISSION);
				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
		}

		if (Validator.isNotNull(domain)) {
			String[] lstDomain = StringUtil.split(domain);
			if (lstDomain != null && lstDomain.length > 0) {
				BooleanQuery subQuery = new BooleanQueryImpl();
				for (int i = 0; i < lstDomain.length; i++) {
					MultiMatchQuery query = new MultiMatchQuery(lstDomain[i]);
					query.addField(DossierTerm.DOMAIN_CODE);
					subQuery.add(query, BooleanClauseOccur.SHOULD);
				}
				booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
			}
			else {
				MultiMatchQuery query = new MultiMatchQuery(domain);
				query.addFields(DossierTerm.DOMAIN_CODE);
				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
		}   		

		// LamTV: Process search LIKE
		if (Validator.isNotNull(domainName)) {
			String[] domainArr = domainName.split(StringPool.SPACE);
			BooleanQuery query = new BooleanQueryImpl();
			for (String key : domainArr) {
				WildcardQuery wildQuery = new WildcardQueryImpl(
						DossierTerm.DOMAIN_NAME,
						key.toLowerCase() + StringPool.STAR);
				query.add(wildQuery, BooleanClauseOccur.MUST);
			}
			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(applicantName)) {
			String[] applicantArr = applicantName.split(StringPool.SPACE);
			BooleanQuery query = new BooleanQueryImpl();
			for (String key : applicantArr) {
				WildcardQuery wildQuery = new WildcardQueryImpl(
						DossierTerm.APPLICANT_NAME,
						StringPool.STAR + key.toLowerCase() + StringPool.STAR);
				query.add(wildQuery, BooleanClauseOccur.MUST);
			}
			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		// LamTV: Process search LIKE
		if (Validator.isNotNull(applicantIdNo)) {
			String[] keywordArr = applicantIdNo.split(StringPool.SPACE);
			BooleanQuery query = new BooleanQueryImpl();
			for (String key : keywordArr) {
				WildcardQuery wildQuery = new WildcardQueryImpl(
						DossierTerm.APPLICANT_ID_NO,
						key.toLowerCase() + StringPool.STAR);
				query.add(wildQuery, BooleanClauseOccur.MUST);
			}
			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		// LamTV: Process search LIKE
		if (Validator.isNotNull(serviceName)) {
			String[] serviceArr = serviceName.split(StringPool.SPACE);
			BooleanQuery query = new BooleanQueryImpl();
			for (String key : serviceArr) {
				WildcardQuery wildQuery = new WildcardQueryImpl(
						DossierTerm.SERVICE_NAME,
						key.toLowerCase() + StringPool.STAR);
				query.add(wildQuery, BooleanClauseOccur.MUST);
			}
			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		String fromReleaseDateFilter =
				fromReleaseDate + ConstantsTerm.HOUR_START;
		String toReleaseDateFilter = toReleaseDate + ConstantsTerm.HOUR_END;

		if (Validator.isNotNull(fromReleaseDate)) {
			if (Validator.isNotNull(toReleaseDate)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
						DossierTerm.RELEASE_DATE_LUCENE, fromReleaseDateFilter,
						toReleaseDateFilter, true, true);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			}
			else {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
						DossierTerm.RELEASE_DATE_LUCENE, fromReleaseDateFilter,
						null, true, false);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			}
		}
		else {
			if (Validator.isNotNull(toReleaseDate)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
						DossierTerm.RELEASE_DATE_LUCENE, null, toReleaseDateFilter,
						false, true);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			}
		}

		// Process Statistic
		// TODO
		if (Validator.isNotNull(fromFinishDate) ||
				Validator.isNotNull(toFinishDate)) {
			String fromFinishDateFilter =
					fromFinishDate + ConstantsTerm.HOUR_START;
			String toFinishDateFilter = toFinishDate + ConstantsTerm.HOUR_END;

			if (Validator.isNotNull(fromFinishDate)) {
				if (Validator.isNotNull(toFinishDate)) {
					TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
							DossierTerm.FINISH_DATE_LUCENE, fromFinishDateFilter,
							toFinishDateFilter, true, true);

					booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
				}
				else {
					TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
							DossierTerm.FINISH_DATE_LUCENE, fromFinishDateFilter,
							toFinishDateFilter, true, false);

					booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
				}
			}
			else {
				if (Validator.isNotNull(toFinishDate)) {
					TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
							DossierTerm.FINISH_DATE_LUCENE, fromFinishDateFilter,
							toFinishDateFilter, false, true);

					booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
				}
			}
			//
			MultiMatchQuery query =
					new MultiMatchQuery(DossierTerm.DOSSIER_STATUS_DONE);
			query.addField(DossierTerm.DOSSIER_STATUS);
			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}


		if (Validator.isNotNull(fromDueDate) ||
				Validator.isNotNull(toDueDate)) {
			String fromDueDateFilter =
					fromDueDate + ConstantsTerm.HOUR_START;
			String toDueDateFilter = toDueDate + ConstantsTerm.HOUR_END;

			if (Validator.isNotNull(fromDueDate)) {
				if (Validator.isNotNull(toDueDate)) {
					TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
							DossierTerm.DUE_DATE_LUCENE, fromDueDateFilter,
							toDueDateFilter, true, true);

					booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
				}
				else {
					TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
							DossierTerm.DUE_DATE_LUCENE, fromDueDateFilter,
							toDueDateFilter, true, false);

					booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
				}
			}
			else {
				if (Validator.isNotNull(toDueDate)) {
					TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
							DossierTerm.DUE_DATE_LUCENE, fromDueDateFilter,
							toDueDateFilter, false, true);

					booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
				}
			}
		}

		if (Validator.isNotNull(fromReceiveNotDoneDate) ||
				Validator.isNotNull(toReceiveNotDoneDate)) {
			// Check Release is null
			MultiMatchQuery queryRelease =
					new MultiMatchQuery(String.valueOf(0));
			queryRelease.addField(DossierTerm.RELEASE_DATE_TIMESTAMP);
			booleanQuery.add(queryRelease, BooleanClauseOccur.MUST);
			//
			String fromReceiveNotDoneDateFilter =
					fromReceiveNotDoneDate + ConstantsTerm.HOUR_START;
			String toReceiveNotDoneDateFilter =
					toReceiveNotDoneDate + ConstantsTerm.HOUR_END;

			if (Validator.isNotNull(fromReceiveNotDoneDate)) {
				if (Validator.isNotNull(toReceiveNotDoneDate)) {
					TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
							DossierTerm.RECEIVE_DATE, fromReceiveNotDoneDateFilter,
							toReceiveNotDoneDateFilter, true, true);

					booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
				}
				else {
					TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
							DossierTerm.RECEIVE_DATE, fromReceiveNotDoneDateFilter,
							toReceiveNotDoneDateFilter, true, false);

					booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
				}
			}
			else {
				if (Validator.isNotNull(toReceiveNotDoneDate)) {
					TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
							DossierTerm.RECEIVE_DATE, fromReceiveNotDoneDateFilter,
							toReceiveNotDoneDateFilter, false, true);

					booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
				}
			}
		}

		if (Validator.isNotNull(paymentStatus)) {
			MultiMatchQuery query = new MultiMatchQuery(paymentStatus);

			query.addFields(PaymentFileTerm.PAYMENT_STATUS);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(origin)) {
			MultiMatchQuery query = new MultiMatchQuery(origin);

			query.addFields(DossierTerm.ORIGIN);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		// Check statistic with key "time"
		if (Validator.isNotNull(time)) {
			String[] lstTimes = StringUtil.split(time);
			if (lstTimes != null && lstTimes.length > 1) {
				BooleanQuery subQuery = new BooleanQueryImpl();
				for (int i = 0; i < lstTimes.length; i++) {
					BooleanQuery query = processStatisticDossier(lstTimes[i]);
					subQuery.add(query, BooleanClauseOccur.SHOULD);
				}
				booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
			}
			else {
				booleanQuery.add(
						processStatisticDossier(time), BooleanClauseOccur.MUST);
			}
		}

		if (Validator.isNotNull(register)) {
			MultiMatchQuery query = new MultiMatchQuery(register);

			query.addFields(DossierTerm.REGISTER);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}
		if (day > 0) {
			MultiMatchQuery query = new MultiMatchQuery(String.valueOf(day));
			query.addFields(DossierTerm.DAY_DOSSIER);
			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

//		if (Validator.isNotNull(groupDossierId)) {
//			MultiMatchQuery query =
//				new MultiMatchQuery(String.valueOf(groupDossierId));
//			query.addField(DossierTerm.GROUP_DOSSIER_ID);
//			booleanQuery.add(query, BooleanClauseOccur.MUST);
//		}
		//Search group dossier
		if (Validator.isNotNull(groupDossierId)) {
			if (groupDossierId.contains(StringPool.COMMA)) {
				String[] groupDossierArr = StringUtil.split(groupDossierId);

				if (groupDossierArr != null && groupDossierArr.length > 0) {
					BooleanQuery subQuery = new BooleanQueryImpl();
					for (int i = 0; i < groupDossierArr.length; i++) {
						MultiMatchQuery query =
								new MultiMatchQuery(groupDossierArr[i]);
						query.addField(DossierTerm.GROUP_DOSSIER_ID);
						subQuery.add(query, BooleanClauseOccur.SHOULD);
					}
					booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
				}
				else {
					MultiMatchQuery query = new MultiMatchQuery(groupDossierId);
					query.addFields(DossierTerm.GROUP_DOSSIER_ID);
					booleanQuery.add(query, BooleanClauseOccur.MUST);
				}
			} else {
				MultiMatchQuery query = new MultiMatchQuery(groupDossierId);
				query.addFields(DossierTerm.GROUP_DOSSIER_ID);
				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
		}
		if (Validator.isNotNull(groupDossierIdHs)) {
			if (groupDossierIdHs.contains(StringPool.COMMA)) {
				String[] groupDossierArr = StringUtil.split(groupDossierIdHs);

				if (groupDossierArr != null && groupDossierArr.length > 0) {
					BooleanQuery subQuery = new BooleanQueryImpl();
					for (int i = 0; i < groupDossierArr.length; i++) {
						MultiMatchQuery query =
								new MultiMatchQuery(groupDossierArr[i]);
						query.addField(DossierTerm.GROUP_DOSSIER_ID);
						subQuery.add(query, BooleanClauseOccur.SHOULD);
					}
					booleanQuery.add(subQuery, BooleanClauseOccur.MUST_NOT);
				}
				else {
					MultiMatchQuery query = new MultiMatchQuery(groupDossierIdHs);
					query.addFields(DossierTerm.GROUP_DOSSIER_ID);
					booleanQuery.add(query, BooleanClauseOccur.MUST_NOT);
				}
			} else {
				MultiMatchQuery query = new MultiMatchQuery(groupDossierIdHs);
				query.addFields(DossierTerm.GROUP_DOSSIER_ID);
				booleanQuery.add(query, BooleanClauseOccur.MUST_NOT);
			}
		}

		// Delegate
		if (delegateType != null && Validator.isNotNull(delegateType)) {
			MultiMatchQuery query =
					new MultiMatchQuery(String.valueOf(delegateType));
			query.addField(DossierTerm.DELEGATE_TYPE);
			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}
		if (Validator.isNotNull(documentNo)) {
			MultiMatchQuery query =
					new MultiMatchQuery(String.valueOf(documentNo));
			query.addField(DossierTerm.DOCUMENT_NO);
			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}
		if (Validator.isNotNull(documentDate)) {
			Date filterDocumentDate = APIDateTimeUtils.convertStringToDate(
					documentDate, APIDateTimeUtils._NORMAL_DATE);
			if (filterDocumentDate != null) {
				Calendar c = Calendar.getInstance();
				c.setTime(filterDocumentDate);
				int yearDocument = c.get(Calendar.YEAR);
				int monthDocument = c.get(Calendar.MONTH) + 1;
				int dayDocument = c.get(Calendar.DAY_OF_MONTH);
				String yearDocumentStr = String.valueOf(yearDocument);
				String monthDocumentStr = (monthDocument < 10)
						? "0" + monthDocument : monthDocument + "";
				String dayDocumentStr =
						(dayDocument < 10) ? "0" + dayDocument : dayDocument + "";
				String fromDocumentDate = yearDocumentStr + monthDocumentStr +
						dayDocumentStr + ConstantsTerm.HOUR_START;
				String toDocumentDate = yearDocumentStr + monthDocumentStr +
						dayDocumentStr + ConstantsTerm.HOUR_END;
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
						DossierTerm.DOCUMENT_DATE, fromDocumentDate, toDocumentDate,
						true, true);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			}
		}

		if (Validator.isNotNull(delegate)) {
			String[] lstDelegateIdNo = StringUtil.split(delegate);

			if (lstDelegateIdNo != null && lstDelegateIdNo.length > 0) {
				BooleanQuery subQuery = new BooleanQueryImpl();
				for (int i = 0; i < lstDelegateIdNo.length; i++) {
					MultiMatchQuery query = new MultiMatchQuery(lstDelegateIdNo[i]);
					query.addField(DossierTerm.DELEGATE_ID_NO);
					subQuery.add(query, BooleanClauseOccur.SHOULD);
				}
				booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
			}
			else {
				MultiMatchQuery query = new MultiMatchQuery(delegate);
				query.addFields(DossierTerm.DELEGATE_ID_NO);
				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}
		}
		
		if (Validator.isNotNull(serviceLevel)) {
			String[] lstServiceLevel = StringUtil.split(serviceLevel);
			if (lstServiceLevel != null && lstServiceLevel.length > 0) {
				BooleanQuery subQuery = new BooleanQueryImpl();
				for (int i = 0; i < lstServiceLevel.length; i++) {
					MultiMatchQuery query = new MultiMatchQuery(lstServiceLevel[i]);
					query.addField(DossierTerm.SERVICE_LEVEL);
					subQuery.add(query, BooleanClauseOccur.SHOULD);
				}
				booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
			}
			else {
				MultiMatchQuery query = new MultiMatchQuery(serviceLevel);
				query.addFields(DossierTerm.SERVICE_LEVEL);
				booleanQuery.add(query, BooleanClauseOccur.MUST);
			}			
		}

		return booleanQuery;
	}

	private BooleanQuery buildBacklogQuery(String backlogDate) {

		BooleanQuery backlogDateQuery = new BooleanQueryImpl();

		if (Validator.isNotNull(backlogDate)) {
			Date filterBacklogDate = APIDateTimeUtils.convertStringToDate(
					backlogDate, APIDateTimeUtils._NORMAL_DATE);

			if (filterBacklogDate != null) {
				String fromBacklogDateDate =
						APIDateTimeUtils.convertDateToString(filterBacklogDate, APIDateTimeUtils._LUCENE_PATTERN);
				TermRangeQueryImpl receiveRange = new TermRangeQueryImpl(
						DossierTerm.RECEIVE_DATE, ConstantsTerm.HOUR_START,
						fromBacklogDateDate, false, true);

				TermRangeQueryImpl releaseRange = new TermRangeQueryImpl(
						DossierTerm.RELEASE_DATE_LUCENE, fromBacklogDateDate, null,
						true, false);
				MultiMatchQuery release0 =
						new MultiMatchQuery(String.valueOf(0));
				release0.addField(DossierTerm.RELEASE_DATE_TIMESTAMP);

				try {
					BooleanQuery releaseDateQuery = new BooleanQueryImpl();
					releaseDateQuery.add(
							releaseRange, BooleanClauseOccur.SHOULD);
					releaseDateQuery.add(release0, BooleanClauseOccur.SHOULD);

					backlogDateQuery.add(
							releaseDateQuery, BooleanClauseOccur.MUST);
					backlogDateQuery.add(receiveRange, BooleanClauseOccur.MUST);
				}
				catch (Exception e) {
					_log.error(e);
				}
			}
		}
		return backlogDateQuery;
	}

	private BooleanQuery processStatisticDossier(String subTime)
			throws ParseException {

		BooleanQuery booleanQuery = new BooleanQueryImpl();
		// Check list dossier is betimes
		if (subTime.equals(DossierTerm.BE_TIME)) {
			BooleanQuery subQueryOne = new BooleanQueryImpl();
			BooleanQuery subQueryTwo = new BooleanQueryImpl();
			BooleanQuery subQueryThree = new BooleanQueryImpl();
			BooleanQuery subQueryFour = new BooleanQueryImpl();

			/** Check condition dueDate != null **/
			MultiMatchQuery queryDueDate =
					new MultiMatchQuery(String.valueOf(0));
			queryDueDate.addField(DossierTerm.DUE_DATE_TIMESTAMP);
			subQueryOne.add(queryDueDate, BooleanClauseOccur.MUST_NOT);
			/** Check condition extendDate != null and releaseDate < dueDate **/
			// Check extendDate != null
			MultiMatchQuery queryExtend =
					new MultiMatchQuery(String.valueOf(0));
			queryExtend.addField(DossierTerm.EXTEND_DATE_TIMESTAMP);
			subQueryTwo.add(queryExtend, BooleanClauseOccur.MUST_NOT);
			// Check releaseDate < dueDate
			// TermRangeQueryImpl termRangeRelease = new
			// TermRangeQueryImpl(DossierTerm.VALUE_COMPARE_RELEASE,
			// null, String.valueOf(0), true, false);
			// subQueryTwo.add(termRangeRelease, BooleanClauseOccur.MUST);
			MultiMatchQuery termRangeRelease = new MultiMatchQuery("3");
			termRangeRelease.addField(DossierTerm.VALUE_COMPARE_RELEASE);
			subQueryTwo.add(termRangeRelease, BooleanClauseOccur.MUST);
			/** Check condition finishDate < dueDate **/
			// TermRangeQueryImpl termRangeFinish = new
			// TermRangeQueryImpl(DossierTerm.VALUE_COMPARE_FINISH,
			// null, String.valueOf(0), true, false);
			MultiMatchQuery termRangeFinish =
					new MultiMatchQuery(String.valueOf(3));
			termRangeFinish.addField(DossierTerm.VALUE_COMPARE_FINISH);
			subQueryThree.add(termRangeFinish, BooleanClauseOccur.MUST);
			/**
			 * Check condition (extendDate != null && releaseDate < dueDate) ||
			 * (finishDate < dueDate)
			 **/
			subQueryFour.add(subQueryThree, BooleanClauseOccur.SHOULD);
			subQueryFour.add(subQueryTwo, BooleanClauseOccur.SHOULD);
			/** Check condition dueDate != null && subQueryTwo **/
			subQueryOne.add(subQueryFour, BooleanClauseOccur.MUST);
			/** Add search all **/
			booleanQuery.add(subQueryOne, BooleanClauseOccur.MUST);
		}
		else if (subTime.equals(DossierTerm.OVER_TIME)) { // Check list dossier
			// is overtime
			BooleanQuery subQueryOne = new BooleanQueryImpl();
			BooleanQuery subQueryTwo = new BooleanQueryImpl();

			/** Check condition releaseDate != null **/
			MultiMatchQuery queryRelease =
					new MultiMatchQuery(String.valueOf(0));
			queryRelease.addField(DossierTerm.RELEASE_DATE_TIMESTAMP);
			subQueryOne.add(queryRelease, BooleanClauseOccur.MUST_NOT);
			/** Check condition dueDate != null **/
			MultiMatchQuery querydueDate =
					new MultiMatchQuery(String.valueOf(0));
			querydueDate.addField(DossierTerm.DUE_DATE_TIMESTAMP);
			subQueryOne.add(querydueDate, BooleanClauseOccur.MUST_NOT);
			/** Check condition releaseDate > dueDate **/
			MultiMatchQuery termRangeRelease =
					new MultiMatchQuery(String.valueOf(1));
			termRangeRelease.addField(DossierTerm.VALUE_COMPARE_RELEASE);
			subQueryTwo.add(termRangeRelease, BooleanClauseOccur.MUST);
			// TermRangeQueryImpl termRangeRelease = new
			// TermRangeQueryImpl(DossierTerm.VALUE_COMPARE_RELEASE,
			// String.valueOf(0), null, false, false);
			// subQueryTwo.add(termRangeRelease, BooleanClauseOccur.MUST);
			/**
			 * Check condition releaseDate != null && dueDate != null &&
			 * subQueryTwo
			 **/
			subQueryOne.add(subQueryTwo, BooleanClauseOccur.MUST);
			/** Add search all **/
			booleanQuery.add(subQueryOne, BooleanClauseOccur.MUST);
		}
		else if (subTime.equals(DossierTerm.ON_TIME)) { // Check list dossier is
			// ontime
			BooleanQuery subQueryOne = new BooleanQueryImpl();
			BooleanQuery subQueryTwo = new BooleanQueryImpl();
			BooleanQuery subQueryThree = new BooleanQueryImpl();
			BooleanQuery subQueryFour = new BooleanQueryImpl();
			BooleanQuery subQueryFive = new BooleanQueryImpl();
			BooleanQuery subQuerySix = new BooleanQueryImpl();
			BooleanQuery subQuerySeven = new BooleanQueryImpl();

			/**
			 * Check condition releaseDate!=null && (dueDate==null ||
			 * (releaseDate<dueDate && extendDate==null &&
			 * (finishDate==null||finishDate>=dueDate))) - START
			 **/
			/** Check condition releaseDate != null **/
			MultiMatchQuery queryReleaseEmpty =
					new MultiMatchQuery(String.valueOf(0));
			queryReleaseEmpty.addField(DossierTerm.RELEASE_DATE_TIMESTAMP);
			subQueryOne.add(queryReleaseEmpty, BooleanClauseOccur.MUST_NOT);

			/**
			 * Check condition (dueDate==null || (releaseDate<dueDate &&
			 * extendDate==null && (finishDate==null||finishDate>=dueDate)) -
			 * START
			 **/
			/** Check condition dueDate == null **/
			MultiMatchQuery queryDueDateEmpty =
					new MultiMatchQuery(String.valueOf(0));
			queryDueDateEmpty.addField(DossierTerm.DUE_DATE_TIMESTAMP);
			subQueryTwo.add(queryDueDateEmpty, BooleanClauseOccur.MUST);

			/**
			 * Check condition (extendDate == null and releaseDate < dueDate &&
			 * (finishDate==null||finishDate>=dueDate))- START
			 **/
			/** Check condition extendDate == null and releaseDate < dueDate **/
			// Check extendDate == null
			MultiMatchQuery queryExtend =
					new MultiMatchQuery(String.valueOf(0));
			queryExtend.addField(DossierTerm.EXTEND_DATE_TIMESTAMP);
			subQueryThree.add(queryExtend, BooleanClauseOccur.MUST);
			// Check dueDate != null
			MultiMatchQuery queryDueDate =
					new MultiMatchQuery(String.valueOf(0));
			queryDueDate.addField(DossierTerm.DUE_DATE_TIMESTAMP);
			subQueryThree.add(queryDueDate, BooleanClauseOccur.MUST_NOT);
			// Check releaseDate < dueDate
			// TermRangeQueryImpl queryCompareRelease = new
			// TermRangeQueryImpl(DossierTerm.VALUE_COMPARE_RELEASE,
			// String.valueOf(2), String.valueOf(2), true, true);
			MultiMatchQuery queryCompareRelease =
					new MultiMatchQuery(String.valueOf(2));
			queryCompareRelease.addField(DossierTerm.VALUE_COMPARE_RELEASE);

			subQueryThree.add(queryCompareRelease, BooleanClauseOccur.MUST);

			/**
			 * Check condition (finishDate == null) || (finishDate != null &&
			 * finishDate >= dueDate) - START
			 **/
			/** Check condition (finishDate == null) **/
			MultiMatchQuery queryFinishDateEmpty =
					new MultiMatchQuery(String.valueOf(0));
			queryFinishDateEmpty.addField(DossierTerm.FINISH_DATE_TIMESTAMP);
			subQueryFour.add(queryFinishDateEmpty, BooleanClauseOccur.MUST);

			/**
			 * Check condition (finishDate != null && finishDate >= dueDate)
			 **/
			// Check finishDate != null
			MultiMatchQuery queryFinishDate =
					new MultiMatchQuery(String.valueOf(0));
			queryFinishDate.addField(DossierTerm.FINISH_DATE_TIMESTAMP);
			subQueryFive.add(queryFinishDate, BooleanClauseOccur.MUST_NOT);
			// Check finishDate >= dueDate
			// TermRangeQueryImpl queryCompareFinish = new
			// TermRangeQueryImpl(DossierTerm.VALUE_COMPARE_FINISH,
			// String.valueOf(1), String.valueOf(2), true, true);
			MultiMatchQuery queryCompareFinish =
					new MultiMatchQuery(String.valueOf(2));
			queryCompareFinish.addField(DossierTerm.VALUE_COMPARE_FINISH);

			subQueryFive.add(queryCompareFinish, BooleanClauseOccur.MUST);
			/**
			 * Check condition (finishDate == null) || (finishDate != null &&
			 * finishDate >= dueDate) - END
			 **/
			// subQuerySix.add(subQueryFive, BooleanClauseOccur.SHOULD);
			// subQuerySix.add(subQueryFour, BooleanClauseOccur.SHOULD);

			/**
			 * Check condition (releaseDate < dueDate && extendDate==null &&
			 * (finishDate==null||finishDate>=dueDate))- END
			 **/
			subQueryThree.add(subQuerySix, BooleanClauseOccur.MUST);

			/**
			 * Check condition (dueDate==null || (releaseDate<dueDate &&
			 * extendDate==null && (finishDate==null||finishDate>=dueDate)) -
			 * END
			 **/
			subQuerySeven.add(subQueryThree, BooleanClauseOccur.SHOULD);
			subQuerySeven.add(subQueryTwo, BooleanClauseOccur.SHOULD);
			/**
			 * Check condition releaseDate!=null && (dueDate==null ||
			 * (releaseDate<dueDate && extendDate==null &&
			 * (finishDate==null||finishDate>=dueDate))) - END
			 **/
			subQueryOne.add(subQuerySeven, BooleanClauseOccur.MUST);
			/** Add search all **/
			booleanQuery.add(subQueryOne, BooleanClauseOccur.MUST);
		}
		else if (subTime.equals(DossierTerm.OVER_DUE)) {// List dossier is
			// processing overdue
			/** Check condition releaseDate == null **/
			MultiMatchQuery queryRelease =
					new MultiMatchQuery(String.valueOf(0));
			queryRelease.addField(DossierTerm.RELEASE_DATE_TIMESTAMP);
			booleanQuery.add(queryRelease, BooleanClauseOccur.MUST);

			/** Check condition dueDate != null **/
			MultiMatchQuery querydueDate =
					new MultiMatchQuery(String.valueOf(0));
			querydueDate.addField(DossierTerm.DUE_DATE_TIMESTAMP);
			booleanQuery.add(querydueDate, BooleanClauseOccur.MUST_NOT);

			/** Check condition status != waiting **/
			MultiMatchQuery queryWaiting =
					new MultiMatchQuery(DossierTerm.DOSSIER_STATUS_WAITING);
			queryWaiting.addField(DossierTerm.DOSSIER_STATUS);
			booleanQuery.add(queryWaiting, BooleanClauseOccur.MUST_NOT);

			/** Check condition status != receiving **/
			MultiMatchQuery queryReceiving =
					new MultiMatchQuery(DossierTerm.DOSSIER_STATUS_RECEIVING);
			queryReceiving.addField(DossierTerm.DOSSIER_STATUS);
			booleanQuery.add(queryReceiving, BooleanClauseOccur.MUST_NOT);

			/** Check condition lockState != PAUSE **/
			// MultiMatchQuery queryLockState = new
			// MultiMatchQuery(DossierTerm.DOSSIER_STATUS_WAITING);
			// queryWaiting.addField(DossierTerm.DOSSIER_STATUS);
			// booleanQuery.add(queryWaiting, BooleanClauseOccur.MUST_NOT);

			/** Check condition dueDate < now **/
			Date date = new Date();
			long nowTime = date.getTime();
			TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
					DossierTerm.DUE_DATE_TIMESTAMP, String.valueOf(0),
					String.valueOf(nowTime), false, false);
			booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);

		}
		else if (subTime.equals(DossierTerm.UN_DUE)) {// List dossier is
			// processing undue
			BooleanQuery subQueryOne = new BooleanQueryImpl();
			BooleanQuery subQueryTwo = new BooleanQueryImpl();
			BooleanQuery subQueryThree = new BooleanQueryImpl();
			BooleanQuery subQueryFour = new BooleanQueryImpl();

			/** Check condition releaseDate == null **/
			MultiMatchQuery queryRelease =
					new MultiMatchQuery(String.valueOf(0));
			queryRelease.addField(DossierTerm.RELEASE_DATE_TIMESTAMP);
			subQueryOne.add(queryRelease, BooleanClauseOccur.MUST);

			/** Check condition dueDate == null **/
			MultiMatchQuery querydueDateNull =
					new MultiMatchQuery(String.valueOf(0));
			querydueDateNull.addField(DossierTerm.DUE_DATE_TIMESTAMP);
			subQueryTwo.add(querydueDateNull, BooleanClauseOccur.MUST);

			/** Check condition (dueDate != null && now < dueDate) - START **/
			// Check dueDate != null
			MultiMatchQuery querydueDate =
					new MultiMatchQuery(String.valueOf(0));
			querydueDate.addField(DossierTerm.DUE_DATE_TIMESTAMP);
			subQueryThree.add(querydueDate, BooleanClauseOccur.MUST_NOT);
			// Check condition dueDate < now
			Date date = new Date();
			long nowTime = date.getTime();
			TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
					DossierTerm.DUE_DATE_TIMESTAMP, String.valueOf(nowTime), null,
					true, false);
			subQueryThree.add(termRangeQuery, BooleanClauseOccur.MUST);
			/** Check condition (dueDate != null && now < dueDate) - END **/

			/**
			 * Check condition (dueDate==null || (dueDate!=null && now<dueDate))
			 **/
			subQueryFour.add(subQueryThree, BooleanClauseOccur.SHOULD);
			subQueryFour.add(subQueryTwo, BooleanClauseOccur.SHOULD);

			/**
			 * Check condition releaseDate==null && (dueDate==null ||
			 * (dueDate!=null && now<dueDate))
			 **/
			subQueryOne.add(subQueryFour, BooleanClauseOccur.MUST);
			//
			booleanQuery.add(subQueryOne, BooleanClauseOccur.MUST);
		}
		else if (subTime.equals(DossierTerm.COMING)) {// List dossier is
			// processing comming

			/** Check condition releaseDate == null **/
			MultiMatchQuery queryRelease =
					new MultiMatchQuery(String.valueOf(0));
			queryRelease.addField(DossierTerm.RELEASE_DATE_TIMESTAMP);
			booleanQuery.add(queryRelease, BooleanClauseOccur.MUST);

			/** Check condition dueDate != null **/
			MultiMatchQuery querydueDateNull =
					new MultiMatchQuery(String.valueOf(0));
			querydueDateNull.addField(DossierTerm.DUE_DATE_TIMESTAMP);
			booleanQuery.add(querydueDateNull, BooleanClauseOccur.MUST_NOT);

			/** Check condition (dueDate-duration/5) < now **/
			Date date = new Date();
			long nowTime = date.getTime();
			TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
					DossierTerm.DUE_DATE_COMING, String.valueOf(0),
					String.valueOf(nowTime), false, true);
			booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);

			/** Check conditionnowDate < dueDate **/
			TermRangeQueryImpl termRangeQueryNow = new TermRangeQueryImpl(
					DossierTerm.DUE_DATE_TIMESTAMP, String.valueOf(nowTime), null,
					true, true);
			booleanQuery.add(termRangeQueryNow, BooleanClauseOccur.MUST);

		}
		else if (subTime.equals(DossierTerm.DELAY)) {// List dossier is
			// processing delay

			/** Check condition releaseDate == null **/
			MultiMatchQuery queryRelease =
					new MultiMatchQuery(String.valueOf(0));
			queryRelease.addField(DossierTerm.RELEASE_DATE_TIMESTAMP);
			booleanQuery.add(queryRelease, BooleanClauseOccur.MUST);

			/** Check condition dueDate != null **/
			MultiMatchQuery querydueDate =
					new MultiMatchQuery(String.valueOf(0));
			querydueDate.addField(DossierTerm.DUE_DATE_TIMESTAMP);
			booleanQuery.add(querydueDate, BooleanClauseOccur.MUST_NOT);

			/** Check condition extendDate > dueDate **/
			MultiMatchQuery query = new MultiMatchQuery(String.valueOf(1));
			query.addFields(DossierTerm.COMPARE_DELAY_DATE);
			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		return booleanQuery;
	}

	private String getDossierTemplateName(
			long groupId, String dossierTemplateCode) {

		String name = StringPool.BLANK;

		DossierTemplate template = dossierTemplatePersistence.fetchByG_DT_TPLNO(
				groupId, dossierTemplateCode);

		if (Validator.isNotNull(template)) {
			name = template.getTemplateName();
		}

		return name;
	}

	private String getDossierNote(
			String serviceInfoCode, String govAgencyCode, String dossierTemplateNo,
			long groupId) {

		String dossierNote = StringPool.BLANK;

		ServiceInfo serviceInfo =
				serviceInfoPersistence.fetchBySC_GI(serviceInfoCode, groupId);

		try {

			ServiceConfig config =
					ServiceConfigLocalServiceUtil.getBySICodeAndGAC(
							groupId, serviceInfoCode, govAgencyCode);

			ProcessOption option =
					ProcessOptionLocalServiceUtil.getByDTPLNoAndServiceCF(
							groupId, dossierTemplateNo, config.getServiceConfigId());

			dossierNote = option.getInstructionNote();

			if (Validator.isNull(dossierNote)) {
				throw new Exception();
			}

		}
		catch (Exception e) {
			_log.debug(e);
			if (Validator.isNotNull(serviceInfo)) {
				dossierNote = serviceInfo.getProcessText();
			}
		}

		return dossierNote;
	}

	private String getDossierNote(
			ServiceInfo serviceInfo, ProcessOption option) {

		if (option != null) {
			return option.getInstructionNote();

		}
		else if (Validator.isNotNull(serviceInfo)) {
			return serviceInfo.getProcessText();
		}

		return StringPool.BLANK;
	}

	public long countDossierByG_C_GAC_SC_DTNO_NOTDS(
			long groupId, long companyId, String govAgencyCode, String serviceCode,
			String dossierTemplateNo, String dossierStatus) {

		return dossierPersistence.countByG_C_GAC_SC_DTNO_NOTDS(
				groupId, companyId, govAgencyCode, serviceCode, dossierTemplateNo,
				dossierStatus);
	}

	private String getServerNo(long groupId) {

		try {
			List<ServerConfig> sc =
					ServerConfigLocalServiceUtil.getGroupId(groupId);
			// _log.debug("sc.get(0).getServerNo():" + sc.get(0).getServerNo());
			return sc.get(0).getServerNo();
		}
		catch (Exception e) {
			_log.error(e);
			return StringPool.BLANK;
		}

	}

	// TrungDK: Process
	public List<Dossier> getDossierByG_NOTO_DS(
			int originality, String dossierStatus) {

		return dossierPersistence.findByNOTO_DS(originality, dossierStatus);
	}

	public List<Dossier> getDossierByG_NOTO_DS(
			int[] originalityArr, String dossierStatus) {

		return dossierPersistence.findByNOTO_DS(originalityArr, dossierStatus);
	}

	public void removeDossierByG_NOTO_DS(
			int[] originalityArr, String dossierStatus) {

		List<Dossier> lstDossiers =
				dossierPersistence.findByNOTO_DS(originalityArr, dossierStatus);
		Date now = new Date();

		if (lstDossiers != null && lstDossiers.size() > 0) {
			for (Dossier dossier : lstDossiers) {
				long diffInMillies =
						Math.abs(now.getTime() - dossier.getCreateDate().getTime());
				long diff = TimeUnit.MINUTES.convert(
						diffInMillies, TimeUnit.MILLISECONDS);

				try {
					if (diff > DossierTerm.GARBAGE_COLLECTOR_TIME)
						dossierPersistence.remove(dossier.getDossierId());
				}
				catch (NoSuchDossierException e) {
					_log.error(e);
				}
			}
		}
	}

	public void removeDossierByF_OG_DS(int originality, String dossierStatus) {

		List<Dossier> lstDossiers =
				dossierPersistence.findByF_OG_DS(originality, dossierStatus);
		Date now = new Date();

		if (lstDossiers != null && lstDossiers.size() > 0) {
			for (Dossier dossier : lstDossiers) {
				long diffInMillies =
						Math.abs(now.getTime() - dossier.getCreateDate().getTime());
				long diff = TimeUnit.HOURS.convert(
						diffInMillies, TimeUnit.MILLISECONDS);

				try {
					if (diff > DossierTerm.GARBAGE_COLLECTOR_GROUP_DOSSIER)
						dossierPersistence.remove(dossier.getDossierId());
				}
				catch (NoSuchDossierException e) {
					_log.error(e);
				}
			}
		}
	}

	public static final String CLASS_NAME = Dossier.class.getName();

	// LamTV: Process get Dossier by dossierId, govAgency, serviceProcess
	public Dossier getByIdAndGovService(
			long groupId, String serviceCode, String govAgencyCode,
			long dossierId) {

		return dossierPersistence.fetchByF_GID_GOV_DID(
				groupId, govAgencyCode, serviceCode, dossierId);
	}

	public List<Dossier> getByNotO_DS_SC_GC(
			long groupId, int originality, String dossierStatus, String serviceCode,
			String govAgencyCode) {

		return dossierPersistence.findByG_NOTO_DS_SC_GC(
				groupId, originality, dossierStatus, serviceCode, govAgencyCode);
	}

	// LamTV_Process update dossier
	@Indexable(type = IndexableType.REINDEX)
	public Dossier initUpdateDossier(
			long groupId, long id, String applicantName, String applicantIdType,
			String applicantIdNo, String applicantIdDate, String address,
			String cityCode, String cityName, String districtCode,
			String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String contactEmail,
			String dossierTemplateNo, Integer viaPostal, String postalAddress,
			String postalCityCode, String postalCityName,
			String postalDistrictCode, String postalDistrictName, String postalTelNo,
			String applicantNote, boolean isSameAsApplicant, String delegateName,
			String delegateIdNo, String delegateTelNo, String delegateEmail,
			String delegateAddress, String delegateCityCode,
			String delegateDistrictCode, String delegateWardCode, Long sampleCount,
			Integer vnpostalStatus, String vnpostalProfile, Integer fromViaPostal,
			ServiceContext serviceContext) {

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
					APIDateTimeUtils.convertStringToDate(
							applicantIdDate, APIDateTimeUtils._NORMAL_PARTTERN));
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

		if (Validator.isNotNull(viaPostal)) {
			dossier.setViaPostal(viaPostal);
			if (viaPostal == 1) {
				dossier.setPostalAddress(StringPool.BLANK);
				dossier.setPostalCityCode(StringPool.BLANK);
				dossier.setPostalTelNo(StringPool.BLANK);
				dossier.setPostalDistrictCode(StringPool.BLANK);

			}
			else if (viaPostal == 2) {
				if (Validator.isNotNull(postalAddress))
					dossier.setPostalAddress(postalAddress);
				if (Validator.isNotNull(postalCityCode))
					dossier.setPostalCityCode(postalCityCode);
				if (Validator.isNotNull(postalTelNo))
					dossier.setPostalTelNo(postalTelNo);
				if (Validator.isNotNull(postalCityName))
					dossier.setPostalCityName(postalCityName);
				if (Validator.isNotNull(postalDistrictCode))
					dossier.setPostalDistrictCode(postalDistrictCode);

			}
			else {
				dossier.setPostalAddress(StringPool.BLANK);
				dossier.setPostalCityCode(StringPool.BLANK);
				dossier.setPostalTelNo(StringPool.BLANK);
				dossier.setPostalDistrictCode(StringPool.BLANK);
			}
		}
		if (Validator.isNotNull(vnpostalStatus)) {
			dossier.setVnpostalStatus(vnpostalStatus);
		}
		if (Validator.isNotNull(fromViaPostal)) {
			dossier.setFromViaPostal(fromViaPostal);
		}
		if (Validator.isNotNull(vnpostalProfile)) {
			dossier.setVnpostalProfile(vnpostalProfile);
		}
		if (Validator.isNotNull(fromViaPostal)) {
			dossier.setFromViaPostal(fromViaPostal);
		}
		if (isSameAsApplicant) {
			dossier.setDelegateName(applicantName);
			dossier.setDelegateIdNo(applicantIdNo);
			dossier.setDelegateTelNo(contactTelNo);
			dossier.setDelegateAddress(address);
			dossier.setDelegateEmail(contactEmail);
			if (Validator.isNotNull(cityCode)) {
				dossier.setDelegateCityCode(cityCode);
				dossier.setDelegateCityName(
						getDictItemName(
								dossier.getGroupId(), ADMINISTRATIVE_REGION, cityCode));
			}

			if (Validator.isNotNull(districtCode)) {
				dossier.setDelegateDistrictCode(districtCode);
				dossier.setDelegateDistrictName(
						getDictItemName(
								dossier.getGroupId(), ADMINISTRATIVE_REGION,
								districtCode));
			}

			if (Validator.isNotNull(wardCode)) {
				dossier.setDelegateWardCode(wardCode);
				dossier.setDelegateWardName(
						getDictItemName(
								dossier.getGroupId(), ADMINISTRATIVE_REGION, wardCode));
			}
		}
		else {
			dossier.setDelegateName(delegateName);
			dossier.setDelegateIdNo(delegateIdNo);
			dossier.setDelegateTelNo(delegateTelNo);
			dossier.setDelegateAddress(delegateAddress);
			dossier.setDelegateEmail(delegateEmail);

			if (Validator.isNotNull(delegateCityCode)) {
				dossier.setDelegateCityCode(delegateCityCode);
				dossier.setDelegateCityName(
						getDictItemName(
								dossier.getGroupId(), ADMINISTRATIVE_REGION,
								delegateCityCode));
			}

			if (Validator.isNotNull(delegateDistrictCode)) {
				dossier.setDelegateDistrictCode(delegateDistrictCode);
				dossier.setDelegateDistrictName(
						getDictItemName(
								dossier.getGroupId(), ADMINISTRATIVE_REGION,
								delegateDistrictCode));
			}

			if (Validator.isNotNull(delegateWardCode)) {
				dossier.setDelegateWardCode(delegateWardCode);
				dossier.setDelegateWardName(
						getDictItemName(
								dossier.getGroupId(), ADMINISTRATIVE_REGION,
								delegateWardCode));
			}
		}

		dossier.setApplicantNote(applicantNote);

		return dossierPersistence.update(dossier);

	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier initUpdateDossier(
			long groupId, long id, String applicantName, String applicantIdType,
			String applicantIdNo, String applicantIdDate, String address,
			String cityCode, String cityName, String districtCode,
			String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String contactEmail,
			String dossierTemplateNo, Integer viaPostal, String postalAddress,
			String postalCityCode, String postalCityName,
			String postalDistrictCode, String postalDistrictName, String postalTelNo,
			String applicantNote, boolean isSameAsApplicant, String delegateName,
			String delegateIdNo, String delegateTelNo, String delegateEmail,
			String delegateAddress, String delegateCityCode,
			String delegateDistrictCode, String delegateWardCode, Long sampleCount,
			String dossierName, Integer vnpostalStatus, String vnpostalProfile, Integer fromViaPostal,
			ServiceContext serviceContext) {

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
					APIDateTimeUtils.convertStringToDate(
							applicantIdDate, APIDateTimeUtils._NORMAL_PARTTERN));
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
		if (Validator.isNotNull(dossierName)) {
			dossier.setDossierName(dossierName);
		}
		if (Validator.isNotNull(viaPostal)) {
			dossier.setViaPostal(viaPostal);
			if (viaPostal == 1) {
				dossier.setPostalAddress(StringPool.BLANK);
				dossier.setPostalCityCode(StringPool.BLANK);
				dossier.setPostalTelNo(StringPool.BLANK);
				dossier.setPostalDistrictCode(StringPool.BLANK);

			}
			else if (viaPostal == 2) {
				if (Validator.isNotNull(postalAddress))
					dossier.setPostalAddress(postalAddress);
				if (Validator.isNotNull(postalCityCode))
					dossier.setPostalCityCode(postalCityCode);
				if (Validator.isNotNull(postalTelNo))
					dossier.setPostalTelNo(postalTelNo);
				if (Validator.isNotNull(postalCityName))
					dossier.setPostalCityName(postalCityName);
				if (Validator.isNotNull(postalDistrictCode))
					dossier.setPostalDistrictCode(postalDistrictCode);

			}
			else {
				dossier.setPostalAddress(StringPool.BLANK);
				dossier.setPostalCityCode(StringPool.BLANK);
				dossier.setPostalTelNo(StringPool.BLANK);
				dossier.setPostalDistrictCode(StringPool.BLANK);
			}
		}
		if (Validator.isNotNull(vnpostalStatus)) {
			dossier.setVnpostalStatus(vnpostalStatus);
		}
		if (Validator.isNotNull(vnpostalProfile)) {
			dossier.setVnpostalProfile(vnpostalProfile);
		}
		if (Validator.isNotNull(fromViaPostal)) {
			dossier.setFromViaPostal(fromViaPostal);
		}
		if (isSameAsApplicant) {
			dossier.setDelegateName(applicantName);
			dossier.setDelegateIdNo(applicantIdNo);
			dossier.setDelegateTelNo(contactTelNo);
			dossier.setDelegateAddress(address);
			dossier.setDelegateEmail(contactEmail);
			if (Validator.isNotNull(cityCode)) {
				dossier.setDelegateCityCode(cityCode);
				dossier.setDelegateCityName(
						getDictItemName(
								dossier.getGroupId(), ADMINISTRATIVE_REGION, cityCode));
			}

			if (Validator.isNotNull(districtCode)) {
				dossier.setDelegateDistrictCode(districtCode);
				dossier.setDelegateDistrictName(
						getDictItemName(
								dossier.getGroupId(), ADMINISTRATIVE_REGION,
								districtCode));
			}

			if (Validator.isNotNull(wardCode)) {
				dossier.setDelegateWardCode(wardCode);
				dossier.setDelegateWardName(
						getDictItemName(
								dossier.getGroupId(), ADMINISTRATIVE_REGION, wardCode));
			}
		}
		else {
			dossier.setDelegateName(delegateName);
			dossier.setDelegateIdNo(delegateIdNo);
			dossier.setDelegateTelNo(delegateTelNo);
			dossier.setDelegateAddress(delegateAddress);
			dossier.setDelegateEmail(delegateEmail);

			if (Validator.isNotNull(delegateCityCode)) {
				dossier.setDelegateCityCode(delegateCityCode);
				dossier.setDelegateCityName(
						getDictItemName(
								dossier.getGroupId(), ADMINISTRATIVE_REGION,
								delegateCityCode));
			}

			if (Validator.isNotNull(delegateDistrictCode)) {
				dossier.setDelegateDistrictCode(delegateDistrictCode);
				dossier.setDelegateDistrictName(
						getDictItemName(
								dossier.getGroupId(), ADMINISTRATIVE_REGION,
								delegateDistrictCode));
			}

			if (Validator.isNotNull(delegateWardCode)) {
				dossier.setDelegateWardCode(delegateWardCode);
				dossier.setDelegateWardName(
						getDictItemName(
								dossier.getGroupId(), ADMINISTRATIVE_REGION,
								delegateWardCode));
			}
		}

		dossier.setApplicantNote(applicantNote);

		return dossierPersistence.update(dossier);

	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateApplicantInfo(
			long dossierId, Date applicantIdDate, String applicantIdNo,
			String applicantIdType, String applicantName, String address,
			String cityCode, String cityName, String districtCode,
			String districtName, String wardCode, String wardName,
			String contactEmail, String contactTelNo)
			throws NoSuchDossierException {

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
	public Dossier updateDossier(long dossierId, JSONObject obj)
			throws NoSuchDossierException {

		// _log.debug("Object dossier update: " + obj.toJSONString());
		Dossier dossier = dossierPersistence.findByPrimaryKey(dossierId);
		if (obj.has(DossierTerm.DOSSIER_NOTE)) {
			if (!obj.getString(DossierTerm.DOSSIER_NOTE).equals(
					dossier.getDossierNote())) {
				dossier.setDossierNote(obj.getString(DossierTerm.DOSSIER_NOTE));
			}
		}
		if (obj.has(DossierTerm.EXTEND_DATE) &&
				Validator.isNotNull(obj.get(DossierTerm.EXTEND_DATE)) &&
				GetterUtil.getLong(obj.get(DossierTerm.EXTEND_DATE)) > 0) {
			if (dossier.getExtendDate() == null || obj.getLong(
					DossierTerm.EXTEND_DATE) != dossier.getExtendDate().getTime()) {
				dossier.setExtendDate(
						new Date(obj.getLong(DossierTerm.EXTEND_DATE)));
			}
		}
		if (obj.has(DossierTerm.DOSSIER_NO)) {
			// _log.debug("Sync dossier no");
			if (Validator.isNotNull(obj.getString(DossierTerm.DOSSIER_NO)) &&
					!obj.getString(DossierTerm.DOSSIER_NO).equals(
							dossier.getDossierNo())) {
				// _log.debug("Sync set dossier no");
				dossier.setDossierNo(obj.getString(DossierTerm.DOSSIER_NO));
			}
		}
		if (obj.has(DossierTerm.DUE_DATE) &&
				Validator.isNotNull(obj.get(DossierTerm.DUE_DATE)) &&
				GetterUtil.getLong(obj.get(DossierTerm.DUE_DATE)) > 0) {
			if (dossier.getDueDate() == null || obj.getLong(
					DossierTerm.DUE_DATE) != dossier.getDueDate().getTime()) {
				dossier.setDueDate(new Date(obj.getLong(DossierTerm.DUE_DATE)));
			}
		}
		if (obj.has(DossierTerm.FINISH_DATE) &&
				Validator.isNotNull(obj.get(DossierTerm.FINISH_DATE)) &&
				GetterUtil.getLong(obj.get(DossierTerm.FINISH_DATE)) > 0) {
			if (dossier.getFinishDate() == null || obj.getLong(
					DossierTerm.FINISH_DATE) != dossier.getFinishDate().getTime()) {
				dossier.setFinishDate(
						new Date(obj.getLong(DossierTerm.FINISH_DATE)));
			}
		}
		if (obj.has(DossierTerm.RECEIVE_DATE) &&
				Validator.isNotNull(obj.get(DossierTerm.RECEIVE_DATE)) &&
				GetterUtil.getLong(obj.get(DossierTerm.RECEIVE_DATE)) > 0) {
			if (dossier.getReceiveDate() == null || obj.getLong(
					DossierTerm.RECEIVE_DATE) != dossier.getReceiveDate().getTime()) {
				dossier.setReceiveDate(
						new Date(obj.getLong(DossierTerm.RECEIVE_DATE)));
			}
		}
		if (obj.has(DossierTerm.SUBMIT_DATE) &&
				Validator.isNotNull(obj.get(DossierTerm.SUBMIT_DATE)) &&
				GetterUtil.getLong(obj.get(DossierTerm.SUBMIT_DATE)) > 0) {
			if (dossier.getSubmitDate() == null ||
					(dossier.getSubmitDate() != null && obj.getLong(
							DossierTerm.SUBMIT_DATE) != dossier.getSubmitDate().getTime())) {
				dossier.setSubmitDate(
						new Date(obj.getLong(DossierTerm.SUBMIT_DATE)));
			}
		}
		if (obj.has(DossierTerm.EXTEND_DATE) &&
				Validator.isNotNull(obj.get(DossierTerm.EXTEND_DATE)) &&
				GetterUtil.getLong(obj.get(DossierTerm.EXTEND_DATE)) > 0) {
			if (dossier.getExtendDate() == null || obj.getLong(
					DossierTerm.EXTEND_DATE) != dossier.getExtendDate().getTime()) {
				dossier.setExtendDate(
						new Date(obj.getLong(DossierTerm.EXTEND_DATE)));
			}
		}
		if (obj.has(DossierTerm.DOSSIER_NOTE)) {
			if (dossier.getDossierNote() == null ||
					!obj.getString(DossierTerm.DOSSIER_NOTE).equals(
							dossier.getDossierNote())) {
				dossier.setDossierNote(obj.getString(DossierTerm.DOSSIER_NOTE));
			}
		}
		if (obj.has(DossierTerm.SUBMISSION_NOTE)) {
			if (!obj.getString(DossierTerm.SUBMISSION_NOTE).equals(
					dossier.getDossierNote())) {
				dossier.setSubmissionNote(
						obj.getString(DossierTerm.SUBMISSION_NOTE));
			}
		}
		if (obj.has(DossierTerm.RELEASE_DATE) &&
				Validator.isNotNull(obj.get(DossierTerm.RELEASE_DATE)) &&
				GetterUtil.getLong(obj.get(DossierTerm.RELEASE_DATE)) > 0) {
			if (dossier.getReleaseDate() == null || obj.getLong(
					DossierTerm.RELEASE_DATE) != dossier.getReleaseDate().getTime()) {
				dossier.setReleaseDate(
						new Date(obj.getLong(DossierTerm.RELEASE_DATE)));
			}
		}
		if (obj.has(DossierTerm.LOCK_STATE)) {
			if (!obj.getString(DossierTerm.LOCK_STATE).equals(
					dossier.getLockState())) {
				dossier.setLockState(obj.getString(DossierTerm.LOCK_STATE));
			}
		}
		if (obj.has(DossierTerm.BRIEF_NOTE)) {
			if (!obj.getString(DossierTerm.BRIEF_NOTE).equals(
					dossier.getBriefNote())) {
				dossier.setBriefNote(obj.getString(DossierTerm.BRIEF_NOTE));
			}
		}

		return dossierPersistence.update(dossier);
	}

	public Dossier getByOrigin(long groupId, long originDossierId) {

		return dossierPersistence.fetchByG_O_DID_First(
				groupId, originDossierId, null);
	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier publishDossier(
			long groupId, long dossierId, String referenceUid, int counter,
			String serviceCode, String serviceName, String govAgencyCode,
			String govAgencyName, String applicantName, String applicantIdType,
			String applicantIdNo, Date applicantIdDate, String address,
			String cityCode, String cityName, String districtCode,
			String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String contactEmail,
			String dossierTemplateNo, String password, int viaPostal,
			String postalAddress, String postalCityCode, String postalCityName,
			String postalDistrictCode, String postalDistrictName,
			String postalTelNo, boolean online, boolean notification,
			String applicantNote, int originality, Date createDate,
			Date modifiedDate, Date submitDate, Date receiveDate, Date dueDate,
			Date releaseDate, Date finishDate, Date cancellingDate,
			Date correctingDate, Date endorsementDate, Date extendDate,
			Date processDate, String dossierNo, String dossierStatus,
			String dossierStatusText, String dossierSubStatus,
			String dossierSubStatusText, long dossierActionId,
			String submissionNote, String lockState, String delegateName,
			String delegateIdNo, String delegateTelNo, String delegateEmail,
			String delegateAddress, String delegateCityCode,
			String delegateCityName, String delegateDistrictCode,
			String delegateDistrictName, String delegateWardCode,
			String delegateWardName, double durationCount, int durationUnit,
			String dossierName, String processNo, String metaData,
			Integer vnpostalStatus, String vnpostalProfile, Integer fromViaPostal, ServiceContext context)
			throws PortalException {

		long userId = context.getUserId();

		User auditUser = userPersistence.fetchByPrimaryKey(userId);

		validateInit(
				groupId, dossierId, referenceUid, serviceCode, govAgencyCode,
				address, cityCode, districtCode, wardCode, contactName,
				contactTelNo, contactEmail, dossierTemplateNo);

		Dossier dossier = null;
		dossier = getByRef(groupId, referenceUid);

		if (dossier == null) {
			String dossierTemplateName =
					getDossierTemplateName(groupId, dossierTemplateNo);

			dossierId = counterLocalService.increment(Dossier.class.getName());

			String dossierNote = getDossierNote(
					serviceCode, govAgencyCode, dossierTemplateNo, groupId);

			dossier = dossierPersistence.create(dossierId);

			dossier.setCreateDate(createDate);
			dossier.setModifiedDate(modifiedDate);
			dossier.setSubmitDate(submitDate);
			dossier.setReceiveDate(receiveDate);
			dossier.setDueDate(dueDate);
			dossier.setReleaseDate(releaseDate);
			dossier.setFinishDate(finishDate);
			dossier.setCancellingDate(cancellingDate);
			dossier.setCorrecttingDate(correctingDate);
			dossier.setEndorsementDate(endorsementDate);
			dossier.setExtendDate(extendDate);
			dossier.setProcessDate(processDate);

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
			dossier.setPostalDistrictCode(postalDistrictCode);
			dossier.setPostalDistrictName(postalDistrictName);
			dossier.setPostalTelNo(postalTelNo);
			dossier.setApplicantNote(applicantNote);
			// dossier.setServerNo(getServerNo(groupId));
			dossier.setOriginality(originality);
			//
			dossier.setDossierNo(dossierNo);
			dossier.setDossierStatus(dossierStatus);
			dossier.setDossierStatusText(dossierStatusText);
			dossier.setDossierSubStatus(dossierSubStatus);
			dossier.setDossierSubStatusText(dossierSubStatusText);
			dossier.setDossierActionId(dossierActionId);
			dossier.setSubmissionNote(submissionNote);
			dossier.setLockState(lockState);
			dossier.setDelegateName(delegateName);
			dossier.setDelegateIdNo(delegateIdNo);
			dossier.setDelegateTelNo(delegateTelNo);
			dossier.setDelegateEmail(delegateEmail);
			dossier.setDelegateAddress(delegateAddress);
			dossier.setDelegateCityCode(delegateCityCode);
			dossier.setDelegateCityName(delegateCityName);
			dossier.setDelegateDistrictCode(delegateDistrictCode);
			dossier.setDelegateDistrictName(delegateDistrictName);
			dossier.setDelegateWardCode(delegateWardCode);
			dossier.setDelegateWardName(delegateWardName);
			dossier.setDurationCount(durationCount);
			dossier.setDurationUnit(durationUnit);
			dossier.setDossierName(dossierName);
			dossier.setProcessNo(processNo);
			dossier.setMetaData(metaData);
			dossier.setVnpostalStatus(vnpostalStatus);
			dossier.setVnpostalProfile(vnpostalProfile);
			if (Validator.isNotNull(fromViaPostal)) {
				dossier.setFromViaPostal(fromViaPostal);
			}

			dossier = dossierPersistence.update(dossier);
		}
		else {
			dossier.setModifiedDate(modifiedDate);
			dossier.setSubmitDate(submitDate);
			dossier.setReceiveDate(receiveDate);
			dossier.setDueDate(dueDate);
			dossier.setReleaseDate(releaseDate);
			dossier.setFinishDate(finishDate);
			dossier.setCancellingDate(cancellingDate);
			dossier.setCorrecttingDate(correctingDate);
			dossier.setEndorsementDate(endorsementDate);
			dossier.setExtendDate(extendDate);
			dossier.setProcessDate(processDate);

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

			if (Validator.isNotNull(dossierNo))
				dossier.setDossierNo(dossierNo);
			if (Validator.isNotNull(dossierStatus))
				dossier.setDossierStatus(dossierStatus);
			if (Validator.isNotNull(dossierStatusText))
				dossier.setDossierStatusText(dossierStatusText);
			if (Validator.isNotNull(dossierSubStatus))
				dossier.setDossierSubStatus(dossierSubStatus);
			if (Validator.isNotNull(dossierSubStatusText))
				dossier.setDossierSubStatusText(dossierSubStatusText);
			if (Validator.isNotNull(dossierActionId))
				dossier.setDossierActionId(dossierActionId);
			if (Validator.isNotNull(submissionNote))
				dossier.setSubmissionNote(submissionNote);
			if (Validator.isNotNull(lockState))
				dossier.setLockState(lockState);
			if (Validator.isNotNull(delegateName))
				dossier.setDelegateName(delegateName);
			if (Validator.isNotNull(delegateIdNo))
				dossier.setDelegateIdNo(delegateIdNo);
			if (Validator.isNotNull(delegateTelNo))
				dossier.setDelegateTelNo(delegateTelNo);
			if (Validator.isNotNull(delegateEmail))
				dossier.setDelegateEmail(delegateEmail);
			if (Validator.isNotNull(delegateAddress))
				dossier.setDelegateAddress(delegateAddress);
			if (Validator.isNotNull(delegateCityCode))
				dossier.setDelegateCityCode(delegateCityCode);
			if (Validator.isNotNull(delegateCityName))
				dossier.setDelegateCityName(delegateCityName);
			if (Validator.isNotNull(delegateDistrictCode))
				dossier.setDelegateDistrictCode(delegateDistrictCode);
			if (Validator.isNotNull(delegateDistrictName))
				dossier.setDelegateDistrictName(delegateDistrictName);
			if (Validator.isNotNull(delegateWardCode))
				dossier.setDelegateWardCode(delegateWardCode);
			if (Validator.isNotNull(delegateWardName))
				dossier.setDelegateWardName(delegateWardName);
			if (Validator.isNotNull(durationCount))
				dossier.setDurationCount(durationCount);
			if (Validator.isNotNull(durationUnit))
				dossier.setDurationUnit(durationUnit);
			if (Validator.isNotNull(dossierName))
				dossier.setDossierName(dossierName);
			if (Validator.isNotNull(processNo))
				dossier.setProcessNo(processNo);
			if (Validator.isNotNull(metaData))
				dossier.setProcessNo(metaData);

			dossier.setViaPostal(viaPostal);
			if (viaPostal == 1) {
				dossier.setPostalAddress(StringPool.BLANK);
				dossier.setPostalCityCode(StringPool.BLANK);
				dossier.setPostalTelNo(StringPool.BLANK);
				dossier.setPostalDistrictCode(StringPool.BLANK);

			}
			else if (viaPostal == 2) {
				if (Validator.isNotNull(postalAddress))
					dossier.setPostalAddress(postalAddress);
				if (Validator.isNotNull(postalCityCode))
					dossier.setPostalCityCode(postalCityCode);
				if (Validator.isNotNull(postalTelNo))
					dossier.setPostalTelNo(postalTelNo);
				if (Validator.isNotNull(postalCityName))
					dossier.setPostalCityName(postalCityName);
				if (Validator.isNotNull(postalDistrictCode))
					dossier.setPostalDistrictCode(postalDistrictCode);
				if (Validator.isNotNull(postalDistrictName))
					dossier.setPostalDistrictCode(postalDistrictName);

			}
			else {
				dossier.setPostalAddress(StringPool.BLANK);
				dossier.setPostalCityCode(StringPool.BLANK);
				dossier.setPostalTelNo(StringPool.BLANK);
				dossier.setPostalDistrictCode(StringPool.BLANK);
			}

			// if (Validator.isNotNull(applicantNote))
			dossier.setApplicantNote(applicantNote);

			dossier.setVnpostalStatus(vnpostalStatus);
			dossier.setVnpostalProfile(vnpostalProfile);
			if (Validator.isNotNull(fromViaPostal)) {
				dossier.setFromViaPostal(fromViaPostal);
			}

			dossier = dossierPersistence.update(dossier);

		}

		return dossier;
	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier rollback(Dossier dossier, DossierAction dossierAction) {

		ProcessStep processStep = ProcessStepLocalServiceUtil.fetchBySC_GID(
				dossierAction.getStepCode(), dossier.getGroupId(),
				dossierAction.getServiceProcessId());
		if (processStep != null) {
			dossierAction.setState(DossierActionTerm.STATE_WAITING_PROCESSING);
			dossierAction = DossierActionLocalServiceUtil.updateState(
					dossierAction.getDossierActionId(),
					DossierActionTerm.STATE_WAITING_PROCESSING);
			JSONObject jsonDataStatusText = getStatusText(
					dossier.getGroupId(), DossierTerm.DOSSIER_SATUS_DC_CODE,
					processStep.getDossierStatus(),
					processStep.getDossierSubStatus());

			dossier.setDossierActionId(dossierAction.getDossierActionId());
			dossier.setDossierStatus(processStep.getDossierStatus());
			dossier.setDossierStatusText(
					jsonDataStatusText != null
							? jsonDataStatusText.getString(
							processStep.getDossierStatus())
							: StringPool.BLANK);
			dossier.setDossierSubStatus(processStep.getDossierSubStatus());
			dossier.setDossierSubStatusText(
					jsonDataStatusText != null
							? jsonDataStatusText.getString(
							processStep.getDossierSubStatus())
							: StringPool.BLANK);
		}
		return dossierPersistence.update(dossier);
	}

	private JSONObject getStatusText(
			long groupId, String collectionCode, String curStatus,
			String curSubStatus) {

		JSONObject jsonData = null;
		DictCollection dc =
				DictCollectionLocalServiceUtil.fetchByF_dictCollectionCode(
						collectionCode, groupId);

		if (Validator.isNotNull(dc) && Validator.isNotNull(curStatus)) {
			jsonData = JSONFactoryUtil.createJSONObject();
			DictItem it = DictItemLocalServiceUtil.fetchByF_dictItemCode(
					curStatus, dc.getPrimaryKey(), groupId);
			if (Validator.isNotNull(it)) {
				jsonData.put(curStatus, it.getItemName());
				if (Validator.isNotNull(curSubStatus)) {
					DictItem dItem =
							DictItemLocalServiceUtil.fetchByF_dictItemCode(
									curSubStatus, dc.getPrimaryKey(), groupId);
					if (Validator.isNotNull(dItem)) {
						jsonData.put(curSubStatus, dItem.getItemName());
					}
				}
			}
		}

		return jsonData;
	}

	public long countDossierByGroup(long groupId) {

		return dossierPersistence.countByG(groupId);
	}

	public List<Dossier> findDossierByGroup(long groupId) {

		return dossierPersistence.findByG(groupId);
	}

	public List<Dossier> findByDN_AN(String dossierNo, String applicantIdNo) {

		return dossierPersistence.findByDN_AN(dossierNo, applicantIdNo);
	}

	public List<Dossier> getByU_G_C_DS_SC_GC_O(
			long userId, long groupId, String serviceCode, String govAgencyCode,
			long dossierActionId, int originality) {

		return dossierPersistence.findByU_G_GAC_SC_DTNO_DAI_O(
				userId, groupId, govAgencyCode, serviceCode, dossierActionId,
				originality);
	}

	public List<Dossier> findByVIAPOSTAL(int viaPostal) {

		return dossierPersistence.findByVIAPOSTAL(viaPostal);
	}

	public List<Dossier> findByVnpostalStatus(long groupId, int vnpostalStatus) {

		return dossierPersistence.findByG_VNP_STT(groupId, vnpostalStatus);
	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier cloneDossier(Dossier srcDossier)
			throws PortalException {

		long desDossierId =
				counterLocalService.increment(Dossier.class.getName());
		Dossier desDossier = dossierPersistence.create(desDossierId);

		int counter = DossierNumberGenerator.counterDossier(
				srcDossier.getUserId(), desDossierId);
		String referenceUid = DossierNumberGenerator.generateReferenceUID(
				desDossier.getGroupId());

		desDossier.setCounter(counter);
		desDossier.setReferenceUid(referenceUid);
		LinkedHashMap<String, Object> params =
				new LinkedHashMap<String, Object>();
		params.put(DossierTerm.GOV_AGENCY_CODE, srcDossier.getGovAgencyCode());
		params.put(DossierTerm.SERVICE_CODE, srcDossier.getServiceCode());
		params.put(
				DossierTerm.DOSSIER_TEMPLATE_NO, srcDossier.getDossierTemplateNo());
		params.put(DossierTerm.DOSSIER_STATUS, StringPool.BLANK);

		Date now = new Date();

		desDossier.setCreateDate(now);
		desDossier.setModifiedDate(now);
		desDossier.setCompanyId(srcDossier.getCompanyId());
		desDossier.setGroupId(srcDossier.getGroupId());
		desDossier.setUserId(srcDossier.getUserId());
		desDossier.setUserName(srcDossier.getUserName());

		// Add extent fields
		desDossier.setServiceCode(srcDossier.getServiceCode());
		desDossier.setServiceName(srcDossier.getServiceName());
		desDossier.setGovAgencyCode(srcDossier.getGovAgencyCode());
		desDossier.setGovAgencyName(srcDossier.getGovAgencyName());
		desDossier.setDossierTemplateNo(srcDossier.getDossierTemplateNo());
		desDossier.setDossierTemplateName(srcDossier.getDossierTemplateName());

		desDossier.setApplicantName(srcDossier.getApplicantName());
		desDossier.setApplicantIdType(srcDossier.getApplicantIdType());
		desDossier.setApplicantIdNo(srcDossier.getApplicantIdNo());
		desDossier.setApplicantIdDate(srcDossier.getApplicantIdDate());
		desDossier.setPassword(srcDossier.getPassword());
		desDossier.setOnline(srcDossier.getOnline());
		desDossier.setDossierNote(srcDossier.getDossierNote());

		desDossier.setAddress(srcDossier.getAddress());
		desDossier.setCityCode(srcDossier.getCityCode());
		desDossier.setCityName(srcDossier.getCityName());
		desDossier.setDistrictCode(srcDossier.getDistrictCode());
		desDossier.setDistrictName(srcDossier.getDistrictName());
		desDossier.setWardCode(srcDossier.getWardCode());
		desDossier.setWardName(srcDossier.getWardName());
		desDossier.setContactName(srcDossier.getContactName());
		desDossier.setContactEmail(srcDossier.getContactEmail());
		desDossier.setContactTelNo(srcDossier.getContactTelNo());

		desDossier.setViaPostal(srcDossier.getViaPostal());
		desDossier.setPostalAddress(srcDossier.getPostalAddress());
		desDossier.setPostalCityCode(srcDossier.getPostalCityCode());
		desDossier.setPostalCityName(srcDossier.getPostalCityName());
		desDossier.setPostalDistrictCode(srcDossier.getPostalDistrictCode());
		desDossier.setPostalDistrictName(srcDossier.getPostalDistrictName());
		desDossier.setPostalTelNo(srcDossier.getPostalTelNo());
		desDossier.setApplicantNote(srcDossier.getApplicantNote());

		desDossier.setServerNo(srcDossier.getServerNo());
		desDossier.setOriginality(srcDossier.getOriginality());

		desDossier.setDurationCount(srcDossier.getDurationCount());
		desDossier.setDurationUnit(srcDossier.getDurationUnit());
		// desDossier.setDossierStatus(srcDossier.getDossierStatus());
		// desDossier.setDossierStatusText(srcDossier.getDossierStatusText());
		// desDossier.setDossierSubStatus(srcDossier.getDossierSubStatus());
		// desDossier.setDossierSubStatusText(srcDossier.getDossierSubStatusText());
		desDossier.setDelegateName(srcDossier.getDelegateName());
		desDossier.setDelegateAddress(srcDossier.getDelegateAddress());
		desDossier.setDelegateCityCode(srcDossier.getDelegateCityCode());
		desDossier.setDelegateCityName(srcDossier.getDelegateCityName());
		desDossier.setDelegateDistrictCode(
				srcDossier.getDelegateDistrictCode());
		desDossier.setDelegateDistrictName(
				srcDossier.getDelegateDistrictName());
		desDossier.setDelegateWardCode(srcDossier.getDelegateWardCode());
		desDossier.setDelegateWardName(srcDossier.getDelegateWardName());
		desDossier.setDelegateEmail(srcDossier.getDelegateEmail());
		desDossier.setDelegateIdNo(srcDossier.getDelegateIdNo());
		desDossier.setDelegateTelNo(srcDossier.getDelegateTelNo());
		desDossier.setDossierName(srcDossier.getDossierName());
		desDossier.setRegisterBookCode(srcDossier.getRegisterBookCode());
		desDossier.setProcessNo(srcDossier.getProcessNo());

		// dossierPersistence.update(desDossier);
		// ServiceProcess serviceProcess = null;
		// ProcessOption option = getProcessOption(srcDossier.getServiceCode(),
		// srcDossier.getGovAgencyCode(), srcDossier.getDossierTemplateNo(),
		// srcDossier.getGroupId());
		// _log.debug("Process option: " + option);
		// if (option != null) {
		// long serviceProcessId = option.getServiceProcessId();
		// serviceProcess =
		// ServiceProcessLocalServiceUtil.fetchServiceProcess(serviceProcessId);
		// String dossierRef =
		// DossierNumberGenerator.generateDossierNumber(srcDossier.getGroupId(),
		// srcDossier.getCompanyId(),
		// desDossierId, option.getProcessOptionId(),
		// serviceProcess.getDossierNoPattern(), params);
		// _log.debug("Dossier no: " + dossierRef);
		// desDossier.setDossierNo(dossierRef.trim());
		// }

		// set dueDate
		desDossier.setDueDate(srcDossier.getDueDate());
		// set receivedDate
		desDossier.setReceiveDate(srcDossier.getReceiveDate());
		desDossier.setDelegateType(srcDossier.getDelegateType());
		desDossier.setDocumentNo(srcDossier.getDocumentNo());
		desDossier.setDocumentDate(srcDossier.getDocumentDate());

		return dossierPersistence.update(desDossier);
	}

	public Dossier getByDossierNo(long groupId, String dossierNo) {

		try {
			return dossierPersistence.findByG_DN(groupId, dossierNo);
		} catch (NoSuchDossierException e) {
			return null;
		}
	}

	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public Dossier adminProcessDelete(Long id) {

		Dossier object = dossierPersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		}
		else {
			dossierPersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier adminProcessData(JSONObject objectData) {

		Dossier object = null;

		if (objectData.getLong("dossierId") > 0) {

			object = dossierPersistence.fetchByPrimaryKey(
					objectData.getLong("dossierId"));

			object.setModifiedDate(new Date());

		}
		else {

			long id =
					CounterLocalServiceUtil.increment(Dossier.class.getName());

			object = dossierPersistence.create(id);

			object.setCreateDate(new Date());

		}

		object.setGroupId(objectData.getLong("groupId"));
		object.setCompanyId(objectData.getLong("companyId"));

		object.setUserId(objectData.getLong("userId"));
		object.setUserName(objectData.getString("userName"));

		object.setReferenceUid(objectData.getString("referenceUid"));
		object.setCounter(objectData.getInt("counter"));
		object.setRegisterBookCode(objectData.getString("registerBookCode"));
		object.setRegisterBookName(objectData.getString("registerBookName"));
		object.setDossierRegister(objectData.getString("dossierRegister"));
		object.setProcessNo(objectData.getString("processNo"));
		object.setServiceCode(objectData.getString("serviceCode"));
		object.setServiceName(objectData.getString("serviceName"));
		object.setGovAgencyCode(objectData.getString("govAgencyCode"));
		object.setApplicantIdType(objectData.getString("applicantIdType"));
		object.setApplicantIdNo(objectData.getString("applicantIdNo"));
		if (Validator.isNotNull(objectData.getLong("applicantIdDate"))) {

			object.setApplicantIdDate(
					new Date(objectData.getLong("applicantIdDate")));
		}
		else {

			object.setApplicantIdDate(object.getApplicantIdDate());
		}
		object.setAddress(objectData.getString("address"));
		object.setApplicantName(objectData.getString("applicantName"));
		object.setPostalAddress(objectData.getString("postalAddress"));

		DictItem govAgencyName = DictCollectionUtils.getDictItemByCode(
				DataMGTConstants.GOVERNMENT_AGENCY,
				objectData.getString("govAgencyCode"),
				objectData.getLong("groupId"));

		if (Validator.isNotNull(govAgencyName)) {
			object.setGovAgencyName(govAgencyName.getItemName());
		}

		object.setCityCode(objectData.getString("cityCode"));
		object.setDistrictCode(objectData.getString("districtCode"));
		object.setWardCode(objectData.getString("wardCode"));
		object.setDelegateCityCode(objectData.getString("delegateCityCode"));
		object.setDelegateDistrictCode(
				objectData.getString("delegateDistrictCode"));
		object.setDelegateWardCode(objectData.getString("delegateWardCode"));
		object.setPostalCityCode(objectData.getString("postalCityCode"));
		object.setPostalDistrictCode(
				objectData.getString("postalDistrictCode"));
		object.setPostalWardCode(objectData.getString("postalWardCode"));

		DictItem dictItem = DictCollectionUtils.getDictItemByCode(
				DataMGTConstants.ADMINISTRATIVE_REGION,
				objectData.getString("cityCode"), objectData.getLong("groupId"));

		if (Validator.isNotNull(dictItem)) {
			object.setCityName(dictItem.getItemName());
		}

		dictItem = DictCollectionUtils.getDictItemByCode(
				DataMGTConstants.ADMINISTRATIVE_REGION,
				objectData.getString("districtCode"),
				objectData.getLong("groupId"));

		if (Validator.isNotNull(dictItem)) {
			object.setDistrictName(dictItem.getItemName());
		}

		dictItem = DictCollectionUtils.getDictItemByCode(
				DataMGTConstants.ADMINISTRATIVE_REGION,
				objectData.getString("wardCode"), objectData.getLong("groupId"));

		if (Validator.isNotNull(dictItem)) {
			object.setWardName(dictItem.getItemName());
		}

		dictItem = DictCollectionUtils.getDictItemByCode(
				DataMGTConstants.ADMINISTRATIVE_REGION,
				objectData.getString("delegateCityCode"),
				objectData.getLong("groupId"));

		if (Validator.isNotNull(dictItem)) {
			object.setDelegateCityName(dictItem.getItemName());
		}

		dictItem = DictCollectionUtils.getDictItemByCode(
				DataMGTConstants.ADMINISTRATIVE_REGION,
				objectData.getString("delegateDistrictCode"),
				objectData.getLong("groupId"));

		if (Validator.isNotNull(dictItem)) {
			object.setDelegateDistrictName(dictItem.getItemName());
		}

		dictItem = DictCollectionUtils.getDictItemByCode(
				DataMGTConstants.ADMINISTRATIVE_REGION,
				objectData.getString("delegateWardCode"),
				objectData.getLong("groupId"));

		if (Validator.isNotNull(dictItem)) {
			object.setDelegateWardName(dictItem.getItemName());
		}

		dictItem = DictCollectionUtils.getDictItemByCode(
				DataMGTConstants.ADMINISTRATIVE_REGION,
				objectData.getString("postalCityCode"),
				objectData.getLong("groupId"));

		if (Validator.isNotNull(dictItem)) {
			object.setPostalCityName(dictItem.getItemName());
		}

		dictItem = DictCollectionUtils.getDictItemByCode(
				DataMGTConstants.ADMINISTRATIVE_REGION,
				objectData.getString("postalDistrictCode"),
				objectData.getLong("groupId"));

		if (Validator.isNotNull(dictItem)) {
			object.setPostalDistrictName(dictItem.getItemName());
		}

		dictItem = DictCollectionUtils.getDictItemByCode(
				DataMGTConstants.ADMINISTRATIVE_REGION,
				objectData.getString("postalWardCode"),
				objectData.getLong("groupId"));

		if (Validator.isNotNull(dictItem)) {
			object.setPostalWardName(dictItem.getItemName());
		}

		object.setPostalServiceCode(objectData.getString("postalServiceCode"));
		object.setPostalServiceName(objectData.getString("postalServiceName"));
		object.setDossierTemplateNo(objectData.getString("dossierTemplateNo"));
		object.setDossierTemplateName(
				objectData.getString("dossierTemplateName"));
		object.setDossierStatus(objectData.getString("dossierStatus"));
		object.setDossierStatusText(objectData.getString("dossierStatusText"));
		object.setDossierSubStatus(objectData.getString("dossierSubStatus"));
		object.setDossierSubStatusText(
				objectData.getString("dossierSubStatusText"));

		object.setContactName(objectData.getString("contactName"));
		object.setContactTelNo(objectData.getString("contactTelNo"));
		object.setContactEmail(objectData.getString("contactEmail"));
		object.setDelegateName(objectData.getString("delegateName"));
		object.setDelegateIdNo(objectData.getString("delegateIdNo"));
		object.setDelegateTelNo(objectData.getString("delegateTelNo"));
		object.setDelegateEmail(objectData.getString("delegateEmail"));
		object.setDelegateAddress(objectData.getString("delegateAddress"));
		object.setDossierNote(objectData.getString("dossierNote"));
		object.setSubmissionNote(objectData.getString("submissionNote"));
		object.setApplicantNote(objectData.getString("applicantNote"));
		object.setBriefNote(objectData.getString("briefNote"));
		object.setDossierNo(objectData.getString("dossierNo"));
		object.setSubmitting(objectData.getBoolean("submitting"));
		if (Validator.isNotNull(objectData.getLong("processDate"))) {

			object.setProcessDate(new Date(objectData.getLong("processDate")));
		}
		else {
			object.setProcessDate(object.getProcessDate());
		}
		if (Validator.isNotNull(objectData.getLong("submitDate"))) {

			object.setSubmitDate(new Date(objectData.getLong("submitDate")));
		}
		else {

			object.setSubmitDate(object.getSubmitDate());
		}
		if (Validator.isNotNull(objectData.getLong("receiveDate"))) {

			object.setReceiveDate(new Date(objectData.getLong("receiveDate")));
		}
		else {
			object.setReceiveDate(object.getReceiveDate());
		}
		if (Validator.isNotNull(objectData.getLong("dueDate"))) {

			object.setDueDate(new Date(objectData.getLong("dueDate")));
		}
		else {
			object.setDueDate(null);
		}
		if (Validator.isNotNull(objectData.getLong("extendDate"))) {

			object.setExtendDate(new Date(objectData.getLong("extendDate")));
		}
		else {
			object.setExtendDate(null);
		}
		if (Validator.isNotNull(objectData.getLong("releaseDate"))) {

			object.setReleaseDate(new Date(objectData.getLong("releaseDate")));
		}
		else {
			object.setReleaseDate(null);
		}
		if (Validator.isNotNull(objectData.getLong("finishDate"))) {

			object.setFinishDate(new Date(objectData.getLong("finishDate")));
		}
		else {
			object.setFinishDate(null);
		}
		if (Validator.isNotNull(objectData.getLong("cancellingDate"))) {

			object.setCancellingDate(
					new Date(objectData.getLong("cancellingDate")));
		}
		else {
			object.setCancellingDate(null);
		}
		if (Validator.isNotNull(objectData.getLong("correcttingDate"))) {

			object.setCorrecttingDate(
					new Date(objectData.getLong("correcttingDate")));
		}
		else {
			object.setCorrecttingDate(null);
		}
		// object.setFolderId(objectData.getString("userName")folderId);
		object.setDossierActionId(objectData.getLong("dossierActionId"));
		object.setViaPostal(objectData.getInt("viaPostal"));
		object.setPostalTelNo(objectData.getString("postalTelNo"));
		object.setPassword(objectData.getString("password"));
		object.setNotification(objectData.getBoolean("notification"));
		object.setOnline(objectData.getBoolean("online"));
		object.setOriginal(objectData.getBoolean("original"));
		object.setServerNo(objectData.getString("serverNo"));
		if (Validator.isNotNull(objectData.getLong("endorsementDate"))) {

			object.setApplicantIdDate(
					new Date(objectData.getLong("endorsementDate")));
		}
		object.setLockState(objectData.getString("lockState"));
		object.setOriginality(objectData.getInt("originality"));
		object.setOriginDossierId(objectData.getLong("originDossierId"));
		object.setSampleCount(objectData.getLong("sampleCount"));
		object.setDurationUnit(objectData.getInt("durationUnit"));
		object.setDurationCount(objectData.getDouble("durationCount"));
		object.setDossierName(objectData.getString("dossierName"));
		object.setOriginDossierNo(objectData.getString("originDossierNo"));
		object.setFromViaPostal(objectData.getInt(DossierTerm.FROM_VIA_POSTAL));

		dossierPersistence.update(object);

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier updateDossierSpecial(long dossierId, JSONObject obj)
			throws NoSuchDossierException {

		// _log.debug("Object dossier update: " + obj.toJSONString());
		Dossier dossier = dossierPersistence.findByPrimaryKey(dossierId);
		if (obj.has(DossierTerm.DOSSIER_NOTE)) {
			if (!obj.getString(DossierTerm.DOSSIER_NOTE).equals(
					dossier.getDossierNote())) {
				dossier.setDossierNote(obj.getString(DossierTerm.DOSSIER_NOTE));
			}
		}
		if (obj.has(DossierTerm.DOSSIER_STATUS)) {
			if (!obj.getString(DossierTerm.DOSSIER_STATUS).equals(
					dossier.getDossierStatus()) &&
					Validator.isNotNull(
							obj.getString(DossierTerm.DOSSIER_STATUS))) {
				dossier.setDossierStatus(
						obj.getString(DossierTerm.DOSSIER_STATUS));
			}
		}
		if (obj.has(DossierTerm.RECEIVE_DATE) &&
				Validator.isNotNull(obj.get(DossierTerm.RECEIVE_DATE)) &&
				GetterUtil.getLong(obj.get(DossierTerm.RECEIVE_DATE)) > 0) {
			if (dossier.getReceiveDate() == null || obj.getLong(
					DossierTerm.RECEIVE_DATE) != dossier.getReceiveDate().getTime()) {
				dossier.setReceiveDate(
						new Date(obj.getLong(DossierTerm.RECEIVE_DATE)));
			}
		}
		if (obj.has(DossierTerm.EXTEND_DATE) &&
				Validator.isNotNull(obj.get(DossierTerm.EXTEND_DATE)) &&
				GetterUtil.getLong(obj.get(DossierTerm.EXTEND_DATE)) > 0) {
			if (dossier.getExtendDate() == null || obj.getLong(
					DossierTerm.EXTEND_DATE) != dossier.getExtendDate().getTime()) {
				dossier.setExtendDate(
						new Date(obj.getLong(DossierTerm.EXTEND_DATE)));
			}
		}
		if (obj.has(DossierTerm.DUE_DATE) &&
				Validator.isNotNull(obj.get(DossierTerm.DUE_DATE)) &&
				GetterUtil.getLong(obj.get(DossierTerm.DUE_DATE)) > 0) {
			if (dossier.getDueDate() == null || obj.getLong(
					DossierTerm.DUE_DATE) != dossier.getDueDate().getTime()) {
				dossier.setDueDate(new Date(obj.getLong(DossierTerm.DUE_DATE)));
			}
		}
		if (obj.has(DossierTerm.FINISH_DATE) &&
				Validator.isNotNull(obj.get(DossierTerm.FINISH_DATE)) &&
				GetterUtil.getLong(obj.get(DossierTerm.FINISH_DATE)) > 0) {
			if (dossier.getFinishDate() == null || obj.getLong(
					DossierTerm.FINISH_DATE) != dossier.getFinishDate().getTime()) {
				dossier.setFinishDate(
						new Date(obj.getLong(DossierTerm.FINISH_DATE)));
			}
		}
		if (obj.has(DossierTerm.RELEASE_DATE) &&
				Validator.isNotNull(obj.get(DossierTerm.RELEASE_DATE)) &&
				GetterUtil.getLong(obj.get(DossierTerm.RELEASE_DATE)) > 0) {
			if (dossier.getReleaseDate() == null || obj.getLong(
					DossierTerm.RELEASE_DATE) != dossier.getReleaseDate().getTime()) {
				dossier.setReleaseDate(
						new Date(obj.getLong(DossierTerm.RELEASE_DATE)));
			}
		}

		return dossierPersistence.update(dossier);
	}

	public List<Dossier> getByG_AN(long groupId, String applicantIdNo) {

		return dossierPersistence.findByG_AN(groupId, applicantIdNo);
	}

	public Dossier getByG_AN_SC_GAC_DTNO_ODID(
			long groupId, String applicantIdNo, String serviceCode,
			String govAgencyCode, String dossierTemplateNo, long originDossierId) {

		return dossierPersistence.fetchByG_AN_SC_GAC_DTNO_ODID(
				groupId, applicantIdNo, serviceCode, govAgencyCode,
				dossierTemplateNo, originDossierId);
	}

	public Dossier fetchOnePublicService() {

		return dossierPersistence.fetchByO_First(0, null);
	}

	public List<Dossier> findByNOT_ST_GT_MD(
			String[] statuses, Date d, int start, int end) {

		return dossierPersistence.findByNOT_ST_GT_MD(statuses, d, start, end);
	}

	public List<Dossier> findByGID(long groupId, int start, int end) {

		return dossierPersistence.findByG(groupId, start, end);
	}

	public Dossier updateStatus(
			Dossier dossier, String status, String statusText, String subStatus,
			String subStatusText, String lockState, String stepInstruction,
			ServiceContext context)
			throws PortalException {

		Date now = new Date();

		dossier.setModifiedDate(now);

		dossier.setDossierStatus(status);
		dossier.setDossierStatusText(statusText);
		dossier.setDossierSubStatus(subStatus);
		dossier.setDossierSubStatusText(subStatusText);
		dossier.setLockState(lockState);
		dossier.setDossierNote(stepInstruction);

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
	public Dossier initUpdateDossier(
			long groupId, long id, String applicantName, String applicantIdType,
			String applicantIdNo, String applicantIdDate, String address,
			String cityCode, String cityName, String districtCode,
			String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String contactEmail,
			String dossierTemplateNo, Integer viaPostal, String postalAddress,
			String postalCityCode, String postalCityName,
			String postalDistrictCode, String postalDistrictName, String postalTelNo,
			String applicantNote, boolean isSameAsApplicant, String delegateName,
			String delegateIdNo, String delegateTelNo, String delegateEmail,
			String delegateAddress, String delegateCityCode,
			String delegateDistrictCode, String delegateWardCode, Long sampleCount,
			String dossierName, String briefNote, ServiceContext serviceContext) {

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
					APIDateTimeUtils.convertStringToDate(
							applicantIdDate, APIDateTimeUtils._NORMAL_PARTTERN));
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

		if (Validator.isNotNull(viaPostal)) {
			dossier.setViaPostal(viaPostal);
			if (viaPostal == 1) {
				dossier.setPostalAddress(StringPool.BLANK);
				dossier.setPostalCityCode(StringPool.BLANK);
				dossier.setPostalTelNo(StringPool.BLANK);
				dossier.setPostalDistrictCode(StringPool.BLANK);

			}
			else if (viaPostal == 2) {
				if (Validator.isNotNull(postalAddress))
					dossier.setPostalAddress(postalAddress);
				if (Validator.isNotNull(postalCityCode))
					dossier.setPostalCityCode(postalCityCode);
				if (Validator.isNotNull(postalTelNo))
					dossier.setPostalTelNo(postalTelNo);
				if (Validator.isNotNull(postalCityName))
					dossier.setPostalCityName(postalCityName);
				if (Validator.isNotNull(postalDistrictCode))
					dossier.setPostalDistrictCode(postalDistrictCode);
				if (Validator.isNotNull(postalCityName))
					dossier.setPostalDistrictName(postalDistrictName);

			}
			else {
				dossier.setPostalAddress(StringPool.BLANK);
				dossier.setPostalCityCode(StringPool.BLANK);
				dossier.setPostalTelNo(StringPool.BLANK);
				dossier.setPostalDistrictCode(StringPool.BLANK);
			}
		}
		if (isSameAsApplicant) {
			dossier.setDelegateName(applicantName);
			dossier.setDelegateIdNo(applicantIdNo);
			dossier.setDelegateTelNo(contactTelNo);
			dossier.setDelegateAddress(address);
			dossier.setDelegateEmail(contactEmail);
			if (Validator.isNotNull(cityCode)) {
				dossier.setDelegateCityCode(cityCode);
				dossier.setDelegateCityName(
						getDictItemName(
								dossier.getGroupId(), ADMINISTRATIVE_REGION, cityCode));
			}

			if (Validator.isNotNull(districtCode)) {
				dossier.setDelegateDistrictCode(districtCode);
				dossier.setDelegateDistrictName(
						getDictItemName(
								dossier.getGroupId(), ADMINISTRATIVE_REGION,
								districtCode));
			}

			if (Validator.isNotNull(wardCode)) {
				dossier.setDelegateWardCode(wardCode);
				dossier.setDelegateWardName(
						getDictItemName(
								dossier.getGroupId(), ADMINISTRATIVE_REGION, wardCode));
			}
		}
		else {
			dossier.setDelegateName(delegateName);
			dossier.setDelegateIdNo(delegateIdNo);
			dossier.setDelegateTelNo(delegateTelNo);
			dossier.setDelegateAddress(delegateAddress);
			dossier.setDelegateEmail(delegateEmail);

			if (Validator.isNotNull(delegateCityCode)) {
				dossier.setDelegateCityCode(delegateCityCode);
				dossier.setDelegateCityName(
						getDictItemName(
								dossier.getGroupId(), ADMINISTRATIVE_REGION,
								delegateCityCode));
			}

			if (Validator.isNotNull(delegateDistrictCode)) {
				dossier.setDelegateDistrictCode(delegateDistrictCode);
				dossier.setDelegateDistrictName(
						getDictItemName(
								dossier.getGroupId(), ADMINISTRATIVE_REGION,
								delegateDistrictCode));
			}

			if (Validator.isNotNull(delegateWardCode)) {
				dossier.setDelegateWardCode(delegateWardCode);
				dossier.setDelegateWardName(
						getDictItemName(
								dossier.getGroupId(), ADMINISTRATIVE_REGION,
								delegateWardCode));
			}
		}

		dossier.setApplicantNote(applicantNote);
		if (Validator.isNotNull(dossierName)) {
			dossier.setDossierName(dossierName);
		}
		System.out.println("Dossier name: " + dossierName);
		dossier.setBriefNote(briefNote);
		// Process add status of group dossier
		if (dossier.getOriginality() == 9) {
			dossier.setDossierStatus(DossierTerm.DOSSIER_STATUS_PROCESSING);
		}

		return dossierPersistence.update(dossier);
	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier initUpdateDossierFull(
			long groupId, long id, String applicantName, String applicantIdType,
			String applicantIdNo, String applicantIdDate, String address,
			String cityCode, String cityName, String districtCode,
			String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String contactEmail,
			String dossierTemplateNo, Integer viaPostal, String postalAddress,
			String postalCityCode, String postalCityName,
			String postalDistrictCode, String postalDistrictName, String postalTelNo,
			String applicantNote, boolean isSameAsApplicant, String delegateName,
			String delegateIdNo, String delegateTelNo, String delegateEmail,
			String delegateAddress, String delegateCityCode,
			String delegateDistrictCode, String delegateWardCode, Long sampleCount,
			String dossierName, String briefNote, Integer delegateType,
			String documentNo, Date documentDate, int systemId,
			Integer vnpostalStatus, String vnpostalProfile, Integer fromViaPostal,
			Date dueDate, int durationCount, ServiceContext serviceContext) {

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
					APIDateTimeUtils.convertStringToDate(
							applicantIdDate, APIDateTimeUtils._NORMAL_PARTTERN));
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

		if (Validator.isNotNull(viaPostal)) {
			dossier.setViaPostal(viaPostal);
			if (viaPostal == 1) {
				dossier.setPostalAddress(StringPool.BLANK);
				dossier.setPostalCityCode(StringPool.BLANK);
				dossier.setPostalTelNo(StringPool.BLANK);
				dossier.setPostalDistrictCode(StringPool.BLANK);

			}
			else if (viaPostal == 2) {
				if (Validator.isNotNull(postalAddress))
					dossier.setPostalAddress(postalAddress);
				if (Validator.isNotNull(postalCityCode))
					dossier.setPostalCityCode(postalCityCode);
				if (Validator.isNotNull(postalTelNo))
					dossier.setPostalTelNo(postalTelNo);
				if (Validator.isNotNull(postalCityName))
					dossier.setPostalCityName(postalCityName);
				if (Validator.isNotNull(postalDistrictCode))
					dossier.setPostalDistrictCode(postalDistrictCode);
				if (Validator.isNotNull(postalDistrictName))
					dossier.setPostalDistrictName(postalDistrictName);

			}
			else {
				dossier.setPostalAddress(StringPool.BLANK);
				dossier.setPostalCityCode(StringPool.BLANK);
				dossier.setPostalTelNo(StringPool.BLANK);
				dossier.setPostalDistrictCode(StringPool.BLANK);
			}
		}
		if (isSameAsApplicant) {
			dossier.setDelegateName(applicantName);
			dossier.setDelegateIdNo(applicantIdNo);
			dossier.setDelegateTelNo(contactTelNo);
			dossier.setDelegateAddress(address);
			dossier.setDelegateEmail(contactEmail);
			if (Validator.isNotNull(cityCode)) {
				dossier.setDelegateCityCode(cityCode);
				dossier.setDelegateCityName(
						getDictItemName(
								dossier.getGroupId(), ADMINISTRATIVE_REGION, cityCode));
			}

			if (Validator.isNotNull(districtCode)) {
				dossier.setDelegateDistrictCode(districtCode);
				dossier.setDelegateDistrictName(
						getDictItemName(
								dossier.getGroupId(), ADMINISTRATIVE_REGION,
								districtCode));
			}

			if (Validator.isNotNull(wardCode)) {
				dossier.setDelegateWardCode(wardCode);
				dossier.setDelegateWardName(
						getDictItemName(
								dossier.getGroupId(), ADMINISTRATIVE_REGION, wardCode));
			}
		}
		else {
			dossier.setDelegateName(delegateName);
			dossier.setDelegateIdNo(delegateIdNo);
			dossier.setDelegateTelNo(delegateTelNo);
			dossier.setDelegateAddress(delegateAddress);
			dossier.setDelegateEmail(delegateEmail);

			if (Validator.isNotNull(delegateCityCode)) {
				dossier.setDelegateCityCode(delegateCityCode);
				dossier.setDelegateCityName(
						getDictItemName(
								dossier.getGroupId(), ADMINISTRATIVE_REGION,
								delegateCityCode));
			}

			if (Validator.isNotNull(delegateDistrictCode)) {
				dossier.setDelegateDistrictCode(delegateDistrictCode);
				dossier.setDelegateDistrictName(
						getDictItemName(
								dossier.getGroupId(), ADMINISTRATIVE_REGION,
								delegateDistrictCode));
			}

			if (Validator.isNotNull(delegateWardCode)) {
				dossier.setDelegateWardCode(delegateWardCode);
				dossier.setDelegateWardName(
						getDictItemName(
								dossier.getGroupId(), ADMINISTRATIVE_REGION,
								delegateWardCode));
			}
		}

		dossier.setApplicantNote(applicantNote);
		if (Validator.isNotNull(dossierName)) {
			dossier.setDossierName(dossierName);
		}
		System.out.println("Dossier name: " + dossierName);
		dossier.setBriefNote(briefNote);
		// Process add status of group dossier
		if (dossier.getOriginality() == 9) {
			dossier.setDossierStatus(DossierTerm.DOSSIER_STATUS_PROCESSING);
		}

		// Delegate type
		if (delegateType != null && Validator.isNotNull(delegateType)) {
			dossier.setDelegateType(delegateType);
		}
		if (Validator.isNotNull(documentNo)) {
			dossier.setDocumentNo(documentNo);
		}
		if (documentDate != null && Validator.isNotNull(documentDate)) {
			dossier.setDocumentDate(documentDate);
		}

		if (systemId > 0) {
			dossier.setSystemId(systemId);
		}

		if (vnpostalStatus != null && Validator.isNotNull(vnpostalStatus)) {
			dossier.setVnpostalStatus(vnpostalStatus);
		}
		if (vnpostalProfile != null && Validator.isNotNull(vnpostalProfile)) {
			dossier.setVnpostalProfile(vnpostalProfile);
		}
		if (fromViaPostal != null && Validator.isNotNull(fromViaPostal)) {
			dossier.setFromViaPostal(fromViaPostal);
		}
		if (dueDate != null)
			dossier.setDueDate(dueDate);
		if (durationCount > 0)
			dossier.setDurationCount(durationCount);

		return dossierPersistence.update(dossier);
	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier initUpdateDossierMeta(
			long groupId, long id, String applicantName, String applicantIdType,
			String applicantIdNo, String applicantIdDate, String address,
			String cityCode, String cityName, String districtCode,
			String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String contactEmail,
			String dossierTemplateNo, Integer viaPostal, String postalAddress,
			String postalCityCode, String postalCityName,
			String postalDistrictCode, String postalDistrictName, String postalTelNo,
			String applicantNote, boolean isSameAsApplicant, String delegateName,
			String delegateIdNo, String delegateTelNo, String delegateEmail,
			String delegateAddress, String delegateCityCode,
			String delegateDistrictCode, String delegateWardCode, Long sampleCount,
			String dossierName, String briefNote, Integer delegateType,
			String documentNo, Date documentDate, int systemId,
			Integer vnpostalStatus, String vnpostalProfile, Integer fromViaPostal,
			String metaData, Date dueDate, int durationCount, ServiceContext serviceContext) {

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
					APIDateTimeUtils.convertStringToDate(
							applicantIdDate, APIDateTimeUtils._NORMAL_PARTTERN));
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

		if (Validator.isNotNull(viaPostal)) {
			dossier.setViaPostal(viaPostal);
			if (viaPostal == 1) {
				dossier.setPostalAddress(StringPool.BLANK);
				dossier.setPostalCityCode(StringPool.BLANK);
				dossier.setPostalTelNo(StringPool.BLANK);
				dossier.setPostalDistrictCode(StringPool.BLANK);

			}
			else if (viaPostal == 2) {
				if (Validator.isNotNull(postalAddress))
					dossier.setPostalAddress(postalAddress);
				if (Validator.isNotNull(postalCityCode))
					dossier.setPostalCityCode(postalCityCode);
				if (Validator.isNotNull(postalTelNo))
					dossier.setPostalTelNo(postalTelNo);
				if (Validator.isNotNull(postalCityName))
					dossier.setPostalCityName(postalCityName);
				if (Validator.isNotNull(postalDistrictCode))
					dossier.setPostalDistrictCode(postalDistrictCode);
				if (Validator.isNotNull(postalDistrictName))
					dossier.setPostalDistrictName(postalDistrictName);

			}
			else {
				dossier.setPostalAddress(StringPool.BLANK);
				dossier.setPostalCityCode(StringPool.BLANK);
				dossier.setPostalTelNo(StringPool.BLANK);
				dossier.setPostalDistrictCode(StringPool.BLANK);
			}
		}
		if (isSameAsApplicant) {
			dossier.setDelegateName(applicantName);
			dossier.setDelegateIdNo(applicantIdNo);
			dossier.setDelegateTelNo(contactTelNo);
			dossier.setDelegateAddress(address);
			dossier.setDelegateEmail(contactEmail);
			if (Validator.isNotNull(cityCode)) {
				dossier.setDelegateCityCode(cityCode);
				dossier.setDelegateCityName(
						getDictItemName(
								dossier.getGroupId(), ADMINISTRATIVE_REGION, cityCode));
			}

			if (Validator.isNotNull(districtCode)) {
				dossier.setDelegateDistrictCode(districtCode);
				dossier.setDelegateDistrictName(
						getDictItemName(
								dossier.getGroupId(), ADMINISTRATIVE_REGION,
								districtCode));
			}

			if (Validator.isNotNull(wardCode)) {
				dossier.setDelegateWardCode(wardCode);
				dossier.setDelegateWardName(
						getDictItemName(
								dossier.getGroupId(), ADMINISTRATIVE_REGION, wardCode));
			}
		}
		else {
			dossier.setDelegateName(delegateName);
			dossier.setDelegateIdNo(delegateIdNo);
			dossier.setDelegateTelNo(delegateTelNo);
			dossier.setDelegateAddress(delegateAddress);
			dossier.setDelegateEmail(delegateEmail);

			if (Validator.isNotNull(delegateCityCode)) {
				dossier.setDelegateCityCode(delegateCityCode);
				dossier.setDelegateCityName(
						getDictItemName(
								dossier.getGroupId(), ADMINISTRATIVE_REGION,
								delegateCityCode));
			}

			if (Validator.isNotNull(delegateDistrictCode)) {
				dossier.setDelegateDistrictCode(delegateDistrictCode);
				dossier.setDelegateDistrictName(
						getDictItemName(
								dossier.getGroupId(), ADMINISTRATIVE_REGION,
								delegateDistrictCode));
			}

			if (Validator.isNotNull(delegateWardCode)) {
				dossier.setDelegateWardCode(delegateWardCode);
				dossier.setDelegateWardName(
						getDictItemName(
								dossier.getGroupId(), ADMINISTRATIVE_REGION,
								delegateWardCode));
			}
		}

		dossier.setApplicantNote(applicantNote);
		if (Validator.isNotNull(dossierName)) {
			dossier.setDossierName(dossierName);
		}
		System.out.println("Dossier name: " + dossierName);
		dossier.setBriefNote(briefNote);
		// Process add status of group dossier
		if (dossier.getOriginality() == 9) {
			dossier.setDossierStatus(DossierTerm.DOSSIER_STATUS_PROCESSING);
		}

		// Delegate type
		if (delegateType != null && Validator.isNotNull(delegateType)) {
			dossier.setDelegateType(delegateType);
		}
		if (Validator.isNotNull(documentNo)) {
			dossier.setDocumentNo(documentNo);
		}
		if (documentDate != null && Validator.isNotNull(documentDate)) {
			dossier.setDocumentDate(documentDate);
		}

		if (systemId > 0) {
			dossier.setSystemId(systemId);
		}

		if (vnpostalStatus != null && Validator.isNotNull(vnpostalStatus)) {
			dossier.setVnpostalStatus(vnpostalStatus);
		}
		if (vnpostalProfile != null && Validator.isNotNull(vnpostalProfile)) {
			dossier.setVnpostalProfile(vnpostalProfile);
		}
		if (fromViaPostal != null && Validator.isNotNull(fromViaPostal)) {
			dossier.setFromViaPostal(fromViaPostal);
		}
		if (Validator.isNotNull(metaData)) {
			dossier.setMetaData(metaData);
		}
		if (dueDate != null)
			dossier.setDueDate(dueDate);
		if (durationCount > 0)
			dossier.setDurationCount(durationCount);

		return dossierPersistence.update(dossier);
	}

	public List<Dossier> getByGroupAndOriginDossierNo(
			long groupId, String originDossierNo) {

		return dossierPersistence.findByGID_ORI_NO(groupId, originDossierNo);
	}

	public int countByGroupAndOriginDossierNo(
			long groupId, String originDossierNo) {

		return dossierPersistence.countByGID_ORI_NO(groupId, originDossierNo);
	}

	public int countByOriginDossierNo(String originDossierNo) {

		return dossierPersistence.countByORIGIN_NO(originDossierNo);
	}

	public List<Dossier> getByU_G_GAC_SC_DTNO_DS_O(
			long userId, long groupId, String govAgencyCode, String serviceCode,
			String dossierTemplateNo, String dossierStatus, int originality) {

		return dossierPersistence.findByU_G_GAC_SC_DTNO_DS_O(
				userId, groupId, govAgencyCode, serviceCode, dossierTemplateNo,
				dossierStatus, originality);
	}

	public int countByG_NOTS_O_SC(
			long groupId, String[] dossierStatuses, int originality,
			String serviceCode) {

		return dossierPersistence.countByG_NOTS_O_SC(
				groupId, dossierStatuses, originality, serviceCode);
	}

	public int countByG_NOTS_O_DTN(
			long groupId, String[] dossierStatuses, int originality,
			String dossierTemplateNo) {

		return dossierPersistence.countByG_NOTS_O_DTN(
				groupId, dossierStatuses, originality, dossierTemplateNo);
	}

	public int countByG_NOTS_O_PN(
			long groupId, String[] dossierStatuses, int originality,
			String processNo) {

		return dossierPersistence.countByG_NOTS_O_PN(
				groupId, dossierStatuses, originality, processNo);
	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier publishImportDossier(
			long groupId, long dossierId, String referenceUid, int counter,
			String serviceCode, String serviceName, String govAgencyCode,
			String govAgencyName, String applicantName, String applicantIdType,
			String applicantIdNo, Date applicantIdDate, String address,
			String contactName, String contactTelNo, String contactEmail,
			Boolean online, int originality, String dossierNo, String dossierStatus,
			String dossierStatusText, long dossierActionId, Double durationCount,
			Integer durationUnit, Integer sampleCount, Date createDate,
			Date modifiedDate, Date submitDate, Date receiveDate, Date dueDate,
			Date releaseDate, Date finishDate, String dossierTemplateNo,
			String dossierTemplateName, Integer vnpostalStatus, String vnpostalProfile,
			Integer fromViaPostal, ServiceContext serviceContext) {

		long userId = serviceContext.getUserId();

		User auditUser = userPersistence.fetchByPrimaryKey(userId);

		Dossier dossier = null;
		if (dossierId > 0) {
			dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
		}
		else {
			dossier = getByRef(groupId, referenceUid);
		}
		if (dossier == null) {
			dossierId = counterLocalService.increment(Dossier.class.getName());

			dossier = dossierPersistence.create(dossierId);

			dossier.setCreateDate(createDate);
			dossier.setModifiedDate(modifiedDate);
			dossier.setSubmitDate(submitDate);
			dossier.setReceiveDate(receiveDate);
			dossier.setDueDate(dueDate);
			dossier.setReleaseDate(releaseDate);
			dossier.setFinishDate(finishDate);

			dossier.setCompanyId(serviceContext.getCompanyId());
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
			dossier.setOnline(online);
			dossier.setAddress(address);
			dossier.setContactName(contactName);
			dossier.setContactEmail(contactEmail);
			dossier.setContactTelNo(contactTelNo);

			dossier.setViaPostal(0);
			dossier.setOriginality(originality);
			dossier.setDossierNo(dossierNo);
			dossier.setDossierStatus(dossierStatus);
			dossier.setDossierStatusText(dossierStatusText);
			if ("releasing".equals(dossierStatus)) {
				dossier.setDossierSubStatus("releasing_0");
				dossier.setDossierSubStatusText("Chờ trả kết quả tại một cửa");
			}
			dossier.setDossierActionId(dossierActionId);
			dossier.setCounter(counter);

			dossier.setDelegateName(applicantName);
			dossier.setDelegateAddress(address);
			dossier.setDelegateIdNo(applicantIdNo);
			dossier.setDelegateTelNo(contactTelNo);
			dossier.setDelegateEmail(contactEmail);
			dossier.setDurationCount(durationCount);
			dossier.setDurationUnit(durationUnit);
			dossier.setSampleCount(sampleCount);
			dossier.setDossierName(serviceName);
			dossier.setVnpostalStatus(vnpostalStatus);
			dossier.setVnpostalProfile(vnpostalProfile);
			dossier.setFromViaPostal(fromViaPostal);

			dossier = dossierPersistence.update(dossier);
		}
		else {
			dossier.setModifiedDate(modifiedDate);
			dossier.setSubmitDate(submitDate);
			dossier.setReceiveDate(receiveDate);
			dossier.setDueDate(dueDate);
			dossier.setReleaseDate(releaseDate);
			dossier.setFinishDate(finishDate);

			if (Validator.isNotNull(address))
				dossier.setAddress(address);
			if (Validator.isNotNull(contactName))
				dossier.setContactName(contactName);
			if (Validator.isNotNull(contactEmail))
				dossier.setContactEmail(contactEmail);
			if (Validator.isNotNull(contactTelNo))
				dossier.setContactTelNo(contactTelNo);
			if (Validator.isNotNull(dossierTemplateNo))
				dossier.setDossierTemplateNo(dossierTemplateNo);
			if (Validator.isNotNull(dossierTemplateName))
				dossier.setDossierTemplateName(dossierTemplateName);

			dossier.setViaPostal(0);
			dossier.setOriginality(originality);
			dossier.setDossierNo(dossierNo);
			dossier.setDossierStatus(dossierStatus);
			dossier.setDossierStatusText(dossierStatusText);
			if ("releasing".equals(dossierStatus)) {
				dossier.setDossierSubStatus("releasing_0");
				dossier.setDossierSubStatusText("Chờ trả kết quả tại một cửa");
			}
			dossier.setDossierActionId(dossierActionId);
			dossier.setCounter(counter);

			dossier.setDelegateName(applicantName);
			dossier.setDelegateAddress(address);
			dossier.setDelegateIdNo(applicantIdNo);
			dossier.setDelegateTelNo(contactTelNo);
			dossier.setDelegateEmail(contactEmail);
			dossier.setDurationCount(durationCount);
			dossier.setDurationUnit(durationUnit);
			dossier.setSampleCount(sampleCount);
			dossier.setDossierName(serviceName);
			if (Validator.isNotNull(vnpostalStatus)) {
				dossier.setVnpostalStatus(vnpostalStatus);
			}
			if (Validator.isNotNull(vnpostalProfile)) {
				dossier.setVnpostalProfile(vnpostalProfile);
			}
			if (Validator.isNotNull(fromViaPostal)) {
				dossier.setFromViaPostal(fromViaPostal);
			}

			dossier = dossierPersistence.update(dossier);
		}

		return dossier;
	}

	public List<Dossier> getByF_GID_AN_DS(
			long groupId, String applicantIdNo, String dossierStatus) {

		return dossierPersistence.findByF_GID_AN_DS(
				groupId, applicantIdNo, dossierStatus);
	}

	public List<Dossier> getByGID_GC_SC_DTN_DS_APP_ORI(
			long groupId, String govAgencyCode, String serviceCode,
			String dossierTemplateNo, String[] statusArr, String applicantIdType,
			int originality) {

		try {
			return dossierPersistence.findByGID_GC_SC_DTN_DS_APP_ORI(
					groupId, govAgencyCode, serviceCode, dossierTemplateNo,
					statusArr, applicantIdType, originality);
		}
		catch (Exception e) {
			_log.debug(e);
		}
		return null;
	}

	public List<Dossier> findByG_GDID(long groupId, String groupDossierId) {

		return dossierPersistence.findByG_GDID(groupId, StringPool.PERCENT + groupDossierId + StringPool.PERCENT);
	}

	public Dossier fetchByDO_NO(String dossierNo) {

		return dossierPersistence.fetchByDO_NO(dossierNo);
	}
	public Dossier fetchByDO_NO_GROUP(String dossierNo, long groupId) {

		return dossierPersistence.fetchByDO_NO_GROUP(dossierNo, groupId);
	}

	public List<Dossier> findByG_UID_DS(long groupId, long userId, String dossierStatus) {
		return dossierPersistence.findByG_UID_DS(groupId, userId, dossierStatus);
	}

	public int countByG_UID_DS(long groupId, long userId, String dossierStatus) {
		return dossierPersistence.countByG_UID_DS(groupId, userId, dossierStatus);
	}

	public Dossier getByCpsRef(long groupId, String refId) {
		Dossier dossier = dossierPersistence.fetchByG_REF(groupId, refId);
		if (dossier == null) {
			if (refId.contains(DossierTerm.PREFIX_UUID)) {
				dossier = dossierPersistence.fetchByG_REF(groupId, refId.substring(DossierTerm.PREFIX_UUID.length()));
			}
			else {
				dossier = dossierPersistence.fetchByG_REF(groupId, DossierTerm.PREFIX_UUID + refId);
			}
		}
		return dossier;
	}

	@Indexable(type = IndexableType.REINDEX)
	public Dossier eparPublishDossier(
			long groupId, long dossierId, String referenceUid, int counter,
			String serviceCode, String serviceName, String govAgencyCode,
			String govAgencyName, String applicantName, String applicantIdType,
			String applicantIdNo, Date applicantIdDate, String address,
			String cityCode, String cityName, String districtCode,
			String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String contactEmail,
			String dossierTemplateNo, String password, int viaPostal,
			String postalAddress, String postalCityCode, String postalCityName,
			String postalDistrictCode, String postalDistrictName,
			String postalTelNo, boolean online, boolean notification,
			String applicantNote, int originality, Date createDate,
			Date modifiedDate, Date submitDate, Date receiveDate, Date dueDate,
			Date releaseDate, Date finishDate, Date cancellingDate,
			Date correctingDate, Date endorsementDate, Date extendDate,
			Date processDate, String dossierNo, String dossierStatus,
			String dossierStatusText, String dossierSubStatus,
			String dossierSubStatusText, long dossierActionId,
			String submissionNote, String lockState, String delegateName,
			String delegateIdNo, String delegateTelNo, String delegateEmail,
			String delegateAddress, String delegateCityCode,
			String delegateCityName, String delegateDistrictCode,
			String delegateDistrictName, String delegateWardCode,
			String delegateWardName, double durationCount, int durationUnit,
			String dossierName, String processNo, String metaData,
			ServiceContext context)
			throws PortalException {

		Dossier dossier = null;
		dossier = getByRef(groupId, referenceUid);

		if (dossier != null) {
			dossier.setModifiedDate(modifiedDate);
			if (submitDate != null)
				dossier.setSubmitDate(submitDate);
			if (receiveDate != null)
				dossier.setReceiveDate(receiveDate);
			if (dueDate != null)
				dossier.setDueDate(dueDate);
			if (releaseDate != null)
				dossier.setReleaseDate(releaseDate);
			if (finishDate != null)
				dossier.setFinishDate(finishDate);
			if (cancellingDate != null)
				dossier.setCancellingDate(cancellingDate);
			if (correctingDate != null)
				dossier.setCorrecttingDate(correctingDate);
			if (endorsementDate != null)
				dossier.setEndorsementDate(endorsementDate);
			if (extendDate != null)
				dossier.setExtendDate(extendDate);
			if (processDate != null)
				dossier.setProcessDate(processDate);

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

			if (Validator.isNotNull(dossierNo))
				dossier.setDossierNo(dossierNo);
			if (Validator.isNotNull(dossierStatus))
				dossier.setDossierStatus(dossierStatus);
			if (Validator.isNotNull(dossierStatusText))
				dossier.setDossierStatusText(dossierStatusText);
			if (Validator.isNotNull(dossierSubStatus))
				dossier.setDossierSubStatus(dossierSubStatus);
			if (Validator.isNotNull(dossierSubStatusText))
				dossier.setDossierSubStatusText(dossierSubStatusText);
			if (Validator.isNotNull(dossierActionId))
				dossier.setDossierActionId(dossierActionId);
			if (Validator.isNotNull(submissionNote))
				dossier.setSubmissionNote(submissionNote);
			if (Validator.isNotNull(lockState))
				dossier.setLockState(lockState);
			if (Validator.isNotNull(delegateName))
				dossier.setDelegateName(delegateName);
			if (Validator.isNotNull(delegateIdNo))
				dossier.setDelegateIdNo(delegateIdNo);
			if (Validator.isNotNull(delegateTelNo))
				dossier.setDelegateTelNo(delegateTelNo);
			if (Validator.isNotNull(delegateEmail))
				dossier.setDelegateEmail(delegateEmail);
			if (Validator.isNotNull(delegateAddress))
				dossier.setDelegateAddress(delegateAddress);
			if (Validator.isNotNull(delegateCityCode))
				dossier.setDelegateCityCode(delegateCityCode);
			if (Validator.isNotNull(delegateCityName))
				dossier.setDelegateCityName(delegateCityName);
			if (Validator.isNotNull(delegateDistrictCode))
				dossier.setDelegateDistrictCode(delegateDistrictCode);
			if (Validator.isNotNull(delegateDistrictName))
				dossier.setDelegateDistrictName(delegateDistrictName);
			if (Validator.isNotNull(delegateWardCode))
				dossier.setDelegateWardCode(delegateWardCode);
			if (Validator.isNotNull(delegateWardName))
				dossier.setDelegateWardName(delegateWardName);
//			if (Validator.isNotNull(durationCount))
//				dossier.setDurationCount(durationCount);
//			if (Validator.isNotNull(durationUnit))
//				dossier.setDurationUnit(durationUnit);
			if (Validator.isNotNull(dossierName))
				dossier.setDossierName(dossierName);
			if (Validator.isNotNull(processNo))
				dossier.setProcessNo(processNo);
			if (Validator.isNotNull(metaData))
				dossier.setProcessNo(metaData);

			dossier.setViaPostal(viaPostal);
			if (viaPostal == 1) {
				dossier.setPostalAddress(StringPool.BLANK);
				dossier.setPostalCityCode(StringPool.BLANK);
				dossier.setPostalTelNo(StringPool.BLANK);
				dossier.setPostalDistrictCode(StringPool.BLANK);

			}
			else if (viaPostal == 2) {
				if (Validator.isNotNull(postalAddress))
					dossier.setPostalAddress(postalAddress);
				if (Validator.isNotNull(postalCityCode))
					dossier.setPostalCityCode(postalCityCode);
				if (Validator.isNotNull(postalTelNo))
					dossier.setPostalTelNo(postalTelNo);
				if (Validator.isNotNull(postalCityName))
					dossier.setPostalCityName(postalCityName);
				if (Validator.isNotNull(postalDistrictCode))
					dossier.setPostalDistrictCode(postalDistrictCode);
				if (Validator.isNotNull(postalDistrictName))
					dossier.setPostalDistrictName(postalDistrictName);

			}
			else {
				dossier.setPostalAddress(StringPool.BLANK);
				dossier.setPostalCityCode(StringPool.BLANK);
				dossier.setPostalTelNo(StringPool.BLANK);
				dossier.setPostalDistrictCode(StringPool.BLANK);
			}

			// if (Validator.isNotNull(applicantNote))
			dossier.setApplicantNote(applicantNote);

			dossier = dossierPersistence.update(dossier);

		}

		return dossier;
	}
	public List<Dossier> findDossierByDay(String date)
	{
		return  dossierFinder.findDossierByDay(date);
	}

	public List<ServiceInfo> findByGID_PUB_SID(long groupId, boolean public_, long[] serviceInfoArr) {
		return serviceInfoPersistence.findByGI_PUB_SID(groupId, public_, serviceInfoArr);
	}
	public Dossier findDossierByDeclarationCode(String code, long groupId) {
		return dossierFinder.findDossierByDeclarationCode(code, groupId);
	}
	public java.util.List<Object[]> getListVotingByDossier(long groupId, List<String> listDossier) {
		return dossierFinder.getListVotingByDossier(groupId, listDossier);
	}
	public Dossier fetchByDO_POST_SEND_GROUP(String postpostalCodeSend, long groupId) {
		return dossierPersistence.fetchByDO_POST_SEND_GROUP(postpostalCodeSend, groupId);
	}

	@Override
	public List<Dossier> fetchByD_OR_D(long[] dossierId) {
		return dossierPersistence.findByD_OR_D(dossierId);
	}

	public Dossier fetchByDO_POST_RECEIVED_GROUP(String postalCodeReceived, long groupId) {
		return dossierPersistence.fetchByDO_POST_RECEIVED_GROUP(postalCodeReceived, groupId);
	}
	
	public List<Dossier> fetchByORIGIN_NO(String originDossierNo) {
		return dossierPersistence.findByORIGIN_NO(originDossierNo, 0, 10);
	}
	
	public List<Dossier> fetchByNEW_DO_NO(String dossierNo) {
		return dossierPersistence.findByNEW_DO_NO(dossierNo, 0, 10);
	}
}