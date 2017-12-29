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

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.dossiermgt.action.RegistrationFormActions;
import org.opencps.dossiermgt.action.impl.RegistrationFormActionsImpl;
import org.opencps.dossiermgt.constants.RegistrationTerm;
import org.opencps.dossiermgt.model.Registration;
import org.opencps.dossiermgt.service.base.RegistrationLocalServiceBaseImpl;
import org.opencps.usermgt.service.util.UserMgtUtils;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.BooleanQueryFactoryUtil;
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
import com.liferay.portal.kernel.search.generic.MultiMatchQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the registration local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.RegistrationLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see RegistrationLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.RegistrationLocalServiceUtil
 */
@ProviderType
public class RegistrationLocalServiceImpl extends RegistrationLocalServiceBaseImpl {

	public static final String CLASS_NAME = Registration.class.getName();

	@Indexable(type = IndexableType.REINDEX)
	public Registration insert(long groupId, String applicantName, String applicantIdType, String applicantIdNo,
			String applicantIdDate, String address, String cityCode, String cityName, String districtCode,
			String districtName, String wardCode, String wardName, String contactName, String contactTelNo,
			String contactEmail, String govAgencyCode, String govAgencyName, int registrationState,
			String registrationClass, ServiceContext serviceContext) throws PortalException, SystemException {
		Date now = new Date();
		long userId = serviceContext.getUserId();
		User userAction = userLocalService.getUser(userId);

		long registrationId = counterLocalService.increment(Registration.class.getName());

		Registration model = registrationPersistence.create(registrationId);

		model.setGroupId(groupId);
		model.setCreateDate(now);
		model.setModifiedDate(now);
		model.setUserId(userAction.getUserId());

		model.setApplicantName(applicantName);
		model.setApplicantIdType(applicantIdType);
		model.setApplicantIdNo(applicantIdNo);
		if (Validator.isNotNull(applicantIdDate)) {
            try {
                Date idDate = UserMgtUtils.convertDate(applicantIdDate);
                
                model.setApplicantIdDate(idDate);
            } catch (Exception e) {
                _log.error(e);
            }
        }
		model.setAddress(address);
		model.setCityCode(cityCode);
		model.setCityName(cityName);
		model.setDistrictCode(districtCode);
		model.setDistrictName(districtName);
		model.setWardCode(wardCode);
		model.setWardName(wardName);
		model.setContactName(contactName);
		model.setContactTelNo(contactTelNo);
		model.setContactEmail(contactEmail);
		model.setGovAgencyCode(govAgencyCode);
		model.setGovAgencyName(govAgencyName);
		model.setRegistrationClass(registrationClass);
		model.setRegistrationState(registrationState);
		model.setSubmitting(false);

		RegistrationFormActions actionForm = new RegistrationFormActionsImpl();

		actionForm.addRegistrationFormbaseonRegTemplate(groupId, registrationId, govAgencyCode, serviceContext);

		return registrationPersistence.update(model);
	}

	@Indexable(type = IndexableType.REINDEX)
	public Registration updateRegistration(long groupId, long registrationId, String applicantName,
			String applicantIdType, String applicantIdNo, String applicantIdDate, String address, String cityCode,
			String cityName, String districtCode, String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String contactEmail, String govAgencyCode, String govAgencyName,
			int registrationState, String registrationClass, ServiceContext serviceContext)
			throws PortalException, SystemException {

		Date now = new Date();

		Registration model = registrationPersistence.fetchByPrimaryKey(registrationId);

		model.setModifiedDate(now);
		model.setSubmitting(true);
		
		if (Validator.isNotNull(applicantIdDate)) {
			try {
				Date idDate = UserMgtUtils.convertDate(applicantIdDate);
				
				model.setApplicantIdDate(idDate);
			} catch (Exception e) {
				_log.error(e);
			}
		}

		if (Validator.isNotNull(applicantName)) {
			model.setApplicantName(applicantName);
		}
		if (Validator.isNotNull(applicantIdType)) {
			model.setApplicantIdType(applicantIdType);
		}
		if (Validator.isNotNull(applicantIdNo)) {
			model.setApplicantIdNo(applicantIdNo);
		}
		if (Validator.isNotNull(address)) {
			model.setAddress(address);
		}
		if (Validator.isNotNull(cityCode)) {
			model.setCityCode(cityCode);
		}
		if (Validator.isNotNull(cityName)) {
			model.setCityName(cityName);
		}
		if (Validator.isNotNull(districtCode)) {
			model.setDistrictCode(districtCode);
		}
		if (Validator.isNotNull(districtName)) {
			model.setDistrictName(districtName);
		}
		if (Validator.isNotNull(wardCode)) {
			model.setWardCode(wardCode);
		}
		if (Validator.isNotNull(wardName)) {
			model.setWardName(wardName);
		}
		if (Validator.isNotNull(contactName)) {
			model.setContactName(contactName);
		}
		if (Validator.isNotNull(contactTelNo)) {
			model.setContactTelNo(contactTelNo);
		}
		if (Validator.isNotNull(contactEmail)) {
			model.setContactEmail(contactEmail);
		}
		if (Validator.isNotNull(govAgencyCode)) {
			model.setGovAgencyCode(govAgencyCode);
		}
		if (Validator.isNotNull(govAgencyName)) {
			model.setGovAgencyName(govAgencyName);
		}
		if (Validator.isNotNull(registrationClass)) {
			model.setRegistrationClass(registrationClass);
		}
		if (Validator.isNotNull(registrationState)) {
			model.setRegistrationState(registrationState);
		}

		return registrationPersistence.update(model);
	}

