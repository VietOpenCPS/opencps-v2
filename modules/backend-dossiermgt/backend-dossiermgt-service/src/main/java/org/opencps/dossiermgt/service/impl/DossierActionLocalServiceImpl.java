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
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.search.generic.BooleanQueryImpl;
import com.liferay.portal.kernel.search.generic.MultiMatchQuery;
import com.liferay.portal.kernel.search.generic.TermRangeQueryImpl;
import com.liferay.portal.kernel.search.generic.WildcardQueryImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.constants.ConstantsTerm;
import org.opencps.dossiermgt.constants.DeliverableTerm;
import org.opencps.dossiermgt.constants.DossierActionTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.service.base.DossierActionLocalServiceBaseImpl;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the dossier action local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.dossiermgt.service.DossierActionLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author huymq
 * @see DossierActionLocalServiceBaseImpl
 * @see org.opencps.dossiermgt.service.DossierActionLocalServiceUtil
 */
@ProviderType
public class DossierActionLocalServiceImpl extends DossierActionLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.dossiermgt.service.DossierActionLocalServiceUtil} to access the
	 * dossier action local service.
	 */

	//@Indexable(type = IndexableType.REINDEX)
	public DossierAction updateDossierAction(long groupId, long dossierActionId, long dossierId, long serviceProcessId,
			long previousActionId, String fromStepCode, String fromStepName, String fromSequenceNo, String actionCode,
			String actionUser, String actionName, String actionNote, int actionOverdue, String syncActionCode,
			boolean pending, boolean rollbackable, String stepCode, String stepName, String sequenceNo, Date dueDate,
			long nextActionId, String payload, String stepInstruction, int state, int eventStatus,
			ServiceContext context) throws PortalException {

		validateUpdateAction(groupId, dossierActionId, dossierId, serviceProcessId, previousActionId, actionCode,
				actionUser, actionName, actionNote, actionOverdue, syncActionCode, pending, rollbackable, stepCode,
				stepName, dueDate, nextActionId, payload, stepInstruction);

		DossierAction object = null;
		long userId = 0l;

		String fullName = StringPool.BLANK;

		Date now = new Date();

		if (context.getUserId() > 0) {
			User userAction = userLocalService.getUser(context.getUserId());

			userId = userAction.getUserId();
			fullName = userAction.getFullName();

		}

		if (dossierActionId == 0) {
			dossierActionId = counterLocalService.increment(DossierAction.class.getName());

			object = dossierActionPersistence.create(dossierActionId);

			// Add audit fields
			object.setCompanyId(context.getCompanyId());
			object.setGroupId(groupId);
			object.setCreateDate(now);
			object.setModifiedDate(now);
			object.setUserId(userId);
			object.setUserName(fullName);

			object.setDossierId(dossierId);
			object.setServiceProcessId(serviceProcessId);
			object.setPreviousActionId(previousActionId);
			object.setFromStepCode(fromStepCode);
			object.setFromStepName(fromStepName);
			object.setFromSequenceNo(fromSequenceNo);
			object.setSequenceNo(sequenceNo);
			object.setActionCode(actionCode);
			object.setActionUser(actionUser);
			object.setActionName(actionName);
			object.setActionNote(actionNote);
			object.setActionOverdue(actionOverdue);
			object.setSyncActionCode(syncActionCode);
			object.setPending(pending);
			object.setRollbackable(rollbackable);
			object.setStepCode(stepCode);
			object.setStepName(stepName);
			object.setDueDate(dueDate);
			object.setNextActionId(nextActionId);
			object.setPayload(payload);
			object.setStepInstruction(stepInstruction);
			object.setState(state);
			object.setEventStatus(eventStatus);

			// Add DossierActionId to Dossier

			Dossier dossier = dossierPersistence.fetchByPrimaryKey(dossierId);
			dossier.setDossierActionId(dossierActionId);
			dossierPersistence.update(dossier);

		} else {

		}

		return dossierActionPersistence.update(object);
	}

	//@Indexable(type = IndexableType.REINDEX)
	public DossierAction updateDossierAction(long groupId, long dossierActionId, long dossierId, long serviceProcessId,
			long previousActionId, String fromStepCode, String fromStepName, String fromSequenceNo, String actionCode,
			String actionUser, String actionName, String actionNote, int actionOverdue, String stepCode,
			String stepName, String sequenceNo, Date dueDate, long nextActionId, String payload, String stepInstruction,
			int state, int eventStatus, ServiceContext context) throws PortalException {

		DossierAction object = null;
		long userId = 0l;
		String fullName = StringPool.BLANK;
		Date now = new Date();

		if (context.getUserId() > 0) {
			User userAction = userLocalService.getUser(context.getUserId());
			userId = userAction.getUserId();
			fullName = userAction.getFullName();
		}

		if (dossierActionId == 0) {
			dossierActionId = counterLocalService.increment(DossierAction.class.getName());

			object = dossierActionPersistence.create(dossierActionId);

			// Add audit fields
			object.setCompanyId(context.getCompanyId());
			object.setGroupId(groupId);
			object.setCreateDate(now);
			object.setModifiedDate(now);
			object.setUserId(userId);
			object.setUserName(fullName);

			object.setDossierId(dossierId);
			object.setServiceProcessId(serviceProcessId);
			object.setPreviousActionId(previousActionId);
			object.setFromStepCode(fromStepCode);
			object.setFromStepName(fromStepName);
			object.setFromSequenceNo(fromSequenceNo);
			object.setSequenceNo(sequenceNo);
			object.setActionCode(actionCode);
			if (Validator.isNotNull(actionUser)) {
				object.setActionUser(actionUser);
			} else {
				object.setActionUser(fullName);
			}
			object.setActionName(actionName);
			object.setActionNote(actionNote);
			object.setActionOverdue(actionOverdue);
			object.setStepCode(stepCode);
			object.setStepName(stepName);
			object.setDueDate(dueDate);
			object.setNextActionId(nextActionId);
			object.setPayload(payload);
			object.setStepInstruction(stepInstruction);
			object.setState(state);
			object.setEventStatus(eventStatus);

			// Add DossierActionId to Dossier

			Dossier dossier = dossierPersistence.fetchByPrimaryKey(dossierId);
			dossier.setDossierActionId(dossierActionId);
			dossierPersistence.update(dossier);

		} else {

		}

		return dossierActionPersistence.update(object);

	}

	//@Indexable(type = IndexableType.DELETE)
	public DossierAction removeAction(long actionId) throws PortalException {
		//DossierAction action = dossierActionPersistence.fetchByPrimaryKey(actionId);

		return dossierActionPersistence.remove(actionId);
	}

	//@Indexable(type = IndexableType.REINDEX)
	public DossierAction updateNextActionId(long actionId, long nextActionId) throws PortalException {
		DossierAction action = dossierActionPersistence.fetchByPrimaryKey(actionId);

		action.setNextActionId(nextActionId);

		Date now = new Date();

		action.setModifiedDate(now);

		return dossierActionPersistence.update(action);
	}

	//@Indexable(type = IndexableType.REINDEX)
	public DossierAction updatePending(long actionId, boolean pending) throws PortalException {
		DossierAction action = dossierActionPersistence.fetchByPrimaryKey(actionId);

		action.setPending(pending);

		Date now = new Date();

		action.setModifiedDate(now);

		return dossierActionPersistence.update(action);
	}

	//@Indexable(type = IndexableType.REINDEX)
	public DossierAction updateState(long actionId, int state) {
		DossierAction action = dossierActionPersistence.fetchByPrimaryKey(actionId);

		action.setState(state);

		Date now = new Date();

		action.setModifiedDate(now);

		return dossierActionPersistence.update(action);
	}

	//@Indexable(type = IndexableType.REINDEX)
	public DossierAction updateRollbackable(long actionId, boolean rollbackable) {
		DossierAction action = dossierActionPersistence.fetchByPrimaryKey(actionId);
		action.setRollbackable(rollbackable);

		Date now = new Date();

		action.setModifiedDate(now);

		return dossierActionPersistence.update(action);
	}

	private void validateUpdateAction(long groupId, long dossierActionId, long dossierId, long serviceProcessId,
			long previousActionId, String actionCode, String actionUser, String actionName, String actionNote,
			int actionOverdue, String syncActionCode, boolean pending, boolean rollbackable, String stepCode,
			String stepName, Date dueDate, long nextActionId, String payload, String stepInstruction)
			throws PortalException {

	}

	public DossierAction getByPenddingStatus(long dossierId, boolean pending) {
		return dossierActionPersistence.fetchByDID_DPG(dossierId, pending);
	}

	public DossierAction getByNextActionId(long dossierId, long nextActionId) {
		return dossierActionPersistence.fetchByDID_NACTID(dossierId, nextActionId);
	}

	public DossierAction getDossierActionById(long dossierId, long userId) {
		return dossierActionPersistence.fetchByDID_UID(dossierId, userId);
	}

	public DossierAction getDossierActionbyDossierIdandActionCode(long dossierId, String actionCode) {
		return dossierActionPersistence.fetchByDID_ACTC(dossierId, actionCode);
	}

	public List<DossierAction> getDossierActionById(long dossierId) {
		return dossierActionPersistence.findByDID(dossierId);
	}

	public Hits searchLucene(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);
