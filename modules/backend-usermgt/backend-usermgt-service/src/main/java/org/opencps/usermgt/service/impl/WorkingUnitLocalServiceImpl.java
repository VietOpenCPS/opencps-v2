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

import com.liferay.asset.kernel.exception.DuplicateCategoryException;
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
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.opencps.usermgt.constants.CommonTerm;
import org.opencps.usermgt.constants.WorkingUnitTerm;
import org.opencps.usermgt.exception.NoSuchWorkingUnitException;
import org.opencps.usermgt.model.EmployeeJobPos;
import org.opencps.usermgt.model.WorkingUnit;
import org.opencps.usermgt.service.base.WorkingUnitLocalServiceBaseImpl;

import aQute.bnd.annotation.ProviderType;
import backend.auth.api.BackendAuthImpl;
import backend.auth.api.exception.NotFoundException;
import backend.auth.api.exception.UnauthenticationException;
import backend.auth.api.exception.UnauthorizationException;
import backend.auth.api.keys.ActionKeys;
import backend.auth.api.keys.ModelNameKeys;

/**
 * The implementation of the working unit local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.opencps.usermgt.service.WorkingUnitLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Binhth
 * @see WorkingUnitLocalServiceBaseImpl
 * @see org.opencps.usermgt.service.WorkingUnitLocalServiceUtil
 */
