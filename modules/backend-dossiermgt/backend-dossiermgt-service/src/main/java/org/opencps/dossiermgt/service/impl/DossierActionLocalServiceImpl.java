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

import org.opencps.dossiermgt.constants.DossierActionTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierAction;
import org.opencps.dossiermgt.service.base.DossierActionLocalServiceBaseImpl;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
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
	 * org.opencps.dossiermgt.service.DossierActionLocalServiceUtil} to access
	 * the dossier action local service.
	 */

	@Indexable(type = IndexableType.REINDEX)
	public DossierAction updateDossierAction(long groupId, long dossierActionId, long dossierId, long serviceProcessId,
			long previousActionId, 
			String fromStepCode, String fromStepName, String fromSequenceNo,
			String actionCode, String actionUser, String actionName, String actionNote,
			int actionOverdue, String syncActionCode, boolean pending, boolean rollbackable, String stepCode,
			String stepName, 
			String sequenceNo,
			Date dueDate, long nextActionId, String payload, String stepInstruction,
			int state, int eventStatus,
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

			// TODO add Indexer for Dossier after update DossierAction
			Dossier dossier = dossierPersistence.fetchByPrimaryKey(dossierId);
			dossier.setDossierActionId(dossierActionId);
			dossierPersistence.update(dossier);

			Indexer<Dossier> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Dossier.class);

			try {
				indexer.reindex(dossier);
			} catch (SearchException e) {
				e.printStackTrace();
			}
			
			
		} else {

		}

		return dossierActionPersistence.update(object);
	}

	@Indexable(type = IndexableType.REINDEX)
	public DossierAction updateDossierAction(long groupId, long dossierActionId, long dossierId,
			long serviceProcessId, long previousActionId, 
			String fromStepCode, String fromStepName, String fromSequenceNo,	
			String actionCode, String actionUser, String actionName, String actionNote, int actionOverdue,
			String stepCode, String stepName, 
			String sequenceNo,
			Date dueDate, long nextActionId, String payload, String stepInstruction,
			int state, int eventStatus,
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

			// TODO add Indexer for Dossier after update DossierAction
			Dossier dossier = dossierPersistence.fetchByPrimaryKey(dossierId);
			dossier.setDossierActionId(dossierActionId);
			dossierPersistence.update(dossier);

			Indexer<Dossier> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Dossier.class);

			try {
				indexer.reindex(dossier);
			} catch (SearchException e) {
				e.printStackTrace();
			}
			
			
		} else {

		}

		object = dossierActionPersistence.update(object);

		return object;
	}

	@Indexable(type = IndexableType.DELETE)
	public DossierAction removeAction(long actionId) throws PortalException {
		DossierAction action = dossierActionPersistence.fetchByPrimaryKey(actionId);

		return dossierActionPersistence.remove(action);
	}

	@Indexable(type = IndexableType.REINDEX)
	public DossierAction updateNextActionId(long actionId, long nextActionId) throws PortalException {
		DossierAction action = dossierActionPersistence.fetchByPrimaryKey(actionId);

		action.setNextActionId(nextActionId);

		Date now = new Date();

		action.setModifiedDate(now);

		return dossierActionPersistence.update(action);
	}

	@Indexable(type = IndexableType.REINDEX)
	public DossierAction updatePending(long actionId, boolean pending) throws PortalException {
		DossierAction action = dossierActionPersistence.fetchByPrimaryKey(actionId);

		action.setPending(pending);

		Date now = new Date();

		action.setModifiedDate(now);

		return dossierActionPersistence.update(action);
	}

	@Indexable(type = IndexableType.REINDEX)
	public DossierAction updateState(long actionId, int state) {
		DossierAction action = dossierActionPersistence.fetchByPrimaryKey(actionId);

		action.setState(state);

		Date now = new Date();

		action.setModifiedDate(now);

		return dossierActionPersistence.update(action);
	}
	
	@Indexable(type = IndexableType.REINDEX)
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
		// String secetKey = GetterUtil.getString(params.get("secetKey"));

		Indexer<DossierAction> indexer = IndexerRegistryUtil.nullSafeGetIndexer(DossierAction.class);

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

				query.addFields(DossierActionTerm.DOSSIER_ID);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}
		// if (!(Validator.isNotNull(secetKey) &&
		// secetKey.contentEquals("OPENCPSV2"))) {
		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		// }

		String dossierId = String.valueOf((params.get(DossierActionTerm.DOSSIER_ID)));

		if (Validator.isNotNull(dossierId)) {
			MultiMatchQuery query = new MultiMatchQuery(dossierId);

			query.addFields(DossierActionTerm.DOSSIER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);
	}

	public long countLucene(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {

		String keywords = (String) params.get(Field.KEYWORD_SEARCH);
		String groupId = (String) params.get(Field.GROUP_ID);
		String secetKey = GetterUtil.getString(params.get("secetKey"));
		Indexer<DossierAction> indexer = IndexerRegistryUtil.nullSafeGetIndexer(DossierAction.class);

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

				query.addFields(DossierActionTerm.DOSSIER_ID);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		String dossierId = String.valueOf(params.get(DossierActionTerm.DOSSIER_ID));

		if (Validator.isNotNull(dossierId)) {
			MultiMatchQuery query = new MultiMatchQuery(dossierId);

			query.addFields(DossierActionTerm.DOSSIER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, CLASS_NAME);

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);
	}

	public static final String CLASS_NAME = DossierAction.class.getName();

	//TODO:
	public List<DossierAction> getDossiersPending(long groupId, String pending) {
		return dossierActionPersistence.findByG_PENDING(groupId, Boolean.parseBoolean(pending));
	}
	
	public List<DossierAction> findDossierActionByDID_FSN(long dossierId, String fromSequenceNo) {
		return dossierActionPersistence.findByDID_FSN(dossierId, fromSequenceNo);
	}
	
	public List<DossierAction> findDossierActionByG_DID_SN(long groupId, long dossierId, String sequenceNo) {
		return dossierActionPersistence.findByG_DID_SN(groupId, dossierId, sequenceNo, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}	

	public List<DossierAction> findDossierActionByG_DID_FSN(long groupId, long dossierId, String fromSequenceNo) {
		return dossierActionPersistence.findByG_DID_FSN(groupId, dossierId, fromSequenceNo, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	public List<DossierAction> findDossierActionByDID_STEP(long dossierId, String fromStepCode) {
		return dossierActionPersistence.findByDID_STEP(dossierId, fromStepCode);
	}
}