//		String dossierIds = String.valueOf((params.get(DossierActionTerm.DOSSIER_ID)));
		String dossierNo = (String) params.get(DossierTerm.DOSSIER_NO);
		String userName = (String) params.get(DossierTerm.USER_NAME);
		String actionUser = (String) params.get(DossierTerm.ACTION_USER);
		String createDateStart =
				GetterUtil.getString(params.get(DossierTerm.CREATE_DATE_START));
		String createDateEnd =
				GetterUtil.getString(params.get(DossierTerm.CREATE_DATE_END));


		Indexer<DossierAction> indexer = IndexerRegistryUtil.nullSafeGetIndexer(DossierAction.class);

		searchContext.addFullQueryEntryClassName(CLASS_NAME);
		searchContext.setEntryClassNames(new String[] { CLASS_NAME });
		searchContext.setAttribute(DeliverableTerm.PAGINATION_TYPE, DeliverableTerm.REGULAR);
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

				query.addFields(DossierActionTerm.DOSSIER_ID);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}
		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(dossierNo)) {
			MultiMatchQuery query = new MultiMatchQuery(dossierNo);
			query.addFields(DossierTerm.DOSSIER_NO);
			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}
		if (Validator.isNotNull(userName)) {
			MultiMatchQuery query = new MultiMatchQuery(userName);
			query.addFields(DossierTerm.USER_NAME);
			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}
		if (Validator.isNotNull(actionUser)) {
			String actionUserArr[] = actionUser.split(StringPool.SPACE);
			BooleanQuery query = new BooleanQueryImpl();
			for(String arr :actionUserArr){
				WildcardQuery wildQuery = new WildcardQueryImpl(
						DossierTerm.ACTION_USER_SEARCH,
						StringPool.STAR + arr.toLowerCase() + StringPool.STAR);
				query.add(wildQuery,BooleanClauseOccur.SHOULD);
			}
			booleanQuery.add(query, BooleanClauseOccur.MUST);

		}

		String createDateStartFilter =
				createDateStart + ConstantsTerm.HOUR_START;
		String createDateEndFilter = createDateEnd + ConstantsTerm.HOUR_END;
		if (Validator.isNotNull(createDateStart)) {
			if (Validator.isNotNull(createDateEnd)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
						DossierTerm.CREATE_DATE, createDateStartFilter,
						createDateEndFilter, true, true);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			}
			else {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
						DossierTerm.CREATE_DATE, createDateStartFilter,
						null, true, false);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			}
		}
		else {
			if (Validator.isNotNull(createDateEnd)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
						DossierTerm.CREATE_DATE, null, createDateEndFilter,
						false, true);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			}
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);
	}

	public long countLucene(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);
		String dossierNo = String.valueOf((params.get(DossierActionTerm.DOSSIER_NO)));
		String createDateStart =
				GetterUtil.getString(params.get(DossierTerm.CREATE_DATE_START));
		String createDateEnd =
				GetterUtil.getString(params.get(DossierTerm.CREATE_DATE_END));

		Indexer<DossierAction> indexer = IndexerRegistryUtil.nullSafeGetIndexer(DossierAction.class);

		searchContext.addFullQueryEntryClassName(CLASS_NAME);
		searchContext.setEntryClassNames(new String[] { CLASS_NAME });
		searchContext.setAttribute(DeliverableTerm.PAGINATION_TYPE, DeliverableTerm.REGULAR);
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

				query.addFields(DossierActionTerm.DOSSIER_ID);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}
		if (Validator.isNotNull(dossierNo)) {
			MultiMatchQuery query = new MultiMatchQuery(dossierNo);
			query.addFields(DossierTerm.DOSSIER_NO);
			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		String createDateStartFilter =
				createDateStart + ConstantsTerm.HOUR_START;
		String createDateEndFilter = createDateEnd + ConstantsTerm.HOUR_END;
		if (Validator.isNotNull(createDateStart)) {
			if (Validator.isNotNull(createDateEnd)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
						DossierTerm.CREATE_DATE, createDateStartFilter,
						createDateEndFilter, true, true);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			}
			else {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
						DossierTerm.CREATE_DATE, createDateStartFilter,
						null, true, false);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			}
		}
		else {
			if (Validator.isNotNull(createDateEnd)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(
						DossierTerm.CREATE_DATE, null, createDateEndFilter,
						false, true);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			}
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);
	}

	public static final String CLASS_NAME = DossierAction.class.getName();

	// TODO:
	public List<DossierAction> getDossiersPending(long groupId, String pending) {
		return dossierActionPersistence.findByG_PENDING(groupId, Boolean.parseBoolean(pending));
	}

	public List<DossierAction> findDossierActionByDID_FSN(long dossierId, String fromSequenceNo) {
		return dossierActionPersistence.findByDID_FSN(dossierId, fromSequenceNo);
	}

	public List<DossierAction> findDossierActionByG_DID_SN(long groupId, long dossierId, String sequenceNo) {
		return dossierActionPersistence.findByG_DID_SN(groupId, dossierId, sequenceNo, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);
	}

	public List<DossierAction> findDossierActionByG_DID_FSN(long groupId, long dossierId, String fromSequenceNo) {
		return dossierActionPersistence.findByG_DID_FSN(groupId, dossierId, fromSequenceNo, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);
	}

	public List<DossierAction> findDossierActionByDID_STEP(long dossierId, String fromStepCode) {
		return dossierActionPersistence.findByDID_STEP(dossierId, fromStepCode);
	}

	public List<DossierAction> getByDossierAndStepCode(long dossierId, String stepCode) {
		return dossierActionPersistence.findByDID_SC(dossierId, stepCode);
	}

	public DossierAction getByDID_CODE_First(long dossierId, String actionCode,
			OrderByComparator<DossierAction> orderByComparator) {
		return dossierActionPersistence.fetchByDID_CODE_First(dossierId, actionCode, orderByComparator);
	}

	// super_admin Generators
