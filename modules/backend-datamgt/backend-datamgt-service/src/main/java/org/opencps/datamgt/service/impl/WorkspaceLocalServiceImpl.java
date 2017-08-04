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

import java.util.Date;
import java.util.LinkedHashMap;

import org.opencps.datamgt.constants.WorkspaceTerm;
import org.opencps.datamgt.model.Workspace;
import org.opencps.datamgt.service.base.WorkspaceLocalServiceBaseImpl;

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

import aQute.bnd.annotation.ProviderType;

/**
 * The implementation of the workspace local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.datamgt.service.WorkspaceLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Binhth
 * @see WorkspaceLocalServiceBaseImpl
 * @see org.opencps.datamgt.service.WorkspaceLocalServiceUtil
 */
@ProviderType
public class WorkspaceLocalServiceImpl extends WorkspaceLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.mobilink.backend.datamgt.service.WorkspaceLocalServiceUtil} to access
	 * the workspace local service.
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Workspace addWorkspace(long userId, long groupId, String name, int seqOrder, ServiceContext serviceContext)
			throws Exception {

		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		long workspaceId = counterLocalService.increment(Workspace.class.getName());

		Workspace workspace = workspacePersistence.create(workspaceId);

		// Group instance
		workspace.setGroupId(groupId);

		// Audit fields
		workspace.setUuid(serviceContext.getUuid());
		workspace.setCompanyId(user.getCompanyId());
		workspace.setUserId(user.getUserId());
		workspace.setUserName(user.getFullName());
		workspace.setCreateDate(serviceContext.getCreateDate(now));
		workspace.setModifiedDate(serviceContext.getCreateDate(now));

		workspace.setName(name);
		workspace.setSeqOrder(seqOrder);

		workspace.setExpandoBridgeAttributes(serviceContext);

		workspacePersistence.update(workspace);

		return workspace;
	}

	/**
	 * @param dictCollectionId
	 * @param serviceContext
	 * @return
	 * @throws Exception
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Workspace deleteWorkspace(long workspaceId, ServiceContext serviceContext) throws Exception {

		// User user =
		// userPersistence.findByPrimaryKey(serviceContext.getUserId());

		Workspace workspace = workspacePersistence.remove(workspaceId);

		return workspace;

	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Workspace updateWorkspace(long userId, long groupId, long workspaceId, String name, int seqOrder,
			ServiceContext serviceContext) throws Exception {

		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		Workspace workspace = workspacePersistence.fetchByPrimaryKey(workspaceId);

		// Group instance
		workspace.setGroupId(groupId);

		// Audit fields
		workspace.setUserId(user.getUserId());
		workspace.setUserName(user.getFullName());
		workspace.setModifiedDate(serviceContext.getCreateDate(now));

		workspace.setName(name);
		workspace.setSeqOrder(seqOrder);

		workspace.setExpandoBridgeAttributes(serviceContext);

		workspacePersistence.update(workspace);

		return workspace;
	}

	public Hits luceneSearchEngine(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {
		// TODO Auto-generated method stub

		Indexer<Workspace> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Workspace.class);

		searchContext.addFullQueryEntryClassName(Workspace.class.getName());
		searchContext.setEntryClassNames(new String[] { Workspace.class.getName() });
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
		String ownUser = (String) params.get(WorkspaceTerm.OWN_USER);

		BooleanQuery booleanQuery = null;
		MultiMatchQuery query = null;

		if (Validator.isNotNull(keywords)) {
			booleanQuery = BooleanQueryFactoryUtil.create(searchContext);
		} else {
			booleanQuery = indexer.getFullQuery(searchContext);
		}

		if (Validator.isNotNull(keywords)) {
			String[] keyword = keywords.split(StringPool.SPACE);
			for (String string : keyword) {

				query = new MultiMatchQuery(string);

				query.addFields(WorkspaceTerm.WORKSPACE_NAME);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(groupId)) {
			query = new MultiMatchQuery(groupId);

			query.addFields(WorkspaceTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(userId)) {
			query = new MultiMatchQuery(userId);

			query.addFields(WorkspaceTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(ownUser)) {
			query = new MultiMatchQuery(ownUser);

			query.addFields(WorkspaceTerm.OWN_USER);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, Workspace.class.getName());

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);

	}

	private static final Log _log = LogFactoryUtil.getLog(WorkspaceLocalServiceImpl.class);
}