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

import com.liferay.petra.string.StringPool;
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
import com.liferay.portal.kernel.search.generic.WildcardQueryImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.LinkedHashMap;

import org.opencps.dossiermgt.constants.EFormTerm;
import org.opencps.dossiermgt.model.EForm;
import org.opencps.dossiermgt.service.base.EFormLocalServiceBaseImpl;

/**
 * The implementation of the e form local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link org.opencps.dossiermgt.service.EFormLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see EFormLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.EFormLocalServiceUtil
 */
public class EFormLocalServiceImpl extends EFormLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link org.opencps.dossiermgt.service.EFormLocalServiceUtil} to access the e form local service.
	 */

	public static final String CLASS_NAME = EForm.class.getName();

	public Hits searchLucene(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);
		// Extra fields
		//String eformNo = GetterUtil.getString(params.get(EFormTerm.EFORM_NO));
		String serviceCode = GetterUtil.getString(params.get(EFormTerm.SERVICE_CODE));
		//String state = String.valueOf((params.get(EFormTerm.STATE)));

		Indexer<EForm> indexer = IndexerRegistryUtil.nullSafeGetIndexer(EForm.class);

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
			BooleanQuery queryBool = new BooleanQueryImpl();
			String[] subQuerieArr = new String[] { EFormTerm.EFORM_NO_SEARCH, EFormTerm.SERVICE_CODE_SEARCH};

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

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(serviceCode)) {
			MultiMatchQuery query = new MultiMatchQuery(serviceCode);

			query.addFields(EFormTerm.SERVICE_CODE_SEARCH);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

//		if (Validator.isNotNull(state)) {
//			String[] stateArr = StringUtil.split(state);
//
//			if (stateArr != null && stateArr.length > 0) {
//				BooleanQuery subQuery = new BooleanQueryImpl();
//				for (int i = 0; i < stateArr.length; i++) {
//					MultiMatchQuery query = new MultiMatchQuery(stateArr[i]);
//					query.addField(EFormTerm.STATE);
//					subQuery.add(query, BooleanClauseOccur.SHOULD);
//				}
//				booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
//			} else {
//				MultiMatchQuery query = new MultiMatchQuery(state);
//				query.addFields(EFormTerm.STATE);
//				booleanQuery.add(query, BooleanClauseOccur.MUST);
//			}
//		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);
	}

	public long countLucene(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);
		String serviceCode = GetterUtil.getString(params.get(EFormTerm.SERVICE_CODE));
		//String state = String.valueOf((params.get(EFormTerm.STATE)));

		Indexer<EForm> indexer = IndexerRegistryUtil.nullSafeGetIndexer(EForm.class);

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
			BooleanQuery queryBool = new BooleanQueryImpl();
			String[] subQuerieArr = new String[] { EFormTerm.EFORM_NO_SEARCH, EFormTerm.SERVICE_CODE_SEARCH};

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

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(serviceCode)) {
			MultiMatchQuery query = new MultiMatchQuery(serviceCode);

			query.addFields(EFormTerm.SERVICE_CODE_SEARCH);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

//		if (Validator.isNotNull(state)) {
//			String[] stateArr = StringUtil.split(state);
//
//			if (stateArr != null && stateArr.length > 0) {
//				BooleanQuery subQuery = new BooleanQueryImpl();
//				for (int i = 0; i < stateArr.length; i++) {
//					MultiMatchQuery query = new MultiMatchQuery(stateArr[i]);
//					query.addField(EFormTerm.STATE);
//					subQuery.add(query, BooleanClauseOccur.SHOULD);
//				}
//				booleanQuery.add(subQuery, BooleanClauseOccur.MUST);
//			} else {
//				MultiMatchQuery query = new MultiMatchQuery(state);
//				query.addFields(EFormTerm.STATE);
//				booleanQuery.add(query, BooleanClauseOccur.MUST);
//			}
//		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);
	}

	public EForm getByEFormNo(long groupId, String eFormNo) {
		return eFormPersistence.fetchByF_GID_FORM(groupId, eFormNo);
	}

	@Indexable(type=IndexableType.REINDEX)
	public EForm updateEForm(long userId, long groupId, long eFormId, String eFormNo, String serviceCode,
			String fileTemplateNo, String eFormName, long formScriptFileId, long formReportFileId, String eFormData,
			String email, String secret, ServiceContext serviceContext) {

		Date now = new Date();

		if (eFormId > 0) {
			EForm eform = eFormPersistence.fetchByPrimaryKey(eFormId);
			//
			eform.setModifiedDate(now);

			if (Validator.isNotNull(eFormNo))
				eform.setEFormNo(eFormNo);
			if (Validator.isNotNull(serviceCode))
				eform.setServiceCode(serviceCode);
			if (Validator.isNotNull(fileTemplateNo))
				eform.setFileTemplateNo(fileTemplateNo);
			if (Validator.isNotNull(eFormName))
				eform.setEFormName(eFormName);
			if (Validator.isNotNull(formScriptFileId))
				eform.setFormScriptFileId(formScriptFileId);
			if (Validator.isNotNull(formReportFileId))
				eform.setFormReportFileId(formReportFileId);
			if (Validator.isNotNull(eFormData))
				eform.setEFormData(eFormData);
			if (Validator.isNotNull(email))
				eform.setEmail(email);
			if (Validator.isNotNull(secret))
				eform.setSecret(secret);
//			if (Validator.isNotNull(checkinDate))
//				eform.setCheckinDate(checkinDate);
//			if (Validator.isNotNull(gateNumber))
//				eform.setGateNumber(gateNumber);
//			if (Validator.isNotNull(state))
//				eform.setState(state);
			//
			return eFormPersistence.update(eform);
		} else {
			eFormId = counterLocalService.increment(EForm.class.getName());
			EForm eform = eFormPersistence.create(eFormId);
			//
			eform.setCreateDate(now);
			eform.setModifiedDate(now);
			eform.setCompanyId(serviceContext.getCompanyId());
			eform.setGroupId(groupId);
			eform.setUserId(userId);
			//
			eform.setEFormNo(eFormNo);
			eform.setServiceCode(serviceCode);
			eform.setFileTemplateNo(fileTemplateNo);
			eform.setEFormName(eFormName);
			eform.setFormScriptFileId(formScriptFileId);
			eform.setFormReportFileId(formReportFileId);
			eform.setEFormData(eFormData);
			eform.setEmail(email);
			eform.setSecret(secret);
//			eform.setCheckinDate(checkinDate);
//			eform.setGateNumber(gateNumber);
//			eform.setState(state);

			return eFormPersistence.update(eform);
		}
	}

	@Indexable(type=IndexableType.REINDEX)
	public EForm updateDataByEFormNo(long eFormId, String eFormData, ServiceContext serviceContext) {

		EForm eform = eFormPersistence.fetchByPrimaryKey(eFormId);
		if (eform != null) {
			eform.setEFormData(eFormData);
			eFormPersistence.update(eform);
		}
		return eform;
	}

}