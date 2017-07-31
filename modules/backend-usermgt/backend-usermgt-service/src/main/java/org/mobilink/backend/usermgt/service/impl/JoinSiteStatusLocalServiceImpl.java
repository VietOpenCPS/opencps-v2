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

package org.mobilink.backend.usermgt.service.impl;

import java.util.Date;
import java.util.LinkedHashMap;

import org.mobilink.backend.usermgt.constants.JoinSiteStatusTerm;
import org.mobilink.backend.usermgt.model.JoinSiteStatus;
import org.mobilink.backend.usermgt.service.base.JoinSiteStatusLocalServiceBaseImpl;

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
import com.liferay.portal.kernel.util.Validator;

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the join site status local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.mobilink.backend.usermgt.service.JoinSiteStatusLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Binhth
 * @see JoinSiteStatusLocalServiceBaseImpl
 * @see org.mobilink.backend.usermgt.service.JoinSiteStatusLocalServiceUtil
 */
@ProviderType
public class JoinSiteStatusLocalServiceImpl extends JoinSiteStatusLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.mobilink.backend.usermgt.service.JoinSiteStatusLocalServiceUtil} to
	 * access the join site status local service.
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public JoinSiteStatus assignJoinSiteStatus(long userId, long groupId, long employeeId, long joinSiteGroupId,
			int status, ServiceContext serviceContext) throws PortalException {

		if (Validator.isNotNull(
				joinSiteStatusPersistence.fetchByF_employeeId_joinSiteGroupId(employeeId, joinSiteGroupId))) {
			throw new PortalException();
		}

		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		long JoinSiteStatusId = counterLocalService.increment(JoinSiteStatus.class.getName());

		JoinSiteStatus JoinSiteStatus = joinSiteStatusPersistence.create(JoinSiteStatusId);

		// Group instance
		JoinSiteStatus.setGroupId(groupId);

		// Audit fields
		JoinSiteStatus.setUuid(serviceContext.getUuid());
		JoinSiteStatus.setCompanyId(user.getCompanyId());
		JoinSiteStatus.setUserId(user.getUserId());
		JoinSiteStatus.setUserName(user.getFullName());
		JoinSiteStatus.setCreateDate(serviceContext.getCreateDate(now));
		JoinSiteStatus.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields
		JoinSiteStatus.setEmployeeId(employeeId);
		JoinSiteStatus.setJoinSiteGroupId(joinSiteGroupId);
		JoinSiteStatus.setStatus(status);

		JoinSiteStatus.setExpandoBridgeAttributes(serviceContext);

		joinSiteStatusPersistence.update(JoinSiteStatus);

		return JoinSiteStatus;
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public JoinSiteStatus addJoinSiteStatus(long userId, long groupId, long employeeId, long joinSiteGroupId,
			ServiceContext serviceContext) throws PortalException {
		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		long JoinSiteStatusId = counterLocalService.increment(JoinSiteStatus.class.getName());

		JoinSiteStatus joinSiteStatus = joinSiteStatusPersistence.create(JoinSiteStatusId);

		// Group instance
		joinSiteStatus.setGroupId(groupId);

		// Audit fields
		joinSiteStatus.setUuid(serviceContext.getUuid());
		joinSiteStatus.setCompanyId(user.getCompanyId());
		joinSiteStatus.setUserId(user.getUserId());
		joinSiteStatus.setUserName(user.getFullName());
		joinSiteStatus.setCreateDate(serviceContext.getCreateDate(now));
		joinSiteStatus.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields
		joinSiteStatus.setEmployeeId(employeeId);
		joinSiteStatus.setJoinSiteGroupId(joinSiteGroupId);
		joinSiteStatus.setStatus(1);

		joinSiteStatus.setExpandoBridgeAttributes(serviceContext);

		joinSiteStatusPersistence.update(joinSiteStatus);

		return joinSiteStatus;
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	public JoinSiteStatus deleteJoinSiteStatus(long JoinSiteStatusId, ServiceContext serviceContext) throws Exception {

		JoinSiteStatus joinSiteStatus = joinSiteStatusPersistence.remove(JoinSiteStatusId);

		return joinSiteStatus;

	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public JoinSiteStatus updateJoinSiteStatus(long userId, long JoinSiteStatusId, long joinSiteGroupId, int status,
			ServiceContext serviceContext) throws PortalException {
		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		JoinSiteStatus joinSiteStatus = joinSiteStatusPersistence.fetchByPrimaryKey(JoinSiteStatusId);

		// Audit fields
		joinSiteStatus.setUserId(user.getUserId());
		joinSiteStatus.setUserName(user.getFullName());
		joinSiteStatus.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields
		joinSiteStatus.setJoinSiteGroupId(joinSiteGroupId);
		joinSiteStatus.setStatus(status);

		joinSiteStatusPersistence.update(joinSiteStatus);

		return joinSiteStatus;
	}

	@SuppressWarnings("deprecation")
	public Hits luceneSearchEngine(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {
		// TODO Auto-generated method stub
		String keywords = (String) params.get("keywords");
		String employeeId = (String) params.get(JoinSiteStatusTerm.EMPLOYEE_ID);
		String groupId = (String) params.get(JoinSiteStatusTerm.GROUP_ID);
		String joinSiteGroupId = (String) params.get(JoinSiteStatusTerm.JOIN_SITE_GROUP_ID);
		String status = (String) params.get(JoinSiteStatusTerm.STATUS);

		Indexer<JoinSiteStatus> indexer = IndexerRegistryUtil.nullSafeGetIndexer(JoinSiteStatus.class);

		searchContext.addFullQueryEntryClassName(JoinSiteStatus.class.getName());
		searchContext.setEntryClassNames(new String[] { JoinSiteStatus.class.getName() });
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

		if (Validator.isNotNull(employeeId)) {
			MultiMatchQuery query = new MultiMatchQuery(employeeId);

			query.addFields(JoinSiteStatusTerm.EMPLOYEE_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(joinSiteGroupId)) {
			MultiMatchQuery query = new MultiMatchQuery(joinSiteGroupId);

			query.addFields(JoinSiteStatusTerm.JOIN_SITE_GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(status)) {
			MultiMatchQuery query = new MultiMatchQuery(status);

			query.addFields(JoinSiteStatusTerm.STATUS);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(JoinSiteStatusTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, JoinSiteStatus.class.getName());

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);

	}
}