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

package org.opencps.datamgt.service.impl;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
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
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.NotFoundException;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.auth.api.keys.ActionKeys;
import org.opencps.auth.api.keys.ModelNameKeys;
import org.opencps.datamgt.constants.WorkTimeTerm;
import org.opencps.datamgt.exception.NoSuchWorkTimeException;
import org.opencps.datamgt.model.Holiday;
import org.opencps.datamgt.model.WorkTime;
import org.opencps.datamgt.service.base.WorkTimeLocalServiceBaseImpl;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the work time local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.datamgt.service.WorkTimeLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Binhth
 * @see WorkTimeLocalServiceBaseImpl
 * @see org.opencps.datamgt.service.WorkTimeLocalServiceUtil
 */
@ProviderType
public class WorkTimeLocalServiceImpl extends WorkTimeLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.datamgt.service.WorkTimeLocalServiceUtil} to access
	 * the work time local service.
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public WorkTime addWorkTime(long userId, long groupId, int day, String hours, ServiceContext serviceContext)
			throws UnauthenticationException, UnauthorizationException, NoSuchUserException {
		// authen
		BackendAuthImpl authImpl = new BackendAuthImpl();

		boolean isAuth = authImpl.isAuth(serviceContext);

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

		long workTimeId = counterLocalService.increment(WorkTime.class.getName());

		WorkTime workTime = workTimePersistence.create(workTimeId);

		// Group instance
		workTime.setGroupId(groupId);

		// Audit fields
		workTime.setUuid(serviceContext.getUuid());
		workTime.setCompanyId(user.getCompanyId());
		workTime.setUserId(user.getUserId());
		workTime.setUserName(user.getFullName());
		workTime.setCreateDate(serviceContext.getCreateDate(now));
		workTime.setModifiedDate(serviceContext.getCreateDate(now));

		workTime.setDay(day);
		workTime.setHours(hours);

		workTime.setExpandoBridgeAttributes(serviceContext);

		return workTimePersistence.update(workTime);
		//return workTime;
	}

	/**
	 * @param dictCollectionId
	 * @param serviceContext
	 * @return
	 * @throws Exception
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public WorkTime deleteWorkTime(long workTimeId, ServiceContext serviceContext)
			throws UnauthenticationException, UnauthorizationException, NotFoundException {
		// authen
		BackendAuthImpl authImpl = new BackendAuthImpl();

		boolean isAuth = authImpl.isAuth(serviceContext);

		if (!isAuth) {
			throw new UnauthenticationException();
		}

		boolean hasPermission = authImpl.hasResource(serviceContext, ModelNameKeys.WORKINGUNIT_MGT_CENTER,
				ActionKeys.EDIT_DATA);

		if (!hasPermission) {
			throw new UnauthorizationException();
		}

		WorkTime workTime = null;

		try {
			workTime = workTimePersistence.remove(workTimeId);
		} catch (NoSuchWorkTimeException e) {
			_log.error(e);
		}

		return workTime;

	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public WorkTime updateWorkTime(long userId, long workTimeId, int day, String hours,
			ServiceContext serviceContext)
			throws UnauthenticationException, UnauthorizationException, NotFoundException, NoSuchUserException {
		// authen
		BackendAuthImpl authImpl = new BackendAuthImpl();

		boolean isAuth = authImpl.isAuth(serviceContext);

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

		WorkTime workTime = workTimePersistence.fetchByPrimaryKey(workTimeId);

		if (Validator.isNull(workTime)) {
			throw new NotFoundException();
		}

		// Audit fields
		workTime.setUserId(user.getUserId());
		workTime.setUserName(user.getFullName());
		workTime.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields
		workTime.setDay(day);
		workTime.setHours(hours);

		workTime.setExpandoBridgeAttributes(serviceContext);

		workTimePersistence.update(workTime);

		return workTime;
	}

	public WorkTime fetchByF_day(long groupId, int day) {
		return workTimePersistence.fetchByF_day(groupId, day);
	}
	
	public Hits luceneSearchEngine(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {
		// TODO Auto-generated method stub

		Indexer<WorkTime> indexer = IndexerRegistryUtil.nullSafeGetIndexer(WorkTime.class);

		searchContext.addFullQueryEntryClassName(WorkTime.class.getName());
		searchContext.setEntryClassNames(new String[] { WorkTime.class.getName() });
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

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		} else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(WorkTimeTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(userId)) {
			MultiMatchQuery query = new MultiMatchQuery(userId);

			query.addFields(WorkTimeTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, WorkTime.class.getName());

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);

	}

	public long countLuceneSearchEngine(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {
		// TODO Auto-generated method stub

		Indexer<WorkTime> indexer = IndexerRegistryUtil.nullSafeGetIndexer(WorkTime.class);

		searchContext.addFullQueryEntryClassName(WorkTime.class.getName());
		searchContext.setEntryClassNames(new String[] { WorkTime.class.getName() });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setAndSearch(true);

		searchContext.setAttribute("params", params);

		// LAY CAC THAM SO TRONG PARAMS.
		String keywords = (String) params.get("keywords");
		String groupId = (String) params.get("groupId");
		String userId = (String) params.get("userId");

		BooleanQuery booleanQuery = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		} else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(WorkTimeTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(userId)) {
			MultiMatchQuery query = new MultiMatchQuery(userId);

			query.addFields(WorkTimeTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, WorkTime.class.getName());

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);

	}

	//Add find by groupId
	public List<WorkTime> getByGroupId(long groupId) {
		return workTimePersistence.findByF_GID(groupId);
	}


	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public WorkTime adminProcessDelete(Long id) {

		WorkTime object = workTimePersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			workTimePersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public WorkTime adminProcessData(JSONObject objectData) {

		WorkTime object = null;

		if (objectData.getLong("workTimeId") > 0) {

			object = workTimePersistence.fetchByPrimaryKey(objectData.getLong("workTimeId"));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(WorkTime.class.getName());

			object = workTimePersistence.create(id);

			object.setGroupId(objectData.getLong("groupId"));
			object.setCompanyId(objectData.getLong("companyId"));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong("userId"));

		object.setDay(objectData.getInt("day"));
		object.setHours(objectData.getString("hours"));
		
		workTimePersistence.update(object);

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public WorkTime updateWorkTimeDB(long userId, long groupId, int workTimeDay, String workTimeHours)
			throws NoSuchUserException {

		Date now = new Date();
		User user = userPersistence.findByPrimaryKey(userId);
		WorkTime workTime = workTimePersistence.fetchByF_day(groupId, workTimeDay);

		if (workTime == null) {
			long workTimeId = counterLocalService.increment(WorkTime.class.getName());
			workTime = workTimePersistence.create(workTimeId);

			// Group instance
			workTime.setGroupId(groupId);
			// Audit fields
			workTime.setCompanyId(user.getCompanyId());
			workTime.setUserId(user.getUserId());
			workTime.setUserName(user.getFullName());
			workTime.setCreateDate(now);
			workTime.setModifiedDate(now);

			// Other fields
			workTime.setDay(workTimeDay);
			workTime.setHours(workTimeHours);
		} else {
			workTime.setModifiedDate(now);
			if (Validator.isNotNull(workTimeDay))
				workTime.setDay(workTimeDay);
			if (Validator.isNotNull(workTimeHours))
				workTime.setHours(workTimeHours);
		}

		return workTimePersistence.update(workTime);
	}

	private static final Log _log = LogFactoryUtil.getLog(WorkTimeLocalServiceImpl.class);
}