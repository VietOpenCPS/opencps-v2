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

import aQute.bnd.annotation.ProviderType;

import java.util.Date;
import java.util.LinkedHashMap;

import org.opencps.dossiermgt.constants.ProcessOptionTerm;
import org.opencps.dossiermgt.constants.ServiceConfigTerm;
import org.opencps.dossiermgt.constants.ServiceInfoTerm;
import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.service.base.ProcessOptionLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.PortalException;
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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

/**
 * The implementation of the process option local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.ProcessOptionLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see ProcessOptionLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.ProcessOptionLocalServiceUtil
 */
@ProviderType
public class ProcessOptionLocalServiceImpl extends ProcessOptionLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.dossiermgt.service.ProcessOptionLocalServiceUtil} to access
	 * the process option local service.
	 */
	@Indexable(type = IndexableType.DELETE)
	public ProcessOption removeProcessOption(long processOptionId) throws PortalException {

		validateRemove(processOptionId);

		ProcessOption option = processOptionPersistence.fetchByPrimaryKey(processOptionId);

		processOptionPersistence.remove(option);

		return option;
	}

	public ProcessOption updateProcessOption(long groupId, long processOptionId, long serviceConfigId, int seqOrder,
			String autoSelect, String instructionNote, String submissionNote, long dossierTemplateId,
			long serviceProcessId, ServiceContext context) throws PortalException {

		validateAdd(processOptionId, seqOrder, autoSelect, instructionNote, submissionNote);

		ProcessOption processOption = null;

		Date now = new Date();

		User auditUser = userPersistence.fetchByPrimaryKey(context.getUserId());

		if (processOptionId == 0) {
			processOptionId = counterLocalService.increment(ProcessOption.class.getName());

			processOption = processOptionPersistence.fetchByPrimaryKey(processOptionId);

			processOption.setCreateDate(now);
			processOption.setModifiedDate(now);
			processOption.setCompanyId(context.getCompanyId());
			processOption.setGroupId(groupId);
			processOption.setUserId(context.getUserId());
			processOption.setUserName(auditUser.getFullName());

			processOption.setServiceConfigId(serviceConfigId);
			processOption.setOptionOrder(seqOrder);
			processOption.setAutoSelect(autoSelect);
			processOption.setInstructionNote(instructionNote);
			processOption.setSubmissionNote(submissionNote);
			processOption.setDossierTemplateId(dossierTemplateId);
			processOption.setServiceProcessId(serviceProcessId);

		} else {

			processOption = processOptionPersistence.fetchByPrimaryKey(processOptionId);

			processOption.setModifiedDate(now);
			processOption.setUserId(context.getUserId());
			processOption.setUserName(auditUser.getFullName());

			processOption.setServiceConfigId(serviceConfigId);
			processOption.setOptionOrder(seqOrder);
			processOption.setAutoSelect(autoSelect);
			processOption.setInstructionNote(instructionNote);
			processOption.setSubmissionNote(submissionNote);
			processOption.setDossierTemplateId(dossierTemplateId);
			processOption.setServiceProcessId(serviceProcessId);

		}

		processOptionPersistence.update(processOption);

		return processOption;
	}

	public static final String CLASS_NAME = ProcessOption.class.getName();

	public Hits searchLucene(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);

		Indexer<ProcessOption> indexer = IndexerRegistryUtil.nullSafeGetIndexer(ProcessOption.class);

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

			String[] keyword = keywords.split(StringPool.SPACE);

			for (String string : keyword) {

				MultiMatchQuery query = new MultiMatchQuery(string);

				query.addFields(ProcessOptionTerm.INSTRUCTION_NOTE, ProcessOptionTerm.SUBMISSION_NOTE);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		// Extra fields
		String configId = GetterUtil.getString(params.get(ProcessOptionTerm.SERVICE_CONFIG_ID));
		String applicantType = GetterUtil.getString(params.get(ProcessOptionTerm.APPLICATION_TYPE));

		if (Validator.isNotNull(configId)) {
			MultiMatchQuery query = new MultiMatchQuery(configId);

			query.addFields(ProcessOptionTerm.SERVICE_CONFIG_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(applicantType)) {
			MultiMatchQuery query = new MultiMatchQuery(applicantType);

			query.addFields(ProcessOptionTerm.APPLICATION_TYPE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);
	}

	public long countLucene(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);

		Indexer<ProcessOption> indexer = IndexerRegistryUtil.nullSafeGetIndexer(ProcessOption.class);

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

			String[] keyword = keywords.split(StringPool.SPACE);

			for (String string : keyword) {

				MultiMatchQuery query = new MultiMatchQuery(string);

				query.addFields(ProcessOptionTerm.INSTRUCTION_NOTE, ProcessOptionTerm.SUBMISSION_NOTE);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		// Extra fields
		String configId = GetterUtil.getString(params.get(ProcessOptionTerm.SERVICE_CONFIG_ID));
		String applicantType = GetterUtil.getString(params.get(ProcessOptionTerm.APPLICATION_TYPE));

		if (Validator.isNotNull(configId)) {
			MultiMatchQuery query = new MultiMatchQuery(configId);

			query.addFields(ProcessOptionTerm.SERVICE_CONFIG_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(applicantType)) {
			MultiMatchQuery query = new MultiMatchQuery(applicantType);

			query.addFields(ProcessOptionTerm.APPLICATION_TYPE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);
	}

	private void validateAdd(long processOptionId, int seqOrder, String autoSelect, String instructionNote,
			String submissionNote) throws PortalException {
		// TODO add more business logic here
	}

	private void validateRemove(long processOptionId) throws PortalException {
		// TODO add more business logic here
	}
}