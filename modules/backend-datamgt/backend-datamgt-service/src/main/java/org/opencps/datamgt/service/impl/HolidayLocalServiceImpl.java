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
import com.liferay.portal.kernel.util.StringPool;
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
import org.opencps.datamgt.constants.HolidayTerm;
import org.opencps.datamgt.exception.NoSuchHolidayException;
import org.opencps.datamgt.model.Holiday;
import org.opencps.datamgt.service.base.HolidayLocalServiceBaseImpl;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the holiday local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.datamgt.service.HolidayLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Binhth
 * @see HolidayLocalServiceBaseImpl
 * @see org.opencps.datamgt.service.HolidayLocalServiceUtil
 */
@ProviderType
public class HolidayLocalServiceImpl extends HolidayLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.datamgt.service.HolidayLocalServiceUtil} to access the holiday
	 * local service.
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Holiday addHoliday(long userId, long groupId, Date holidayDate, String description,
			ServiceContext serviceContext)
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

		long holidayId = counterLocalService.increment(Holiday.class.getName());

		Holiday holiday = holidayPersistence.create(holidayId);

		// Group instance
		holiday.setGroupId(groupId);

		// Audit fields
		holiday.setUuid(serviceContext.getUuid());
		holiday.setCompanyId(user.getCompanyId());
		holiday.setGroupId(groupId);
		holiday.setUserId(user.getUserId());
		holiday.setUserName(user.getFullName());
		holiday.setCreateDate(serviceContext.getCreateDate(now));
		holiday.setModifiedDate(serviceContext.getCreateDate(now));

		holiday.setHolidayDate(holidayDate);
		holiday.setDescription(description);

		holiday.setExpandoBridgeAttributes(serviceContext);
		return holidayPersistence.update(holiday);
	}

	/**
	 * @param dictCollectionId
	 * @param serviceContext
	 * @return
	 * @throws Exception
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Holiday deleteHoliday(long holidayId, ServiceContext serviceContext)
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

		Holiday holiday = null;

		try {
			holiday = holidayPersistence.remove(holidayId);
		} catch (NoSuchHolidayException e) {
			_log.error(e);
		}

		return holiday;

	}

	public Holiday fetchByF_holidayDate(long groupId, Date holidayDate) {
		return holidayPersistence.fetchByF_holidayDate(groupId, holidayDate);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Holiday updateHoliday(long userId, long holidayId, Date holidayDate, String description,
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

		Holiday holiday = holidayPersistence.fetchByPrimaryKey(holidayId);

		if (Validator.isNull(holiday)) {
			throw new NotFoundException();
		}

		// Audit fields
		holiday.setUserId(user.getUserId());
		holiday.setUserName(user.getFullName());
		holiday.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields
		holiday.setHolidayDate(holidayDate);
		holiday.setDescription(description);

		holiday.setExpandoBridgeAttributes(serviceContext);

		return holidayPersistence.update(holiday);
		// return holiday;
	}

	public Hits luceneSearchEngine(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {
		// TODO Auto-generated method stub

		Indexer<Holiday> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Holiday.class);

		searchContext.addFullQueryEntryClassName(Holiday.class.getName());
		searchContext.setEntryClassNames(new String[] { Holiday.class.getName() });
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
		String year = (String) params.get("year");

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

				query.addFields(HolidayTerm.DESCRIPTION);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(HolidayTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(userId)) {
			MultiMatchQuery query = new MultiMatchQuery(userId);

			query.addFields(HolidayTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(year)) {
			MultiMatchQuery query = new MultiMatchQuery(year);

			query.addFields("year");

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, Holiday.class.getName());

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);

	}

	public long countLuceneSearchEngine(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {
		// TODO Auto-generated method stub

		Indexer<Holiday> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Holiday.class);

		searchContext.addFullQueryEntryClassName(Holiday.class.getName());
		searchContext.setEntryClassNames(new String[] { Holiday.class.getName() });
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

		if (Validator.isNotNull(keywords)) {
			String[] keyword = keywords.split(StringPool.SPACE);
			for (String string : keyword) {

				MultiMatchQuery query = new MultiMatchQuery(string);

				query.addFields(HolidayTerm.DESCRIPTION);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(HolidayTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(userId)) {
			MultiMatchQuery query = new MultiMatchQuery(userId);

			query.addFields(HolidayTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, Holiday.class.getName());

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);

	}

	public List<Holiday> getHolidayByGroupId(long groupId) {
		return holidayPersistence.findByF_GID(groupId);
	}

	public List<Holiday> getHolidayByGroupIdAndType(long groupId, int holidayType) {
		return holidayPersistence.findByF_GID_TYPE(groupId, holidayType);
	}

	public List<Holiday> getHolidayGtThan(long groupId, Date holidayDate) {
		return holidayPersistence.findByF_NEWER_THAN(groupId, holidayDate);
	}

	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public Holiday adminProcessDelete(Long id) {

		Holiday object = holidayPersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			holidayPersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public Holiday adminProcessData(JSONObject objectData) {

		Holiday object = null;

		if (objectData.getLong("holidayId") > 0) {

			object = holidayPersistence.fetchByPrimaryKey(objectData.getLong("holidayId"));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(Holiday.class.getName());

			object = holidayPersistence.create(id);

			object.setGroupId(objectData.getLong("groupId"));
			object.setCompanyId(objectData.getLong("companyId"));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong("userId"));

		object.setHolidayDate(new Date(objectData.getLong("holidayDate")));
		object.setDescription(objectData.getString("description"));

		holidayPersistence.update(object);

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public Holiday updateHolidayDB(long userId, long groupId, Date holidayDate, String description, int holidayType)
			throws NoSuchUserException {

		Date now = new Date();
		User user = userPersistence.findByPrimaryKey(userId);
		Holiday holiday = holidayPersistence.fetchByF_holidayDate(groupId, holidayDate);

		if (holiday == null) {
			long holidayId = counterLocalService.increment(Holiday.class.getName());
			holiday = holidayPersistence.create(holidayId);

			// Group instance
			holiday.setGroupId(groupId);
			// Audit fields
			holiday.setCompanyId(user.getCompanyId());
			holiday.setUserId(user.getUserId());
			holiday.setUserName(user.getFullName());
			holiday.setCreateDate(now);
			holiday.setModifiedDate(now);

			// Other fields
			holiday.setHolidayDate(holidayDate);
			holiday.setDescription(description);
			holiday.setHolidayType(holidayType);
		} else {
			holiday.setModifiedDate(now);
			if (Validator.isNotNull(holidayDate))
				holiday.setHolidayDate(holidayDate);
			if (Validator.isNotNull(description))
				holiday.setDescription(description);
			if (Validator.isNotNull(holidayType)) {
				holiday.setHolidayType(holidayType);
			}
		}

		return holidayPersistence.update(holiday);
	}

	private static final Log _log = LogFactoryUtil.getLog(HolidayLocalServiceImpl.class);
}