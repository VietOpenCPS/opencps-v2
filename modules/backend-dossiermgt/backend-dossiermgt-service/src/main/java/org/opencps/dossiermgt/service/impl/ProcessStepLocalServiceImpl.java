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

import org.opencps.dossiermgt.constants.ProcessStepTerm;
import org.opencps.dossiermgt.exception.DossierURLException;
import org.opencps.dossiermgt.exception.DuplicateStepNoException;
import org.opencps.dossiermgt.exception.HasChildrenException;
import org.opencps.dossiermgt.exception.RequiredStepNoException;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.model.ProcessStepRole;
import org.opencps.dossiermgt.service.base.ProcessStepLocalServiceBaseImpl;

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

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the process step local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.ProcessStepLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see ProcessStepLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.ProcessStepLocalServiceUtil
 */
@ProviderType
public class ProcessStepLocalServiceImpl extends ProcessStepLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.dossiermgt.service.ProcessStepLocalServiceUtil} to access the
	 * process step local service.
	 */
	
	public List<ProcessStep> getByStatusAnsSubStatus(String dossierStatus, String dossierSubStatus, long groupId) {
		return processStepPersistence.findByDST_DSST(dossierStatus, dossierSubStatus, groupId);
	}

	public List<ProcessStep> getBySC_SPID(String stepCode, long serviceProcessId) throws PortalException {
		return processStepPersistence.findBySC_SPID(stepCode, serviceProcessId);
	}

	@Indexable(type = IndexableType.REINDEX)
	public ProcessStep updateProcessStep(long groupId, long processStepId, String stepCode, String stepName,
			long serviceProcessId, String sequenceNo, String dossierStatus, String dossierSubStatus, int durationCount,
			String customProcessUrl, String stepInstruction, String briefNote, boolean editable, String lockState,
			ServiceContext context) throws PortalException {

		Date now = new Date();

		User userAction = userLocalService.getUser(context.getUserId());

		ProcessStep object = null;

		validateAdd(groupId, processStepId, stepCode, serviceProcessId, sequenceNo, dossierStatus, dossierSubStatus,
				customProcessUrl);

		if (processStepId == 0) {

			processStepId = counterLocalService.increment(ProcessStep.class.getName());

			object = processStepLocalService.createProcessStep(processStepId);

			// Add audit fields
			object.setCompanyId(context.getCompanyId());
			object.setGroupId(groupId);
			object.setCreateDate(now);
			object.setModifiedDate(now);
			object.setUserId(userAction.getUserId());
			object.setUserName(userAction.getFullName());

			// Add other fields

			object.setStepCode(stepCode);
			object.setServiceProcessId(serviceProcessId);
			object.setStepName(stepName);
			object.setSequenceNo(sequenceNo);
			object.setDossierStatus(dossierStatus);
			object.setDossierSubStatus(dossierSubStatus);
			object.setDurationCount(durationCount);
			object.setCustomProcessUrl(customProcessUrl);
			object.setStepInstruction(stepInstruction);
			object.setBriefNote(briefNote);
			object.setEditable(editable);
			object.setLockState(lockState);

		} else {
			validateUpdate(groupId, processStepId, stepCode, serviceProcessId, sequenceNo, dossierStatus,
					dossierSubStatus, customProcessUrl);

			object = processStepPersistence.findByPrimaryKey(processStepId);

			// Add audit fields
			object.setModifiedDate(now);
			object.setUserId(userAction.getUserId());
			object.setUserName(userAction.getFullName());

			// Add other fields

			object.setStepCode(stepCode);
			// object.setServiceProcessId(serviceProcessId);
			object.setStepName(stepName);
			object.setSequenceNo(sequenceNo);
			object.setDossierStatus(dossierStatus);
			object.setDossierSubStatus(dossierSubStatus);
			object.setDurationCount(durationCount);
			object.setCustomProcessUrl(customProcessUrl);
			object.setStepInstruction(stepInstruction);
			object.setBriefNote(briefNote);
			object.setEditable(editable);
			object.setLockState(lockState);
		}

		processStepPersistence.update(object);

		return object;
	}

	@Indexable(type = IndexableType.DELETE)
	public ProcessStep removeProcessStep(long processStepId) throws PortalException {
		validateRemove(processStepId);

		List<ProcessStepRole> processStepRoles = processStepRolePersistence.findByP_S_ID(processStepId);

		for (ProcessStepRole stepRole : processStepRoles) {
			processStepRolePersistence.remove(stepRole);
		}

		ProcessStep processStep = processStepPersistence.fetchByPrimaryKey(processStepId);

		processStepPersistence.remove(processStep);

		return processStep;
	}

	public static final String CLASS_NAME = ProcessStep.class.getName();

	public Hits searchLucene(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);

		Indexer<ProcessStep> indexer = IndexerRegistryUtil.nullSafeGetIndexer(ProcessStep.class);

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

				query.addFields(ProcessStepTerm.STEP_CODE, ProcessStepTerm.STEP_NAME, ProcessStepTerm.STEP_INSTRUCTION);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		String serviceProcessId = String.valueOf(params.get(ProcessStepTerm.SERVICE_PROCESS_ID));

		if (Validator.isNotNull(serviceProcessId)) {
			MultiMatchQuery query = new MultiMatchQuery(serviceProcessId);

			query.addFields(ProcessStepTerm.SERVICE_PROCESS_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);
	}

	public long countLucene(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);

		Indexer<ProcessStep> indexer = IndexerRegistryUtil.nullSafeGetIndexer(ProcessStep.class);

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

				query.addFields(ProcessStepTerm.STEP_CODE, ProcessStepTerm.STEP_NAME, ProcessStepTerm.STEP_INSTRUCTION);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		String serviceProcessId = String.valueOf(params.get(ProcessStepTerm.SERVICE_PROCESS_ID));

		if (Validator.isNotNull(serviceProcessId)) {
			MultiMatchQuery query = new MultiMatchQuery(serviceProcessId);

			query.addFields(ProcessStepTerm.SERVICE_PROCESS_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);
	}

	public ProcessStep fetchBySC_GID(String stepCode, long groupId, long serviceProcessId) {
		return processStepPersistence.fetchBySC_GID(stepCode, groupId, serviceProcessId);
	}

	private void validateAdd(long groupId, long processStepId, String stepCode, long serviceProcessId,
			String sequenceNo, String dossierStatus, String dossierSubStatus, String customProcessUrl)
			throws PortalException {

		if (Validator.isNull(stepCode)) {
			throw new RequiredStepNoException("RequiredStepCodeException");
		}

		ProcessStep processStep = processStepPersistence.fetchBySC_GID(stepCode, groupId, serviceProcessId);

		if (processStepId == 0) {
			if (Validator.isNotNull(processStep)) {
				throw new DuplicateStepNoException("DuplicateStepNoException");
			}

		} else {
			if (Validator.isNotNull(processStep) && processStep.getPrimaryKey() != processStepId) {
				throw new DuplicateStepNoException("DuplicateStepNoException");
			}
		}

		if (Validator.isNotNull(customProcessUrl) && !Validator.isUrl(customProcessUrl)) {
			throw new DossierURLException("InvalidCustomProcessURL");
		}

		// TODO add validate for DossierStatus and DossierSubStatus
	}

	@Deprecated
	private void validateUpdate(long groupId, long processStepId, String stepCode, long serviceProcessId,
			String sequenceNo, String dossierStatus, String dossierSubStatus, String customProcessUrl)
			throws PortalException {

		ProcessStep processStep = processStepPersistence.findByPrimaryKey(processStepId);

		if (!processStep.getStepCode().toLowerCase().contentEquals(stepCode.toLowerCase())) {
			validateAdd(groupId, processStepId, stepCode, serviceProcessId, sequenceNo, dossierStatus, dossierSubStatus,
					customProcessUrl);
		}

		if (Validator.isNotNull(customProcessUrl) && !Validator.isUrl(customProcessUrl)) {
			throw new DossierURLException("InvalidCustomProcessURL");
		}
	}

	private void validateRemove(long processStepId) throws PortalException {

		ProcessStep processStep = processStepPersistence.findByPrimaryKey(processStepId);

		// List<ProcessStepRole> processStepRoles =
		// processStepRolePersistence.findByP_S_ID(processStepId);

		List<ProcessAction> preActions = processActionPersistence.findByPRE_CODE(processStep.getStepCode(),
				processStep.getGroupId());

		List<ProcessAction> postActions = processActionPersistence.findByPOST_CODE(processStep.getStepCode(),
				processStep.getGroupId());

		if (preActions.size() != 0 || postActions.size() != 0) {
			throw new HasChildrenException("HasChildrenException");
		}
	}

	public List<ProcessStep> getProcessStepbyServiceProcessId(long serviceProcessId) {
		return processStepPersistence.findByS_P_ID(serviceProcessId);
	}
}