//	@Indexable(type = IndexableType.DELETE)
	public DossierAction adminProcessDelete(Long id) {

		DossierAction object = dossierActionPersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			dossierActionPersistence.remove(object);
		}

		return object;
	}

//	@Indexable(type = IndexableType.REINDEX)
	public DossierAction adminProcessData(JSONObject objectData) {

		DossierAction object = null;

		if (objectData.getLong(DossierActionTerm.DOSSIERACTION_ID) > 0) {

			object = dossierActionPersistence.fetchByPrimaryKey(objectData.getLong(DossierActionTerm.DOSSIERACTION_ID));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(DossierAction.class.getName());

			object = dossierActionPersistence.create(id);

			object.setGroupId(objectData.getLong(Field.GROUP_ID));
			object.setCompanyId(objectData.getLong(Field.COMPANY_ID));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong(Field.USER_ID));
		object.setUserName(objectData.getString(Field.USER_NAME));

		object.setDossierId(objectData.getLong(DossierActionTerm.DOSSIER_ID));
		object.setServiceProcessId(objectData.getLong(DossierActionTerm.SERVICE_PROCESS_ID));
		object.setPreviousActionId(objectData.getLong(DossierActionTerm.PREVIOUS_ACTION_ID));
		object.setFromStepCode(objectData.getString(DossierActionTerm.FROM_STEP_CODE));
		object.setFromStepName(objectData.getString(DossierActionTerm.FROM_STEP_NAME));
		object.setFromSequenceNo(objectData.getString(DossierActionTerm.FROM_SEQUENCE_NO));
		object.setActionCode(objectData.getString(DossierActionTerm.ACTION_CODE));
		object.setActionUser(objectData.getString(DossierActionTerm.ACTION_USER));
		object.setActionName(objectData.getString(DossierActionTerm.ACTION_NAME));
		object.setActionNote(objectData.getString(DossierActionTerm.ACTION_NOTE));
		object.setActionOverdue(objectData.getInt(DossierActionTerm.ACTION_OVER_DUE));
		object.setSyncActionCode(objectData.getString(DossierActionTerm.SYNC_ACTION_CODE));
		object.setPending(objectData.getBoolean(DossierActionTerm.PENDING));
		object.setRollbackable(objectData.getBoolean(DossierActionTerm.ROLLBACK_ABLE));
		object.setStepCode(objectData.getString(DossierActionTerm.STEP_CODE));
		object.setStepName(objectData.getString(DossierActionTerm.STEP_NAME));
		object.setSequenceNo(objectData.getString(DossierActionTerm.SEQUENCE_NO));
		object.setDueDate(new Date(objectData.getLong(DossierActionTerm.DUE_DATE)));
		object.setNextActionId(objectData.getLong(DossierActionTerm.NEXT_ACTION_ID));
		object.setPayload(objectData.getString(DossierActionTerm.PAYLOAD));
		object.setStepInstruction(objectData.getString(DossierActionTerm.STEP_INSTRUCTION));
		object.setState(objectData.getInt(DossierActionTerm.STATE));
		object.setEventStatus(objectData.getInt(DossierActionTerm.EVENT_STATUS));

		dossierActionPersistence.update(object);

		return object;
		
	}

