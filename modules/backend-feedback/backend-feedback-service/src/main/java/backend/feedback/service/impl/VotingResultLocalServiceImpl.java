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

package backend.feedback.service.impl;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.NoSuchUserException;
import com.liferay.portal.kernel.json.JSONObject;
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
import com.liferay.portal.kernel.search.generic.TermRangeQueryImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.LinkedHashMap;

import javax.ws.rs.NotFoundException;

import org.opencps.dossiermgt.constants.ConstantsTerm;
import org.opencps.dossiermgt.constants.DossierTerm;

import backend.feedback.constants.VotingResultTerm;
import backend.feedback.constants.VotingTerm;
import backend.feedback.exception.NoSuchVotingResultException;
import backend.feedback.model.VotingResult;
import backend.feedback.service.base.VotingResultLocalServiceBaseImpl;

/**
 * The implementation of the voting result local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link backend.feedback.service.VotingResultLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author sondt
 * @see VotingResultLocalServiceBaseImpl
 * @see backend.feedback.service.VotingResultLocalServiceUtil
 */
public class VotingResultLocalServiceImpl extends VotingResultLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * backend.feedback.service.VotingResultLocalServiceUtil} to access the voting
	 * result local service.
	 */

	@Indexable(type = IndexableType.REINDEX)
	public VotingResult addVotingResult(long userId, long groupId, long votingId, String fullname, String email,
			String comment, String selected, ServiceContext serviceContext) throws NoSuchUserException {

		User user = userPersistence.findByPrimaryKey(userId);

		// Voting voting = votingPersistence.fetchByPrimaryKey(votingId);

		long votingResultId = counterLocalService.increment(VotingResult.class.getName());

		VotingResult votingResult = votingResultPersistence.create(votingResultId);

		Date now = new Date();

		// Group instance
		votingResult.setGroupId(groupId);

		// Audit fields
		votingResult.setUuid(serviceContext.getUuid());
		votingResult.setCompanyId(user.getCompanyId());
		votingResult.setUserId(user.getUserId());
		votingResult.setUserName(user.getFullName());
		votingResult.setCreateDate(serviceContext.getCreateDate(now));
		votingResult.setModifiedDate(serviceContext.getCreateDate(now));

		votingResult.setVotingId(votingId);
		votingResult.setFullname(fullname);
		votingResult.setEmail(email);
		votingResult.setComment(comment);
		votingResult.setSelected(selected);

		votingResult.setExpandoBridgeAttributes(serviceContext);

		return votingResultPersistence.update(votingResult);
		// return votingResult;
	}

	/**
	 * @param dictCollectionId
	 * @param serviceContext
	 * @return
	 * @throws NotFoundException
	 * @throws Exception
	 */
	@Indexable(type = IndexableType.DELETE)
	public VotingResult deleteVoteResult(long votingResultId, ServiceContext serviceContext)
			throws NoSuchVotingResultException {

		// try {
		return votingResultPersistence.remove(votingResultId);
		// } catch (NoSuchVotingResultException e) {
		// throw new NotFoundException();
		// }
	}

	@Indexable(type = IndexableType.REINDEX)
	public VotingResult updateVoteResult(long userId, long votingResultId, long votingId, String fullname, String email,
			String comment, String selected, ServiceContext serviceContext) throws NoSuchUserException {

		User user = userPersistence.findByPrimaryKey(userId);

		VotingResult votingResult = votingResultPersistence.fetchByPrimaryKey(votingResultId);

		if (Validator.isNull(votingResult)) {
			throw new NotFoundException();
		}

		Date now = new Date();
		// Audit fields
		votingResult.setUserId(user.getUserId());
		votingResult.setUserName(user.getFullName());
		votingResult.setModifiedDate(serviceContext.getCreateDate(now));

		votingResult.setVotingId(votingId);
		votingResult.setFullname(fullname);
		votingResult.setEmail(email);
		votingResult.setComment(comment);
		votingResult.setSelected(selected);

		votingResult.setExpandoBridgeAttributes(serviceContext);

		return votingResultPersistence.update(votingResult);

	}

	public VotingResult fetchByF_votingId_userId(long userId, long votingId) {

		return votingResultPersistence.fetchByF_votingId_userId(userId, votingId);
	}

	public int countByF_votingId_selected(long votingId, String selected) {

		return votingResultPersistence.countByF_votingId_selected(votingId, selected);
	}

	public Hits luceneSearchEngine(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {
		// TODO Auto-generated method stub

		Indexer<VotingResult> indexer = IndexerRegistryUtil.nullSafeGetIndexer(VotingResult.class);

		searchContext.addFullQueryEntryClassName(VotingResult.class.getName());
		searchContext.setEntryClassNames(new String[] { VotingResult.class.getName() });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setStart(start);
		searchContext.setEnd(end);
		searchContext.setAndSearch(true);
		searchContext.setSorts(sorts);

		searchContext.setAttribute("params", params);

		// LAY CAC THAM SO TRONG PARAMS.
		String keywords = (String) params.get("keywords");
		String groupId = (String) params.get("groupId");
		String userId = (String) params.get("userId");
		String votingId = (String) params.get(VotingResultTerm.VOTING_ID);
		int month = GetterUtil.getInteger(params.get(VotingResultTerm.MONTH_VOTING));
		int year = GetterUtil.getInteger(params.get(VotingResultTerm.YEAR_VOTING));
		String className =  (String) params.get(VotingTerm.CLASS_NAME);
		String govAgencyCode = GetterUtil.getString(params.get(VotingTerm.GOV_AGENCY_CODE));
		String fromVotingDate = GetterUtil.getString(params.get(VotingResultTerm.FROM_VOTING_DATE));
		String toVotingDate = GetterUtil.getString(params.get(VotingResultTerm.TO_VOTING_DATE));

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

				query.addFields(VotingResultTerm.EMAIL);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(VotingResultTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(userId)) {
			MultiMatchQuery query = new MultiMatchQuery(userId);

			query.addFields(VotingResultTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(votingId)) {
			MultiMatchQuery query = new MultiMatchQuery(votingId);
			
			query.addFields(VotingResultTerm.VOTING_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(month) && month > 0) {
			MultiMatchQuery query = new MultiMatchQuery(String.valueOf(month));

			query.addFields(VotingResultTerm.MONTH_VOTING);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(year) && year > 0) {
			MultiMatchQuery query = new MultiMatchQuery(String.valueOf(year));

			query.addFields(VotingResultTerm.YEAR_VOTING);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(className)) {
			MultiMatchQuery query = new MultiMatchQuery(className);
			query.addFields(VotingTerm.CLASS_NAME);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(govAgencyCode)) {
			MultiMatchQuery query = new MultiMatchQuery(govAgencyCode);
			query.addFields(VotingTerm.GOV_AGENCY_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		String fromVotingDateFilter = fromVotingDate + ConstantsTerm.HOUR_START;
		String toVotingDateFilter = toVotingDate + ConstantsTerm.HOUR_END;

		if (Validator.isNotNull(fromVotingDate)) {
			if (Validator.isNotNull(toVotingDate)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(VotingResultTerm.CREATE_DATE,
						fromVotingDateFilter, toVotingDateFilter, true, true);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			} else {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(DossierTerm.CREATE_DATE,
						fromVotingDateFilter, toVotingDateFilter, true, false);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			}
		} else {
			if (Validator.isNotNull(toVotingDate)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(DossierTerm.CREATE_DATE,
						fromVotingDateFilter, toVotingDateFilter, false, true);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			}
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, VotingResult.class.getName());

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);

	}

	public long countLuceneSearchEngine(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {
		// TODO Auto-generated method stub

		Indexer<VotingResult> indexer = IndexerRegistryUtil.nullSafeGetIndexer(VotingResult.class);

		searchContext.addFullQueryEntryClassName(VotingResult.class.getName());
		searchContext.setEntryClassNames(new String[] { VotingResult.class.getName() });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setAndSearch(true);

		searchContext.setAttribute("params", params);

		// LAY CAC THAM SO TRONG PARAMS.
		String keywords = (String) params.get("keywords");
		String groupId = (String) params.get("groupId");
		String userId = (String) params.get("userId");
		String votingId = (String) params.get(VotingResultTerm.VOTING_ID);
		int month = GetterUtil.getInteger(params.get(VotingResultTerm.MONTH_VOTING));
		int year = GetterUtil.getInteger(params.get(VotingResultTerm.YEAR_VOTING));
		String className =  (String) params.get(VotingTerm.CLASS_NAME);
		String govAgencyCode = GetterUtil.getString(params.get(VotingTerm.GOV_AGENCY_CODE));
		String fromVotingDate = GetterUtil.getString(params.get(VotingResultTerm.FROM_VOTING_DATE));
		String toVotingDate = GetterUtil.getString(params.get(VotingResultTerm.TO_VOTING_DATE));

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

				query.addFields(VotingResultTerm.EMAIL);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(VotingResultTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(userId)) {
			MultiMatchQuery query = new MultiMatchQuery(userId);

			query.addFields(VotingResultTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(votingId)) {
			MultiMatchQuery query = new MultiMatchQuery(votingId);

			query.addFields(VotingResultTerm.VOTING_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(month) && month > 0) {
			MultiMatchQuery query = new MultiMatchQuery(String.valueOf(month));

			query.addFields(VotingResultTerm.MONTH_VOTING);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(year) && year > 0) {
			MultiMatchQuery query = new MultiMatchQuery(String.valueOf(year));

			query.addFields(VotingResultTerm.YEAR_VOTING);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(className)) {
			MultiMatchQuery query = new MultiMatchQuery(className);
			query.addFields(VotingTerm.CLASS_NAME);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(govAgencyCode)) {
			MultiMatchQuery query = new MultiMatchQuery(govAgencyCode);
			query.addFields(VotingTerm.GOV_AGENCY_CODE);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		String fromVotingDateFilter = fromVotingDate + ConstantsTerm.HOUR_START;
		String toVotingDateFilter = toVotingDate + ConstantsTerm.HOUR_END;

		if (Validator.isNotNull(fromVotingDate)) {
			if (Validator.isNotNull(toVotingDate)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(VotingResultTerm.CREATE_DATE,
						fromVotingDateFilter, toVotingDateFilter, true, true);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			} else {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(DossierTerm.CREATE_DATE,
						fromVotingDateFilter, toVotingDateFilter, true, false);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			}
		} else {
			if (Validator.isNotNull(toVotingDate)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(DossierTerm.CREATE_DATE,
						fromVotingDateFilter, toVotingDateFilter, false, true);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			}
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, VotingResult.class.getName());

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);

	}

	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public VotingResult adminProcessDelete(Long id) {

		VotingResult object = votingResultPersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			votingResultPersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public VotingResult adminProcessData(JSONObject objectData) {

		VotingResult object = null;

		if (objectData.getLong("votingResultId") > 0) {

			object = votingResultPersistence.fetchByPrimaryKey(objectData.getLong("votingResultId"));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(VotingResult.class.getName());

			object = votingResultPersistence.create(id);

			object.setGroupId(objectData.getLong("groupId"));
			object.setCompanyId(objectData.getLong("companyId"));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong("userId"));

		object.setVotingId(objectData.getLong("votingId"));
		object.setFullname(objectData.getString("fullname"));
		object.setEmail(objectData.getString("email"));
		object.setComment(objectData.getString("comment"));
		object.setSelected(objectData.getString("selected"));

		votingResultPersistence.update(object);

		return object;
	}

}