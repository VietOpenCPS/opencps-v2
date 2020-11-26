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
import com.liferay.portal.kernel.search.generic.TermRangeQueryImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.ws.rs.NotFoundException;

import org.opencps.dossiermgt.constants.ConstantsTerm;

import backend.feedback.constants.VotingTerm;
import backend.feedback.exception.NoSuchVotingException;
import backend.feedback.model.Voting;
import backend.feedback.service.base.VotingLocalServiceBaseImpl;
import backend.feedback.service.util.ConfigConstants;

/**
 * The implementation of the voting local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link backend.feedback.service.VotingLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author sondt
 * @see VotingLocalServiceBaseImpl
 * @see backend.feedback.service.VotingLocalServiceUtil
 */
public class VotingLocalServiceImpl extends VotingLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * backend.feedback.service.VotingLocalServiceUtil} to access the voting local
	 * service.
	 */

	private static Log _log = LogFactoryUtil.getLog(VotingLocalServiceImpl.class);

	@Indexable(type = IndexableType.REINDEX)
	public Voting addVoting(long userId, long groupId, String className, String classPK, String subject, String choices,
			String templateNo, boolean commentable, ServiceContext serviceContext) throws NoSuchUserException {

		User user = userPersistence.findByPrimaryKey(userId);

		long votingId = counterLocalService.increment(Voting.class.getName());

		Voting voting = votingPersistence.create(votingId);

		Date now = new Date();

		// Group instance
		voting.setGroupId(groupId);

		// Audit fields
		voting.setUuid(serviceContext.getUuid());
		voting.setCompanyId(user.getCompanyId());
		voting.setUserId(user.getUserId());
		voting.setUserName(user.getFullName());
		voting.setCreateDate(serviceContext.getCreateDate(now));
		voting.setModifiedDate(serviceContext.getCreateDate(now));

		voting.setClassName(className);
		voting.setClassPK(classPK);
		voting.setSubject(subject);
		voting.setChoices(choices);
		voting.setTemplateNo(templateNo);
		voting.setCommentable(commentable);

		voting.setExpandoBridgeAttributes(serviceContext);

		return votingPersistence.update(voting);

	}

	/**
	 * @param dictCollectionId
	 * @param serviceContext
	 * @return
	 * @throws NoSuchVotingException
	 * @throws NotFoundException
	 * @throws Exception
	 */
	@Indexable(type = IndexableType.DELETE)
	public Voting deleteVote(long votingId, ServiceContext serviceContext) throws NoSuchVotingException {

		// try {
		return votingPersistence.remove(votingId);

		// } catch (NoSuchVotingException e) {
		// throw new NoSuchVotingException();
		// }
	}

	@Indexable(type = IndexableType.REINDEX)
	public Voting updateVote(long userId, long votingId, String className, String classPK, String subject,
			String choices, String templateNo, boolean commentable, ServiceContext serviceContext)
			throws NoSuchUserException {

		User user = userPersistence.findByPrimaryKey(userId);

		Voting voting = votingPersistence.fetchByPrimaryKey(votingId);

		// if (Validator.isNull(voting)) {
		// throw new NotFoundException();
		// }

		Date now = new Date();

		// Audit fields
		voting.setUserId(user.getUserId());
		voting.setUserName(user.getFullName());
		voting.setModifiedDate(serviceContext.getCreateDate(now));

		voting.setClassName(className);
		voting.setClassPK(classPK);
		voting.setSubject(subject);
		voting.setChoices(choices);
		voting.setTemplateNo(templateNo);
		voting.setCommentable(commentable);

		voting.setExpandoBridgeAttributes(serviceContext);

		return votingPersistence.update(voting);

	}

	public Hits luceneSearchEngine(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {
		// TODO Auto-generated method stub

		Indexer<Voting> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Voting.class);

		searchContext.addFullQueryEntryClassName(Voting.class.getName());
		searchContext.setEntryClassNames(new String[] { Voting.class.getName() });
		searchContext.setAttribute(VotingTerm.PAGINATION_TYPE, ConfigConstants.PAGINATION_TYPE_REGULAR);
		searchContext.setLike(true);
		searchContext.setStart(start);
		searchContext.setEnd(end);
		searchContext.setAndSearch(true);
		searchContext.setSorts(sorts);

		searchContext.setAttribute(VotingTerm.PARAMS, params);

		// LAY CAC THAM SO TRONG PARAMS.
		String keywords = (String) params.get(VotingTerm.KEYWORDS);
		String groupId = (String) params.get(Field.GROUP_ID);
		_log.info("groupId: " + groupId);
//		String userId = (String) params.get(VotingTerm.USER_ID);
		String className = (String) params.get(VotingTerm.CLASS_NAME);
		String classPK = (String) params.get(VotingTerm.CLASS_PK);
		String fromVotingDate = GetterUtil.getString(params.get(VotingTerm.FROM_VOTING_DATE));
		String toVotingDate = GetterUtil.getString(params.get(VotingTerm.TO_VOTING_DATE));
		_log.info("strFromVotingDate: "+fromVotingDate);
		_log.info("strToVotingDate: "+toVotingDate);

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

				query.addFields(VotingTerm.SUBJECT);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

//		if (Validator.isNotNull(userId)) {
//			MultiMatchQuery query = new MultiMatchQuery(userId);
//
//			query.addFields(VotingTerm.USER_ID);
//
//			booleanQuery.add(query, BooleanClauseOccur.MUST);
//		}

		if (Validator.isNotNull(className)) {
			MultiMatchQuery query = new MultiMatchQuery(className);

			query.addFields(VotingTerm.CLASS_NAME);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(classPK)) {
			MultiMatchQuery query = new MultiMatchQuery(classPK);

			query.addFields(VotingTerm.CLASS_PK);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		String fromVotingDateFilter = fromVotingDate + ConstantsTerm.HOUR_START;
		String toVotingDateFilter = toVotingDate + ConstantsTerm.HOUR_END;
		_log.info("FROM VOTING DATE FILTER: " + fromVotingDateFilter);
		_log.info("TO VOTING DATE FILTER: " + toVotingDateFilter);
		
		if (Validator.isNotNull(fromVotingDate)) {
			if (Validator.isNotNull(toVotingDate)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(Field.MODIFIED_DATE,
						fromVotingDateFilter, toVotingDateFilter, true, true);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			} else {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(Field.MODIFIED_DATE,
						fromVotingDateFilter, toVotingDateFilter, true, false);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			}
		} else {
			if (Validator.isNotNull(toVotingDate)) {
				TermRangeQueryImpl termRangeQuery = new TermRangeQueryImpl(Field.MODIFIED_DATE,
						fromVotingDateFilter, toVotingDateFilter, false, true);

				booleanQuery.add(termRangeQuery, BooleanClauseOccur.MUST);
			}
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, Voting.class.getName());

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);

	}

	public long countLuceneSearchEngine(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {
		// TODO Auto-generated method stub

		Indexer<Voting> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Voting.class);

		searchContext.addFullQueryEntryClassName(Voting.class.getName());
		searchContext.setEntryClassNames(new String[] { Voting.class.getName() });
		searchContext.setAttribute(VotingTerm.PAGINATION_TYPE, ConfigConstants.PAGINATION_TYPE_REGULAR);
		searchContext.setLike(true);
		searchContext.setAndSearch(true);
		searchContext.setAttribute(VotingTerm.PARAMS, params);

		// LAY CAC THAM SO TRONG PARAMS.
		String keywords = (String) params.get(VotingTerm.KEYWORDS);
		String groupId = (String) params.get(Field.GROUP_ID);
		String userId = (String) params.get(VotingTerm.USER_ID);
		String className = (String) params.get(VotingTerm.CLASS_NAME);
		String classPK = (String) params.get(VotingTerm.CLASS_PK);

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

				query.addFields(VotingTerm.SUBJECT);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(Field.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(userId)) {
			MultiMatchQuery query = new MultiMatchQuery(userId);

			query.addFields(VotingTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(className)) {
			MultiMatchQuery query = new MultiMatchQuery(className);

			query.addFields(VotingTerm.CLASS_NAME);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(classPK)) {
			MultiMatchQuery query = new MultiMatchQuery(classPK);

			query.addFields(VotingTerm.CLASS_PK);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, Voting.class.getName());

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);

	}

	public List<Voting> getVotingByClass_Name_PK(String className, String classPK) {
		return votingPersistence.findByF_CLNAME_CLPK(className, classPK);
	}

	public long countVotingByClass_Name_PK(String className, String classPK) {
		return votingPersistence.countByF_CLNAME_CLPK(className, classPK);
	}

	public List<Voting> getVotingByG_Class_Name_PK(long groupId, String className, String classPK) {
		return votingPersistence.findByF_G_CLNAME_CLPK(groupId, className, classPK);
	}

	public long countVotingByG_Class_Name_PK(long groupId, String className, String classPK) {
		return votingPersistence.countByF_G_CLNAME_CLPK(groupId, className, classPK);
	}

	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public Voting adminProcessDelete(Long id) {

		Voting object = votingPersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			votingPersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public Voting adminProcessData(JSONObject objectData) {

		Voting object = null;

		if (objectData.getLong(VotingTerm.VOTING_ID) > 0) {

			object = votingPersistence.fetchByPrimaryKey(objectData.getLong(VotingTerm.VOTING_ID));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(Voting.class.getName());

			object = votingPersistence.create(id);

			object.setGroupId(objectData.getLong(Field.GROUP_ID));
			object.setCompanyId(objectData.getLong(VotingTerm.COMPANY_ID));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong(VotingTerm.USER_ID));

		object.setClassName(objectData.getString(VotingTerm.CLASS_NAME));
		object.setClassPK(objectData.getString(VotingTerm.CLASS_PK));
		object.setSubject(objectData.getString(VotingTerm.SUBJECT));
		object.setChoices(objectData.getString(VotingTerm.CHOICES));
		object.setTemplateNo(objectData.getString(VotingTerm.TEMPLATE_NO));
		object.setCommentable(objectData.getBoolean(VotingTerm.COMMENTABLE));

		votingPersistence.update(object);

		return object;
	}
	
		@Indexable(type = IndexableType.DELETE)
		public void deleteVoteConfig(long votingId, ServiceContext serviceContext) throws NoSuchVotingException {
	
			Voting voting = votingPersistence.fetchByPrimaryKey(votingId);
			if (voting != null) {
				votingPersistence.removeByF_CLNAME_VC(voting.getClassName(), voting.getVotingCode());
			}

	}
		
		public List<Voting> getVotingByClass_Name_VC(String className, String votingCode) {		
			return votingPersistence.findByF_CLNAME_VC(className, votingCode);
	}

		public long countVotingByClass_Name_VC(String className, String votingCode) {
			return votingPersistence.countByF_CLNAME_VC(className, votingCode);
	}

}