//	@ThreadLocalCachable
	public List<DossierAction> getByDID_U_SC(long dossierId, long userId, String stepCode) {
		return dossierActionPersistence.findByDID_U_SC(dossierId, userId, stepCode);
	}	
	
//	@ThreadLocalCachable
	public List<DossierAction> getByDID_SC_NOT_DAI(long dossierId, String stepCode, long dossierActionId) {
		return dossierActionPersistence.findByDID_SC_NOT_DAI(dossierId, stepCode, dossierActionId);
	}

//	@ThreadLocalCachable
	public List<DossierAction> getByDID_FSC_NOT_DAI(long dossierId, String stepCode, long dossierActionId) {
		return dossierActionPersistence.findByDID_FSC_NOT_DAI(dossierId, stepCode, dossierActionId);
	}
	
//	@ThreadLocalCachable
	public List<DossierAction> getByDID_U_FSC(long dossierId, long userId, String stepCode) {
		return dossierActionPersistence.findByDID_U_FSC(dossierId, userId, stepCode);
	}	
	
//	@ThreadLocalCachable
	public List<DossierAction> findByG_DID(long groupId, long dossierId) {
		return dossierActionPersistence.findByG_DID(groupId, dossierId);
	}
	
//	@ThreadLocalCachable
	public List<DossierAction> findOverdue(Date now) {
		return dossierActionPersistence.findByDD(now, 0l);
	}

	public List<DossierAction> findOverdueByType(Date now, String type, String value) {
		int valueInt = 0;
		try {
			valueInt = Integer.parseInt(value);
		}
		catch (NumberFormatException e) {
			
		}
		Calendar c = Calendar.getInstance();
//		List<DossierAction> lstActions = new ArrayList<DossierAction>();
		List<DossierAction> lstActions;
		
		if (DossierTerm.DUE_DATE_NOTIFY_TYPE_HOUR.contentEquals(type)) {
			c.setTime(now);
			c.add(Calendar.HOUR, -valueInt);
			
			lstActions = findOverdue(c.getTime());
		}
		else if (DossierTerm.DUE_DATE_NOTIFY_TYPE_DAY.contentEquals(type)) {
			c.setTime(now);
			c.add(Calendar.HOUR, -valueInt * DossierTerm.WORKING_HOUR_PER_DAY);
			
			lstActions = findOverdue(c.getTime());			
		}
		else {
			lstActions = dossierActionPersistence.findByDD(now, 0l);			
		}
		List<DossierAction> results = new ArrayList<DossierAction>();
		for (DossierAction da : lstActions) {
			if (now.before(da.getDueDate())) {
				results.add(da);
			}
		}
		
		return results;
	}
	
	//@Indexable(type = IndexableType.REINDEX)
	public DossierAction updateDossierAction(long groupId, long dossierActionId, long dossierId, long serviceProcessId,
			long previousActionId, String fromStepCode, String fromStepName, String fromSequenceNo, String actionCode,
			String actionUser, String actionName, String actionNote, int actionOverdue, String stepCode,
			String stepName, String sequenceNo, Date dueDate, long nextActionId, String payload, String stepInstruction,
			int state, int eventStatus, boolean rollbackable, ServiceContext context) throws PortalException {

		DossierAction object = null;
		long userId = 0l;
		String fullName = StringPool.BLANK;
		Date now = new Date();

		if (context.getUserId() > 0) {
			User userAction = userLocalService.getUser(context.getUserId());
			userId = userAction.getUserId();
			fullName = userAction.getFullName();
		}

		if (dossierActionId == 0) {
			dossierActionId = counterLocalService.increment(DossierAction.class.getName());

			object = dossierActionPersistence.create(dossierActionId);

			// Add audit fields
			object.setCompanyId(context.getCompanyId());
			object.setGroupId(groupId);
			object.setCreateDate(now);
			object.setModifiedDate(now);
			object.setUserId(userId);
			object.setUserName(fullName);

			object.setDossierId(dossierId);
			object.setServiceProcessId(serviceProcessId);
			object.setPreviousActionId(previousActionId);
			object.setFromStepCode(fromStepCode);
			object.setFromStepName(fromStepName);
			object.setFromSequenceNo(fromSequenceNo);
			object.setSequenceNo(sequenceNo);
			object.setActionCode(actionCode);
			if (Validator.isNotNull(actionUser)) {
				object.setActionUser(actionUser);
			} else {
				object.setActionUser(fullName);
			}
			object.setActionName(actionName);
			object.setActionNote(actionNote);
			object.setActionOverdue(actionOverdue);
			object.setStepCode(stepCode);
			object.setStepName(stepName);
			object.setDueDate(dueDate);
			object.setNextActionId(nextActionId);
			object.setPayload(payload);
			object.setStepInstruction(stepInstruction);
			object.setState(state);
			object.setEventStatus(eventStatus);
			object.setRollbackable(rollbackable);
			// Add DossierActionId to Dossier

			Dossier dossier = dossierPersistence.fetchByPrimaryKey(dossierId);
			dossier.setDossierActionId(dossierActionId);
			dossierPersistence.update(dossier);

//			Indexer<Dossier> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Dossier.class);
//
//			try {
//				indexer.reindex(dossier);
//			} catch (SearchException e) {
//				_log.debug(e);
//			}

		} else {

		}

		object = dossierActionPersistence.update(object);

		return object;
	}

	//@Indexable(type = IndexableType.REINDEX)
	public DossierAction updateImportDossierAction(long groupId, long dossierActionId, long serviceProcessId,
			String fromStepCode, String fromStepName, String fromSequenceNo, String actionCode, String actionUser,
			String actionName, String stepCode, String stepName, Date dueDate, long nextActionId, int state,
			ServiceContext context) throws PortalException {

		DossierAction object = null;
		long userId = 0l;
		String fullName = StringPool.BLANK;
		Date now = new Date();

		if (context.getUserId() > 0) {
			User userAction = userLocalService.getUser(context.getUserId());
			userId = userAction.getUserId();
			fullName = userAction.getFullName();
		}

		if (dossierActionId == 0) {
			dossierActionId = counterLocalService.increment(DossierAction.class.getName());

			object = dossierActionPersistence.create(dossierActionId);

			// Add audit fields
			object.setCompanyId(context.getCompanyId());
			object.setGroupId(groupId);
			object.setCreateDate(now);
			object.setModifiedDate(now);
			object.setUserId(userId);
			object.setUserName(fullName);

			object.setServiceProcessId(serviceProcessId);
			object.setPreviousActionId(0);
			object.setFromStepCode(fromStepCode);
			object.setFromStepName(fromStepName);
			object.setFromSequenceNo(fromSequenceNo);
			object.setActionCode(actionCode);
			if (Validator.isNotNull(actionUser)) {
				object.setActionUser(actionUser);
			} else {
				object.setActionUser(fullName);
			}
			object.setActionName(actionName);
			object.setStepCode(stepCode);
			object.setStepName(stepName);
			object.setDueDate(dueDate);
			object.setNextActionId(nextActionId);
			object.setState(state);

			object = dossierActionPersistence.update(object);
		}

		return object;
	}

	public List findActionOverdue(Date fromDate, Date toDate, long groupId) {
		return dossierActionFinder.findActionOverdue(fromDate, toDate, groupId);
	}
	
	public List findActionUndue(Date fromDate, Date toDate, long groupId) {
		return dossierActionFinder.findActionUndue(fromDate, toDate, groupId);
	}

	public List findActionOverdueFuture(long groupId) {
		return dossierActionFinder.findActionOverdueFuture(groupId);
	}

	private static Log _log = LogFactoryUtil.getLog(DossierActionLocalServiceImpl.class);
}