	public int getfileEntryId(String formdata, String formScript, String formReport) {

		int fileEntryId = 0;

		return fileEntryId;
	}

	public Hits searchLucene(long userId, LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {
		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);

		Indexer<Registration> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Registration.class);

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

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		// Extra fields
		String registrationState = GetterUtil.getString(params.get(RegistrationTerm.REGISTRATIONSTATE));
		String govAgencyCode = GetterUtil.getString(params.get(RegistrationTerm.GOV_AGENCY_CODE));
		String owner = GetterUtil.getString(params.get(RegistrationTerm.OWNER));
		String registrationClass = GetterUtil.getString(params.get(RegistrationTerm.REGISTRATION_CLASS));
		String submitting = GetterUtil.getString(params.get(RegistrationTerm.SUBMITTING));

		if (Validator.isNotNull(registrationState) && !registrationState.isEmpty()) {
			MultiMatchQuery query = new MultiMatchQuery(registrationState);

			query.addFields(RegistrationTerm.REGISTRATIONSTATE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(govAgencyCode) && !govAgencyCode.isEmpty()) {
			MultiMatchQuery query = new MultiMatchQuery(govAgencyCode);

			query.addFields(RegistrationTerm.GOV_AGENCY_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(owner) && !owner.isEmpty()) {
			MultiMatchQuery query = new MultiMatchQuery(String.valueOf(userId));

			query.addFields(RegistrationTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(registrationClass) && !registrationClass.isEmpty()) {
			MultiMatchQuery query = new MultiMatchQuery(registrationClass);

			query.addFields(RegistrationTerm.REGISTRATION_CLASS);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(submitting) && !submitting.isEmpty()) {
			MultiMatchQuery query = new MultiMatchQuery(submitting);

			query.addFields(RegistrationTerm.SUBMITTING);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);
	}

	public long countLucense(long userId, LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {
		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);

		Indexer<Registration> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Registration.class);

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

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		// Extra fields
		String registrationState = GetterUtil.getString(params.get(RegistrationTerm.REGISTRATIONSTATE));
		String govAgencyCode = GetterUtil.getString(params.get(RegistrationTerm.GOV_AGENCY_CODE));
		String owner = GetterUtil.getString(params.get(RegistrationTerm.OWNER));
		String registrationClass = GetterUtil.getString(params.get(RegistrationTerm.REGISTRATION_CLASS));
		String submitting = GetterUtil.getString(params.get(RegistrationTerm.SUBMITTING));

		if (Validator.isNotNull(registrationState) && !registrationState.isEmpty()) {
			MultiMatchQuery query = new MultiMatchQuery(registrationState);

			query.addFields(RegistrationTerm.REGISTRATIONSTATE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(govAgencyCode) && !govAgencyCode.isEmpty()) {
			MultiMatchQuery query = new MultiMatchQuery(govAgencyCode);

			query.addFields(RegistrationTerm.GOV_AGENCY_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(owner) && !owner.isEmpty()) {
			MultiMatchQuery query = new MultiMatchQuery(String.valueOf(userId));

			query.addFields(RegistrationTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(registrationClass) && !registrationClass.isEmpty()) {
			MultiMatchQuery query = new MultiMatchQuery(registrationClass);

			query.addFields(RegistrationTerm.REGISTRATION_CLASS);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(submitting) && !submitting.isEmpty()) {
			MultiMatchQuery query = new MultiMatchQuery(submitting);

			query.addFields(RegistrationTerm.SUBMITTING);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);
	}

	// binhth
	public List<Registration> getdByF_submitting(long groupId, boolean submitting) {
		return registrationPersistence.findByF_submitting(groupId, submitting);
	}

	@Indexable(type = IndexableType.REINDEX)
	public Registration registrationSync(long groupId, String uuid, String applicantName, String applicantIdType,
			String applicantIdNo, String applicantIdDate, String address, String cityCode, String cityName,
			String districtCode, String districtName, String wardCode, String wardName, String contactName,
			String contactTelNo, String contactEmail, String govAgencyCode, String govAgencyName, int registrationState,
			String registrationClass, ServiceContext serviceContext) throws PortalException, SystemException {

		Date now = new Date();
		long userId = serviceContext.getUserId();
		User userAction = userLocalService.getUser(userId);

		Registration registration = registrationPersistence.fetchByUUID_G(uuid, groupId);

		if (Validator.isNotNull(registration)) {
			registration.setModifiedDate(now);

			registration.setApplicantName(applicantName);
			registration.setApplicantIdType(applicantIdType);
			registration.setApplicantIdNo(applicantIdNo);
			if (Validator.isNotNull(applicantIdDate)) {
	            try {
	                Date idDate = UserMgtUtils.convertDate(applicantIdDate);
	                
	                registration.setApplicantIdDate(idDate);
	            } catch (Exception e) {
	                _log.error(e);
	            }
	        }
			registration.setAddress(address);
			registration.setCityCode(cityCode);
			registration.setCityName(cityName);
			registration.setDistrictCode(districtCode);
			registration.setDistrictName(districtName);
			registration.setWardCode(wardCode);
			registration.setWardName(wardName);
			registration.setContactName(contactName);
			registration.setContactTelNo(contactTelNo);
			registration.setContactEmail(contactEmail);
			registration.setGovAgencyCode(govAgencyCode);
			registration.setGovAgencyName(govAgencyName);
			registration.setRegistrationClass(registrationClass);
			registration.setRegistrationState(registrationState);

			registration = registrationPersistence.update(registration);
		} else {

			long registrationId = counterLocalService.increment(Registration.class.getName());

			registration = registrationPersistence.create(registrationId);

			registration.setGroupId(groupId);
			registration.setCreateDate(now);
			registration.setModifiedDate(now);
			registration.setUserId(userAction.getUserId());

			registration.setApplicantName(applicantName);
			registration.setApplicantIdType(applicantIdType);
			registration.setApplicantIdNo(applicantIdNo);
			if (Validator.isNotNull(applicantIdDate)) {
                try {
                    Date idDate = UserMgtUtils.convertDate(applicantIdDate);
                    
                    registration.setApplicantIdDate(idDate);
                } catch (Exception e) {
                    _log.error(e);
                }
            }
			registration.setAddress(address);
			registration.setCityCode(cityCode);
			registration.setCityName(cityName);
			registration.setDistrictCode(districtCode);
			registration.setDistrictName(districtName);
			registration.setWardCode(wardCode);
			registration.setWardName(wardName);
			registration.setContactName(contactName);
			registration.setContactTelNo(contactTelNo);
			registration.setContactEmail(contactEmail);
			registration.setGovAgencyCode(govAgencyCode);
			registration.setGovAgencyName(govAgencyName);
			registration.setRegistrationClass(registrationClass);
			registration.setRegistrationState(registrationState);

			registration.setUuid(uuid);

			registration = registrationPersistence.update(registration);

		}

		return registration;
	}

	public List<Registration> getRegistrationByGID_UID(long groupId, long userId) {
		return registrationPersistence.findByGID_UID(groupId, userId);
	}

	public Registration getRegistrationByGID_UID_Last(long groupId, long userId) {
		return registrationPersistence.fetchByGID_UID_Last(groupId, userId, null);
	}

	public Registration getRegistrationByG_REGID(long groupId, long registrationId) {
		return registrationPersistence.fetchByG_REGID(groupId, registrationId);
	}

	@Indexable(type = IndexableType.REINDEX)
	public Registration updateSubmitting(long registrationId, boolean submitting) throws PortalException {
		Registration model = registrationPersistence.findByPrimaryKey(registrationId);
		model.setSubmitting(submitting);
		model.setModifiedDate(new Date());
		return registrationPersistence.update(model);
	}

	private Log _log = LogFactoryUtil.getLog(RegistrationLocalServiceImpl.class);
}