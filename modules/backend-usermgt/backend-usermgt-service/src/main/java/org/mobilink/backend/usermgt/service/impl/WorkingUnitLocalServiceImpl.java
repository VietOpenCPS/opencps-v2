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

import org.mobilink.backend.usermgt.constants.WorkingUnitTerm;
import org.mobilink.backend.usermgt.exception.NoSuchWorkingUnitException;
import org.mobilink.backend.usermgt.model.WorkingUnit;
import org.mobilink.backend.usermgt.service.base.WorkingUnitLocalServiceBaseImpl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
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
 * The implementation of the working unit local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link org.mobilink.backend.usermgt.service.WorkingUnitLocalService}
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Binhth
 * @see WorkingUnitLocalServiceBaseImpl
 * @see org.mobilink.backend.usermgt.service.WorkingUnitLocalServiceUtil
 */
@ProviderType
public class WorkingUnitLocalServiceImpl extends WorkingUnitLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link
	 * org.mobilink.backend.usermgt.service.WorkingUnitLocalServiceUtil} to
	 * access the working unit local service.
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public WorkingUnit addWorkingUnit(long userId, long groupId, String name, String enName, String govAgencyCode,
			long parentWorkingUnitId, String sibling, String address, String cityCode, String districtCode,
			String wardCode, String telNo, String faxNo, String email, String website, boolean isEmployer,
			long managerWorkingUnitId, long siteTemplateId, long employeeTemplateId,
			long partnerTemplateId, long adminUserId, ServiceContext serviceContext) throws Exception {
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
		workingUnit.setAddress(address);
		workingUnit.setTelNo(telNo);
		workingUnit.setFaxNo(faxNo);
		workingUnit.setEmail(email);
		workingUnit.setWebsite(website);

		workingUnit.setExpandoBridgeAttributes(serviceContext);

		workingUnitPersistence.update(workingUnit);

		return workingUnit;
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	public WorkingUnit deleteWorkingUnit(long workingUnitId, ServiceContext serviceContext) throws Exception {

		WorkingUnit workingUnit = workingUnitPersistence.remove(workingUnitId);

		return workingUnit;

	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public WorkingUnit updateWorkingUnit(long userId, long workingUnitId, String name, String enName,
			String govAgencyCode, long parentWorkingUnitId, String sibling, String address, String cityCode,
			String districtCode, String wardCode, String telNo, String faxNo, String email, String website,
			boolean isEmployer, long managerWorkingUnitId, long siteTemplateId,
			long employeeTemplateId, long partnerTemplateId, long adminUserId, ServiceContext serviceContext)
			throws PortalException {
		Date now = new Date();

		User user = userPersistence.findByPrimaryKey(userId);

		WorkingUnit workingUnit = workingUnitPersistence.fetchByPrimaryKey(workingUnitId);

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
		workingUnit.setAddress(address);
		workingUnit.setTelNo(telNo);
		workingUnit.setFaxNo(faxNo);
		workingUnit.setEmail(email);
		workingUnit.setWebsite(website);
		
		workingUnitPersistence.update(workingUnit);

		return workingUnit;
	}

	@SuppressWarnings("deprecation")
	public Hits luceneSearchEngine(LinkedHashMap<String, Object> params, Sort[] sorts, int start, int end,
			SearchContext searchContext) throws ParseException, SearchException {
		// TODO Auto-generated method stub
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

	protected String getTreeIndex(long workingUnitId, long parentWorkingUnitId, String sibling)
			throws SystemException, NoSuchWorkingUnitException {

		if (parentWorkingUnitId == 0) {
			return sibling;
		} else if (parentWorkingUnitId > 0) {
			WorkingUnit parentItem = workingUnitPersistence.findByPrimaryKey(parentWorkingUnitId);
			return parentItem.getTreeIndex() + StringPool.PERIOD + sibling;
		} else {
			throw new NoSuchWorkingUnitException();
		}
	}
}