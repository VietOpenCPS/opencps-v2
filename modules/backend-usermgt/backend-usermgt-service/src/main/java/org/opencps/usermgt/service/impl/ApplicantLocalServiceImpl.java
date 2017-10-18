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

package org.opencps.usermgt.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.usermgt.constants.ApplicantTerm;
import org.opencps.usermgt.exception.DuplicateApplicantIdException;
import org.opencps.usermgt.exception.DuplicateContactEmailException;
import org.opencps.usermgt.exception.DuplicateContactTelNoException;
import org.opencps.usermgt.exception.NoApplicantIdDateException;
import org.opencps.usermgt.exception.NoApplicantIdNoException;
import org.opencps.usermgt.exception.NoApplicantIdTypeException;
import org.opencps.usermgt.exception.NoApplicantNameException;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.service.base.ApplicantLocalServiceBaseImpl;
import org.opencps.usermgt.service.util.ServiceProps;
import org.opencps.usermgt.service.util.UserMgtUtils;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Role;
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
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PwdGenerator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the applicant local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.usermgt.service.ApplicantLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author khoavu
 * @see ApplicantLocalServiceBaseImpl
 * @see org.opencps.usermgt.service.ApplicantLocalServiceUtil
 */
@ProviderType
public class ApplicantLocalServiceImpl extends ApplicantLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.usermgt.service.ApplicantLocalServiceUtil} to access the
	 * applicant local service.
	 */

	public Applicant fetchByMappingID(long mappingID) {
		return applicantPersistence.fetchByF_MAPPING_ID(mappingID);
	}

	public Applicant fetchByEmail(String email) {
		return applicantPersistence.fetchByF_CTE_ID(email);
	}

	public Applicant fetchByTelNo(String telNo) {
		return applicantPersistence.fetchByF_CTT_ID(telNo);
	}

	public Applicant fetchByAppId(String appId) {
		return applicantPersistence.fetchByF_APLC_ID(appId);
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

	@Indexable(type = IndexableType.REINDEX)
	public Applicant updateApplication(ServiceContext context, long applicantId, String applicantName,
			String applicantIdType, String applicantIdNo, String applicantIdDate, String address, String cityCode,
			String cityName, String districtCode, String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String contactEmail, String profile, String password)
			throws PortalException, SystemException {

		_log.info(applicantName);
		_log.info(applicantIdNo);
		_log.info(applicantIdDate);
		_log.info(applicantIdNo);

		Applicant applicant = null;

		Date now = new Date();

		User auditUser = userPersistence.fetchByPrimaryKey(context.getUserId());

		if (applicantId == 0) {

			validateAdd(applicantName, applicantIdType, applicantIdNo, applicantIdDate);

			validateDuplicate(context.getCompanyId(), contactTelNo, applicantIdNo, contactEmail);

			applicantId = counterLocalService.increment(Applicant.class.getName());

			applicant = applicantPersistence.create(applicantId);

			Role roleDefault = RoleLocalServiceUtil.getRole(context.getCompanyId(), ServiceProps.APPLICANT_ROLE_NAME);

			String activationCode = PwdGenerator.getPassword(ServiceProps.PASSWORD_LENGHT);
			;

			boolean autoPassword = false;
			boolean autoScreenName = true;
			boolean sendEmail = false;

			long[] groupIds = null;
			long[] organizationIds = null;
			long[] roleIds = null;
			long[] userGroupIds = null;

			String screenName = null;

			if (Validator.isNull(password)) {
				password = PwdGenerator.getPassword(ServiceProps.PASSWORD_LENGHT);
			}

			UserMgtUtils.SplitName spn = UserMgtUtils.splitName(applicantName, applicantIdType);

			// add default role
			if (Validator.isNotNull(roleDefault)) {
				roleIds = new long[] { roleDefault.getRoleId() };
			}

			Role adminRole = RoleLocalServiceUtil.getRole(context.getCompanyId(), ServiceProps.ADM_ROLE_NAME);

			List<User> adminUsers = userLocalService.getRoleUsers(adminRole.getRoleId());

			long creatorUserId = 0;

			if (adminUsers.size() != 0) {
				creatorUserId = adminUsers.get(0).getUserId();
			}

			Calendar calendar = Calendar.getInstance();

			calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 20);

			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH); // jan = 0, dec = 11
			int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

			User mappingUser = userLocalService.addUserWithWorkflow(creatorUserId, context.getCompanyId(), autoPassword,
					password, password, autoScreenName, screenName, contactEmail, 0l, StringPool.BLANK,
					LocaleUtil.getDefault(), spn.getFirstName(), spn.getMidName(), spn.getLastName(), 0, 0, true, month,
					dayOfMonth, year, ServiceProps.APPLICANT_JOB_TITLE, groupIds, organizationIds, roleIds,
					userGroupIds, sendEmail, context);

			mappingUser.setStatus(WorkflowConstants.STATUS_PENDING);

			long mappingUserId = mappingUser.getUserId();

			Date idDate = null;

			try {
				idDate = UserMgtUtils.convertDate(applicantIdDate);
			} catch (Exception e) {
				_log.error(ApplicantLocalServiceImpl.class.getName() + "date input error");
			}

			// Add audit field
			applicant.setCreateDate(now);
			applicant.setModifiedDate(now);
			applicant.setCompanyId(context.getCompanyId());
			applicant.setUserId(context.getUserId());
			applicant.setUserName(auditUser.getFullName());
			applicant.setGroupId(context.getScopeGroupId());

			applicant.setApplicantName(applicantName);
			applicant.setApplicantIdType(applicantIdType);
			applicant.setApplicantIdNo(applicantIdNo);
			applicant.setApplicantIdDate(idDate);
			applicant.setAddress(address);
			applicant.setCityCode(cityCode);
			applicant.setCityName(cityName);
			applicant.setDistrictCode(districtCode);
			applicant.setDistrictName(districtName);
			applicant.setWardCode(wardCode);
			applicant.setWardName(wardName);
			applicant.setContactName(contactName);
			applicant.setContactTelNo(contactTelNo);
			applicant.setContactEmail(contactEmail);
			applicant.setMappingUserId(mappingUserId);
			applicant.setProfile(profile);
			applicant.setActivationCode(activationCode);
			applicant.setTmpPass(password);

		} else {
			applicant = applicantPersistence.fetchByPrimaryKey(applicantId);

			applicant.setModifiedDate(now);
			applicant.setUserId(context.getUserId());
			applicant.setUserName(auditUser.getFullName());

			Date idDate = null;

			try {
				idDate = UserMgtUtils.convertDate(applicantIdDate);
			} catch (Exception e) {
				_log.error(ApplicantLocalServiceImpl.class.getName() + " date input error");
			}

			if (Validator.isNotNull(applicantName))
				applicant.setApplicantName(applicantName);

			if (Validator.isNotNull(applicantIdType))
				applicant.setApplicantIdType(applicantIdType);

			if (Validator.isNotNull(applicantIdNo))
				applicant.setApplicantIdNo(applicantIdNo);

			if (Validator.isNotNull(idDate))
				applicant.setApplicantIdDate(idDate);

			if (Validator.isNotNull(address))
				applicant.setAddress(address);

			if (Validator.isNotNull(cityCode))
				applicant.setCityCode(cityCode);

			if (Validator.isNotNull(cityName))
				applicant.setCityName(cityName);

			if (Validator.isNotNull(districtCode))
				applicant.setDistrictCode(districtCode);

			if (Validator.isNotNull(districtName))
				applicant.setDistrictName(districtName);

			if (Validator.isNotNull(wardCode))
				applicant.setWardCode(wardCode);

			if (Validator.isNotNull(wardName))
				applicant.setWardName(wardName);

			if (Validator.isNotNull(contactName))
				applicant.setContactName(contactName);

			if (Validator.isNotNull(contactTelNo))
				applicant.setContactTelNo(contactTelNo);

			if (Validator.isNotNull(contactEmail))
				applicant.setContactEmail(contactEmail);

			if (Validator.isNotNull(profile)) {
				applicant.setProfile(profile);
			}

		}

		applicantPersistence.update(applicant);

		return applicant;
	}

	/**
	 * @param appicantName
	 * @param applicantIdType
	 * @param applicantIdNo
	 * @param applicantIdDate
	 * @throws PortalException
	 */
	private void validateAdd(String applicantName, String applicantIdType, String applicantIdNo, String applicantIdDate)
			throws PortalException {
		if (Validator.isNull(applicantName)) {
			throw new NoApplicantNameException("NoApplicantNameException");
		}

		if (Validator.isNull(applicantIdType))
			throw new NoApplicantIdTypeException("NoApplicantIdTypeException");

		if (Validator.isNull(applicantIdNo))
			throw new NoApplicantIdNoException("NoApplicantIdNoException");

		if (Validator.isNull(applicantIdDate))
			throw new NoApplicantIdDateException("NoApplicantIdDateException");
	}

	private void validateDuplicate(long companyId, String contactTelNo, String applicantIdNo, String email)
			throws PortalException {

		Applicant applicant = null;

		applicant = fetchByAppId(applicantIdNo);

		if (Validator.isNotNull(applicant))
			throw new DuplicateApplicantIdException("DuplicateApplicantIdException");

		applicant = fetchByEmail(email);

		if (Validator.isNotNull(applicant))
			throw new DuplicateContactEmailException("DuplicateContactEmailException");

		User user = userLocalService.fetchUserByEmailAddress(companyId, email);

		if (Validator.isNotNull(user))
			throw new DuplicateContactEmailException("DuplicateContactEmailException");

		if (Validator.isNotNull(contactTelNo)) {

			applicant = fetchByTelNo(contactTelNo);

			if (Validator.isNotNull(applicant))
				throw new DuplicateContactTelNoException("DuplicateContactTelNoException");
		}
	}

	@Indexable(type = IndexableType.REINDEX)
	public Applicant removeProfile(long applicantId) {
		Applicant applicant = applicantPersistence.fetchByPrimaryKey(applicantId);

		applicant.setProfile(StringPool.BLANK);

		applicantPersistence.update(applicant);

		return applicant;
	}

	@Indexable(type = IndexableType.REINDEX)
	public Applicant lockoutApplicant(long applicantId) throws PortalException {

		Applicant applicant = applicantLocalService.fetchApplicant(applicantId);

		User user = userPersistence.fetchByPrimaryKey(applicant.getMappingUserId());

		userLocalService.updateLockout(user, true);

		user.setLockout(true);

		userPersistence.update(user);
		applicant.setLock_(true);

		applicantPersistence.update(applicant);

		return applicant;
	}

	@Indexable(type = IndexableType.REINDEX)
	public Applicant activateApplicant(long applicantId, ServiceContext context) throws PortalException {

		Applicant applicant = applicantLocalService.fetchApplicant(applicantId);

		User user = userPersistence.fetchByPrimaryKey(applicant.getMappingUserId());

		userLocalService.updateStatus(user.getUserId(), WorkflowConstants.STATUS_APPROVED, context);

		// reset activationCode
		applicant.setActivationCode(StringPool.BLANK);
		// applicant.setTmpPass(StringPool.BLANK);

		applicantPersistence.update(applicant);

		return applicant;
	}

	@Indexable(type = IndexableType.DELETE)
	public Applicant removeApplicant(long applicantId) throws PortalException, SystemException {
		Applicant applicant = applicantPersistence.fetchByPrimaryKey(applicantId);

		long mappingId = applicant.getMappingUserId();

		userPersistence.remove(mappingId);

		applicantPersistence.remove(applicant);

		return applicant;
	}

	public Hits searchLucene(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {

		String keywords = (String) params.get("keywords");
		String groupId = (String) params.get(ApplicantTerm.GROUP_ID);

		Indexer<Applicant> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Applicant.class);

		searchContext.addFullQueryEntryClassName(Applicant.class.getName());
		searchContext.setEntryClassNames(new String[] { Applicant.class.getName() });
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

			String[] keyword = keywords.split(StringPool.SPACE);

			for (String string : keyword) {

				MultiMatchQuery query = new MultiMatchQuery(string);

				query.addFields(ApplicantTerm.CONTACTNAME, ApplicantTerm.CONTACTEMAIL, ApplicantTerm.CONTACTTELNO,
						ApplicantTerm.ADDRESS);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(ApplicantTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		String type = String.valueOf(params.get("type"));
		String lock = String.valueOf(params.get("lock"));

		if (Validator.isNotNull(type)) {
			MultiMatchQuery query = new MultiMatchQuery(type);

			query.addFields(ApplicantTerm.APPLICANTIDTYPE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(lock)) {
			MultiMatchQuery query = new MultiMatchQuery(lock);

			query.addFields(ApplicantTerm.LOCK);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, Applicant.class.getName());

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);
	}

	public long countLucene(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {

		String keywords = (String) params.get("keywords");
		String groupId = (String) params.get(ApplicantTerm.GROUP_ID);

		Indexer<Applicant> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Applicant.class);

		searchContext.addFullQueryEntryClassName(Applicant.class.getName());
		searchContext.setEntryClassNames(new String[] { Applicant.class.getName() });
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

			String[] keyword = keywords.split(StringPool.SPACE);

			for (String string : keyword) {

				MultiMatchQuery query = new MultiMatchQuery(string);

				query.addFields(ApplicantTerm.CONTACTNAME, ApplicantTerm.CONTACTEMAIL, ApplicantTerm.CONTACTTELNO,
						ApplicantTerm.ADDRESS);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(ApplicantTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		String type = String.valueOf(params.get("type"));
		String lock = String.valueOf(params.get("lock"));

		if (Validator.isNotNull(type)) {
			MultiMatchQuery query = new MultiMatchQuery(type);

			query.addFields(ApplicantTerm.APPLICANTIDTYPE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(lock)) {
			MultiMatchQuery query = new MultiMatchQuery(lock);

			query.addFields(ApplicantTerm.LOCK);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, Applicant.class.getName());

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);
	}

	private Log _log = LogFactoryUtil.getLog(ApplicantLocalServiceImpl.class);
}