@ProviderType
public class WorkingUnitLocalServiceImpl extends WorkingUnitLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.opencps.usermgt.service.WorkingUnitLocalServiceUtil} to access the
	 * working unit local service.
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public WorkingUnit addWorkingUnit(long userId, long groupId, String name, String enName, String govAgencyCode,
			long parentWorkingUnitId, String sibling, String address, String telNo, String faxNo, String email,
			String website, Date ceremonyDate, ServiceContext serviceContext) throws UnauthenticationException,
			UnauthorizationException, NoSuchUserException, NotFoundException, DuplicateCategoryException {
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
		//System.out.println("WorkingUnitLocalServiceImpl.addWorkingUnit()" + parentWorkingUnitId + "//" + groupId);
		sibling = getSibling(groupId, parentWorkingUnitId, sibling);

		WorkingUnit workingUnitCheck = workingUnitPersistence.fetchByF_govAgencyCode(groupId, govAgencyCode);

		if (Validator.isNotNull(workingUnitCheck)) {
			throw new DuplicateCategoryException();
		}

		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		long workingUnitId = counterLocalService.increment(WorkingUnit.class.getName());

		WorkingUnit workingUnit = workingUnitPersistence.create(workingUnitId);

		// Group instance
		workingUnit.setGroupId(groupId);

		// Audit fields
		workingUnit.setUuid(serviceContext.getUuid());
		workingUnit.setCompanyId(user.getCompanyId());
		workingUnit.setUserId(user.getUserId());
		workingUnit.setUserName(user.getFullName());
		workingUnit.setCreateDate(serviceContext.getCreateDate(now));
		workingUnit.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields
		workingUnit.setName(name);
		workingUnit.setEnName(enName);
		workingUnit.setGovAgencyCode(govAgencyCode);
		workingUnit.setParentWorkingUnitId(parentWorkingUnitId);
		workingUnit.setSibling(sibling);

		String treeIndex = getTreeIndex(workingUnitId, parentWorkingUnitId, sibling);

		workingUnit.setTreeIndex(treeIndex);
		workingUnit.setLevel(StringUtil.count(treeIndex, StringPool.PERIOD));
		workingUnit.setAddress(address);
		workingUnit.setTelNo(telNo);
		workingUnit.setFaxNo(faxNo);
		workingUnit.setEmail(email);
		workingUnit.setWebsite(website);
		workingUnit.setCeremonyDate(ceremonyDate);

		workingUnit.setExpandoBridgeAttributes(serviceContext);

		workingUnitPersistence.update(workingUnit);

		return workingUnit;
	}

	@Indexable(type = IndexableType.REINDEX)
	public WorkingUnit addWorkingUnitPublish(long userId, long groupId, long companyId, String userName, String name,
			String enName, String govAgencyCode, long parentWorkingUnitId, String sibling, String treeIndex,
			int level, String address, String telNo, String faxNo, String email, String website, Date ceremonyDate,
			ServiceContext serviceContext) throws UnauthenticationException, UnauthorizationException,
			NoSuchUserException, NotFoundException, DuplicateCategoryException {

		WorkingUnit workingUnitCheck = workingUnitPersistence.fetchByF_govAgencyCode(groupId, govAgencyCode);
		if (workingUnitCheck == null) {
			long workingUnitId = counterLocalService.increment(WorkingUnit.class.getName());
			WorkingUnit workingUnit = workingUnitPersistence.create(workingUnitId);

			// Group instance
			Date now = new Date();
			workingUnit.setGroupId(groupId);

			// Audit fields
			workingUnit.setCompanyId(companyId);
			workingUnit.setUserId(userId);
			workingUnit.setUserName(userName);
			workingUnit.setCreateDate(now);
			workingUnit.setModifiedDate(now);

			// Other fields
			workingUnit.setName(name);
			workingUnit.setEnName(enName);
			workingUnit.setGovAgencyCode(govAgencyCode);
			workingUnit.setParentWorkingUnitId(parentWorkingUnitId);
			workingUnit.setSibling(sibling);

			workingUnit.setTreeIndex(treeIndex);
			workingUnit.setLevel(level);
			workingUnit.setAddress(address);
			workingUnit.setTelNo(telNo);
			workingUnit.setFaxNo(faxNo);
			workingUnit.setEmail(email);
			workingUnit.setWebsite(website);
			workingUnit.setCeremonyDate(ceremonyDate);

			return workingUnitPersistence.update(workingUnit);
		} else {
			// Audit fields
			workingUnitCheck.setModifiedDate(new Date());
			if(userId > 0)
				workingUnitCheck.setUserId(userId);
			if(Validator.isNotNull(userName))
				workingUnitCheck.setUserName(userName);

			// Other fields
			if(Validator.isNotNull(name))
				workingUnitCheck.setName(name);
			if(Validator.isNotNull(enName))
				workingUnitCheck.setEnName(enName);
			if(Validator.isNotNull(govAgencyCode))
				workingUnitCheck.setGovAgencyCode(govAgencyCode);
			if(Validator.isNotNull(parentWorkingUnitId))
				workingUnitCheck.setParentWorkingUnitId(parentWorkingUnitId);
			if(Validator.isNotNull(sibling))
				workingUnitCheck.setSibling(sibling);
			if(Validator.isNotNull(treeIndex))
				workingUnitCheck.setTreeIndex(treeIndex);
			if(Validator.isNotNull(level))
				workingUnitCheck.setLevel(level);
			if(Validator.isNotNull(address))
				workingUnitCheck.setAddress(address);
			if(Validator.isNotNull(telNo))
				workingUnitCheck.setTelNo(telNo);
			if(Validator.isNotNull(faxNo))
				workingUnitCheck.setFaxNo(faxNo);
			if(Validator.isNotNull(email))
				workingUnitCheck.setEmail(email);
			if(Validator.isNotNull(website))
				workingUnitCheck.setWebsite(website);
			if(Validator.isNotNull(ceremonyDate))
				workingUnitCheck.setCeremonyDate(ceremonyDate);

			return workingUnitPersistence.update(workingUnitCheck);
		}

	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	public WorkingUnit deleteWorkingUnit(long workingUnitId, ServiceContext serviceContext)
			throws UnauthenticationException, UnauthorizationException, NotFoundException {
		// authen
		BackendAuthImpl authImpl = new BackendAuthImpl();

		boolean isAuth = authImpl.isAuth(serviceContext, StringPool.BLANK, StringPool.BLANK);
		if (!isAuth) {
			throw new UnauthenticationException();
		}

		boolean hasPermission = authImpl.hasResource(serviceContext, ModelNameKeys.WORKINGUNIT_MGT_CENTER,
				ActionKeys.EDIT_DATA);

		WorkingUnit workingUnit = workingUnitPersistence.fetchByPrimaryKey(workingUnitId);

		// TODO
		List<EmployeeJobPos> listEmp = employeeJobPosPersistence.findByF_workingUnitId(workingUnitId);

		if (!hasPermission || (Validator.isNotNull(listEmp) && listEmp.size() > 0)) {
			//_log.info("Working unit has employees");
			throw new UnauthorizationException();
		}

		List<WorkingUnit> listWor = workingUnitPersistence.findByF_childs_unit(workingUnit.getGroupId(),
				workingUnit.getTreeIndex());

		// It's own tree index and it's childs
		if (Validator.isNotNull(listWor) && listWor.size() > 1) {
			throw new UnauthorizationException();
		}
		// System.out.println("After check child wu");
		try {

			workingUnit = workingUnitPersistence.remove(workingUnitId);

		} catch (NoSuchWorkingUnitException e) {
			// throw new NotFoundException();
			_log.error(e);
		}

		return workingUnit;

	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public WorkingUnit updateWorkingUnit(long userId, long workingUnitId, String name, String enName,
			String govAgencyCode, long parentWorkingUnitId, String sibling, String address, String telNo, String faxNo,
			String email, String website, long logoFileEntryId, Date ceremonyDate, ServiceContext serviceContext)
			throws UnauthenticationException, UnauthorizationException, NoSuchUserException, NotFoundException,
			DuplicateCategoryException {
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

		WorkingUnit workingUnit = workingUnitPersistence.fetchByPrimaryKey(workingUnitId);

		WorkingUnit workingUnitCheck = workingUnitPersistence.fetchByF_govAgencyCode(workingUnit.getGroupId(),
				govAgencyCode);

		if (Validator.isNotNull(workingUnitCheck) && workingUnitId != workingUnitCheck.getWorkingUnitId()) {
			throw new DuplicateCategoryException();
		}

		// Audit fields
		workingUnit.setUserId(user.getUserId());
		workingUnit.setUserName(user.getFullName());
		workingUnit.setModifiedDate(serviceContext.getCreateDate(now));

		// Other fields
		workingUnit.setName(name);
		workingUnit.setEnName(enName);
		workingUnit.setGovAgencyCode(govAgencyCode);
		workingUnit.setParentWorkingUnitId(parentWorkingUnitId);
		workingUnit.setSibling(sibling);

		String treeIndex = getTreeIndex(workingUnitId, parentWorkingUnitId, sibling);

		workingUnit.setTreeIndex(treeIndex);
		workingUnit.setLevel(StringUtil.count(treeIndex, StringPool.PERIOD));
		workingUnit.setAddress(address);
		workingUnit.setTelNo(telNo);
		workingUnit.setFaxNo(faxNo);
		workingUnit.setEmail(email);
		workingUnit.setWebsite(website);
		workingUnit.setLogoFileEntryId(logoFileEntryId);
		workingUnit.setCeremonyDate(ceremonyDate);

		workingUnitPersistence.update(workingUnit);

		return workingUnit;
	}

	protected String getSibling(long groupId, long parentId, String sibling) {
		int level = 0;

		if (parentId == 0) {

		} else {

			WorkingUnit parentItem = workingUnitPersistence.fetchByPrimaryKey(parentId);

			level = Validator.isNotNull(parentItem) ? parentItem.getLevel() + 1 : 0;
		}
		WorkingUnit workingUnit = workingUnitPersistence.fetchByF_parentId_level_Last(groupId, parentId, level, null);
		if ((Validator.isNotNull(workingUnit) && CommonTerm.STR_ZERO.equals(sibling))
				|| CommonTerm.STR_ZERO.equals(sibling)) {
			try {
				sibling = workingUnit.getSibling() + 1 + StringPool.BLANK;
			} catch (Exception e) {
				_log.error(e);
				sibling = String.valueOf(1);
			}
		}

		return sibling;

	}

	public Hits luceneSearchEngine(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {
		String keywords = (String) params.get("keywords");
		String groupId = (String) params.get(WorkingUnitTerm.GROUP_ID);
		String parentWorkingUnitId = (String) params.get(WorkingUnitTerm.PARENT_WORKING_UNIT_ID);
		String workingUnitId = (String) params.get(WorkingUnitTerm.WORKINGUNIT_ID);
		String govAgencyCodeTree = (String) params.get("govAgencyCodeTree");

		Indexer<WorkingUnit> indexer = IndexerRegistryUtil.nullSafeGetIndexer(WorkingUnit.class);

		searchContext.addFullQueryEntryClassName(WorkingUnit.class.getName());
		searchContext.setEntryClassNames(new String[] { WorkingUnit.class.getName() });
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

				query.addFields(WorkingUnitTerm.NAME, WorkingUnitTerm.ENNAME);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(workingUnitId)) {
			MultiMatchQuery query = new MultiMatchQuery(workingUnitId);

			query.addFields(WorkingUnitTerm.WORKINGUNIT_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(parentWorkingUnitId)) {
			MultiMatchQuery query = new MultiMatchQuery(parentWorkingUnitId);

			query.addFields(WorkingUnitTerm.PARENT_WORKING_UNIT_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(WorkingUnitTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(govAgencyCodeTree)) {
			MultiMatchQuery query = new MultiMatchQuery(govAgencyCodeTree);

			query.addFields("govAgencyCodeTree");

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, WorkingUnit.class.getName());

		return IndexSearcherHelperUtil.search(searchContext, booleanQuery);

	}

	public long countLuceneSearchEngine(LinkedHashMap<String, Object> params, SearchContext searchContext)
			throws ParseException, SearchException {
		String keywords = (String) params.get("keywords");
		String groupId = (String) params.get(WorkingUnitTerm.GROUP_ID);
		String parentWorkingUnitId = (String) params.get(WorkingUnitTerm.PARENT_WORKING_UNIT_ID);
		String workingUnitId = (String) params.get(WorkingUnitTerm.WORKINGUNIT_ID);
		String govAgencyCodeTree = (String) params.get("govAgencyCodeTree");

		Indexer<WorkingUnit> indexer = IndexerRegistryUtil.nullSafeGetIndexer(WorkingUnit.class);

		searchContext.addFullQueryEntryClassName(WorkingUnit.class.getName());
		searchContext.setEntryClassNames(new String[] { WorkingUnit.class.getName() });
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

				query.addFields(WorkingUnitTerm.NAME, WorkingUnitTerm.ENNAME);

				booleanQuery.add(query, BooleanClauseOccur.MUST);

			}
		}

		if (Validator.isNotNull(workingUnitId)) {
			MultiMatchQuery query = new MultiMatchQuery(workingUnitId);

			query.addFields(WorkingUnitTerm.WORKINGUNIT_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(parentWorkingUnitId)) {
			MultiMatchQuery query = new MultiMatchQuery(parentWorkingUnitId);

			query.addFields(WorkingUnitTerm.PARENT_WORKING_UNIT_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(groupId)) {
			MultiMatchQuery query = new MultiMatchQuery(groupId);

			query.addFields(WorkingUnitTerm.GROUP_ID);

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		if (Validator.isNotNull(govAgencyCodeTree)) {
			MultiMatchQuery query = new MultiMatchQuery(govAgencyCodeTree);

			query.addFields("govAgencyCodeTree");

			booleanQuery.add(query, BooleanClauseOccur.MUST);
		}

		booleanQuery.addRequiredTerm(Field.ENTRY_CLASS_NAME, WorkingUnit.class.getName());

		return IndexSearcherHelperUtil.searchCount(searchContext, booleanQuery);

	}

	protected String getTreeIndex(long workingUnitId, long parentWorkingUnitId, String sibling)
			throws NotFoundException {

		if (parentWorkingUnitId == 0) {

			String ext = "";

			for (int i = 0; i < 4 - sibling.length(); i++) {

				ext += "0";

			}

			return ext + sibling;

		} else if (parentWorkingUnitId > 0) {

			WorkingUnit parentItem;
			try {
				parentItem = workingUnitPersistence.findByPrimaryKey(parentWorkingUnitId);

				String ext = "";

				for (int i = 0; i < 4 - sibling.length(); i++) {
					ext += "0";
				}

				return parentItem.getTreeIndex() + StringPool.PERIOD + ext
						+ Integer.toHexString(Integer.valueOf(sibling));

			} catch (NoSuchWorkingUnitException e) {
				// throw new NotFoundException();
				_log.error(e);
			}
		}
		// else {
		// throw new NotFoundException();
		// }
		return StringPool.BLANK;
	}

	public WorkingUnit getWorkingUnitbyGidandWid(long groupId, long workingUnitId) {
		return workingUnitPersistence.fetchByF_WID(groupId, workingUnitId);
	}

	// super_admin Generators
	@Indexable(type = IndexableType.DELETE)
	public WorkingUnit adminProcessDelete(Long id) {

		WorkingUnit object = workingUnitPersistence.fetchByPrimaryKey(id);

		if (Validator.isNull(object)) {
			return null;
		} else {
			workingUnitPersistence.remove(object);
		}

		return object;
	}

	@Indexable(type = IndexableType.REINDEX)
	public WorkingUnit adminProcessData(JSONObject objectData) {

		WorkingUnit object = null;

		if (objectData.getLong("workingUnitId") > 0) {

			object = workingUnitPersistence.fetchByPrimaryKey(objectData.getLong("workingUnitId"));

			object.setModifiedDate(new Date());

		} else {

			long id = CounterLocalServiceUtil.increment(WorkingUnit.class.getName());

			object = workingUnitPersistence.create(id);

			object.setGroupId(objectData.getLong("groupId"));
			object.setCompanyId(objectData.getLong("companyId"));
			object.setCreateDate(new Date());

		}

		object.setUserId(objectData.getLong("userId"));

		object.setName(objectData.getString("name"));
		object.setEnName(objectData.getString("enName"));
		object.setGovAgencyCode(objectData.getString("govAgencyCode"));
		object.setParentWorkingUnitId(objectData.getLong("parentWorkingUnitId"));
		object.setAddress(objectData.getString("address"));
		object.setTelNo(objectData.getString("telNo"));
		object.setFaxNo(objectData.getString("faxNo"));
		object.setEmail(objectData.getString("email"));
		object.setWebsite(objectData.getString("website"));
//		object.setLogoFileEntryId(objectData.getString("actionCode")logoFileEntryId);
		object.setCeremonyDate(new Date(objectData.getLong("ceremonyDate")));
		
		String sibling = getSibling(objectData.getLong("groupId"), objectData.getLong("parentWorkingUnitId"), objectData.getString("sibling"));
		
		object.setSibling(sibling);
		
		String treeIndex;
		try {
			treeIndex = getTreeIndex(objectData.getLong("workingUnitId"), objectData.getLong("parentWorkingUnitId"), objectData.getString("sibling"));
			object.setTreeIndex(treeIndex);
			object.setLevel(StringUtil.count(treeIndex, StringPool.PERIOD));
		} catch (NotFoundException e) {
			_log.error(e);
			object.setLevel(0);
			object.setTreeIndex(StringPool.BLANK);
		}
		
		workingUnitPersistence.update(object);

		return object;
	}

	public WorkingUnit fetchByF_govAgencyCode(long groupId, String govAgencyCode) {
		return workingUnitPersistence.fetchByF_govAgencyCode(groupId, govAgencyCode);
	}
	
	private final Log _log =
			LogFactoryUtil.getLog(WorkingUnitLocalServiceImpl.class);	
}