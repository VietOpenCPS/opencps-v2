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
import java.util.regex.Pattern;

import org.opencps.auth.utils.APIDateTimeUtils;
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
import com.liferay.portal.kernel.search.TermQuery;
import com.liferay.portal.kernel.search.TermQueryFactoryUtil;
import com.liferay.portal.kernel.search.generic.MultiMatchQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
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
	public Registration insert(long groupId, long companyId, String applicantName, String applicantIdType, String applicantIdNo,
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

		actionForm.addRegistrationFormbaseonRegTemplate(groupId, companyId, registrationId, govAgencyCode, serviceContext);

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
	
	public Registration getLastSubmittingByUser(long groupId, long userId, boolean submitting) {
		return registrationPersistence.fetchByF_USERID_SUBMITTING_First(groupId, userId, true, null);
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

		// Search elastic
//		String pattern = "thiet_bi_san_xuat_chinh = ?";
//		String paramValues = "11111";
//		String paramTypes = "String";
		String pattern = String.valueOf(params.get("pattern"));
		String paramValues = String.valueOf(params.get("paramValues"));
		String paramTypes = String.valueOf(params.get("paramTypes"));
		//Query elastic
		LuceneQuery( pattern, paramValues, paramTypes, searchContext);

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

		// Add params query
		int count = 0;
		for (BooleanQuery boolQuery : _subQueries) {
			if (count == 0) {
				booleanQuery.add(boolQuery, BooleanClauseOccur.MUST);
			} else {
				booleanQuery.add(boolQuery, _occurs.get(count - 1));
			}
			count++;
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
	                Date idDate = APIDateTimeUtils.convertStringToDate(applicantIdDate, APIDateTimeUtils._TIMESTAMP);
	                
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
                    Date idDate = APIDateTimeUtils.convertStringToDate(applicantIdDate, APIDateTimeUtils._TIMESTAMP);
                    
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

	/**
	 * Get registration of applicant has registrationState in use
	 * 
	 */
	public Registration getByApplicantAndAgency(long groupId, String applicantNo, String agencyNo) {
	    List<Registration> registrations = registrationPersistence.findByG_APPNO_GOVCODE(
	        groupId, applicantNo, agencyNo, 2);
	    
		if(registrations.size() > 0) {
		    return registrations.get(0);
		} else {
		    return null;
		}
	}

	private Log _log = LogFactoryUtil.getLog(RegistrationLocalServiceImpl.class);

	/**
	 * @param pattern
	 * @return
	 */
	protected static List<String> getSplitIndex(String pattern) {
		List<String> splitIndexs = new ArrayList<String>();
		int eliminateParenthesis = 0;
		int startIndex = 0;
		int endIndex = 0;

		for (int i = 0; i < pattern.length(); i++) {

			Character c = pattern.charAt(i);

			if (c.toString().equals(StringPool.OPEN_PARENTHESIS)) {
				eliminateParenthesis += 1;
			} else if (c.toString().equals(StringPool.CLOSE_PARENTHESIS)) {
				eliminateParenthesis += -1;
			}

			if (eliminateParenthesis == 1
					&& c.toString().equals(StringPool.OPEN_PARENTHESIS)) {
				startIndex = i;
			}

			if (eliminateParenthesis == 0
					&& c.toString().equals(StringPool.CLOSE_PARENTHESIS)) {
				endIndex = i;

			}

			if (!splitIndexs.contains(startIndex + StringPool.DASH + endIndex)
					&& startIndex < endIndex) {

				splitIndexs.add(startIndex + StringPool.DASH + endIndex);
			}
		}

		return splitIndexs;
	}

	/**
	 * @param pattern
	 * @param subQueries
	 * @return
	 * @throws ParseException
	 */
	public static List<String> getSubQueries(String pattern,
			List<String> subQueries) throws ParseException {

		pattern = validPattern(pattern);

		// if (Validator.isNull(pattern)) {
		// return null;
		// }

		List<String> splitIndexs = getSplitIndex(pattern);

		if (splitIndexs != null) {
			if (splitIndexs.isEmpty()) {
				subQueries.add(pattern);
			} else {
				for (String splitIndex : splitIndexs) {

					int[] splitIndexsTemp = StringUtil.split(splitIndex,
							StringPool.DASH, 0);
					String subQuery = pattern.substring(splitIndexsTemp[0],
							splitIndexsTemp[1] + 1);
					if (subQuery.contains("[and]") || subQuery.contains("[or]")
							|| subQuery.contains("[not]")) {
						getSubQueries(subQuery, subQueries);
					} else {
						subQuery = subQuery.replaceAll("\\(", StringPool.BLANK);

						subQuery = subQuery.replaceAll("\\)", StringPool.BLANK);

						subQueries.add(subQuery);

					}
				}
			}

		}

		return subQueries;
	}
	
	
	/**
	 * @param pattern
	 * @return
	 */
	public static String validPattern(String pattern) {
		int eliminateParenthesis = 0;
		int startParenthesisIndex = 0;
		int endParenthesisIndex = 0;
		// pattern = pattern.trim().toLowerCase();
		for (int i = 0; i < pattern.length(); i++) {

			Character c = pattern.charAt(i);

			if (c.toString().equals(StringPool.OPEN_PARENTHESIS)) {
				eliminateParenthesis += 1;
			} else if (c.toString().equals(StringPool.CLOSE_PARENTHESIS)) {
				eliminateParenthesis += -1;
			}

			if (eliminateParenthesis == 1
					&& c.toString().equals(StringPool.OPEN_PARENTHESIS)) {
				startParenthesisIndex = i;
			}

			if (eliminateParenthesis == 0
					&& c.toString().equals(StringPool.CLOSE_PARENTHESIS)) {
				endParenthesisIndex = i;
			}

		}

		if (eliminateParenthesis != 0) {
			return StringPool.BLANK;
		}

		if (endParenthesisIndex == pattern.length() - 1
				&& startParenthesisIndex == 0) {
			pattern = pattern.substring(startParenthesisIndex + 1,
					endParenthesisIndex);

			pattern = validPattern(pattern);

		}

		return pattern;
	}

	/////////////
	public void LuceneQuery(String pattern, String paramValues, String paramTypes,
			SearchContext searchContext) {

		BooleanQuery query = BooleanQueryFactoryUtil.create(searchContext);
		List<String> subPatterns = new ArrayList<String>();
		List<String> paramNames = new ArrayList<String>();
		List<BooleanClauseOccur> occurs = null;
		List<BooleanQuery> subQueries = null;
		List<Object> params = new ArrayList<Object>();
		List<Class<?>> clazzs = new ArrayList<Class<?>>();

		String[] arrParamValue = Validator.isNotNull(paramValues) ? StringUtil
				.split(paramValues, StringPool.POUND) : null;
		String[] arrParamTypes = Validator.isNotNull(paramTypes) ? StringUtil
				.split(paramTypes) : null;

		if (arrParamValue != null && arrParamTypes != null
				&& arrParamTypes.length > 0 && arrParamValue.length > 0
				&& arrParamValue.length == arrParamTypes.length) {
			try {
//				pattern = LuceneQueryUtil.validPattern(pattern);

				if (Validator.isNull(pattern)) {
					throw new Exception();
				}

				for (int i = 0; i < arrParamValue.length; i++) {
					String paramType = arrParamTypes[i].toLowerCase();
					Object param = null;
					Class<?> clazz = null;
					switch (paramType) {
					case "long":
						param = GetterUtil.getLong(arrParamValue[i]);
						clazz = long.class;
						break;
					case "integer":
						param = GetterUtil.getInteger(arrParamValue[i]);
						clazz = int.class;
						break;
					case "int":
						param = GetterUtil.getInteger(arrParamValue[i]);
						clazz = int.class;
						break;
					case "short":
						param = GetterUtil.getShort(arrParamValue[i]);
						clazz = short.class;
						break;
					case "double":
						param = GetterUtil.getDouble(arrParamValue[i]);
						clazz = double.class;
						break;
					case "float":
						param = GetterUtil.getFloat(arrParamValue[i]);
						clazz = float.class;
						break;
					case "boolean":
						param = GetterUtil.getBoolean(arrParamValue[i]);
						clazz = boolean.class;
						break;
					case "date":
//						param = DateTimeUtil
//								.convertStringToDate(arrParamValue[i]);
						clazz = Date.class;
						break;
					case "string":
						param = GetterUtil.getString(arrParamValue[i]);
						clazz = String.class;
						break;
					case "null":
						param = null;
						clazz = null;
						break;
					case "":
						param = null;
						clazz = null;
						break;
					case " ":
						param = null;
						clazz = null;
						break;
					default:
						break;
					}

					params.add(param);
					clazzs.add(clazz);
				}

				getSubQueries(pattern, subPatterns);

				if (subPatterns != null && !subPatterns.isEmpty()) {
					subQueries = createBooleanQueries(
							subPatterns, params, paramNames, searchContext);

					occurs = getBooleanClauseOccurs(pattern,
							subPatterns);

					if (subQueries.size() - 1 != occurs.size()) {
						throw new Exception();
					}
					int count = 0;
					for (BooleanQuery booleanQuery : subQueries) {
						if (count == 0) {
							query.add(booleanQuery, BooleanClauseOccur.MUST);
						} else {
							query.add(booleanQuery, occurs.get(count - 1));
						}

						count++;
					}
				}

			} catch (Exception e) {
				try {
					throw new Exception();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} finally {
				this.setOccurs(occurs);
				this.setParams(params);
				this.setPattern(pattern);
				this.setQuery(query);
				this.setSubPatterns(subPatterns);
				this.setSubQueries(subQueries);
				this.setSearchContext(searchContext);
				this.setParamNames(paramNames);
				this.setParamTypes(clazzs);
			}
		} else {
			//TODO
		}

	}
	private SearchContext _searchContext;
	private String _pattern;
	private BooleanQuery _query;
	private List<BooleanQuery> _subQueries;
	private List<String> _subPatterns;
	private List<String> _paramNames;
	private List<Object> _params;
	private List<BooleanClauseOccur> _occurs;
	private List<Class<?>> _paramTypes;

	public List<Class<?>> getParamTypes() {
		return _paramTypes;
	}

	public void setParamTypes(List<Class<?>> paramTypes) {
		this._paramTypes = paramTypes;
	}

	public SearchContext getSearchContext() {
		return _searchContext;
	}

	public void setSearchContext(SearchContext searchContext) {
		this._searchContext = searchContext;
	}

	public String getPattern() {
		return _pattern;
	}

	public void setPattern(String pattern) {
		this._pattern = pattern;
	}

	public BooleanQuery getQuery() {
		return _query;
	}

	public void setQuery(BooleanQuery query) {
		this._query = query;
	}

	public List<BooleanQuery> getSubQueries() {
		return _subQueries;
	}

	public void setSubQueries(List<BooleanQuery> subQueries) {
		this._subQueries = subQueries;
	}

	public List<String> getSubPatterns() {
		return _subPatterns;
	}

	public void setSubPatterns(List<String> subPatterns) {
		this._subPatterns = subPatterns;
	}

	public List<String> getParamNames() {
		return _paramNames;
	}

	public void setParamNames(List<String> paramNames) {
		this._paramNames = paramNames;
	}

	public List<Object> getParams() {
		return _params;
	}

	public void setParams(List<Object> params) {
		this._params = params;
	}

	public List<BooleanClauseOccur> getOccurs() {
		return _occurs;
	}

	public void setOccurs(List<BooleanClauseOccur> occurs) {
		this._occurs = occurs;
	}
	
	public static List<BooleanClauseOccur> getBooleanClauseOccurs(
			String pattern, List<String> subQueries) {
		List<BooleanClauseOccur> booleanClauseOccurs = new ArrayList<BooleanClauseOccur>();
		pattern = pattern.replaceAll(Pattern.quote("("), StringPool.BLANK);

		pattern = pattern.replaceAll("\\)", StringPool.BLANK);

		pattern = pattern.replaceAll(StringPool.SPACE, StringPool.BLANK);
		for (String subQuery : subQueries) {
			subQuery = subQuery.replaceAll(StringPool.SPACE, StringPool.BLANK);
			pattern = pattern.replace(subQuery, StringPool.BLANK);
		}

		pattern = pattern.replaceAll("\\]\\[", StringPool.COMMA);

		pattern = pattern.replaceAll("\\[", StringPool.BLANK);

		pattern = pattern.replaceAll("\\]", StringPool.BLANK);

		String[] conditions = StringUtil.split(pattern);

		if (conditions != null && conditions.length > 0) {
			for (int c = 0; c < conditions.length; c++) {
				if (conditions[c].equalsIgnoreCase("and")) {
					booleanClauseOccurs.add(BooleanClauseOccur.MUST);
				} else if (conditions[c].equalsIgnoreCase("or")) {
					booleanClauseOccurs.add(BooleanClauseOccur.SHOULD);
				} else if (conditions[c].equalsIgnoreCase("not")) {
					booleanClauseOccurs.add(BooleanClauseOccur.MUST_NOT);
				}
			}
		}

		return booleanClauseOccurs;
	}
	
	
	public static List<BooleanQuery> createBooleanQueries(
			List<String> subQueries, List<Object> params,
			List<String> paramNames, SearchContext searchContext)
			throws ParseException {
		List<BooleanQuery> booleanQueries = new ArrayList<BooleanQuery>();
		if (subQueries != null) {
			for (String subQuery : subQueries) {
				String[] terms = StringUtil.split(subQuery);
				if (terms != null && terms.length > 0) {
					BooleanQuery query = BooleanQueryFactoryUtil
							.create(searchContext);
					for (int t = 0; t < terms.length; t++) {
						int paramPossition = subQueries.indexOf(subQuery)
								* terms.length + t;
						// String term = terms[t].trim().toLowerCase();
						String term = terms[t].trim();
						String key = StringPool.BLANK;
						if (term.contains((StringPool.EQUAL.toLowerCase()))) {
							key = term
									.substring(
											0,
											term.indexOf(StringPool.EQUAL
													.toLowerCase())).trim();
							// addExactTerm(query, key,
							// params.get(paramPossition));

							TermQuery termQuery = null;

							Object tempValue = params.get(paramPossition);

							if (tempValue instanceof Long) {
								termQuery = TermQueryFactoryUtil.create(
										searchContext, key, (long) tempValue);
							} else {
								termQuery = TermQueryFactoryUtil.create(
										searchContext, key,
										String.valueOf(tempValue));
							}

							if (termQuery != null) {
								query.add(termQuery, BooleanClauseOccur.MUST);
							}
						} else if (term.contains(StringPool.LIKE.toLowerCase())) {
							key = term
									.substring(
											0,
											term.indexOf(StringPool.LIKE
													.toLowerCase())).trim();

							query.addTerm(key, params.get(paramPossition)
									.toString(), true);

						} else if (term.contains(StringPool.BETWEEN
								.toLowerCase())) {
							key = term.substring(
									0,
									term.indexOf(StringPool.BETWEEN
											.toLowerCase())).trim();
//							query = addRangeTerm(query, key,
//									params.get(paramPossition));
						}

						if (Validator.isNotNull(key)) {
							paramNames.add(key);
						}

					}

					booleanQueries.add(query);
				}
			}
		}
		return booleanQueries;
	}
}