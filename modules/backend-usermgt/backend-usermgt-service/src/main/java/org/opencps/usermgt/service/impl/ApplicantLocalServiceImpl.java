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

import org.opencps.backend.usermgt.service.util.ConfigConstants;
import org.opencps.backend.usermgt.service.util.ConfigProps;
import org.opencps.datamgt.constants.DataMGTConstants;
import org.opencps.datamgt.model.DictItem;
import org.opencps.datamgt.utils.DictCollectionUtils;
import org.opencps.usermgt.constants.ApplicantTerm;
import org.opencps.usermgt.exception.DuplicateApplicantIdException;
import org.opencps.usermgt.exception.DuplicateContactEmailException;
import org.opencps.usermgt.exception.NoApplicantIdDateException;
import org.opencps.usermgt.exception.NoApplicantIdNoException;
import org.opencps.usermgt.exception.NoApplicantIdTypeException;
import org.opencps.usermgt.exception.NoApplicantNameException;
import org.opencps.usermgt.exception.NoSuchApplicantException;
import org.opencps.usermgt.model.Applicant;
import org.opencps.usermgt.service.base.ApplicantLocalServiceBaseImpl;
import org.opencps.usermgt.service.util.DateTimeUtils;
import org.opencps.usermgt.service.util.MessageUtil;
import org.opencps.usermgt.service.util.ServiceProps;
import org.opencps.usermgt.service.util.UserMgtUtils;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
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
import com.liferay.portal.kernel.search.WildcardQuery;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.search.generic.MultiMatchQuery;
import com.liferay.portal.kernel.search.generic.TermRangeQueryImpl;
import com.liferay.portal.kernel.search.generic.WildcardQueryImpl;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PwdGenerator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the applicant local service.
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.usermgt.service.ApplicantLocalService} interface.
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
	 * NOTE FOR DEVELOPERS: Never reference this class directly. Always use {@link
	 * org.opencps.usermgt.service.ApplicantLocalServiceUtil} to access the
	 * applicant local service.
	 */

	private static Log _log = LogFactoryUtil.getLog(ApplicantLocalServiceImpl.class);

	public Applicant fetchByMappingID(long mappingID) {
		List<Applicant> lstApps = mappingID > 0 ? applicantPersistence.findByF_MAPPING_ID(mappingID)
				: applicantPersistence.findByF_MAPPING_ID(mappingID, 0, 5);
		Applicant res = (lstApps.size() > 0) ? lstApps.get(0) : null;
		return res;
	}

	public Applicant fetchByEmail(String email) {

		return applicantPersistence.fetchByF_CTE_ID(email);
	}

	public Applicant fetchByTelNo(String telNo) {

		return applicantPersistence.fetchByF_CTT_ID(telNo);
	}

	public Applicant fetchBy_GTelNo(Long groupId, String telNo) {

		return applicantPersistence.fetchByF_GID_CTN(groupId, telNo);
	}

	public Applicant fetchByAppId(String appId) {

		return applicantPersistence.fetchByF_APLC_ID(appId);
	}

	public List<Applicant> getApplicantByType(long groupId, String applicantIdType) {

		return applicantPersistence.findByF_GID_TYPE(groupId, applicantIdType);
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
	public Applicant updateApplication(ServiceContext context, long groupId, long applicantId, String applicantName,
			String applicantIdType, String applicantIdNo, String applicantIdDate, String address, String cityCode,
			String cityName, String districtCode, String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String contactEmail, String profile, String password)
			throws PortalException, SystemException {

		Applicant applicant = null;

		Date now = new Date();

		User auditUser = userPersistence.fetchByPrimaryKey(context.getUserId());

		Date idDate = DateTimeUtils.stringToDate(applicantIdDate);
		_log.debug("ADD APPLICANT | profile: "+profile);
		if (applicantId == 0) {
			_log.info("4444444");
			validateAdd(applicantName, applicantIdType, applicantIdNo, applicantIdDate);

			validateApplicantDuplicate(groupId, context.getCompanyId(), contactTelNo, applicantIdNo, contactEmail);

			applicantId = counterLocalService.increment(Applicant.class.getName());
			
			_log.info("5555555 : applicantId " + applicantId);
			applicant = applicantPersistence.create(applicantId);
			_log.info("ADD APPLICANT: " + applicant);

			Role roleDefault = RoleLocalServiceUtil.getRole(context.getCompanyId(), ServiceProps.APPLICANT_ROLE_NAME);

			String activationCode = PwdGenerator.getPassword(ServiceProps.PASSWORD_LENGHT);

			boolean autoPassword = false;
			boolean autoScreenName = true;
			boolean sendEmail = false;

			long[] groupIds = new long[] { groupId };
			long[] organizationIds = null;
			long[] roleIds = null;
			long[] userGroupIds = null;

			String screenName = null;

			if (Validator.isNull(password)) {
				password = PwdGenerator.getPassword(ServiceProps.PASSWORD_LENGHT);
			}

			String applicantTypeCitizen = ConfigProps.get(ConfigConstants.APPLICANT_TYPE_CITIZEN);
			String applicantTypeBussiness = ConfigProps.get(ConfigConstants.APPLICANT_TYPE_BUSSINESS);
			String headerUser = MessageUtil.getMessage(
					ConfigConstants.HEADER_USER);
			String headerCompany = MessageUtil.getMessage(
					ConfigConstants.HEADER_COMPANY);
			String headerBussiness = MessageUtil.getMessage(
					ConfigConstants.HEADER_BUSSINESS);
			String firstName = (applicantTypeCitizen.equals(applicantIdType) ? headerUser
					: (applicantTypeBussiness.equals(applicantIdType) ? headerCompany
							: headerBussiness));
			String lastName = applicantName;

			UserMgtUtils.SplitName spn = UserMgtUtils.splitName(firstName, lastName);

			// add default role
			if (Validator.isNotNull(roleDefault)) {
				roleIds = new long[] { roleDefault.getRoleId() };
			}

			Role adminRole = RoleLocalServiceUtil.getRole(
				context.getCompanyId(), ServiceProps.ADM_ROLE_NAME);

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
			// _log.info("CREATE APPLICANT: " + spn.getLastName() + "," +
			// spn.getFirstName() + "," + spn.getMidName());
			User mappingUser = userLocalService.addUserWithWorkflow(creatorUserId, context.getCompanyId(), autoPassword,
					password, password, autoScreenName, screenName, contactEmail, 0l, StringPool.BLANK,
					LocaleUtil.getDefault(), spn.getFirstName(), spn.getMidName(), spn.getLastName(), 0, 0, true, month,
					dayOfMonth, year, ServiceProps.APPLICANT_JOB_TITLE, groupIds, organizationIds, roleIds,
					userGroupIds, sendEmail, context);
			// _log.info("MAPPING USER: " + mappingUser.getLastName() + "," +
			// mappingUser.getFullName());
			mappingUser.setStatus(WorkflowConstants.STATUS_PENDING);
			
			_log.info("6666666 : " + JSONFactoryUtil.looseSerialize(mappingUser));

			long mappingUserId = mappingUser.getUserId();

			// Add audit field
			applicant.setCreateDate(now);
			applicant.setModifiedDate(new Date());
			applicant.setCompanyId(context.getCompanyId());
			applicant.setUserId(context.getUserId());
			applicant.setUserName(auditUser.getFullName());
			applicant.setGroupId(groupId);

			applicant.setApplicantName(applicantName);
			applicant.setApplicantIdType(applicantIdType);
			applicant.setApplicantIdNo(applicantIdNo);
			if (Validator.isNotNull(idDate))
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
			applicant.setVerification(0);

		} else {
			applicant = applicantPersistence.fetchByPrimaryKey(applicantId);

			applicant.setModifiedDate(now);
			applicant.setUserId(context.getUserId());
			applicant.setUserName(auditUser.getFullName());

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

		_log.info("7777777 : " + JSONFactoryUtil.looseSerialize(applicant));
		return applicantPersistence.update(applicant);
	}

	@Indexable(type = IndexableType.REINDEX)
	public Applicant updateApplication(ServiceContext context, long groupId, long applicantId, String applicantName,
			String applicantIdType, String applicantIdNo, String applicantIdDate, String address, String cityCode,
			String cityName, String districtCode, String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String contactEmail, String profile, String password,
			boolean activeUser) throws PortalException, SystemException {
		Applicant applicant = null;

		Date now = new Date();

		User auditUser = userPersistence.fetchByPrimaryKey(context.getUserId());

		Date idDate = DateTimeUtils.stringToDate(applicantIdDate);

		if (applicantId == 0) {

			validateAdd(applicantName, applicantIdType, applicantIdNo, applicantIdDate);

			validateApplicantDuplicate(groupId, context.getCompanyId(), contactTelNo, applicantIdNo, contactEmail);

			applicantId = counterLocalService.increment(Applicant.class.getName());

			applicant = applicantPersistence.create(applicantId);

			Role roleDefault = RoleLocalServiceUtil.getRole(context.getCompanyId(), ServiceProps.APPLICANT_ROLE_NAME);

			String activationCode = PwdGenerator.getPassword(ServiceProps.PASSWORD_LENGHT);

			boolean autoPassword = false;
			boolean autoScreenName = true;
			boolean sendEmail = false;

			long[] groupIds = new long[] { groupId };
			long[] organizationIds = null;
			long[] roleIds = null;
			long[] userGroupIds = null;

			String screenName = null;

			if (Validator.isNull(password)) {
				password = PwdGenerator.getPassword(ServiceProps.PASSWORD_LENGHT);
			}

			String firstName = (ApplicantTerm.APPLICANTIDTYPE_CITIZEN.equals(applicantIdType) ? MessageUtil.getMessage(
					ConfigConstants.HEADER_USER)
					: (ApplicantTerm.APPLICANTIDTYPE_BUSINESS.equals(applicantIdType) ? MessageUtil.getMessage(
							ConfigConstants.HEADER_COMPANY) : MessageUtil.getMessage(
									ConfigConstants.HEADER_BUSSINESS)));
			String lastName = applicantName;

			UserMgtUtils.SplitName spn = UserMgtUtils.splitName(firstName, lastName);

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
			// _log.info("CREATE APPLICANT: " + spn.getLastName() + "," +
			// spn.getFirstName() + "," + spn.getMidName());
			_log.info("Appicant LastName: " + spn.getLastName() + "|" + spn.getLastName().length());
			User mappingUser = userLocalService.addUserWithWorkflow(creatorUserId, context.getCompanyId(), autoPassword,
					password, password, autoScreenName, screenName, contactEmail, 0l, StringPool.BLANK,
					LocaleUtil.getDefault(), spn.getFirstName(), spn.getMidName(), spn.getLastName(), 0, 0, true, month,
					dayOfMonth, year, ServiceProps.APPLICANT_JOB_TITLE, groupIds, organizationIds, roleIds,
					userGroupIds, sendEmail, context);
			// _log.info("MAPPING USER: " + mappingUser.getLastName() + "," +
			// mappingUser.getFullName());
			if(activeUser) {
				mappingUser.setStatus(WorkflowConstants.STATUS_APPROVED);
			}else {
				mappingUser.setStatus(WorkflowConstants.STATUS_PENDING);
			}
			
			long mappingUserId = mappingUser.getUserId();

			// Add audit field
			applicant.setCreateDate(now);
			applicant.setModifiedDate(new Date());
			applicant.setCompanyId(context.getCompanyId());
			applicant.setUserId(context.getUserId());
			applicant.setUserName(auditUser.getFullName());
			applicant.setGroupId(groupId);

			applicant.setApplicantName(applicantName);
			applicant.setApplicantIdType(applicantIdType);
			applicant.setApplicantIdNo(applicantIdNo);
			if (Validator.isNotNull(idDate))
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

		_log.debug("BEFORE UPDATE APPLICANT");
		applicantPersistence.update(applicant);
		_log.debug("AFTER UPDATE APPLICANT");
		return applicant;
	}
	
	
	@Indexable(type = IndexableType.REINDEX)
	public Applicant updateApplication(ServiceContext context, long groupId, long applicantId, String mappingClassName,
			String mappingClassPK) throws PortalException, SystemException {
		Applicant applicant = null;

		Date now = new Date();

		User auditUser = userPersistence.fetchByPrimaryKey(context.getUserId());

		applicant = applicantPersistence.fetchByPrimaryKey(applicantId);

		applicant.setModifiedDate(now);
		applicant.setUserId(context.getUserId());
		applicant.setUserName(auditUser.getFullName());

		if (Validator.isNotNull(mappingClassName))
			applicant.setMappingClassName(mappingClassName);

		if (Validator.isNotNull(mappingClassPK))
			applicant.setMappingClassPK(mappingClassPK);

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

		if (Validator.isNull(applicantIdType)) {
			throw new NoApplicantIdTypeException("NoApplicantIdTypeException");
		}
		if (Validator.isNull(applicantIdNo)) {
			throw new NoApplicantIdNoException("NoApplicantIdNoException");
		}
		if (Validator.isNull(applicantIdDate)) {
			throw new NoApplicantIdDateException("NoApplicantIdDateException");
		}
	}

	// private void validateDuplicate(long companyId, String contactTelNo,
	// String applicantIdNo,
	// String email) throws PortalException {
	//
	// Applicant applicant = null;
	//
	// applicant = fetchByAppId(applicantIdNo);
	//
	// if (Validator.isNotNull(applicant))
	// throw new DuplicateApplicantIdException("DuplicateApplicantIdException");
	//
	// applicant = fetchByEmail(email);
	//
	// if (Validator.isNotNull(applicant))
	// throw new
	// DuplicateContactEmailException("DuplicateContactEmailException");
	//
	// User user = userLocalService.fetchUserByEmailAddress(companyId, email);
	//
	// if (Validator.isNotNull(user))
	// throw new
	// DuplicateContactEmailException("DuplicateContactEmailException");
	//
	/// * if (Validator.isNotNull(contactTelNo)) {
	//
	// applicant = fetchByTelNo(contactTelNo);
	//
	// if (Validator.isNotNull(applicant))
	// throw new
	// DuplicateContactTelNoException("DuplicateContactTelNoException");
	// }*/
	// }

	// Process validate applicant register
	private void validateApplicantDuplicate(long groupId, long companyId, String contactTelNo, String applicantIdNo,
			String email) throws PortalException {

		Applicant applicant = applicantPersistence.fetchByF_APLC_GID(groupId, applicantIdNo);

		if (Validator.isNotNull(applicant))
			throw new DuplicateApplicantIdException("DuplicateApplicantIdException-" + applicant.getContactEmail());

		applicant = applicantPersistence.fetchByF_GID_CTEM(groupId, email);

		if (Validator.isNotNull(applicant))
			throw new DuplicateContactEmailException("DuplicateContactEmailException-" + applicant.getContactEmail());

		User user = userLocalService.fetchUserByEmailAddress(companyId, email);

		if (Validator.isNotNull(user))
			throw new DuplicateContactEmailException("DuplicateContactEmailException");

		/*
		 * if (Validator.isNotNull(contactTelNo)) { applicant =
		 * fetchByTelNo(contactTelNo); if (Validator.isNotNull(applicant)) throw new
		 * DuplicateContactTelNoException("DuplicateContactTelNoException"); }
		 */
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

		boolean lockStatus = user.getLockout();

		if (lockStatus) {
			lockStatus = false;
		} else {
			lockStatus = true;
		}

		userLocalService.updateLockout(user, lockStatus);

		user.setLockout(lockStatus);
		userPersistence.update(user);

		applicant.setLock_(lockStatus);

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

		try {
			userPersistence.remove(mappingId);
		} catch (Exception e) {
			_log.warn("User mapping not exists with mappingUserId: " + mappingId);
		}

		applicantPersistence.remove(applicant);

		return applicant;
	}

	@SuppressWarnings("deprecation")
	public Hits searchLucene(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(ApplicantTerm.GROUP_ID);
		String type = String.valueOf(params.get(ApplicantTerm.APPLICANTIDTYPE));
		String lock = String.valueOf(params.get(ApplicantTerm.LOCK));
		String idNo = String.valueOf(params.get(ApplicantTerm.APPLICANTIDNO));
		String applicantName = String.valueOf(params.get(ApplicantTerm.APPLICANTNAME));
		String verification = String.valueOf(params.get(ApplicantTerm.VERIFICATION));
		Boolean haveAccount = params.get(ApplicantTerm.HAVE_ACCOUNT) != null ? (Boolean)params.get(ApplicantTerm.HAVE_ACCOUNT) : false;
		String fromRegistryDate = GetterUtil.getString(params.get(ApplicantTerm.FROM_REGISTRY_DATE));
		String toRegistryDate = GetterUtil.getString(params.get(ApplicantTerm.TO_REGISTRY_DATE));
		
		Indexer<Applicant> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Applicant.class);

		searchContext.addFullQueryEntryClassName(Applicant.class.getName());
		searchContext.setEntryClassNames(new String[] { Applicant.class.getName() });
		searchContext.setAttribute(ApplicantTerm.PAGINATION_TYPE, ApplicantTerm.REGULAR);
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

				query.addFields(
					ApplicantTerm.CONTACTNAME, ApplicantTerm.CONTACTEMAIL,
					ApplicantTerm.CONTACTTELNO, ApplicantTerm.ADDRESS, ApplicantTerm.APPLICANTNAME);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

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

		// LamTV: Process search LIKE
		if (Validator.isNotNull(idNo)) {
			String[] keywordArr = idNo.split(StringPool.SPACE);
			BooleanQuery query = new BooleanQueryImpl();
			for (String key : keywordArr) {
				WildcardQuery wildQuery = new WildcardQueryImpl(ApplicantTerm.APPLICANTIDNO,
						key.toLowerCase() + StringPool.STAR);
				query.add(wildQuery, BooleanClauseOccur.MUST);
			}
			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}
		if (Validator.isNotNull(applicantName)) {
			MultiMatchQuery query = new MultiMatchQuery(applicantName);

			query.addFields(ApplicantTerm.APPLICANTNAME);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}
		
		if (Validator.isNotNull(verification)) {
			if (verification.contains(StringPool.COMMA)) {
				String[] multi = verification.split(StringPool.COMMA);
				BooleanQuery verificationQuery = new BooleanQueryImpl();
				
				for (String oneVerification : multi) {
					MultiMatchQuery query = new MultiMatchQuery(oneVerification);
					query.addFields(ApplicantTerm.VERIFICATION);
					verificationQuery.add(query, BooleanClauseOccur.SHOULD);									
				}
				
				booleanQuery.add(verificationQuery, BooleanClauseOccur.MUST);
			}
			else {
				MultiMatchQuery query = new MultiMatchQuery(verification);

				query.addFields(ApplicantTerm.VERIFICATION);

				booleanQuery.add(query, BooleanClauseOccur.MUST);				
			}
		}
		
		if (haveAccount) {
			MultiMatchQuery query = new MultiMatchQuery(String.valueOf(0));
			query.addFields(ApplicantTerm.MAPPINGUSERID);
			booleanQuery.add(query, BooleanClauseOccur.MUST_NOT);			
		}
		
		if (Validator.isNotNull(fromRegistryDate) ||
				Validator.isNotNull(toRegistryDate)) {
			String fromRegistryDateFilter = fromRegistryDate + ApplicantTerm.HOUR_START;
			String toRegistryDateFilter = toRegistryDate + ApplicantTerm.HOUR_END;
			_log.info("fromRegistryDateFilter: "+fromRegistryDateFilter);
			_log.info("toRegistryDateFilter: "+toRegistryDateFilter);

			if (Validator.isNotNull(fromRegistryDate)) {
				if (Validator.isNotNull(toRegistryDate)) {
					TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
							ApplicantTerm.CREATE_DATE, fromRegistryDateFilter,
							toRegistryDateFilter, true, true);

					booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
				} else {
					TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
							ApplicantTerm.CREATE_DATE, fromRegistryDateFilter,
							toRegistryDateFilter, true, false);

					booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
				}
			} else {
				if (Validator.isNotNull(toRegistryDate)) {
					TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
							ApplicantTerm.CREATE_DATE, fromRegistryDateFilter,
							toRegistryDateFilter, false, true);

					booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
				}
			}
		}
		
		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, Applicant.class.getName());

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);
	}

	@SuppressWarnings("deprecation")
	public long countLucene(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);
		String type = String.valueOf(params.get(ApplicantTerm.APPLICANTIDTYPE));
		String lock = String.valueOf(params.get(ApplicantTerm.LOCK));
		String idNo = String.valueOf(params.get(ApplicantTerm.APPLICANTIDNO));
		String applicantName = String.valueOf(params.get(ApplicantTerm.APPLICANTNAME));
		String verification = String.valueOf(params.get(ApplicantTerm.VERIFICATION));
		Boolean haveAccount = params.get(ApplicantTerm.HAVE_ACCOUNT) != null ? (Boolean)params.get(ApplicantTerm.HAVE_ACCOUNT) : false;
		String fromRegistryDate = GetterUtil.getString(params.get(ApplicantTerm.FROM_REGISTRY_DATE));
		String toRegistryDate = GetterUtil.getString(params.get(ApplicantTerm.TO_REGISTRY_DATE));
		
		Indexer<Applicant> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Applicant.class);

		searchContext.addFullQueryEntryClassName(Applicant.class.getName());
		searchContext.setEntryClassNames(new String[] {
			Applicant.class.getName()
		});
		searchContext.setAttribute(ApplicantTerm.PAGINATION_TYPE, ConfigConstants.PAGINATION_TYPE_REGULAR);
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

				query.addFields(
					ApplicantTerm.CONTACTNAME, ApplicantTerm.CONTACTEMAIL,
					ApplicantTerm.CONTACTTELNO, ApplicantTerm.ADDRESS, ApplicantTerm.APPLICANTNAME);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(ApplicantTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

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

		// LamTV: Process search LIKE
		if (Validator.isNotNull(idNo)) {
			String[] keywordArr = idNo.split(StringPool.SPACE);
			BooleanQuery query = new BooleanQueryImpl();
			for (String key : keywordArr) {
				WildcardQuery wildQuery = new WildcardQueryImpl(ApplicantTerm.APPLICANTIDNO,
						key.toLowerCase() + StringPool.STAR);
				query.add(wildQuery, BooleanClauseOccur.MUST);
			}
			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}
		if (Validator.isNotNull(applicantName)) {
			MultiMatchQuery query = new MultiMatchQuery(applicantName);

			query.addFields(ApplicantTerm.APPLICANTNAME);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}
		if (Validator.isNotNull(verification)) {
			if (verification.contains(StringPool.COMMA)) {
				String[] multi = verification.split(StringPool.COMMA);
				BooleanQuery verificationQuery = new BooleanQueryImpl();
				
				for (String oneVerification : multi) {
					MultiMatchQuery query = new MultiMatchQuery(oneVerification);
					query.addFields(ApplicantTerm.VERIFICATION);
					verificationQuery.add(query, BooleanClauseOccur.SHOULD);									
				}
				
				booleanQuery.add(verificationQuery, BooleanClauseOccur.MUST);
			}
			else {
				MultiMatchQuery query = new MultiMatchQuery(verification);

				query.addFields(ApplicantTerm.VERIFICATION);

				booleanQuery.add(query, BooleanClauseOccur.MUST);				
			}
		}
		
		if (haveAccount) {
			MultiMatchQuery query = new MultiMatchQuery(String.valueOf(0));
			query.addFields(ApplicantTerm.MAPPINGUSERID);
			booleanQuery.add(query, BooleanClauseOccur.MUST_NOT);			
		}
		if (Validator.isNotNull(fromRegistryDate) ||
				Validator.isNotNull(toRegistryDate)) {
			String fromRegistryDateFilter = fromRegistryDate + "000000";
			String toRegistryDateFilter = toRegistryDate + "235959";
			_log.info("fromRegistryDateFilter: "+fromRegistryDateFilter);
			_log.info("toRegistryDateFilter: "+toRegistryDateFilter);

			if (Validator.isNotNull(fromRegistryDate)) {
				if (Validator.isNotNull(toRegistryDate)) {
					TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
							ApplicantTerm.CREATE_DATE, fromRegistryDateFilter,
							toRegistryDateFilter, true, true);

					booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
				} else {
					TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
							ApplicantTerm.CREATE_DATE, fromRegistryDateFilter,
							toRegistryDateFilter, true, false);

					booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
				}
			} else {
				if (Validator.isNotNull(toRegistryDate)) {
					TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
							ApplicantTerm.CREATE_DATE, fromRegistryDateFilter,
							toRegistryDateFilter, false, true);

					booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
				}
			}
		}
		
		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, Applicant.class.getName());

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);
	}

	// Add applicant when listener
	@Indexable(type = IndexableType.REINDEX)
	public Applicant updateApplicant(long groupId, long userId, long companyId, String applicantName,
			String applicantIdType, String applicantIdNo, Date applicantIdDate, String address, String cityCode,
			String cityName, String districtCode, String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String contactEmail) {

		Applicant applicant = applicantPersistence.fetchByF_APLC_GID(groupId, applicantIdNo);
		Date now = new Date();

		if (applicant == null) {
			long applicantId = counterLocalService.increment(Applicant.class.getName());
			applicant = applicantPersistence.create(applicantId);

			// Add audit field
			applicant.setCreateDate(now);
			applicant.setModifiedDate(now);
			applicant.setCompanyId(companyId);
			applicant.setUserId(userId);
			applicant.setGroupId(groupId);

			applicant.setApplicantName(applicantName);
			applicant.setApplicantIdType(applicantIdType);
			applicant.setApplicantIdNo(applicantIdNo);
			applicant.setApplicantIdDate(applicantIdDate);
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

		} else {

			applicant.setModifiedDate(now);
			applicant.setUserId(userId);

			if (Validator.isNotNull(applicantName))
				applicant.setApplicantName(applicantName);

			if (Validator.isNotNull(applicantIdType))
				applicant.setApplicantIdType(applicantIdType);

			if (Validator.isNotNull(applicantIdNo))
				applicant.setApplicantIdNo(applicantIdNo);

			if (Validator.isNotNull(applicantIdDate))
				applicant.setApplicantIdDate(applicantIdDate);

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

		}

		return applicantPersistence.update(applicant);
	}

	// Add applicant when listener
	@Indexable(type = IndexableType.REINDEX)
	public Applicant updateApplicant(long groupId, long userId, long companyId, String applicantName,
			String applicantIdType, String applicantIdNo, Date applicantIdDate, String address, String cityCode,
			String cityName, String districtCode, String districtName, String wardCode, String wardName,
			String contactName, String contactTelNo, String contactEmail, String profile) {

		Applicant applicant = applicantPersistence.fetchByF_APLC_GID(groupId, applicantIdNo);
		Date now = new Date();

		if (applicant == null) {
			long applicantId = counterLocalService.increment(Applicant.class.getName());
			applicant = applicantPersistence.create(applicantId);

			// Add audit field
			applicant.setCreateDate(now);
			applicant.setModifiedDate(now);
			applicant.setCompanyId(companyId);
			applicant.setUserId(userId);
			applicant.setGroupId(groupId);

			applicant.setApplicantName(applicantName);
			applicant.setApplicantIdType(applicantIdType);
			applicant.setApplicantIdNo(applicantIdNo);
			applicant.setApplicantIdDate(applicantIdDate);
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
			applicant.setProfile(profile);
		} else {

			applicant.setModifiedDate(now);
			applicant.setUserId(userId);

			if (Validator.isNotNull(applicantName))
				applicant.setApplicantName(applicantName);

			if (Validator.isNotNull(applicantIdType))
				applicant.setApplicantIdType(applicantIdType);

			if (Validator.isNotNull(applicantIdNo))
				applicant.setApplicantIdNo(applicantIdNo);

			if (Validator.isNotNull(applicantIdDate))
				applicant.setApplicantIdDate(applicantIdDate);

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

		return applicantPersistence.update(applicant);
	}

	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public Applicant adminProcessDelete(Long id) {

		Applicant object = applicantPersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			long mappingUserId = object.getMappingUserId();
			if (mappingUserId > 0) {
				try {
					userPersistence.remove(mappingUserId);
				} catch (NoSuchUserException e) {
					_log.debug(e);
				}
			}
			applicantPersistence.remove(object);			
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public Applicant adminProcessData(JSONObject objectData)
		throws DuplicateContactEmailException, NoApplicantIdNoException, DuplicateApplicantIdException {

		Applicant object = null;

		if (objectData.getLong(ApplicantTerm.APPLICANT_ID) > 0) {

			object = applicantPersistence.fetchByPrimaryKey(
				objectData.getLong(ApplicantTerm.APPLICANT_ID));

			object.setModifiedDate(new Date());

		} else {

			int checkExitsAplc = applicantPersistence.countByF_GID_CTEM(objectData.getLong(Field.GROUP_ID), objectData.getString(ApplicantTerm.CONTACTEMAIL));
			if (checkExitsAplc > 0 && !GetterUtil.getBoolean(objectData.getBoolean(ApplicantTerm.NOT_CHECK_DUPLICATE))) {
				throw new DuplicateContactEmailException("email_exits");
			} else if (Validator.isNull(objectData.getString(ApplicantTerm.APPLICANTIDNO))) {
				throw new NoApplicantIdNoException("NoApplicantIdNo");
			} else {
				checkExitsAplc = applicantPersistence.countByF_APLC_GID(objectData.getLong(Field.GROUP_ID), objectData.getString(ApplicantTerm.APPLICANTIDNO));
				if (checkExitsAplc > 0) {
					throw new DuplicateApplicantIdException("applicantIdNo_exits");
				}
			}

			long id = CounterLocalServiceUtil.increment(Applicant.class.getName());

			object = applicantPersistence.create(id);

			object.setGroupId(objectData.getLong(Field.GROUP_ID));
			object.setCompanyId(objectData.getLong(ApplicantTerm.COMPANY_ID));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong(ApplicantTerm.USER_ID));

		object.setApplicantName(objectData.getString(ApplicantTerm.APPLICANTNAME));
		object.setApplicantIdType(objectData.getString(ApplicantTerm.APPLICANTIDTYPE));
		object.setApplicantIdNo(objectData.getString(ApplicantTerm.APPLICANTIDNO));
		if (objectData.getLong(ApplicantTerm.APPLICANTIDDATE) > 0)
			object.setApplicantIdDate(
				new Date(objectData.getLong(ApplicantTerm.APPLICANTIDDATE)));
		object.setAddress(objectData.getString(ApplicantTerm.ADDRESS));
		object.setContactName(objectData.getString(ApplicantTerm.CONTACTNAME));
		object.setContactTelNo(objectData.getString(ApplicantTerm.CONTACTTELNO));
		object.setContactEmail(objectData.getString(ApplicantTerm.CONTACTEMAIL));
		object.setMappingUserId(objectData.getLong(ApplicantTerm.MAPPINGUSERID));
		object.setActivationCode(objectData.getString(ApplicantTerm.ACTIVATION_CODE));
		object.setLock_(objectData.getBoolean(ApplicantTerm.LOCK_));
		object.setProfile(objectData.getString(ApplicantTerm.PROFILE));
		object.setTmpPass(objectData.getString(ApplicantTerm.TMP_SECRET));
		object.setRepresentativeEnterprise(
			objectData.getString(ApplicantTerm.REPRESENTATIVE_ENTERPRISE));

		object.setCityCode(objectData.getString(ApplicantTerm.CITYCODE));
		object.setDistrictCode(objectData.getString(ApplicantTerm.DISTRICTCODE));
		object.setWardCode(objectData.getString(ApplicantTerm.WARDCODE));

		DictItem dictItem = DictCollectionUtils.getDictItemByCode(
			DataMGTConstants.ADMINISTRATIVE_REGION,
			objectData.getString(ApplicantTerm.CITYCODE), objectData.getLong(Field.GROUP_ID));

		if (Validator.isNotNull(dictItem)) {
			object.setCityName(dictItem.getItemName());
		}

		dictItem = DictCollectionUtils.getDictItemByCode(
			DataMGTConstants.ADMINISTRATIVE_REGION,
			objectData.getString(ApplicantTerm.DISTRICTCODE),
			objectData.getLong(Field.GROUP_ID));

		if (Validator.isNotNull(dictItem)) {
			object.setDistrictName(dictItem.getItemName());
		}

		dictItem = DictCollectionUtils.getDictItemByCode(
			DataMGTConstants.ADMINISTRATIVE_REGION,
			objectData.getString(ApplicantTerm.WARDCODE), objectData.getLong(Field.GROUP_ID));

		if (Validator.isNotNull(dictItem)) {
			object.setWardName(dictItem.getItemName());
		}

		applicantPersistence.update(object);

		return object;
	}

	public Applicant fetchByF_APLC_GID(long groupId, String applicantIdNo) {

		Applicant applicant = applicantPersistence.fetchByF_APLC_GID(groupId, applicantIdNo);
		return applicant;
	}

	@Indexable(type = IndexableType.REINDEX)
	public Applicant importApplicationDB(long groupId, long userId, long applicantId, long mappingUserId,
			String applicantIdNo, String applicantName, String applicantIdType, Date applicantIdDate,
			String contactEmail, String contactTelNo, String address, String cityCode, String cityName,
			String districtCode, String districtName, String wardCode, String wardName, String contactName,
			String profile, ServiceContext context) throws PortalException {

		Date now = new Date();
		User auditUser = userPersistence.fetchByPrimaryKey(userId);
		Applicant applicant = null;
		if (applicantId == 0) {
//			System.out.println("contactEmail: " + contactEmail + "| applicantId: " + applicantId);
			applicantId = counterLocalService.increment(Applicant.class.getName());
			applicant = applicantPersistence.create(applicantId);

			try {
//				System.out.println("MAPPING USER ID: " + mappingUserId);
				// Add audit field
				applicant.setCreateDate(now);
				applicant.setModifiedDate(now);
				applicant.setCompanyId(context.getCompanyId());
				applicant.setUserId(context.getUserId());
				applicant.setUserName(auditUser.getFullName());
				applicant.setGroupId(groupId);

				applicant.setApplicantName(applicantName);
				applicant.setApplicantIdType(applicantIdType);
				applicant.setApplicantIdNo(applicantIdNo);
				if (Validator.isNotNull(applicantIdDate))
					applicant.setApplicantIdDate(applicantIdDate);
				applicant.setContactTelNo(contactTelNo);
				applicant.setCityCode(cityCode);
				applicant.setCityName(cityName);
				applicant.setDistrictCode(districtCode);
				applicant.setDistrictName(districtName);
				applicant.setWardCode(wardCode);
				applicant.setWardName(wardName);
				applicant.setContactEmail(contactEmail);
				applicant.setMappingUserId(mappingUserId);
				applicant.setActivationCode(StringPool.BLANK);
				applicant.setContactName(contactName);
				applicant.setProfile(profile);
			} catch (Exception e) {
				_log.info(e);
			}

		}
		return applicantPersistence.update(applicant);
	}

	@Indexable(type = IndexableType.REINDEX)
	public Applicant importApplicationDB(long groupId, long userId, long applicantId, String applicantIdNo,
			String applicantName, String applicantIdType, Date applicantIdDate, String contactEmail,
			String contactTelNo, String address, String cityCode, String cityName, String districtCode,
			String districtName, String wardCode, String wardName, String contactName, String profile,
			ServiceContext context) throws PortalException {

		Date now = new Date();
		User auditUser = userPersistence.fetchByPrimaryKey(userId);
		Applicant applicant = null;
		// String password = "12345";
		String password = PwdGenerator.getPassword(4) + "1@#A";
//		System.out.println("contactEmail: " + contactEmail + "| password: " + password);
		_log.info("contactEmail: " + contactEmail + "| password: " + password);

		if (applicantId == 0) {
//			System.out.println("contactEmail: " + contactEmail + "| applicantId: " + applicantId);
			applicantId = counterLocalService.increment(Applicant.class.getName());
			applicant = applicantPersistence.create(applicantId);

			try {
				Role roleDefault = RoleLocalServiceUtil.getRole(auditUser.getCompanyId(),
						ServiceProps.APPLICANT_ROLE_NAME);

				String activationCode = StringPool.BLANK;

				boolean autoPassword = false;
				boolean autoScreenName = true;
				boolean sendEmail = false;

				long[] groupIds = new long[] { groupId };
				long[] organizationIds = null;
				long[] roleIds = null;
				long[] userGroupIds = null;

				String screenName = null;
				if (Validator.isNull(password)) {
					password = PwdGenerator.getPassword(ServiceProps.PASSWORD_LENGHT);
				}

				String firstName = (ApplicantTerm.APPLICANTIDTYPE_CITIZEN.equals(applicantIdType) ? MessageUtil.getMessage(
						ConfigConstants.HEADER_USER)
						: (ApplicantTerm.APPLICANTIDTYPE_BUSINESS.equals(applicantIdType) ? MessageUtil.getMessage(
								ConfigConstants.HEADER_COMPANY) : MessageUtil.getMessage(
										ConfigConstants.HEADER_BUSSINESS)));
				String lastName = applicantName;

				UserMgtUtils.SplitName spn = UserMgtUtils.splitName(firstName, lastName);

				// add default role
				if (Validator.isNotNull(roleDefault)) {
					roleIds = new long[] { roleDefault.getRoleId() };
				}

				Role adminRole = RoleLocalServiceUtil.getRole(auditUser.getCompanyId(), ServiceProps.ADM_ROLE_NAME);
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
				// _log.info("CREATE APPLICANT: " + spn.getLastName() + "," +
				// spn.getFirstName() + "," + spn.getMidName());
				User mappingUser = userLocalService.addUserWithWorkflow(creatorUserId, auditUser.getCompanyId(),
						autoPassword, password, password, autoScreenName, screenName, contactEmail, 0l,
						StringPool.BLANK, LocaleUtil.getDefault(), spn.getFirstName(), spn.getMidName(),
						spn.getLastName(), 0, 0, true, month, dayOfMonth, year, ServiceProps.APPLICANT_JOB_TITLE,
						groupIds, organizationIds, roleIds, userGroupIds, sendEmail, context);
//				System.out.println("MAPPING USER: " + mappingUser.getLastName() + "," + mappingUser.getFullName());
				// mappingUser.setStatus(WorkflowConstants.STATUS_APPROVED);
				userLocalService.updateStatus(mappingUser.getUserId(), WorkflowConstants.STATUS_APPROVED, context);
				//

				long mappingUserId = mappingUser.getUserId();
//				System.out.println("MAPPING USER ID: " + mappingUserId);
				// Add audit field
				applicant.setCreateDate(now);
				applicant.setModifiedDate(now);
				applicant.setCompanyId(context.getCompanyId());
				applicant.setUserId(context.getUserId());
				applicant.setUserName(auditUser.getFullName());
				applicant.setGroupId(groupId);

				applicant.setApplicantName(applicantName);
				applicant.setApplicantIdType(applicantIdType);
				applicant.setApplicantIdNo(applicantIdNo);
				if (Validator.isNotNull(applicantIdDate))
					applicant.setApplicantIdDate(applicantIdDate);
				applicant.setContactTelNo(contactTelNo);
				applicant.setCityCode(cityCode);
				applicant.setCityName(cityName);
				applicant.setDistrictCode(districtCode);
				applicant.setDistrictName(districtName);
				applicant.setWardCode(wardCode);
				applicant.setWardName(wardName);
				applicant.setContactEmail(contactEmail);
				applicant.setMappingUserId(mappingUserId);
				applicant.setActivationCode(activationCode);
				applicant.setTmpPass(password);
				applicant.setContactName(contactName);
				applicant.setProfile(profile);
			} catch (Exception e) {
				_log.info(e);
			}

		} else {
			applicant = applicantPersistence.fetchByPrimaryKey(applicantId);

			applicant.setModifiedDate(now);
			applicant.setUserId(context.getUserId());
			applicant.setUserName(auditUser.getFullName());

			if (Validator.isNotNull(applicantName))
				applicant.setApplicantName(applicantName);

			if (Validator.isNotNull(applicantIdType))
				applicant.setApplicantIdType(applicantIdType);

			if (Validator.isNotNull(applicantIdNo))
				applicant.setApplicantIdNo(applicantIdNo);

			if (Validator.isNotNull(applicantIdDate))
				applicant.setApplicantIdDate(applicantIdDate);

			if (Validator.isNotNull(contactTelNo))
				applicant.setContactTelNo(contactTelNo);

			if (Validator.isNotNull(contactEmail))
				applicant.setContactEmail(contactEmail);

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
			
			if (Validator.isNotNull(profile))
				applicant.setProfile(profile);
			
		}

		return applicantPersistence.update(applicant);
	}

	@Indexable(type = IndexableType.REINDEX)
	public Applicant updateApplicationDB(long groupId, long userId, long applicantId, String applicantIdNo,
			String applicantName, String applicantIdType, Date applicantIdDate, String contactEmail,
			String contactTelNo, ServiceContext context) throws PortalException {

		Date now = new Date();
		User auditUser = userPersistence.fetchByPrimaryKey(userId);
		Applicant applicant = null;
		String secretCode = "12345";

		if (applicantId == 0) {
			// validateAdd(applicantName, applicantIdType, applicantIdNo,
			// applicantIdDate);
			// validateApplicantDuplicate(groupId, context.getCompanyId(),
			// contactTelNo, applicantIdNo, contactEmail);

			applicantId = counterLocalService.increment(Applicant.class.getName());
			applicant = applicantPersistence.create(applicantId);

			Role roleDefault = RoleLocalServiceUtil.getRole(auditUser.getCompanyId(), ServiceProps.APPLICANT_ROLE_NAME);

			// String activationCode =
			// PwdGenerator.getPassword(ServiceProps.PASSWORD_LENGHT);
			String activationCode = StringPool.BLANK;

			boolean autoPassword = false;
			boolean autoScreenName = true;
			boolean sendEmail = false;

			long[] groupIds = new long[] { groupId };
			long[] organizationIds = null;
			long[] roleIds = null;
			long[] userGroupIds = null;

			String screenName = null;
			if (Validator.isNull(secretCode)) {
				secretCode = PwdGenerator.getPassword(ServiceProps.PASSWORD_LENGHT);
			}

			String firstName = (ApplicantTerm.APPLICANTIDTYPE_CITIZEN.equals(applicantIdType) ? MessageUtil.getMessage(
					ConfigConstants.HEADER_USER)
					: (ApplicantTerm.APPLICANTIDTYPE_BUSINESS.equals(applicantIdType) ? MessageUtil.getMessage(
							ConfigConstants.HEADER_COMPANY) : MessageUtil.getMessage(
									ConfigConstants.HEADER_BUSSINESS)));
			String lastName = applicantName;

			UserMgtUtils.SplitName spn = UserMgtUtils.splitName(firstName, lastName);

			// add default role
			if (Validator.isNotNull(roleDefault)) {
				roleIds = new long[] { roleDefault.getRoleId() };
			}

			Role adminRole = RoleLocalServiceUtil.getRole(auditUser.getCompanyId(), ServiceProps.ADM_ROLE_NAME);
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
			// _log.info("CREATE APPLICANT: " + spn.getLastName() + "," +
			// spn.getFirstName() + "," + spn.getMidName());
			User mappingUser = userLocalService.addUserWithWorkflow(creatorUserId, auditUser.getCompanyId(),
					autoPassword, secretCode, secretCode, autoScreenName, screenName, contactEmail, 0l, StringPool.BLANK,
					LocaleUtil.getDefault(), spn.getFirstName(), spn.getMidName(), spn.getLastName(), 0, 0, true, month,
					dayOfMonth, year, ServiceProps.APPLICANT_JOB_TITLE, groupIds, organizationIds, roleIds,
					userGroupIds, sendEmail, context);
			// _log.info("MAPPING USER: " + mappingUser.getLastName() + "," +
			// mappingUser.getFullName());
			// mappingUser.setStatus(WorkflowConstants.STATUS_APPROVED);
			userLocalService.updateStatus(mappingUser.getUserId(), WorkflowConstants.STATUS_APPROVED, context);
			//

			long mappingUserId = mappingUser.getUserId();

			// Add audit field
			applicant.setCreateDate(now);
			applicant.setModifiedDate(now);
			applicant.setCompanyId(context.getCompanyId());
			applicant.setUserId(context.getUserId());
			applicant.setUserName(auditUser.getFullName());
			applicant.setGroupId(groupId);

			applicant.setApplicantName(applicantName);
			applicant.setApplicantIdType(applicantIdType);
			applicant.setApplicantIdNo(applicantIdNo);
			if (Validator.isNotNull(applicantIdDate))
				applicant.setApplicantIdDate(applicantIdDate);
			applicant.setContactTelNo(contactTelNo);
			applicant.setContactEmail(contactEmail);
			applicant.setMappingUserId(mappingUserId);
			applicant.setActivationCode(activationCode);
			applicant.setTmpPass(secretCode);

		} else {
			applicant = applicantPersistence.fetchByPrimaryKey(applicantId);

			applicant.setModifiedDate(now);
			applicant.setUserId(context.getUserId());
			applicant.setUserName(auditUser.getFullName());

			if (Validator.isNotNull(applicantName))
				applicant.setApplicantName(applicantName);

			if (Validator.isNotNull(applicantIdType))
				applicant.setApplicantIdType(applicantIdType);

			if (Validator.isNotNull(applicantIdNo))
				applicant.setApplicantIdNo(applicantIdNo);

			if (Validator.isNotNull(applicantIdDate))
				applicant.setApplicantIdDate(applicantIdDate);

			if (Validator.isNotNull(contactTelNo))
				applicant.setContactTelNo(contactTelNo);

			if (Validator.isNotNull(contactEmail))
				applicant.setContactEmail(contactEmail);
		}

		return applicantPersistence.update(applicant);
	}

	@Indexable(type = IndexableType.REINDEX)
	public Applicant updateApplicantApproved(long groupId, long userId, long applicantId, String applicantIdNo,
			String applicantName, String applicantIdType, Date applicantIdDate, String contactEmail,
			String contactTelNo, String secrectKey, ServiceContext context) throws PortalException {

		Date now = new Date();
		User auditUser = userPersistence.fetchByPrimaryKey(userId);
		Applicant applicant = null;
		if (applicantId == 0) {
			validateAdd(applicantName, applicantIdType, applicantIdNo, applicantIdDate != null ? String.valueOf(applicantIdDate.getTime()) : StringPool.BLANK);
			validateApplicantDuplicate(groupId, context.getCompanyId(), contactTelNo, applicantIdNo, contactEmail);

			applicantId = counterLocalService.increment(Applicant.class.getName());
			applicant = applicantPersistence.create(applicantId);

			Role roleDefault = RoleLocalServiceUtil.getRole(auditUser.getCompanyId(), ServiceProps.APPLICANT_ROLE_NAME);

			String activationCode = StringPool.BLANK;

			boolean autoPassword = false;
			boolean autoScreenName = true;
			boolean sendEmail = false;

			long[] groupIds = new long[] { groupId };
			long[] organizationIds = null;
			long[] roleIds = null;
			long[] userGroupIds = null;

			String screenName = null;

			String firstName = (ApplicantTerm.APPLICANTIDTYPE_CITIZEN.equals(applicantIdType) ? MessageUtil.getMessage(
					ConfigConstants.HEADER_USER)
					: (ApplicantTerm.APPLICANTIDTYPE_BUSINESS.equals(applicantIdType) ? MessageUtil.getMessage(
							ConfigConstants.HEADER_COMPANY) : MessageUtil.getMessage(
									ConfigConstants.HEADER_BUSSINESS)));
			String lastName = applicantName;

			UserMgtUtils.SplitName spn = UserMgtUtils.splitName(firstName, lastName);

			// add default role
			if (Validator.isNotNull(roleDefault)) {
				roleIds = new long[] { roleDefault.getRoleId() };
			}

			Role adminRole = RoleLocalServiceUtil.getRole(auditUser.getCompanyId(), ServiceProps.ADM_ROLE_NAME);
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
			// _log.info("CREATE APPLICANT: " + spn.getLastName() + "," +
			// spn.getFirstName() + "," + spn.getMidName());
			User mappingUser = userLocalService.addUserWithWorkflow(creatorUserId, auditUser.getCompanyId(),
					autoPassword, secrectKey, secrectKey, autoScreenName, screenName, contactEmail, 0l, StringPool.BLANK,
					LocaleUtil.getDefault(), spn.getFirstName(), spn.getMidName(), spn.getLastName(), 0, 0, true, month,
					dayOfMonth, year, ServiceProps.APPLICANT_JOB_TITLE, groupIds, organizationIds, roleIds,
					userGroupIds, sendEmail, context);
			// mappingUser.getFullName());
			userLocalService.updateStatus(mappingUser.getUserId(), WorkflowConstants.STATUS_APPROVED, context);
			//

			long mappingUserId = mappingUser.getUserId();

			// Add audit field
			applicant.setCreateDate(now);
			applicant.setModifiedDate(now);
			applicant.setCompanyId(context.getCompanyId());
			applicant.setUserId(context.getUserId());
			applicant.setUserName(auditUser.getFullName());
			applicant.setGroupId(groupId);

			applicant.setApplicantName(applicantName);
			applicant.setApplicantIdType(applicantIdType);
			applicant.setApplicantIdNo(applicantIdNo);
			if (Validator.isNotNull(applicantIdDate))
				applicant.setApplicantIdDate(applicantIdDate);
			applicant.setContactTelNo(contactTelNo);
			applicant.setContactEmail(contactEmail);
			applicant.setMappingUserId(mappingUserId);
			applicant.setActivationCode(activationCode);
			applicant.setTmpPass(secrectKey);

		} else {
			applicant = applicantPersistence.fetchByPrimaryKey(applicantId);

			applicant.setModifiedDate(now);
			applicant.setUserId(context.getUserId());
			applicant.setUserName(auditUser.getFullName());

			if (Validator.isNotNull(applicantName))
				applicant.setApplicantName(applicantName);

			if (Validator.isNotNull(applicantIdType))
				applicant.setApplicantIdType(applicantIdType);

			if (Validator.isNotNull(applicantIdNo))
				applicant.setApplicantIdNo(applicantIdNo);

			if (Validator.isNotNull(applicantIdDate))
				applicant.setApplicantIdDate(applicantIdDate);

			if (Validator.isNotNull(contactTelNo))
				applicant.setContactTelNo(contactTelNo);

			if (Validator.isNotNull(contactEmail))
				applicant.setContactEmail(contactEmail);
		}

		return applicantPersistence.update(applicant);
	}

	public List<Applicant> findByAppIds(String applicantIdNo) {

		return applicantPersistence.findByF_APLC_IDS(applicantIdNo);
	}

	public List<Applicant> findByContactEmailList(String contactEmail) {

		return applicantPersistence.findByF_EMAIL(contactEmail);
	}
	
	public Applicant fetchByF_GID_MCN_MCPK(long groupId, String mappingClassName, String mappingClassPK) {
		return applicantPersistence.fetchByF_GID_MCN_MCPK(groupId, mappingClassName, mappingClassPK);
	}
	
	public Applicant findByF_GID_MCN_MCPK(long groupId, String mappingClassName, String mappingClassPK) throws NoSuchApplicantException {
		return applicantPersistence.findByF_GID_MCN_MCPK(groupId, mappingClassName, mappingClassPK);
	}

	@Indexable(type = IndexableType.REINDEX)
	public Applicant verifyApplicant(long applicantId) throws PortalException {

		Applicant applicant = applicantLocalService.fetchApplicant(applicantId);

		applicant.setVerification(ApplicantTerm.UNLOCKED);

		applicantPersistence.update(applicant);

		return applicant;
	}

	@Indexable(type = IndexableType.REINDEX)
	public Applicant verifyApplicantWithValue(long applicantId, int verification) throws PortalException {

		Applicant applicant = applicantLocalService.fetchApplicant(applicantId);

		applicant.setVerification(verification);

		applicantPersistence.update(applicant);

		return applicant;
	}

	public Applicant fetchByF_GID_CTEM(long groupId, String email) {
		return applicantPersistence.fetchByF_GID_CTEM(groupId, email);
	}
	// private Log _log =
	// LogFactoryUtil.getLog(ApplicantLocalServiceImpl.class);

}
