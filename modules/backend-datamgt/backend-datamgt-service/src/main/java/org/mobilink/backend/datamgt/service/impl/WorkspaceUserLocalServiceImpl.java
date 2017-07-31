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

package org.mobilink.backend.datamgt.service.impl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.mobilink.backend.datamgt.constants.WorkspaceUserTerm;
import org.mobilink.backend.datamgt.model.Workspace;
import org.mobilink.backend.datamgt.model.WorkspaceUser;
import org.mobilink.backend.datamgt.service.WorkspaceLocalServiceUtil;
import org.mobilink.backend.datamgt.service.base.WorkspaceUserLocalServiceBaseImpl;

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
 * The implementation of the workspace user local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.mobilink.backend.datamgt.service.WorkspaceUserLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Binhth
 * @see WorkspaceUserLocalServiceBaseImpl
 * @see org.mobilink.backend.datamgt.service.WorkspaceUserLocalServiceUtil
 */
@ProviderType
public class WorkspaceUserLocalServiceImpl extends WorkspaceUserLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.mobilink.backend.datamgt.service.WorkspaceUserLocalServiceUtil} to
	 * access the workspace user local service.
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public WorkspaceUser addWorkspaceUser(long userId, long groupId, long workspaceId, long assignUserId,
			ServiceContext serviceContext) throws Exception {

		User user = userPersistence.findByPrimaryKey(userId);

		Date now = new Date();

		long workspaceUserId = counterLocalService.increment(WorkspaceUser.class.getName());

		WorkspaceUser workspaceuser = workspaceUserPersistence.create(workspaceUserId);

		// Group instance
		workspaceuser.setGroupId(groupId);

		// Audit fields
		workspaceuser.setUuid(serviceContext.getUuid());
		workspaceuser.setCompanyId(user.getCompanyId());
		workspaceuser.setUserId(user.getUserId());
		workspaceuser.setUserName(user.getFullName());
		workspaceuser.setCreateDate(serviceContext.getCreateDate(now));
		workspaceuser.setModifiedDate(serviceContext.getCreateDate(now));

		workspaceuser.setWorkspaceId(workspaceId);
		workspaceuser.setAssignUserId(assignUserId);

		workspaceuser.setExpandoBridgeAttributes(serviceContext);

		workspaceUserPersistence.update(workspaceuser);

		Workspace mWorkspace = workspacePersistence.fetchByPrimaryKey(workspaceId);
		WorkspaceLocalServiceUtil.updateWorkspace(mWorkspace.getUserId(), mWorkspace.getGroupId(),
				mWorkspace.getWorkspaceId(), mWorkspace.getName(), mWorkspace.getSeqOrder(), serviceContext);

		return workspaceuser;
	}

	/**
	 * @param dictCollectionId
	 * @param serviceContext
	 * @return
	 * @throws Exception
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public WorkspaceUser deleteWorkspaceUser(long workspaceUserId, ServiceContext serviceContext) throws Exception {

//		User user = userPersistence.findByPrimaryKey(serviceContext.getUserId());

		WorkspaceUser workspaceuser = workspaceUserPersistence.remove(workspaceUserId);

		Workspace mWorkspace = workspacePersistence.fetchByPrimaryKey(workspaceuser.getWorkspaceId());

		WorkspaceLocalServiceUtil.updateWorkspace(mWorkspace.getUserId(), mWorkspace.getGroupId(),
				mWorkspace.getWorkspaceId(), mWorkspace.getName(), mWorkspace.getSeqOrder(), serviceContext);

		return workspaceuser;

	}

	/**
	 * @param userId
	 * @param dictCollectionId
	 * @param fullName
	 * @param companyName
	 * @param telNo
	 * @param email
	 * @param mobilinkId
	 * @param userMappingId
	 * @param outSide
	 * @param isOrg
	 * @param serviceContext
	 * @return
	 * @throws Exception
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public WorkspaceUser updateWorkspaceUser(long userId, long groupId, long workspaceUserId, long workspaceId,
			long assignUserId, ServiceContext serviceContext) throws Exception {

		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		WorkspaceUser workspaceuser = workspaceUserPersistence.fetchByPrimaryKey(workspaceUserId);

		// Group instance
		workspaceuser.setGroupId(groupId);

		// Audit fields
		workspaceuser.setUserId(user.getUserId());
		workspaceuser.setUserName(user.getFullName());
		workspaceuser.setModifiedDate(serviceContext.getCreateDate(now));

		workspaceuser.setWorkspaceId(workspaceId);
		workspaceuser.setAssignUserId(assignUserId);
		workspaceuser.setExpandoBridgeAttributes(serviceContext);

		workspaceUserPersistence.update(workspaceuser);

		Workspace mWorkspace = workspacePersistence.fetchByPrimaryKey(workspaceId);
		
		WorkspaceLocalServiceUtil.updateWorkspace(mWorkspace.getUserId(), mWorkspace.getGroupId(),
				mWorkspace.getWorkspaceId(), mWorkspace.getName(), mWorkspace.getSeqOrder(), serviceContext);

		return workspaceuser;
	}

	public Hits workspaceUserFilter(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {
		// TODO Auto-generated method stub

		Indexer<WorkspaceUser> indexer = IndexerRegistryUtil.nullSafeGetIndexer(WorkspaceUser.class);

		searchContext.addFullQueryEntryClassName(WorkspaceUser.class.getName());
		searchContext.setEntryClassNames(new String[] { WorkspaceUser.class.getName() });
		searchContext.setAttribute("paginationType", "regular");
		searchContext.setLike(true);
		searchContext.setStart(start);
		searchContext.setEnd(end);
		searchContext.setAndSearch(true);
		searchContext.setSorts(sorts);

		searchContext.setAttribute("params", params);

		// LAY CAC THAM SO TRONG PARAMS.
		String workspaceId = (String) params.get(WorkspaceUserTerm.WORKSPACE_ID);
		String keywords = (String) params.get("keywords");
		String groupId = (String) params.get("groupId");
		String userId = (String) params.get("userId");
		String assignUserId = (String) params.get(WorkspaceUserTerm.ASSIGN_USER_ID);

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

				query.addFields(WorkspaceUserTerm.WORKSPACE_ID);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(workspaceId)) {
			query = new MultiMatchQuery(workspaceId);

			query.addFields(WorkspaceUserTerm.WORKSPACE_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(groupId)) {
			query = new MultiMatchQuery(groupId);

			query.addFields(WorkspaceUserTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(userId)) {
			query = new MultiMatchQuery(userId);

			query.addFields(WorkspaceUserTerm.USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(assignUserId)) {
			query = new MultiMatchQuery(assignUserId);

			query.addFields(WorkspaceUserTerm.ASSIGN_USER_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, WorkspaceUser.class.getName());

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);

	}

	public List<WorkspaceUser> findByF_workspaceId(long workspaceId) {
		
		return workspaceUserPersistence.findByF_workspaceId(workspaceId);
		
	}
	private static final Log _log = LogFactoryUtil.getLog(WorkspaceUserLocalServiceImpl.class);
}