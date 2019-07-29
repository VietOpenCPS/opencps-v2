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
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
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
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.dossiermgt.constants.ProcessActionTerm;
import org.opencps.dossiermgt.exception.InvalidPostStepCodeException;
import org.opencps.dossiermgt.exception.InvalidPreStepCodeException;
import org.opencps.dossiermgt.exception.RequiredPaymentFeeException;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.service.base.ProcessActionLocalServiceBaseImpl;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the process action local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.ProcessActionLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see ProcessActionLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.ProcessActionLocalServiceUtil
 */
@ProviderType
public class ProcessActionLocalServiceImpl extends ProcessActionLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.dossiermgt.service.ProcessActionLocalServiceUtil} to access the
	 * process action local service.
	 */

	Log _log = LogFactoryUtil.getLog(ProcessActionLocalServiceImpl.class);

	@Indexable(type = IndexableType.REINDEX)
	public ProcessAction updateProcessAction(long groupId, long processActionId, long serviceProcessId,
			String preStepCode, String postStepCode, String autoEvent, String preCondition, String actionCode,
			String actionName, int allowAssignUser, long assignUserId, Integer requestPayment, String paymentFee,
			String createDossierFiles, String returnDossierFiles, String makeBriefNote, String syncActionCode,
			boolean rollbackable, boolean createDossierNo, boolean eSignature, 
			String signatureType,
			String configNote,
			String dossierTemplateNo, ServiceContext context) throws PortalException {

		Date now = new Date();

		_log.info("##########ConfigNote" + configNote);

		User userAction = userLocalService.getUser(context.getUserId());

		ProcessAction object = null;

		validateAdd(groupId, serviceProcessId, preStepCode, postStepCode, autoEvent, preCondition, actionCode,
				actionName, allowAssignUser, assignUserId, requestPayment, paymentFee, createDossierFiles,
				returnDossierFiles, makeBriefNote, syncActionCode, rollbackable);

		if (processActionId == 0) {

			processActionId = counterLocalService.increment(ProcessAction.class.getName());

			object = processActionPersistence.create(processActionId);

			// Add audit fields
			object.setCompanyId(context.getCompanyId());
			object.setGroupId(groupId);
			object.setCreateDate(now);
			object.setModifiedDate(now);
			object.setUserId(userAction.getUserId());
			object.setUserName(userAction.getFullName());

			object.setServiceProcessId(serviceProcessId);
			object.setPreStepCode(preStepCode);
			object.setPostStepCode(postStepCode);
			object.setAutoEvent(autoEvent);
			object.setPreCondition(preCondition);
			object.setActionCode(actionCode);
			object.setActionName(actionName);
			object.setAllowAssignUser(allowAssignUser);
			object.setAssignUserId(assignUserId);
			object.setRequestPayment(requestPayment);
			object.setPaymentFee(paymentFee);
			object.setCreateDossierFiles(createDossierFiles);
			object.setReturnDossierFiles(returnDossierFiles);
			object.setMakeBriefNote(makeBriefNote);
			object.setSyncActionCode(syncActionCode);
			object.setRollbackable(rollbackable);
			object.setCreateDossierNo(createDossierNo);
			object.setESignature(eSignature);
			object.setSignatureType(signatureType);
			object.setConfigNote(configNote);
			object.setDossierTemplateNo(dossierTemplateNo);

		} else {
			object = processActionPersistence.fetchByPrimaryKey(processActionId);

			// Add audit fields
			object.setModifiedDate(now);
			object.setUserId(userAction.getUserId());
			object.setUserName(userAction.getFullName());

			// object.setServiceProcessId(serviceProcessId);
			object.setPreStepCode(preStepCode);
			object.setPostStepCode(postStepCode);
			object.setAutoEvent(autoEvent);
			object.setPreCondition(preCondition);
			object.setActionCode(actionCode);
			object.setActionName(actionName);
			object.setAllowAssignUser(allowAssignUser);
			object.setAssignUserId(assignUserId);
			object.setRequestPayment(requestPayment);
			object.setPaymentFee(paymentFee);
			object.setCreateDossierFiles(createDossierFiles);
			object.setReturnDossierFiles(returnDossierFiles);
			object.setMakeBriefNote(makeBriefNote);
			object.setSyncActionCode(syncActionCode);
			object.setRollbackable(rollbackable);
			object.setCreateDossierNo(createDossierNo);
			object.setESignature(eSignature);
			object.setSignatureType(signatureType);
			object.setConfigNote(configNote);
			object.setDossierTemplateNo(dossierTemplateNo);

		}

		processActionPersistence.update(object);

		return object;
	}

	@Deprecated
	@Indexable(type = IndexableType.REINDEX)
	public ProcessAction updateProcessAction(long groupId, long processActionId, long serviceProcessId,
			String preStepCode, String postStepCode, String autoEvent, String preCondition, String actionCode,
			String actionName, int allowAssignUser, long assignUserId, Integer requestPayment, String paymentFee,
			String createDossierFiles, String returnDossierFiles, String makeBriefNote, String syncActionCode,
			boolean rollbackable, boolean createDossierNo, boolean eSignature, ServiceContext context)
			throws PortalException {

		Date now = new Date();

		User userAction = userLocalService.getUser(context.getUserId());

		ProcessAction object = null;

		validateAdd(groupId, serviceProcessId, preStepCode, postStepCode, autoEvent, preCondition, actionCode,
				actionName, allowAssignUser, assignUserId, requestPayment, paymentFee, createDossierFiles,
				returnDossierFiles, makeBriefNote, syncActionCode, rollbackable);

		if (processActionId == 0) {

			processActionId = counterLocalService.increment(ProcessAction.class.getName());

			object = processActionPersistence.create(processActionId);

			// Add audit fields
			object.setCompanyId(context.getCompanyId());
			object.setGroupId(groupId);
			object.setCreateDate(now);
			object.setModifiedDate(now);
			object.setUserId(userAction.getUserId());
			object.setUserName(userAction.getFullName());

			object.setServiceProcessId(serviceProcessId);
			object.setPreStepCode(preStepCode);
			object.setPostStepCode(postStepCode);
			object.setAutoEvent(autoEvent);
			object.setPreCondition(preCondition);
			object.setActionCode(actionCode);
			object.setActionName(actionName);
			object.setAllowAssignUser(allowAssignUser);
			object.setAssignUserId(assignUserId);
			object.setRequestPayment(requestPayment);
			object.setPaymentFee(paymentFee);
			object.setCreateDossierFiles(createDossierFiles);
			object.setReturnDossierFiles(returnDossierFiles);
			object.setMakeBriefNote(makeBriefNote);
			object.setSyncActionCode(syncActionCode);
			object.setRollbackable(rollbackable);
			object.setCreateDossierNo(createDossierNo);
			object.setESignature(eSignature);

		} else {
			object = processActionPersistence.fetchByPrimaryKey(processActionId);

			// Add audit fields
			object.setModifiedDate(now);
			object.setUserId(userAction.getUserId());
			object.setUserName(userAction.getFullName());

			// object.setServiceProcessId(serviceProcessId);
			object.setPreStepCode(preStepCode);
			object.setPostStepCode(postStepCode);
			object.setAutoEvent(autoEvent);
			object.setPreCondition(preCondition);
			object.setActionCode(actionCode);
			object.setActionName(actionName);
			object.setAllowAssignUser(allowAssignUser);
			object.setAssignUserId(assignUserId);
			object.setRequestPayment(requestPayment);
			object.setPaymentFee(paymentFee);
			object.setCreateDossierFiles(createDossierFiles);
			object.setReturnDossierFiles(returnDossierFiles);
			object.setMakeBriefNote(makeBriefNote);
			object.setSyncActionCode(syncActionCode);
			object.setRollbackable(rollbackable);
			object.setCreateDossierNo(createDossierNo);
			object.setESignature(eSignature);

		}

		processActionPersistence.update(object);

		return object;
	}

	@Deprecated
	@Indexable(type = IndexableType.REINDEX)
	public ProcessAction updateProcessAction(long groupId, long processActionId, long serviceProcessId,
			String preStepCode, String postStepCode, String autoEvent, String preCondition, String actionCode,
			String actionName, int allowAssignUser, long assignUserId, Integer requestPayment, String paymentFee,
			String createDossierFiles, String returnDossierFiles, String makeBriefNote, String syncActionCode,
			boolean rollbackable, ServiceContext context) throws PortalException {

		Date now = new Date();

		User userAction = userLocalService.getUser(context.getUserId());

		ProcessAction object = null;

		validateAdd(groupId, serviceProcessId, preStepCode, postStepCode, autoEvent, preCondition, actionCode,
				actionName, allowAssignUser, assignUserId, requestPayment, paymentFee, createDossierFiles,
				returnDossierFiles, makeBriefNote, syncActionCode, rollbackable);

		if (processActionId == 0) {

			processActionId = counterLocalService.increment(ProcessAction.class.getName());

			object = processActionPersistence.create(processActionId);

			// Add audit fields
			object.setCompanyId(context.getCompanyId());
			object.setGroupId(groupId);
			object.setCreateDate(now);
			object.setModifiedDate(now);
			object.setUserId(userAction.getUserId());
			object.setUserName(userAction.getFullName());

			object.setServiceProcessId(serviceProcessId);
			object.setPreStepCode(preStepCode);
			object.setPostStepCode(postStepCode);
			object.setAutoEvent(autoEvent);
			object.setPreCondition(preCondition);
			object.setActionCode(actionCode);
			object.setActionName(actionName);
			object.setAllowAssignUser(allowAssignUser);
			object.setAssignUserId(assignUserId);
			object.setRequestPayment(requestPayment);
			object.setPaymentFee(paymentFee);
			object.setCreateDossierFiles(createDossierFiles);
			object.setReturnDossierFiles(returnDossierFiles);
			object.setMakeBriefNote(makeBriefNote);
			object.setSyncActionCode(syncActionCode);
			object.setRollbackable(rollbackable);

		} else {
			object = processActionPersistence.fetchByPrimaryKey(processActionId);

			// Add audit fields
			object.setModifiedDate(now);
			object.setUserId(userAction.getUserId());
			object.setUserName(userAction.getFullName());

			// object.setServiceProcessId(serviceProcessId);
			object.setPreStepCode(preStepCode);
			object.setPostStepCode(postStepCode);
			object.setAutoEvent(autoEvent);
			object.setPreCondition(preCondition);
			object.setActionCode(actionCode);
			object.setActionName(actionName);
			object.setAllowAssignUser(allowAssignUser);
			object.setAssignUserId(assignUserId);
			object.setRequestPayment(requestPayment);
			object.setPaymentFee(paymentFee);
			object.setCreateDossierFiles(createDossierFiles);
			object.setReturnDossierFiles(returnDossierFiles);
			object.setMakeBriefNote(makeBriefNote);
			object.setSyncActionCode(syncActionCode);
			object.setRollbackable(rollbackable);

		}

		processActionPersistence.update(object);

		return object;
	}

	@Indexable(type = IndexableType.DELETE)
	public ProcessAction removeProcessAction(long processActionId) throws PortalException {

		validateRemove(processActionId);

		ProcessAction processAction = processActionPersistence.fetchByPrimaryKey(processActionId);

		processActionPersistence.remove(processAction);

		return processAction;
	}

	public Hits searchLucene(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);

		Indexer<ProcessAction> indexer = IndexerRegistryUtil.nullSafeGetIndexer(ProcessAction.class);

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

				query.addFields(ProcessActionTerm.PRESTEP_CODE, ProcessActionTerm.POSTSTEP_CODE,
						ProcessActionTerm.ACTION_NAME);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		String serviceProcessId = String.valueOf(params.get(ProcessActionTerm.SERVICE_PROCESS_ID));
		String preStepCode = String.valueOf(params.get(ProcessActionTerm.PRESTEP_CODE));
		String postStepCode = String.valueOf(params.get(ProcessActionTerm.POSTSTEP_CODE));

		if (Validator.isNotNull(serviceProcessId)) {
			MultiMatchQuery query = new MultiMatchQuery(serviceProcessId);

			query.addFields(ProcessActionTerm.SERVICE_PROCESS_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(preStepCode)) {
			MultiMatchQuery query = new MultiMatchQuery(preStepCode);

			query.addFields(ProcessActionTerm.PRESTEP_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(postStepCode)) {
			MultiMatchQuery query = new MultiMatchQuery(postStepCode);

			query.addFields(ProcessActionTerm.POSTSTEP_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);
	}

	public long searchCount(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);

		Indexer<ProcessAction> indexer = IndexerRegistryUtil.nullSafeGetIndexer(ProcessAction.class);

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

				query.addFields(ProcessActionTerm.PRESTEP_CODE, ProcessActionTerm.POSTSTEP_CODE,
						ProcessActionTerm.ACTION_NAME);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		String serviceProcessId = String.valueOf(params.get(ProcessActionTerm.SERVICE_PROCESS_ID));
		String preStepCode = String.valueOf(params.get(ProcessActionTerm.PRESTEP_CODE));
		String postStepCode = String.valueOf(params.get(ProcessActionTerm.POSTSTEP_CODE));

		if (Validator.isNotNull(serviceProcessId)) {
			MultiMatchQuery query = new MultiMatchQuery(serviceProcessId);

			query.addFields(ProcessActionTerm.SERVICE_PROCESS_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(preStepCode)) {
			MultiMatchQuery query = new MultiMatchQuery(preStepCode);

			query.addFields(ProcessActionTerm.PRESTEP_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(postStepCode)) {
			MultiMatchQuery query = new MultiMatchQuery(postStepCode);

			query.addFields(ProcessActionTerm.POSTSTEP_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);
	}

	public static final String CLASS_NAME = ProcessAction.class.getName();

	private void validateRemove(long processActionId) throws PortalException {
		// TODO add more logic for remove processAction in here
	}

	private void validateAdd(long groupId, long serviceProcessId, String preStepCode, String postStepCode,
			String autoEvent, String preCondition, String actionCode, String actionName, int allowAssignUser,
			long assignUserId, Integer requestPayment, String paymentFee, String createDossierFiles,
			String returnDossierFiles, String makeBriefNote, String syncActionCode, boolean rollbackable)
			throws PortalException {

		ProcessStep perStep = processStepPersistence.fetchBySC_GID(preStepCode, groupId, serviceProcessId);
		ProcessStep postStep = processStepPersistence.fetchBySC_GID(postStepCode, groupId, serviceProcessId);

		if (Validator.isNotNull(preStepCode) && Validator.isNull(perStep)) {
			throw new InvalidPreStepCodeException("InvalidPreStepCodeException");
		}

		if (Validator.isNotNull(postStepCode) && Validator.isNull(postStep)) {
			throw new InvalidPostStepCodeException("InvalidPostStepCodeException");
		}

		if (Validator.isNull(requestPayment) && requestPayment > 0 && Validator.isNull(paymentFee)) {
			throw new RequiredPaymentFeeException("RequiredPaymentFeeException");
		}

		/*
		 * if (allowAssignUser && Validator.isNull(assignUserId)) { throw new
		 * RequiredAssignUserIdException("RequiredAssignUserIdException"); }
		 */
		// TODO add more validate for actionCode, actionName, createDossierFiles
		// returnDossierFiles, makeBriefNote in here

	}

	public List<ProcessAction> getByActionCode(long groupId, String actionCode) throws PortalException {
		return processActionPersistence.findByGI_AC(groupId, actionCode);
	}

	public List<ProcessAction> getByActionCode(long groupId, String actionCode, long serviceProcessId)
			throws PortalException {
		return processActionPersistence.findByGI_AC_SP(groupId, actionCode, serviceProcessId);
	}

	public ProcessAction fetchBySPI_PRESC_AEV(long serviceProcessId, String preStepCode, String autoEvent) {
		return processActionPersistence.fetchBySPI_PRESC_AEV(serviceProcessId, preStepCode, autoEvent);
	}

	public ProcessAction fetchBySPID_AC(long serviceProcessId, String actionCode) {
		return processActionPersistence.fetchBySPID_AC(serviceProcessId, actionCode);
	}

	public List<ProcessAction> getProcessActionbyServiceProcessId(long serviceProcessId) throws PortalException {
		return processActionPersistence.findByS_P_ID(serviceProcessId);
	}

	public List<ProcessAction> getProcessActionByG_SPID_PRESC(long groupId, long serviceProcessId, String preStepCode)
			throws PortalException {
		return processActionPersistence.findByG_SPID_PRESC(groupId, serviceProcessId, preStepCode);
	}

	public List<ProcessAction> getByGroupAndAutoEvent(long groupId, String autoEvent, int start, int end) {

		if (groupId == 0)
			return processActionPersistence.findByPSC_AEV(autoEvent, start, end);
		else
			return processActionPersistence.findByPSC_AEV_GI(groupId, autoEvent, start, end);

	}

	public ProcessAction getByNameActionNo(long serviceProcessId, String actionCode, String actionName) {
		return processActionPersistence.fetchBySPID_AC_AN(serviceProcessId, actionCode, actionName);
	}

	// LamTV_process
	public ProcessAction getByServiceProcess(long serviceProcessId, String actionCode) {
		return processActionPersistence.fetchBySPID_AC(serviceProcessId, actionCode);
	}

	// LamTV_Process output ProcessAction to DB
	@Indexable(type = IndexableType.REINDEX)
	public ProcessAction updateProcessActionDB(long userId, long groupId, long serviceProcessId, String actionCode,
			String actionName, String preStepCode, String postStepCode, String autoEvent, String preCondition,
			int allowAssignUser, long assignUserId, String assignUserName, Integer requestPayment, String paymentFee,
			String createDossierFiles, String returnDossierFiles, boolean eSignature, String signatureType,
			String createDossiers, ServiceContext serviceContext) throws PortalException {

		Date now = new Date();
		User userAction = userLocalService.getUser(userId);

		long processActionId = counterLocalService.increment(ProcessAction.class.getName());
		ProcessAction object = processActionPersistence.create(processActionId);

		// Add audit fields
		object.setCompanyId(serviceContext.getCompanyId());
		object.setGroupId(groupId);
		object.setCreateDate(now);
		object.setModifiedDate(now);
		object.setUserId(userAction.getUserId());
		object.setUserName(userAction.getFullName());

		object.setServiceProcessId(serviceProcessId);
		object.setPreStepCode(preStepCode);
		object.setPostStepCode(postStepCode);
		object.setAutoEvent(autoEvent);
		object.setPreCondition(preCondition);
		object.setActionCode(actionCode);
		object.setActionName(actionName);
		object.setAllowAssignUser(allowAssignUser);
		object.setAssignUserId(assignUserId);
		// object.setAssignUserName(assignUserName);
		object.setRequestPayment(requestPayment);
		object.setPaymentFee(paymentFee);
		object.setCreateDossierFiles(createDossierFiles);
		object.setReturnDossierFiles(returnDossierFiles);
		object.setESignature(eSignature);
		object.setSignatureType(signatureType);
		object.setCreateDossiers(createDossiers);

		return processActionPersistence.update(object);

	}

	public List<ProcessAction> getByServiceStepCode(long groupId, long serviceProcessId, String preStepCode) {
		return processActionPersistence.findByF_GID_SID_PRE(groupId, serviceProcessId, preStepCode);
	}

	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public ProcessAction adminProcessDelete(Long id) {

		ProcessAction object = processActionPersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			processActionPersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public ProcessAction adminProcessData(JSONObject objectData) {

		ProcessAction object = null;

		if (objectData.getLong("processActionId") > 0) {

			object = processActionPersistence.fetchByPrimaryKey(objectData.getLong("processActionId"));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(ProcessAction.class.getName());

			object = processActionPersistence.create(id);

			object.setGroupId(objectData.getLong("groupId"));
			object.setCompanyId(objectData.getLong("companyId"));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong("userId"));
		object.setUserName(objectData.getString("userName"));

		object.setServiceProcessId(objectData.getLong("serviceProcessId"));
		object.setPreStepCode(objectData.getString("preStepCode"));
		object.setPostStepCode(objectData.getString("postStepCode"));
		object.setAutoEvent(objectData.getString("autoEvent"));
		object.setPreCondition(objectData.getString("preCondition"));
		object.setActionCode(objectData.getString("actionCode"));
		object.setActionName(objectData.getString("actionName"));
		object.setAllowAssignUser(objectData.getInt("allowAssignUser"));
		object.setAssignUserId(objectData.getLong("assignUserId"));
		object.setRequestPayment(objectData.getInt("requestPayment"));
		object.setPaymentFee(objectData.getString("paymentFee"));
		object.setCreateDossierFiles(objectData.getString("createDossierFiles"));
		object.setReturnDossierFiles(objectData.getString("returnDossierFiles"));
		object.setMakeBriefNote(objectData.getString("makeBriefNote"));
		object.setSyncActionCode(objectData.getString("syncActionCode"));
		object.setRollbackable(objectData.getBoolean("rollbackable"));
		object.setCreateDossierNo(objectData.getBoolean("createDossierNo"));
		object.setESignature(objectData.getBoolean("eSignature"));
		object.setConfigNote(objectData.getString("configNote"));
		object.setDossierTemplateNo(objectData.getString("dossierTemplateNo"));
		object.setSignatureType(objectData.getString("signatureType"));
		object.setCreateDossiers(objectData.getString("createDossiers"));
		object.setCheckInput(objectData.getInt("checkInput"));

		processActionPersistence.update(object);

		return object;
	}

	public ProcessAction fetchByF_GID_SID_AC_PRE_POST(long groupId, long serviceProcessId, String actionCode, String preStepCode, String postStepCode) {
		return processActionPersistence.fetchByF_GID_SID_AC_PRE_POST(groupId, serviceProcessId, actionCode, preStepCode, postStepCode);
	}
	
	public List<ProcessAction> getByG_SID_ACS(long groupId, long serviceProcessId, String[] actionCodes) {
		return processActionPersistence.findByF_GID_SID_ACS(groupId, serviceProcessId, actionCodes);
	}
}