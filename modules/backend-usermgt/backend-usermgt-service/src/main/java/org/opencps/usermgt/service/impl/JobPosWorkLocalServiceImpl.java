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

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.NoSuchUserException;
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

import org.opencps.usermgt.constants.JobPosWorkTerm;
import org.opencps.usermgt.model.JobPosWork;
import org.opencps.usermgt.service.base.JobPosWorkLocalServiceBaseImpl;

import aQute.bnd.annotation.ProviderType;
import backend.auth.api.BackendAuthImpl;
import backend.auth.api.exception.NotFoundException;
import backend.auth.api.exception.UnauthenticationException;
import backend.auth.api.exception.UnauthorizationException;
import backend.auth.api.keys.ActionKeys;
import backend.auth.api.keys.ModelNameKeys;

/**
 * The implementation of the job pos work local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.mobilink.backend.usermgt.service.JobPosWorkLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Binhth
 * @see JobPosWorkLocalServiceBaseImpl
 * @see org.mobilink.backend.usermgt.service.JobPosWorkLocalServiceUtil
 */
@ProviderType
public class JobPosWorkLocalServiceImpl extends JobPosWorkLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.mobilink.backend.usermgt.service.JobPosWorkLocalServiceUtil} to
	 * access the job pos work local service.
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public JobPosWork addJobPosWork(long userId, long groupId, long jobPostId, String checklistCat,
			ServiceContext serviceContext)
			throws UnauthenticationException, UnauthorizationException, NoSuchUserException, NotFoundException {
		// authen
		BackendAuthImpl authImpl = new BackendAuthImpl();

		boolean isAuth = authImpl.isAuth(serviceContext, StringPool.BLANK, StringPool.BLANK);

		if (!isAuth) {
			throw new UnauthenticationException();
		}

		boolean hasPermission = authImpl.hasResource(serviceContext, ModelNameKeys.WORKINGUNIT_MGT_CENTER,
				ActionKeys.EDIT_DATA);

		if (!hasPermission) {
			throw new UnauthorizationException();
		}

		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		long jobPosWorkId = counterLocalService.increment(JobPosWork.class.getName());

		JobPosWork jobPosWork = jobPosWorkPersistence.create(jobPosWorkId);

		// Group instance
		jobPosWork.setGroupId(groupId);

		// Audit fields
		jobPosWork.setUuid(serviceContext.getUuid());
		jobPosWork.setCompanyId(user.getCompanyId());
		jobPosWork.setUserId(user.getUserId());
		jobPosWork.setUserName(user.getFullName());
		jobPosWork.setCreateDate(serviceContext.getCreateDate(now));
		jobPosWork.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields
		jobPosWork.setJobPostId(jobPostId);
		jobPosWork.setChecklistCat(checklistCat);

		jobPosWork.setExpandoBridgeAttributes(serviceContext);

		jobPosWorkPersistence.update(jobPosWork);

		return jobPosWork;
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	public JobPosWork deleteJobPosWork(long jobPosWorkId, ServiceContext serviceContext)
			throws UnauthenticationException, UnauthorizationException, NotFoundException {
		// authen
		BackendAuthImpl authImpl = new BackendAuthImpl();

		boolean isAuth = authImpl.isAuth(serviceContext, StringPool.BLANK, StringPool.BLANK);

		if (!isAuth) {
			throw new UnauthenticationException();
		}

		boolean hasPermission = authImpl.hasResource(serviceContext, ModelNameKeys.WORKINGUNIT_MGT_CENTER,
				ActionKeys.EDIT_DATA);

		JobPosWork jobPosWork = jobPosWorkPersistence.fetchByPrimaryKey(jobPosWorkId);

//		List<Employee> listEmp = employeePersistence.findByF_groupId(jobPosWork.getGroupId());

		if (!hasPermission /* || (Validator.isNotNull(listEmp) && listEmp.size() > 0) */) {
			throw new UnauthorizationException();
		}

		if (jobPosWork != null) {
			jobPosWork = jobPosWorkPersistence.remove(jobPosWork);
		} else {
			return null;
		}
		

		return jobPosWork;

	}

	public JobPosWork fetchByF_ChecklistCat(long groupId, long jobPostId, String checklistCat) {
		return jobPosWorkPersistence.fetchByF_ChecklistCat(groupId, jobPostId, checklistCat);
	}

	public List<JobPosWork> findByF_JobPostId(long groupId, long jobPostId) {
		return jobPosWorkPersistence.findByF_JobPostId(groupId, jobPostId);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public JobPosWork updateJobPosWork(long userId, long jobPosWorkId, long jobPostId, String checklistCat,
			ServiceContext serviceContext)
			throws UnauthenticationException, UnauthorizationException, NoSuchUserException, NotFoundException {
		// authen
		BackendAuthImpl authImpl = new BackendAuthImpl();

		boolean isAuth = authImpl.isAuth(serviceContext, StringPool.BLANK, StringPool.BLANK);

		if (!isAuth) {
			throw new UnauthenticationException();
		}

		boolean hasPermission = authImpl.hasResource(serviceContext, ModelNameKeys.WORKINGUNIT_MGT_CENTER,
				ActionKeys.EDIT_DATA);

		if (!hasPermission) {
			throw new UnauthorizationException();
		}

		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		JobPosWork jobPosWork = jobPosWorkPersistence.fetchByPrimaryKey(jobPosWorkId);

		// Audit fields
		jobPosWork.setUserId(user.getUserId());
		jobPosWork.setUserName(user.getFullName());
		jobPosWork.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields
		jobPosWork.setJobPostId(jobPostId);
		jobPosWork.setChecklistCat(checklistCat);

		jobPosWorkPersistence.update(jobPosWork);

		return jobPosWork;
	}

	public Hits luceneSearchEngine(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {
		String keywords = (String) params.get("keywords");
		String groupId = (String) params.get(JobPosWorkTerm.GROUP_ID);
		String JobPosId = (String) params.get(JobPosWorkTerm.JOBPOS_ID);

		Indexer<JobPosWork> indexer = IndexerRegistryUtil.nullSafeGetIndexer(JobPosWork.class);

		searchContext.addFullQueryEntryClassName(JobPosWork.class.getName());
		searchContext.setEntryClassNames(new String[] { JobPosWork.class.getName() });
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

		if (Validator.isNotNull(JobPosId)) {
			MultiMatchQuery query = new MultiMatchQuery(JobPosId);

			query.addFields(JobPosWorkTerm.JOBPOS_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(JobPosWorkTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, JobPosWork.class.getName());

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);

	}

	public long countLuceneSearchEngine(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {
		String keywords = (String) params.get("keywords");
		String groupId = (String) params.get(JobPosWorkTerm.GROUP_ID);
		String JobPosId = (String) params.get(JobPosWorkTerm.JOBPOS_ID);

		Indexer<JobPosWork> indexer = IndexerRegistryUtil.nullSafeGetIndexer(JobPosWork.class);

		searchContext.addFullQueryEntryClassName(JobPosWork.class.getName());
		searchContext.setEntryClassNames(new String[] { JobPosWork.class.getName() });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setAndSearch(true);

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		} else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		if (Validator.isNotNull(JobPosId)) {
			MultiMatchQuery query = new MultiMatchQuery(JobPosId);

			query.addFields(JobPosWorkTerm.JOBPOS_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(JobPosWorkTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, JobPosWork.class.getName());

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);

	}

}